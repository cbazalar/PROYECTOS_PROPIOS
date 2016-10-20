package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAMDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROL;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLLocator;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;

/**
 * Service para el proceso de Recepcionar Stock Diarios
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
/**
 * @author Sergio Apaza
 */
@Service("sisicc.interfazSAMRecepcionarStockDiarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMRecepcionarStockDiarioServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;
	
	@Resource(name="spusicc.procesoPEDService")
	protected ProcesoPEDService servicePed;

	@Resource(name="genericoService")
	protected GenericoService serviceGen;
		
	protected void addLine(List data, Map row) {
		data.add(row);
	}
	
	protected void processData(InterfazParams interfazParams, List data) throws InterfazException {
		if (data == null || data.size() == 0) {
			throw new InterfazException(Constants.INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA);
		}		
		interfazParams.getQueryParams().put("registrosProcesados", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneos", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneosProducto", new Long(0));
				
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
		String codigoPais =	(String)interfazParams.getQueryParams().get("codigoPais");
		String codigoUsuario=	(String)interfazParams.getQueryParams().get("codigoUsuario");
		String numeroLote=	(String)interfazParams.getQueryParams().get("numeroLote");
		Usuario usuario = (Usuario)interfazParams.getQueryParams().get("usuario");
		
		try {
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoOperacion", Constants.PED_OPERACION_BEL1);
			params.put("numeroLote", numeroLote.substring(2));			
			
			//obtenemos el ultimo numero de movimiento para la Operacion BEL001
			String numeroMovimiento = interfazSAMDAO.getUltimoNumeroMovimiento(params);
			params.put("numeroMovimiento", numeroMovimiento);
			
			//obtenemos el ultimo numero de secuencia para Movimientos de Almacen Cabecera
			String oidSecuenciaCabecera = interfazSAMDAO.getSecuencialMovimientoAlmacen(params);
			params.put("oidSecuenciaCabecera", oidSecuenciaCabecera);
			
			//Insertamos en la cabecera de Movimientos Almacen
			interfazSAMDAO.insertMovimientoAlmacenCabecera(params);
			
			Iterator it = data.iterator();			
			log.info("insertando los registros leidos");
			int i=0;
			int numeroLinea = 1;
			while(it.hasNext()) {
				HashMap row = (HashMap)it.next();  

				try {	
					String codigoAlmacen = (String)row.get("codigoAlmacen");
					String codigoProducto = (String)row.get("codigoProducto");
					String stockLibre = row.get("stockLibre").toString();
					
					params.put("codigoAlmacen", codigoAlmacen);
					params.put("codigoProducto", codigoProducto);
					params.put("cantidad", stockLibre);
					params.put("numeroLinea", String.valueOf(numeroLinea));
					
					interfazSAMDAO.insertMovimientoAlmacenDetalle(params);
					
					numeroLinea++;
					registroProcesado(interfazParams);
				}catch(Exception ex) {
					if(ex.getCause()!=null) {
						Throwable tex = ex.getCause();							
						if(tex.getCause()!=null) {
							String mensaje = tex.getCause().getMessage();
							
							//en caso NULO en campo Oid Producto obtenemos la cantidad de errores que hubo por este tipo
							if(mensaje.indexOf("ORA-01400")>=0 && mensaje.indexOf("PROD_OID_PROD")>0) {
								registroErroneoProducto(interfazParams);
							}
							
							interfazParams.appendLog("Registro Nro: " + (i + 1) + ". " +  mensaje);
							
						} else
							interfazParams.appendLog("Registro Nro: " + (i + 1) + ". " +  tex.getMessage());
					} else {
						interfazParams.appendLog("Registro Nro: " + (i + 1) + ". " +  ex.getMessage());
					}						
					registroErroneo(interfazParams);
				}
				i++;
			}//fin del while						
		}	
		catch (Exception e) {
			e.printStackTrace();
			InterfazException er = new InterfazException(e.getMessage());
			er.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_BASE_DATOS);
			throw er;
		}
		
	}

	/**
	 * Actualiza elr egistro procesado
	 * @param interfazParams
	 */
	private void registroProcesado(InterfazParams interfazParams) {
		Long registrosProcesados = (Long) interfazParams.getQueryParams().get(
				"registrosProcesados");
		registrosProcesados = new Long(registrosProcesados.longValue() + 1);
		interfazParams.getQueryParams().put("registrosProcesados",
				registrosProcesados);
	}

	/**
	 * Procesa el registro erroneo
	 * @param interfazParams
	 */
	private void registroErroneo(InterfazParams interfazParams) {
		Long registrosErroneos = (Long) interfazParams.getQueryParams().get(
				"registrosErroneos");
		registrosErroneos = new Long(registrosErroneos.longValue() + 1);
		interfazParams.getQueryParams().put("registrosErroneos",
				registrosErroneos);
	}

	/**
	 * Procesa el registro erroneo para una insercion NULL en campo de Oid Producto
	 * @param interfazParams
	 */
	private void registroErroneoProducto(InterfazParams interfazParams) {
		Long registrosErroneosProducto = (Long) interfazParams.getQueryParams().get(
				"registrosErroneosProducto");
		registrosErroneosProducto = new Long(registrosErroneosProducto.longValue() + 1);
		interfazParams.getQueryParams().put("registrosErroneosProducto",
				registrosErroneosProducto);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazAbstractService#beforeSaveHistorico(biz.belcorp.ssicc.sisicc.service.beans.InterfazParams)
	 */
	protected void onFinally(InterfazParams interfazParams, InterfazResult interfazResult) {
		String log = interfazParams.getLog();
		if (StringUtils.isNotBlank(log)) {
			Historico historico = interfazParams.getHistorico();
			
			Long registrosErroneosProducto = (Long) interfazParams.getQueryParams().get("registrosErroneosProducto");
			Long registrosErroneos = (Long) interfazParams.getQueryParams().get("registrosErroneos");
			
			if(registrosErroneosProducto.longValue() == registrosErroneos.longValue()) {
				historico.setFlagError(Constants.NO);
				historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_OK);
				interfazResult.setCompletado(true);
			} else {
				historico.setFlagError(Constants.SI);
				historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);
				interfazResult.setCompletado(false);
			}	
			
			historico.setRegistrosProcesados((Long) interfazParams.getQueryParams()
					.get("registrosProcesados"));
			historico.setRegistrosErroneos((Long) interfazParams.getQueryParams()
					.get("registrosErroneos"));
			
			historico.setDescripcionError("Proceso Termino con Errores, revisar archivo Log de Errores");
		}
	}
	
	@Override
	protected void afterExecuteInterfaz(Map params) throws Exception {
		// TODO Auto-generated method stub
		super.afterExecuteInterfaz(params);
		log.debug("Entering 'reload' method");

    	String codigoPais = (String)params.get("codigoPais");
    	String indicadorPROL = (String)params.get("indicadorPROL");
    	String codigoPeriodo = (String)params.get("codigoPeriodo");
    	String tipoCarga = (String)params.get("tipoCarga");
    	Map criteria = new HashMap();

    	criteria.put("codigoPais", codigoPais);
    	criteria.put("codigoPeriodo", codigoPeriodo);

    	//Se actualiza el indicador de activa prol a cero
    	if (Constants.SAM_TIPO_CARGA_BATCH.equals(tipoCarga) &&
    	    Constants.NUMERO_TRES.equals(indicadorPROL)){
    			criteria.put("indicador", Constants.NUMERO_CUATRO);
    			servicePed.executeActualizaIndicadorPROL(criteria);
    	}

    	if (Constants.SAM_TIPO_CARGA_PROL.equals(tipoCarga)){

        	servicePed.executeInicializaStock();

        	//Se actualiza el indicador de activa prol a uno
        	if (Constants.NUMERO_CERO.equals(indicadorPROL)){
        		criteria.put("indicador", Constants.NUMERO_UNO);
        		servicePed.executeActualizaIndicadorPROL(criteria);
        	}
        	/*INICIO CAMBIO LOG*/
        	//Invocando al WebService
        	
        	String indActivaWS = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_IND_ACTIVA_WS_PROL); 
        	log.debug("indActivaWS : "+indActivaWS);
        	if (Constants.NUMERO_UNO.equals(indActivaWS)){
        		 try{
        			 log.info("ActivacioPROL - Invocando al Web Service ");
             		 String codPaisPROL = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_PAIS_PROL);
             		 String codMarcaPROL = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_MARCA_PROL);
             		 String urlWSPROL = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_URL_WS_PROL);
             		 WsPortalesPROL locator = new WsPortalesPROLLocator();
             		 WsPortalesPROLSoap service =locator.getwsPortalesPROLSoap(new java.net.URL(urlWSPROL));
         			 StringHolder indicador = new StringHolder("");
         		 	 StringHolder mensaje = new StringHolder("");
         		 	 log.debug("ActivacioPROL - Parametros: codPaisPROL="+codPaisPROL+ " codMarcaPROL="+ codMarcaPROL);
         			 service.activacionPROL(codPaisPROL, codMarcaPROL,indicador ,mensaje);
         			 log.debug("ActivacioPROL - Resouesta: indicador="+indicador+ " mensaje="+ mensaje);
        		 }catch(Exception e ){
        			 log.error("ActivacioPROL - " + e);
        			 e.printStackTrace();
        		 }
        	}
        	/*FIN CAMBIO LOG*/
    	}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#prepareQueryParams(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	/*public Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
        
		if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'prepareQueryParams'");
        }

		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);

		String tipoCarga = MapUtils.getString(queryParams, "tipoCarga");

		if (Constants.SAM_TIPO_CARGA_PROL.equals(tipoCarga)){
			String nombreArchivo = MapUtils.getString(queryParams, "nombreArchivoCargaPROL");

			queryParams.put("nombreArchivo", nombreArchivo);
			
			// Actualizamos tambien el objeto interfaz
			interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);	
		}

		if (log.isDebugEnabled()) {
			log.debug(queryParams);
            log.debug(interfazParams.getInterfaz().getNombreArchivoEntradaSalida());
		}
		
		return queryParams;
	}*/
}

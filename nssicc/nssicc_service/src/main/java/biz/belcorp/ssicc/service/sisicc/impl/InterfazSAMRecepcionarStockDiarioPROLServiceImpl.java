package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAMDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service para el proceso de Recepcionar Stock Diarios
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
/**
 * @author Sergio Apaza
 */
@Service("sisicc.interfazSAMRecepcionarStockDiarioPROLService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMRecepcionarStockDiarioPROLServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;
		
	protected void addLine(List data, Map row) {
		data.add(row);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.util.List)
	 */
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

}
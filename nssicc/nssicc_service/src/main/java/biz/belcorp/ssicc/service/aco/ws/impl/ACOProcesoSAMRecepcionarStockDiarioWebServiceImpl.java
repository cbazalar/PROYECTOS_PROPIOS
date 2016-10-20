/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoSAMRecepcionarStockDiarioWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROL;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLLocator;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoSAMRecepcionarStockDiarioWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoSAMRecepcionarStockDiarioWebServiceImpl extends
		BaseInterfazHiloAbstractWebService implements
		ACOProcesoSAMRecepcionarStockDiarioWebService {

	MantenimientoPRECambioCodigoVentaService service;
	ProcesoPEDService servicePed;
	GenericoService serviceGen;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		
		service = (MantenimientoPRECambioCodigoVentaService) getWebApplicationContext().getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		servicePed = (ProcesoPEDService) getWebApplicationContext().getBean("spusicc.procesoPEDService");
    	GenericoService serviceGen = (GenericoService) getWebApplicationContext().getBean("genericoService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoSAMRecepcionarStockDiarioWebService#ejecutarProceso(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String tipoCarga, String codigoUsuario) throws RemoteException {
		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.SAM_CODIGO_PROCESO_BATCH_RECEPCIONAR_STOCK_DIARIO;
		String CODIGO_INTERFAZ_PROL = Constants.SAM_CODIGO_INTERFAZ_RECEPCIONAR_STOCK_DIARIO_PROL;
		String CODIGO_INTERFAZ_BATCH = Constants.SAM_CODIGO_INTERFAZ_RECEPCIONAR_STOCK_DIARIO_BATCH;
		String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_SAM;
		String codigoInterfaz = CODIGO_INTERFAZ_BATCH;
		
		if (log.isDebugEnabled()) {
			log.debug("ejecutarProceso");
		}

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)	|| StringUtils.isEmpty(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

		    /**
		     * Valida tipoCarga
		     */
		    if(StringUtils.isBlank(tipoCarga)|| StringUtils.isEmpty(tipoCarga)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarTipoCarga",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
			if (StringUtils.isBlank(codigoUsuario) || StringUtils.isEmpty(codigoUsuario)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
									
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);

			//Obtenemos los parametros adicionales
			Map criteria = new HashMap();

			criteria.put("codigoPais", pais.getCodigo());
			String codigoPeriodo = service.getPeriodoActivo(criteria);
			criteria.put("codigoPeriodo", codigoPeriodo);			
			String indicadorPROL = servicePed.getIndicadorActividadPROL(criteria);
			//
			
			//Validaciones Adicionales
			if(StringUtils.equals(tipoCarga, Constants.NUMERO_UNO)) //SAM-10
			{
				codigoInterfaz = CODIGO_INTERFAZ_BATCH;
				
 	 			if (StringUtils.equals(indicadorPROL, Constants.NUMERO_CERO))
 	 			{
 			    	mensajeError = getWebApplicationContext().getMessage("interfazSAMRecepcionarStockDiarioForm.indicadorPROL.carga.Batch.realizada",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
 		        	throw new Exception(mensajeError); 	 				
 	 			}
 	 			else if (StringUtils.equals(indicadorPROL, Constants.NUMERO_UNO))
 	 			{
 			    	mensajeError = getWebApplicationContext().getMessage("interfazSAMRecepcionarStockDiarioForm.indicadorPROL.carga.activo",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
 		        	throw new Exception(mensajeError); 	 				
 	 			}
 	 			else if (StringUtils.equals(indicadorPROL, Constants.NUMERO_DOS))
 	 			{
 			    	mensajeError = getWebApplicationContext().getMessage("interfazSAMRecepcionarStockDiarioForm.indicadorPROL.carga.Batch.envioSAP",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
 		        	throw new Exception(mensajeError); 	 				
 	 			}
			}
			else if(StringUtils.equals(tipoCarga, Constants.NUMERO_DOS)) //SAM-12
			{
				codigoInterfaz = CODIGO_INTERFAZ_PROL;
				
 	 			if (StringUtils.isBlank(indicadorPROL))
 	 			{
 			    	mensajeError = getWebApplicationContext().getMessage("interfazSAMRecepcionarStockDiarioForm.indicadorPROL.carga.nodisponible",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
 		        	throw new Exception(mensajeError); 	 				
 	 			}				
 	 			else if (StringUtils.equals(indicadorPROL, Constants.NUMERO_UNO))
 	 			{
 			    	mensajeError = getWebApplicationContext().getMessage("interfazSAMRecepcionarStockDiarioForm.indicadorPROL.carga.PROL.realizada",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
 		        	throw new Exception(mensajeError); 	 				
 	 			}
 	 			else if (StringUtils.equals(indicadorPROL, Constants.NUMERO_DOS))
 	 			{
 			    	mensajeError = getWebApplicationContext().getMessage("interfazSAMRecepcionarStockDiarioForm.indicadorPROL.carga.cerrado",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
 		        	throw new Exception(mensajeError); 	 				
 	 			}
 	 			else if (StringUtils.equals(indicadorPROL, Constants.NUMERO_TRES))
 	 			{
 			    	mensajeError = getWebApplicationContext().getMessage("interfazSAMRecepcionarStockDiarioForm.indicadorPROL.carga.PROL.envioSAP",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
 		        	throw new Exception(mensajeError); 	 				
 	 			}
			}			
			//
			
			criteria.put("indicadorPROL", indicadorPROL);
			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
			criteria.put("codigoSistema", CODIGO_SISTEMA);
			criteria.put("codigoInterfaz", codigoInterfaz);
			criteria.put("descripcion", getDescripcionInterfaz(criteria));
			criteria.put("usuario", usuario);	
						
			executionResult = this.executeInterfaz(criteria);

			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);

			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(this.mensajeError);
				log.debug(objetoRespuesta.getMensaje());

				List<ParametroACOWebService> respuestaWebService = new ArrayList<ParametroACOWebService>();

				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroRegistros");
				parametroACOWebService.setValor(String.valueOf(interfazResult.getRegistrosProcesados()));
				respuestaWebService.add(parametroACOWebService);

				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("nombreArchivoEntradaSalida");
				parametroACOWebService.setValor(getNombreArchivoEntradaSalida(interfazResult));
				respuestaWebService.add(parametroACOWebService);

				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroLote");
				parametroACOWebService.setValor(interfazResult.getNumeroLote());
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("indicadorProceso");
				parametroACOWebService.setValor((String) criteria.get("indicadorProceso"));
				respuestaWebService.add(parametroACOWebService);

				objetoRespuesta.setRespuestaWebService(respuestaWebService.toArray(new ParametroACOWebService[respuestaWebService.size()]));
			}


		} catch (Exception e) {
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);
			objetoRespuesta.setEjecucionExitosa(estado);
		} finally {
			log.debug("Estado del servicio: " + estado);
			if (estado) {
				log.info("Se ejecuto el servicio con exito.");
			} else {
				log.error("Excepcion en el servicio.");
			}
		}

		return objetoRespuesta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	@Override
	protected void afterExecuteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) throws Exception {
		
		if(log.isDebugEnabled())
			log.debug("afterExecuteInterfaz");
		
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
             		 log.debug("ActivacioPROL : urlWSPROL="+urlWSPROL);
             		 WsPortalesPROLSoap service =locator.getwsPortalesPROLSoap(new java.net.URL(urlWSPROL));
             		 log.debug("ActivacioPROL : "+ service);
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

}

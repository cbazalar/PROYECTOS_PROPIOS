package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCCCActualizarSaldosSeguimientoLevantamientosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCActualizarSaldosSeguimientoLevantamientosService;
import biz.belcorp.ssicc.service.util.impl.MailUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoCCCActualizarSaldosSeguimientoLevantamientosWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public class ACOProcesoCCCActualizarSaldosSeguimientoLevantamientosWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoCCCActualizarSaldosSeguimientoLevantamientosWebService {

	ProcesoCCCActualizarSaldosSeguimientoLevantamientosService procesoCCCActualizarSaldosSeguimientoLevantamientosService; 
	ReporteService reporteService;
	InterfazSiCCService interfazSiCCService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		procesoCCCActualizarSaldosSeguimientoLevantamientosService = (ProcesoCCCActualizarSaldosSeguimientoLevantamientosService) getWebApplicationContext().getBean("spusicc.procesoCCCActualizarSaldosSeguimientoLevantamientosService");
		reporteService = (ReporteService) getWebApplicationContext().getBean("scsicc.reporteService");
		interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoCCCActualizarSaldosSeguimientoLevantamientosWebService#ejecutarProcesoCCCActualizarSaldosSeguimientoLevantamientos(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoCCCActualizarSaldosSeguimientoLevantamientos(
			String codigoPais, String codigoSociedad, String codigoUsuario)
			throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoCCCActualizarSaldosSeguimientoLevantamientos");
		}
		
		boolean estado = false;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.CCC_CODIGO_PROCESO_BATCH_ACTUALIZACION_SALDOS_PARA_SEGUIMIENTO_LEVANTAMIENTOS;
		String CODIGO_SISTEMA = Constants.CCC_CODIGO_SISTEMA;

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
					    
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoProcesoBatch", CODIGO_BATCH);
			params.put("codigoSistema", CODIGO_SISTEMA);			
			params.put("codigoUsuario", codigoUsuario);
			
			List ssiccSociedadList = interfazSiCCService.getSociedadesByCodigoPais(pais.getCodigo());
			
			/**
			 * Si marca es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoSociedad) || StringUtils.isEmpty(codigoSociedad)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoSociedad",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}else{
				if(ssiccSociedadList.size()!=0){
					if(!existeCodigoEnLista(ssiccSociedadList, codigoSociedad)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoSociedad",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de sociedad vacio.");
				}
			}
			params.put("codigoSociedad", codigoSociedad);
			
			this.executeProceso(params);
		    objetoRespuesta.setEjecucionExitosa(true);

		}catch (Exception e) {			
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);			
			objetoRespuesta.setEjecucionExitosa(estado);
		}finally{
			log.debug("Estado del servicio: " + estado);
			if(estado){
				log.info("Se ejecuto el servicio con exito.");					
			}else{
				log.error("Excepcion en el servicio.");				
			}						
		}	

		return objetoRespuesta;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#executeProcess(java.util.Map)
	 */
	@Override
	protected Map executeProcess(Map params) throws Exception {
		
		procesoCCCActualizarSaldosSeguimientoLevantamientosService.executeProcesarSaldosSeguimientoLevantamientos(params);
		
		return params;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#afterExecuteProcess(java.util.Map)
	 */
	@Override
	protected void afterExecuteProcess(Map params) throws Exception {
		
		log.debug("inicio afterExecuteProcessSuccess");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", MapUtils.getString(params, "codigoPais"));
		criteria.put("nombreReporte","procesoCCCActualizarSaldos"); //nombre del proceso sirve para buscar en la tabla generica de envios de correo
				
		Map paramReporte = reporteService.getParametrosReporte(criteria);
		paramReporte.put("correosDestinos",(String) paramReporte.get("correoDefault"));
		if(paramReporte!=null){
						
			String enviarCorreo = (String) paramReporte.get("enviarCorreo");						
			if (Constants.SI.equals(enviarCorreo)) {
								MailParams mailParams = new MailParams();
								paramReporte.put("parameterMap",criteria);
								String body = (String) paramReporte.get("bodyTxt");
								mailParams.setQueryParams(paramReporte);
								
								MailUtil mailService = (MailUtil) getWebApplicationContext().getBean(this.getMailService()); 
								criteria.put("bodyTxt", body);
								mailService.enviarMail(mailParams);	
				
			} 				
		}
				
		log.debug("Finalizando afterExecuteProcessSuccess");
		
		super.afterExecuteProcess(params);
	}

	/**
	 * Devuelve Service a trabajar para el envio de correo del reporte
	 * Dicho metodo debe ser sobreescrito para que devuelva el Service correspondiente al reporte en
	 * ejecuci�n	
	 * @return
	 */
	private String getMailService () {
		String service = "sisicc.mailUtil";// "sisicc.baseMailAbstractService";		
		return service;
	}	

}

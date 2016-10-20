/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoPRIGenerarSolicitudesPrivilegeWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.scdf.PremioService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.impl.MailProcesoPRIGenerarSolicitudesPrivilegeImpl;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoPRIGenerarSolicitudesPrivilegeWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoPRIGenerarSolicitudesPrivilegeWebService {

	private static final String CODIGO_SISTEMA = "PRI";
	private static final String CODIGO_PROCESO_BATCH = "02";
	
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	PremioService premioService;
	InterfazSiCCService interfazSiCCService;
	ReporteService reporteService;
	BaseMailService mailProcesoPRIGenerarSolicitudesPrivilege;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.premioService = (PremioService) getWebApplicationContext().getBean("scdf.premioService");
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.reporteService = (ReporteService) getWebApplicationContext().getBean("scsicc.reporteService");
		this.mailProcesoPRIGenerarSolicitudesPrivilege = (BaseMailService) getWebApplicationContext().getBean("sic.mailReporteGenerarPedidosDigitadosZona");
		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#executeProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, java.util.Map)
	 */
	protected Map executeProcess(Map params) throws Exception {
		log.info("Entro a ACOProcesoPRIGenerarSolicitudesPrivilegeWebServiceImpl - executeProcess");
		
		String codigoPais = MapUtils.getString(params, "codigoPais");
        String codigoPeriodo = MapUtils.getString(params, "codigoPeriodo");
        Usuario usuario = (Usuario)MapUtils.getObject(params, "usuario");
         
        if(log.isDebugEnabled()) {
            log.debug("Codigo de Pais: " + codigoPais);
        }
        
        // Obtenemos la referencia al service
        Pais pais = this.paisService.getPais(codigoPais);
        String fechaProceso = (String) params.get("fechaProceso");
        
 		Map paramsBatch = (Map)params.get("procesoBatchParams");
 		paramsBatch.put("codigoPais", codigoPais);
 		paramsBatch.put("codigoUsuario", usuario.getLogin());
 		paramsBatch.put("codigoPeriodo",codigoPeriodo);
 		paramsBatch.put("fechaProceso",fechaProceso);
 		
 		this.premioService.generarSolicitudesAtencionPrivilege(pais, codigoPeriodo, usuario, paramsBatch);
 		
        return params;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#afterExecuteProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void afterExecuteProcess(Map params) throws Exception {
		log.info("Entro a ProcesoPRIGenerarSolicitudesPrivilegeAction - afterExecuteProcess");
		
		//-- Variables
		HashMap mapeo = null;
		String oidPeriodo = new String();
		
		//-- Logica de capturar Periodo y Fecha Proceso Actual ----------------
		
		//-- Pojo
		Map criteria = new HashMap();
		String codigoPais = (String) params.get("codigoPais");
		criteria.put("codigoPais", codigoPais);
		
		//-- Logica 
		List l_interfaz = this.interfazSiCCService.getPeriodoFechaProcesoActual(criteria);
		mapeo = (HashMap)l_interfaz.get(0);
		
		//-- Logica de capturar Oid Periodo -----------------------------------
		
		//-- Pojo
		criteria = new HashMap();
		criteria.put("codigoPeriodo", mapeo.get("cod_peri").toString());
		
		//-- Logica - oidPeriodo
		oidPeriodo = this.reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
		
		//-- Logica de capturar reporte ---------------------------------------
		
		//-- Pojo
		criteria = new HashMap();
		criteria.put("oidPeriodo", oidPeriodo);
		criteria.put("fechaProceso", mapeo.get("fec_proc").toString());
		
		//-- Logica
		List lista = this.reporteService.getReportePRIGenerarSolicitudesPrivilege(criteria);

		//-- Logica de correo -------------------------------------------------	
		
		//-- Pojo
		criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("nombreReporte","reportePRIGenerarSolicitudesPrivilegeXLS");
		
		//-- Retornos
		Map paramReporte = this.reporteService.getParametrosReporte(criteria);
		if(lista.size() < 1){
			log.info("Entro a ProcesoPRIGenerarSolicitudesPrivilegeAction - afterExecuteProcess - envioCorreoSinAdjunto");
			
			//-- Logica
			paramReporte.put("correosDestinos",(String) paramReporte.get("correoDefault"));
			paramReporte.put("bodyTxt","MailTxtReportePRIGenerarSolicitudesPrivilege2.vm");
			paramReporte.put("bodyHtml","MailHtmlReportePRIGenerarSolicitudesPrivilege2.vm");
			if(paramReporte!=null){
							
				String enviarCorreo = (String) paramReporte.get("enviarCorreo");						
				if (Constants.SI.equals(enviarCorreo)) {
					
					//-- Variables
					MailParams mailParams = new MailParams();
					
					//-- Configurar Parametros
					paramReporte.put("parameterMap",criteria);
					mailParams.setQueryParams(paramReporte);
					
					//-- Envio de correo
					//mailProcesoPRIGenerarSolicitudesPrivilege.getFrom(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getEnviarCorreoElectronico(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getEnviarCC(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getSubject(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getBodyHtml(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getBodyTxt(mailParams);
					mailProcesoPRIGenerarSolicitudesPrivilege.enviarMail(mailParams);
					
				} 				
			}
			
		}else{
			log.info("Entro a ProcesoPRIGenerarSolicitudesPrivilegeAction - afterExecuteProcess - envioCorreoConAdjunto");
			
			//-- Logica
			paramReporte.put("correosDestinos",(String) paramReporte.get("correoDefault"));
			if(paramReporte!=null){
							
				String enviarCorreo = (String) paramReporte.get("enviarCorreo");						
				if (Constants.SI.equals(enviarCorreo)) {
					
					//-- Variables
					MailParams mailParams = new MailParams();
					
					//-- Configurar Parametros
					criteria.put("listaReporte",lista);
					paramReporte.put("parameterMap",criteria);
					mailParams.setQueryParams(paramReporte);
					
					//-- Envio de correo
					//mailProcesoPRIGenerarSolicitudesPrivilege.getFrom(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getEnviarCorreoElectronico(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getEnviarCC(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getSubject(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getBodyHtml(mailParams);
					//mailProcesoPRIGenerarSolicitudesPrivilege.getBodyTxt(mailParams);
					mailProcesoPRIGenerarSolicitudesPrivilege.enviarMail(mailParams);
					
				} 				
			}

		}

		log.info("Salio a ProcesoPRIGenerarSolicitudesPrivilegeAction - afterExecuteProcess");
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.aco.ws.ProcesoACOWebService#
	 * ejecutarProcesoPRIGenerarSolicitudesPrivilege(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoPRIGenerarSolicitudesPrivilege(
			String codigoPais, String codigoPeriodo, String fechaProceso,
			String codigoUsuario) throws RemoteException {
		String mensajeError = "";
		ACOWebServiceResponse resultado = new ACOWebServiceResponse();
		PedidoControlFacturacion pedidoControlFacturacion = null;

		String codigoSistema = CODIGO_SISTEMA;
		String codigoProcesoBatch = CODIGO_PROCESO_BATCH;
		try {
			Pais pais = this.paisService.getPais(codigoPais);

			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			if (StringUtils.isBlank(codigoUsuario)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			Map mapControlFacturacion = new HashMap();
			mapControlFacturacion.put("codigoPais", codigoPais);
			mapControlFacturacion.put("estadoCampanha", Constants.NUMERO_CERO);
			mapControlFacturacion.put("indicadorActiva", Constants.ESTADO_ACTIVO);

			pedidoControlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(mapControlFacturacion);

			if (StringUtils.isBlank(codigoPeriodo)) {
				codigoPeriodo = pedidoControlFacturacion.getCodigoPeriodo();
			}

			if (StringUtils.isBlank(fechaProceso)) {
				fechaProceso = pedidoControlFacturacion.getFechaProceso();
			}

			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario) ;
					//this.usuarioService.getUsuarioByUsername(codigoUsuario);

			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoSistema", codigoSistema);
			params.put("codigoProcesoBatch", codigoProcesoBatch);
			params.put("codigoUsuario", codigoUsuario);
			params.put("codigoPeriodo", codigoPeriodo);
			params.put("fechaProceso", fechaProceso);

			// ejecuta proceso
			this.executeProceso(params);
			
			resultado.setEjecucionExitosa(true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resultado.setEjecucionExitosa(false);
			resultado.setMensaje(e.getMessage());
		}
		return resultado;
	}



}

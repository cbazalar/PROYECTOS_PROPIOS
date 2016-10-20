package biz.belcorp.ssicc.web.scdf.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.collections.MapUtils;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scdf.InterfazSiCCService;
import biz.belcorp.ssicc.service.scdf.PremioService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.impl.MailProcesoPRIGenerarSolicitudesPrivilegeImpl;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.scdf.form.ProcesoPRIGenerarSolicitudesPrivilegeForm;

@SessionScoped
@ManagedBean
public class ProcesoPRIGenerarSolicitudesPrivilegeAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6887838866394631433L;
	private List priPremiosEstadoConsultoraList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoPRIGenerarSolicitudesPrivilegeForm form = new ProcesoPRIGenerarSolicitudesPrivilegeForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub

    	String codigoPais = MapUtils.getString(params, "codigoPais");
        String codigoPeriodo = MapUtils.getString(params, "codigoPeriodo");
        Usuario usuario = (Usuario)MapUtils.getObject(params, "usuario");
         
        if(log.isDebugEnabled()) {
            log.debug("Codigo de Pais: " + codigoPais);
        }
        
        // Obtenemos la referencia al service
        InterfazSiCCService siccService = (InterfazSiCCService) getBean("scdf.interfazSiCCService");
        PaisService paisService = (PaisService)getBean("paisService");
        Pais pais = paisService.getPais(codigoPais);
        
        ProcesoPRIGenerarSolicitudesPrivilegeForm f = (ProcesoPRIGenerarSolicitudesPrivilegeForm) this.formProceso;

 		Map paramsBatch = (Map)params.get("procesoBatchParams");
 		paramsBatch.put("codigoPais", codigoPais);
 		paramsBatch.put("codigoUsuario", usuario.getLogin());
 		paramsBatch.put("codigoPeriodo",codigoPeriodo);
 		paramsBatch.put("fechaProceso",f.getFechaProceso());

 		PremioService premioService = (PremioService) getBean("scdf.premioService");
 		premioService.generarSolicitudesAtencionPrivilege(pais, codigoPeriodo, usuario, paramsBatch);
 		
    	return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
	
		Map criteriaPeriodo = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		
		ProcesoPRIGenerarSolicitudesPrivilegeForm f = (ProcesoPRIGenerarSolicitudesPrivilegeForm) this.formProceso;
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());		
		f.setFechaProceso(controlFacturacion.getFechaProceso());
		f.setFechaProcesoD(DateUtil.convertStringToDate(f.getFechaProceso()));
		this.mostrarBotonBuscar=true;
		
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		
		log.debug("setFindAttributes.");
		
		ProcesoPRIGenerarSolicitudesPrivilegeForm f = (ProcesoPRIGenerarSolicitudesPrivilegeForm)this.formProceso;
		f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoD()));
		PremioService premioService = (PremioService) getBean("scdf.premioService");
				Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("fechaInicio",f.getFechaProceso());
		
		List estadosPremiosList =  premioService.getCantidadPremiosxEstado(criteria);
		this.priPremiosEstadoConsultoraList=estadosPremiosList;
		//Integer cantidad = premioService.getConsultaPremios();
		Integer cantidad = premioService.getNumeroSolicitudesGeneradas();		
		f.setCantidadPremios(Integer.toString(cantidad));
		
	    return estadosPremiosList;		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#afterExecuteProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	public void afterExecuteProcess() {
		log.info("Entro a ProcesoPRIGenerarSolicitudesPrivilegeAction - afterExecuteProcess");
		
		//-- Variables
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		biz.belcorp.ssicc.service.sisicc.InterfazSiCCService interfazSiCCService = (biz.belcorp.ssicc.service.sisicc.InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		ProcesoPRIGenerarSolicitudesPrivilegeForm f = (ProcesoPRIGenerarSolicitudesPrivilegeForm) this.formProceso;
		HashMap mapeo = null;
		String oidPeriodo = new String();
		
		//-- Logica de capturar Periodo y Fecha Proceso Actual ----------------
		
		//-- Pojo
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		
		//-- Logica 
		List l_interfaz = interfazSiCCService.getPeriodoFechaProcesoActual(criteria);
		mapeo = (HashMap)l_interfaz.get(0);
		
		//-- Logica de capturar Oid Periodo -----------------------------------
		
		//-- Pojo
		criteria = new HashMap();
		criteria.put("codigoPeriodo", mapeo.get("cod_peri").toString());
		
		//-- Logica - oidPeriodo
		oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
		
		//-- Logica de capturar reporte ---------------------------------------
		
		//-- Pojo
		criteria = new HashMap();
		criteria.put("oidPeriodo", oidPeriodo);
		criteria.put("fechaProceso", mapeo.get("fec_proc").toString());
		
		//-- Logica
		List lista = reporteService.getReportePRIGenerarSolicitudesPrivilege(criteria);

		//-- Logica de correo -------------------------------------------------	
		
		//-- Pojo
		criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("nombreReporte","reportePRIGenerarSolicitudesPrivilegeXLS");
		
		//-- Retornos
		Map paramReporte = reporteService.getParametrosReporte(criteria);
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
					MailProcesoPRIGenerarSolicitudesPrivilegeImpl mailProcesoPRIGenerarSolicitudesPrivilege = (MailProcesoPRIGenerarSolicitudesPrivilegeImpl) getBean("sic.mailReporteGenerarPedidosDigitadosZona");
					try {
						mailProcesoPRIGenerarSolicitudesPrivilege.getFrom(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getEnviarCorreoElectronico(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getEnviarCC(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getSubject(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getBodyHtml(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getBodyTxt(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.enviarMail(mailParams);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
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
					MailProcesoPRIGenerarSolicitudesPrivilegeImpl mailProcesoPRIGenerarSolicitudesPrivilege = (MailProcesoPRIGenerarSolicitudesPrivilegeImpl) getBean("sic.mailReporteGenerarPedidosDigitadosZona");
					try {
						mailProcesoPRIGenerarSolicitudesPrivilege.getFrom(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getEnviarCorreoElectronico(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getEnviarCC(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getSubject(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getBodyHtml(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.getBodyTxt(mailParams);
						mailProcesoPRIGenerarSolicitudesPrivilege.enviarMail(mailParams);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} 				
			}

		}

		log.info("Salio a ProcesoPRIGenerarSolicitudesPrivilegeAction - afterExecuteProcess");
	}

	/**
	 * @return the priPremiosEstadoConsultoraList
	 */
	public List getPriPremiosEstadoConsultoraList() {
		return priPremiosEstadoConsultoraList;
	}

	/**
	 * @param priPremiosEstadoConsultoraList the priPremiosEstadoConsultoraList to set
	 */
	public void setPriPremiosEstadoConsultoraList(
			List priPremiosEstadoConsultoraList) {
		this.priPremiosEstadoConsultoraList = priPremiosEstadoConsultoraList;
	}
	
	

    

}

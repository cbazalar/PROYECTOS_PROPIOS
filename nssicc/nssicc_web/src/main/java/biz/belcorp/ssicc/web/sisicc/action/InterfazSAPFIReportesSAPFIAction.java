/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazRECService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAPFIReportesSAPFIForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarAdministracionFlujosAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazSAPFIReportesSAPFIAction extends BaseInterfazAbstractAction {
	
	private String formaEnvio;
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 2610233430392472561L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAPFIReportesSAPFIForm interfazSAPFIReportesSAPFIForm = new InterfazSAPFIReportesSAPFIForm();
		return interfazSAPFIReportesSAPFIForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		InterfazSAPFIReportesSAPFIForm f = (InterfazSAPFIReportesSAPFIForm)formInterfaz;
		f.setFechaProcesoD(new Date(System.currentTimeMillis()));
		
		this.ejecucionEnHilo = false;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		params =  super.prepareParamsBeforeExecute(params, form);
		this.ejecucionEnHilo = false;
    	Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
    	
		params.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
		params.put("fechaProceso", ((InterfazSAPFIReportesSAPFIForm) formInterfaz).getFechaProceso());
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("usuario",usuario);
		
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		params.put("pais",pais);
		params.put("formaEnvio", this.formaEnvio);
		
		return params;
	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult)
     */
    @Override
    protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception {
    	String formaEnvio=(String)params.get("formaEnvio");
    	if(StringUtils.isNotBlank(formaEnvio)&&StringUtils.equals(formaEnvio, "CORREO")){
			return ;
		}
		else{
			 String archivo = (String) params.get("nombreArchivo");
	   		 String extension = (String) params.get("extensionArchivo");
	   		 String directorio = (String) params.get("directorioArchivo");
	   		 String nombreArchivoDescarga = (String) params.get("nombreArchivoDescarga");
	   	 	 String filePath = directorio + archivo + extension; 
	   	 	 
	   		 ServletContext servletContext = this.getServletContext();
             String contentType = servletContext.getMimeType(filePath);
             
             if (this.getRequest().getParameter("contentType") != null) {
               contentType = this.getRequest().getParameter("contentType");
             }
             System.out.println(" ---> filePath "+filePath + " contentType "+ contentType);
             
             HttpServletResponse response = this.getResponse();
             response.reset();
             response.setContentType(contentType);
             
             if(StringUtils.isNotBlank(nombreArchivoDescarga)) {
            	 response.setHeader("Content-disposition", "attachment; filename=" + nombreArchivoDescarga);
            	 this.generarResponseOutputStream(nombreArchivoDescarga);
             }
             else {
            	 response.setHeader("Content-disposition", "attachment; filename=" + getNameFile(filePath));
            	 this.generarResponseOutputStream(filePath);
             }
         
		}
    }
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#setValidarInterfaz()
	 */
	@Override
	public String setValidarInterfaz() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		this.formaEnvio = externalContext.getRequestParameterMap().get("parametroAccion");
		return null;
	}
	

}

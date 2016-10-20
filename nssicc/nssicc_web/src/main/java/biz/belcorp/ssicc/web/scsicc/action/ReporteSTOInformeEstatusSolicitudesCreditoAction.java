//TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEnviarInformeEstatusSolicitudesCreditoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOInformeEstatusSolicitudesCreditoForm;

@ManagedBean
@SessionScoped
public class ReporteSTOInformeEstatusSolicitudesCreditoAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = 461328607812475919L;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOInformeEstatusSolicitudesCreditoForm reporteForm = new ReporteSTOInformeEstatusSolicitudesCreditoForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteSTOInformeEstatusSolicitudesCreditoForm f = (ReporteSTOInformeEstatusSolicitudesCreditoForm) this.formReporte;
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		f.setFechaInicio("");
		f.setFechaFin("");	
		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteSTOInformeEstatusSolicitudesCreditoForm form = (ReporteSTOInformeEstatusSolicitudesCreditoForm)this.formReporte;
	    if (form.getFechaFinD().compareTo(form.getFechaInicioD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }				
	    return null;
	}


	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReporteSTOInformeEstatusSolicitudesCreditoForm f = (ReporteSTOInformeEstatusSolicitudesCreditoForm) this.formReporte;
		this.formatoExportacion = f.getFormatoExportacion();
		
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		params.put("fechaInicio", f.getFechaInicio());
		params.put("fechaFin", f.getFechaFin());
        return params;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSTOInformeEstatusSolicitudesCreditoForm form = (ReporteSTOInformeEstatusSolicitudesCreditoForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if (StringUtils.equals(formReporte.getFormatoExportacion(), "XLS"))
//		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteSTOInformeEstatusSolicitudesCreditoXLS";
		else
			return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}
	
	/**
	 * @param event
	 */
	public void sendReporte(ActionEvent event){
		try {
			Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario user= this.mPantallaPrincipalBean.getCurrentUser();
			ReporteSTOInformeEstatusSolicitudesCreditoForm form = (ReporteSTOInformeEstatusSolicitudesCreditoForm) this.formReporte;
			ProcesoSTOEnviarInformeEstatusSolicitudesCreditoService service = (ProcesoSTOEnviarInformeEstatusSolicitudesCreditoService) getBean("spusicc.procesoSTOEnviarInformeEstatusSolicitudesCreditoService");
			
			this.formatoExportacion = "XLS";
			Map params = BeanUtils.describe(form); 
			params.put("usuarioTemp", user);
			params.put("pais", pais);
			params.put("codigoPais", pais.getCodigo());
			params = this.prepareParameterMap(params);
			this.formatoExportacion = "XLS";
			params.put("formatoExportacion", this.formatoExportacion);
			
			//params.put("rutaPath", httpServletRequest.getRealPath("/"));
			String status = service.executeEnviarReportes(params);
			
			if(StringUtils.equals(status, Constants.OK))            
	            this.addInfo("Info:", this.getResourceMessage("reporteSTOInformeEstatusSolicitudesCreditoForm.envio.ok.msg"));	
			else
				this.addError("Error:", this.getResourceMessage("reporteSTOInformeEstatusSolicitudesCreditoForm.envio.error.msg"));
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	
		
          
	}

	


}

package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMSGValidacionesMensajesForm;

/**
 * The Class ReporteMSGValidacionesMensajesAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 25/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteMSGValidacionesMensajesAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -7766049885913050501L;
	//-- Variables instancia
	private String formatoReporte;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMSGValidacionesMensajesForm form = new ReporteMSGValidacionesMensajesForm();
		return form;
	}	
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteMSGValidacionesMensajesAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte = ((ReporteMSGValidacionesMensajesForm)this.formReporte).getFormatoExportacion();
		if ("XLS".equals(formatoReporte)) {
			return "reporteMSGValidacionesMensajesXLS";
		} else {
			return "reporteMaestroHorizontal";
		}	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (("PDF".equals(formatoReporte))||("VPDF".equals(formatoReporte))) {
			return "reporteMSGValidacionesMensajesPDF";
		}
		return "";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.info("Entro a ReporteMSGValidacionesMensajesAction - prepareParameterMap");
		
		//-- Variables
		ReporteMSGValidacionesMensajesForm f = (ReporteMSGValidacionesMensajesForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		
		//-- Logica
		params.put("NroReporte", " ");
		params.put("titulo", getResourceMessage("reporteMSGValidacionesMensajesForm.title"));
		
		log.info("Salio a ReporteMSGValidacionesMensajesAction - prepareParameterMap");
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
	
}
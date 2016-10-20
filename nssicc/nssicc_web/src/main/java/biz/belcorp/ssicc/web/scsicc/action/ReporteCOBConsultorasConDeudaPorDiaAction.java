package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBConsultorasConDeudaPorDiaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOBConsultorasConDeudaPorDiaAction extends
		BaseReporteAbstractAction {
	private String formatoReporte;

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBConsultorasConDeudaPorDiaForm reporteForm = new ReporteCOBConsultorasConDeudaPorDiaForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {
		if ("PDF".equals(this.formatoReporte))
			return "reporteMaestroVertical";
		else{
			return "reporteCOBConsultorasConDeudaPorDiaXLS";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(this.formatoReporte))
			return "reporteCOBConsultorasConDeudaPorDiaPDF";
		else
			return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOBConsultorasConDeudaPorDiaForm.setViewAtributes' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		ReporteCOBConsultorasConDeudaPorDiaForm reporteOCRForm = (ReporteCOBConsultorasConDeudaPorDiaForm) this.formReporte;
		reporteOCRForm.setNumeroDias("21");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteAPETotalArticulosAFPAction.prepareParameterMap' method");
		}
		ReporteCOBConsultorasConDeudaPorDiaForm f = (ReporteCOBConsultorasConDeudaPorDiaForm) this.formReporte;
		setGenerateTabsXLS(true);
		this.formatoReporte = f.getFormatoExportacion();
		params.put("NroReporte", "");
		params.put("numeroDias", new Integer(f.getNumeroDias()));
		
		String titulo = getReportResourceMessage("reporteCOBConsultorasConDeudaPorDia.titulo");
		titulo+= "\n" + getReportResourceMessage("reporteCOBConsultorasConDeudaPorDia.dias") + f.getNumeroDias();
		
		params.put("titulo", titulo);
		return params;
	}
}
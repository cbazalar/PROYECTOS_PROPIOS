package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOSCierreCostosForm;

@ManagedBean
@SessionScoped
public class ReporteCOSCierreCostosAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8828978247757479367L;
	private String formatoReporte;
	private String tipoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOSCierreCostosForm reporteForm = new ReporteCOSCierreCostosForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("----- in setViewAttributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		log.debug("Todo OK: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCOSCierreCostosForm form = (ReporteCOSCierreCostosForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "ReporteCOSCierreCostos" + tipoReporte + "XLS";
		else
			return "reporteMaestroVertical";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteCOSCierreCostosForm f = (ReporteCOSCierreCostosForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		tipoReporte = f.getCodigoReporte();
		String oidSecuenciaCCVta = "";
		String oidSecuenciaCCDev = "";

		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
        params.put("oidPais",reporteService.getOidString("getOidPaisByCodigoPais", params));
        
		params.put("codigoMesActual",f.getCodigoAnhoMes());
		
	
		
		log.info("Salio a ReporteCOSCierreCostosAction - prepareParameterMap");
		return params;
	}

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

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

}

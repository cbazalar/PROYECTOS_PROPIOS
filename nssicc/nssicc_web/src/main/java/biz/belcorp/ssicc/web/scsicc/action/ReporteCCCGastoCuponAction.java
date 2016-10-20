package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCGastoCuponForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


@ManagedBean
@SessionScoped
public class ReporteCCCGastoCuponAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8066502758836871618L;
	private String formatoReporte;
	private String tipoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCGastoCuponForm reporteForm = new ReporteCCCGastoCuponForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteOCSV = true;
		this.mostrarReporteXLS= true;
		this.mostrarReportePDF = false;

		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method - ReporteCCCGastoCuponAction");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCGastoCuponService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {

		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteCCCGastoCupon" + tipoReporte + "XLS";
		else
			return " ";
		
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method - ReporteCCCGastoCuponAction");
		}
		
		ReporteCCCGastoCuponForm f = (ReporteCCCGastoCuponForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		
		tipoReporte = f.getTipoReporte();
		params.put("tiporeporte", f.getTipoReporte());
		params.put("formatoexportacion", f.getFormatoExportacion());
		
		String fechaDesde = DateUtil.convertDateToString(f.getFechaDesdeD());
		String fechaHasta = DateUtil.convertDateToString(f.getFechaHastaD());
		
		params.put("fechaDesde", fechaDesde);
		params.put("fechaHasta", fechaHasta);
		log.info("Salio a ReporteCCCGastoCuponAction - prepareParameterMap");
		return params;
	}
	
	public String setValidarReporte() {
		ReporteCCCGastoCuponForm form = (ReporteCCCGastoCuponForm) this.formReporte;	    
		if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }
	    					
	    return null;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
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

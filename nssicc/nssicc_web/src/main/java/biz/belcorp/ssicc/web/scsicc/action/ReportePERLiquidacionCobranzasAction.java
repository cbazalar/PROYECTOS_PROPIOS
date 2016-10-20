//TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERLiquidacionCobranzasForm;

@ManagedBean
@SessionScoped
public class ReportePERLiquidacionCobranzasAction extends
		BaseReporteAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7521118584500251560L;

	private String formatoReporte;
	private List siccSociedadList;
	private List siccBancoList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERLiquidacionCobranzasForm reporteForm = new ReportePERLiquidacionCobranzasForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		ReportePERLiquidacionCobranzasForm f = (ReportePERLiquidacionCobranzasForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		siccSociedadList = service.getSociedadesByCodigoPais(codpais);

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		siccBancoList = service
				.getCuentasCorrientesByPaisBancoSociedad(criteria);

	}

	public String setValidarReporte() {
		ReportePERLiquidacionCobranzasForm form = (ReportePERLiquidacionCobranzasForm) this.formReporte;
		if (form.getFechaPagoHastaD() != null
				&& form.getFechaPagoDesdeD() != null) {
			if (form.getFechaPagoHastaD().compareTo(form.getFechaPagoDesdeD()) < 0) {
				String mensaje = this
						.getResourceMessage("errors.compare.dates");
				return mensaje;
			}
		}
		if (form.getFechaProcHastaD() != null
				&& form.getFechaProcDesdeD() != null) {
			if (form.getFechaProcHastaD().compareTo(form.getFechaProcDesdeD()) < 0) {
				String mensaje = this
						.getResourceMessage("errors.compare.dates");
				return mensaje;
			}

		}

		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReportePERLiquidacionCobranzasForm f = (ReportePERLiquidacionCobranzasForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();

		params.put("NroReporte", "");
		params.put("titulo",
				getResourceMessage("reportePERLiquidacionCobranzasForm.title"));
		f.setFechaPagoDesde(DateUtil.convertDateToString(f.getFechaPagoDesdeD()));
		f.setFechaPagoHasta(DateUtil.convertDateToString(f.getFechaPagoHastaD()));
		f.setFechaProcDesde(DateUtil.convertDateToString(f.getFechaProcDesdeD()));
		f.setFechaProcHasta(DateUtil.convertDateToString(f.getFechaProcHastaD()));
		params.put("fechaPagoDesde",f.getFechaPagoDesde());
		params.put("fechaPagoHasta",f.getFechaPagoHasta());
		params.put("fechaProcDesde",f.getFechaProcDesde());
		params.put("fechaProcHasta",f.getFechaProcHasta());
		log.info("Salio a ReportePERLiquidacionCobranzasAction - prepareParameterMap");
		return params;

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePERLiquidacionCobranzasForm form = (ReportePERLiquidacionCobranzasForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reportePERLiquidacionCobranzasPERDZXLS";
		else
			return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
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
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccBancoList
	 */
	public List getSiccBancoList() {
		return siccBancoList;
	}

	/**
	 * @param siccBancoList
	 *            the siccBancoList to set
	 */
	public void setSiccBancoList(List siccBancoList) {
		this.siccBancoList = siccBancoList;
	}

}

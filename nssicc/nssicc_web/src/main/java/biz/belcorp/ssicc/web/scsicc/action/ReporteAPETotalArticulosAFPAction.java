package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPETotalArticulosAFPForm;


@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteAPETotalArticulosAFPAction extends
		BaseReporteAbstractAction {
	private String formatoReporte;
	private List tipoReporteList;
	private List siccMarcaList;
	private List siccCentrodList;
	private List siccLineaList;
	private List siccTipoSolList;

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
	 * @return
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return
	 */
	public List getSiccCentrodList() {
		return siccCentrodList;
	}

	/**
	 * @param siccCentrodList
	 */
	public void setSiccCentrodList(List siccCentrodList) {
		this.siccCentrodList = siccCentrodList;
	}

	/**
	 * @return
	 */
	public List getSiccLineaList() {
		return siccLineaList;
	}

	/**
	 * @param siccLineaList
	 */
	public void setSiccLineaList(List siccLineaList) {
		this.siccLineaList = siccLineaList;
	}

	/**
	 * @return
	 */
	public List getSiccTipoSolList() {
		return siccTipoSolList;
	}

	/**
	 * @param siccTipoSolList
	 */
	public void setSiccTipoSolList(List siccTipoSolList) {
		this.siccTipoSolList = siccTipoSolList;
	}

	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return
	 */
	public List getTipoReporteList() {
		return tipoReporteList;
	}

	/**
	 * @param tipoReporteList
	 */
	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
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
		ReporteAPETotalArticulosAFPForm reporteForm = new ReporteAPETotalArticulosAFPForm();
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

		ReporteAPETotalArticulosAFPForm reporteForm = (ReporteAPETotalArticulosAFPForm) this.formReporte;
		this.formatoReporte = reporteForm.getFormatoExportacion();

		if (this.formatoReporte.equals("XLS") || this.formatoReporte.equals("CSV")
				|| this.formatoReporte.equals("XLSX")) {
			return "reporteAPETotalArticulosAFPXLS";
		} else
			return "reporteMaestroVertical";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteAPETotalArticulosAFPPDF";
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
			this.log.debug("Entering 'ReporteAPETotalArticulosAFPForm.setViewAtributes' method");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		ReporteAPETotalArticulosAFPForm form = (ReporteAPETotalArticulosAFPForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoPais(pais.getCodigo());
		this.siccMarcaList = service.getFacturacion(criteria);
		this.siccCentrodList = service.getCentroDistribucionByPais(criteria);
		this.siccLineaList = service.getLinea(criteria);
		this.siccTipoSolList = service.getSublineaxLinea(criteria);
		this.log.debug("Paises" + form.getCodigoMarca() + form.getCodigoPais());

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
		ReporteAPETotalArticulosAFPForm reporteForm = (ReporteAPETotalArticulosAFPForm) this.formReporte;
		params.put("NroReporte", " "); // ReporteCOMComisionIngresoAction
		params.put(
				"titulo",
				this.getReportResourceMessage("reporteAPETotalArticulosAFPForm.titulo"));
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String valorFecha = DateUtil.getDate(reporteForm
				.getFechaFacturacionDt());
		reporteForm.setFechaFacturacion(valorFecha);
		params.put("codigoPais", pais.getCodigo());
		params.put("fechaFacturacion", valorFecha);
		params.put("marca", Constants.CODIGO_MARCA_DEFAULT);// listo
		params.put("canal", Constants.CODIGO_CANAL_DEFAULT);// listo
		params.put("codigoLinea", reporteForm.getCodigoLinea());
		params.put("campana", reporteForm.getCodigoPeriodo());
		params.put("codigoLote", reporteForm.getCodigoLote());
		params.put("codsublinea", reporteForm.getCodsublinea());
		params.put("centro", reporteForm.getCodigoCentro());
		return params;

	}

}
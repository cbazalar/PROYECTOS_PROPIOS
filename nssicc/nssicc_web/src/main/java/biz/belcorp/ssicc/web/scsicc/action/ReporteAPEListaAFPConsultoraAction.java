package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEListaAFPConsultoraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteAPEListaAFPConsultoraAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = 5452137025798023586L;
	
	private String formatoReporte;
	private List tipoReporteList;
	private List siccMarcaList;
	private List siccCentrodList;
	private List siccLineaList;
	private List siccTipoSolList;	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteAPEListaAFPConsultoraForm reporteForm = new ReporteAPEListaAFPConsultoraForm();
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
		return "reporteAPEListaAFPConsultoraPDF";
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
			this.log.debug("Entering 'ReporteAPEListaAFPConsultoraForm.setViewAtributes' method");
		}

		this.mostrarReportePDF = true;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteAPEListaAFPConsultoraForm form = (ReporteAPEListaAFPConsultoraForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		//reset()
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		if (StringUtils.isEmpty(form.getCodigoPeriodo()))
			form.setCodigoPeriodo(periodo);
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setCodigoPais(pais.getCodigo());
		//
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		this.siccMarcaList = service.getFacturacion(criteria);
		this.siccCentrodList = service.getCentroDistribucionByPais(criteria);
		this.siccLineaList = service.getLinea(criteria);
		this.siccTipoSolList = service.getSublineaxLinea(criteria);
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
		ReporteAPEListaAFPConsultoraForm reporteAPEForm = (ReporteAPEListaAFPConsultoraForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String valorFecha = DateUtil.getDate(reporteAPEForm
				.getFechaFacturacionDt());
		this.formatoReporte = reporteAPEForm.getFormatoExportacion();

		params.put("NroReporte", " ");
		params.put("titulo",
				getResourceMessage("reporteAPEListaAFPConsultoraForm.title"));

		String codigoPais = pais.getCodigo();

		String fechaSaldo = valorFecha;
		String marca = reporteAPEForm.getCodigoMarca();
		String canal = reporteAPEForm.getCodigoCanal();
		String linea = reporteAPEForm.getCodigoLinea();
		String campana = "%" + reporteAPEForm.getCodigoPeriodo();
		String fuente = reporteAPEForm.getCodigoLote();
		String sublinea = reporteAPEForm.getCodsublinea();
		String centro = reporteAPEForm.getCodigoCentro();

		params.put("codigoPais", codigoPais);
		params.put("fechaFacturacion", fechaSaldo);
		params.put("marca", marca);
		params.put("canal", canal);
		params.put("codigoLinea", linea);
		params.put("campana", campana);
		params.put("codigoLote", fuente);
		params.put("codsublinea", sublinea);
		params.put("centro", centro);
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

	/**
	 * @return the tipoReporteList
	 */
	public List getTipoReporteList() {
		return tipoReporteList;
	}

	/**
	 * @param tipoReporteList the tipoReporteList to set
	 */
	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCentrodList
	 */
	public List getSiccCentrodList() {
		return siccCentrodList;
	}

	/**
	 * @param siccCentrodList the siccCentrodList to set
	 */
	public void setSiccCentrodList(List siccCentrodList) {
		this.siccCentrodList = siccCentrodList;
	}

	/**
	 * @return the siccLineaList
	 */
	public List getSiccLineaList() {
		return siccLineaList;
	}

	/**
	 * @param siccLineaList the siccLineaList to set
	 */
	public void setSiccLineaList(List siccLineaList) {
		this.siccLineaList = siccLineaList;
	}

	/**
	 * @return the siccTipoSolList
	 */
	public List getSiccTipoSolList() {
		return siccTipoSolList;
	}

	/**
	 * @param siccTipoSolList the siccTipoSolList to set
	 */
	public void setSiccTipoSolList(List siccTipoSolList) {
		this.siccTipoSolList = siccTipoSolList;
	}

}
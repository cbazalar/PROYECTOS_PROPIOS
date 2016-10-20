package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECOperacionesUnidadAdmForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECOperacionesUnidadAdmAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -377825064070150862L;

	private String formatoReporte;

	private String tipoMovimiento;
	private List siccMarcaList = new ArrayList();
	private List siccUnidadNegocioList = new ArrayList();
	private List siccEstadoOperacionList = new ArrayList();
	private List siccEstadoReclamoList = new ArrayList();
	private List siccNegocioList = new ArrayList();
	private List siccOperacionesList = new ArrayList();
	private String codigoIdiomaISO;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccTerritorioList = {};
	private LabelValue[] siccTipoOperacionList = {};
	private String periodoActual;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccMarcaList = reporteService.getListaGenerico("getMarcaProdu",
				criteriaOperacion);
		this.siccUnidadNegocioList = reporteService.getListaGenerico(
				"getListaUnidadNegocio", criteriaOperacion);
		this.siccEstadoOperacionList = reporteService.getListaGenerico(
				"getListaEstadoOperacion", criteriaOperacion);

		this.siccEstadoReclamoList = reporteService.getListaGenerico(
				"getListaEstadoReclamo", criteriaOperacion);

		this.siccNegocioList = reporteService.getListaGenerico(
				"getListaNegocio", criteriaOperacion);

		this.siccOperacionesList = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);

		this.codigoIdiomaISO = this.mPantallaPrincipalBean.getOidIdiomaIso();

		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}

		ReporteRECOperacionesUnidadAdmForm reporteRECForm = (ReporteRECOperacionesUnidadAdmForm) this.formReporte;

		reporteRECForm.setCodigoPais(pais.getCodigo());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		this.periodoActual = this.mPantallaPrincipalBean.getAnyoActualperiodo();
		reporteRECForm.setCodigoPeriodoInicial(this.periodoActual);
		if (StringUtils.isEmpty(reporteRECForm.getCodigoPeriodoInicial()))
			;
		reporteRECForm.setCodigoPeriodoInicial(periodo);
		reporteRECForm.setCodigoPeriodoFinal(this.periodoActual);
		if (StringUtils.isEmpty(reporteRECForm.getCodigoPeriodoFinal()))
			;
		reporteRECForm.setCodigoPeriodoFinal(periodo);

	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegions ");
		try {
			ReporteRECOperacionesUnidadAdmForm form = (ReporteRECOperacionesUnidadAdmForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc
						.getZonasMultipleByPaisMarcaCanalRegion(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, regiones,
								Constants.FORMATO_TOTAL));
				form.setZonaList(null);
			} else {
				this.siccZonaList = null;
				form.setZonaList(null);
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECOperacionesUnidadAdmForm form = (ReporteRECOperacionesUnidadAdmForm) this.formReporte;
		Integer fecha1, fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicial());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (fecha1 > fecha2) {
			String mensaje = "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		}
		return null;
	}

	/**
	 * Muestra los tipos de operacion
	 */
	public void showTiposxOperacion(ValueChangeEvent val) {
		log.debug(">>showTiposxOperacion ");
		try {
			ReporteRECOperacionesUnidadAdmForm form = (ReporteRECOperacionesUnidadAdmForm) this.formReporte;
			String[] valores = (String[]) val.getNewValue();
			// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ArrayList<String> listaValores = new ArrayList<String>(
					Arrays.asList(valores));
			if (!val.equals(null) && valores.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccTipoOperacionList(aSvc
						.getTiposOperaMultipleByOpera(form.getCodigoPais(),
								listaValores, ""));
				form.setTipoOperacionList(null);
			} else {
				this.siccTipoOperacionList = null;
				form.setTipoOperacionList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Muestra los territorios de acuerdo a la zonas escogidas.
	 */
	public void showTerritorioxZona(ValueChangeEvent val) {
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteRECOperacionesUnidadAdmForm form = (ReporteRECOperacionesUnidadAdmForm) this.formReporte;

			String[] regiones = (String[]) form.getRegionList();

			String[] zonas = (String[]) val.getNewValue();
			if (!val.equals(null) && zonas.length > 0) {

				List<String> listaRegiones = new ArrayList<String>(
						Arrays.asList(regiones));

				List<String> listaZonas = new ArrayList<String>(
						Arrays.asList(zonas));

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccTerritorioList(aSvc
						.getTerritoriosMultipleByPaisMarcaCanalRegionZona(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, listaRegiones,
								listaZonas, Constants.FORMATO_TOTAL));

				form.setTerritorioList(null);
			} else {
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECOperacionesUnidadAdmForm form = new ReporteRECOperacionesUnidadAdmForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteRECOperacionesUnidAdmXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteRECOperacionesUnidAdmPDF";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECOperacionesUnidadAdmForm reporteRECForm = (ReporteRECOperacionesUnidadAdmForm) this.formReporte;
		this.formatoReporte = reporteRECForm.getFormatoExportacion();
		this.tipoMovimiento = reporteRECForm.getTipoMovimiento();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = params;
		if (StringUtils.equals(reporteRECForm.getTipoPeriodo(), "0")) {
			params.put("periodoReferenciaInicial",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("periodoReferenciaFinal",
					reporteRECForm.getCodigoPeriodoFinal());

			criteria.put("codigoPeriodo",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("oidPeriodoReferenciaInicial", reporteService
					.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
			criteria.put("codigoPeriodo",
					reporteRECForm.getCodigoPeriodoFinal());
			params.put("oidPeriodoReferenciaFinal", reporteService
					.getOidString("getOidPeriodoByCodigoPeriodo", criteria));

			params.put("periodoRegistroInicial", null);
			params.put("periodoRegistroFinal", null);
		} else {
			params.put("periodoRegistroInicial",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("periodoRegistroFinal",
					reporteRECForm.getCodigoPeriodoFinal());

			criteria.put("codigoPeriodo",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("oidPeriodoRegistroInicial", reporteService
					.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
			criteria.put("codigoPeriodo",
					reporteRECForm.getCodigoPeriodoFinal());
			params.put("oidPeriodoRegistroFinal", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));

			params.put("periodoReferenciaInicial", null);
			params.put("periodoReferenciaFinal", null);
		}
		params.put("codigoOperacion",
				(reporteRECForm.getCodigoOperacion() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getCodigoOperacion()));
		params.put("negocioList",
				(reporteRECForm.getNegocioList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getNegocioList()));
		params.put(
				"unidadNegocioList",
				(reporteRECForm.getUnidadNegocioList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getUnidadNegocioList()));
		params.put("territorioList",
				(reporteRECForm.getTerritorioList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getTerritorioList()));
		params.put(
				"tipoOperacionList",
				(reporteRECForm.getTipoOperacionList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getTipoOperacionList()));
		params.put("marcaList",
				(reporteRECForm.getMarcaList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getMarcaList()));
		params.put("zonaList",
				(reporteRECForm.getZonaList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getZonaList()));
		params.put("regionList",
				(reporteRECForm.getRegionList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getRegionList()));
		params.put(
				"NroReporte",
				getReportResourceMessage("reporteRECOperacionesUnidadAdmForm.numero.reporte"));
		params.put(
				"titulo",
				getReportResourceMessage("reporteRECOperacionesUnidadAdmForm.titulo"));
		params.put("tipoMovimiento", this.tipoMovimiento);

		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteRECOperacionesUnidadAdmService";
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
	 * @return the tipoMovimiento
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param tipoMovimiento
	 *            the tipoMovimiento to set
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccUnidadNegocioList
	 */
	public List getSiccUnidadNegocioList() {
		return siccUnidadNegocioList;
	}

	/**
	 * @param siccUnidadNegocioList
	 *            the siccUnidadNegocioList to set
	 */
	public void setSiccUnidadNegocioList(List siccUnidadNegocioList) {
		this.siccUnidadNegocioList = siccUnidadNegocioList;
	}

	/**
	 * @return the siccEstadoOperacionList
	 */
	public List getSiccEstadoOperacionList() {
		return siccEstadoOperacionList;
	}

	/**
	 * @param siccEstadoOperacionList
	 *            the siccEstadoOperacionList to set
	 */
	public void setSiccEstadoOperacionList(List siccEstadoOperacionList) {
		this.siccEstadoOperacionList = siccEstadoOperacionList;
	}

	/**
	 * @return the siccEstadoReclamoList
	 */
	public List getSiccEstadoReclamoList() {
		return siccEstadoReclamoList;
	}

	/**
	 * @param siccEstadoReclamoList
	 *            the siccEstadoReclamoList to set
	 */
	public void setSiccEstadoReclamoList(List siccEstadoReclamoList) {
		this.siccEstadoReclamoList = siccEstadoReclamoList;
	}

	/**
	 * @return the siccNegocioList
	 */
	public List getSiccNegocioList() {
		return siccNegocioList;
	}

	/**
	 * @param siccNegocioList
	 *            the siccNegocioList to set
	 */
	public void setSiccNegocioList(List siccNegocioList) {
		this.siccNegocioList = siccNegocioList;
	}

	/**
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 *            the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 *            the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList
	 *            the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the siccTipoOperacionList
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList
	 *            the siccTipoOperacionList to set
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	/**
	 * @return the periodoActual
	 */
	public String getPeriodoActual() {
		return periodoActual;
	}

	/**
	 * @param periodoActual
	 *            the periodoActual to set
	 */
	public void setPeriodoActual(String periodoActual) {
		this.periodoActual = periodoActual;
	}
}
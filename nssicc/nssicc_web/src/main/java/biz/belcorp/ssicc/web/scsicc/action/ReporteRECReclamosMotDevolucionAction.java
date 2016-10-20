package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteRECReclamosMotDevolucionForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECReclamosMotDevolucionAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -7400714135529482585L;
	private String formatoReporte;
	private String tipoMovimiento;
	private List siccMarcaList;
	private List siccUnidadNegocioList;
	private List siccEstadoOperacionList;
	private List siccEstadoReclamosList;
	private List siccNegocioList;
	private List siccRegionList;
	private List siccOperacionesList;
	private List siccMotivoDevolucionList;
	private LabelValue[] siccTipoOperacionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccTerritorioList = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECReclamosMotDevolucionForm reporteForm = new ReporteRECReclamosMotDevolucionForm();

		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteRECReclamosMotDevolucionAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteRECReclamosMotDevolucionForm f = (ReporteRECReclamosMotDevolucionForm) this.formReporte;
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccMarcaList = reporteService.getListaGenerico("getMarcaProdu",
				criteriaOperacion);
		this.siccUnidadNegocioList = reporteService.getListaGenerico(
				"getListaUnidadNegocio", criteriaOperacion);
		this.siccEstadoOperacionList = reporteService.getListaGenerico(
				"getListaEstadoOperacion", criteriaOperacion);
		this.siccEstadoReclamosList = reporteService.getListaGenerico(
				"getListaEstadoReclamo", criteriaOperacion);
		this.siccNegocioList = reporteService.getListaGenerico(
				"getListaNegocio", criteriaOperacion);
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.siccOperacionesList = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);
		this.siccMotivoDevolucionList = reporteService.getListaGenerico(
				"getMotivoDevolucionByPais", criteriaOperacion);
		f.setOidIdiomaIso(usuario.getIdioma().getCodigoISO());

		log.debug("Todo OK: Redireccionando");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteRECReclamosMotDevolucionService";
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
		ReporteRECReclamosMotDevolucionForm form = (ReporteRECReclamosMotDevolucionForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteRECReclamosMotDevolucionXLS";
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
		ReporteRECReclamosMotDevolucionForm form = (ReporteRECReclamosMotDevolucionForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteRECReclamosMotDevolucionPDF";
		else
			return "";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECReclamosMotDevolucionForm form = (ReporteRECReclamosMotDevolucionForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicial());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (codperfin < codperini) {
			String mensaje = "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		}

		return null;

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
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteRECReclamosMotDevolucionForm reporteRECForm = (ReporteRECReclamosMotDevolucionForm) this.formReporte;
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
				(reporteRECForm.getNegocioList() == null) ? new ArrayList()
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
				"motivoDevolucionList",
				(reporteRECForm.getMotivoDevolucionList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm
								.getMotivoDevolucionList()));
		params.put(
				"NroReporte",
				getResourceMessage("reporteRECReclamosMotDevolucionForm.numero.reporte"));
		params.put(
				"titulo",
				getResourceMessage("reporteRECReclamosMotDevolucionForm.titulo"));

		// ##############################################################
		String[] valores = reporteRECForm.getTerritorioList();
		String descripcionTerritorioList = descripcionMultipleLista(valores,
				this.siccTerritorioList);
		params.put("descripcionTerritorioList", descripcionTerritorioList);

		// ##############################################################
		String descripcionTipoOperacionList = descripcionMultipleLista(
				reporteRECForm.getTipoOperacionList(),
				this.siccTipoOperacionList);
		params.put("descripcionTipoOperacionList", descripcionTipoOperacionList);

		// ##############################################################
		String descripcionMotivoDevolucionList = descripcionMultipleLista(
				reporteRECForm.getMotivoDevolucionList(),
				this.siccMotivoDevolucionList);
		params.put("descripcionMotivoDevolucionList",
				descripcionMotivoDevolucionList);

		// #######################################################################
		String descripcionRegionList = descripcionMultipleLista(
				reporteRECForm.getRegionList(), this.siccRegionList);
		params.put("descripcionRegionList", descripcionRegionList);

		// ##############################################################
		String descripcionZonaList = descripcionMultipleLista(
				reporteRECForm.getZonaList(), this.siccZonaList);
		params.put("descripcionZonaList", descripcionZonaList);

		// ##############################################################
		String descripcionUnidadNegocioList = descripcionMultipleLista(
				reporteRECForm.getUnidadNegocioList(),
				this.siccUnidadNegocioList);
		params.put("descripcionUnidadNegocioList", descripcionUnidadNegocioList);

		// ##############################################################
		String descripcionNegocioList = descripcionMultipleLista(
				reporteRECForm.getNegocioList(), this.siccNegocioList);
		params.put("descripcionNegocioList", descripcionNegocioList);

		// ##############################################################
		String descripcionMarcaList = descripcionMultipleLista(
				reporteRECForm.getMarcaList(), this.siccMarcaList);
		params.put("descripcionMarcaList", descripcionMarcaList);

		log.info("Salio a ReporteRECReclamosMotDevolucionAction - prepareParameterMap");
		return params;
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");
		try {
			String[] valores = (String[]) val.getNewValue();
			if (valores.length > 0) {
				String[] regiones = (String[]) val.getNewValue();

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.siccZonaList = aSvc
						.getZonasMultipleByPaisMarcaCanalRegion(
								this.mPantallaPrincipalBean.getCurrentCountry()
										.getCodigo(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, regiones,
								Constants.FORMATO_TOTAL);

			} else {
				setSiccZonaList(null);
				setSiccTerritorioList(null);

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo para obtener Lista de Territorios
	 * 
	 * @param val
	 */
	public void loadterritorio(ValueChangeEvent val) {
		log.debug("loadterritorio");
		try {
			ReporteRECReclamosMotDevolucionForm form = (ReporteRECReclamosMotDevolucionForm) this.formReporte;
			String[] regiones = (String[]) form.getRegionList();
			String[] zonas = (String[]) val.getNewValue();
			List<String> listaRegiones = new ArrayList<String>(
					Arrays.asList(regiones));
			List<String> listaZonas = new ArrayList<String>(
					Arrays.asList(zonas));
			if (regiones.length > 0 && zonas.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				setSiccTerritorioList(aSvc
						.getTerritoriosMultipleByPaisMarcaCanalRegionZona(
								this.mPantallaPrincipalBean.getCurrentCountry()
										.getCodigo(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, listaRegiones,
								listaZonas, Constants.FORMATO_TOTAL));

			} else {
				setSiccTerritorioList(null);

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param val
	 */
	public void loadTipoOperacion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoOperacion");
		}
		try {
			String[] valores = (String[]) val.getNewValue();

			if (valores.length > 0) {
				ArrayList<String> listaValores = new ArrayList<String>(
						Arrays.asList(valores));
				AjaxService ajax = (AjaxService) getBean("ajaxService");

				this.siccTipoOperacionList = ajax.getTiposOperaMultipleByOpera(
						this.mPantallaPrincipalBean.getCurrentCountry()
								.getCodigo(), listaValores,
						Constants.FORMATEAR_TODOS);

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

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
	 * @return the siccEstadoReclamosList
	 */
	public List getSiccEstadoReclamosList() {
		return siccEstadoReclamosList;
	}

	/**
	 * @param siccEstadoReclamosList
	 *            the siccEstadoReclamosList to set
	 */
	public void setSiccEstadoReclamosList(List siccEstadoReclamosList) {
		this.siccEstadoReclamosList = siccEstadoReclamosList;
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
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
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
	 * @return the siccMotivoDevolucionList
	 */
	public List getSiccMotivoDevolucionList() {
		return siccMotivoDevolucionList;
	}

	/**
	 * @param siccMotivoDevolucionList
	 *            the siccMotivoDevolucionList to set
	 */
	public void setSiccMotivoDevolucionList(List siccMotivoDevolucionList) {
		this.siccMotivoDevolucionList = siccMotivoDevolucionList;
	}

}

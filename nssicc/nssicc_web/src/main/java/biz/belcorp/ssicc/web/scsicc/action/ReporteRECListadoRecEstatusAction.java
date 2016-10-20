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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteRECListadoRecEstatusForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteRECListadoRecEstatusAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1435748424609950127L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccUnidadNegocioList;
	private List siccEstadoOperacionList;
	private List siccEstadoReclamosList;
	private List siccNegocioList;
	private List siccRegionList;
	private List siccOperacionesList;
	private LabelValue[] siccTipoOperacionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccTerritorioList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECListadoRecEstatusForm reporteForm = new ReporteRECListadoRecEstatusForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteRECListadoRecEstatusAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteRECListadoRecEstatusForm f = (ReporteRECListadoRecEstatusForm) this.formReporte;
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

		f.setOidIdiomaIso(usuario.getIdioma().getCodigoISO());

		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteRECListadoRecEstatusService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECListadoRecEstatusForm form = (ReporteRECListadoRecEstatusForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteRECListadoRecEstatusXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECListadoRecEstatusForm form = (ReporteRECListadoRecEstatusForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteRECListadoRecEstatusPDF";
		else
			return "";

	}

	public String setValidarReporte() {
		ReporteRECListadoRecEstatusForm form = (ReporteRECListadoRecEstatusForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicial());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (codperfin < codperini) {
			String mensaje = "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		}

		return null;

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteRECListadoRecEstatusForm reporteRECForm = (ReporteRECListadoRecEstatusForm) this.formReporte;
		formatoReporte = reporteRECForm.getFormatoExportacion();

		params.put("codigoOperacion", reporteRECForm.getCodigoOperacion());
		if (StringUtils.equals(reporteRECForm.getTipoPeriodo(), "0")) {
			params.put("periodoReferenciaInicial",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("periodoReferenciaFinal",
					reporteRECForm.getCodigoPeriodoFinal());
			params.put("periodoRegistroInicial", null);
			params.put("periodoRegistroFinal", null);
		} else {
			params.put("periodoRegistroInicial",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("periodoRegistroFinal",
					reporteRECForm.getCodigoPeriodoFinal());
			params.put("periodoReferenciaInicial", null);
			params.put("periodoReferenciaFinal", null);
		}
		params.put(
				"estadoOperacionList",
				(reporteRECForm.getEstadoOperacionList() == null || StringUtils
						.equals(StringUtils.substring(reporteRECForm
								.getDescripcionEstadoOperacionList(), 0, 5),
								"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getEstadoOperacionList()));
		params.put(
				"estadoReclamoList",
				(reporteRECForm.getEstadoReclamoList() == null || StringUtils
						.equals(StringUtils.substring(reporteRECForm
								.getDescripcionEstadoReclamoList(), 0, 5),
								"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getEstadoReclamoList()));

		params.put(
				"negocioList",
				(reporteRECForm.getNegocioList() == null || StringUtils.equals(
						StringUtils.substring(
								reporteRECForm.getDescripcionNegocioList(), 0,
								5), "Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getNegocioList()));
		params.put(
				"unidadNegocioList",
				(reporteRECForm.getUnidadNegocioList() == null || StringUtils
						.equals(StringUtils.substring(reporteRECForm
								.getDescripcionUnidadNegocioList(), 0, 5),
								"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getUnidadNegocioList()));
		params.put(
				"territorioList",
				(reporteRECForm.getTerritorioList() == null || StringUtils
						.equals(StringUtils.substring(
								reporteRECForm.getDescripcionTerritorioList(),
								0, 5), "Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getTerritorioList()));
		params.put(
				"tipoOperacionList",
				(reporteRECForm.getTipoOperacionList() == null || StringUtils
						.equals(StringUtils.substring(reporteRECForm
								.getDescripcionTipoOperacionList(), 0, 5),
								"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getTipoOperacionList()));
		params.put(
				"marcaList",
				(reporteRECForm.getMarcaList() == null || StringUtils.equals(
						StringUtils.substring(
								reporteRECForm.getDescripcionMarcaList(), 0, 5),
						"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getMarcaList()));
		params.put(
				"zonaList",
				(reporteRECForm.getZonaList() == null || StringUtils.equals(
						StringUtils.substring(
								reporteRECForm.getDescripcionZonaList(), 0, 5),
						"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getZonaList()));
		params.put(
				"regionList",
				(reporteRECForm.getRegionList() == null || StringUtils.equals(
						StringUtils
								.substring(reporteRECForm
										.getDescripcionRegionList(), 0, 5),
						"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getRegionList()));

		params.put(
				"NroReporte",
				getResourceMessage("reporteRECListadoRecEstatusForm.numero.reporte"));
		params.put("titulo",
				getResourceMessage("reporteRECListadoRecEstatusForm.titulo"));

		log.info("Salio a ReporteRECListadoRecEstatusAction - prepareParameterMap");
		return params;
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");

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

	}

	/**
	 * Metodo para obtener Lista de Territorios
	 * 
	 * @param val
	 */
	public void loadterritorio(ValueChangeEvent val) {
		log.debug("loadterritorio");
		ReporteRECListadoRecEstatusForm form = (ReporteRECListadoRecEstatusForm) this.formReporte;
		String[] regiones = (String[]) form.getRegionList();
		String[] zonas = (String[]) val.getNewValue();
		List<String> listaRegiones = new ArrayList<String>(
				Arrays.asList(regiones));
		List<String> listaZonas = new ArrayList<String>(Arrays.asList(zonas));
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

	}

	public void loadTipoOperacion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoOperacion");
		}
		String[] valor0 = (String[]) val.getNewValue();
		String valor = valor0[0];

		AjaxService ajax = (AjaxService) getBean("ajaxService");

		setSiccTipoOperacionList(ajax.getTiposOperaByOpera(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				valor, ""));

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

}

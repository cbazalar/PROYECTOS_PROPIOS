package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENEvaluacionVentaVariableSeccionForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteVENEvaluacionVentaVariableSeccionAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2952145857277867284L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENEvaluacionVentaVariableSeccionForm reporteForm = new ReporteVENEvaluacionVentaVariableSeccionForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("ReporteVENEvaluacionVentaVariableSeccionAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		ReporteVENEvaluacionVentaVariableSeccionForm reporteVENForm = (ReporteVENEvaluacionVentaVariableSeccionForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		reporteVENForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		loadRegiones();
		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteVENEvaluacionVentaVariableSeccionService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteVENEvaluacionVentaVariableSeccionForm form = (ReporteVENEvaluacionVentaVariableSeccionForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteVENEvaluacionVentaVariableSeccionXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteVENEvaluacionVentaVariableSeccionForm form = (ReporteVENEvaluacionVentaVariableSeccionForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteVENEvaluacionVentaVariableSeccionPDF";
		else
			return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}

		ReporteVENEvaluacionVentaVariableSeccionForm reporteVENForm = (ReporteVENEvaluacionVentaVariableSeccionForm) this.formReporte;
		formatoReporte = reporteVENForm.getFormatoExportacion();

		params.put("NroReporte", "");
		params.put("superiorIzquierda", "");
		params.put("condicionUsuario", "NO");
		params.put(
				"titulo",
				getResourceMessage("reporteVENEvaluacionVentaVariableSeccionForm.titulo"));
		log.info("Salio a ReporteVENEvaluacionVentaVariableSeccionAction - prepareParameterMap");
		return params;
	}

	public void loadRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegion");
		}
		ReporteVENEvaluacionVentaVariableSeccionForm form = (ReporteVENEvaluacionVentaVariableSeccionForm) this.formReporte;

		String marca = (String) val.getNewValue();
		String canal = form.getCodigoCanal();
		if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
				&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {
			loadRegiones();
		} else {
			setSiccRegionList(null);
			setSiccZonaList(null);

		}
	}

	public void loadRegion2(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegion2");
		}
		ReporteVENEvaluacionVentaVariableSeccionForm form = (ReporteVENEvaluacionVentaVariableSeccionForm) this.formReporte;

		String canal = (String) val.getNewValue();
		String marca = form.getCodigoMarca();
		if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
				&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {
			loadRegiones();

		} else {
			setSiccRegionList(null);
			setSiccZonaList(null);
		}
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		if (!val.equals(null)) {
			String valor = (String) val.getNewValue();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(ajaxService.getZonasByPaisMarcaCanalRegion(
					this.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, valor));

		} else
			this.setSiccZonaList(null);
	}

	public void loadRegiones() {
		Map criteriaOperacion = new HashMap();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int i = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}

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
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
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

}

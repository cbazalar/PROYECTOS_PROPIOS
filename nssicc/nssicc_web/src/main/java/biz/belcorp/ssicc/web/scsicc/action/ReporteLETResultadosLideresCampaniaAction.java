package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
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
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETResultadosLideresCampaniaForm;

@ManagedBean
@SessionScoped
public class ReporteLETResultadosLideresCampaniaAction extends
		BaseReporteAbstractAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3609685547076105991L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;

		log.debug("ReporteLETResultadosLideresCampaniaAction - setViewAttributes");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		ReporteLETResultadosLideresCampaniaForm f = (ReporteLETResultadosLideresCampaniaForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		// ################### LISTA MARCA ################
		this.siccMarcaList = interfazSiCCService.getMarcas();

		// ################### LISTA CANAL ################
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		// ################### LISTA REGIONES ################
		this.siccRegionList = ajaxService
				.getRegionesByPaisLet(pais.getCodigo());

		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPais(pais.getCodigo());

		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETResultadosLideresCampaniaForm form = new ReporteLETResultadosLideresCampaniaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteLETResultadosLideresCampaniaForm form = (ReporteLETResultadosLideresCampaniaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion())) {
			return "reporteLETResultadosLideresCampaniaXLS";
		} else
			return "reporteMaestroHorizontalLETResultadosLideresCampania";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formReporte.getFormatoExportacion())) {
			return "reporteLETResultadosLideresCampaniaPDF";
		} else
			return "";

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		log.debug("ReporteLETResultadosLideresCampaniaAction ::::: prepareParameterMap");

		ReporteLETResultadosLideresCampaniaForm f = (ReporteLETResultadosLideresCampaniaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) this
				.getBean("scsicc.reporteService");

		formatoReporte = f.getFormatoExportacion();

		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPeriodo", f.getCodigoPeriodo());

		String codigoZona = (String) params.get("codigoZona");
		String codigoRegion = (String) params.get("codigoRegion");

		String condicionRegion = "";

		if (StringUtils.isNotBlank(codigoRegion))
			condicionRegion = "AND SC.COD_REGI = '" + codigoRegion + "' ";
		else
			params.put("codigoRegion", null);

		String condicionZonas = "";

		if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		} else {
			condicionZonas = obtieneCondicion(f.getCodigoZona(), "SC.COD_ZONA",
					"'");
		}

		String condicion = condicionRegion + condicionZonas;

		params.put("condicion", condicion);
		params.put(
				"titulo",
				getResourceMessage(
						"reporteLETResultadosLideresCampaniaForm.titulo"));
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("codigoCanal", f.getCodigoCanal());
		params.put("oidPais", Integer.valueOf(reporteService.getOidString(
				"getOidPaisByCodigoPais", params)));
		params.put("oidMarca", Integer.valueOf(reporteService.getOidString(
				"getOidMarcaByCodigoMarca", params)));
		params.put("oidCanal", Integer.valueOf(reporteService.getOidString(
				"getOidCanalByCodigoCanal", params)));
		return params;
	}
	
	
	
	/**
	 * Metodo para obtener Lista de Zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		
		if (!val.equals(null)) {
			String valor = (String) val.getNewValue();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			setSiccZonaList(ajaxService
					.getZonasByPaisRegionLet(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), valor));
		}else
			setSiccZonaList(null);
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
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	
	

}

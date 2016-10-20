package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
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
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENConsejerasIngresadasCampanhaForm;

@ManagedBean
@SessionScoped
public class ReporteVENConsejerasIngresadasCampanhaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3991466101682161090L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccTipoClienteList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSubTipoClienteList = {};
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccTerritorioList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENConsejerasIngresadasCampanhaForm reporteForm = new ReporteVENConsejerasIngresadasCampanhaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteAPPPedidosServicioAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);

		this.siccTipoClienteList = service.getTiposClientesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		ArrayList temp = new ArrayList();
		temp.add(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccSubTipoClienteList = aSvc
				.getSubTiposClientesPorPaisTipoClientes(usuario.getIdioma()
						.getCodigoISO(), temp);

		ReporteVENConsejerasIngresadasCampanhaForm f = (ReporteVENConsejerasIngresadasCampanhaForm) this.formReporte;
		f.setCodigoPeriodo(codigoPeriodo);
		f.setCodigoTipoCliente(new String[] { Constants.CODIGO_TIPO_CLIENTE_DEFAULT });
		f.setCodigoSubTipoCliente(new String[] { Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT_M });

		loadRegiones();
//		Base base = new Base();
//		base = (Base) listaRegiones.get(0);
//		codregion = base.getCodigo();

		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteVENConsejerasIngresadasCampanhaForm form = (ReporteVENConsejerasIngresadasCampanhaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteVENConsejerasIngresadasCampanhaXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteVENConsejerasIngresadasCampanhaForm form = (ReporteVENConsejerasIngresadasCampanhaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteVENConsejerasIngresadasCampanhaPDF";
		else
			return "";

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteVENConsejerasIngresadasCampanhaForm reportePERForm = (ReporteVENConsejerasIngresadasCampanhaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.formatoReporte = reportePERForm.getFormatoExportacion();
		String codigoRegion = reportePERForm.getCodigoRegion();

		log.debug("Los parametros del Reporte en el Action "
				+ params.toString());
		params.put("NroReporte", " ");
		params.put("condicionUsuario", "NO");
		Map criteria = params;
		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);
		String condicionCliente = "";
		if (reportePERForm.getCodigoSubTipoCliente() != null
				&& reportePERForm.getCodigoSubTipoCliente().length > 0) {
			condicionCliente = obtieneCondicion(
					reportePERForm.getCodigoSubTipoCliente(),
					"TC.COD_TIPO_CLIE || '-' || SC.COD_SUBT_CLIE", "'");
		} else {
			if (reportePERForm.getCodigoTipoCliente() != null
					&& reportePERForm.getCodigoTipoCliente().length > 0) {
				condicionCliente = obtieneCondicion(
						reportePERForm.getCodigoTipoCliente(),
						"TC.COD_TIPO_CLIE", "'");
			} else
				condicionCliente = "";
		}
		params.put("superiorIzquierda", "");
		params.put("condicionCliente", condicionCliente);
		params.put("oidPeriodo", oidPeriodo);
		params.put(
				"titulo",
				getResourceMessage("reporteVENConsejerasIngresadasCampanhaForm.titulo"));

		return params;
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
		ReporteVENConsejerasIngresadasCampanhaForm form = (ReporteVENConsejerasIngresadasCampanhaForm) this.formReporte;

		String valor = (String) val.getNewValue();
		if (!valor.equals("T")) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			setSiccZonaList(ajaxService.getZonasByPaisMarcaCanalRegion(
					this.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, valor));

		}else{
			setSiccZonaList(null);
			setSiccSeccionList(null);
			setSiccTerritorioList(null);
			form.setCodigoZona("T");
			form.setCodigoSeccion("T");
			form.setCodigoTerritorio("T");
			
		}
	}

	/**
	 * Metodo para obtener Seccion por Zona
	 * 
	 * @param val
	 */
	public void showSeccionxZona(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showSeccionxZona");
		}
		ReporteVENConsejerasIngresadasCampanhaForm form = (ReporteVENConsejerasIngresadasCampanhaForm) this.formReporte;

		String valor = (String) val.getNewValue();
		if (!valor.equals("T")) {


			String zona = (String) val.getNewValue();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccSeccionList(aSvc
					.getSeccionesByPaisMarcaCanalRegionZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, form.getCodigoRegion(), zona));

		} else {
			setSiccSeccionList(null);
			setSiccTerritorioList(null);
			form.setCodigoSeccion("T");
			form.setCodigoTerritorio("T");
		}

	}

	/**
	 * Metodo para obtener Territorio por Seccion
	 * 
	 * @param val
	 */
	public void showTerritorioxSeccion(ValueChangeEvent val) {
		log.debug(">>showTerritorioxZona ");
		log.debug("val: " + val.getNewValue().toString());
		ReporteVENConsejerasIngresadasCampanhaForm form = (ReporteVENConsejerasIngresadasCampanhaForm) this.formReporte;

		String valor = (String) val.getNewValue();
		if (!valor.equals("T")) {

			String region = form.getCodigoRegion();
			String zona = form.getCodigoZona();
			String seccion = (String) val.getNewValue();

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccTerritorioList(aSvc
					.getTerritoriosByPaisMarcaCanalRegionZonaSeccion(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, region, zona, seccion));
			
		}else{
			
			setSiccTerritorioList(null);
			form.setCodigoTerritorio("T");
		}
	

	}

	/**
	 * Metodo para obtener Lista de Sub Clientes
	 * 
	 * @param val
	 */
	public void loadSubClientes(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadSubClientes");
		}
		String[] valor = (String[]) val.getNewValue();
		ArrayList<String> valores = new ArrayList<String>(Arrays.asList(valor));
		if (valor.length > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccSubTipoClienteList(ajaxService
					.getSubTiposClientesPorPaisTipoClientes(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), valores));

		}
	}
	
	public void loadRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegion");
		}
		ReporteVENConsejerasIngresadasCampanhaForm form = (ReporteVENConsejerasIngresadasCampanhaForm) this.formReporte;

		String marca = (String) val.getNewValue();
		String canal = form.getCodigoCanal();
		if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
				&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {
			loadRegiones();
		} else {
			setSiccRegionList(null);
			setSiccZonaList(null);
			setSiccSeccionList(null);
			setSiccTerritorioList(null);
			form.setCodigoRegion("T");
			form.setCodigoZona("T");
			form.setCodigoSeccion("T");
			form.setCodigoTerritorio("T");

		}
	}

	public void loadRegion2(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegion2");
		}
		ReporteVENConsejerasIngresadasCampanhaForm form = (ReporteVENConsejerasIngresadasCampanhaForm) this.formReporte;

		String canal = (String) val.getNewValue();
		String marca = form.getCodigoMarca();
		if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
				&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {
			loadRegiones();

		} else {
			setSiccRegionList(null);
			setSiccZonaList(null);
			setSiccSeccionList(null);
			setSiccTerritorioList(null);
			form.setCodigoRegion("T");
			form.setCodigoZona("T");
			form.setCodigoSeccion("T");
			form.setCodigoTerritorio("T");
		}
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
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList
	 *            the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	/**
	 * @return the siccSubTipoClienteList
	 */
	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList
	 *            the siccSubTipoClienteList to set
	 */
	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
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
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
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

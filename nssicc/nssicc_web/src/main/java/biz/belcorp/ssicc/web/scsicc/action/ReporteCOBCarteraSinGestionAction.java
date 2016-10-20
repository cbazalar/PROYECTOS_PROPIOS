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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBCarteraSinGestionForm;

@ManagedBean
@SessionScoped
public class ReporteCOBCarteraSinGestionAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2729496906829479397L;
	private String formatoReporte;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List siccSociedadList;
	private LabelValue[] siccEtapaDeudaList = {};
	private LabelValue[] siccCobradoresList = {};
	private String tipoVista;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBCarteraSinGestionForm reporteForm = new ReporteCOBCarteraSinGestionForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteCOBCarteraSinGestionAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		// ----------- LISTA DE SOCIEDADES --------------
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());

		Object listaSociedad = new Object();
		listaSociedad = getSiccSociedadList().get(0);
		Base base = (Base) listaSociedad;
		String codsociedad0 = base.getCodigo();

		// ----------- LISTA DE ETAPAS --------------
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccEtapaDeudaList = ajax.getEtapasDeudaByPaisSociedad(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				codsociedad0);
		LabelValue listaEtapa0 = new LabelValue();

		if (getSiccEtapaDeudaList() != null) {
			listaEtapa0 = getSiccEtapaDeudaList()[0];
			String codetapa0 = listaEtapa0.getValue();

			// ----------- LISTA DE COBRADORES ------------------
			this.siccCobradoresList = ajax
					.getCobradoresByPaisSociedadEtapaDeuda(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(), codsociedad0, codetapa0);

		}

		// ------------ LISTA DE REGIONES -----------------
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

		log.debug("Todo OK: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCOBCarteraSinGestionForm form = (ReporteCOBCarteraSinGestionForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteCOBCarteraSinGestion" + tipoVista + "XLS";
		else
			return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	private String getEstadoCartera(String criterio) {

		log.debug("___Estado Cartera = " + criterio);

		// Canceladas
		if (criterio.equals("CA"))
			return " AND D.IMP_DEUD_PEND=0 ";

		// Parcialmente Canceladas
		if (criterio.equals("PA"))
			return " AND D.IMP_DEUD_CANC>0 ";

		// Pendientes
		if (criterio.equals("PE"))
			return " AND D.IMP_DEUD_CANC=0 ";

		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}

		ReporteCOBCarteraSinGestionForm reporteCOBForm = (ReporteCOBCarteraSinGestionForm) this.formReporte;

		this.tipoVista = reporteCOBForm.getTipoVista();

		String codigoPais = reporteCOBForm.getCodigoPais();
		String codigoSociedad = reporteCOBForm.getCodigoSociedad();
		params.put("estadoCartera",
				getEstadoCartera(reporteCOBForm.getEstadoCartera()));

		String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(),
				"COD_ZONA_CLIE", "'");
		String condicionRegion = obtieneCondicion(
				reporteCOBForm.getRegionList(), "COD_REGI_CLIE", "'");

		String condicion = condicionZonas + condicionRegion;

		params.put("condicion", condicion);

		log.debug(" Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
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
		String[] valor = (String[]) val.getNewValue();
		if (valor.length > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			this.siccZonaList = ajax
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor,
							Constants.FORMATEAR_TODOS);

		}else 
			this.siccZonaList = new LabelValue[]{};			
	}

	/**
	 * Metodo para obtener Lista de Etapas
	 * 
	 * @param val
	 */
	public void loadEtapas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadEtapas");
		}
		String valor = (String) val.getNewValue();
		if (valor.length() > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue[] dd = ajax
					.getEtapasDeudaByPaisSociedad(this.mPantallaPrincipalBean
							.getCurrentCountry().getCodigo(), valor);
			this.siccEtapaDeudaList = dd;
			

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
	 * @return
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
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
	 * @return the siccEtapaDeudaList
	 */
	public LabelValue[] getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList
	 *            the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(LabelValue[] siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	/**
	 * @return the siccCobradoresList
	 */
	public LabelValue[] getSiccCobradoresList() {
		return siccCobradoresList;
	}

	/**
	 * @param siccCobradoresList
	 *            the siccCobradoresList to set
	 */
	public void setSiccCobradoresList(LabelValue[] siccCobradoresList) {
		this.siccCobradoresList = siccCobradoresList;
	}

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 *            the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

}

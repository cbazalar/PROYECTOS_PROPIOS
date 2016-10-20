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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECListadoDeudaPendPeriodoForm;

@ManagedBean
@SessionScoped
public class ReporteRECListadoDeudaPendPeriodoAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 6423608164293393968L;
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccTerritorioList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECListadoDeudaPendPeriodoForm reporteForm = new ReporteRECListadoDeudaPendPeriodoForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteRECListadoDeudaPendPeriodoAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteRECListadoDeudaPendPeriodoForm f = (ReporteRECListadoDeudaPendPeriodoForm) this.formReporte;

		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);

		f.setOidIdiomaIso(usuario.getIdioma().getCodigoISO());

		log.debug("Todo OK: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECListadoDeudaPendPeriodoForm form = (ReporteRECListadoDeudaPendPeriodoForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS0".equals(this.formatoReporte))
			return "reporteRECListadoDeudaPendPeriodoConsolidadoXLS";
		else if ("XLS1".equals(this.formatoReporte))
			return "reporteRECListadoDeudaPendPeriodoXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECListadoDeudaPendPeriodoForm form = (ReporteRECListadoDeudaPendPeriodoForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF0".equals(this.formatoReporte))
			return "reporteRECListadoDeudaPendPeriodoConsolidadoPDF";
		else
			return "reporteRECListadoDeudaPendPeriodoPDF";

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECListadoDeudaPendPeriodoForm form = (ReporteRECListadoDeudaPendPeriodoForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicio());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFin());
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
		ReporteRECListadoDeudaPendPeriodoForm reporteRECForm = (ReporteRECListadoDeudaPendPeriodoForm) this.formReporte;
		formatoReporte = this.formatoExportacion
				+ reporteRECForm.getTipoReporte();
		reporteRECForm.setFormatoExportacion(this.formatoExportacion);
		String condicionZonas = obtieneCondicion(reporteRECForm.getZonaList(),
				"ZON.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(
				reporteRECForm.getRegionList(), "R.COD_REGI", "'");
		String condicionTerritorio = obtieneCondicion(
				reporteRECForm.getTerritorioList(), "TE.COD_TERR", "'");
		String condicion = condicionZonas + condicionRegion
				+ condicionTerritorio;
		params.put("condicion", condicion);

		params.put(
				"NroReporte",
				getResourceMessage("reporteRECListadoDeudaPendPeriodoForm.numero.reporte"));
		params.put(
				"titulo",
				getResourceMessage("reporteRECListadoDeudaPendPeriodoForm.titulo"));
		
		reporteRECForm.setFechaFacturacion(DateUtil.convertDateToString(reporteRECForm.getFechaFacturacionD()));
		params.put("fechaFacturacion", reporteRECForm.getFechaFacturacion());
		
		/* Obteniendo oid periodos */
		Map paramPeriodo = new HashMap();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String codigoPeriodoInicio = reporteRECForm.getCodigoPeriodoInicio();
		paramPeriodo.put("codigoPeriodo", codigoPeriodoInicio);
		int oidPeriodoInicio = reporteService.getOidPeriodo(paramPeriodo);
		params.put("oidPeriodoInicio", oidPeriodoInicio);
		
		String codigoPeriodoFin = reporteRECForm.getCodigoPeriodoFin();
		paramPeriodo.put("codigoPeriodo", codigoPeriodoFin);
		int oidPeriodoFin = reporteService.getOidPeriodo(paramPeriodo);
		params.put("oidPeriodoFin", oidPeriodoFin);
		
		
		log.info("Salio a ReporteRECListadoDeudaPendPeriodoAction - prepareParameterMap");
		return params;
	}

	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		if ("PDF0".equals(this.formatoReporte) || "XLS0".equals(this.formatoReporte)) {
			reporteService.insertarReporteRECListadoDeudaPendPeriodoConsolidado(params);
		}
		else {
			reporteService.insertarReporteRECListadoDeudaPendPeriodoDetallado(params);
		}
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
		ReporteRECListadoDeudaPendPeriodoForm form = (ReporteRECListadoDeudaPendPeriodoForm) this.formReporte;
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

	
	
	/* GET -SET */
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

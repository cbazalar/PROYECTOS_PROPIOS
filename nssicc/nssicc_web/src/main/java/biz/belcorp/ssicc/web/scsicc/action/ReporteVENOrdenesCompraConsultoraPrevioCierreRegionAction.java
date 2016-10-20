package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm;

/**
 * The Class ReporteVENOrdenesCompraConsultoraPrevioCierreRegionAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 13/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteVENOrdenesCompraConsultoraPrevioCierreRegionAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 3465057011020292343L;
	private String formatoReporte;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccSeccionList;
	private LabelValue[] siccTerritorioList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm form = new ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteVENOrdenesCompraConsultoraPrevioCierreRegionAction.setViewAtributes' method");
		}
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
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

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm reporteVENForm = (ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm) this.formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga Fecha y Periodo
		reporteVENForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		reporteVENForm.setCodigoPais(pais.getCodigo());
		loadFechasPeriodos(reporteVENForm.getCodigoPeriodo());
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
		this.formatoReporte = ((ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm) this.formReporte)
				.getFormatoExportacion();
		if ("XLS".equals(formatoReporte)) {
			return "reporteVENOrdenesCompraConsultoraPrevioCierreRegionFormXLS";
		} else {
			return "reporteVENOrdenesCompraConsultoraPrevioCierreRegionFormPDF";
		}
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
		ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm form = (ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm) this.formReporte;
		Date fecha1D = form.getFechaDesdeD();
		Date fecha2D = form.getFechaHastaD();
		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteVENOrdenesCompraConsultoraPrevioCierreRegionForm.errors.compare.fecha");
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
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm reporteVENForm = (ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm) this.formReporte;
		formatoReporte = reporteVENForm.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = params;
		criteria.put("codigoPeriodo", reporteVENForm.getCodigoPeriodo());
		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);

		String fecha1 = DateUtil.getDate(reporteVENForm.getFechaDesdeD());
		String fecha2 = DateUtil.getDate(reporteVENForm.getFechaHastaD());
		reporteVENForm.setFechaDesde(fecha1);
		reporteVENForm.setFechaHasta(fecha2);

		String fechaDesde = reporteVENForm.getFechaDesde();
		String fechaHasta = reporteVENForm.getFechaHasta();

		String condicionZonas = obtieneCondicion(reporteVENForm.getZonaList(),
				"ZON.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(
				reporteVENForm.getRegionList(), "REG.COD_REGI", "'");
		String condicionTerritorio = obtieneCondicion(
				reporteVENForm.getTerritorioList(), "TER.COD_TERR", "'");
		String condicionSeccion = obtieneCondicion(
				reporteVENForm.getSeccionList(), "SEC.COD_SECC", "'");
		String condicion = condicionZonas + condicionRegion
				+ condicionTerritorio + condicionSeccion;

		params.put("oidPeriodo", oidPeriodo);
		params.put("condicion", condicion);
		params.put("NroReporte", "");
		params.put("fechaDesde", fechaDesde);
		params.put("fechaHasta", fechaHasta);
		params.put(
				"titulo",
				getReportResourceMessage("reporteVENOrdenesCompraConsultoraPrevioCierreRegionForm.titulo")
						+ reporteVENForm.getCodigoPeriodo());
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	/**
	 * Show zonasx region.
	 * 
	 * @param val
	 *            the val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		try {
			log.debug(">>showZonasxRegion ");
			log.debug("val: " + val.getNewValue().toString());
			ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm form = (ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
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
				this.siccSeccionList = null;
				this.siccTerritorioList = null;
				this.siccZonaList = null;
				form.setZonaList(null);
				form.setTerritorioList(null);
				form.setSeccionList(null);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo para obtener Seccion por Zona
	 * 
	 * @param val
	 */
	public void showSeccionxZona(ValueChangeEvent val) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("showSeccionxZona");
			}
			ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm form = (ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm) this.formReporte;
			String[] regiones = (String[]) form.getRegionList();
			String[] zonas = (String[]) val.getNewValue();
			if (!val.equals(null) && zonas.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccSeccionList(aSvc
						.getSeccionMultipleByPaisMarcaCanalRegionZona(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, regiones,
								zonas, Constants.FORMATO_TOTAL));

				form.setSeccionList(null);
			} else {
				this.siccSeccionList = null;
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
				form.setSeccionList(null);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo para obtener Territorio por Seccion
	 * 
	 * @param val
	 */
	public void showTerritorioxSeccion(ValueChangeEvent val) {
		try {
			log.debug(">>showTerritorioxZona ");
			log.debug("val: " + val.getNewValue().toString());
			ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm form = (ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm) this.formReporte;
			String[] regiones = (String[]) form.getRegionList();
			String[] zonas = (String[]) form.getZonaList();
			String[] secciones = (String[]) val.getNewValue();
			if (!val.equals(null) && secciones.length > 0) {
				ArrayList<String> listaRegiones = new ArrayList<String>(
						Arrays.asList(regiones));
				ArrayList<String> listaZonas = new ArrayList<String>(
						Arrays.asList(zonas));
				ArrayList<String> listaSecciones = new ArrayList<String>(
						Arrays.asList(secciones));
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccTerritorioList(aSvc
						.getTerritoriosMultipleByPaisMarcaCanalRegionZonaSeccion(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, listaRegiones,
								listaZonas, listaSecciones,
								Constants.FORMATO_TOTAL));
				form.setTerritorioList(null);
			} else {
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * loadFechasPeriodos
	 * 
	 * @param val
	 */
	public void loadFechasPeriodos(String val) {
		try{
		log.debug(">>loadFechasPeriodos ");
		log.debug("val: " + val);
		ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm form = (ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		try {
			form.setFechaDesdeD(DateUtil.convertStringToDate(
					Constants.DEFAULT_DATE_FORMAT, ajaxService
							.getFechaInicioPeriodoByPaisMarcaCanal(
									form.getCodigoPais(),
									Constants.CODIGO_MARCA_DEFAULT,
									Constants.CODIGO_CANAL_DEFAULT, val)));
			form.setFechaHastaD(DateUtil.convertStringToDate(
					Constants.DEFAULT_DATE_FORMAT, ajaxService
							.getFechaFinalPeriodoByPaisMarcaCanal(
									form.getCodigoPais(),
									Constants.CODIGO_MARCA_DEFAULT,
									Constants.CODIGO_CANAL_DEFAULT, val)));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
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
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

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENUnidadesVentaEstadisticableForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteVENUnidadesVentaEstadisticableAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -7054679091584808093L;
	private String formatoReporte;
	private String tipoTotal;
	private String codigoIdiomaISO;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccTerritorioList = {};
	private LabelValue[] siccZonaList = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.mostrarReporteXLS = true;
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
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
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

		ReporteVENUnidadesVentaEstadisticableForm reporteVENForm = (ReporteVENUnidadesVentaEstadisticableForm) this.formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga Fecha y Periodo
		reporteVENForm.setCodigoPais(pais.getCodigo());

		reporteVENForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		reporteVENForm.setCodigoPeriodo(periodo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteVENUnidadesVentaEstadisticableForm form = (ReporteVENUnidadesVentaEstadisticableForm) this.formReporte;
		Date fecha1D, fecha2D;
		fecha1D = form.getFechaDesdeD();
		fecha2D = form.getFechaHastaD();

		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this.getResourceMessage("errors.compare.dates");
			// reporteRECEmisionNotaCreditoForm.validar.fechas
			return mensaje;
		}
		return null;
	}

	/**
	 * @param Muestra
	 *            las zonas por regiones escogidas.
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteVENUnidadesVentaEstadisticableForm form = (ReporteVENUnidadesVentaEstadisticableForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0 && !regiones[0].equals("") ) {
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
			this.siccZonaList = null;
			this.siccSeccionList = null;
			this.siccTerritorioList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param Muestra
	 *            las secciones por las zonas seleccionadas.
	 */
	public void showSeccionxZona(ValueChangeEvent val) {
		log.debug(">>showSeccionxZona ");
		try {
			ReporteVENUnidadesVentaEstadisticableForm form = (ReporteVENUnidadesVentaEstadisticableForm) this.formReporte;

			String[] regiones = (String[]) form.getRegionList();

			String[] zonas = (String[]) val.getNewValue();

			if (!val.equals(null) && zonas.length > 0 && !zonas[0].equals("")) {
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
			this.siccSeccionList = null;
			this.siccTerritorioList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param Muestra
	 *            los territorios por la secciones escogidas.
	 */
	public void showTerritorioxSeccion(ValueChangeEvent val) {
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteVENUnidadesVentaEstadisticableForm form = (ReporteVENUnidadesVentaEstadisticableForm) this.formReporte;

			String[] regiones = (String[]) form.getRegionList();

			String[] zonas = (String[]) form.getZonaList();

			String[] secciones = (String[]) val.getNewValue();

			if (!val.equals(null) && secciones.length > 0 && !secciones[0].equals("")) {
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
		ReporteVENUnidadesVentaEstadisticableForm form = new ReporteVENUnidadesVentaEstadisticableForm();
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
			return "reporteVENUnidadesVentaEstadisticable" + tipoTotal + "XLS";
		else
			return "reporteVENUnidadesVentaEstadisticable" + tipoTotal + "PDF";
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
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteVENUnidadesVentaEstadisticableForm reporteVENForm = (ReporteVENUnidadesVentaEstadisticableForm) this.formReporte;
		this.tipoTotal = reporteVENForm.getTipoTotal();
		this.formatoReporte = reporteVENForm.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = params;
		criteria.put("codigoPeriodo", reporteVENForm.getCodigoPeriodo());
		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);

		if(reporteVENForm.getFechaDesdeD() != null){
			reporteVENForm.setFechaDesde(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteVENForm.getFechaDesdeD()));
		}
		
		if(reporteVENForm.getFechaHastaD() != null){
			reporteVENForm.setFechaHasta(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteVENForm.getFechaHastaD()));
		}
		
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
				getReportResourceMessage("reporteVENUnidadesVentaEstadisticableForm.titulo")
						+ reporteVENForm.getCodigoPeriodo());

		return params;
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
	 * @return the tipoTotal
	 */
	public String getTipoTotal() {
		return tipoTotal;
	}

	/**
	 * @param tipoTotal
	 *            the tipoTotal to set
	 */
	public void setTipoTotal(String tipoTotal) {
		this.tipoTotal = tipoTotal;
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
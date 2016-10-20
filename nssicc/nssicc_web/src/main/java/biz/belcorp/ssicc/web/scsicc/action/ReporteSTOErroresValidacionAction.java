package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOErroresValidacionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTOErroresValidacionAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -3187348200754255342L;
	private String formatoReporte;
	private String tipoReporte;
	private String tipoAgrupamiento;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List stoTipoDocumentoList = new ArrayList();
	private String STO_TIPO_DOCUMENTO_CABECERA;
	private String STO_TIPO_DOCUMENTO_DETALLE;
	private String STO_TIPO_DOCUMENTO_TODOS;
	private Date fechaini;
	private Date fechafin;
	private String tipoActualHistorico;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteService reporteService = (ReporteService) this
				.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());

		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteria);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}

		ReporteSTOErroresValidacionForm reporteSTOForm = (ReporteSTOErroresValidacionForm) this.formReporte;

		// Carga periodo
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
																		// Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) this
				.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteriaPeriodo);

		reporteSTOForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		reporteSTOForm.setCodigoPais(pais.getCodigo());

		// Carga lista de documentos
		ProcesoSTOEjecucionValidacionesService procesoSTOService = (ProcesoSTOEjecucionValidacionesService) this
				.getBean("spusicc.procesoSTOEjecucionValidacionesService");

		stoTipoDocumentoList = procesoSTOService
				.getTiposDocumentosSTO(criteria);

		loadFechasPeriodos(reporteSTOForm.getCodigoPeriodo());

		STO_TIPO_DOCUMENTO_CABECERA = Constants.STO_TIPO_DOCUMENTO_CABECERA;
		STO_TIPO_DOCUMENTO_DETALLE = Constants.STO_TIPO_DOCUMENTO_DETALLE;
		STO_TIPO_DOCUMENTO_TODOS = Constants.STO_TIPO_DOCUMENTO_TODOS;
		if (StringUtils.isBlank(STO_TIPO_DOCUMENTO_TODOS)) {
			this.setSTO_TIPO_DOCUMENTO_TODOS("T");
		}
		
		this.siccZonaList = new LabelValue[1];
		LabelValue labelValue = new LabelValue();
		labelValue.setLabel("Todos");
		labelValue.setValue("");
		this.siccZonaList[0] = labelValue;
		this.tipoActualHistorico="ACTU";

	}

	/**
	 * Load fechas periodos.
	 */
	public void loadFechasPeriodos(String val) {
		log.debug("loadFechasPeriodos...");
		ReporteSTOErroresValidacionForm form = (ReporteSTOErroresValidacionForm) this.formReporte;		
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		form.setCodigoPeriodo(val);
		try {

			String fechaDesde = ajaxService
					.getFechaInicioPeriodoByPaisMarcaCanal(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT,
							form.getCodigoPeriodo());
			String fechaHasta = ajaxService
					.getFechaFinalPeriodoByPaisMarcaCanal(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT,
							form.getCodigoPeriodo());

			form.setFechaDesdeD(DateUtil.convertStringToDate(fechaDesde));
			form.setFechaHastaD(DateUtil.convertStringToDate(fechaHasta));

			this.fechaini = DateUtil.convertStringToDate(fechaDesde);
			this.fechafin = DateUtil.convertStringToDate(fechaHasta);

		} catch (ParseException e) {
			log.error(e.getMessage());
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
		ReporteSTOErroresValidacionForm form = (ReporteSTOErroresValidacionForm) this.formReporte;
		if(form.getFechaDesdeD() == null) return null;
		if(form.getFechaHastaD() == null) return null;
		String fechaIni = DateUtil.convertDateToString(fechaini);
		String fechaFin = DateUtil.convertDateToString(fechafin);
		
		
		if (fechaini.compareTo(form.getFechaDesdeD()) == 1
				|| fechafin.compareTo(form.getFechaDesdeD()) < 0) {
			String mensaje = this
					.getResourceMessage("errors.compare.campaigns.periodo.fechaInicio")
					+ fechaIni + " - " + fechaFin;
			return mensaje;
		} else if (fechafin.compareTo(form.getFechaHastaD()) < 0
				|| fechaini.compareTo(form.getFechaHastaD()) == 1) {
			String mensaje = this
					.getResourceMessage("errors.compare.campaigns.periodo.fechaFin")
					+ fechaIni + " - " + fechaFin;
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
		ReporteSTOErroresValidacionForm reporteForm = (ReporteSTOErroresValidacionForm) this.formReporte;
		this.formatoReporte = reporteForm.getFormatoExportacion();
		this.tipoReporte = reporteForm.getTipoConsulta();

		String fecha1 = DateUtil.getDate(reporteForm.getFechaDesdeD());
		String fecha2 = DateUtil.getDate(reporteForm.getFechaHastaD());
		reporteForm.setFechaDesde(fecha1);
		reporteForm.setFechaHasta(fecha2);

		this.tipoAgrupamiento = Constants.STO_TIPO_AGRUPACION_REGION;
		if (reporteForm.getZonaList() != null
				&& reporteForm.getZonaList().length > 0) {
			this.tipoAgrupamiento = Constants.STO_TIPO_AGRUPACION_ZONA;
		}

		String descripcionRegionList = descripcionMultipleLista(
				reporteForm.getRegionList(), this.siccRegionList);
		String descripcionZonaList = descripcionMultipleLista(
				reporteForm.getZonaList(), this.siccZonaList);

		params.put("descripcionRegionList", descripcionRegionList);
		params.put("descripcionZonaList", descripcionZonaList);

		String condicionRegion = this.obtieneCondicion(
				reporteForm.getRegionList(), "A.COD_REGI", "'");
		String condicionZonas = this.obtieneCondicion(
				reporteForm.getZonaList(), "A.COD_ZONA", "'");
		String condicion = condicionRegion + condicionZonas;

		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("tipoDocumento", reporteForm.getTipoDocumento());
		params.put("periodo", reporteForm.getCodigoPeriodo());
		params.put("fechaDesde", reporteForm.getFechaDesde());
		params.put("fechaHasta", reporteForm.getFechaHasta());
		params.put("tipoReporte", this.tipoReporte);
		params.put("condicion", condicion);
		params.put("titulo",this.getResourceMessage("reporteSTOErroresValidacionForm.titulo"));
		return params;
	}

	/**
	 * Show zonasx region.
	 * 
	 * @param val
	 *            the val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteSTOErroresValidacionForm form = (ReporteSTOErroresValidacionForm) this.formReporte;
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
				this.siccZonaList = null;
				form.setZonaList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOErroresValidacionForm form = new ReporteSTOErroresValidacionForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equals(this.tipoActualHistorico, "ACTU"))
			return "reporteSTOErroresValidacion" + this.tipoAgrupamiento + "XLS";
		else
			return "reporteSTOErroresValidacionHistoricoXLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
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
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the tipoAgrupamiento
	 */
	public String getTipoAgrupamiento() {
		return tipoAgrupamiento;
	}

	/**
	 * @param tipoAgrupamiento
	 *            the tipoAgrupamiento to set
	 */
	public void setTipoAgrupamiento(String tipoAgrupamiento) {
		this.tipoAgrupamiento = tipoAgrupamiento;
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
	 * @return the stoTipoDocumentoList
	 */
	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	/**
	 * @param stoTipoDocumentoList
	 *            the stoTipoDocumentoList to set
	 */
	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}

	/**
	 * @return the sTO_TIPO_DOCUMENTO_CABECERA
	 */
	public String getSTO_TIPO_DOCUMENTO_CABECERA() {
		return STO_TIPO_DOCUMENTO_CABECERA;
	}

	/**
	 * @param sTO_TIPO_DOCUMENTO_CABECERA
	 *            the sTO_TIPO_DOCUMENTO_CABECERA to set
	 */
	public void setSTO_TIPO_DOCUMENTO_CABECERA(
			String sTO_TIPO_DOCUMENTO_CABECERA) {
		STO_TIPO_DOCUMENTO_CABECERA = sTO_TIPO_DOCUMENTO_CABECERA;
	}

	/**
	 * @return the sTO_TIPO_DOCUMENTO_DETALLE
	 */
	public String getSTO_TIPO_DOCUMENTO_DETALLE() {
		return STO_TIPO_DOCUMENTO_DETALLE;
	}

	/**
	 * @param sTO_TIPO_DOCUMENTO_DETALLE
	 *            the sTO_TIPO_DOCUMENTO_DETALLE to set
	 */
	public void setSTO_TIPO_DOCUMENTO_DETALLE(String sTO_TIPO_DOCUMENTO_DETALLE) {
		STO_TIPO_DOCUMENTO_DETALLE = sTO_TIPO_DOCUMENTO_DETALLE;
	}

	/**
	 * @return the sTO_TIPO_DOCUMENTO_TODOS
	 */
	public String getSTO_TIPO_DOCUMENTO_TODOS() {
		return STO_TIPO_DOCUMENTO_TODOS;
	}

	/**
	 * @param sTO_TIPO_DOCUMENTO_TODOS
	 *            the sTO_TIPO_DOCUMENTO_TODOS to set
	 */
	public void setSTO_TIPO_DOCUMENTO_TODOS(String sTO_TIPO_DOCUMENTO_TODOS) {
		STO_TIPO_DOCUMENTO_TODOS = sTO_TIPO_DOCUMENTO_TODOS;
	}

	/**
	 * @return the fechaini
	 */
	public Date getFechaini() {
		return fechaini;
	}

	/**
	 * @param fechaini
	 *            the fechaini to set
	 */
	public void setFechaini(Date fechaini) {
		this.fechaini = fechaini;
	}

	/**
	 * @return the fechafin
	 */
	public Date getFechafin() {
		return fechafin;
	}

	/**
	 * @param fechafin
	 *            the fechafin to set
	 */
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	/**
	 * @return the tipoActualHistorico
	 */
	public String getTipoActualHistorico() {
		return tipoActualHistorico;
	}

	/**
	 * @param tipoActualHistorico the tipoActualHistorico to set
	 */
	public void setTipoActualHistorico(String tipoActualHistorico) {
		this.tipoActualHistorico = tipoActualHistorico;
	}
	
	
}
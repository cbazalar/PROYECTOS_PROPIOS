package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.ParseException;
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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOEstadisticoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTOEstadisticoAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = -6531215815148415540L;

	private String formatoReporte;
	private String tipoReporte;
	private String tipoAgrupamiento;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List stoTipoDocumentoList = new ArrayList();
	private String STO_TIPO_DOCUMENTO_CABECERA;
	private String STO_TIPO_DOCUMENTO_DETALLE;
	private String STO_TIPO_DOCUMENTO_TODOS;

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSTOEstadisticoForm reporte = (ReporteSTOEstadisticoForm) this.formReporte;
		this.formatoReporte = reporte.getFormatoExportacion();

		String fecha1 = DateUtil.getDate(reporte.getFechaDesdeD());
		String fecha2 = DateUtil.getDate(reporte.getFechaHastaD());
		reporte.setFechaDesde(fecha1);
		reporte.setFechaHasta(fecha2);

		// Establecer el tipo de reporte segun lo seleccionado en el combo Tipo
		// Consulta
		if (reporte.getTipoConsulta().equals("cabecera")) {
			// Reporte muestra solo cabecera
			this.tipoReporte = "Cabecera";
		} else if (reporte.getTipoConsulta().equals("detalle")) {
			// Reporte muestra solo detalle
			this.tipoReporte = "Detalle";
		} else {
			// Reporte muestra cabecera y detalle
			this.tipoReporte = "";
		}

		// Reporte sin mostrar datos de Zona
		if (reporte.getZonaList() == null && reporte.getRegionList() != null) {
			this.tipoReporte = this.tipoReporte + "ZonaOut";
		}

		// Parametros estandar que se envan a los reportes
		params.put("NroReporte", "");
		params.put("codigoPais", reporte.getCodigoPais());
		params.put("titulo", this
				.getReportResourceMessage("reporteSTOEstadisticoForm.title"));

		// Parametros del reporte
		params.put("tipoDocumento", reporte.getTipoDocumento());

		if (reporte.getPeriodo().equals("")) {
			params.put("periodo", "TODOS");
		} else {
			params.put("periodo", reporte.getPeriodo());
		}

		if (reporte.getFechaDesde().equals("")
				&& !reporte.getFechaHasta().equals("")) {
			params.put("condicionFecha",
					" and a.cod_peri <= '" + reporte.getFechaHasta() + "'");
		} else {
			if (reporte.getFechaHasta().equals("")
					&& !reporte.getFechaDesde().equals("")) {
				params.put("condicionFecha",
						" and a.cod_peri >= '" + reporte.getFechaDesde() + "'");
			} else {
				if (reporte.getFechaDesde().equals("")
						&& reporte.getFechaHasta().equals("")) {
					params.put("condicionFecha", "");
				} else {
					params.put("condicionFecha", "and a.fec_modi between "
							+ "to_date('" + reporte.getFechaDesde()
							+ "','dd/MM/yyyy')" + " and " + "to_date('"
							+ reporte.getFechaHasta() + "','dd/MM/yyyy')");
				}
			}
		}

		if (reporte.getRegionList() != null) {
			params.put("condicionRegion", this.obtieneCondicion(
					reporte.getRegionList(), "a.cod_regi", "'"));
		} else {
			params.put("condicionRegion", " ");
		}

		if (reporte.getZonaList() != null) {
			params.put("condicionZona", this.obtieneCondicion(
					reporte.getZonaList(), "a.cod_zona", "'"));
		} else {
			params.put("condicionZona", " ");
		}
		return params;
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
		ReporteSTOEstadisticoForm form = new ReporteSTOEstadisticoForm();
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
		if ("XLS".equals(this.formatoReporte)) {
			return "reporteSTOEstadistico" + this.tipoReporte + "XLS";
		} else {
			return "reporteMaestroHorizontal";
		}
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
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
			ReporteSTOEstadisticoForm form = (ReporteSTOEstadisticoForm) this.formReporte;
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

	/**
	 * Load fechas periodos.
	 */
	private void loadFechasPeriodos() {
		log.debug("loadFechasPeriodos...");
		ReporteSTOEstadisticoForm form = (ReporteSTOEstadisticoForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		try {
			form.setFechaDesdeD(DateUtil.convertStringToDate(
					Constants.DEFAULT_DATE_FORMAT, ajaxService
							.getFechaInicioPeriodoByPaisMarcaCanal(this
									.getmPantallaPrincipalBean()
									.getCurrentCountry().getCodigo(),
									Constants.CODIGO_MARCA_DEFAULT,
									Constants.CODIGO_CANAL_DEFAULT,
									form.getPeriodo())));
			form.setFechaHastaD(DateUtil.convertStringToDate(
					Constants.DEFAULT_DATE_FORMAT, ajaxService
							.getFechaFinalPeriodoByPaisMarcaCanal(this
									.getmPantallaPrincipalBean()
									.getCurrentCountry().getCodigo(),
									Constants.CODIGO_MARCA_DEFAULT,
									Constants.CODIGO_CANAL_DEFAULT,
									form.getPeriodo())));
		} catch (ParseException e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

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
		ReporteService reportService = (ReporteService) this
				.getBean("scsicc.reporteService");

		ReporteSTOEstadisticoForm f = (ReporteSTOEstadisticoForm) this.formReporte;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());

		// Carga la lista de tipo de documentos
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) this
				.getBean("spusicc.procesoSTOEjecucionValidacionesService");
		stoTipoDocumentoList = procesoSTOEjecucionValidacionesService
				.getTiposDocumentosSTO(criteria);

		// Carga la lista de Regiones
		List listaRegiones = new ArrayList();
		listaRegiones = reportService.getListaGenerico("getRegionesByPais",
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

		// Se setean los valores por default de los filtros
		/*
		 * f.setPeriodo(service.getPeriodoDefaultByPaisCanal(
		 * this.getPais(request.getSession()).getCodigo(),
		 * Constants.CODIGO_CANAL_DEFAULT));
		 */
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

		f.setPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPais(pais.getCodigo());
		this.log.debug("Todo Ok: Redireccionando");
		this.loadFechasPeriodos();
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
}
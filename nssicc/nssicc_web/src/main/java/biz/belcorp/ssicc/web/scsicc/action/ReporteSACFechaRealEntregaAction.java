package biz.belcorp.ssicc.web.scsicc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSACFechaRealEntregaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * The Class ReportePEDPedidosFacturadosCampanaAction.
 * 
 * @autor: Belcorp
 * @version: 1.0 30/05/2014
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSACFechaRealEntregaAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};

	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACFechaRealEntregaForm form = new ReporteSACFechaRealEntregaForm();
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
		ReporteSACFechaRealEntregaForm f = (ReporteSACFechaRealEntregaForm) this.formReporte;
		log.debug(f.getFormatoExportacion());
		if ("XLS".equals(f.getFormatoExportacion())) {
			return "reporteSACFechaRealEntregaXLS";
		} else {
			return "reporteMaestroHorizontal";
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
		ReporteSACFechaRealEntregaForm f = (ReporteSACFechaRealEntregaForm) this.formReporte;
		log.debug(f.getFormatoExportacion());
		if ("PDF".equals(f.getFormatoExportacion())) {
			return "reporteSACFechaRealEntregaPDF";
		} else {
			return "";
		}
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
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap");
		}
		ReporteSACFechaRealEntregaForm reporteSICForm = (ReporteSACFechaRealEntregaForm) this.formReporte;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fechaIni = formato.format(reporteSICForm.getFechaInicio());
		String fechaFin = formato.format(reporteSICForm.getFechaFin());
		
		log.debug(reporteSICForm.getFormatoExportacion());

		params.put("NroReporte", "");

		params.put("titulo", this
				.getResourceMessage("reporteSACFechaRealEntregaForm.titulo"));
		params.put("codigoPais", reporteSICForm.getCodigoPais());
		params.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		params.put("fechaInicio", fechaIni);
		params.put("fechaFin", fechaFin);

		params.put("condicionRegiones", this.obtieneCondicion(
				reporteSICForm.getRegionList(), "TEMP.COD_REGI", "'"));
		params.put("condicionesZonas", this.obtieneCondicion(
				reporteSICForm.getZonaList(), "TEMP.COD_ZONA", "'"));

		return params;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteSICFacturacionAction - setViewAtributes");
		}
		ReporteSACFechaRealEntregaForm reporteSACFechaRealEntregaForm = (ReporteSACFechaRealEntregaForm) formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		Map criteria = new HashMap();

		criteria.put("codigoPais", this.getmPantallaPrincipalBean()
				.getCurrentCountry().getCodigo());
		criteria.put("codigoUsuario", this.getmPantallaPrincipalBean()
				.getCurrentUser());

		List listaRegiones = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int i = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}

		Map params = new HashMap();
		params.put("codigoPais", this.getmPantallaPrincipalBean()
				.getCurrentCountry().getCodigo());
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		params.put("estadoCampanha", Constants.NUMERO_CERO);
		params.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(params);

		reporteSACFechaRealEntregaForm.setCodigoPeriodo(controlFacturacion
				.getCodigoPeriodo());
		params.put("codigoPeriodo",
				reporteSACFechaRealEntregaForm.getCodigoPeriodo());

		Base baseInicioPeriodo = (Base) reporteService.getListaGenerico(
				"getFechaInicioPeriodoByPaisMarcaCanal", params).get(0);
		reporteSACFechaRealEntregaForm.setFechaInicio(DateUtil
				.convertStringToDate(baseInicioPeriodo.getCodigo()));

		Base baseFinPeriodo = (Base) reporteService.getListaGenerico(
				"getFechaFinalPeriodoByPaisMarcaCanal", params).get(0);
		reporteSACFechaRealEntregaForm.setFechaFin(DateUtil
				.convertStringToDate(baseFinPeriodo.getCodigo()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSACFechaRealEntrega2Service";
	}

	/**
	 * Load zonas.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		try {
			String[] valor = (String[]) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaList = ajax
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor,
							Constants.FORMATEAR_TODOS);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if (log.isDebugEnabled()) {
			log.debug("setValidarReporte");
		}
		ReporteSACFechaRealEntregaForm form = (ReporteSACFechaRealEntregaForm) this.formReporte;
		if (form.getFechaInicio() != null && form.getFechaFin() != null) {
			if (form.getFechaInicio().compareTo(form.getFechaFin()) > 0) {
				this.setMensajeAlertaDefault(this
						.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		return null;
	}

	/**
	 * Carga las fechas segun el periodo
	 * 
	 */
	public void loadFechasPeriodo(String valor){
		log.debug("Enter method - loadFechasPeriodo");
		try {
			ReporteSACFechaRealEntregaForm form = (ReporteSACFechaRealEntregaForm) this.formReporte;
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String fechaInicio = "";
			String fechaFinal = "";
			
			if(StringUtils.isNotBlank(valor)){
				fechaInicio = ajax.getFechaInicioPeriodoByPaisMarcaCanal( pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valor);
				fechaFinal = ajax.getFechaFinalPeriodoByPaisMarcaCanal( pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valor);
				
				form.setFechaInicio(DateUtil.convertStringToDate(fechaInicio));
				form.setFechaFin(DateUtil.convertStringToDate(fechaFinal));				
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * Gets the sicc region list.
	 * 
	 * @return the sicc region list
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * Sets the sicc region list.
	 * 
	 * @param siccRegionList
	 *            the new sicc region list
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * Gets the sicc zona list.
	 * 
	 * @return the sicc zona list
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * Sets the sicc zona list.
	 * 
	 * @param siccZonaList
	 *            the new sicc zona list
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}

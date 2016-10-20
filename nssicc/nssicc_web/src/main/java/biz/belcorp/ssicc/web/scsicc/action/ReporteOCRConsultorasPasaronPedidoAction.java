package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteOCRForm;


/**
 * The Class ReporteOCRPedidosNoFacturadosBloqueoAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 20/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteOCRConsultorasPasaronPedidoAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sicc periodo list. */
	private List siccPeriodoList;
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};
	
	/** The ocr clasificacion list. */
	private List ocrClasificacionList = new ArrayList();
	
	/** The mostrar agrupacion. */
	boolean mostrarAgrupacion;
	
	private String tipoReporteMaestro;
	private String reporteExcel;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteOCRForm form = new ReporteOCRForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteOCRForm form = (ReporteOCRForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())){
			return reporteExcel;
		}else{
			return "reporteOCRMaestroVertical" + tipoReporteMaestro;
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteOCRForm form = (ReporteOCRForm)this.formReporte;
		return "reporteOCRConsultorasPasaronPedido" + form.getTipoReporte();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		String auxiliar = "";
		//StringBuilder reporteExcel = new StringBuilder("OCRConsultorasPasaronPed");
		reporteExcel = "OCRConsultorasPasaronPed";
		
		form.setFechaInicial(DateUtil.convertDateToString(DateUtil.getDatePattern(), form.getFechaInicialDate()));
		form.setFechaFinal(DateUtil.convertDateToString(DateUtil.getDatePattern(), form.getFechaFinalDate()));
		
		if (StringUtils.isNotEmpty(form.getAgrupamientoReporte()))
			reporteExcel = reporteExcel + StringUtils.substring(form.getAgrupamientoReporte(), 0, 1);
		if (StringUtils.equals((form.getTipoReporte()), "1")) {
			auxiliar = "Historicos";
			reporteExcel = reporteExcel + "H";
		}
		reporteExcel = reporteExcel + form.getFormatoExportacion();
		form.setTipoReporte(form.getAgrupamientoReporte() + auxiliar);
		tipoReporteMaestro = form.getAgrupamientoReporte();
		form.setReporteExcel(reporteExcel.toString());
	
		String titulo = "Relacion de Consultoras que Pasaron más de un Pedido \n Campaña: ";
		titulo = titulo
				+ StringUtils
						.substring(form.getCodigoPeriodo(), 0, 4)
				+ "/"
				+ StringUtils
						.substring(form.getCodigoPeriodo(), 4, 6);
		/*if (StringUtils.equals((form.getTipoReporte()), "1"))
			titulo = titulo
					+ "\n "
					+ this.getReportResourceMessage("reporteOCRConsultorasPasaronPedidoForm.historicos");
		else
			titulo = titulo
					+ "\n "
					+ this.getReportResourceMessage("reporteOCRConsultorasPasaronPedidoForm.actual");*/

		/*if (StringUtils.isNotEmpty(form.getFechaInicial())
				&& !StringUtils.equals(form.getFechaInicial(), "")) {
			if (StringUtils.isEmpty(form.getFechaFinal())
					|| StringUtils.equals(form.getFechaFinal(), ""))
				titulo = titulo + "\n "
						+ this.getReportResourceMessage("reporte.generico.del")
						+ " " + form.getFechaInicial();
			else
				titulo = titulo + "\n "
						+ this.getReportResourceMessage("reporte.generico.del")
						+ " " + form.getFechaInicial() + " "
						+ this.getReportResourceMessage("reporte.generico.hasta")
						+ " " + form.getFechaFinal();
		} else {
			if (StringUtils.isNotEmpty(form.getFechaFinal())
					&& !StringUtils.equals(form.getFechaFinal(), ""))
				titulo = titulo + "\n "
						+ this.getReportResourceMessage("reporte.generico.hasta")
						+ " " + form.getFechaFinal();
		}*/

		params.put("NroReporte", "REP-SAC-07");
		params.put("titulo", titulo);
		params.put("fechaInicial",form.getFechaInicial());
		params.put("fechaFinal",form.getFechaFinal());
		params.put("codigoRegion",form.getCodigoRegion());
		params.put("codigoZona", form.getCodigoZona());
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}		
		this.mostrarReporteXLS = true;
		
		// Carga de los Periodos
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		List lista = reporteService.getListaPeriodosByBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "0");
		
		if(lista.size() > 0){			
			setSiccPeriodoList(lista);			
		}
		
		//-- Logica - getTipoBloqueos
		List l_tipblo=reporteService.getTipoBloqueos();
		if(l_tipblo.size() > 0){
			this.setOcrClasificacionList(l_tipblo);
		}
		valoresByDefault();		
		loadRegiones();
		loadFechasPeriodos();
	}
	
	/**
	 * Load regiones.
	 */
	private void loadRegiones() {
		log.debug("loadRegiones...");
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		if (StringUtils.equals(form.getTipoReporte(), "1"))			
			setSiccRegionList(ajaxService.getRegionesByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo() ,Constants.FORMATEAR_TODOS));
	    else
	    	setSiccRegionList(ajaxService.getRegionesByPeriodoIntEviPerioRegio( this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),form.getCodigoPeriodo(),Constants.FORMATEAR_TODOS));
		this.loadZonas();
	}
	
	/**
	 * Valores by default.
	 */
	private void valoresByDefault(){
		log.debug("valoresByDefault...");
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		form.setTipoReporte("0");
		if(this.getSiccPeriodoList().size()>0){
			form.setCodigoPeriodo(((Base)this.getSiccPeriodoList().get(0)).getCodigo());
		}		
	}
	
	/**
	 * Load zonas.
	 */
	private void loadZonas(){
		log.debug("loadZonas...");
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		if(StringUtils.equals(form.getTipoReporte(), "1")){
			this.setSiccZonaList(ajaxService.getZonasByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo(), form.getCodigoRegion(), Constants.OPCION_TODOS));
		}else{
			this.setSiccZonaList(ajaxService.getZonasByPeriodoIntEviPerioRegioZona(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo(), form.getCodigoRegion(), Constants.OPCION_TODOS));
		}
	}
	
	/**
	 * Load fechas periodos.
	 */
	private void loadFechasPeriodos(){
		log.debug("loadFechasPeriodos...");
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		try {
			form.setFechaInicialDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo())));
			form.setFechaFinalDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cambia zonas by region.
	 *
	 * @param val the val
	 */
	public void cambiaZonasByRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaZonas...");
		}
		log.debug(val.getNewValue().toString());
		String valor = val.getNewValue().toString();
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) || StringUtils.isNotBlank(val.getNewValue().toString())){
			String[] valores = new String[1];
			valores[0] = val.getNewValue().toString();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			
			if(StringUtils.equals(form.getTipoReporte(), Constants.NUMERO_UNO)){
				setSiccZonaList(ajaxService.getZonasByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),form.getCodigoPeriodo(),valor,Constants.FORMATEAR_TODOS));
			}else{
				setSiccZonaList(ajaxService.getZonasByPeriodoIntEviPerioRegioZona(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),form.getCodigoPeriodo(),valor,Constants.FORMATEAR_TODOS));
			}
			//setSiccZonaList(ajaxService.getZonasByMultiplePeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),form.getCodigoPeriodo(),valores,Constants.OPCION_TODOS));
		}
		this.loadFechasPeriodos();
	}
	
	/**
	 * Cambia region by tipo reporte.
	 *
	 * @param val the val
	 */
	public void cambiaRegionByTipoReporte(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaRegionByTipoReporte...");
		}
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		
		String valor = (String) val.getNewValue();
		
		if(StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)){
			form.setTipoReporte(valor);
						
			if (StringUtils.equals(form.getTipoReporte(), "1")){
				this.setSiccRegionList(ajaxService.getRegionesByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo() ,Constants.FORMATEAR_TODOS));
			}else{
		    	this.setSiccRegionList(ajaxService.getRegionesByPeriodoIntEviPerioRegio(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),form.getCodigoPeriodo(),Constants.FORMATEAR_TODOS));
			}
			
			setSiccPeriodoList(reporteService.getListaPeriodosByBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getTipoReporte()));
		}
		
		this.loadFechasPeriodos();
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	public boolean isMostrarAgrupacion() {
		return mostrarAgrupacion;
	}

	public void setMostrarAgrupacion(boolean mostrarAgrupacion) {
		this.mostrarAgrupacion = mostrarAgrupacion;
	}

	public List getOcrClasificacionList() {
		return ocrClasificacionList;
	}

	public void setOcrClasificacionList(List ocrClasificacionList) {
		this.ocrClasificacionList = ocrClasificacionList;
	}
}

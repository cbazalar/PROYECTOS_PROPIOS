package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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



// TODO: Auto-generated Javadoc
/**
 * The Class ReporteOCRPedidosDigitadosAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 22/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteOCRPedidosDigitadosAction extends BaseReporteAbstractAction {

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
	
	private String fechaInicialPeriodo;
	private String fechaFinalPeriodo;
	private String reporteExcel;
	private String tipoReporteMaestro;

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
		
		return "reporteOCRPedidosDigitados" + form.getTipoReporte();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		String auxiliar = "";
		//StringBuilder reporteExcel = new StringBuilder("OCRPedidosDigitados");
		reporteExcel = "OCRPedidosDigitados";
		
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
		form.setReporteExcel(reporteExcel.toString());
		tipoReporteMaestro = form.getAgrupamientoReporte();
		
		params.put("NroReporte", "REP-SAC-09");
		params.put("titulo", "Reporte de Recibidos pero no Digitados \n Campaña: "
				+ StringUtils
						.substring(form.getCodigoPeriodo(), 0, 4)
				+ "/"
				+ StringUtils
						.substring(form.getCodigoPeriodo(), 4, 6));
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
		//loadFechasPeriodos();
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
		
		try {
			form.setFechaInicialDate(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
			form.setFechaFinalDate(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
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
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) || StringUtils.isNotBlank(val.getNewValue().toString())){
			String[] valores = new String[1];
			valores[0] = val.getNewValue().toString();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.siccZonaList = ajaxService.getZonasByMultiplePeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),form.getCodigoPeriodo(),valores,Constants.OPCION_TODOS);
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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		int resultado = 0;
		int resultado1 = 0;
		String mensaje = "";
		
		if(form.getFechaInicialDate()!=null && form.getFechaFinalDate()!=null){
			if(form.getFechaInicialDate().compareTo(form.getFechaFinalDate())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		
		fechaInicialPeriodo = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo());
		fechaFinalPeriodo = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo());
		
		try {
			if (form.getFechaInicialDate() != null){
				resultado = form.getFechaInicialDate().compareTo(DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaFinalPeriodo));
				resultado1 = DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaInicialPeriodo).compareTo(form.getFechaInicialDate());
				
				/*resultado  = compareDates(fechaDesde ,'dd/MM/yyyy',fechaFinal,'dd/MM/yyyy');    
		    	resultado1 = compareDates(fechaInicio ,'dd/MM/yyyy',fechaDesde,'dd/MM/yyyy');*/
				
				if (resultado == 1 || resultado1 == 1){
		    		mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaInicio") + fechaInicialPeriodo + " - " + fechaFinalPeriodo;
					
					//alert("<fmt:message key="errors.compare.campaigns.periodo.fechaInicio"/>"+ fechaInicio+' - '+fechaFinal) ; 
					return mensaje;
		        }
			}
			
			if (form.getFechaFinalDate() != null) {
				resultado = form.getFechaFinalDate().compareTo(DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaFinalPeriodo));
				resultado1 = DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaInicialPeriodo).compareTo(form.getFechaFinalDate());
				
				/*resultado = compareDates(fechaHasta ,'dd/MM/yyyy',fechaFinal,'dd/MM/yyyy');    
		    	resultado1 = compareDates(fechaInicio ,'dd/MM/yyyy',fechaHasta,'dd/MM/yyyy');*/        
		    	
		    	if (resultado == 1 || resultado1 == 1){
		    		mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaFin") + fechaInicialPeriodo + " - " + fechaFinalPeriodo;
		    		
		    		//alert("<fmt:message key="errors.compare.campaigns.periodo.fechaFin"/>"+ fechaInicio+' - '+fechaFinal) ; 
		        	return mensaje;
		        }   
	      	}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
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
	 * @param siccRegionList the new sicc region list
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
	 * @param siccZonaList the new sicc zona list
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * Gets the sicc periodo list.
	 *
	 * @return the sicc periodo list
	 */
	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * Sets the sicc periodo list.
	 *
	 * @param siccPeriodoList the new sicc periodo list
	 */
	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * Gets the ocr clasificacion list.
	 *
	 * @return the ocr clasificacion list
	 */
	public List getOcrClasificacionList() {
		return ocrClasificacionList;
	}

	/**
	 * Sets the ocr clasificacion list.
	 *
	 * @param ocrClasificacionList the new ocr clasificacion list
	 */
	public void setOcrClasificacionList(List ocrClasificacionList) {
		this.ocrClasificacionList = ocrClasificacionList;
	}
}

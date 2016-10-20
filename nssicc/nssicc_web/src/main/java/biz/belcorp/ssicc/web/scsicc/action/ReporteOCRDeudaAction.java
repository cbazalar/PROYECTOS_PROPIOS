package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
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

/**
 * The Class ReporteOCRDeudaAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 19/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteOCRDeudaAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	
	/** The sicc periodo list. */
	private List siccPeriodoList;
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};	
	
	/** The mostrar agrupacion. */
	boolean mostrarAgrupacion;
	
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
			if (StringUtils.equals(tipoReporteMaestro, ""))
				return "reporteOCRMaestroHorizontal2";
			else
				return "reporteOCRMaestroVertical" + tipoReporteMaestro;
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		
		return "reporteOCRDeuda" + form.getTipoReporte();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		String auxiliar = "";
		//StringBuilder reporteExcel = new StringBuilder("OCRDeuda");
		reporteExcel = "OCRDeuda";
		
		if (StringUtils.isNotBlank(form.getAgrupamientoReporte()))
			reporteExcel = reporteExcel + StringUtils.substring(form.getAgrupamientoReporte(), 0, 1);
		if (StringUtils.equals((form.getTipoReporte()), "1")) {
			auxiliar = "Historicos";
			reporteExcel = reporteExcel + "H";
		}
		reporteExcel = reporteExcel + form.getFormatoExportacion();
		form.setTipoReporte(form.getAgrupamientoReporte().trim() + auxiliar.trim());
		tipoReporteMaestro = form.getAgrupamientoReporte().trim();

		String levantamiento = "";
		String condicion = "";
		if (StringUtils.isBlank(form.getAgrupamientoReporte())) {
			levantamiento = form.getLevantamiento();
			if (StringUtils.equals(form.getLevantamiento(),	"Con Levantamiento")) {
				condicion = "and IND_ADMI_CART=1";
			}
			if (StringUtils.equals(form.getLevantamiento(),"Sin Levantamiento")) {
				condicion = "and IND_ADMI_CART=0";
			}
		}
		String titulo = "Relacion de Consultoras con Deuda Pendiente  \n Campaña: ";
		titulo = titulo	+ StringUtils.substring(form.getCodigoPeriodo(), 0, 4)	+ "/" + StringUtils.substring(form.getCodigoPeriodo(), 4, 6);
		if (StringUtils.equals((form.getTipoReporte()), "1")){
			titulo = titulo	+ "\n (Historicos)";
		}else{
			titulo = titulo + "\n (Actual)";
		}

		form.setFechaInicial(DateUtil.convertDateToString(form.getFechaInicialDate()));
		form.setFechaFinal(DateUtil.convertDateToString(form.getFechaFinalDate()));
		
		if (StringUtils.isNotEmpty(form.getFechaInicial())	&& !StringUtils.equals(form.getFechaInicial(), "")) {
			if (StringUtils.isEmpty(form.getFechaFinal()) || StringUtils.equals(form.getFechaFinal(), "")){
				titulo = titulo + "\n Del "	+  form.getFechaInicial();
			}else{
				titulo = titulo + "\n Del "	+ form.getFechaInicial() + " Hasta " + form.getFechaFinal();
			}
		} else {
			if (StringUtils.isNotEmpty(form.getFechaFinal())	&& !StringUtils.equals(form.getFechaFinal(), ""))
				titulo = titulo + "\n Hasta " + form.getFechaFinal();
		}
		if (StringUtils.isNotEmpty(levantamiento)) {
			titulo = titulo + "\n" + levantamiento;
		}
		params.put("NroReporte", "REP-SAC-04");
		params.put("titulo", titulo);
		params.put("condicion", condicion);
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
		valoresByDefault();
		//loadLevantamiento();
		loadRegiones();
		//loadFechasPeriodos();
	}
	
	/**
	 * Cambia levantamiento by tipo agrupamiento.
	 *
	 * @param val the val
	 */
	public void loadLevantamiento(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadLevantamiento...");
		}
		
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		
		String valor = (String) val.getNewValue();
		
		if(StringUtils.isBlank(valor) || StringUtils.isEmpty(valor)){
			this.setMostrarAgrupacion(false);
		}else{
			this.setMostrarAgrupacion(true);
			form.setLevantamiento("Con Levantamiento");
		}
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
		form.setCodigoRegion(Constants.OPCION_TODOS);
		form.setAgrupamientoReporte("Region");
		
		if(this.getSiccPeriodoList().size()>0){			
			form.setCodigoPeriodo(((Base)this.getSiccPeriodoList().get(0)).getCodigo());
		}
		
		if(StringUtils.isBlank(form.getAgrupamientoReporte()) || StringUtils.isEmpty(form.getAgrupamientoReporte())){
			this.setMostrarAgrupacion(false);
		}else{
			this.setMostrarAgrupacion(true);
			form.setLevantamiento("Con Levantamiento");
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
		String valor = (String) val.getNewValue();
		if(StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
			form.setCodigoRegion(valor);
			if(StringUtils.equals(form.getTipoReporte(), "1")){
				this.setSiccZonaList(ajaxService.getZonasByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo(), form.getCodigoRegion(), Constants.OPCION_TODOS));
			}else{
				this.setSiccZonaList(ajaxService.getZonasByPeriodoIntEviPerioRegioZona(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo(), form.getCodigoRegion(), Constants.OPCION_TODOS));
			}
		}
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
}

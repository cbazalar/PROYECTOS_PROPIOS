/*
 * 
 */
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
 * The Class ReporteOCRMontoMaximoAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 14/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteOCRMontoMaximoAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	
	/** The sicc periodo list. */
	private List siccPeriodoList;
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};
	
	private String fechaInicialPeriodo;
	private String fechaFinalPeriodo;
	private String tipoReporteMaestro;
	private String reporteExel;
	private String tipoReporte;
	
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
			return reporteExel;
		}else{
			if(StringUtils.isBlank(tipoReporteMaestro))
				return "reporteMaestroVertical";
			else
				return "reporteOCRMaestroVertical" + tipoReporteMaestro;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		log.debug("devuelveNombreSubReporte...");
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		if(StringUtils.isNotBlank(this.tipoReporte))		
			return "reporteOCRMontoMaximo" + this.tipoReporte;
		else
			return "reporteOCRMontoMaximo";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		String auxiliar = "";
		reporteExel = "OCRMontoMaximo";
		//StringBuilder reporteExcel = new StringBuilder("OCRMontoMaximo");		
				
		if (StringUtils.isNotBlank(form.getAgrupamientoReporte()))
			reporteExel = reporteExel + StringUtils.substring(form.getAgrupamientoReporte(), 0, 1);
		if (StringUtils.equals((form.getTipoReporte()), "1")) {
			auxiliar = "Historicos";
			reporteExel = reporteExel + "H";
		}
		reporteExel = reporteExel + form.getFormatoExportacion();
		if(StringUtils.isNotBlank(form.getAgrupamientoReporte()))
			this.tipoReporte = form.getAgrupamientoReporte() + auxiliar;
		else
			this.tipoReporte = auxiliar;
		this.tipoReporteMaestro = form.getAgrupamientoReporte();
		params.put("NroReporte", "REP-SAC-02");
		String titulo = "Relacion de Pedidos Rechazados por Monto Máximo  \n Campaña: ";
		titulo = titulo	+ StringUtils.substring(form.getCodigoPeriodo(), 0, 4) + "/" + StringUtils.substring(form.getCodigoPeriodo(), 4, 6);
		if (StringUtils.equals((form.getTipoReporte()), "1"))
			titulo = titulo + "\n (Historicos)";
		else
			titulo = titulo + "\n (Actual)";
//		if(StringUtils.isNotBlank(form.getAgrupamientoReporte()))
//			form.setTipoReporte(form.getAgrupamientoReporte() + auxiliar.trim());
//		else
//			form.setTipoReporte(auxiliar.trim());

		if (StringUtils.isNotEmpty(DateUtil.convertDateToString(form.getFechaInicialDate()))
				&& !StringUtils.equals(DateUtil.convertDateToString(form.getFechaInicialDate()), "")) {
			if (StringUtils.isEmpty(DateUtil.convertDateToString(form.getFechaFinalDate()))
					|| StringUtils.equals(DateUtil.convertDateToString(form.getFechaFinalDate()), "")){
				form.setFechaInicial(DateUtil.convertDateToString("dd/MM/yyyy",form.getFechaInicialDate()));
				titulo = titulo + "\n Del " + form.getFechaInicial();
			}else{
				form.setFechaInicial(DateUtil.convertDateToString("dd/MM/yyyy",form.getFechaInicialDate()));
				form.setFechaFinal(DateUtil.convertDateToString("dd/MM/yyyy",form.getFechaFinalDate()));
				titulo = titulo + "\n Del " + form.getFechaInicial()	+ " Hasta " + form.getFechaFinal();
			}
		} else {
			if (StringUtils.isNotEmpty(DateUtil.convertDateToString(form.getFechaFinalDate()))	&& !StringUtils.equals(DateUtil.convertDateToString(form.getFechaFinalDate()), "")){
				form.setFechaFinal(DateUtil.convertDateToString("dd/MM/yyyy",form.getFechaFinalDate()));
				titulo = titulo + "\n Hasta " + form.getFechaFinal();
			}
		}
		
		params.put("fechaInicial", form.getFechaInicial());
		params.put("fechaFinal", form.getFechaFinal());
		params.put("titulo", titulo);		
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
	}
	
	/**
	 * Valores by default.
	 */
	private void valoresByDefault(){
		log.debug("valoresByDefault...");
		ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
		form.setTipoReporte("0");		
		form.setCodigoRegion(Constants.OPCION_TODOS);
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
		String valor = (String) val.getNewValue();
			if(StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ReporteOCRForm form = (ReporteOCRForm) this.formReporte;
			form.setTipoReporte(valor);
			LabelValue[] labelPeriodos= ajaxService.getListaPeriodosByBasCtrlFact( this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getTipoReporte(), "");
			this.siccPeriodoList = new ArrayList();
			for (int i = 0; i < labelPeriodos.length; i++) {
				Base periodos = new Base();
				periodos.setCodigo(labelPeriodos[i].getValue());
				periodos.setDescripcion(labelPeriodos[i].getLabel());
				this.siccPeriodoList.add(periodos);
			}
						
			if (StringUtils.equals(form.getTipoReporte(), "1")){
				this.setSiccRegionList(ajaxService.getRegionesByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo() ,Constants.FORMATEAR_TODOS));
			}else{
		    	this.setSiccRegionList(ajaxService.getRegionesByPeriodoIntEviPerioRegio(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),form.getCodigoPeriodo(),Constants.FORMATEAR_TODOS));
			}
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

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getFechaInicialPeriodo() {
		return fechaInicialPeriodo;
	}

	public void setFechaInicialPeriodo(String fechaInicialPeriodo) {
		this.fechaInicialPeriodo = fechaInicialPeriodo;
	}

	public String getFechaFinalPeriodo() {
		return fechaFinalPeriodo;
	}

	public void setFechaFinalPeriodo(String fechaFinalPeriodo) {
		this.fechaFinalPeriodo = fechaFinalPeriodo;
	}

	public String getTipoReporteMaestro() {
		return tipoReporteMaestro;
	}

	public void setTipoReporteMaestro(String tipoReporteMaestro) {
		this.tipoReporteMaestro = tipoReporteMaestro;
	}

	public String getReporteExel() {
		return reporteExel;
	}

	public void setReporteExel(String reporteExel) {
		this.reporteExel = reporteExel;
	}
	
	
}
/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteOCRForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACFacturacionAdicionalForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACFacturacionAdicionalAction.
 *
 * @author Belcorp
 * @version 1.0
 * 12/11/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACFacturacionAdicionalAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	
	/** The sicc periodo list. */
	private List siccPeriodoList;
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};
	
	/** The sicc origen list. */
	private LabelValue[] siccOrigenList = {};
	
	private String fechaInicialPeriodo;
	private String fechaFinalPeriodo;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACFacturacionAdicionalForm form = new ReporteSACFacturacionAdicionalForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACFacturacionAdicionalForm form = (ReporteSACFacturacionAdicionalForm) this.formReporte;
		log.debug("formato exportacion..." + form.getFormatoExportacion());
		
		if("XLS".equals(form.getFormatoExportacion())){
			return "reporteSACFacturacionAdicionalXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSACFacturacionAdicionalPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap...");
		}
		ReporteSACFacturacionAdicionalForm form = (ReporteSACFacturacionAdicionalForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		if(form.getFechaInicioDate() != null){
			form.setFechaInicio(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaInicioDate()));
		}
		
		if(form.getFechaFinDate() != null){
			form.setFechaFin(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFinDate()));
		}
		
		
		params.put("codigoPeriodo", form.getCodigoPeriodoInicial());
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", params);
		params.put("oidPeriodo", oidPeriodo);

		params.put("fechaInicio", form.getFechaInicio());
		params.put("fechaFin", form.getFechaFin());

		String condicionZonas = obtieneCondicion( form.getCodigoZona(), "zz.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(form.getCodigoRegion(), "zr.COD_REGI", "'");
		
		String condicionZonasExcel = obtieneCondicion( form.getCodigoZona(), "g.COD_ZONA", "'");
		String condicionRegionExcel = obtieneCondicion(form.getCodigoRegion(), "h.COD_REGI", "'");
		
		params.put("condicionZonasExcel", condicionZonasExcel);
		params.put("condicionRegionExcel", condicionRegionExcel);
		
		params.put("periodoInicial", form.getCodigoPeriodoInicial());
		params.put("periodoFinal", form.getCodigoPeriodoFinal());
		
		String condicion = condicionRegion + condicionZonas;
		params.put("condicion", condicion);
		
		String indicadorFAD = "";
		if (StringUtils.equals(form.getIndicadorFAD(), Constants.SI)) {
			indicadorFAD = " and tota_ped > 1";
		}
		
		params.put("indicadorFAD", indicadorFAD);
		
		params.put("NroReporte", "");
		params.put("titulo", getReportResourceMessage("reporteSACFacturacionAdicionalForm.titulo"));
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
		ReporteSACFacturacionAdicionalForm form = (ReporteSACFacturacionAdicionalForm)this.formReporte;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService)getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		
		this.mostrarReporteXLS = true;
		
		Map criteriaOperacion = new HashMap();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		List listaRegiones = new ArrayList();
		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		//-- Logica
		String codigoPeriodo = service.getPeriodoActivo(criteriaOperacion);
		
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		
		if(listaRegiones.size()>0){
			this.siccRegionList = new LabelValue[listaRegiones.size()]; 		
			int i = 0;
			for(Object object : listaRegiones){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getSiccRegionList()[i] = labelValue;
				i++;
			}
		}
		
		form.setCodigoPeriodo(codigoPeriodo);
		form.setCodigoPeriodoInicial(codigoPeriodo);
		form.setCodigoPeriodoFinal(codigoPeriodo);
		
		try {
			form.setFechaInicioDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, aSvc.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo)));
			form.setFechaFinDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, aSvc.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo)));
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
		ReporteSACFacturacionAdicionalForm form = (ReporteSACFacturacionAdicionalForm) this.formReporte;
		String[] valores = (String[]) val.getNewValue();
		if(valores.length>0){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");			
			this.setSiccZonaList(ajaxService.getZonasByMultiplePeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo(), valores, Constants.OPCION_TODOS));
		}
	}
	
	/**
	 * Load fechas periodos por Periodo Inicio.
	 */
	public void loadFechasPeriodosInicial(String val){
		log.debug("loadFechasPeriodosInicial...");
		
		ReporteSACFacturacionAdicionalForm form = (ReporteSACFacturacionAdicionalForm)this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		
		form.setCodigoPeriodoInicial(val);
		
		try {
			form.setFechaInicioDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load fechas periodos por Periodo Final.
	 */
	public void loadFechasPeriodosFinal(String val){
		log.debug("loadFechasPeriodosFinal...");
		
		ReporteSACFacturacionAdicionalForm form = (ReporteSACFacturacionAdicionalForm)this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		
		form.setCodigoPeriodoFinal(val);
		
		try {
			form.setFechaFinDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoFinal())));
		} catch (ParseException e) {
			e.printStackTrace();
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
		
		ReporteSACFacturacionAdicionalForm form = (ReporteSACFacturacionAdicionalForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		int resultado = 0;
		int resultado1 = 0;
		String mensaje = "";
		
		if(form.getFechaInicioDate()!=null && form.getFechaFinDate()!=null){
			if(form.getFechaInicioDate().compareTo(form.getFechaFinDate())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		
		fechaInicialPeriodo = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial());
		fechaFinalPeriodo = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoFinal());
		
		try {
			if (form.getFechaInicioDate().toString() != ""){
				resultado = form.getFechaInicioDate().compareTo(DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaFinalPeriodo));
				resultado1 = DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaInicialPeriodo).compareTo(form.getFechaInicioDate());
				
				/*resultado  = compareDates(fechaDesde ,'dd/MM/yyyy',fechaFinal,'dd/MM/yyyy');    
		    	resultado1 = compareDates(fechaInicio ,'dd/MM/yyyy',fechaDesde,'dd/MM/yyyy');*/
				
				if (resultado == 1 || resultado1 == 1){
		    		mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaInicio") + fechaInicialPeriodo + " - " + fechaFinalPeriodo;
					
					//alert("<fmt:message key="errors.compare.campaigns.periodo.fechaInicio"/>"+ fechaInicio+' - '+fechaFinal) ; 
					return mensaje;
		        }
			}
			
			if (form.getFechaFinDate().toString() != "") {
				resultado = form.getFechaFinDate().compareTo(DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaFinalPeriodo));
				resultado1 = DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaInicialPeriodo).compareTo(form.getFechaFinDate());
				
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
	 * Gets the sicc origen list.
	 *
	 * @return the sicc origen list
	 */
	public LabelValue[] getSiccOrigenList() {
		return siccOrigenList;
	}

	/**
	 * Sets the sicc origen list.
	 *
	 * @param siccOrigenList the new sicc origen list
	 */
	public void setSiccOrigenList(LabelValue[] siccOrigenList) {
		this.siccOrigenList = siccOrigenList;
	}
}

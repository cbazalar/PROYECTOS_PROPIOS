package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDPedidosFacturadosYobelForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACFacturaDetalleForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACFacturaDetalleAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	
	private LabelValue[] siccAlmacenList = {};
	
	private LabelValue[] siccTipoOperacionList = {};
	
	private LabelValue[] siccZonaList = {};
	
	private String fechaInicialPeriodo;
	private String fechaFinalPeriodo;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACFacturaDetalleForm form = new ReporteSACFacturaDetalleForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACFacturaDetalleForm form = (ReporteSACFacturaDetalleForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteSACFacturaDetalleXLS";
		}
		
		return null;
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");			
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteSACFacturaDetalleForm form = (ReporteSACFacturaDetalleForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService)getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		
		Map params = new HashMap();
		
		List listaPeriodo = reporteService.getListaPeriodosByBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), null);
		if (listaPeriodo.size()>0){
			log.debug("lista llena");
		}else{
			listaPeriodo = new ArrayList();
		}		
		
		params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		String codigoPeriodo = service.getPeriodoActivo(params);
		form.setCodigoPeriodoInicial(codigoPeriodo);
		
		this.siccRegionList = ajaxService.getRegionesByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
																			form.getCodigoPeriodoInicial(), 
																			Constants.FORMATEAR_TODOS);
		
		form.setFechaInicioDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
															Constants.CODIGO_MARCA_DEFAULT, 
															Constants.CODIGO_CANAL_DEFAULT, 
															form.getCodigoPeriodoInicial())));
		
		form.setFechaFinDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
															Constants.CODIGO_MARCA_DEFAULT, 
															Constants.CODIGO_CANAL_DEFAULT, 
															form.getCodigoPeriodoInicial())));
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteSACFacturaDetalleForm form = (ReporteSACFacturaDetalleForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteria = params;
		
		if(form.getFechaInicioDate()!=null){
			form.setFechaInicio(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaInicioDate()));
		}
		
		if(form.getFechaFinDate()!= null){
			form.setFechaFin(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFinDate()));
		}
		
		criteria.put("codigoRegion", form.getCodigoRegion());
		criteria.put("codigoZona", form.getCodigoZona());
		criteria.put("codigoPeriodo", form.getCodigoPeriodoInicial());
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodo", oidPeriodo);
		params.put("fechaInicio", form.getFechaInicio());
		params.put("fechaFin", form.getFechaFin());
		
		if(StringUtils.isBlank(form.getOrigen())){
			params.put("origen", "");
		}else{
			params.put("origen", form.getOrigen());
		}
		
		params.put("titulo", "");
		return params;
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
		String[] valores = (String[]) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteSACFacturaDetalleForm form = (ReporteSACFacturaDetalleForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasByMultiplePeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
																				form.getCodigoPeriodoInicial(), 
																				valores, 
																				Constants.FORMATEAR_TODOS));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		
		ReporteSACFacturaDetalleForm form = (ReporteSACFacturaDetalleForm) this.formReporte;
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
		fechaFinalPeriodo = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial());
		
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
	 * Load fechas periodos.
	 */
	public void loadFechasPeriodos(String val){
		log.debug("loadFechasPeriodos...");
		
		ReporteSACFacturaDetalleForm form = (ReporteSACFacturaDetalleForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		
		form.setCodigoPeriodoInicial(val);
		
		try {
			form.setFechaInicioDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial())));
			form.setFechaFinDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSACFacturaDetalleService";
	}	

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccAlmacenList() {
		return siccAlmacenList;
	}

	public void setSiccAlmacenList(LabelValue[] siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
	}

	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}	
}
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDPedidosFacturadosYobelForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACPedidosRecibidosPorOrigenForm;

/**
 * The Class ReportePEDPedidosFacturadosYobelAction.
 */
@ManagedBean
@SessionScoped
public class ReporteSACPedidosRecibidosPorOrigenAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private String fechaInicialPeriodo;
	private String fechaFinalPeriodo;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACPedidosRecibidosPorOrigenForm form = new ReporteSACPedidosRecibidosPorOrigenForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACPedidosRecibidosPorOrigenForm form = (ReporteSACPedidosRecibidosPorOrigenForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if(StringUtils.equals("XLS", form.getFormatoExportacion())){
			return "reporteSACPedidosRecibXLS";
		}else{
			return null;
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap...");
		}
		
		ReporteSACPedidosRecibidosPorOrigenForm form = (ReporteSACPedidosRecibidosPorOrigenForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.setGenerateTabsXLS(true);
				
		if(form.getFechaInicialDate()!=null){
			form.setFechaInicio(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaInicialDate()));
		}
		
		if(form.getFechaFinDate() != null){
			form.setFechaFin(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFinDate()));
		}
				
		params.put("codigoPais",form.getCodigoPais());
		params.put("codigoPeriodo", form.getCodigoPeriodoInicial());
		params.put("fechaInicio", form.getFechaInicio());
		params.put("fechaFin", form.getFechaFin());
		params.put("NroReporte", "");
		params.put("titulo", "");
		
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
		this.mostrarReportePDF = false;
		
		ReporteSACPedidosRecibidosPorOrigenForm form = (ReporteSACPedidosRecibidosPorOrigenForm)this.formReporte;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
		
		String periodoActual = new String();
	    
		Map params = new HashMap();
		params.put("codigoPais", form.getCodigoPais());
		periodoActual = service.getPeriodoActivo(params);
		
		form.setCodigoPeriodoInicial(periodoActual);
		form.setFechaInicialDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, aSvc.getFechaInicioPeriodoByPaisMarcaCanal(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial())));
		form.setFechaFinDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, aSvc.getFechaFinalPeriodoByPaisMarcaCanal(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial())));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		
		ReporteSACPedidosRecibidosPorOrigenForm form = (ReporteSACPedidosRecibidosPorOrigenForm)this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		int resultado = 0;
		int resultado1 = 0;
		String mensaje = "";
		
		if(form.getFechaInicialDate()!=null && form.getFechaFinDate()!=null){
			if(form.getFechaInicialDate().compareTo(form.getFechaFinDate())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		
		fechaInicialPeriodo = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial());
		fechaFinalPeriodo = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial());
		
		try {
			if (form.getFechaInicialDate().toString() != ""){
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
		
		ReporteSACPedidosRecibidosPorOrigenForm form = (ReporteSACPedidosRecibidosPorOrigenForm)this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		
		form.setCodigoPeriodoInicial(val);
		
		try {
			form.setFechaInicialDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial())));
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
		return "reportes.reporteSACPedidosRecibidosPorOrigenService";
	}
}
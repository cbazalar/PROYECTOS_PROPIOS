/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDPedidosFacturadosYobelForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACFacturacionAdicionalForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportePEDPedidosFacturadosYobelAction.
 *
 * @author Belcorp
 * @version 1.0
 * 12/11/2014
 */
@ManagedBean
@SessionScoped
public class ReportePEDPedidosFacturadosYobelAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDPedidosFacturadosYobelForm form = new ReportePEDPedidosFacturadosYobelForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePEDPedidosFacturadosYobelForm form = (ReportePEDPedidosFacturadosYobelForm) this.formReporte;
		log.debug("formato exportacion..." + form.getFormatoExportacion());
		
		return "reportePEDPedidosFacturadosYobelXLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap...");
		}
		ReportePEDPedidosFacturadosYobelForm form = (ReportePEDPedidosFacturadosYobelForm)this.formReporte;
		
		if(form.getFechaInicialDate()!= null){
			form.setFechaInicial(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,form.getFechaInicialDate()));
		}
		
		if(form.getFechaFinalDate() != null){
			form.setFechaFinal(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,form.getFechaFinalDate()));
		}
		
		String condicionFechaInicial = "";
		String condicionFechaFinal = "";
		
		if(StringUtils.isNotEmpty(form.getFechaInicial())){
			condicionFechaInicial = " AND ped_solic_cabec.FEC_FACT >= to_date('"+form.getFechaInicial()+"','dd/MM/yyyy') ";
			params.put("fechaInicial",form.getFechaInicial());
		}
		
		if(StringUtils.isNotEmpty(form.getFechaFinal())){
			condicionFechaFinal = " AND ped_solic_cabec.FEC_FACT <= to_date('"+form.getFechaFinal()+"','dd/MM/yyyy') ";
			params.put("fechaFinal",form.getFechaFinal());
		}
						
		String condicion = condicionFechaInicial+ condicionFechaFinal;		
		params.put("condicion", condicion);
		params.put("codigoPais", form.getCodigoPais());		
		
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
		
		ReportePEDPedidosFacturadosYobelForm form = (ReportePEDPedidosFacturadosYobelForm)this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	     
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) this.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteriaPeriodo);
		
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		AjaxService ajaxService = (AjaxService)getBean("ajaxService");
		
		form.setFechaInicial(ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo()));
		form.setFechaFinal(ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo()));
		
		if(StringUtils.isNotBlank(form.getFechaInicial()) || StringUtils.isNotEmpty(form.getFechaInicial())){
			form.setFechaInicialDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, form.getFechaInicial()));
		}
		
		if(StringUtils.isNotBlank(form.getFechaFinal()) || StringUtils.isNotEmpty(form.getFechaFinal())){
			form.setFechaFinalDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, form.getFechaFinal()));
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
		ReportePEDPedidosFacturadosYobelForm form = (ReportePEDPedidosFacturadosYobelForm)this.formReporte;
		if(form.getFechaInicialDate()!=null && form.getFechaFinalDate()!=null){
			if(form.getFechaInicialDate().compareTo(form.getFechaFinalDate())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		return null;
	}
	
	/**
	 * Load fechas periodos.
	 */
	public void loadFechasPeriodos(String val){
		log.debug("loadFechasPeriodos...");
		
		ReportePEDPedidosFacturadosYobelForm form = (ReportePEDPedidosFacturadosYobelForm)this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		
		form.setCodigoPeriodo(val);
		
		try {
			form.setFechaInicialDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo())));
			form.setFechaFinalDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

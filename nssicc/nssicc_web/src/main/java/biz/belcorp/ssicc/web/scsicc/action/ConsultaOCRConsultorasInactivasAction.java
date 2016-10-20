/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaOCRConsultorasInactivasForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteOCRMasVeinteUnidadesAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 26/08/2014
 */
@ManagedBean
@SessionScoped
public class ConsultaOCRConsultorasInactivasAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaOCRConsultorasInactivasForm form = new ConsultaOCRConsultorasInactivasForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");			
		}
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = false;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
        ConsultaOCRConsultorasInactivasForm form = (ConsultaOCRConsultorasInactivasForm)formReporte ; 
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga Fecha y Periodo
//        consultorasInactivasForm.setFechaFact(controlFacturacion.getFechaProceso());
        form.setPeriodo(controlFacturacion.getCodigoPeriodo());
        form.setCodigoPais((String)criteria.get("codigoPais"));
        
        form.setFechaInicioDate(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
        form.setFechaFinDate(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
        //this.loadFechasPeriodo();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ConsultaOCRConsultorasInactivasForm form = (ConsultaOCRConsultorasInactivasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String valor = "";
		if ("XLS".equals(form.getFormatoExportacion())) {			
			valor =  "reporteMaestroHorizontal";
		}
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes...");
		}
		ConsultaOCRConsultorasInactivasForm f = (ConsultaOCRConsultorasInactivasForm) formReporte;

		Map criteria = BeanUtils.describe(f);

		// La busqueda solo la realizaremos en los sistemas activos
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		if (log.isDebugEnabled()) {
			log.debug("criteria search " + criteria.toString());
		}

		List lista = service.getOCRConsultorasInactivasList(criteria);		
		return lista;
	}
	
	/**
	 * Load fechas periodo.
	 */
	private void loadFechasPeriodo(){
		if(log.isDebugEnabled()){
			log.debug("loadFechasPeriodo...");
		}
		ConsultaOCRConsultorasInactivasForm form = (ConsultaOCRConsultorasInactivasForm) formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		try{
			form.setFechaInicioDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT,ajaxService.getFechaInicioPeriodoByPaisMarcaCanal( form.getCodigoPais(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getPeriodo())));
			form.setFechaFinDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT,ajaxService.getFechaFinalPeriodoByPaisMarcaCanal( form.getCodigoPais(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getPeriodo())));
			if(form.getFechaInicioDate()!=null){
				form.setFechaInicio(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaInicioDate()));
			}
			if(form.getFechaFinDate()!=null){
				form.setFechaFin(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFinDate()));
			}
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
		ConsultaOCRConsultorasInactivasForm form = (ConsultaOCRConsultorasInactivasForm) this.formReporte;
		if(form.getFechaInicioDate()!=null && form.getFechaFinDate()!=null){
			if(form.getFechaInicioDate().compareTo(form.getFechaFinDate())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		return null;
	}
}

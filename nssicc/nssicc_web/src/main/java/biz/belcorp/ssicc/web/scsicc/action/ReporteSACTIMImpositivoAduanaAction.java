package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSACTIMImpositivoAduanaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACFacturaDetalleForm;


/**
 * The Class ReporteSACTIMImpositivoAduanaAction
 *
 * @autor: cbazalar
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACTIMImpositivoAduanaAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	
	private LabelValue[] siccAlmacenList = {};
	
	private LabelValue[] siccZonaList = {};
	
	private String fechaInicialPeriodo;
	private String fechaFinalPeriodo;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACTIMImpositivoAduanaForm form = new ReporteSACTIMImpositivoAduanaForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACTIMImpositivoAduanaForm form = (ReporteSACTIMImpositivoAduanaForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteSACTIMImpositivoAduanaXLS";
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
		
		ReporteSACTIMImpositivoAduanaForm form = (ReporteSACTIMImpositivoAduanaForm) this.formReporte;
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
		form.setCodigoPeriodo(codigoPeriodo);
		
		this.siccRegionList = ajaxService.getRegionesByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
																			form.getCodigoPeriodo(), 
																			Constants.FORMATEAR_TODOS);
		
		form.setFechaInicioDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, 
				                                            ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
															Constants.CODIGO_MARCA_DEFAULT, 
															Constants.CODIGO_CANAL_DEFAULT, 
															form.getCodigoPeriodo())));
		
		form.setFechaFinDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, 
				                                            ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
															Constants.CODIGO_MARCA_DEFAULT, 
															Constants.CODIGO_CANAL_DEFAULT, 
															form.getCodigoPeriodo())));
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteSACTIMImpositivoAduanaForm form = (ReporteSACTIMImpositivoAduanaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteria = params;
		
		if(form.getFechaInicioDate()!=null){
			form.setFechaInicio(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaInicioDate()));
		}
		
		if(form.getFechaFinDate()!= null){
			form.setFechaFin(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFinDate()));
		}
		
		params.put("codigoUsuario", this.mPantallaPrincipalBean.getCurrentUser().getLogin());
		params.put("fechaInicio", form.getFechaInicio());
		params.put("fechaFin", form.getFechaFin());	
		params.put("titulo", "");
		
		String[] codigoRegion = form.getCodigoRegion();
		String[] codigoZona = form.getCodigoZona();
		
		String condicionRegion = this.obtieneCondicionIN(codigoRegion, "", "'");
		if(StringUtils.isNotEmpty(condicionRegion)) condicionRegion = condicionRegion.substring(5, condicionRegion.length()-2);
		
		String condicionZona = this.obtieneCondicionIN(codigoZona, "", "'");
		if(StringUtils.isNotEmpty(condicionZona)) condicionZona = condicionZona.substring(5, condicionZona.length()-2);
		
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
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
		ReporteSACTIMImpositivoAduanaForm form = (ReporteSACTIMImpositivoAduanaForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasByMultiplePeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
																				form.getCodigoPeriodo(), 
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
		String mensaje = "";
		ReporteSACTIMImpositivoAduanaForm form = (ReporteSACTIMImpositivoAduanaForm) this.formReporte;
		if(form.getFechaInicioDate()!=null && form.getFechaFinDate()!=null){
			if(form.getFechaInicioDate().compareTo(form.getFechaFinDate())>0){
				mensaje = this.getResourceMessage("errors.compare.dates");
				return mensaje;
			}
		}
		String fecha1 = DateUtil.convertDateToString(form.getFechaInicioDate());
		String fecha2 = DateUtil.convertDateToString(form.getFechaFinDate());
		int diferencia = DateUtil.diferenciaFechas(fecha1, fecha2, 1);
		if (diferencia > 31) {
			mensaje = this.getResourceMessage("reporteSACTIMImpositivoAduanaForm.error.dateMayor31");
			return mensaje;
		}
		return mensaje;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSACTIMImpositivoAduanaService";
	}	
	
	
	
	
	
	/* GET - SET */

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

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}	
}
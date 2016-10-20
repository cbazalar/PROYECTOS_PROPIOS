/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
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
import biz.belcorp.ssicc.web.scsicc.form.ReporteOCRMontoMayorForm;

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
public class ReporteOCRMontoMayorAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	
	/** The sicc periodo list. */
	private List siccPeriodoList;
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteOCRMontoMayorForm form = new ReporteOCRMontoMayorForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteOCRMontoMayorForm form = (ReporteOCRMontoMayorForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			return "OCRMontoMayorXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		log.debug("devuelveNombreSubReporte...");
		return "reporteOCRMontoMayor";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRMontoMayorForm form = (ReporteOCRMontoMayorForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = params;
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		criteria.put("codigoPeriodo", form.getCodigoPeriodo());
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPais", oidPais);
		params.put("oidPeriodo", oidPeriodo);
		params.put("NroReporte", this.getReportResourceMessage("reporteOCRMontoMayorForm.numero.reporte"));		
		String titulo = this.getReportResourceMessage("reporteOCRMontoMayorForm.titulo") + " "
				+ form.getMontoMayor()	+ " \n " + 
				this.getReportResourceMessage("reporteOCRMontoMayorForm.campaniaDosPuntos")	+ "  "
				+ StringUtils.substring(form.getCodigoPeriodo(), 0, 4)
				+ "/" + StringUtils.substring(form.getCodigoPeriodo(), 4, 6);
		if (StringUtils.isNotEmpty(DateUtil.convertDateToString(form.getFechaInicialDate())) && !StringUtils.equals(DateUtil.convertDateToString(form.getFechaInicialDate()), "")){
			if (StringUtils.isEmpty(DateUtil.convertDateToString(form.getFechaFinalDate())) || StringUtils.equals(DateUtil.convertDateToString(form.getFechaFinalDate()), "")){
				form.setFechaInicial(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,form.getFechaInicialDate()));
				titulo = titulo + "\n "	+ this.getReportResourceMessage("reporte.generico.del")	+ " " + form.getFechaInicial();
			}else{
				form.setFechaInicial(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,form.getFechaInicialDate()));
				form.setFechaFinal(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,form.getFechaFinalDate()));
				titulo = titulo + "\n "
						+ this.getReportResourceMessage("reporte.generico.del")
						+ " " + form.getFechaInicial()
						+ " "
						+ this.getReportResourceMessage("reporte.generico.hasta")
						+ " " + form.getFechaFinal();
			}
		} else {
			if (StringUtils.isNotEmpty(DateUtil.convertDateToString(form.getFechaFinalDate())) && !StringUtils.equals(DateUtil.convertDateToString(form.getFechaFinalDate()), "")){
				form.setFechaFinal(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,form.getFechaFinalDate()));
				titulo = titulo + "\n "	+ this.getReportResourceMessage("reporte.generico.hasta") + " " + form.getFechaFinal();
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
		
		/*List lista = reporteService.getListaPeriodosByBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "0");
		
		if(lista.size() > 0){			
			setSiccPeriodoList(lista);			
		}*/
		
		valoresByDefault();		
		loadRegiones();
		//loadFechasPeriodos();
	}
	
	/**
	 * Load regiones.
	 */
	private void loadRegiones() {
		log.debug("loadRegiones...");
		ReporteOCRMontoMayorForm form = (ReporteOCRMontoMayorForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		this.setSiccRegionList(ajaxService.getRegionesByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT));
	}
	
	/**
	 * Valores by default.
	 */
	private void valoresByDefault(){
		log.debug("valoresByDefault...");
		ReporteOCRMontoMayorForm form = (ReporteOCRMontoMayorForm) this.formReporte;
		form.setCodigoRegion(Constants.OPCION_TODOS);
		
		/*if(this.getSiccPeriodoList().size()>0){			
			form.setCodigoPeriodo(((Base)this.getSiccPeriodoList().get(0)).getCodigo());
		}*/
	}
	
	/**
	 * Load zonas.
	 */
	private void loadZonas(){
		log.debug("loadZonas...");
		ReporteOCRMontoMayorForm form = (ReporteOCRMontoMayorForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		this.setSiccZonaList(ajaxService.getZonasByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_CANAL_DEFAULT, Constants.CODIGO_MARCA_DEFAULT, Constants.OPCION_TODOS));
	}
	
	/**
	 * Load fechas periodos.
	 */
	private void loadFechasPeriodos(){
		log.debug("loadFechasPeriodos...");
		ReporteOCRMontoMayorForm form = (ReporteOCRMontoMayorForm) this.formReporte;
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
			ReporteOCRMontoMayorForm form = (ReporteOCRMontoMayorForm) this.formReporte;
			form.setCodigoRegion(valor);
			
			this.setSiccZonaList(ajaxService.getZonasByPaisMarcaCanalRegion( this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "T", "VD", form.getCodigoRegion()));
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
			ReporteOCRMontoMayorForm form = (ReporteOCRMontoMayorForm) this.formReporte;
			form.setTipoReporte(valor);
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
		ReporteOCRMontoMayorForm form = (ReporteOCRMontoMayorForm) this.formReporte;
		if(form.getFechaInicialDate()!=null && form.getFechaFinalDate()!=null){
			if(form.getFechaInicialDate().compareTo(form.getFechaFinalDate())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
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
}

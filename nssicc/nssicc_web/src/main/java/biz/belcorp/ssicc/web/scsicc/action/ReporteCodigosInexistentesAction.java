/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

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
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCodigosInexistentesForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteCodigosInexistentesAction.
 *
 * @author Belcorp
 * @version 1.0
 * 12/11/2014
 */
@ManagedBean
@SessionScoped
public class ReporteCodigosInexistentesAction extends BaseReporteAbstractAction {

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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCodigosInexistentesForm form = new ReporteCodigosInexistentesForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCodigosInexistentesForm form = (ReporteCodigosInexistentesForm) this.formReporte;
		log.debug("formato exportacion..." + form.getFormatoExportacion());
		
		if("XLS".equals(form.getFormatoExportacion())){
			return "reporteCodigosInexistentesXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		reporteService.executeReporteCodigosInexistentes(params);
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteCodigosInexistentesPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap...");
		}
		
		ReporteCodigosInexistentesForm form = (ReporteCodigosInexistentesForm) this.formReporte;
		
		if(form.getFechaFacturacionInicioDate() != null){
			form.setFechaFacturacionInicio(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFacturacionInicioDate()));
		}
		
		if(form.getFechaFacturacionFinDate() != null){
			form.setFechaFacturacionFin(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFacturacionFinDate()));
		}
		
		params.put("codigoPais",form.getCodigoPais());
		params.put("periodoInicial", form.getPeriodoInicial());
		params.put("periodoFinal", form.getPeriodoFinal());
				
		params.put("codigoRegion", StringUtils.isBlank(form.getCodigoRegion())?null:form.getCodigoRegion());
		params.put("codigoZona", StringUtils.isBlank(form.getCodigoZona())?null:form.getCodigoZona());
		params.put("fechaFacturacionInicio", StringUtils.isBlank(form.getFechaFacturacionInicio())?null:form.getFechaFacturacionInicio());
		params.put("fechaFacturacionFin", StringUtils.isBlank(form.getFechaFacturacionFin())?null:form.getFechaFacturacionFin());
		params.put("estado", StringUtils.isBlank(form.getEstado())?null:form.getEstado());
		
		params.put("preimpreso", StringUtils.isBlank(form.getPreimpreso())?null:form.getPreimpreso());
		params.put("codigoCliente", StringUtils.isBlank(form.getCodigoCliente())?null:form.getCodigoCliente());
		
		params.put("titulo", getReportResourceMessage("reporteCodigosInexistentesForm.title"));	
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
		
		ReporteCodigosInexistentesForm form = (ReporteCodigosInexistentesForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		List listaRegiones = new ArrayList();
		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
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
		ReporteCodigosInexistentesForm form = (ReporteCodigosInexistentesForm) this.formReporte;
		String codigoRegion = (String) val.getNewValue();
		if(StringUtils.isNotBlank(codigoRegion) || StringUtils.isNotEmpty(codigoRegion)){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");			
			this.setSiccZonaList(ajaxService.getZonasByPaisRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), codigoRegion));
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
		ReporteCodigosInexistentesForm form = (ReporteCodigosInexistentesForm) this.formReporte;
		if(form.getFechaFacturacionInicioDate()!=null && form.getFechaFacturacionFinDate()!=null){
			if(form.getFechaFacturacionInicioDate().compareTo(form.getFechaFacturacionFinDate())>0){
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

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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACPedidoOrigenForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACPedidoOrigenAction.
 *
 * @author Belcorp
 * @version 1.0
 * 12/11/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACPedidoOrigenAction extends BaseReporteAbstractAction {

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
		ReporteSACPedidoOrigenForm form = new ReporteSACPedidoOrigenForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSACPedidoOrigenForm form = (ReporteSACPedidoOrigenForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if("1".equals(form.getTipoRepAgr())){
			return "reporteSACPedidoOrigenConsolidadoRegXLS";
		}else if("2".equals(form.getTipoRepAgr())){
			return "reporteSACPedidoOrigenConsolidadoZonXLS";
		}else{ 
			return "reporteSACPedidoOrigenDetalladoZonXLS";
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
		
		ReporteSACPedidoOrigenForm form = (ReporteSACPedidoOrigenForm) this.formReporte;

		params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
				
		this.setGenerateTabsXLS(true);
		params.put("codigoOrigen", form.getCodigoOrigen());
		
		if(form.getFechaInicialDate() != null){
			form.setFechaInicial(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaInicialDate()));
		}
		
		if(form.getFechaFinalDate() != null){
			form.setFechaFinal(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFinalDate()));
		}
		
		params.put("fechaInicial", form.getFechaInicial());
		params.put("fechaFinal", form.getFechaFinal());
		params.put("codigoRegion", form.getCodigoRegion());
		params.put("codigoZona", form.getCodigoZona());		
		params.put("codigoPerIni", form.getCodigoPeriodoInicial());
		params.put("codigoPerFin", form.getCodigoPeriodoFinal());
		
		if(StringUtils.equals(form.getTipoReporte(), "0") && StringUtils.equals(form.getTipoAgrupacion(), "0")) //-- consolidado y region
        	form.setTipoRepAgr("1");
        else if(StringUtils.equals(form.getTipoReporte(), "0") && StringUtils.equals(form.getTipoAgrupacion(), "1")) //-- consolidado y zona
        	form.setTipoRepAgr("2");
        else if(StringUtils.equals(form.getTipoReporte(), "1") && StringUtils.equals(form.getTipoAgrupacion(), "0")) //-- detallado y region
        	form.setTipoRepAgr("3");
        else //-- detallado y zona
        	form.setTipoRepAgr("4");
		
		log.info("Salio a ReporteSACPedidoOrigenAction - prepareParameterMap");
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
		
		ReporteSACPedidoOrigenForm form = (ReporteSACPedidoOrigenForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		Map criteriaOperacion = new HashMap();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		String periodoActual = new String();
		
		List listaRegiones = new ArrayList();
		
		//-- Variables por defecto
		this.valoresPorDefecto();
		this.loadOrigenes();
		
		//-- Crear pojo
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		//-- Logica 
		periodoActual = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
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
		
		//-- Peticiones respuesta
		form.setCodigoPeriodoInicial(periodoActual);
		form.setCodigoPeriodoFinal(periodoActual);

		log.info("Salio a ReporteSACPedidoOrigenAction - setViewAttributes");
	}
	
	/**
	 * Valores por defecto.
	 */
	private void valoresPorDefecto(){
		if(log.isDebugEnabled()){
			log.debug("valoresPorDefecto");
		}
		
		ReporteSACPedidoOrigenForm form = (ReporteSACPedidoOrigenForm)this.formReporte;
		
		form.setCodigoOrigen("");
		form.setTipoReporte("0");
		form.setTipoAgrupacion("0");		
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
	}
	
	/**
	 * Load origenes.
	 */
	private void loadOrigenes(){
		if(log.isDebugEnabled()){
			log.debug("loadOrigenes");
		}
		ReporteSACPedidoOrigenForm form = (ReporteSACPedidoOrigenForm)this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		this.setSiccOrigenList(ajaxService.getOrigenSTOByTipoDocumento(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.STO_TIPO_DOCUMENTO_OCC));
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
		String[] valores = new String[1];
		valores[0] = valor;
		if(valores.length>0){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");			
			this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valores, Constants.OPCION_TODOS));
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
		ReporteSACPedidoOrigenForm form = (ReporteSACPedidoOrigenForm)this.formReporte;
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

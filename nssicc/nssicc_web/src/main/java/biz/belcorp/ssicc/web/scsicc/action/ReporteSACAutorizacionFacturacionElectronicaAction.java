/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACAutorizacionFacturacionElectronicaForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACAutorizacionFacturacionElectronicaAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};
	
	/** The mostrar popup ocr cliente. */
	private boolean mostrarPopupOCRCliente = false;

	/** The busqueda hip cliente popup search action. */
	@ManagedProperty(value="#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;
	
	/** The Constant POPUP_HIPCLIENTE. */
	private static final String POPUP_OCRCLIENTE = "OCRCLIENTE";
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}		
		StringBuilder cadena = new StringBuilder();
		if (accion.equals(this.POPUP_OCRCLIENTE)) {
			this.busquedaHIPClientePOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClientePOPUPSearchAction.isSeleccionoRegistro()) {								
				Map clienteHipMap = (Map)this.busquedaHIPClientePOPUPSearchAction.getBeanRegistroSeleccionado();
				ReporteSACAutorizacionFacturacionElectronicaForm form = (ReporteSACAutorizacionFacturacionElectronicaForm)this.formReporte;
				form.setCodigoClienteBuscar((String)clienteHipMap.get("codigoCliente"));
				cadena.append((String)clienteHipMap.get("nombre1")+ " ")
						.append((String)clienteHipMap.get("nombre2") + " ")
						.append((String)clienteHipMap.get("apellido1") + " ")
						.append((String)clienteHipMap.get("apellido2"));
				form.setNombreCliente(cadena.toString());
				this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
			}
		}		
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupOCRCliente = false;
		this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_OCRCLIENTE)){ 
			this.mostrarPopupOCRCliente = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACAutorizacionFacturacionElectronicaForm form = new ReporteSACAutorizacionFacturacionElectronicaForm();
		return form;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteSACAutorizacionFacturacionElectronicaXLS";
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
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteSACAutorizacionFacturacionElectronicaForm form = (ReporteSACAutorizacionFacturacionElectronicaForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
  		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
  		
  		log.debug(this.getmPantallaPrincipalBean().getLocale());
  		
  		Idioma idioma = idiomaService.getIdiomaByCodigoISO(this.getmPantallaPrincipalBean().getLocale());
        
        Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
        
        String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		//form.setOidIdioma(oidIdiomaIso);
		
		ClienteUAGenerarService clienteService2 = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		Integer limiteClienes = clienteService2.getTamanhoNumeroCliente(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		siccRegionList = ajaxService.getRegionesByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		
		form.setFechaDesdeDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, new Date())));
		form.setFechaHastaDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, new Date())));
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSACAutorizacionFacturacionElectronicaForm form = (ReporteSACAutorizacionFacturacionElectronicaForm) this.formReporte;
		ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService)getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");		
		
		String condicion = "";
		
		if(StringUtils.isEmpty(form.getCodigoOrigen()))
			params.put("codigoOrigen", null);
		else
			params.put("codigoOrigen", form.getCodigoOrigen());
		
		if(StringUtils.isNotBlank(form.getCodigoClienteBuscar()))
			condicion += " AND mae_clien.cod_clie = '" + form.getCodigoClienteBuscar()+"' ";
							
		condicion += obtieneCondicion(form.getCodigoRegion(), "zon_regio.cod_regi", "'");
		condicion += obtieneCondicion(form.getCodigoZona(), "zon_zona.cod_zona", "'");
		
		if(form.getFechaDesdeDate() != null){
			form.setFechaDesde(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaDesdeDate()));
		}
		
		if(form.getFechaHastaDate() != null){
			form.setFechaHasta(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaHastaDate()));
		}
		
		params.put("fechaDesde", form.getFechaDesde());
		params.put("fechaHasta", form.getFechaHasta());
		
		params.put("condicion", condicion);
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
		ReporteSACAutorizacionFacturacionElectronicaForm form = (ReporteSACAutorizacionFacturacionElectronicaForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valores, Constants.OPCION_TODOS));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		ReporteSACAutorizacionFacturacionElectronicaForm form = (ReporteSACAutorizacionFacturacionElectronicaForm) this.formReporte;
		if(form.getFechaDesdeDate()!=null && form.getFechaHastaDate()!=null){
			if(form.getFechaDesdeDate().compareTo(form.getFechaHastaDate())>0){
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
	 * Checks if is mostrar popup ocr cliente.
	 *
	 * @return true, if is mostrar popup ocr cliente
	 */
	public boolean isMostrarPopupOCRCliente() {
		return mostrarPopupOCRCliente;
	}

	/**
	 * Sets the mostrar popup ocr cliente.
	 *
	 * @param mostrarPopupOCRCliente the new mostrar popup ocr cliente
	 */
	public void setMostrarPopupOCRCliente(boolean mostrarPopupOCRCliente) {
		this.mostrarPopupOCRCliente = mostrarPopupOCRCliente;
	}

	/**
	 * Gets the busqueda hip cliente popup search action.
	 *
	 * @return the busqueda hip cliente popup search action
	 */
	public BusquedaHIPClientePOPUPSearchAction getBusquedaHIPClientePOPUPSearchAction() {
		return busquedaHIPClientePOPUPSearchAction;
	}

	/**
	 * Sets the busqueda hip cliente popup search action.
	 *
	 * @param busquedaHIPClientePOPUPSearchAction the new busqueda hip cliente popup search action
	 */
	public void setBusquedaHIPClientePOPUPSearchAction(
			BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction) {
		this.busquedaHIPClientePOPUPSearchAction = busquedaHIPClientePOPUPSearchAction;
	}		
}

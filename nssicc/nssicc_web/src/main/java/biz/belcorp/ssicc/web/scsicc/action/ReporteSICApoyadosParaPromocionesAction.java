package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICApoyadosParaPromocionesForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;

/**
 * The Class ReporteSICApoyadosParaPromocionesAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 15/07/2014
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSICApoyadosParaPromocionesAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sufijo reporte. */
	private String sufijoReporte;
	
	/** The ssicc catalogo list. */
	private List ssiccCatalogoList = new ArrayList();
	
	private boolean mostrarPopupProducto;
	
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	
	@ManagedProperty(value="#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_SACPRODUCTO)) {
			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map)this.busquedaProductoSearchAction.getBeanRegistroSeleccionado(); 
				ReporteSICApoyadosParaPromocionesForm f = (ReporteSICApoyadosParaPromocionesForm)this.formReporte;
				f.setCodigoSap((String)clienteHipMap.get("codigoSap"));
				f.setDescripcionCorta((String)clienteHipMap.get("descripcionCorta"));
				this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_SACPRODUCTO)){ 
			this.mostrarPopupProducto = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICApoyadosParaPromocionesForm form = new ReporteSICApoyadosParaPromocionesForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSICApoyadosParaPromocionesForm form = (ReporteSICApoyadosParaPromocionesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteSICApoyadosParaPromocionesXLS";
		}else{
			return "reporteMaestroVertical";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICApoyadosParaPromocionesForm form = (ReporteSICApoyadosParaPromocionesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(form.getFormatoExportacion())){
			return "reporteSICApoyadosParaPromocionesPDF";
		}else{
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSICApoyadosParaPromocionesForm reporteSICForm = (ReporteSICApoyadosParaPromocionesForm)this.formReporte;
		log.debug(reporteSICForm.getFormatoExportacion());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = params;
		
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		params.put("NroReporte", "");
		params.put("oidPeriodo", oidPeriodo);
		params.put("titulo", this.getResourceMessage("reporteSICApoyadosParaPromocionesForm.titulo"));
				
		return params;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteSICFacturacionAction - setViewAtributes");
		}
		this.mostrarReporteXLS = true;
		ReporteSICApoyadosParaPromocionesForm reporteSICForm = (ReporteSICApoyadosParaPromocionesForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.ssiccCatalogoList = reporteService.getListaGenerico("getCatalogoProductos", null);		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		reporteSICForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
	}
	
	/**
	 * @return
	 */
	public String getSufijoReporte() {
		return sufijoReporte;
	}

	/**
	 * @param sufijoReporte
	 */
	public void setSufijoReporte(String sufijoReporte) {
		this.sufijoReporte = sufijoReporte;
	}

	/**
	 * @return
	 */
	public List getSsiccCatalogoList() {
		return ssiccCatalogoList;
	}

	/**
	 * @param ssiccCatalogoList
	 */
	public void setSsiccCatalogoList(List ssiccCatalogoList) {
		this.ssiccCatalogoList = ssiccCatalogoList;
	}

	/**
	 * @return
	 */
	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	/**
	 * @param busquedaProductoSearchAction
	 */
	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	/**
	 * @return
	 */
	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	/**
	 * @param mostrarPopupProducto
	 */
	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}	
}
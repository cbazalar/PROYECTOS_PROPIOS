package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;

/**
 * The Class ReporteSICDetalleUnidadesAtendidasFaltanteResumenAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICDetalleUnidadesAtendidasFaltanteResumenAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sufijo reporte. */
	private String sufijoReporte;
	
	/** The ssicc catalogo list. */
	private List ssiccCatalogoList = new ArrayList();
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	private boolean mostrarPopupProducto;
	
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	
	@ManagedProperty(value="#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	
	
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
				ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm f = (ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm)this.formReporte;
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
		ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm form = new ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm form = (ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteSICDetUnidAtenFaltResXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm form = (ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(form.getFormatoExportacion())){
			return "reporteSICDetUnidAtenFaltResPDF";
		}else{
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {;
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm reporteSICForm = (ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm)this.formReporte;
		log.debug(reporteSICForm.getFormatoExportacion());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = params;
		params.put("tipoSolicitud", "SOC");
		params.put("NroReporte", "");
		params.put("fechaFacturacion", DateUtil.convertDateToString("dd/MM/yyyy", reporteSICForm.getFechaFacturacion()));
		params.put("titulo", this.getResourceMessage("reporteSICDetalleUnidadesAtendidasFaltanteResumenForm.titulo"));
				
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
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ssiccCatalogoList = reporteService.getListaGenerico("getCatalogoProductos", null);
	}
	
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public String getSufijoReporte() {
		return sufijoReporte;
	}

	public void setSufijoReporte(String sufijoReporte) {
		this.sufijoReporte = sufijoReporte;
	}

	public List getSsiccCatalogoList() {
		return ssiccCatalogoList;
	}

	public void setSsiccCatalogoList(List ssiccCatalogoList) {
		this.ssiccCatalogoList = ssiccCatalogoList;
	}

	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}
	
	
}

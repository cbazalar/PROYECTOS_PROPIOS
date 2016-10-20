package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;

/**
 * The Class ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 29/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
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
				ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm f = (ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm)this.formReporte;
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
		ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm form = new ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm form = (ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteSICPediAfecDetalleFNAXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm form = (ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(form.getFormatoExportacion()) || "VPDF".equals(form.getFormatoExportacion())){
			return "reporteSICPediAfecDetalleFNAPDF";
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
		ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm reporteSICForm = (ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm)this.formReporte;
		log.debug(reporteSICForm.getFormatoExportacion());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = params;
		params.put("codigoPais", reporteSICForm.getCodigoPais());
		params.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		params.put("fechaFacturacion", DateUtil.convertDateToString("dd/MM/yyyy",reporteSICForm.getFechaFacturacion()));
		params.put("beforeExecuteReporte",true);
		
		//Codigo SAP
		if (!StringUtils.equals(reporteSICForm.getCodigoSap(), ""))
			params.put("codigoSAP", reporteSICForm.getCodigoSap());
		
		//Codigo de venta
		if (!StringUtils.equals(reporteSICForm.getCodigoVenta(), ""))
			params.put("codigoVenta", reporteSICForm.getCodigoVenta());
		
		//Codigo Region	
		String condicionRegion = "";
		if (!(reporteSICForm.getCodigoRegion() == null || 
	        StringUtils.equals(StringUtils.substring(reporteSICForm.getDescripcionCorta(),0, 5),Constants.OPCION_TODOS))){
			condicionRegion = this.obtieneCondicion(reporteSICForm.getCodigoRegion(), "STDF.COD_REGI", "'");		
		}
		params.put("condicion", condicionRegion);
		
		params.put("NroReporte", "");
		params.put("titulo", getResourceMessage("reporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm.titulo"));
				
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
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais",this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		List listaRegiones = reporteService.getListaGenerico("getCatalogoProductos", criteriaOperacion);
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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteSICPedidosAfectadosDetFaltanteNoAnunciadoService";
	}
	
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
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

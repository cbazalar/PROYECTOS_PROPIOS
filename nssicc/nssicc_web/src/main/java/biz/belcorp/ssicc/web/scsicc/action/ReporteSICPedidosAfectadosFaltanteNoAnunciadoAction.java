package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSICPedidosAfectadosFaltanteNoAnunciadoAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 15/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICPedidosAfectadosFaltanteNoAnunciadoAction extends BaseReporteAbstractAction {

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
				ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm f = (ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm)this.formReporte;
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
		ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm form = new ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm form = (ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteSICPedidosAfectadosFaltanteNoAnunciadoXLS";
		}else{
			return "reporteMaestroVertical";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm form = (ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("PDF".equals(form.getFormatoExportacion())){
			return "reporteSICPedidosAfectadosFaltanteNoAnunciadoPDF";
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
		
		ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm reporteSICForm = (ReporteSICPedidosAfectadosFaltanteNoAnunciadoForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		log.debug(reporteSICForm.getFormatoExportacion());
		
		Map criteria = params;
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		String condicionRegion = obtieneCondicion(reporteSICForm.getCodigoRegion(), "g.COD_REGI", "'");
		
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		params.put("NroReporte", "");
		params.put("oidPeriodo", oidPeriodo);
		params.put("condicion", condicionRegion);
		
		if(reporteSICForm.getFechaFacturacionDate() != null){
			reporteSICForm.setFechaFacturacion(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSICForm.getFechaFacturacionDate()));
		}
		
		params.put("fechaFacturacion", reporteSICForm.getFechaFacturacion());
		params.put("titulo", this.getResourceMessage("reporteSICPedidosAfectadosFaltanteNoAnunciadoForm.titulo"));
				
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
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size() + 1];		
		this.getSiccRegionList()[0] = new LabelValue(Constants.OPCION_TODOS, "");
		
		int i = 1;
		for(Object object : listaRegiones){
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base)object).getDescripcion());
			labelValue.setValue(((Base)object).getCodigo().toString());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}
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

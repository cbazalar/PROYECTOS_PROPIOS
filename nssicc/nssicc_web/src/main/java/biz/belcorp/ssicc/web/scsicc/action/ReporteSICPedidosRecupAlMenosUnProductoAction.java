package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICPedidosRecupAlMenosUnProductoForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;


/**
 * The Class ReporteSICPedidosSustAlmenosUnProductoAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 15/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICPedidosRecupAlMenosUnProductoAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sufijo reporte. */
	private String sufijoReporte;
	
	/** The ssicc catalogo list. */
	private List ssiccCatalogoList = new ArrayList();
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	
	private boolean mostrarPopupProducto;
	private boolean mostrarPopupProductoUno;
	private boolean mostrarPopupProductoDos;
	
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	private static final String POPUP_SACPROD_REEMP = "SACPRODREEMPLAZO";
	
	private String parametroAccion = "";
	
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
		accion = this.getParametroAccion();
		this.busquedaProductoSearchAction.verificarRegistro(event);
		if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {
			Map clienteHipMap = (Map)this.busquedaProductoSearchAction.getBeanRegistroSeleccionado(); 
			ReporteSICPedidosRecupAlMenosUnProductoForm f = (ReporteSICPedidosRecupAlMenosUnProductoForm)this.formReporte;			
			if (accion.equals(this.POPUP_SACPRODUCTO)) {
				f.setCodigoSap((String)clienteHipMap.get("codigoSap"));
				f.setDescVentaRecuperacion((String)clienteHipMap.get("descripcionCorta"));
			}
			if(accion.equals(this.POPUP_SACPROD_REEMP)){
				f.setCodigoSapReemplazo((String)clienteHipMap.get("codigoSap"));
				f.setDescVentaRecuperado((String)clienteHipMap.get("descripcionCorta"));
			}
			this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
		}	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupProductoUno = false;
		this.mostrarPopupProductoDos = false;
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		
		if(accion.equals(this.POPUP_SACPRODUCTO)){
			this.mostrarPopupProductoUno = true;
			this.mostrarPopupProductoDos = false;
		}
		if(accion.equals(this.POPUP_SACPROD_REEMP)){
			this.mostrarPopupProductoUno = false;
			this.mostrarPopupProductoDos = true;
		}
		//this.mostrarPopupProducto = true;		
		parametroAccion = accion;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICPedidosRecupAlMenosUnProductoForm form = new ReporteSICPedidosRecupAlMenosUnProductoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSICPedidosRecupAlMenosUnProductoForm form = (ReporteSICPedidosRecupAlMenosUnProductoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteSICPedidosRecupAlMenosUnProductoXLS";
		} else {
			return "reporteMaestroVertical";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSICPedidosRecupAlMenosUnProductoPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {;
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSICPedidosRecupAlMenosUnProductoForm f = (ReporteSICPedidosRecupAlMenosUnProductoForm)this.formReporte;
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		Map criteria = params;
		params.put("NroReporte", "");
		params.put("codigoPais", f.getCodigoPais());	
		params.put("titulo", this.getReportResourceMessage("reporteSICPedidosRecupAlMenosUnProductoForm.titulo"));
		
		criteria.put("codigoPeriodo", f.getPeriodo());
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		
		params.put("oidPeriodo", oidPeriodo);
		
		if(f.getFechaFacturacionDate() != null){
			f.setFechaFacturacion(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, f.getFechaFacturacionDate()));
		}
		
		params.put("fechaFacturacion",f.getFechaFacturacion());
		params.put("cuvRecuperacion",f.getCuvRecuperacion());
		params.put("productoRecuperacion",f.getCodigoSap());
		params.put("cuvRecuperado",f.getCuvRecuperado());
		params.put("productoRecuperado",f.getCodigoSapReemplazo());
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "j.COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		
		String condicionZona = this.obtieneCondicion(f.getZonaList(), "i.COD_ZONA", "'");
		params.put("condicionZona", condicionZona);
				
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
		
		InterfazSiCCService service = (InterfazSiCCService) this.getBean("sisicc.interfazSiCCService");
		ReporteService reportService = (ReporteService) this.getBean("scsicc.reporteService");
		ReporteSICPedidosRecupAlMenosUnProductoForm f = ( ReporteSICPedidosRecupAlMenosUnProductoForm) formReporte ;
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		Pais pais =  this.getmPantallaPrincipalBean().getCurrentCountry();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());
		
		//Carga la lista de Regiones
		List listaRegiones = reportService.getListaGenerico("getRegionesByPais",criteria);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		
		int i = 0;
		for(Object object : listaRegiones){
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base)object).getDescripcion());
			labelValue.setValue(((Base)object).getCodigo().toString());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}
		
		f.setPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
	}
	
	/**
	 * Load zonas.
	 *
	 * @param val the val
	 */
	public void loadZonas(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadZonas");
		}
		String[] valor = (String[]) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ReporteSICPedidosRecupAlMenosUnProductoForm reporteSICForm = (ReporteSICPedidosRecupAlMenosUnProductoForm) this.formReporte;
		
		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), 
				Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, 
				valor, 
				Constants.FORMATEAR_TODOS);
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

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public boolean isMostrarPopupProductoUno() {
		return mostrarPopupProductoUno;
	}

	public void setMostrarPopupProductoUno(boolean mostrarPopupProductoUno) {
		this.mostrarPopupProductoUno = mostrarPopupProductoUno;
	}

	public boolean isMostrarPopupProductoDos() {
		return mostrarPopupProductoDos;
	}

	public void setMostrarPopupProductoDos(boolean mostrarPopupProductoDos) {
		this.mostrarPopupProductoDos = mostrarPopupProductoDos;
	}

	public String getParametroAccion() {
		return parametroAccion;
	}

	public void setParametroAccion(String parametroAccion) {
		this.parametroAccion = parametroAccion;
	}

	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}
	
	
}

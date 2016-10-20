package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECLiquidacionBoletaRecojoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECBloqueoCDRService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ProcesoRECBloqueoCDRForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoRECBloqueoCDRAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -8937239830604605700L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRegionList;
	private List recEstadosList;
	private List recBloqueosCdrList;
	private Integer longitudCampoClientes;
	private LabelValue[] siccZonaList={};
	private Object[] objEliminarMultiple;
	private DataTableModel dmBloqueoCDR;
	
	private boolean mostrarPopupCliente = false;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoRECBloqueoCDRForm procesoForm =new ProcesoRECBloqueoCDRForm();		
		return procesoForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {		
		ProcesoRECBloqueoCDRService service = (ProcesoRECBloqueoCDRService)getBean("spusicc.procesoRECBloqueoCDRService");		
		service.executeProcesoRECBloqueoCDR(params);
		this.recBloqueosCdrList = this.setFindAttributes();
		this.dmBloqueoCDR = new DataTableModel(this.recBloqueosCdrList);
		return params;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}		
		ProcesoRECBloqueoCDRForm f = (ProcesoRECBloqueoCDRForm)this.formProceso;		
		Map criteria = new HashMap();		
		criteria.put("codigoPeriodo", f.getPeriodo());
		criteria.put("codigoConsultora", f.getCodigoConsultora());
		String [] regiones=f.getCodigoRegion();
		if(f.getCodigoRegion() != null && regiones.length>0){
			if(!regiones[0].equals("")){
				criteria.put("regionList",Arrays.asList(f.getCodigoRegion()));				
			}
		}
		if(f.getCodigoZona() != null && f.getCodigoZona().length>0){
			if(!f.getCodigoZona()[0].equals("")){
				criteria.put("zonaList",Arrays.asList(f.getCodigoZona()));
			}
		}
		
		List lista = new ArrayList();
		ProcesoRECBloqueoCDRService service = (ProcesoRECBloqueoCDRService)getBean("spusicc.procesoRECBloqueoCDRService");
		lista = service.getBloqueosCDRByCriteria(criteria);
		this.recBloqueosCdrList=lista;
		this.dmBloqueoCDR = new DataTableModel(this.recBloqueosCdrList);
		
		//muestra panel adicional
		this.mostrarPanelAdicionalProceso = true;
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		if(this.objEliminarMultiple.length == 0){
			return "Debe seleccionar al menos un registro";
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");	
		this.mostrarBotonBuscar=true;
		this.mostrarBotonExecute = false;
		this.mostrarListaBusqueda = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		MantenimientoRECLiquidacionBoletaRecojoService serviceRec = (MantenimientoRECLiquidacionBoletaRecojoService)getBean("spusicc.mantenimientoRECLiquidacionBoletaRecojoService");		
		this.siccMarcaList=service.getMarcas();
		this.siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
	
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.recEstadosList=serviceRec.getEstados();
		this.longitudCampoClientes= clienteService.getTamanhoNumeroCliente(pais.getCodigo());	
		ProcesoRECBloqueoCDRForm f = (ProcesoRECBloqueoCDRForm)this.formProceso;
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoPais(pais.getCodigo());		
	}	
	
	/**
	 * Carga Zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ProcesoRECBloqueoCDRForm f = (ProcesoRECBloqueoCDRForm)this.formProceso;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
						f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regiones,
						Constants.FORMATO_TOTAL));			
				f.setCodigoZona(null);
			} else {
				this.siccZonaList = null;			
				f.setCodigoZona(null);
			}	
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	//POPUP
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		
		if (accion.equals(this.POPUP_CLIENTE)){ 
			this.mostrarPopupCliente = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}		
		
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);			
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado();					
				ProcesoRECBloqueoCDRForm f = (ProcesoRECBloqueoCDRForm) this.formProceso;	
				String codigo=cliente.getCodigo();
				f.setCodigoConsultora(codigo);					
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formProceso=  f;				
			}
		}	
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	/**
	 * Ejecuta Desbloquear
	 * @param event
	 * @throws Exception
	 */
	public void desbloquear(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'desbloquear' method");
		}
		if(this.objEliminarMultiple.length == 0){
			 this.addError("ERROR: ", this.getResourceMessage("errors.select.item"));
			 return;
		}
		try {
			ProcesoRECBloqueoCDRService service = (ProcesoRECBloqueoCDRService)getBean("spusicc.procesoRECBloqueoCDRService");
			String[] listaIDs = new String[this.objEliminarMultiple.length];

			for (int i = 0; i < this.objEliminarMultiple.length; i++) {
				Map sistemabusqueda = (Map) this.objEliminarMultiple[i];
				String codigo=sistemabusqueda.get("oidBloqueo").toString();
				listaIDs[i] = codigo;
			}
			
			Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();		
			service.updateDesbloqueoCDR(listaIDs, usuario.getLogin());
			this.recBloqueosCdrList = this.setFindAttributes();
			this.dmBloqueoCDR = new DataTableModel(this.recBloqueosCdrList);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setValidarProceso()
	 */
	@Override
	public String setValidarProceso(){
		ProcesoRECBloqueoCDRForm f = (ProcesoRECBloqueoCDRForm)this.formProceso;	
		if(StringUtils.isBlank(f.getCodigoConsultora()))			
			return this.getResourceMessage("errors.procesoRECBloqueoCDR.codigoConsultora");
		return null;
	}
	

	/**
	 * @return
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public List getRecEstadosList() {
		return recEstadosList;
	}

	/**
	 * @param recEstadosList
	 */
	public void setRecEstadosList(List recEstadosList) {
		this.recEstadosList = recEstadosList;
	}

	/**
	 * @return
	 */
	public List getRecBloqueosCdrList() {
		return recBloqueosCdrList;
	}

	/**
	 * @param recBloqueosCdrList
	 */
	public void setRecBloqueosCdrList(List recBloqueosCdrList) {
		this.recBloqueosCdrList = recBloqueosCdrList;
	}

	/**
	 * @return
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return
	 */
	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	/**
	 * @param mostrarPopupCliente
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	/**
	 * @return
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @return
	 */
	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}

	/**
	 * @return the objEliminarMultiple
	 */
	public Object[] getObjEliminarMultiple() {
		return objEliminarMultiple;
	}

	/**
	 * @param objEliminarMultiple the objEliminarMultiple to set
	 */
	public void setObjEliminarMultiple(Object[] objEliminarMultiple) {
		this.objEliminarMultiple = objEliminarMultiple;
	}

	/**
	 * @return the dmBloqueoCDR
	 */
	public DataTableModel getDmBloqueoCDR() {
		return dmBloqueoCDR;
	}

	/**
	 * @param dmBloqueoCDR the dmBloqueoCDR to set
	 */
	public void setDmBloqueoCDR(DataTableModel dmBloqueoCDR) {
		this.dmBloqueoCDR = dmBloqueoCDR;
	}
}
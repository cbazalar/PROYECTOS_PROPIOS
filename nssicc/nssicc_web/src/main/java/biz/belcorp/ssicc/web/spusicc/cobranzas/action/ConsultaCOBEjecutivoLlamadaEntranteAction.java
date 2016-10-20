package biz.belcorp.ssicc.web.spusicc.cobranzas.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBEjecutivoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.ConsultaHIPDatosClienteAction;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPDatosClienteForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ConsultaCOBEjecutivoLlamadaEntranteForm;

@SessionScoped
@ManagedBean
public class ConsultaCOBEjecutivoLlamadaEntranteAction extends BaseConsultaAbstractAction {

	private static final long serialVersionUID = -8271679937013922665L;

	private Integer longitudCampoClientes;
	private List siccTelefonosList;
	private List bitacoraLlamadasList;
	private List referenciasList;
	private List referenciasDeudoraList;
	private List avalesDeudoraList;
	private LabelValue[] siccAccionesCobranzaList;
	private List listaTelefonos;
	private List listaConsultorasLlamadaEntrante;
	private List detalleConsultorasList;
	private List detalleMovimientoList;
	private boolean seleccionable;
	private List historialGestionesList;
	
	@ManagedProperty(value="#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;
	private static final String POPUP_HIPCLIENTE = "HIPCLIENTE";
	private boolean mostrarPopupHIPCliente;
	
	@ManagedProperty(value = "#{consultaHIPDatosClienteAction}")
	protected ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private String paginaPadre;
	
	private boolean mostrarPopupCuentaCorriente;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_HIPCLIENTE)){ 
			this.mostrarPopupHIPCliente = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		this.mostrarProcesoBatch = true;
		this.mostrarPopupHIPCliente = false;
		if (accion.equals(this.POPUP_HIPCLIENTE)) {
			this.busquedaHIPClientePOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClientePOPUPSearchAction.isSeleccionoRegistro()) {	
				
				Map clienteHipMap = (Map)this.busquedaHIPClientePOPUPSearchAction.getBeanRegistroSeleccionado(); 
				ConsultaCOBEjecutivoLlamadaEntranteForm busquedaForm = (ConsultaCOBEjecutivoLlamadaEntranteForm)this.formBusqueda;				
				busquedaForm.setCodigoConsultoraSearch((String)clienteHipMap.get("codigoCliente"));
				busquedaForm.setNumeroDocumentoSearch((String)clienteHipMap.get("numeroDocumento"));
			
				this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formBusqueda =  busquedaForm;
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
		this.mostrarProcesoBatch = true;
		this.mostrarPopupHIPCliente = false;
		this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return new ConsultaCOBEjecutivoLlamadaEntranteForm();
	}

	
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering ConsultaCOBEjecutivoLlamadaEntrante 'view' method");
		}

		ConsultaCOBEjecutivoLlamadaEntranteForm f = (ConsultaCOBEjecutivoLlamadaEntranteForm) this.formBusqueda;
		// Carga de los combos
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
						
		//Carga combo de Sociedades
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		ClienteUAGenerarService svc = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		this.longitudCampoClientes = svc.getTamanhoNumeroCliente(f.getCodigoPais());
		
		log.debug("svc.getTamanhoNumeroCliente(pais.getCodigo(): " + this.longitudCampoClientes);
		
		this.setMostrarListaBusqueda(false);
		this.setMostrarBotonBuscar(false);
	}
	
	public void limpiar(ConsultaCOBEjecutivoLlamadaEntranteForm f){
		f.setCodigoCartera("");		
		f.setCodigoEtapaConsultora("");		
		f.setCodigoConsultora("");		
		f.setDigitoControl("");		
		f.setNombreConsultora("");		
		f.setNumeroDocumentoIdentidad("");		
		f.setCampanhaAsignacion("");		
		f.setFechaAsignacion("");		
		f.setRegionTab("");	
		f.setZonaTab("");		
		f.setSeccionTab("");		
		f.setTerritorioTab("");		
	    f.setDepartamento("");	    
	    f.setProvincia("");	    
	    f.setDistrito("");	   
		f.setDireccionTab("");		
		f.setReferenciaTab("");		
		f.setFechaIngresoTab("");		
		f.setFechaNacimientoTab("");		
		f.setEdadTab("");		
		f.setOcupacionTab("");		
		f.setFechaCierre("");		
		f.setDeudaCartera("");	
		f.setDeudaTotal("");		
		f.setTiempoMora("");		
		f.setCampanaIngresoTab("");		
	    f.setUltimoPedidoTab("");	    
		f.setStatusTab("");		
		f.setCodigoRegion("");
		f.setTelefonoFijo("");
		f.setTelefonoMovil("");
		f.setTelefonoTrabajo("");
		
		f.setCodigoConsultoraSearch("");
		f.setNumeroDocumentoSearch("");
		f.setNuevoTelefonoFijoTab("");
		f.setNuevoTelefonoMovilTab("");
		f.setNuevoTelefonoTrabajoTab("");
		
		this.listaTelefonos = null;								
		this.bitacoraLlamadasList = null;
		this.referenciasList = null;
		this.referenciasDeudoraList = null;
		this.avalesDeudoraList = null;
	}
	
	/**
	 * Setea el Form
	 * @param f
	 * @param map
	 * @param listaTelefonos
	 */
	private void setForm(ConsultaCOBEjecutivoLlamadaEntranteForm f, Map map, List listaTelefonos) {
		
		log.debug("___SETEANDO EL FORM");
		f.setCodigoCartera(map.get("codigoCartera").toString());		
		f.setCodigoEtapaConsultora(map.get("codigoEtapaConsultora").toString());		
		f.setCodigoConsultora(map.get("codigoCliente").toString());		
		f.setDigitoControl(map.get("digitoControl").toString());		
		f.setNombreConsultora(map.get("nombreCliente").toString());		
		f.setNumeroDocumentoIdentidad(map.get("numeroDocumentoIdentidad").toString());		
		f.setCampanhaAsignacion(map.get("campanhaAsignacion").toString());		
		f.setFechaAsignacion(map.get("fechaAsignacion").toString());		
		f.setRegionTab(map.get("region").toString());	
		f.setZonaTab(map.get("zona").toString());		
		f.setSeccionTab(map.get("seccion").toString());		
		f.setTerritorioTab(map.get("territorio").toString());		
	    f.setDepartamento(map.get("departamento").toString());	    
	    f.setProvincia(map.get("provincia").toString());	    
	    f.setDistrito(map.get("distrito").toString());	   
		f.setDireccionTab(map.get("direccion").toString());		
		f.setReferenciaTab(map.get("referencia").toString());		
		f.setFechaIngresoTab(map.get("fechaIngreso").toString());		
		f.setFechaNacimientoTab(map.get("fechaNacimiento").toString());		
		f.setEdadTab(map.get("edad").toString());		
		f.setOcupacionTab(map.get("ocupacion").toString());		
		f.setFechaCierre(map.get("fechaCierre").toString());		
		f.setDeudaCartera(map.get("deudaCartera").toString());	
		f.setDeudaTotal(map.get("deudaTotal").toString());		
		f.setTiempoMora(map.get("mora").toString());		
		f.setCampanaIngresoTab(map.get("campanaIngreso").toString());		
	    f.setUltimoPedidoTab(map.get("ultimoPedido").toString());	    
		f.setStatusTab(map.get("status").toString());		
		f.setCodigoRegion(map.get("region").toString());

		try {
			f.setTelefonoFijo(map.get("telefonoFijo").toString());
		} catch (Exception e) {
			f.setTelefonoFijo("");
		}
		try {
			f.setTelefonoMovil(map.get("telefonoMovil").toString());
		} catch (Exception e) {
			f.setTelefonoMovil("");
		}			
		try {
			f.setTelefonoTrabajo(map.get("telefonoTrabajo").toString());
		} catch (Exception e) {
			f.setTelefonoTrabajo("");
		}
		
		if(!f.getTelefonoFijo().equals(""))listaTelefonos.add(f.getTelefonoFijo());
		if(!f.getTelefonoMovil().equals(""))listaTelefonos.add(f.getTelefonoMovil());
		if(!f.getTelefonoTrabajo().equals(""))listaTelefonos.add(f.getTelefonoTrabajo());
	}
	
	/**
	 * Obtiene los criterios de busqueda
	 * @param f
	 * @return
	 */  	 
	private Map getCriteriaSearch(ConsultaCOBEjecutivoLlamadaEntranteForm f) {
		Map criteria = new HashMap();
			
		criteria.put("codigoConsultoraSearch",f.getCodigoConsultoraSearch());
		criteria.put("numeroDocumentoSearch",f.getNumeroDocumentoSearch());		
 
		return criteria;
	}  
	
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}
	
	public void search(ActionEvent event){
		if (log.isWarnEnabled()) {
			log.warn("Entering 'search' method");
		}
		
		try {		
			ConsultaCOBEjecutivoService service = (ConsultaCOBEjecutivoService)getBean("spusicc.consultaCOBEjecutivoService");
			ConsultaCOBEjecutivoLlamadaEntranteForm f = (ConsultaCOBEjecutivoLlamadaEntranteForm)this.formBusqueda;
			List listaConsultorasLlamadaEntrante = new ArrayList();

			listaConsultorasLlamadaEntrante = service.getCarteraLlamadaEntranteByFilter(getCriteriaSearch(f));
					
			Map map = new HashMap();
			List listaTelefonos = new ArrayList();
			map=(HashMap)listaConsultorasLlamadaEntrante.get(0);									
			
			setForm(f, map, listaTelefonos);
			
			this.listaTelefonos = listaTelefonos;								
			this.bitacoraLlamadasList = service.getListaGestionesCartera(getCriteriaSave(f));
			this.referenciasList = service.getReferencias(getCriteriaSave(f));
			this.referenciasDeudoraList = service.getReferenciasDeudora(getCriteriaSave(f));
			this.avalesDeudoraList = service.getAvalesDeudora(getCriteriaSave(f));
			
		}
		catch (Exception e) {
			log.debug("___NO HAY RESULTADOS");
			
			this.addError("Error: ", this.getResourceMessage("cobranzas.cartera.notfound"));
			
		}
		
		return;
	}

	 
	/**
	 * Obtiene los criterios para guardar los datos
	 * @param f
	 * @return
	 */
	private Map getCriteriaSave(ConsultaCOBEjecutivoLlamadaEntranteForm f) {
		Map criteria = new HashMap();
		
		criteria.put("codigoCartera",f.getCodigoCartera());	    
		criteria.put("codigoConsultora",f.getCodigoConsultora());		
		criteria.put("codigoEtapaDeuda",f.getCodigoEtapaConsultora());
		criteria.put("codigoCobrador",f.getCodigoCobrador());
		criteria.put("tipoAccion",f.getTipoAccion());
		criteria.put("accionCobranza",f.getAccionCobranza());
		criteria.put("observaciones",f.getObservaciones());
		criteria.put("fechaPago",DateUtil.getDate(f.getFechaPago()));
		criteria.put("importePago",f.getImportePago());
				
		log.debug(f.getCodigoCartera());
		
		return criteria;
	} 		
	
	public void loadAcciones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadAcciones");
		}

		ConsultaCOBEjecutivoLlamadaEntranteForm f = (ConsultaCOBEjecutivoLlamadaEntranteForm)this.formBusqueda;
		String accion = (String) val.getNewValue();
		if (StringUtils.isNotBlank(accion) && StringUtils.isNotBlank(f.getCodigoEtapaConsultora())) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
		      this.siccAccionesCobranzaList = ajax.getAccionesCobranzaByEtapaTipoAccion(					
						  f.getCodigoEtapaConsultora(),
						  accion);

		} else {
			this.siccAccionesCobranzaList  = null;
		}
	}

	
	/**
	 * Obtiene los criterios necesarios para la actualizacion de Datos
	 * @param f
	 * @return
	 */
	private Map getCriteriaActualizar(ConsultaCOBEjecutivoLlamadaEntranteForm f) {
		Map criteria = new HashMap();
									
		criteria.put("codigoCartera",f.getCodigoCartera());	    	
		criteria.put("codigoConsultora",f.getCodigoConsultora());		
		criteria.put("codigoCobrador",f.getCodigoCobrador());
		criteria.put("codigoEtapaDeuda",f.getCodigoEtapaConsultora());
		criteria.put("nuevoTelefonoFijoTab",f.getNuevoTelefonoFijoTab());
		criteria.put("nuevoTelefonoTrabajoTab",f.getNuevoTelefonoTrabajoTab());
		criteria.put("nuevoTelefonoMovilTab",f.getNuevoTelefonoMovilTab());				
		
		log.debug(f.getCodigoCartera());
													
		return criteria;
	} 
	
	/**
	 * Metodo que permite visualizar el Detalle de la Cuenta Corriente
	 */
	 
	public void showPopUp(ActionEvent event) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'showPopUp' method");
		}
		
		try {						
			this.mostrarPopupCuentaCorriente = true;
			ConsultaCOBEjecutivoLlamadaEntranteForm f = (ConsultaCOBEjecutivoLlamadaEntranteForm)this.formBusqueda;
			ConsultaCOBEjecutivoService service = (ConsultaCOBEjecutivoService)getBean("spusicc.consultaCOBEjecutivoService");
			Map criteria = new HashMap();
			
			criteria.put("codigoConsultora",f.getCodigoConsultora());
			
			this.detalleConsultorasList = service.getDetalleConsultora(criteria);
			this.listaBusqueda = null;
			
		} catch (Exception e) {
			this.mostrarPopupCuentaCorriente = false;
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * Metodo que permite visualizar el Detalle de la Cuenta Corriente
	 */
	public void showDetalleMovimiento(ActionEvent event) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'showPopUpDetalleMovimiento' method");
		}
		
		ConsultaCOBEjecutivoService service = (ConsultaCOBEjecutivoService)getBean("spusicc.consultaCOBEjecutivoService");
		Map criteria = new HashMap();
		//criteria.put("oidConsolidado",request.getParameter("valor"));
		
		this.detalleMovimientoList = service.getDetalleConsultora(criteria);
		this.listaBusqueda = null;
	}
	
	
	/**
	 * Metodo que permite visualizar un historial de gestiones
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showPopupHistorialGestiones(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'showPopupHistorialGestiones' method");
		}
		ConsultaCOBEjecutivoLlamadaEntranteForm f = (ConsultaCOBEjecutivoLlamadaEntranteForm)this.formBusqueda;
		ConsultaCOBGenericoService service = (ConsultaCOBGenericoService)getBean("spusicc.consultaCOBGenericoService");
		Map criteria = new HashMap();
					    
		criteria.put("codigoConsultora",f.getCodigoConsultora());
					
		this.historialGestionesList = service.getHistorialGestionesCobranza(criteria);
		
		this.listaBusqueda = null;
	}
	

		/**
		 * Metodo uqe permite grabar la gestion realizada
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		 public void saveIngresoGestion(ActionEvent event) {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'saveIngresoGestion' method");
			}
			
			ConsultaCOBEjecutivoLlamadaEntranteForm f = (ConsultaCOBEjecutivoLlamadaEntranteForm)this.formBusqueda;
			
			ConsultaCOBEjecutivoService service = (ConsultaCOBEjecutivoService)getBean("spusicc.consultaCOBEjecutivoService");	
			
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
			f.setCodigoCobrador(usuario.getLogin());
			try {	
			service.saveGestionCartera(getCriteriaSave(f));
			
  			 this.bitacoraLlamadasList = service.getListaGestionesCartera(getCriteriaSave(f));
			 this.addInfo("", this.getResourceMessage("llamada.added"));
			 
			 f.setFechaPago(null);
			 f.setImportePago("");
			 f.setObservaciones("");

			} catch (Exception e) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
			}
		}
	 
	public void actualizarTelefono(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("actualizarTelefono");
		}
		ConsultaCOBEjecutivoLlamadaEntranteForm f = (ConsultaCOBEjecutivoLlamadaEntranteForm)this.formBusqueda;
		
		ConsultaCOBEjecutivoService service = (ConsultaCOBEjecutivoService)getBean("spusicc.consultaCOBEjecutivoService");			
		
		try {
			service.actualizarTelefonoDeudora(getCriteriaActualizar(f));
			f.setNuevoTelefono("");
			this.addInfo("", this.getResourceMessage("actualiza.datos.deudora"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	public void linkHiperconsulta(ActionEvent event) {
		try {
			ConsultaCOBEjecutivoLlamadaEntranteForm f = (ConsultaCOBEjecutivoLlamadaEntranteForm)this.formBusqueda;	
			ConsultaHIPDatosClienteForm searchForm = (ConsultaHIPDatosClienteForm)  this.consultaHIPDatosClienteAction.getFormBusqueda();	
			this.consultaHIPDatosClienteAction.setPaginaPadre("consultaCOBEjecutivoLlamadaEntranteForm");
			this.consultaHIPDatosClienteAction.view();
			this.redireccionarPagina("consultaHIPDatosClienteFormCOB");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
	}
	
	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}


	/**
	 * @param longitudCampoClientes the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}


	/**
	 * @return the siccTelefonosList
	 */
	public List getSiccTelefonosList() {
		return siccTelefonosList;
	}


	/**
	 * @param siccTelefonosList the siccTelefonosList to set
	 */
	public void setSiccTelefonosList(List siccTelefonosList) {
		this.siccTelefonosList = siccTelefonosList;
	}


	/**
	 * @return the bitacoraLlamadasList
	 */
	public List getBitacoraLlamadasList() {
		return bitacoraLlamadasList;
	}


	/**
	 * @param bitacoraLlamadasList the bitacoraLlamadasList to set
	 */
	public void setBitacoraLlamadasList(List bitacoraLlamadasList) {
		this.bitacoraLlamadasList = bitacoraLlamadasList;
	}


	/**
	 * @return the referenciasList
	 */
	public List getReferenciasList() {
		return referenciasList;
	}


	/**
	 * @param referenciasList the referenciasList to set
	 */
	public void setReferenciasList(List referenciasList) {
		this.referenciasList = referenciasList;
	}


	/**
	 * @return the referenciasDeudoraList
	 */
	public List getReferenciasDeudoraList() {
		return referenciasDeudoraList;
	}


	/**
	 * @param referenciasDeudoraList the referenciasDeudoraList to set
	 */
	public void setReferenciasDeudoraList(List referenciasDeudoraList) {
		this.referenciasDeudoraList = referenciasDeudoraList;
	}


	/**
	 * @return the avalesDeudoraList
	 */
	public List getAvalesDeudoraList() {
		return avalesDeudoraList;
	}


	/**
	 * @param avalesDeudoraList the avalesDeudoraList to set
	 */
	public void setAvalesDeudoraList(List avalesDeudoraList) {
		this.avalesDeudoraList = avalesDeudoraList;
	}

	/**
	 * @return the siccAccionesCobranzaList
	 */
	public LabelValue[] getSiccAccionesCobranzaList() {
		return siccAccionesCobranzaList;
	}


	/**
	 * @param siccAccionesCobranzaList the siccAccionesCobranzaList to set
	 */
	public void setSiccAccionesCobranzaList(LabelValue[] siccAccionesCobranzaList) {
		this.siccAccionesCobranzaList = siccAccionesCobranzaList;
	}


	/**
	 * @return the busquedaHIPClientePOPUPSearchAction
	 */
	public BusquedaHIPClientePOPUPSearchAction getBusquedaHIPClientePOPUPSearchAction() {
		return busquedaHIPClientePOPUPSearchAction;
	}


	/**
	 * @param busquedaHIPClientePOPUPSearchAction the busquedaHIPClientePOPUPSearchAction to set
	 */
	public void setBusquedaHIPClientePOPUPSearchAction(
			BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction) {
		this.busquedaHIPClientePOPUPSearchAction = busquedaHIPClientePOPUPSearchAction;
	}

	/**
	 * @return the mostrarPopupHIPCliente
	 */
	public boolean isMostrarPopupHIPCliente() {
		return mostrarPopupHIPCliente;
	}

	/**
	 * @param mostrarPopupHIPCliente the mostrarPopupHIPCliente to set
	 */
	public void setMostrarPopupHIPCliente(boolean mostrarPopupHIPCliente) {
		this.mostrarPopupHIPCliente = mostrarPopupHIPCliente;
	}

	/**
	 * @return the listaTelefonos
	 */
	public List getListaTelefonos() {
		return listaTelefonos;
	}

	/**
	 * @param listaTelefonos the listaTelefonos to set
	 */
	public void setListaTelefonos(List listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
	}

	/**
	 * @return the detalleConsultorasList
	 */
	public List getDetalleConsultorasList() {
		return detalleConsultorasList;
	}

	/**
	 * @param detalleConsultorasList the detalleConsultorasList to set
	 */
	public void setDetalleConsultorasList(List detalleConsultorasList) {
		this.detalleConsultorasList = detalleConsultorasList;
	}

	/**
	 * @return the seleccionable
	 */
	public boolean isSeleccionable() {
		return seleccionable;
	}

	/**
	 * @param seleccionable the seleccionable to set
	 */
	public void setSeleccionable(boolean seleccionable) {
		this.seleccionable = seleccionable;
	}

	/**
	 * @return the historialGestionesList
	 */
	public List getHistorialGestionesList() {
		return historialGestionesList;
	}

	/**
	 * @param historialGestionesList the historialGestionesList to set
	 */
	public void setHistorialGestionesList(List historialGestionesList) {
		this.historialGestionesList = historialGestionesList;
	}

	/**
	 * @return the listaConsultorasLlamadaEntrante
	 */
	public List getListaConsultorasLlamadaEntrante() {
		return listaConsultorasLlamadaEntrante;
	}

	/**
	 * @param listaConsultorasLlamadaEntrante the listaConsultorasLlamadaEntrante to set
	 */
	public void setListaConsultorasLlamadaEntrante(
			List listaConsultorasLlamadaEntrante) {
		this.listaConsultorasLlamadaEntrante = listaConsultorasLlamadaEntrante;
	}

	/**
	 * @return the paginaPadre
	 */
	public String getPaginaPadre() {
		return paginaPadre;
	}

	/**
	 * @param paginaPadre the paginaPadre to set
	 */
	public void setPaginaPadre(String paginaPadre) {
		this.paginaPadre = paginaPadre;
	}

	/**
	 * @return the consultaHIPDatosClienteAction
	 */
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	/**
	 * @param consultaHIPDatosClienteAction the consultaHIPDatosClienteAction to set
	 */
	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}
	

	// metodo que sale del popup	
	public void salirAPantallaPadre(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina(this.paginaPadre);			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}	
			
	}

	/**
	 * @return the mostrarPopupCuentaCorriente
	 */
	public boolean isMostrarPopupCuentaCorriente() {
		return mostrarPopupCuentaCorriente;
	}

	/**
	 * @param mostrarPopupCuentaCorriente the mostrarPopupCuentaCorriente to set
	 */
	public void setMostrarPopupCuentaCorriente(boolean mostrarPopupCuentaCorriente) {
		this.mostrarPopupCuentaCorriente = mostrarPopupCuentaCorriente;
	}
		
}

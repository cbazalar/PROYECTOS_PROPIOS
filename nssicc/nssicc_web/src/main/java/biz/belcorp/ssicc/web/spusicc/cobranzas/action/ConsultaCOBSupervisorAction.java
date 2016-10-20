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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBEjecutivoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBSupervisorService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.ConsultaHIPDatosClienteAction;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPDatosClienteForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ConsultaCOBSupervisorForm;

@SessionScoped
@ManagedBean
public class ConsultaCOBSupervisorAction extends BaseConsultaAbstractAction {

	private static final long serialVersionUID = -8271679937013922665L;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList = {};

	private List siccSociedadList;
	private Integer longitudCampoClientes;
	private List siccTelefonosList;
	private List bitacoraLlamadasList;
	private List referenciasList;
	private List referenciasDeudoraList;
	private List avalesDeudoraList;
	private LabelValue[] siccCobradorList;
	private LabelValue[] siccEtapasList;
	private LabelValue[] siccAccionesCobranzaList;
	private List listaTelefonos;
	private List listaConsultoras;
	private List detalleConsultorasList;
	private boolean seleccionable;
	private List historialGestionesList;
	
	@ManagedProperty(value = "#{consultaHIPDatosClienteAction}")
	protected ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	@ManagedProperty(value = "#{consultaCOBEjecutivoLlamadaEntranteAction}")
	protected ConsultaCOBEjecutivoLlamadaEntranteAction consultaCOBEjecutivoLlamadaEntranteAction;
	
	@ManagedProperty(value="#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;
	private static final String POPUP_HIPCLIENTE = "HIPCLIENTE";
	private boolean mostrarPopupHIPCliente;
	
	
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
				ConsultaCOBSupervisorForm busquedaForm = (ConsultaCOBSupervisorForm)this.formBusqueda;				
				busquedaForm.setCodigoConsultoraSearch((String)clienteHipMap.get("codigoCliente"));
			
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
		return new ConsultaCOBSupervisorForm();
	}

	
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm) this.formBusqueda;
		// Carga de los combos
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
						
		//Carga combo de Sociedades
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccSociedadList = service.getSociedadesByCodigoPais(f.getCodigoPais());
		
        f.setIndice(0);
        
        f.setCodigoCobrador(usuario.getLogin());

		ClienteUAGenerarService svc = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		this.longitudCampoClientes = svc.getTamanhoNumeroCliente(f.getCodigoPais());
		
		log.debug("svc.getTamanhoNumeroCliente(pais.getCodigo(): " + this.longitudCampoClientes);
		
		this.setMostrarListaBusqueda(false);
		this.setMostrarBotonBuscar(false);
	}
	
	public void limpiar(ConsultaCOBSupervisorForm f){
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
		f.setDeudaAsignada("");
		f.setTelefonoFijo("");
		f.setTelefonoMovil("");
		f.setTelefonoTrabajo("");
		f.setIndice(0);
		
		f.setCodigoConsultoraSearch("");
		f.setNumeroDocumentoSearch("");
		
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
	private void setForm(ConsultaCOBSupervisorForm f, Map map, List listaTelefonos) {
		
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
		f.setDeudaAsignada((map.get("deudaAsignada") == null) ? "":map.get("deudaAsignada").toString());
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
	private Map getCriteriaSearch(ConsultaCOBSupervisorForm f) {
		Map criteria = new HashMap();
			
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("codigoPeriodo",f.getCodigoPeriodo());
		criteria.put("codigoSociedad",f.getCodigoSociedad());
		criteria.put("codigoEtapa",f.getCodigoEtapa());
		criteria.put("codigoCobrador",f.getCodigoCobrador());
		criteria.put("codigoRegion",f.getCodigoRegion());
		criteria.put("codigoZona",f.getCodigoZona());
		criteria.put("codigoConsultoraSearch",f.getCodigoConsultoraSearch());
		criteria.put("numeroDocumentoSearch",f.getNumeroDocumentoSearch());		
		
		criteria.put("gestionFiltro",f.getGestionFiltro());
		log.debug(f.getGestionFiltro());
		
		criteria.put("criterioFiltro",getCriterioFiltro(f.getCriterioFiltro()));  
		criteria.put("criterioOrdenamiento",getCriterioOrdenamiento(f.getCriterioOrdenamiento()));    
 
		return criteria;
	}  
	
	/**
	 * Obtiene el criterio del Filtro
	 * @param criterio
	 * @return
	 */
	private String getCriterioFiltro(String criterio){
		
		log.debug("___FILTRO = " + criterio);
		
		//No Gestionadas
		if(criterio.equals("NG"))return "AND CARTE.IND_CART_GEST=0";
		
		// Asignadas a Supervisor
		if(criterio.equals("AS")) return "AND CARTE.IND_GEST_SUPE=1";
		
		//Incumplidas Comppromiso de Pago
		if(criterio.equals("IC")) return "AND CARTE.IND_COMP_PAGO=2";
		
		//Volver a Llamar
		if(criterio.equals("VL")) return "AND CARTE.COD_ULTI_GEST_COBR='VOL'";
		
		//Volver a Gestionar
		if(criterio.equals("VG")) return "AND CARTE.COD_ULTI_GEST_COBR='VGE'";
				
		return "";
	}
	
	/**
	 * Obtiene el criterio de Ordenamiento en el Filtro
	 * @param criterio
	 * @return
	 */
	private String getCriterioOrdenamiento(String criterio){
		log.debug("___CRITERIO = " + criterio);
		if(criterio.equals("MD"))  return  "IMP_DEUD_PEND DESC";
		if(criterio.equals("FC"))  return  "FEC_CIER ASC, IMP_DEUD_PEND DESC";
		if(criterio.equals("DA"))  return  "NUM_DIAS_MORA DESC , IMP_DEUD_PEND DESC"; 	
		return "";
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	public void searchConsultora(ActionEvent event){
		if (log.isWarnEnabled()) {
			log.warn("Entering 'searchConsultora' method");
		}
		
		try {		
			ConsultaCOBSupervisorService service = (ConsultaCOBSupervisorService)getBean("spusicc.consultaCOBSupervisorService");
			ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm) this.formBusqueda;
			List listaConsultoras = new ArrayList();

			Map criteria = new HashMap();
			criteria.put("codigoConsultoraSearch",f.getCodigoConsultoraSearch());					
			listaConsultoras = service.getConsultoraSupervisorByFilter(criteria);
					
			if (CollectionUtils.isEmpty(listaConsultoras)) {
				listaConsultoras = new ArrayList();
				listaConsultoras.add(new HashMap());
				limpiar(f);
				
				log.debug("___NO HAY RESULTADOS");
				
				this.addError("Error: ", this.getResourceMessage("cobranzas.cartera.notfound"));
				
			}else{
				this.listaConsultoras = listaConsultoras;	

				Map map = new HashMap();
				List listaTelefonos = new ArrayList();
		
					map=(HashMap)listaConsultoras.get(0);			
					
					if (f.getIndiceSearch()==0 ){							
					   f.setIndice(1);
					}else{
						f.setIndice(f.getIndiceSearch());
					}
					
					f.setTamano(listaConsultoras.size());
					
					f.setCodigoEtapaSearch(f.getCodigoEtapa());
					f.setCodigoSociedadSearch(f.getCodigoSociedad());
					f.setCodigoZonaSearch(f.getCodigoZona());
					f.setCodigoRegionSearch(f.getCodigoRegion());
					f.setCodigoCobradorSearch(f.getCodigoCobrador());
					
					setForm(f, map, listaTelefonos);
					
					this.listaTelefonos = listaTelefonos;								
					this.bitacoraLlamadasList = service.getListaGestionesCartera(getCriteriaSave(f));
					this.referenciasList = service.getReferencias(getCriteriaSave(f));
					this.referenciasDeudoraList = service.getReferenciasDeudora(getCriteriaSave(f));
					this.avalesDeudoraList = service.getAvalesDeudora(getCriteriaSave(f));
			}
			
		}
		catch (Exception e) {
			log.debug("___NO HAY RESULTADOS");
			
			this.addError("Error: ", this.getResourceMessage("cobranzas.cartera.notfound"));
			
		}
		
		return;
	}
	
	public void search(ActionEvent event){
		if (log.isWarnEnabled()) {
			log.warn("Entering 'search' method");
		}
		
		try {		
			ConsultaCOBSupervisorService service = (ConsultaCOBSupervisorService)getBean("spusicc.consultaCOBSupervisorService");
			ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm) this.formBusqueda;
			List listaConsultoras = new ArrayList();

			listaConsultoras = service.getCarteraSupervisorByFilter(getCriteriaSearch(f));
					
			if (CollectionUtils.isEmpty(listaConsultoras)) {
				listaConsultoras = new ArrayList();
				listaConsultoras.add(new HashMap());
				limpiar(f);
				
				log.debug("___NO HAY RESULTADOS");
				
				this.addError("Error: ", this.getResourceMessage("cobranzas.cartera.notfound"));
				
			}else{
				this.listaConsultoras = listaConsultoras;	

				Map map = new HashMap();
				List listaTelefonos = new ArrayList();
		
					map=(HashMap)listaConsultoras.get(0);			
					
					if (f.getIndiceSearch()==0 ){							
					   f.setIndice(1);
					}else{
						f.setIndice(f.getIndiceSearch());
					}
					
					f.setTamano(listaConsultoras.size());
					
					f.setCodigoEtapaSearch(f.getCodigoEtapa());
					f.setCodigoSociedadSearch(f.getCodigoSociedad());
					f.setCodigoZonaSearch(f.getCodigoZona());
					f.setCodigoRegionSearch(f.getCodigoRegion());
					f.setCodigoCobradorSearch(f.getCodigoCobrador());
					
					setForm(f, map, listaTelefonos);
					
					this.listaTelefonos = listaTelefonos;								
					this.bitacoraLlamadasList = service.getListaGestionesCartera(getCriteriaSave(f));
					this.referenciasList = service.getReferencias(getCriteriaSave(f));
					this.referenciasDeudoraList = service.getReferenciasDeudora(getCriteriaSave(f));
					this.avalesDeudoraList = service.getAvalesDeudora(getCriteriaSave(f));
			}
			
		}
		catch (Exception e) {
			log.debug("___NO HAY RESULTADOS");
			
			this.addError("Error: ", this.getResourceMessage("cobranzas.cartera.notfound"));
			
		}
		
		return;
	}

	public void next(ActionEvent event) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'next' method");
	 	}
		 
		Map map = new HashMap();
		List listaTelefonos = new ArrayList();
		
		ConsultaCOBSupervisorService service = (ConsultaCOBSupervisorService)getBean("spusicc.consultaCOBSupervisorService");
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;

		if(f.getIndice() < listaConsultoras.size()){ 
			map=(HashMap)listaConsultoras.get(f.getIndice());
			f.setIndice(f.getIndice()+1);			
			setForm(f, map, listaTelefonos);						
		}
		
		this.listaTelefonos = listaTelefonos;								
		this.bitacoraLlamadasList = service.getListaGestionesCartera(getCriteriaSave(f));
		this.referenciasList = service.getReferencias(getCriteriaSave(f));
		this.referenciasDeudoraList = service.getReferenciasDeudora(getCriteriaSave(f));
		this.avalesDeudoraList = service.getAvalesDeudora(getCriteriaSave(f));
		
	 }
	
	 public void previous(ActionEvent event) throws Exception {
	 	 
		 if (log.isDebugEnabled()) {
				log.debug("Entering 'previous' method");
			}
			 
		 Map map = new HashMap();
		 List listaTelefonos = new ArrayList();
		 
		 ConsultaCOBEjecutivoService service = (ConsultaCOBEjecutivoService)getBean("spusicc.consultaCOBEjecutivoService");
		 ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		
		 if(f.getIndice() > 1){ 
			map=(HashMap)listaConsultoras.get(f.getIndice()-2);
			f.setIndice(f.getIndice()-1);
			setForm(f, map, listaTelefonos);
		 }
			
		 this.listaTelefonos = listaTelefonos;								
		 this.bitacoraLlamadasList = service.getListaGestionesCartera(getCriteriaSave(f));
		 this.referenciasList = service.getReferencias(getCriteriaSave(f));
		 this.referenciasDeudoraList = service.getReferenciasDeudora(getCriteriaSave(f));
		 this.avalesDeudoraList = service.getAvalesDeudora(getCriteriaSave(f));
		 
	 }
	 
	/**
	 * Obtiene los criterios para guardar los datos
	 * @param f
	 * @return
	 */
	private Map getCriteriaSave(ConsultaCOBSupervisorForm f) {
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
	
	public void loadEtapas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadEtapas");
		}
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		String codigoSociedad = (String) val.getNewValue();
		if (StringUtils.isNotBlank(codigoSociedad)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.setSiccEtapasList(ajax.getEtapasDeudaByPaisSociedad( f.getCodigoPais(), codigoSociedad));	 	
              
		} else {
			this.setSiccEtapasList(null);
		}
	}
	
	public void loadRegionesPeriodo(String valor){
		if (log.isDebugEnabled()) {
			log.debug("loadRegionesPeriodo");
		}
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		if (StringUtils.isNotBlank(valor)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
	
			LabelValue[] lv = new LabelValue[1];
			lv[0] = new  LabelValue("Todos", "");
			
			LabelValue[] lvLista = ajax.getRegionesByPaisSociedadEtapaDeudaPeriodo(f.getCodigoPais(),f.getCodigoSociedad(),f.getCodigoEtapa(), 
					valor);
					
			this.setSiccRegionList(ArrayUtils.addAll(lv, lvLista));	 	

		}else{
			this.setSiccRegionList(null);
		}

	}
	
	public void loadRegiones(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadRegiones");
		}
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		String codigo = (String) val.getNewValue();
		if (StringUtils.isNotBlank(codigo)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
	
			LabelValue[] lv = new LabelValue[1];
			lv[0] = new  LabelValue("Todos", "");
			
			LabelValue[] lvLista = ajax.getRegionesByPaisSociedadEtapaDeudaPeriodo(f.getCodigoPais(),f.getCodigoSociedad(),f.getCodigoEtapa(), 
		 			f.getCodigoPeriodo());
			
			this.setSiccRegionList(ArrayUtils.addAll(lv, lvLista));	 	

		}else{
			this.setSiccRegionList(null);
		}

	}
	
	public void loadCobraAndAccionFiltro(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadCobraAndAccionFiltro");
		}
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		String codigoEtapa = (String) val.getNewValue();
		if (StringUtils.isNotBlank(codigoEtapa)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue[] lvLista = null;
			LabelValue[] lv = new LabelValue[1];
			lv[0] = new  LabelValue("Todos", "");
			
			lvLista = ajax.getCobradoresByPaisSociedadEtapaDeuda(f.getCodigoPais(), 
		      											 f.getCodigoSociedad(),codigoEtapa);

			this.setSiccCobradorList(ArrayUtils.addAll(lv, lvLista));
			
			lvLista = ajax.getAccionesCobranzaByPaisSociedadEtapa(f.getCodigoPais(), 
														 //f.getCodigoSociedad(),      																
															codigoEtapa);
			this.setSiccAccionesCobranzaList(ArrayUtils.addAll(lv, lvLista));
			
			lvLista = ajax.getRegionesByPaisSociedadEtapaDeudaPeriodo(f.getCodigoPais(),f.getCodigoSociedad(),codigoEtapa, 
		 			f.getCodigoPeriodo());	
		    
		    this.setSiccRegionList(ArrayUtils.addAll(lv, lvLista));
		} else {
			this.setSiccCobradorList(null);
			this.setSiccAccionesCobranzaList(null);
			this.setSiccRegionList(null);
		}
	}
	
	
	public String completarCaracteres(String valor, String longitud, String caracter) {		
		String valorAux = new String("");		
		if (valor.length() != 0) {
			int longitudInt = Integer.parseInt(longitud);
			int faltante = longitudInt - valor.length();
			if (faltante >= 0) {
				for (int i = 0; i < faltante; i++) {
					valorAux = valorAux + caracter;
				}
				valorAux = valorAux + valor;
			} else {			
				faltante = valor.length() - longitudInt;
				valorAux = valor.substring(faltante, longitudInt);
			}
		}		
		return valorAux;
	}
	
	public void loadAcciones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadAcciones");
		}

		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		String accion = (String) val.getNewValue();
		if (StringUtils.isNotBlank(accion) && StringUtils.isNotBlank(f.getCodigoSociedad())
				&& StringUtils.isNotBlank(f.getCodigoEtapaConsultora())) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
		      this.siccAccionesCobranzaList = ajax.getAccionesCobranzaByPaisSociedadEtapaTipoAccion(f.getCodigoPais(), 
						  f.getCodigoSociedad(),      																
						  f.getCodigoEtapaConsultora(),
						  accion);

		} else {
			this.siccAccionesCobranzaList  = null;
		}
	}

	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		String region = (String) val.getNewValue();
		if (StringUtils.isNotBlank(region)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			
			LabelValue[] lv = new LabelValue[1];
			lv[0] = new  LabelValue("Todos", "");
			
			LabelValue[] lvLista = ajax.getZonasByPaisRegion(f.getCodigoPais(), region);
			
			setSiccZonaList(ArrayUtils.addAll(lv, lvLista));

		} else {
			setSiccZonaList(null);
		}
	}
	
	/**
	 * Obtiene los criterios necesarios para la actualizacion de Datos
	 * @param f
	 * @return
	 */
	private Map getCriteriaActualizar(ConsultaCOBSupervisorForm f) {
		Map criteria = new HashMap();
									
		criteria.put("codigoCartera",f.getCodigoCartera());	    	
		criteria.put("codigoConsultora",f.getCodigoConsultora());		
		criteria.put("codigoCobrador",f.getCodigoCobrador());
		criteria.put("codigoEtapaDeuda",f.getCodigoEtapaConsultora());
		criteria.put("tipoTelefono",f.getTipoTelefono());
		criteria.put("nuevoTelefono",f.getNuevoTelefono());				
		
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
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		ConsultaCOBEjecutivoService service = (ConsultaCOBEjecutivoService)getBean("spusicc.consultaCOBEjecutivoService");
		Map criteria = new HashMap();
		
		criteria.put("codigoConsultora",f.getCodigoConsultora());
		
		this.detalleConsultorasList = service.getDetalleConsultora(criteria);
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
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		ConsultaCOBGenericoService service = (ConsultaCOBGenericoService)getBean("spusicc.consultaCOBGenericoService");
		Map criteria = new HashMap();
					    
		criteria.put("codigoConsultora",f.getCodigoConsultora());
					
		this.historialGestionesList = service.getHistorialGestionesCobranza(criteria);
		
		this.listaBusqueda = null;
	}
	
	 public void bloquear(ActionEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'rebajar' method");
		}
		
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
		f.setCodigoCobrador(usuario.getLogin());
		
		ConsultaCOBSupervisorService service = (ConsultaCOBSupervisorService)getBean("spusicc.consultaCOBSupervisorService");				
		try {						
		  service.bloquearFinancieroDeudora(getCriteriaActualizar(f));
		  
		  this.addInfo("", this.getResourceMessage("bloqueo.deudora.added"));
		
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	} 
	 
	 
	 public void rebajarDefinitivo(ActionEvent event) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'rebajar Definitivo' method");
		}
		
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
		f.setCodigoCobrador(usuario.getLogin());
		
		ConsultaCOBSupervisorService service = (ConsultaCOBSupervisorService)getBean("spusicc.consultaCOBSupervisorService");				
		try {							
		service.rebajarDefinitivoCarteraDeudora(getCriteriaActualizar(f));
		 
		  this.addInfo("", this.getResourceMessage("rebaja.deudora.added"));
		} catch (Exception e) {
			log.debug(" JFA Errror al Realizar la Rebaja Definitiva");
			this.addInfo("", this.getResourceMessage("cobranzas.rebaja.error"));
		}			

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
			
			ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
			
			ConsultaCOBSupervisorService service = (ConsultaCOBSupervisorService)getBean("spusicc.consultaCOBSupervisorService");
			
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
		 
	 public void rebajarTemporal(ActionEvent event) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'rebajar Temporal' method");
		}
		
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		f.setCodigoCobrador(usuario.getLogin());
		
		ConsultaCOBSupervisorService service = (ConsultaCOBSupervisorService)getBean("spusicc.consultaCOBSupervisorService");				
		try {							
			service.rebajarTemporalCarteraDeudora(getCriteriaActualizar(f));
			this.addInfo("", this.getResourceMessage("rebaja.deudora.added"));
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}			

	}
	 
	public void actualizarTelefono(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("actualizarTelefono");
		}
		ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;
		
		ConsultaCOBSupervisorService service = (ConsultaCOBSupervisorService)getBean("spusicc.consultaCOBSupervisorService");				
		
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
			ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;	
			ConsultaHIPDatosClienteForm searchForm = (ConsultaHIPDatosClienteForm)  this.consultaHIPDatosClienteAction.getFormBusqueda();	
			this.consultaHIPDatosClienteAction.setPaginaPadre("consultaCOBSupervisorForm");
			this.consultaHIPDatosClienteAction.view();
			this.redireccionarPagina("consultaHIPDatosClienteFormCOB");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
	}
	
	public void linkLlamadaEntrante(ActionEvent event) {
		try {
			ConsultaCOBSupervisorForm f = (ConsultaCOBSupervisorForm)this.formBusqueda;	
			this.consultaCOBEjecutivoLlamadaEntranteAction.setPaginaPadre("consultaCOBSupervisorForm");
			this.consultaCOBEjecutivoLlamadaEntranteAction.view();
			this.redireccionarPagina("consultaCOBEjecutivoLlamadaEntranteForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
	}
	
	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}


	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}


	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
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
	 * @return the siccCobradorList
	 */
	public LabelValue[] getSiccCobradorList() {
		return siccCobradorList;
	}


	/**
	 * @param siccCobradorList the siccCobradorList to set
	 */
	public void setSiccCobradorList(LabelValue[] siccCobradorList) {
		this.siccCobradorList = siccCobradorList;
	}


	/**
	 * @return the siccEtapasList
	 */
	public LabelValue[] getSiccEtapasList() {
		return siccEtapasList;
	}


	/**
	 * @param siccEtapasList the siccEtapasList to set
	 */
	public void setSiccEtapasList(LabelValue[] siccEtapasList) {
		this.siccEtapasList = siccEtapasList;
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
	 * @return the listaConsultoras
	 */
	public List getListaConsultoras() {
		return listaConsultoras;
	}

	/**
	 * @param listaConsultoras the listaConsultoras to set
	 */
	public void setListaConsultoras(List listaConsultoras) {
		this.listaConsultoras = listaConsultoras;
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

	/**
	 * @return the consultaCOBEjecutivoLlamadaEntranteAction
	 */
	public ConsultaCOBEjecutivoLlamadaEntranteAction getConsultaCOBEjecutivoLlamadaEntranteAction() {
		return consultaCOBEjecutivoLlamadaEntranteAction;
	}

	/**
	 * @param consultaCOBEjecutivoLlamadaEntranteAction the consultaCOBEjecutivoLlamadaEntranteAction to set
	 */
	public void setConsultaCOBEjecutivoLlamadaEntranteAction(
			ConsultaCOBEjecutivoLlamadaEntranteAction consultaCOBEjecutivoLlamadaEntranteAction) {
		this.consultaCOBEjecutivoLlamadaEntranteAction = consultaCOBEjecutivoLlamadaEntranteAction;
	}

}

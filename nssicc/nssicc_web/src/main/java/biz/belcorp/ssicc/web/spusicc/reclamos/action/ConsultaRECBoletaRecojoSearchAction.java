package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.DatosConsultora;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionBoletasRecojoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ConsultaRECBoletaRecojoSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConsultaRECBoletaRecojoSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = 8795725961471495077L;
	private Integer longitudCampoClientes;
	private List recCabecerasBoletaRecojoList;
	private static final String POPUP_CONSULTORA = "CONSULTORA";
	private boolean mostrarPopupConsultora;
	private DataTableModel dataModelDetalle;
	private String codigoCliente;
	private String nomConsultora;
	private BoletaRecojoCabecera objSeleccionado;
	private Integer sumaReclamadasT;
	private Integer sumaRecogidasT;
	private Integer sumaEliminadasT;
	private boolean botonesShow;
	private boolean mostrarBotonesEdicion;

	@ManagedProperty(value = "#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ConsultaRECBoletaRecojoSearchAction - devuelveFormBusqueda" );
		}
		
		ConsultaRECBoletaRecojoSearchForm f = new ConsultaRECBoletaRecojoSearchForm();
		return f;
	}
	
	/**
	 * @param actionEvent
	 */
	public void salirPadre(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina("consultaRECBoletaRecojoForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * @param event
	 */
	public void abrirPopup(ActionEvent event){
		String mensaje="";
		this.objSeleccionado = new BoletaRecojoCabecera();
		this.objSeleccionado = (BoletaRecojoCabecera) this.beanRegistroSeleccionado;
	    ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;

		if (objSeleccionado == null) {
			mensaje= "Seleccione un registro";
			this.addError("Error: ", mensaje);
			return;
		}
		String codigoConsultora= "";
		try {
			this.search();
			String ventana = "PF('dialogMantenimientoForm2').show()";
			this.getRequestContext().execute(ventana);
			this.mostrarBotonSalir = false;
			this.mostrarBotonSave = false;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@SuppressWarnings("static-access")
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction
						.getBeanRegistroSeleccionado();
				ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm) this.formBusqueda;
				f.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.busquedaConsultoraPOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@SuppressWarnings("static-access")
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.mostrarPopupConsultora = true;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		this.activarHotkeyEstandar = false;
	    log.info("Entro a ConsultaRECBoletaRecojoSearchAction - setFindAttributes");
	    List cabecerasList = buscar();	    	    
	    log.info("Salio a ConsultaRECBoletaRecojoSearchAction - setFindAttributes");
		dataModelDetalle = new DataTableModel(cabecerasList);
		if(cabecerasList!=null && cabecerasList.size()>0)
			this.botonesShow=true;
	    return cabecerasList;		
	}

	public void limpiandoPantalla(){
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar  = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonBuscar=false;
		this.botonesShow=false;
		this.activarHotkeyEstandar = false;
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		log.debug("Inicio view ");
		String mensaje = "";
		limpiandoPantalla();
		
		//PARAMETRO TEMPORAL, HASTA QUE SALGA SECURITY
		GenericoService genericoService = (GenericoService)this.getBeanService("genericoService");				
		
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		parametroPais.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		//parametroPais.setCodigoParametro("017");
		parametroPais.setNombreParametro("indMostrarBotonesEdicionBR");
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		
		if(lstParametros != null && lstParametros.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros.get(0);
			String valor = ps.getValorParametro();
			
			if(StringUtils.equals(valor, Constants.ESTADO_ACTIVO))
			{
				mostrarBotonesEdicion = true;
			}
		}
		//
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.botonesShow=false;
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes =  clienteService.getTamanhoNumeroCliente(pais.getCodigo());
	    ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;
		
		String codigoConsultora = f.getCodigoConsultora();
		
		if(StringUtils.isNotBlank(codigoConsultora))
		{
			f.setCodigoConsultora(codigoConsultora);
			
			List cabecerasList = buscar();
			
			if ((cabecerasList == null) ||(cabecerasList.size() == 0)){
				mensaje = this.getResourceMessage("errors.datos.fuentes.busqueda");
				this.addInfo("Info : ", mensaje);
			}
		}
		else
		{
			this.recCabecerasBoletaRecojoList = new ArrayList();
		}			
		
	}
    
	/**
	 * 
	 */
	public void search() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}	
		String mensaje = "";
		try {
			 ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;
				this.objSeleccionado = (BoletaRecojoCabecera) this.beanRegistroSeleccionado;

				String id = this.objSeleccionado.getBoletaRecojo();
				if (!StringUtils.isBlank(id)) {
					log.debug("Id seleccionado de la lista: " + id);				
					Map criteria = new HashMap();			
					criteria.put("codigoBoletaRecojo", id);						//consultaRECBoletaRecojoDetallesSearchList
					this.recCabecerasBoletaRecojoList = new ArrayList();
					MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService)getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");
				    List lista = service.getDetallesBoletaRecojo(criteria);
				    Iterator iLista = lista.iterator();
				    while (iLista.hasNext()) {
						BoletaRecojoDetalle object = (BoletaRecojoDetalle) iLista.next();
						object.setIntUnidadesEliminadas(Integer.parseInt(object.getUnidadesEliminadas()));
						object.setIntUnidadesReclamadas(Integer.parseInt(object.getUnidadesReclamadas()));
						object.setIntUnidadesRecogidas(Integer.parseInt(object.getUnidadesRecogidas()));
					}
				    this.recCabecerasBoletaRecojoList = lista;
				    int tamanio = this.recCabecerasBoletaRecojoList.size();
				    if(tamanio > 0){
				    	int sumaReclamadasT = 0;
						int sumaRecogidasT = 0;
						int sumaEliminadasT = 0;
						for (int i = 0; i < tamanio; i++) {
							BoletaRecojoDetalle obj = (BoletaRecojoDetalle) this.recCabecerasBoletaRecojoList
									.get(i);
							sumaReclamadasT = sumaReclamadasT + obj.getIntUnidadesReclamadas();
							sumaRecogidasT = sumaRecogidasT + obj.getIntUnidadesRecogidas();
							sumaEliminadasT = sumaEliminadasT + obj.getIntUnidadesEliminadas();
						}
						
						this.sumaReclamadasT = sumaReclamadasT;
						this.sumaRecogidasT = sumaRecogidasT;
						this.sumaEliminadasT = sumaEliminadasT;
				    	
				    }
				    
				    List list = service.getConsultaRECBoletaRecojoDetail(id);
				    
				    String nombreCompleto = f.getNombreConsultora();
				    //(String) request.getSession().getAttribute("NOMBRE_COMPLETO_CONSULTORA");
				   
				    if(list!=null && list.size()>0){
				    	Map map = (Map)list.get(0);		    	
				    	f.setCodigoCliente((String)map.get("codigoMotivo")==null?(String)map.get("codigoCliente"):
				    		(String)map.get("codigoCliente")+"-"+(String)map.get("nombreCliente"));	
				    	f.setNombreConsultora(nombreCompleto);
				    	f.setNumeroBoleta((String)map.get("numeroBoleta"));
				    	f.setNumeroRecojo((String)map.get("numeroRecojo"));
				    	f.setGestion((String)map.get("gestion"));
				    	f.setResultado((String)map.get("resultado"));
				    	f.setFechaEmision1((String)map.get("fechaEmision1"));
				    	f.setFechaEmision2((String)map.get("fechaEmision2"));
				    	f.setFechaRecojo1((String)map.get("fechaRecojo1"));
				    	f.setFechaRecojo2((String)map.get("fechaRecojo2"));
				    	f.setImpreso1((String)map.get("impreso1"));
				    	f.setImpreso2((String)map.get("impreso2"));
				    	f.setEnviado1((String)map.get("enviado1"));
				    	f.setEnviado2((String)map.get("enviado2"));
				    	f.setRegreso1((String)map.get("regreso1"));
				    	f.setRegreso2((String)map.get("regreso2"));
				    	f.setLoteEnvio1((String)map.get("loteEnvio1"));
				    	f.setLoteEnvio2((String)map.get("loteEnvio2"));
				    	f.setLoteRegreso1((String)map.get("loteRegreso1"));
				    	f.setLoteRegreso2((String)map.get("loteRegreso2"));
				    }
				    	
				}				
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	   		
	}
	
	 /**
	 * @param evt
	 * @throws Exception
	 */
	public void activarBoletaRecojo(ActionEvent evt)   throws Exception {
	        
		 log.debug("Entering 'actualizaIndicadores' method");
	        String mensaje= "";
		    ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;
			this.objSeleccionado = (BoletaRecojoCabecera) this.beanRegistroSeleccionado;
			if (objSeleccionado == null) {
				mensaje= "Seleccione un registro";
				this.addError("Error: ", mensaje);
				return;
			}
			String id = this.objSeleccionado.getBoletaRecojo();
	                
	        if (log.isDebugEnabled()) {
	            log.debug("Id seleccionado de la lista: " + id);
	         } 
	    
		    MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService)getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		       
		    Map activacionBoletaRecojoMap = new HashMap();	    
			try{
				
				if (StringUtils.isNotEmpty(id)){
					
					activacionBoletaRecojoMap.put("numeroBoleta", id);
					activacionBoletaRecojoMap.put("usuario", usuario.getLogin());
					service.activarBoletaRecojoProcess(activacionBoletaRecojoMap);		
					
					String mensajeError = (String) activacionBoletaRecojoMap.get("mensajeError");
									
					if (StringUtils.isNotEmpty(mensajeError)){
						this.addError("Error : ", mensajeError);
					 
					}else{
						mensaje = this.getResourceMessage("consultaRECBoletaRecojoSearchList.activacion.success");
						this.addInfo("Info : ", mensaje);						
					}	
					
					this.find();
				}				
			
			}catch (Exception e){
				mensaje = this.getResourceMessage("errors.invalid.transaction.clusterizacion");
				this.addError("Error : ", mensaje);
				
			
			}
		}
	 
	 
	 /**
	 * @param evt
	 * @throws Exception
	 */
	public void eliminarBoletaRecojo(ActionEvent evt) throws Exception {
		 log.debug("Entering 'eliminarBoletaRecojo' method");
	        
			this.objSeleccionado = (BoletaRecojoCabecera) this.beanRegistroSeleccionado;

		    String mensaje=  "";

		    String id = this.objSeleccionado.getBoletaRecojo();
	    	if (objSeleccionado == null) {
				mensaje= "Seleccione un registro";
				this.addError("Error: ", mensaje);
				return;
			}        
	        if (log.isDebugEnabled()) {
	            log.debug("Id seleccionado de la lista: " + id);
	         } 
	    
		    MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService)getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");
		    Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		       
		    Map eliminacionBoletaRecojoMap = new HashMap();
		    
		    
			try{
				
				if (StringUtils.isNotEmpty(id)){
					
					eliminacionBoletaRecojoMap.put("numeroBoleta", id);
					eliminacionBoletaRecojoMap.put("usuario", usuario.getLogin());
					service.eliminarBoletaRecojoProcess(eliminacionBoletaRecojoMap);				
					
					String mensajeError = (String) eliminacionBoletaRecojoMap.get("mensajeError");
					
					if (StringUtils.isNotEmpty(mensajeError)){
						this.addError("Error : ", mensajeError);				
					 
					}else{
						mensaje = this.getResourceMessage("consultaRECBoletaRecojoSearchList.eliminacion.success");
						this.addInfo("Info : ", mensaje);						
					}
					
					this.find();
				}
				
			
			}catch (Exception e){
	            mensaje = this.getResourceMessage("errors.invalid.transaction.clusterizacion");
				this.addError("Error : ", mensaje);	
			}			    	    
		}
	
	
	 /**
	 * @return
	 */
	public List buscar(){
		 ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;

		    MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService)getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService"); 
		    String codigoConsultora = f.getCodigoConsultora();
		    
		    //-- Crear pojo
		    Map criteria = new HashMap();
		    criteria.put("codigoConsultora", codigoConsultora.equals("")?"%":codigoConsultora);
		    criteria.put("numeroBoletaRecojo", f.getNumeroBoletaRecojo().equals("")?"%":f.getNumeroBoletaRecojo());
		    
		    //-- Logica para traer listado de boletas recojos de la consultora
		    List cabecerasList = service.getCabeceraBoletaRecojo(criteria);
		    
		    if(cabecerasList.size() > 0 && codigoConsultora.equals("")){
		    	BoletaRecojoCabecera bean = (BoletaRecojoCabecera) cabecerasList.get(0);
		    	codigoConsultora = bean.getCodigoConsultora();
			    criteria.put("codigoConsultora", codigoConsultora);
		    }
		    
		    //-- Logica para traer indicador de Consultora Bloqueada
		    f.setIndicadorBloqueada(service.getBloqueoConsultora(criteria));
		    
		    //-- Logica para traer datos de la consultora
		    DatosConsultora datosConsultora = service.getDatosConsultora(criteria);
		    if(datosConsultora.getNombreCliente()==null)
		    	f.setNombreConsultora(null);
		    else
			    f.setNombreConsultora( 
			    	(
			    		f.getCodigoConsultora().equals("") ?  (codigoConsultora.equals("")?"":codigoConsultora.concat(" - ")) : ""
			    	) + 
			    	datosConsultora.getNombreCliente() 
			    );
		    f.setZona(datosConsultora.getZona());
		    f.setGerente(datosConsultora.getNombreManager());
		    this.listaBusqueda = cabecerasList;
		    this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		    if(cabecerasList!=null && cabecerasList.size()>0)
				this.botonesShow=true;
		    
		    //this.dataModelDetalle = new DataTableModel(cabecerasList);
		    return this.recCabecerasBoletaRecojoList = cabecerasList;		
	}
    	
	//getters && setters
	
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
	 * @return the recCabecerasBoletaRecojoList
	 */
	public List getRecCabecerasBoletaRecojoList() {
		return recCabecerasBoletaRecojoList;
	}

	/**
	 * @param recCabecerasBoletaRecojoList the recCabecerasBoletaRecojoList to set
	 */
	public void setRecCabecerasBoletaRecojoList(List recCabecerasBoletaRecojoList) {
		this.recCabecerasBoletaRecojoList = recCabecerasBoletaRecojoList;
	}

	/**
	 * @return the mostrarPopupConsultora
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora the mostrarPopupConsultora to set
	 */
	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}

	/**
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @return the popupConsultora
	 */
	public static String getPopupConsultora() {
		return POPUP_CONSULTORA;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {	
		return null;
	}

	/**
	 * @return the dataModelDetalle
	 */
	public DataTableModel getDataModelDetalle() {
		return dataModelDetalle;
	}

	/**
	 * @param dataModelDetalle the dataModelDetalle to set
	 */
	public void setDataModelDetalle(DataTableModel dataModelDetalle) {
		this.dataModelDetalle = dataModelDetalle;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the nomConsultora
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}

	/**
	 * @param nomConsultora the nomConsultora to set
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}

	/**
	 * @return the objSeleccionado
	 */
	public BoletaRecojoCabecera getObjSeleccionado() {
		return objSeleccionado;
	}

	/**
	 * @param objSeleccionado the objSeleccionado to set
	 */
	public void setObjSeleccionado(BoletaRecojoCabecera objSeleccionado) {
		this.objSeleccionado = objSeleccionado;
	}

	/**
	 * @return the sumaReclamadasT
	 */
	public Integer getSumaReclamadasT() {
		return sumaReclamadasT;
	}

	/**
	 * @param sumaReclamadasT the sumaReclamadasT to set
	 */
	public void setSumaReclamadasT(Integer sumaReclamadasT) {
		this.sumaReclamadasT = sumaReclamadasT;
	}

	/**
	 * @return the sumaRecogidasT
	 */
	public Integer getSumaRecogidasT() {
		return sumaRecogidasT;
	}

	/**
	 * @param sumaRecogidasT the sumaRecogidasT to set
	 */
	public void setSumaRecogidasT(Integer sumaRecogidasT) {
		this.sumaRecogidasT = sumaRecogidasT;
	}

	/**
	 * @return the sumaEliminadasT
	 */
	public Integer getSumaEliminadasT() {
		return sumaEliminadasT;
	}

	/**
	 * @param sumaEliminadasT the sumaEliminadasT to set
	 */
	public void setSumaEliminadasT(Integer sumaEliminadasT) {
		this.sumaEliminadasT = sumaEliminadasT;
	}

	public boolean isBotonesShow() {
		return botonesShow;
	}

	public void setBotonesShow(boolean botonesShow) {
		this.botonesShow = botonesShow;
	}

	/**
	 * @return the mostrarBotonesEdicion
	 */
	public boolean isMostrarBotonesEdicion() {
		return mostrarBotonesEdicion;
	}

	/**
	 * @param mostrarBotonesEdicion the mostrarBotonesEdicion to set
	 */
	public void setMostrarBotonesEdicion(boolean mostrarBotonesEdicion) {
		this.mostrarBotonesEdicion = mostrarBotonesEdicion;
	}	
	
}
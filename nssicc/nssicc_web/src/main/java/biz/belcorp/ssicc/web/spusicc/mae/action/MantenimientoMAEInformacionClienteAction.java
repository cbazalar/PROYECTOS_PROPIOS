package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteSubTipo;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorp;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpservice;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpserviceLocator;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXConsultoraForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEInformacionClienteForm;
import biz.belcorp.ssicc.web.spusicc.sto.action.MantenimientoSTOSolicitudCreditoAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoMAEInformacionClienteAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -618772004488208811L;

	private List maeNivelRiesgoList = new ArrayList();
	private List siccSubTipoClienteList = new ArrayList();
	private List maeClienteSubTipoList = new ArrayList();
	private DataTableModel dtmaeClienteSubTipoList = new DataTableModel();
	private boolean mostrarPopUpCliente = false;
	private ClienteSubTipo clienteSubTipoSelected;	
	private static final String POPUP_CLIENTES = "CLIENTES";
	private String beanMensaje = "";
	private String msjeGuardar;
	private String nuevoNivelRiesgo;
	private MantenimientoFLXConsultoraForm objetSeleccionado = new MantenimientoFLXConsultoraForm();
	private boolean mostrarTab;
	private boolean hijoPopup;
	private String msjeModificacion;
	private String codCliente;
	

	@ManagedProperty(value = "#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;

	private String paginaPadre;

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CLIENTES)) {
			this.busquedaHIPClientePOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClientePOPUPSearchAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map) this.busquedaHIPClientePOPUPSearchAction
						.getBeanRegistroSeleccionado();
				MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
				f.setCodigoClienteBuscar(((String) clienteHipMap
						.get("codigoCliente")));
				String apellido1 = (String) clienteHipMap.get("apellido1");
				String apellido2 = (String) clienteHipMap.get("apellido2");
				String nombre1 = (String) clienteHipMap.get("nombre1");
				String nombre2 = (String) clienteHipMap.get("nombre2");
				String numeroDocumento = (String) clienteHipMap
						.get("numeroDocumento");
				
				
				if(nombre2==null){
					f.setNombreCliente(nombre1 + " " + apellido1
							+ " " + apellido2);
				}else{
					f.setNombreCliente(nombre1 + " " + nombre2 + " " + apellido1
							+ " " + apellido2);
				}
				
				f.setNumeroDocumentoIdentidad(numeroDocumento);
				this.busquedaHIPClientePOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}
	}

	/**
	 * Metodo que se ejecuta al hacer click en el Boton Busqueda de Popup para
	 * mostrar el popup respectivo
	 */
	public void invocarPopup(ActionEvent event) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			String accion = externalContext.getRequestParameterMap().get(
					"parametroAccion");
			this.mPantallaPrincipalBean
					.setPantallaAdicionalesXhtml("/templates/templatePopupCabeceraEast.xhtml");
			log.debug("parametroAccion:" + accion);
			this.setInvocarPopup(accion);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {		
		this.mostrarPopUpCliente = false;
		this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */

	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_CLIENTES)) {
			this.mostrarPopUpCliente = true;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		if(this.hijoPopup)
			return "mantenimientoSTOSolicitudCreditoForm";
		else
			return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoMAEInformacionClienteForm f = new MantenimientoMAEInformacionClienteForm();
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) formBusqueda;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		// Obtenemos los datos del usuario Logueado
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();

		// ubicamos al cliente y su nivel de riesgo
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoCliente", f.getCodigoClienteBuscar());
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());

		// limpiamos los datos del formulario
		inicializar(f);

		/* INI SA PER-SiCC-2012-0367 */
		String validarEstatusComercial = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_ESTATUS_COMERCIAL);

		if (validarEstatusComercial != null)
			f.setValidarEstatusComercial(true);
		/* FIN SA PER-SiCC-2012-0367 */

		// inicializamos la lista de SubTipoCliente
		f.setListaSubTipoClienteActual(new ArrayList());
		this.maeClienteSubTipoList = f.getListaSubTipoClienteActual();
		this.dtmaeClienteSubTipoList = new DataTableModel(maeClienteSubTipoList);

		Map mapNivelRiesgo = clienteService.getNivelRiesgoCliente(criteria);
		if (mapNivelRiesgo != null) {
			f.setOidCliente((String) mapNivelRiesgo.get("oidCliente"));
			f.setCodigoCliente((String) mapNivelRiesgo.get("codigoCliente"));
			f.setNombreCliente((String) mapNivelRiesgo.get("nombreCliente"));
			f.setOidNivelRiesgoActual((String) mapNivelRiesgo
					.get("oidNivelRiesgo"));
			f.setCodigoNivelRiesgoActual((String) mapNivelRiesgo
					.get("codigoNivelRiesgo"));
			f.setDescripcionNivelRiesgoActual((String) mapNivelRiesgo
					.get("descripcionRiesgo"));
			f.setOidPeriodoNivelRiezgo((String) mapNivelRiesgo
					.get("oidPeriodoNivelRiezgo"));
			f.setIndicadorActividadActual((String) mapNivelRiesgo
					.get("indicadorActividad"));
			f.setOidEstatus((String) mapNivelRiesgo.get("oidEstatus"));
			f.setIndicadorActividadNuevo(f.getIndicadorActividadActual());
			f.setNuevoNivelRiesgo(f.getOidNivelRiesgoActual() + "-"
					+ f.getCodigoNivelRiesgoActual());

			/* INI SA PER-SiCC-2012-0367 */
			f.setNumeroDocumentoIdentidad((String) mapNivelRiesgo
					.get("numeroDocIdentidad"));
			/* FIN SA PER-SiCC-2012-0367 */

			f.setNumeroPeriodosSinPedido(Integer.parseInt((String) mapNivelRiesgo.get("numeroPeriodosSinPedido")));

			criteria.put("oidCliente", f.getOidCliente());

			/* INI SA PER-SiCC-2012-0580 */
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); 
			//Indica Campanha activa que se procesa actualmente													
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); 
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = service
					.getControlFacturacionById(criteria);
			criteria.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
			criteria.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());

			boolean tienePedidosEnProceso = clienteService
					.validaPedidosEnProceso(criteria);
			if (tienePedidosEnProceso) {
				inicializar(f);
				this.addError("Error",this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.pedidoEnProceso"));
				return null;
			}
			/* FIN SA PER-SiCC-2012-0580 */

			f.setFechaUltimaActualizacion(clienteService.getFechaUltimaNivelRiesgoCliente(criteria));

			List listTipoSubtipoCliente = clienteService.getListTipoSubtipoCliente(f.getOidCliente());
			f.setListaSubTipoClienteActual(listTipoSubtipoCliente);

			// Recuperamos los Tipos del Cliente/ Clasificacion del Cliente
			List listClienteSubTipo = new ArrayList();
			for (int i = 0; i < listTipoSubtipoCliente.size(); i++) {
				ClienteSubTipo clienteSubTipo = (ClienteSubTipo) listTipoSubtipoCliente
						.get(i);
				listClienteSubTipo.add(clienteSubTipo);

				if (clienteSubTipo.getIndicadorPrincipal().intValue() == 1) {
					f.setIndicadorPrincipalSubTipo(String.valueOf(i));
				}

				if (clienteSubTipo.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
					f.setTieneSubTipoClienteConsultora(true);
				}

				if (clienteSubTipo.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_GERENTE)) {
					if ((clienteSubTipo.getCodigoSubTipoCliente().equals(Constants.MAE_SUBTIPO_GERENTE_REGION))
							|| (clienteSubTipo.getCodigoSubTipoCliente().equals(Constants.MAE_SUBTIPO_GERENTE_ZONA)))
						f.setTieneSubTipoClienteGerente(true);
				}

				if (clienteSubTipo.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
					inicializar(f);
					this.addError("Error:",this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.hijaDuplaEncontrada"));
					return null;
				}

				// recuperamos las clasificaciones del TipoSubTipo
				clienteSubTipo.setListClienteClasificacion(clienteService.getListClasificacionCliente(clienteSubTipo.getOid().toString()));

			}
			this.maeClienteSubTipoList = listClienteSubTipo;
			f.setTabSeleccion(Constants.MAE_TAB_NIVEL_RIESGO);
			this.dtmaeClienteSubTipoList = new DataTableModel(this.maeClienteSubTipoList);
			f.setClienteEncontrado(true);
			this.mostrarTab=true;
		} else {
			f.setTabSeleccion(Constants.MAE_TAB_NIVEL_RIESGO);
			this.addError("Error:",this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.clienteNoEncontrado"));
		}
		
		return maeClienteSubTipoList;
	}

	/**
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("onRowSelect");
		}
		ClienteSubTipo clienteSubTipo = (ClienteSubTipo) event.getObject();
		if (clienteSubTipo != null) {
			this.clienteSubTipoSelected= clienteSubTipo;
		}
	}

	/**
	 * @param e
	 */
	public void onRowSelectChck(AjaxBehaviorEvent e) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		DataTable dataTable = (DataTable) context.getViewRoot().findComponent(
				":principalForm:idTabview:datatable");
		SelectBooleanCheckbox selectBooleanCheckbox = (SelectBooleanCheckbox) ((UIOutput) e.getSource());
		boolean valor = (Boolean) selectBooleanCheckbox.getSubmittedValue();

		for (int i = 0; i < maeClienteSubTipoList.size(); i++) {
			if (i == dataTable.getRowIndex()) {
				((ClienteSubTipo) maeClienteSubTipoList.get(i))
						.setIndicadorPrincipal(valor == true ? 1 : 0);
				((ClienteSubTipo) maeClienteSubTipoList.get(i))
						.setPrincipal(valor);
			} else {
				((ClienteSubTipo) maeClienteSubTipoList.get(i))
						.setIndicadorPrincipal(0);
				((ClienteSubTipo) maeClienteSubTipoList.get(i))
						.setPrincipal(!valor);
			}
		}
		this.dtmaeClienteSubTipoList = new DataTableModel(maeClienteSubTipoList);
		
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
		if(this.hijoPopup)
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.clienteSubTipoSelected = null;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda = false;
		this.mostrarTab=false;
		this.activarHotkeyEstandar=false;
		
		MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) formBusqueda;

		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		// Obtenemos los datos del usuario Logueado
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();

		// Asignamos al codigo del periodo el valor por defecto
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());

		// cargamos la lista de nivel de riesgo
		this.maeNivelRiesgoList = clienteService.getNivelesRiesgo(criteria);

		// cargamos los subtipos de cliente
		List subTiposCliente = clienteService
				.getSubTiposClienteInsertar(criteria);
		for (int i = 0; i < subTiposCliente.size(); i++) {
			Base base = (Base) subTiposCliente.get(i);

			StringTokenizer st = new StringTokenizer(base.getCodigo(), "-");
			String codigoTipoCliente = st.nextToken();

			if (codigoTipoCliente.equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
				subTiposCliente.remove(i);
				break;
			}
		}
		this.siccSubTipoClienteList = subTiposCliente;

		f.setListaSubTipoClienteActual(new ArrayList());
		this.maeClienteSubTipoList = f.getListaSubTipoClienteActual();

		inicializar(f);
		f.setCodigoClienteBuscar("");
		f.setTabSeleccion(Constants.MAE_TAB_NIVEL_RIESGO);

		// recuperamos el oid Pais
		String oidPais = clienteService.getOidPais(criteria);
		f.setOidPais(oidPais);

		// para saber si la peticion llega de solicitud de credito
		/*
		 * f.setIndicadorSolicitudCredito(reemplazarNulo(request.getParameter(
		 * "indicadorSolicitudCredito")));
		 * if(f.getIndicadorSolicitudCredito()!=null
		 * &&f.getIndicadorSolicitudCredito().equals(Constants.NUMERO_UNO)){
		 * f.setCodigoClienteBuscar
		 * (request.getParameter("codigoClienteBuscar")); String id =
		 * request.getParameter("id"); f.setValorId(reemplazarNulo(id)); }
		 */
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		f.setGraboOK(false);
		this.msjeGuardar = "";
	}

	/**
	 * inicializa los datos del formulario	
	 */
	private void inicializar(MantenimientoMAEInformacionClienteForm f) {
		f.setCodigoCliente("");
		f.setNombreCliente("");
		f.setOidCliente("");

		f.setCodigoNivelRiesgoActual("");
		f.setDescripcionNivelRiesgoActual("");
		f.setFechaUltimaActualizacion("");
		f.setClienteEncontrado(false);

		f.setIndicadorActividadActual("");
		f.setIndicadorActividadNuevo("");

		f.setIndicadorPrincipalSubTipo("");
		f.setCambioSubTipoCliente(false);
		f.setTieneSubTipoClienteGerente(false);
		f.setTieneSubTipoClienteConsultora(false);
		f.setOidEstatus(null);

		/* INI SA PER-SiCC-2012-0367 */
		f.setNumeroDocumentoIdentidad("");
		f.setValidarEstatusComercial(false);
		f.setMensajeConsultoraRoja("");
		/* FIN SA PER-SiCC-2012-0367 */

		f.setNumeroPeriodosSinPedido(0);
		this.hijoPopup=false;
	}

	/**
	 * Se añade un nuevo registro de tipo cliente en la pantalla	
	 */
	public void addDetalSubTipoCliente(ActionEvent e) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addDetalSubTipoCliente' method");
		}
		MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		f.setTabSeleccion(Constants.MAE_TAB_TIPO_SUBTIPO);

		List detalList = this.maeClienteSubTipoList;
		log.debug("formulario  :  " + f);
		ClienteSubTipo clienteSubTipo = getSubTipoCliente();
		boolean ingresar = true;

		if (!existeSubTipoCliente(clienteSubTipo.getCodigoTipoCliente(),
				clienteSubTipo.getCodigoSubTipoCliente(), detalList)) {

			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_GERENTE))
					&& (!existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
							detalList))) {

				ingresar = false;
				String cadError = clienteSubTipo.getDescripcionTipoCliente();
				this.addError("Error:",	this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validacionGerente",
								new Object[] { cadError }));
			}

			if (ingresar) {
				if (clienteSubTipo.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
					f.setTieneSubTipoClienteConsultora(true);
				}
				if (clienteSubTipo.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_GERENTE)) {
					f.setTieneSubTipoClienteGerente(true);
				}

				if (detalList.size() == 0) {
					clienteSubTipo.setIndicadorPrincipal(new Integer(1));
					f.setIndicadorPrincipalSubTipo("0");
				} else
					clienteSubTipo.setIndicadorPrincipal(new Integer(0));

				log.debug("- seteando lista : "
						+ clienteSubTipo.getDescripcionTipoCliente() + "/"
						+ clienteSubTipo.getDescripcionSubTipoCliente());
				detalList.add(clienteSubTipo);
				log.debug("lista size : " + detalList.size());

				if (clienteSubTipo.getListClienteClasificacion() == null
						|| clienteSubTipo.getListClienteClasificacion().size() == 0) {
					// Agregamos la clasificacion por Defecto relacionado al
					// SubTipoCliente
					String tipoClasificacionDefault = clienteService.getTipoClasificacionDefault(
									clienteSubTipo.getCodigoTipoCliente(),
									clienteSubTipo.getCodigoSubTipoCliente());

					String clasificacionDefault = clienteService.getClasificacionDefault(
									clienteSubTipo.getCodigoTipoCliente(),
									clienteSubTipo.getCodigoSubTipoCliente());

					Map criteria = new HashMap();
					criteria.put("codigoPais", f.getCodigoPais());
					criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
					criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
					LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(criteria);
					// obtenemos el periodo actual
					Long oidPeriodo = new Long(periodos[0].getValue());

					// obtenemos la fecha actual
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String fechaActual = sdf.format(new Date(System
							.currentTimeMillis()));

					// Agregamos la clasificacion x Defecto
					ClienteClasificacion clienteClasificacion = getClasificacionCliente(
							tipoClasificacionDefault, clasificacionDefault);
					clienteClasificacion.setOidPeriodo(oidPeriodo);
					clienteClasificacion.setFechaClasificacion(sdf.parse(fechaActual));
					clienteClasificacion.setIndicadorPrincipal(new Integer(1));
					clienteSubTipo.setListClienteClasificacion(new ArrayList());
					clienteSubTipo.getListClienteClasificacion().add(clienteClasificacion);
				}

				f.setCambioSubTipoCliente(true);
				this.maeClienteSubTipoList = detalList;
				this.dtmaeClienteSubTipoList = new DataTableModel(maeClienteSubTipoList);
			}
		} else {
			String cadError = clienteSubTipo.getDescripcionTipoCliente();
			this.addError("Error:",	this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validacionSubTipoExiste",
							new Object[] { cadError }));
		}

	}
	
	//Se ejecuta antes del eliminar un registro de la grilla
	public void validacionesPrevias(ActionEvent actionEvent) {
		try {		
		MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		if (this.clienteSubTipoSelected != null) {
			if (f.isTieneSubTipoClienteGerente()
					&& StringUtils.equals(this.getSubTipoCliente().getCodigoTipoCliente(), "02")
					&& StringUtils.equals(this.getSubTipoCliente().getCodigoSubTipoCliente(), "06")) {
				this.addError("Error:",this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validacionEliminarConsultoraOficina"));
				return;
			}

			String ventana = externalContext.getRequestParameterMap().get("parametroVentana");
			String ventanaConfirmar = "PF('" + ventana+ "_confirmationDialogConfirmar').show()";
			beanMensaje = this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.eliminarSubTipo1")
					+ " "+ this.getClienteSubTipoSelected().getDescripcionSubTipoCliente()
					+ " "+ this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.eliminarSubTipo2");
			this.getRequestContext().execute(ventanaConfirmar);
		} else {
			this.addWarn("Alerta:",this.getResourceMessage("mantenimientoMAEClienteForm.select.item"));
			return;
		}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo que elimina un registro SubTipoCliente de la lista de
	 * SubTipoCliente ingresados para el nuevo cliente
	 */
	public void deleteDetalSubTipoCliente(ActionEvent e) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDetalSubTipoCliente' method");
		}

		MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
		f.setTabSeleccion(Constants.MAE_TAB_TIPO_SUBTIPO);

		List detalList = this.getMaeClienteSubTipoList();

		BeanPredicate beanPredicate1 = new BeanPredicate(
				"codigoSubTipoCliente", new EqualPredicate(this
						.getClienteSubTipoSelected().getCodigoSubTipoCliente()));
		ClienteSubTipo clienteSubTipo = (ClienteSubTipo) CollectionUtils.find(
				detalList, beanPredicate1);
		// ClienteSubTipo clienteSubTipo =
		// (ClienteSubTipo)detalList.get(Integer.parseInt(id)-1);

		boolean eliminar = true;

		// SI ES GERENTE
		if (clienteSubTipo.getCodigoTipoCliente().equals(
				Constants.MAE_TIPO_CLIENTE_GERENTE)) {
			f.setTieneSubTipoClienteGerente(false);
		}
		// SI ES CONSULTORA
		if (clienteSubTipo.getCodigoTipoCliente().equals(
				Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
			f.setTieneSubTipoClienteConsultora(false);
		}

		if (eliminar) {

			int posicion = 0;
			for (Object object : detalList) {
				if (((ClienteSubTipo) object).getCodigoSubTipoCliente() == clienteSubTipo
						.getCodigoSubTipoCliente()) {
					break;
				}
				posicion++;
			}

			detalList.remove(posicion);
			f.setCambioSubTipoCliente(true);

			if (detalList.size() == 1)
				f.setIndicadorPrincipalSubTipo("0");

			this.setMaeClienteSubTipoList(detalList);
			this.dtmaeClienteSubTipoList = new DataTableModel(this.maeClienteSubTipoList);
		}
	}	

	/**
	 * @param event
	 */
	public void saveAttributes(ActionEvent event) {
		try {

			InterfazSiCCService interfazSicc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) formBusqueda;
			StringTokenizer st = new StringTokenizer(f.getNuevoNivelRiesgo(),
					"-");
			f.setOidNuevoNivelRiesgo(st.nextToken());
			f.setCodigoNuevoNivelRiesgo(st.nextToken());

			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
			Map params = BeanUtils.describe(f);

			// Obtenemos los datos del usuario Logueado
			Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
			params.put("usuario", usuario.getLogin());

			/*
			 * if(!(StringUtils.equals(f.getOidEstatus(),
			 * Constants.NUMERO_SIETE) || StringUtils.equals(f.getOidEstatus(),
			 * Constants.NUMERO_CINCO)) &&
			 * StringUtils.equals(f.getIndicadorActividadNuevo(),
			 * Constants.NUMERO_CERO)) {
			 * messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
			 * "mantenimientoMAEInformacionClienteForm.msg.errorNoRetirada"));
			 * saveErrors(request, messages); return false; }
			 */

			/* INI SA PER-SiCC-2012-0367 */
			if (f.isValidarEstatusComercial()
					&& (!f.getIndicadorActividadActual().equals(
							f.getIndicadorActividadNuevo()))) {
				String host = this.getRequest().getRemoteHost();
				String resultado = validarBoletinComercial(f.getCodigoPais(),
						f.getNumeroDocumentoIdentidad(), host,usuario.getLogin());
				if (resultado.equals(Constants.MAE_WEBSERVICE_RESULTADO_ERROR)) {
					this.addError(
							"Error:",
							this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.errorWebServiceBoletinComercial"));
					return;
				} else { // Si la Consultora es Marcado como Roja
					if (resultado.equals("07") || resultado.equals("08")
							|| resultado.equals("09") || resultado.equals("10")
							|| resultado.equals("11") || resultado.equals("12")) {
						String texto = this
								.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.consultoraRojaBoletinComercial");
						f.setMensajeConsultoraRoja(texto);

						return;
					}
				}
			}
			/* FIN SA PER-SiCC-2012-0367 */
			StringBuilder messages = new StringBuilder();
			boolean bGrabarCab = false;
			List listClienteSubTipo = obtenerListClienteSubTipoUltimo(f);
			params.put("listClienteSubTipo", listClienteSubTipo);
			params.put("indicadorActividad", f.getIndicadorActividadNuevo());

			// obtenemos el oidPeriodo Actual
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(criteria);
			// obtenemos el periodo actual
			params.put("oidPeriodo", new Long(periodos[0].getValue()));

			log.debug("Actualizando Informacion del Cliente...");
			
			Map campañaProceso = new HashMap();
			campañaProceso.put("codigoPais", f.getCodigoPais());
			campañaProceso.put("estadoCampanha", Constants.NRO_CERO);
			campañaProceso.put("indicadorActiva", Constants.ESTADO_ACTIVO);
			Map campañaActiva = interfazSicc.getPeriodoFechaCampanyaActivaSF(campañaProceso);
			params.put("campaniaProceso", campañaActiva.get("periodo"));

			clienteService.updateInformacionCliente(params);				
			messages.append(this.getResourceMessage("mantenimientoMAEInformacionClienteForm.ok", new Object[]{f.getCodigoCliente()}));
			//Si la ventana es llamada desde STO
			if(this.hijoPopup){
				f.setCodigoClienteBuscar(this.codCliente);
				f.setIndicadorSolicitudCredito("1");
				this.msjeModificacion=messages.toString();
				this.setSaveFindBeforeAttributes();
				this.find();
				this.setSaveFindAfterAttributes();
				this.hijoPopup=true;
				this.setMensajeAlertaDefaultAction(this.msjeModificacion);
				RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
				String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
				this.getRequestContext().execute(ventana);
				MantenimientoSTOSolicitudCreditoAction action = findManageBean("mantenimientoSTOSolicitudCreditoAction");
				action.inicializarValores();
							
			}else{
				inicializar(f);
				f.setListaSubTipoClienteActual(new ArrayList());
				this.setMaeClienteSubTipoList(f.getListaSubTipoClienteActual());
				f.setCodigoClienteBuscar("");
				f.setNombreCliente("");
				f.setTabSeleccion(Constants.MAE_TAB_NIVEL_RIESGO);
				bGrabarCab = true;		
				f.setGraboOK(bGrabarCab);
				this.mostrarTab=false;
				if (bGrabarCab)			
					this.addInfo("Info:", messages.toString());
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			this.addError("Error:", this.getResourceMessage("mantenimientoEDULocal.cabecera.error"));
		}
	}

	/**
	 * Invoca el servico web de boletin comercial	
	 */
	private String validarBoletinComercial(String codigoPais, String numeroRUT,
			String host, String codigoUsuario) {
		GenericoService serviceGen = (GenericoService) getBean("genericoService");
		log.debug("validarBoletinComercial,  numeroRUT >>> " + numeroRUT);
		String urlWSOCR = serviceGen.getParametroPais(codigoPais,
				Constants.SISTEMA_OCR, Constants.OCR_URL_WS_SOCCRED);
		Integer port = new Integer(serviceGen.getParametroPais(codigoPais,
				Constants.SISTEMA_OCR, Constants.OCR_URL_PORT_SOCCRED));
		String hostParametro = serviceGen.getParametroPais(codigoPais,
				Constants.SISTEMA_OCR, Constants.OCR_URL_HOST_SOCCRED);

		if (StringUtils.isEmpty(hostParametro))
			hostParametro = host;

		log.debug("host " + hostParametro);
		log.debug("port " + port);

		String serie = "";
		String zona = "";

		IConsultaBelcorpservice locator = new IConsultaBelcorpserviceLocator();
		log.debug("URL ws Boletin electronico >>> " + urlWSOCR);
		String res = null;

		try {
			IConsultaBelcorp service = locator
					.getIConsultaBelcorpPort(new java.net.URL(urlWSOCR));
			log.debug("service ws conectado " + service);
			res = service.getEvaluacionBelcorp(hostParametro, port, numeroRUT,
					serie, zona, codigoUsuario);

		} catch (Exception e) {
			log.debug("error conexion web service " + e.getMessage());
			res = null;
		}

		log.debug("res " + res);
		String resultadoServicio = Constants.MAE_WEBSERVICE_RESULTADO_ERROR;

		if (StringUtils.isNotEmpty(res)) {
			String[] split = StringUtils.split(res, "|");// reporte|montoBic|MontoMic|MontoInfoCom|Estado|Mensaje|Error
			if (split.length == 7)
				resultadoServicio = split[4]; // estado
		}

		return resultadoServicio;
	}

	/**
	 * @param f
	 * @return
	 */
	private List obtenerListClienteSubTipoUltimo(
			MantenimientoMAEInformacionClienteForm f) {
		// SUBTIPOS DEL CLIENTE
		List listSubTipo = this.getMaeClienteSubTipoList();
		List listSubTipoActual = f.getListaSubTipoClienteActual();

		// Actualizamos el indicador Principal
		int indicadorPrincipal = Integer.parseInt(f
				.getIndicadorPrincipalSubTipo());
		for (int i = 0; i < listSubTipo.size(); i++) {
			ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo) listSubTipo
					.get(i);

			if (i == indicadorPrincipal)
				clienteSubTipoAux.setIndicadorPrincipal(new Integer(1));
			else
				clienteSubTipoAux.setIndicadorPrincipal(new Integer(0));
		}

		// Verificamos si el SubTipo de Cliente ya existe
		Integer indicadorPrincipalAux = null;
		Iterator itClienteSubTipo = listSubTipoActual.iterator();
		while (itClienteSubTipo.hasNext()) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) itClienteSubTipo
					.next();
			boolean encontrado = false;

			for (int i = 0; i < listSubTipo.size(); i++) {
				ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo) listSubTipo
						.get(i);

				if ((clienteSubTipo.getCodigoTipoCliente()
						.equals(clienteSubTipoAux.getCodigoTipoCliente()))
						&& (clienteSubTipo.getCodigoSubTipoCliente()
								.equals(clienteSubTipoAux
										.getCodigoSubTipoCliente()))) {
					encontrado = true;
					indicadorPrincipalAux = clienteSubTipoAux
							.getIndicadorPrincipal();
					break;
				}
			}

			if (!encontrado) {
				clienteSubTipo.setEliminar(true);

				// Si el subTipoCliente ha sido eliminado, tambien sera
				// eliminado sus clasificaciones
				Iterator itClasificaciones = clienteSubTipo.getListClienteClasificacion().iterator();
				while (itClasificaciones.hasNext()) {
					ClienteClasificacion clienteClasificacion = (ClienteClasificacion) itClasificaciones.next();
					clienteClasificacion.setEliminar(true);
				}
			} else 
				clienteSubTipo.setIndicadorPrincipal(indicadorPrincipalAux);			
		}

		// Verificamos si el SubTipo de Cliente es nuevo
		itClienteSubTipo = listSubTipo.iterator();
		while (itClienteSubTipo.hasNext()) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) itClienteSubTipo.next();
			boolean encontrado = false;

			for (int i = 0; i < listSubTipoActual.size(); i++) {
				ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo) listSubTipoActual.get(i);

				if ((clienteSubTipo.getCodigoTipoCliente().equals(clienteSubTipoAux.getCodigoTipoCliente()))
						&& (clienteSubTipo.getCodigoSubTipoCliente().equals(clienteSubTipoAux.getCodigoSubTipoCliente()))) {
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				clienteSubTipo.setOidCliente(new Long(f.getOidCliente()));
				listSubTipoActual.add(clienteSubTipo);
			}
		}

		return listSubTipoActual;
	}

	/**
	 * metodo auxiliar que permite recuperar el tipo de cliente agregado por el
	 * usuario y verificamos si existe el tipo/subtipo para el cliente
	 * recuperado al ingresar a esta pantalla se recuperar dicha informacion
	 */
	private ClienteSubTipo getSubTipoCliente() {
		if (log.isDebugEnabled()) {
			log.debug("getSubTipoCliente");
		}
		MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
		String subTipoCliente = f.getSubTipoCliente();

		StringTokenizer st = new StringTokenizer(subTipoCliente, "-");

		ClienteSubTipo clienteSubTipo = new ClienteSubTipo();
		clienteSubTipo.setCodigoTipoCliente(st.nextToken());
		clienteSubTipo.setCodigoSubTipoCliente(st.nextToken());
		clienteSubTipo.setOidTipoCliente(new Long(st.nextToken()));
		clienteSubTipo.setOidSubTipoCliente(new Long(st.nextToken()));

		StringTokenizer st2 = new StringTokenizer(st.nextToken(), "/");
		clienteSubTipo.setDescripcionTipoCliente(st2.nextToken());

		if (st2.hasMoreTokens())
			clienteSubTipo.setDescripcionSubTipoCliente(st2.nextToken());

		Iterator itClienteSubTipo = f.getListaSubTipoClienteActual().iterator();
		while (itClienteSubTipo.hasNext()) {
			ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo) itClienteSubTipo
					.next();

			if ((clienteSubTipoAux.getCodigoTipoCliente().equals(clienteSubTipo
					.getCodigoTipoCliente()))
					&& (clienteSubTipoAux.getCodigoSubTipoCliente()
							.equals(clienteSubTipo.getCodigoSubTipoCliente()))) {
				clienteSubTipo = clienteSubTipoAux;
				break;
			}
		}

		return clienteSubTipo;
	}

	/**
	 * Se valida si ya se agrego un SubTipo Cliente	
	 */
	private boolean existeSubTipoCliente(String codigoTipoCliente,
			String codigoSubTipoCliente, List detalList) {
		Iterator it = detalList.iterator();

		while (it.hasNext()) {
			ClienteSubTipo ccd = (ClienteSubTipo) it.next();

			if (ccd.getCodigoTipoCliente().equals(codigoTipoCliente)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param tipoClasificacion
	 * @param clasificacion
	 * @return
	 */
	private ClienteClasificacion getClasificacionCliente(
			String tipoClasificacion, String clasificacion) {
		StringTokenizer st = new StringTokenizer(tipoClasificacion, "-");

		ClienteClasificacion clienteClasificacion = new ClienteClasificacion();
		clienteClasificacion.setOidClienteSubTipo(new Long(st.nextToken()));
		clienteClasificacion.setOidTipoClasificacion(new Long(st.nextToken()));
		clienteClasificacion.setDescripcionTipoClasificacion(st.nextToken());

		StringTokenizer st2 = new StringTokenizer(clasificacion, "-");
		clienteClasificacion.setOidClasificacion(new Long(st2.nextToken()));
		clienteClasificacion.setDescripcionClasificacion(st2.nextToken());

		return clienteClasificacion;
	}

	/**
	 * @param e
	 */
	public void cambiaSubTipoCliente(ValueChangeEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("cambiaSubTipoCliente");
		}
		MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
		log.debug(f.getSubTipoCliente());
	}

	/**
	 * @param event
	 */
	public void onTabChange(TabChangeEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("onTabChange");
		}
		MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
		log.debug(event.getTab().getId());
		// maeClienteSubTipoList
		if (StringUtils.equals(event.getTab().getId(),
				Constants.MAE_TAB_TIPO_SUBTIPO)) {
			if (this.siccSubTipoClienteList.size() > 0
					&& this.siccSubTipoClienteList != null) {
				String valor = ((Base) this.siccSubTipoClienteList.get(0))
						.getCodigo();
				if (StringUtils.isNotEmpty(valor)
						|| StringUtils.isNotBlank(valor)) {
					f.setSubTipoCliente(valor);
				}
			}
		}
	}

	// metodo que sale del popup
	public void salirAPantallaPadre(ActionEvent event) {
		try {
			this.redireccionarPagina(this.paginaPadre);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * Colocar nuevo valor  OidNuevoNivelRiesgo
	 * @param val
	 */
	public void cambiarValores(ValueChangeEvent val) {
		
		try {
			String valor = val.getNewValue().toString();
			MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
			f.setOidNuevoNivelRiesgo(valor);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
		
	}
	
	//Realiza las validaciones previas ntes de guardar los datos modificados
	public void validaSave(ActionEvent event) {
		try {
			MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			
			if (!f.isClienteEncontrado()) {
				this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validacionclienteNoEncontrado"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			if (!validarSubTipoCliente())
				return;

			// Verificamos si modifico Nivel de Riesgo y de Actividad
			String[] valores = new String[3];
			valores = f.getNuevoNivelRiesgo().split("-");
			String oidNivelRiesgoNuevo = valores[0];
			String mensajeCambios = "";
			if (!StringUtils.equals(f.getOidNivelRiesgoActual(),oidNivelRiesgoNuevo)) {
				mensajeCambios = "Nivel de Riesgo";
			}

			// Validamos si ha realizado cambios del indicador Principal
			String encontradoIndicador = "";
			for (int i = 0; i < this.maeClienteSubTipoList.size(); i++) {
				ClienteSubTipo clienteTipo = new ClienteSubTipo();
				clienteTipo = (ClienteSubTipo) this.maeClienteSubTipoList.get(i);
				if (clienteTipo.isPrincipal())
					encontradoIndicador = String.valueOf(i);
			}

			if (!StringUtils.equals(f.getIndicadorPrincipalSubTipo(),
					encontradoIndicador)) {
				f.setCambioSubTipoCliente(true);
				f.setIndicadorPrincipalSubTipo(encontradoIndicador);
			}

			if (f.isCambioSubTipoCliente()) {
				if (StringUtils.isBlank(mensajeCambios))
					mensajeCambios = "Tipo y Subtipo";
				else
					mensajeCambios = mensajeCambios + ", Tipo y Subtipo";
			}

			if (!StringUtils.equals(f.getIndicadorActividadActual(),
					f.getIndicadorActividadNuevo())) {
				if (StringUtils.isBlank(mensajeCambios))
					mensajeCambios = "Estado de Actividad";
				else
					mensajeCambios = mensajeCambios + ", Estado de Actividad";
			}

			/*
			if (StringUtils.isNotBlank(mensajeCambios)) {
				String ventana = externalContext.getRequestParameterMap().get("parametroVentana");
				String ventanaConfirmar = "PF('" + ventana+ "_confirmationDialogConfirmar').show()";
				this.msjeGuardar = this	.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validacionCambios1")
						+ mensajeCambios+ this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validacionCambios2");
				this.getRequestContext().execute(ventanaConfirmar);
			} else {
				String ventana = externalContext.getRequestParameterMap().get("parametroVentana");
				String ventanaConfirmar = "PF('" + ventana+ "_confirmationDialogConfirmar').show()";
				this.msjeGuardar = this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validacionSinCambios");
				this.getRequestContext().execute(ventanaConfirmar);
			}
			*/
			this.saveAttributes(event);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	//Valida el Sub tipo del Cliente antes de Guardar los  cambios
	public boolean validarSubTipoCliente() {
		MantenimientoMAEInformacionClienteForm f = (MantenimientoMAEInformacionClienteForm) this.formBusqueda;
		
		if (this.maeClienteSubTipoList== null || this.maeClienteSubTipoList.size()==0) {
			this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validaSubTipo"));
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return false;
		}

		//String codigoTipoCliente = this.principalSubtipoCliente.getCodigoTipoCliente();
		//String codigoSubTipoCliente = this.principalSubtipoCliente.getCodigoSubTipoCliente();
		
		
		// Gerente
		if (f.isTieneSubTipoClienteGerente()) {
			boolean esValidoSubTipoClienteGerente = false;
			for(int i=0;i<this.maeClienteSubTipoList.size();i++){
				ClienteSubTipo cliente= (ClienteSubTipo)this.maeClienteSubTipoList.get(i);
				String codigo=cliente.getCodigoTipoCliente();
				String subCodigo=cliente.getCodigoSubTipoCliente();
				if (StringUtils.equals(codigo, "02")	&& StringUtils.equals(subCodigo, "06"))
					esValidoSubTipoClienteGerente = true;
			}			

			if (!esValidoSubTipoClienteGerente) {
				this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validacionConsultoraOficina"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return false;
			}
		}

		
		// Consultora
		if (f.isTieneSubTipoClienteConsultora()) {
			boolean esValidoSubTipoClienteConsultora = true;			
			for(int i=0;i<this.maeClienteSubTipoList.size();i++){
				ClienteSubTipo cliente= (ClienteSubTipo)this.maeClienteSubTipoList.get(i);
				String codigo=cliente.getCodigoTipoCliente();
				if (!StringUtils.equals(codigo, "02") && !StringUtils.equals(codigo, "04"))
					esValidoSubTipoClienteConsultora = false;
			}	
			
			if (!esValidoSubTipoClienteConsultora) {
				this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validacionOtrosTiposCliente"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return false;
			}
		}
		

		// Valida El campom Principal
		boolean encontrado = false;
		for (int i = 0; i < this.maeClienteSubTipoList.size(); i++) {
			ClienteSubTipo clienteTipo = new ClienteSubTipo();
			clienteTipo = (ClienteSubTipo) this.maeClienteSubTipoList.get(i);
			if (clienteTipo.isPrincipal()) {
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoMAEInformacionClienteForm.msg.validaSubTipoPrincipal"));
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return false;
		}

		return true;
	}
	
	
	protected String devuelveMensajeKeySaveOK() {
		if(this.hijoPopup)
			return this.msjeModificacion;
		else
			return "";
					
	}

	/**
	 * @return
	 */
	public List getMaeNivelRiesgoList() {
		return maeNivelRiesgoList;
	}

	/**
	 * @param maeNivelRiesgoList
	 */
	public void setMaeNivelRiesgoList(List maeNivelRiesgoList) {
		this.maeNivelRiesgoList = maeNivelRiesgoList;
	}

	/**
	 * @return
	 */
	public List getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList
	 */
	public void setSiccSubTipoClienteList(List siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	/**
	 * @return
	 */
	public List getMaeClienteSubTipoList() {
		return maeClienteSubTipoList;
	}

	/**
	 * @param maeClienteSubTipoList
	 */
	public void setMaeClienteSubTipoList(List maeClienteSubTipoList) {
		this.maeClienteSubTipoList = maeClienteSubTipoList;
	}

	/**
	 * @return
	 */
	public BusquedaHIPClientePOPUPSearchAction getBusquedaHIPClientePOPUPSearchAction() {
		return busquedaHIPClientePOPUPSearchAction;
	}

	/**
	 * @param busquedaHIPClientePOPUPSearchAction
	 */
	public void setBusquedaHIPClientePOPUPSearchAction(
			BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction) {
		this.busquedaHIPClientePOPUPSearchAction = busquedaHIPClientePOPUPSearchAction;
	}

	/**
	 * @return
	 */
	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	/**
	 * @param mostrarPopUpCliente
	 */
	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}

	/**
	 * @return
	 */
	public ClienteSubTipo getClienteSubTipoSelected() {
		return clienteSubTipoSelected;
	}

	/**
	 * @param clienteSubTipoSelected
	 */
	public void setClienteSubTipoSelected(ClienteSubTipo clienteSubTipoSelected) {
		this.clienteSubTipoSelected = clienteSubTipoSelected;
	}

	/**
	 * @return
	 */
	public DataTableModel getDtmaeClienteSubTipoList() {
		return dtmaeClienteSubTipoList;
	}

	/**
	 * @param dtmaeClienteSubTipoList
	 */
	public void setDtmaeClienteSubTipoList(
			DataTableModel dtmaeClienteSubTipoList) {
		this.dtmaeClienteSubTipoList = dtmaeClienteSubTipoList;
	}

	/**
	 * @return
	 */
	public String getBeanMensaje() {
		return beanMensaje;
	}

	/**
	 * @param beanMensaje
	 */
	public void setBeanMensaje(String beanMensaje) {
		this.beanMensaje = beanMensaje;
	}

	/**
	 * @return
	 */
	public String getPaginaPadre() {
		return paginaPadre;
	}

	/**
	 * @param paginaPadre
	 */
	public void setPaginaPadre(String paginaPadre) {
		this.paginaPadre = paginaPadre;
	}

	/**
	 * @return
	 */
	public String getMsjeGuardar() {
		return msjeGuardar;
	}

	/**
	 * @param msjeGuardar
	 */
	public void setMsjeGuardar(String msjeGuardar) {
		this.msjeGuardar = msjeGuardar;
	}

	/**
	 * @return the nuevoNivelRiesgo
	 */
	public String getNuevoNivelRiesgo() {
		return nuevoNivelRiesgo;
	}

	/**
	 * @param nuevoNivelRiesgo the nuevoNivelRiesgo to set
	 */
	public void setNuevoNivelRiesgo(String nuevoNivelRiesgo) {
		this.nuevoNivelRiesgo = nuevoNivelRiesgo;
	}

	/**
	 * @return the objetSeleccionado
	 */
	public MantenimientoFLXConsultoraForm getObjetSeleccionado() {
		return objetSeleccionado;
	}

	/**
	 * @param objetSeleccionado the objetSeleccionado to set
	 */
	public void setObjetSeleccionado(
			MantenimientoFLXConsultoraForm objetSeleccionado) {
		this.objetSeleccionado = objetSeleccionado;
	}

	/**
	 * @return the popupClientes
	 */
	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}

	public boolean isMostrarTab() {
		return mostrarTab;
	}

	public void setMostrarTab(boolean mostrarTab) {
		this.mostrarTab = mostrarTab;
	}

	public boolean isHijoPopup() {
		return hijoPopup;
	}

	public void setHijoPopup(boolean hijoPopup) {
		this.hijoPopup = hijoPopup;
	}

	public String getMsjeModificacion() {
		return msjeModificacion;
	}

	public void setMsjeModificacion(String msjeModificacion) {
		this.msjeModificacion = msjeModificacion;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	
	
}

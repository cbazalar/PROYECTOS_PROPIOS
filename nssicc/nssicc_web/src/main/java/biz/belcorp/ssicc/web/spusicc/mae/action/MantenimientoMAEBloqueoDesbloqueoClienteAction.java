package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEBloqueoDesbloqueoClienteForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEBloqueoDesbloqueoClienteSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.action.MantenimientoSTOSolicitudCreditoAction;

@ManagedBean
@SessionScoped
public class MantenimientoMAEBloqueoDesbloqueoClienteAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3023785073362455267L;
	private String codigoPais;
	private String oidPais;
	private List siccTipoDocumentoList;
	private String mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear;
	private String mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear;
	private int longitudCampoClientes;
	private List siccSubTipoClienteList;
	private String indicadorCompletarCerosNumDocumento;
	private List mantenimientoMAEBloqueoDesbloqueoClienteList;
	private boolean indicadorBuscar = false;
	private List mantenimientoMAEBloqueoDesbloqueoClienteTipoBloqueoList;
	private List mantenimientoMAEBloqueoDesbloqueoClienteTipoAreaList;
	private List mantenimientoMAEBloqueoDesbloqueoLogBloqueoList;
	private List mantenimientoMAEBloqueoDesbloqueoLogDesbloqueoList;
	private DataTableModel listaConsultaBloqueo;
	private DataTableModel listaConsultaDesbloqueo;
	private boolean indicador = false;

	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "POPUP_CLIENTES";
	private String mensajeConfirmacion;
	private String mensajeAlerta;
	private String bloqueo;

	@ManagedProperty(value = "#{busquedaConsultorasAction}")
	private BusquedaConsultorasAction busquedaConsultorasAction;
	
	private boolean ocultarInfo;

	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_CLIENTES)) {
			this.mostrarPopUpCliente = true;
		}
	}

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {

		if (accion.equals(this.POPUP_CLIENTES)) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map) this.busquedaConsultorasAction
						.getBeanRegistroSeleccionado();

				MantenimientoMAEBloqueoDesbloqueoClienteSearchForm f = (MantenimientoMAEBloqueoDesbloqueoClienteSearchForm) this.formBusqueda;

				f.setCodigoCliente((String) clienteHipMap.get("codigoCliente"));
				this.busquedaConsultorasAction
						.setBeanRegistroSeleccionado(null);
			}
		}

	}

	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopUpCliente = false;
		this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
	}

	@Override
	protected String getSalirForward() {
		if(this.ocultarInfo){
			try {
				MantenimientoSTOSolicitudCreditoAction action = findManageBean("mantenimientoSTOSolicitudCreditoAction");
				action.inicializarValores();
				return "mantenimientoSTOSolicitudCreditoForm";
			} catch (Exception e) {
				return "";
			}				
		}else
			return "mantenimientoMAEBloqueoDesbloqueoClienteList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoMAEBloqueoDesbloqueoClienteForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {

		MantenimientoMAEBloqueoDesbloqueoClienteSearchForm search = new MantenimientoMAEBloqueoDesbloqueoClienteSearchForm();
		return search;
	}

	@Override
	protected List setFindAttributes() throws Exception {

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		MantenimientoMAEBloqueoDesbloqueoClienteSearchForm searchForm = (MantenimientoMAEBloqueoDesbloqueoClienteSearchForm) this.formBusqueda;

		searchForm.setCodigoUsuario(usuario.getCodigo());

		Map criteria = BeanUtils.describe(searchForm);

		int aux = validaConsultoraOnEnter();
		if (aux == 1) {

			String mensaje = "Codigo de Consultora no existe, por favor ingrese un codigo valido para hacer la busqueda";
			this.addError("Error : ", mensaje);
			return null;
		} else {
			if (StringUtils.isNotBlank(searchForm.getCodigoCliente())) {
				criteria.put("codigoCliente", searchForm.getCodigoCliente());
			} else {
				searchForm.setNombreCliente(null);
			}

			if (StringUtils.isNotBlank(searchForm.getApellido1())) {
				criteria.put("apellido1", searchForm.getApellido1());
			}

			if (StringUtils.isNotBlank(searchForm.getApellido2())) {
				criteria.put("apellido2", searchForm.getApellido2());
			}

			if (StringUtils.isNotBlank(searchForm.getNombre1())) {
				criteria.put("nombre1", searchForm.getNombre1());
			}

			if (StringUtils
					.isNotBlank(searchForm.getNumeroDocumentoIdentidad())) {
				criteria.put("numeroDocumentoIdentidad",
						"%" + searchForm.getNumeroDocumentoIdentidad());
			}

			if (StringUtils.isNotBlank(searchForm.getSubTipoCliente())) {
				String subtipo = searchForm.getSubTipoCliente();
				String[] split = StringUtils.split(subtipo, "-");
				String oidTipoCliente = (StringUtils.isNotEmpty(subtipo) ? split[2]
						: "");
				String oidSubTipoCliente = (StringUtils.isNotEmpty(subtipo) ? split[3]
						: "");
				criteria.put("oidTipoCliente", oidTipoCliente);
				criteria.put("oidSubTipoCliente", oidSubTipoCliente);
			}

			if (this.log.isDebugEnabled()) {
				this.log.debug(criteria.toString());
			}

			List resultado = clienteService
					.getClientesBloqueoDesbloqueoByCriteria(criteria);
			this.mantenimientoMAEBloqueoDesbloqueoClienteList = resultado;

			if (resultado != null && resultado.size() == 0) {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEBloqueoDesbloqueoClienteList.notfound"));
			}

			searchForm.setGraboOK(false);
			if (this.indicadorBuscar) {
				searchForm.setGraboOK(true);
			}

			return resultado;
		}
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'guardar' method");
		}
		this.indicadorBuscar = true;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		MantenimientoMAEBloqueoDesbloqueoClienteForm editForm = (MantenimientoMAEBloqueoDesbloqueoClienteForm) this.formMantenimiento;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		if (editForm.getBloqueoForm()) {
			if (log.isDebugEnabled())
				log.debug("Bloqueando al cliente");

			// Validamos al cliente antes de bloquearlo.
			List bloqueos = clienteService.getBloqueosClienteByTipoBloqueo(
					editForm.getOidCliente(), editForm.getTipoBloqueo());

			// VALIDACION DE GRUPOS DE PROCESOS
			Map criteria = new HashMap();
			criteria.put("codigoPais", editForm.getCodigoPais());
			criteria.put("codigoSistema", Constants.MAE_CODIGO_SISTEMA);
			criteria.put("nombreParametro",
					Constants.MAE_NOMBRE_PARAMETRO_PRE_FACTURACION);
			criteria.put("codigoCliente", editForm.getCodigoCliente());
			InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			criteria.put("codigoPeriodo", service.getPeriodoDefaultByPaisCanal(
					editForm.getCodigoPais(), Constants.CODIGO_CANAL_DEFAULT));

			if (bloqueos == null || bloqueos.size() == 0) {
				if (!clienteService.validaClientePedido(criteria)) {
					// Registramos el bloqueo
					Map params = BeanUtils.describe(editForm);
					params.put("usuarioBloqueo", usuario.getLogin());
					clienteService.insertBloqueoCliente(params);					
					return true;
				} else {

					this.addError("Mensaje",this.getResourceMessage("mantenimientoMAEBloqueoDesbloqueoClienteForm.grupoProcesos"));

					return false;
				}

			} else {
				// No se registra el bloqueo
				Map bloqueo = (HashMap) bloqueos.get(0);

				// messages.add(ActionErrors.GLOBAL_MESSAGE, new
				// ActionMessage("mantenimientoMAEBloqueoDesbloqueoClienteForm.already.exists",
				// MapUtils.getString(bloqueo, "tipoBloqueo"),
				// MapUtils.getString(bloqueo, "usuarioBloqueo"),
				// MapUtils.getString(bloqueo, "fechaBloqueo")
				// ));
				addError(
						"Mensaje",
						getResourceMessage(
								"mantenimientoMAEBloqueoDesbloqueoClienteForm.already.exists",
								new Object[] {
										MapUtils.getString(bloqueo,
												"tipoBloqueo"),
										MapUtils.getString(bloqueo,
												"usuarioBloqueo"),
										MapUtils.getString(bloqueo,
												"fechaBloqueo") }));

				return false;
			}
		} else {
			if (log.isDebugEnabled())
				log.debug("Desbloqueando al cliente");

			// Valida que no tenga fecha de desbloqueo, si tiene es que ya fue
			// desbloqueado.
			if (StringUtils.isBlank(editForm.getFechaDesbloqueo())) {

				if (this.indicador)
					editForm.setIndicadorDesbloqueo("1");
				Map params = BeanUtils.describe(editForm);
				params.put("usuarioDesbloqueo", usuario.getLogin());

				clienteService.updateBloqueoCliente(params);
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEBloqueoDesbloqueoClienteForm.unlocked"));

				return true;
			} else {
				// Ya fue desbloqueado
				// messages.add(ActionErrors.GLOBAL_MESSAGE, new
				// ActionMessage("mantenimientoMAEBloqueoDesbloqueoClienteForm.already.unlocked",
				// editForm.getTipoBloqueo(),
				// editForm.getUsuarioDesbloqueo(),
				// editForm.getFechaDesbloqueo()
				// ));
				addError(
						"Error",
						getResourceMessage(
								"mantenimientoMAEBloqueoDesbloqueoClienteForm.already.unlocked",
								new Object[] { editForm.getTipoBloqueo(),
										editForm.getUsuarioDesbloqueo(),
										editForm.getFechaDesbloqueo() }));

				return false;
			}

		}

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.cargarValoresPorDefecto();
		this.ocultarInfo=false;
		this.activarVentanaConfirmacionSave = false;

	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMAEBloqueoDesbloqueoClienteForm editForm = (MantenimientoMAEBloqueoDesbloqueoClienteForm) this.formMantenimiento;
		boolean isNew = editForm.isNewRecord();
		String resultado="";
		if (isNew) {
			 if(this.bloqueo.equals("1"))
				 resultado="mantenimientoMAEBloqueoDesbloqueoClienteForm.added";
			 if(this.bloqueo.equals("0"))
				 resultado= "mantenimientoMAEBloqueoDesbloqueoClienteForm.unlocked";
		} else {
			resultado= "";
		}
		
		return resultado;
	}

	public void bloquear(ActionEvent actionEvent) throws Exception {
		if (!this.verificarRegistroSeleccionado()) {
			this.getResourceMessage("errors.select.item");
			return;
		}

		if (log.isDebugEnabled()) {
			log.debug("Entering 'bloquear' method");
		}
		this.bloqueo="1";
		this.mostrarBotonSave=true;
		MantenimientoMAEBloqueoDesbloqueoClienteForm editForm = new MantenimientoMAEBloqueoDesbloqueoClienteForm();
		editForm.setBloqueoForm(true);

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		// Lista de tipos de bloqueo
		this.mantenimientoMAEBloqueoDesbloqueoClienteTipoBloqueoList = clienteService
				.getAccesosTiposBloqueoByUsuario(usuario.getCodigo());
		this.mantenimientoMAEBloqueoDesbloqueoClienteTipoAreaList = clienteService
				.getAccesosTiposAreaByPais(pais.getCodigo());

		// Datos del cliente
		Map cliente = (Map) this.beanRegistroSeleccionado;
		// Cliente clie = new Cliente();
		// clie.setCodigo(cliente.get("codigoCliente").toString());

		String codigoCliente = cliente.get("codigoCliente").toString();
		if (codigoCliente != null) {
			if (this.log.isDebugEnabled()) {
				this.log.debug("Id seleccionado de la lista: " + codigoCliente);
			}

			// id = oidBloqueo|codigoCliente|bloqueado|oidtipobloqueo

			Cliente cliente2 = new Cliente();
			cliente2 = clienteService.getDatosBasicosCliente(
					usuario.getCodigoPais(), codigoCliente);
			String nombre = cliente2.getApellido1();
			editForm.setCodigoPais(pais.getCodigo());
			editForm.setCodigoCliente(cliente2.getCodigo());
			editForm.setOidCliente(cliente2.getOid());
			editForm.setTipoBloqueo("");
			editForm.setMotivoBloqueo("");
			editForm.setObservacionesBloqueo("");
			editForm.setCodigoArea("");
			editForm.setDescripcionArea("");
			editForm.setIndicadorDesbloqueo("");
			editForm.setDesIndicadorDesbloqueo("");
			editForm.setObservacionesDesbloqueo(Constants.NO);

			if (!StringUtils.isBlank(cliente2.getApellido2()))
				nombre = nombre + " " + cliente2.getApellido2();

			nombre = nombre + " " + cliente2.getNombre1();

			if (!StringUtils.isBlank(cliente2.getNombre2()))
				nombre = nombre + " " + cliente2.getNombre2();

			editForm.setNombreCliente(nombre);
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		}
		formMantenimiento = editForm;
		if(this.ocultarInfo){
			this.mostrarBotonSalir=false;
			this.redireccionarPagina("mantenimientoMAEBloqueoDesbloqueoClienteFormSTO");
		}		
		else
			this.redireccionarPagina("mantenimientoMAEBloqueoDesbloqueoClienteForm");
	}

	public void desbloquear(ActionEvent actionEvent) throws Exception {
		if (!this.verificarRegistroSeleccionado()) {
			this.getResourceMessage("errors.select.item");
			return;
		}

		if (log.isDebugEnabled()) {
			log.debug("Entering 'desbloquear' method");
		}
		this.bloqueo="0";
		this.mostrarBotonSave=true;

		MantenimientoMAEBloqueoDesbloqueoClienteForm editForm = new MantenimientoMAEBloqueoDesbloqueoClienteForm();
		editForm.setBloqueoForm(false);
		this.indicador=false;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		// Datos del cliente
		Map cliente = (Map) this.beanRegistroSeleccionado;
		String id = cliente.get("codigoCliente").toString();
		if (!StringUtils.isBlank(id)) {
			if (this.log.isDebugEnabled()) {
				this.log.debug("Id seleccionado de la lista: " + id);
			}
			String oidBloqueo = "";
			String bloqueado = "";
			String oidTipoBloqueo = "";
			if (cliente.get("oidBloqueo") == null) {
				oidBloqueo = "";
			} else {
				oidBloqueo = cliente.get("oidBloqueo").toString();
			}
			if (cliente.get("bloqueado") == null) {
				bloqueado = "";
			} else {
				bloqueado = cliente.get("bloqueado").toString();
			}
			if (cliente.get("oidTipoBloqueo") == null) {
				oidTipoBloqueo = "";
			} else {
				oidTipoBloqueo = cliente.get("oidTipoBloqueo").toString();
			}

			// Validamos que sea un cliente bloqueado
			if (StringUtils
					.equals(bloqueado,
							Constants.MANTENIMIENTO_MAE_BLOQUEO_DESBLOQUEO_BLOQUEADO_SI)) {
				// Verificamos que el cliente tenga autorizacion para
				// desbloquear.
				List accesos = clienteService
						.getAccesosTiposDesbloqueoByUsuario(
								usuario.getCodigo(), oidTipoBloqueo);

				if (accesos != null && accesos.size() > 0) {
					// Tiene permiso, cargamos los datos del cliente
					Map bloqueo = clienteService.getBloqueoCliente(oidBloqueo);

					if (bloqueo != null) {
						editForm.setCodigoPais(usuario.getCodigoPais());
						editForm.setOidBloqueo(MapUtils.getLong(bloqueo,
								"oidBloqueo"));
						editForm.setCodigoCliente(MapUtils.getString(bloqueo,
								"codigoCliente"));
						editForm.setNombreCliente(MapUtils.getString(bloqueo,
								"nombre"));
						editForm.setOidCliente(MapUtils.getLong(bloqueo,
								"oidCliente"));
						editForm.setTipoBloqueo(MapUtils.getString(bloqueo,
								"tipoBloqueo"));
						editForm.setMotivoBloqueo(MapUtils.getString(bloqueo,
								"motivoBloqueo"));
						editForm.setObservacionesBloqueo(MapUtils.getString(
								bloqueo, "observacionesBloqueo"));
						editForm.setFechaDesbloqueo(MapUtils.getString(bloqueo,
								"fechaDesbloqueo"));
						editForm.setCodigoArea(MapUtils.getString(bloqueo,
								"codigoArea"));
						editForm.setDescripcionArea(MapUtils.getString(bloqueo,
								"descripcionArea"));
						editForm.setIndicadorDesbloqueo(MapUtils.getString(
								bloqueo, "indicadorDesbloqueo"));
						editForm.setDesIndicadorDesbloqueo(MapUtils.getString(
								bloqueo, "desIndicadorDesbloqueo"));
						editForm.setObservacionesDesbloqueo("");
						this.listaBusqueda = this.setFindAttributes();

						this.datatableBusqueda = new DataTableModel(
								this.listaBusqueda);
					}
				} else {
					// No tiene permiso
					this.addError(
							"",
							getResourceMessage("mantenimientoMAEBloqueoDesbloqueoClienteForm.sin.permiso"));
				}
			} else {
				// Cliente no esta bloqueado
				this.addError(
						"Error",
						getResourceMessage("mantenimientoMAEBloqueoDesbloqueoClienteForm.no.bloqueado"));
			}
		}
		this.formMantenimiento = editForm;
		if(this.ocultarInfo){
			this.mostrarBotonSalir=false;
			this.redireccionarPagina("mantenimientoMAEBloqueoDesbloqueoClienteFormSTO");
		}
			
		else
			this.redireccionarPagina("mantenimientoMAEBloqueoDesbloqueoClienteForm");
	}

	public void Consultar(ActionEvent actionEvent) throws Exception {
		if (!this.verificarRegistroSeleccionado()) {
			this.getResourceMessage("errors.select.item");
			return;
		}
		this.mostrarBotonSave = false;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		MantenimientoMAEBloqueoDesbloqueoClienteForm editForm = new MantenimientoMAEBloqueoDesbloqueoClienteForm();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Datos del cliente
		Map cliente = (Map) this.beanRegistroSeleccionado;
		// Cliente clie = new Cliente();
		// clie.setCodigo(cliente.getCodigo());

		String id = cliente.get("codigoCliente").toString();
		if (id != null) {
			if (this.log.isDebugEnabled()) {
				this.log.debug("Id seleccionado de la lista: " + id);
			}

			// id = oidBloqueo|codigoCliente|bloqueado|oidtipobloqueo
			// String []codigos = StringUtils.splitPreserveAllTokens(id, '|');
			// String codigoCliente = codigos[1];

			Cliente cliente2 = clienteService.getDatosBasicosCliente(usuario
					.getCodigoPais(), cliente.get("codigoCliente").toString());
			String nombre = cliente2.getApellido1();
			editForm.setCodigoPais(cliente2.getCodigoPais());
			editForm.setCodigoCliente(cliente2.getCodigo());

			if (!StringUtils.isBlank(cliente2.getApellido2()))
				nombre = nombre + " " + cliente2.getApellido2();

			nombre = nombre + " " + cliente2.getNombre1();

			if (!StringUtils.isBlank(cliente2.getNombre2()))
				nombre = nombre + " " + cliente2.getNombre2();

			editForm.setNombreCliente(nombre);

			// Cargamos las listas de bloqueos y desbloqueos
			this.mantenimientoMAEBloqueoDesbloqueoLogBloqueoList = clienteService
					.getLogBloqueosCliente(cliente2.getOid());
			this.mantenimientoMAEBloqueoDesbloqueoLogDesbloqueoList = clienteService
					.getLogDesbloqueosCliente(cliente2.getOid());

			listaConsultaBloqueo = new DataTableModel(
					mantenimientoMAEBloqueoDesbloqueoLogBloqueoList);
			listaConsultaDesbloqueo = new DataTableModel(
					mantenimientoMAEBloqueoDesbloqueoLogDesbloqueoList);

		}

		formMantenimiento = editForm;
		this.redireccionarPagina("mantenimientoMAEBloqueoDesbloqueoClienteConsultaForm");
	}

	public void executeBloqueoInformacion(ActionEvent actionEvent)
			throws Exception {

		if (log.isDebugEnabled())
			log.debug("Entering 'informacion bloqueo cliente' method");
		MantenimientoMAEBloqueoDesbloqueoClienteSearchForm searchForm = (MantenimientoMAEBloqueoDesbloqueoClienteSearchForm) this.formBusqueda;

		searchForm.setGraboOK(false);

	}

	public void viewParaBloqueosDesbloqueos(ActionEvent actionEvent)
			throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewParaBloqueosDesbloqueos' method");
		}

		this.cargarValoresPorDefecto();

	}

	public void viewBloqueosDesbloqueos() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewBloqueosDesbloqueos' method");
		}

		// Removemos el form bean

		// Removemos la lista de clientes

		setViewAtributes();
	}

	public void limpiara() {
		MantenimientoMAEBloqueoDesbloqueoClienteSearchForm search = new MantenimientoMAEBloqueoDesbloqueoClienteSearchForm();
		search.setCodigoCliente("");
		search.setNombreCliente("");
		search.setApellido1("");
		search.setApellido2("");
		search.setNombre1("");
		search.setNombre2("");
		search.setCodigoTipoDocumentoIdentidad("");
		search.setNumeroDocumentoIdentidad("");
		search.setSubTipoCliente("");
		this.mantenimientoMAEBloqueoDesbloqueoClienteList = new ArrayList();
	}

	/**
	 * @param request
	 */
	private void cargarValoresPorDefecto() {

		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.limpiara();

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		String oidPais = clienteService.getOidPais(criteria);

		criteria.put("oidPais", oidPais);

		this.siccTipoDocumentoList = clienteService
				.getTiposDocumentoIdentidad(criteria);

		// Cargamos los flags de bloqueo y desbloqueo, para mostrar u ocultar
		// los botones

		// Lista de tipos de bloqueo, para controlar el boton BLOQUEAR
		List listaBloqueos1 = clienteService
				.getAccesosTiposBloqueoByUsuario(usuario.getCodigo());
		this.mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear = Constants.NO;
		if (listaBloqueos1 != null && listaBloqueos1.size() > 0)
			this.mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear = Constants.SI;

		// Lista de tipos de bloqueo, para controlar el boton DESBLOQUEAR
		List listaBloqueos2 = clienteService
				.getAccesosTiposDesbloqueoByUsuario(usuario.getCodigo());

		this.mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear = Constants.NO;
		if (listaBloqueos2 != null && listaBloqueos2.size() > 0)
			this.mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear = Constants.SI;

		//

		// Longitud del campo consultora
		ClienteUAGenerarService clienteService2 = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService2
				.getTamanhoNumeroCliente(pais.getCodigo());

		// Lista de tipo subtipo cliente
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		List subTiposCliente = clienteService
				.getSubTiposClienteInsertar(criteria);
		this.siccSubTipoClienteList = subTiposCliente;

		String indicadorCompletarCerosNumDocumento = clienteService
				.getValorByPaisAndTipo(pais.getCodigo(),
						Constants.HIP_VALID_COMPLETA_CEROS_DOCUMENTO_IDENTIDAD);
		if (indicadorCompletarCerosNumDocumento != null) {
			String[] iccn = indicadorCompletarCerosNumDocumento.split(",");
			if (iccn[0].equals("NOC00") && iccn[1].equals("1")) {
				this.indicadorCompletarCerosNumDocumento = "false";
			} else {
				this.indicadorCompletarCerosNumDocumento = "true";
			}
		} else {
			this.indicadorCompletarCerosNumDocumento = "true";
		}
	}

	public void cargarPantalla(ActionEvent actionEvent) throws Exception {

		this.redireccionarPagina("procesoMAECargaBloqueoDesbloqueoMasivoForm");
		return;
	}
	
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = "";
		
			HashMap cliente =  (HashMap) this.beanRegistroSeleccionado;
			if (!this.verificarRegistroSeleccionado()) {
				return this.getResourceMessage("errors.select.item");

			}
			String id =(String) cliente.get("codigoCliente");
			

			if (!StringUtils.isBlank(id)) {
				if (this.log.isDebugEnabled()) {
					this.log.debug("Id seleccionado de la lista: " + id);
				}

				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				String codigoUsuario = usuario.getCodigo();

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				int regularizar = ajax.getValidarRegularizacion(
						id, codigoUsuario);	
				
				switch (regularizar) {				
				case 0:					
					break;				
				case 1:
					mensaje = this.getResourceMessage("mantenimientoMAEBloqueoDesbloqueoClienteSearchForm.cliente.sin.desbloqueo");
					
					break;
				case 2:
					mensaje = this.getResourceMessage("mantenimientoMAEBloqueoDesbloqueoClienteSearchForm.cliente.con.desbloqueo");
					
					break;
				case 3:
					//this.mensajeConfirmacion=this.getResourceMessage("mantenimientoMAEBloqueoDesbloqueoClienteSearchForm.cliente.confirmacion.desbloqueo");
					//mensaje = levantarRegularizarcionCliente(id,oidBloqueo);
					break;
				
				default:
					break;
				}
			}
		return mensaje;
	}
	

	public void levantarRegularizarcionCliente(ActionEvent event) {
		this.mensajeAlerta = "ERROR DE ACTUALIZACION";
		try {
			HashMap cliente =  (HashMap) this.beanRegistroSeleccionado;
			if (!this.verificarRegistroSeleccionado()) {
				return;

			}
			String id =(String) cliente.get("codigoCliente");
			BigDecimal oidBloqueo = (BigDecimal)cliente.get("oidBloqueo");
			
			Integer intOidBloqueo = null;
			if(oidBloqueo!=null){
				intOidBloqueo = oidBloqueo.intValue();
			}

			if (!StringUtils.isBlank(id)) {
				if (this.log.isDebugEnabled()) {
					this.log.debug("Id seleccionado de la lista: " + id);
				}

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				String data =	ajax.updateRegularizacion(id,intOidBloqueo);
				Integer intData = Integer.parseInt(data);
				
				switch (intData.intValue()) {				
				case 0:					
					this.mensajeAlerta = "Registro actualizado correctamente.";
					break;
				case 1:
					this.mensajeAlerta = "ERROR DE ACTUALIZACION\n\nEl registro no tiene el campo observaciones.";
					break;
				case 2:
					this.mensajeAlerta = "ERROR DE ACTUALIZACION\n\nEl cliente no está bloqueado.";					
					break;				
				default:
					this.mensajeAlerta = "ERROR DE ACTUALIZACION";
					break;
				}
				
				String ventanaConfirmar = "PF('alertDialogMaeBloqueoDesbloqueo_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				this.activarHotkeyEstandar = true;
				return ;
				
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}



	public void completarNumeroDocumento(ActionEvent actionEvent) {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEBloqueoDesbloqueoClienteSearchForm f = (MantenimientoMAEBloqueoDesbloqueoClienteSearchForm) this.formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		String indicadorCompletarCerosNumDocumento = clienteService
				.getValorByPaisAndTipo(pais.getCodigo(), "idIndicadorCC");
		String idPais = pais.getCodigo();
		String tipoDocumentoIdentidad = f.getCodigoTipoDocumentoIdentidad();
		String mensaje = "";

		if (tipoDocumentoIdentidad != "") {
			if (indicadorCompletarCerosNumDocumento == "true") {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				String codCliente = ajax
						.getLongitudDefaultTipoDocumento(idPais);
				if (codCliente == null) {
					mensaje = "Documento de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda";
					this.addError("Error : ", mensaje);
					f.setCodigoCliente(null);
				}
			}
		}

	}

	public void completarNumeroDocumento2(ActionEvent actionEvent) {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEBloqueoDesbloqueoClienteSearchForm f = (MantenimientoMAEBloqueoDesbloqueoClienteSearchForm) this.formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		String indicadorCompletarCerosNumDocumento = clienteService
				.getValorByPaisAndTipo(pais.getCodigo(), "idIndicadorCC");
		String idPais = pais.getCodigo();
		String tipoDocumentoIdentidad = f.getCodigoTipoDocumentoIdentidad();
		String mensaje = "";
		String numDocumento = "";

		if (tipoDocumentoIdentidad != "") {
			if (indicadorCompletarCerosNumDocumento == "true") {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				numDocumento = ajax.getLongitudTipoDocumentoByCodigo(idPais,
						tipoDocumentoIdentidad);
				if (numDocumento == null) {
					mensaje = "Documento de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda";
					this.addError("Error : ", mensaje);
					f.setCodigoCliente(null);
				}
			} else {

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				numDocumento = ajax.getLongitudDefaultTipoDocumento(idPais);
				if (numDocumento == null) {
					mensaje = "Documento de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda";
					this.addError("Error : ", mensaje);
					f.setCodigoCliente(null);
				}
			}
		}
	}

	public int validaConsultoraOnEnter() {
		MantenimientoMAEBloqueoDesbloqueoClienteSearchForm search = (MantenimientoMAEBloqueoDesbloqueoClienteSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		String codigoConsultora = "";
		codigoConsultora = search.getCodigoCliente();
		if (StringUtils.isEmpty(codigoConsultora)) {
			return 0;
		} else {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelDatosConsultoraValue[] codigoconsultora = ajax
					.getCabeceraConsultoraSimple(pais.getCodigo(),
							codigoConsultora);
			if (codigoconsultora != null) {
				return 0;
			} else
				return 1;
		}

	}

	public void limpiarCajaTextoDocumento(ActionEvent actionEvent) {
		MantenimientoMAEBloqueoDesbloqueoClienteSearchForm search = (MantenimientoMAEBloqueoDesbloqueoClienteSearchForm) this.formBusqueda;
		search.setNumeroDocumentoIdentidad("");
	}

	// Salir a la Pantalla Padre
	public void salirAPantallaPadre(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("mantenimientoSTOSolicitudCreditoForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getOidPais() {
		return oidPais;
	}

	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	public List getSiccTipoDocumentoList() {
		return siccTipoDocumentoList;
	}

	public void setSiccTipoDocumentoList(List siccTipoDocumentoList) {
		this.siccTipoDocumentoList = siccTipoDocumentoList;
	}

	public String getMantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear() {
		return mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear;
	}

	public void setMantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear(
			String mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear) {
		this.mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear = mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear;
	}

	public String getMantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear() {
		return mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear;
	}

	public void setMantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear(
			String mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear) {
		this.mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear = mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear;
	}

	public int getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(int longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public List getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	public void setSiccSubTipoClienteList(List siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	public String getIndicadorCompletarCerosNumDocumento() {
		return indicadorCompletarCerosNumDocumento;
	}

	public void setIndicadorCompletarCerosNumDocumento(
			String indicadorCompletarCerosNumDocumento) {
		this.indicadorCompletarCerosNumDocumento = indicadorCompletarCerosNumDocumento;
	}

	public List getMantenimientoMAEBloqueoDesbloqueoClienteList() {
		return mantenimientoMAEBloqueoDesbloqueoClienteList;
	}

	public void setMantenimientoMAEBloqueoDesbloqueoClienteList(
			List mantenimientoMAEBloqueoDesbloqueoClienteList) {
		this.mantenimientoMAEBloqueoDesbloqueoClienteList = mantenimientoMAEBloqueoDesbloqueoClienteList;
	}

	public boolean isIndicadorBuscar() {
		return indicadorBuscar;
	}

	public void setIndicadorBuscar(boolean indicadorBuscar) {
		this.indicadorBuscar = indicadorBuscar;
	}

	public List getMantenimientoMAEBloqueoDesbloqueoClienteTipoBloqueoList() {
		return mantenimientoMAEBloqueoDesbloqueoClienteTipoBloqueoList;
	}

	public void setMantenimientoMAEBloqueoDesbloqueoClienteTipoBloqueoList(
			List mantenimientoMAEBloqueoDesbloqueoClienteTipoBloqueoList) {
		this.mantenimientoMAEBloqueoDesbloqueoClienteTipoBloqueoList = mantenimientoMAEBloqueoDesbloqueoClienteTipoBloqueoList;
	}

	public List getMantenimientoMAEBloqueoDesbloqueoClienteTipoAreaList() {
		return mantenimientoMAEBloqueoDesbloqueoClienteTipoAreaList;
	}

	public void setMantenimientoMAEBloqueoDesbloqueoClienteTipoAreaList(
			List mantenimientoMAEBloqueoDesbloqueoClienteTipoAreaList) {
		this.mantenimientoMAEBloqueoDesbloqueoClienteTipoAreaList = mantenimientoMAEBloqueoDesbloqueoClienteTipoAreaList;
	}

	public List getMantenimientoMAEBloqueoDesbloqueoLogBloqueoList() {
		return mantenimientoMAEBloqueoDesbloqueoLogBloqueoList;
	}

	public void setMantenimientoMAEBloqueoDesbloqueoLogBloqueoList(
			List mantenimientoMAEBloqueoDesbloqueoLogBloqueoList) {
		this.mantenimientoMAEBloqueoDesbloqueoLogBloqueoList = mantenimientoMAEBloqueoDesbloqueoLogBloqueoList;
	}

	public List getMantenimientoMAEBloqueoDesbloqueoLogDesbloqueoList() {
		return mantenimientoMAEBloqueoDesbloqueoLogDesbloqueoList;
	}

	public void setMantenimientoMAEBloqueoDesbloqueoLogDesbloqueoList(
			List mantenimientoMAEBloqueoDesbloqueoLogDesbloqueoList) {
		this.mantenimientoMAEBloqueoDesbloqueoLogDesbloqueoList = mantenimientoMAEBloqueoDesbloqueoLogDesbloqueoList;
	}

	public DataTableModel getListaConsultaBloqueo() {
		return listaConsultaBloqueo;
	}

	public void setListaConsultaBloqueo(DataTableModel listaConsultaBloqueo) {
		this.listaConsultaBloqueo = listaConsultaBloqueo;
	}

	public DataTableModel getListaConsultaDesbloqueo() {
		return listaConsultaDesbloqueo;
	}

	public void setListaConsultaDesbloqueo(
			DataTableModel listaConsultaDesbloqueo) {
		this.listaConsultaDesbloqueo = listaConsultaDesbloqueo;
	}

	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}

	public BusquedaConsultorasAction getBusquedaConsultorasAction() {
		return busquedaConsultorasAction;
	}

	public void setBusquedaConsultorasAction(
			BusquedaConsultorasAction busquedaConsultorasAction) {
		this.busquedaConsultorasAction = busquedaConsultorasAction;
	}

	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}

	public boolean isIndicador() {
		return indicador;
	}

	public void setIndicador(boolean indicador) {
		this.indicador = indicador;
	}

	public boolean isOcultarInfo() {
		return ocultarInfo;
	}

	public void setOcultarInfo(boolean ocultarInfo) {
		this.ocultarInfo = ocultarInfo;
	}

	/**
	 * @return the mensajeConfirmacion
	 */
	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	/**
	 * @param mensajeConfirmacion the mensajeConfirmacion to set
	 */
	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	/**
	 * @return the mensajeAlerta
	 */
	public String getMensajeAlerta() {
		return mensajeAlerta;
	}

	/**
	 * @param mensajeAlerta the mensajeAlerta to set
	 */
	public void setMensajeAlerta(String mensajeAlerta) {
		this.mensajeAlerta = mensajeAlerta;
	}
	
	

}

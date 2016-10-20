package biz.belcorp.ssicc.web.spusicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoEMPBajaManualEmprendedoraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoEMPBajaManualEmprendedoraAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 4831008411765334067L;
	private List empMotivoBajaList = new ArrayList();
	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "SCLIENTES";

	@ManagedProperty(value = "#{busquedaConsultorasAction}")
	private BusquedaConsultorasAction busquedaConsultorasAction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CLIENTES)) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map) this.busquedaConsultorasAction
						.getBeanRegistroSeleccionado();
				MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) this.formBusqueda;
				f.setCodigoCliente((String) clienteHipMap.get("codigoCliente"));
				this.busquedaConsultorasAction
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
	@Override
	protected void setSalirPopup() {
		this.mostrarPopUpCliente = false;
		this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
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
	public BusquedaConsultorasAction getbusquedaConsultorasAction() {
		return busquedaConsultorasAction;
	}

	/**
	 * @param busquedaConsultorasAction
	 */
	public void setbusquedaConsultorasAction(
			BusquedaConsultorasAction busquedaConsultorasAction) {
		this.busquedaConsultorasAction = busquedaConsultorasAction;
	}

	/**
	 * @return
	 */
	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}

	/**
	 * @return
	 */
	public List getEmpMotivoBajaList() {
		return empMotivoBajaList;
	}

	/**
	 * @param empMotivoBajaList
	 */
	public void setEmpMotivoBajaList(List empMotivoBajaList) {
		this.empMotivoBajaList = empMotivoBajaList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCRAGrupoZonaList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCRAGrupoZonaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoEMPBajaManualEmprendedoraForm searchForm = new MantenimientoEMPBajaManualEmprendedoraForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		// Obtenemos los datos del usuario Logueado

		// Obtiene la información de la consultora
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoCliente", f.getCodigoCliente());

		// limpiamos los datos del formulario
		inicializar(f);

		Map mapDatosCliente = clienteService.getDatosCliente(criteria);
		if (mapDatosCliente != null) {

			if (StringUtils.isEmpty((String) mapDatosCliente
					.get("indicadorEmprendedora"))) {

				this.addError(
						"Atención",
						this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteNoEmpresaria"));
				f.setCodigoClienteBuscar("");
			}

			else {

				if (!StringUtils.isEmpty((String) mapDatosCliente
						.get("indicadorBaja"))) {
					if (mapDatosCliente.get("indicadorBaja").toString()
							.equals("1")) {

						this.addError(
								"Atención",
								this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteDeBaja"));
						f.setCodigoClienteBuscar("");
					}
				}

				f.setNombreCliente((String) mapDatosCliente
						.get("nombreCliente"));
				f.setRegion((String) mapDatosCliente.get("region"));
				f.setZona((String) mapDatosCliente.get("zona"));
				f.setIndicadorEmprendedora((String) mapDatosCliente
						.get("indicadorEmprendedora"));
				f.setCodigoCliente((String) mapDatosCliente
						.get("codigoCliente"));

				criteria.put("indicadorEmprendedora",
						(String) mapDatosCliente.get("indicadorEmprendedora"));

				// Obtiene los motivos de baja para emprendedoras y pre
				// emprendedoras
				this.empMotivoBajaList = clienteService
						.getMotivosBaja(criteria);

				f.setClienteEncontrado(true);
			}

		} else {
			this.addError(
					"Atención",
					this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteNoEncontrado"));
			f.setCodigoClienteBuscar("");
		}

		return null;

	}

	/**
	 * 
	 */
	public void buscarAttributes() {
		try {
			MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) this.formBusqueda;

			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

			// Obtenemos los datos del usuario Logueado

			// Obtiene la información de la consultora
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoCliente", f.getCodigoCliente());

			// limpiamos los datos del formulario
			// inicializar(f);

			Map mapDatosCliente = clienteService.getDatosCliente(criteria);
			if (mapDatosCliente != null) {
				if (StringUtils.isEmpty((String) mapDatosCliente
						.get("indicadorEmprendedora"))) {
					addError(
							"Atención",
							this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteNoEmpresaria"));
					f.setCodigoClienteBuscar("");
				} else {
					if (!StringUtils.isEmpty((String) mapDatosCliente
							.get("indicadorBaja"))) {
						if (mapDatosCliente.get("indicadorBaja").toString()
								.equals("1")) {
							addError(
									"Atención",
									this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteDeBaja"));
							f.setCodigoClienteBuscar("");
						}
					}

					f.setNombreCliente((String) mapDatosCliente
							.get("nombreCliente"));
					f.setRegion((String) mapDatosCliente.get("region"));
					f.setZona((String) mapDatosCliente.get("zona"));
					f.setIndicadorEmprendedora((String) mapDatosCliente
							.get("indicadorEmprendedora"));
					f.setCodigoCliente((String) mapDatosCliente
							.get("codigoCliente"));

					criteria.put("indicadorEmprendedora",
							(String) mapDatosCliente
									.get("indicadorEmprendedora"));

					// Obtiene los motivos de baja para emprendedoras y pre
					// emprendedoras
					this.empMotivoBajaList = clienteService
							.getMotivosBaja(criteria);

					f.setClienteEncontrado(true);
				}

			} else {
				this.addError(
						"Atención",
						this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteNoEncontrado"));
				f.setCodigoClienteBuscar("");
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) formBusqueda;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		Map criteria = new HashMap();

		// Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		criteria.put("codigoUsuario", usuario.getLogin());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("motivo", f.getMotivo());

		clienteService.executeBajaManualEmpresarias(criteria);

		// addInfo("Mensaje",
		// this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.success"));
		// limpiamos los datos del formulario
		inicializar(f);
		f.setCodigoClienteBuscar("");
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("add new");
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonBuscar = false;
		this.mostrarListaBusqueda = false;
		MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) this.formBusqueda;

		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

		// inicializa la lista de motivos de baja

		this.empMotivoBajaList = new ArrayList();
		// session.setAttribute(Constants.EMP_MOTIVO_BAJA_LIST, new
		// ArrayList());

		inicializar(f);
		f.setCodigoClienteBuscar("");

	}

	/**
	 * @param f
	 */
	private void inicializar(MantenimientoEMPBajaManualEmprendedoraForm f) {
		f.setCodigoCliente("");
		f.setNombreCliente("");
		f.setRegion("");
		f.setZona("");
		f.setIndicadorEmprendedora("");
		f.setClienteEncontrado(false);
		this.empMotivoBajaList.clear();
	}

	/**
	 * @param
	 * @throws Exception
	 */

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) this.formBusqueda;
		boolean isNew = f.isNewRecord();
		if (isNew) {
			return "mantenimientoEMPBajaManualEmprendedoraForm.success";
		} else {
			return "mantenimientoEMPBajaManualEmprendedoraForm.success";
		}

	}

}

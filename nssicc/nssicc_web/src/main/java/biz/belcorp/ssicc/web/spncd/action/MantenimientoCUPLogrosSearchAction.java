package biz.belcorp.ssicc.web.spncd.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPLogrosService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPLogrosForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPLogrosSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCUPLogrosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private List cupTipoLogrosList;
	private static final long serialVersionUID = -2199101092305768146L;
	private Integer longitudCampoClientes;
	private List cupMediosCapturaList;
	private String codigoTipoLogroBD;
	private static final String POPUP_CONSULTORA = "CONSULTORA";
	private static final String POPUP_CONSULTORA_SEARCH = "CONSULTORA_SEARCH";
	private boolean mostrarPopupConsultora;
	private String nombreConsultora;
	private String validacionRegistro;
	private String periodoConsultora;
	private Boolean mostrarCalendars;

	@ManagedProperty(value = "#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;
		MantenimientoCUPLogrosSearchForm f = (MantenimientoCUPLogrosSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCUPLogrosService service = (MantenimientoCUPLogrosService) getBean("spncd.procesoIMPGeneraConsolidadoService");
		f.setCodigoPais(pais.getCodigo());
		// Lista del grid
		this.nombreConsultora = "";
		this.mostrarCalendars = false;
		// Obtiene la campa�a activa del archivo de control
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteria);
		String codigoPeriodo = controlFacturacion.getCodigoPeriodo();
		f.setCodigoPeriodo(codigoPeriodo);

		// Obtiene la longitud del campo codigo de cliente, segun la parametria
		// del pais
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(pais.getCodigo());
		this.cupTipoLogrosList = service.getTiposLogro();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		MantenimientoCUPLogrosForm form = (MantenimientoCUPLogrosForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		LabelValue mensaje = ajax.getValidaMontoLogro(
				form.getCodigoTipoLogro(), form.getMonto());
		String contenedor = "";
		if (mensaje.getValue().equalsIgnoreCase("0")) {
			contenedor = this.getResourceMessage("error.monto.fuera.rango")
					+ mensaje.getLabel().toString();
			return contenedor;
		}
		return contenedor;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {

		MantenimientoCUPLogrosForm form = (MantenimientoCUPLogrosForm) this.formMantenimiento;

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		LabelValue mensaje = ajax.getValidaMontoLogro(
				form.getCodigoTipoLogro(), form.getMonto());
		String contenedor = "";
		if (mensaje.getValue().equalsIgnoreCase("0")) {
			contenedor = this.getResourceMessage("error.monto.fuera.rango")
					+ mensaje.getLabel().toString();
			return contenedor;
		}
		if (!this.accion.equalsIgnoreCase(ACCION_MODIFICAR)) {
			String valor = form.getCodigoConsultora();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.validacionRegistro = aSvc.getValidacionRegistroMeta(
					pais.getCodigo(), valor);

			String mensajeFinal = null;

			if (validacionRegistro.equals("1")) {
				mensajeFinal = this
						.getResourceMessage("mantenimientoCUPLogrosForm.error.codigoConsultora.noPerteneceNegocio");
				return mensajeFinal;
			}
			if (validacionRegistro.equals("2")) {
				mensajeFinal = this
						.getResourceMessage("mantenimientoCUPLogrosForm.error.consultora.noActiva");
				return mensajeFinal;
			}
			if (validacionRegistro.equals("3")) {
				mensajeFinal = this
						.getResourceMessage("mantenimientoCUPLogrosForm.error.consultora.tieneMetaRegistrada");
				return mensajeFinal;
			}
			if (validacionRegistro.equals("4")) {
				mensajeFinal = this
						.getResourceMessage("mantenimientoCUPLogrosForm.error.campanyaIngreso.fueraRango");
				return mensajeFinal;
			}
		}
		return contenedor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCUPLogrosForm mantenimientoForm = (MantenimientoCUPLogrosForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCUPLogrosForm.insert";
		} else {
			return "mantenimientoCUPLogrosForm.update";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
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
				MantenimientoCUPLogrosSearchForm f = (MantenimientoCUPLogrosSearchForm) this.formBusqueda;
				f.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		} else if (accion.equals(this.POPUP_CONSULTORA_SEARCH)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction
						.getBeanRegistroSeleccionado();
				MantenimientoCUPLogrosForm f = (MantenimientoCUPLogrosForm) this.formMantenimiento;
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
		} else if (accion.equals(this.POPUP_CONSULTORA_SEARCH)) {
			this.mostrarPopupConsultora = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCUPLogrosList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCUPLogrosForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCUPLogrosSearchForm form = new MantenimientoCUPLogrosSearchForm();
		return form;
	}

	/**
	 * Valida parametros
	 * 
	 * @return
	 */
	public Boolean primeraValidacion() {
		try {
			MantenimientoCUPLogrosForm form = (MantenimientoCUPLogrosForm) this.formMantenimiento;

			if (!this.accion.equalsIgnoreCase(ACCION_MODIFICAR)) {
				String valor = form.getCodigoConsultora();
				Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.validacionRegistro = aSvc.getValidacionRegistroMeta(
						pais.getCodigo(), valor);

				String mensaje = null;

				if (validacionRegistro.equals("1")) {
					mensaje = this
							.getResourceMessage("mantenimientoCUPLogrosForm.error.codigoConsultora.noPerteneceNegocio");
					this.addWarn("Error", mensaje);
				}
				if (validacionRegistro.equals("2")) {
					mensaje = this
							.getResourceMessage("mantenimientoCUPLogrosForm.error.consultora.noActiva");
					this.addWarn("Error", mensaje);
				}
				if (validacionRegistro.equals("3")) {
					mensaje = this
							.getResourceMessage("mantenimientoCUPLogrosForm.error.consultora.tieneMetaRegistrada");
					this.addWarn("Error", mensaje);
				}
				if (validacionRegistro.equals("4")) {
					mensaje = this
							.getResourceMessage("mantenimientoCUPLogrosForm.error.campanyaIngreso.fueraRango");
					this.addWarn("Error", mensaje);
				}
			}

			return true;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return false;
		}

	}

	/**
	 * @throws Exception
	 */
	public void seteaConsultoraBusqueda() {

		try {
			this.nombreConsultora = null;
			this.validacionRegistro = null;
			this.periodoConsultora = null;
			
			MantenimientoCUPLogrosSearchForm form = (MantenimientoCUPLogrosSearchForm) this.formBusqueda;
			String valor = form.getCodigoConsultora();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.nombreConsultora = aSvc.getNombreCliente(valor);

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @throws Exception
	 */
	public void seteaConsultora() {

		try {
			this.nombreConsultora = null;
			this.validacionRegistro = null;
			this.periodoConsultora = null;
			
			MantenimientoCUPLogrosForm form = (MantenimientoCUPLogrosForm) this.formMantenimiento;
			String valor = form.getCodigoConsultora();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.validacionRegistro = aSvc.getValidacionRegistroMeta(
					pais.getCodigo(), valor);

			Boolean validar = primeraValidacion();
			if (validar.equals(false)) {
				return;
			}

			this.nombreConsultora = aSvc.getNombreCliente(valor);

			this.periodoConsultora = aSvc.getPeriodoIngresoConsultora(
					pais.getCodigo(), valor);
			String partes[] = this.periodoConsultora.split(",");
			String part1 = partes[0];
			String part2 = partes[1];
			form.setCodigoPeriodoInicio(part1);
			form.setCodigoPeriodoFin(part2);
			this.mostrarCalendars = true;

		} catch (Exception e) {
			this.mostrarCalendars = false;

			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoCUPLogrosSearchForm f = (MantenimientoCUPLogrosSearchForm) this.formBusqueda;
		MantenimientoCUPLogrosService service = (MantenimientoCUPLogrosService) getBean("spncd.procesoIMPGeneraConsolidadoService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoCliente", f.getCodigoConsultora());

		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoTipoLogro", f.getCodigoTipoLogro());

		criteria.put("estado", f.getEstado());

		List resultado = (List) service.getLogrosList(criteria);

		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		MantenimientoCUPLogrosService service = (MantenimientoCUPLogrosService) getBean("spncd.procesoIMPGeneraConsolidadoService");
		HashMap<String, Object> objBeanSelect = (HashMap<String, Object>) this.beanRegistroSeleccionado;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		String codigoPais = (String) objBeanSelect.get("codigoPais");
		String codigoCliente = (String) objBeanSelect.get("codigoCliente");
		String campanaInicio = (String) objBeanSelect.get("campanaInicio");
		String codigoTipoLogro = (String) objBeanSelect.get("codigoTipoLogro");

		String[] scripts = new String[1];
		scripts[0] = codigoPais + "|" + codigoCliente + "|" + campanaInicio
				+ "|" + codigoTipoLogro;
		service.deleteLogros(scripts, usuario.getCodigo());
		String mensaje = this
				.getResourceMessage("mantenimientoCUPLogrosForm.deleted");
		this.addInfo("Info : ", mensaje);

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
		MantenimientoCUPLogrosForm f = (MantenimientoCUPLogrosForm) this.formMantenimiento;
		MantenimientoCUPLogrosService service = (MantenimientoCUPLogrosService) getBean("spncd.procesoIMPGeneraConsolidadoService");
		LabelValue monton = null;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String mensaje = null;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Boolean validar = primeraValidacion();

		if (validar.equals(false)) {
			return false;
		}

		Map params = new HashMap();

		String codigoPais = f.getCodigoPais();

		// Carga los combos
		// f.setIndicadorEdit(Constants.NUMERO_CERO);
		String codigoCliente = f.getCodigoConsultora();
		String campanaInicio = f.getCodigoPeriodoInicio();
		String codigoTipoLogro = f.getCodigoTipoLogro();
		String codigoMedioCaptura = "";
		String montoLogro = f.getMonto();
		String campanaFin = f.getCodigoPeriodoFin();
		String codigoUsuario = usuario.getLogin();
		String descripcionLarga = f.getDescripcionLarga();

		String getMonto = f.getMonto();

		params.put("codigoPais", codigoPais);
		params.put("codigoCliente", codigoCliente);
		params.put("codigoTipoLogro", codigoTipoLogro);
		params.put("codigoMedioCaptura", codigoMedioCaptura);
		params.put("montoLogro", montoLogro);
		params.put("campanaInicio", campanaInicio);
		params.put("campanaFin", campanaFin);
		params.put("codigoOrigen", Constants.CUP_LOGROS_SISTEMA_COMERCIAL);
		params.put("codigoUsuario", codigoUsuario);
		params.put("descripcionLarga", descripcionLarga);
		monton = aSvc.getValidaMontoLogro(codigoTipoLogro, getMonto);
		if (monton.getValue().equals("0")) {
			mensaje = this
					.getResourceMessage("mantenimientoCUPLogrosForm.error.campanyaIngreso.fueraRango");
			this.addError("Error", mensaje);
			return false;

		} else {

			if (f.getIndicadorEdit().equals(Constants.NUMERO_CERO)) {
				// Nuevo registro - INSERT
				service.insertLogros(params);
				// mensaje = this
				// .getResourceMessage("mantenimientoCUPLogrosForm.insert");

			} else {
				// Registro existente - UPDATE
				if (f.getCodigoTipoLogro() == null
						|| f.getCodigoTipoLogro().equals("")) {
					f.setCodigoTipoLogro(codigoTipoLogroBD.toString());
					params.put("codigoTipoLogro", f.getCodigoTipoLogro());
				}
				service.updateLogros(params);
				// mensaje = this
				// .getResourceMessage("mantenimientoCUPLogrosForm.update");

			}

		}
		// addInfo("Info :", mensaje);
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
		log.debug("Entering 'edit' method");
		MantenimientoCUPLogrosForm f = new MantenimientoCUPLogrosForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCUPLogrosService service = (MantenimientoCUPLogrosService) getBean("spncd.procesoIMPGeneraConsolidadoService");
		f.setCodigoPais(pais.getCodigo());
		this.cupTipoLogrosList = service.getTiposLogro();
		this.cupMediosCapturaList = service.getMediosCaptura();
		this.nombreConsultora  = "";

		if (!this.accion.equals(this.ACCION_NUEVO)) {

			HashMap<String, Object> sistemabusqueda = (HashMap<String, Object>) this.beanRegistroSeleccionado;
			f.setNewRecord(true);
			String codigoPais = (String) sistemabusqueda.get("codigoPais");
			String codigoCliente = (String) sistemabusqueda
					.get("codigoCliente");
			String campanaInicio = (String) sistemabusqueda
					.get("campanaInicio");
			String codigoTipoLogro = (String) sistemabusqueda
					.get("codigoTipoLogro");
			// String id = f.getSelectedItems()[0];
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoCliente", codigoCliente);
			criteria.put("campanaInicio", campanaInicio);
			criteria.put("codigoTipoLogro", codigoTipoLogro);
			this.nombreConsultora = (String) sistemabusqueda
					.get("nombreApellidoCliente");

			// Captura el criteria y arma un result
			Map result = new HashMap();
			result = (Map) service.getLogrosByIdList(criteria).get(0);

			criteria.put("codCliente", criteria.get("codigoCliente"));
			// Setea el indicador de actividad de la consultora
			f.setIndicadorActividadConsultora(service
					.getIndicadorActivoConsultora(criteria));

			criteria.put("codigoPais", f.getCodigoPais());
			String indicadorNuevas = service.getParametroNuevasLogro(criteria);
			f.setIndicadorNuevas(indicadorNuevas);

			ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");

			f.setCodigoPais(pais.getCodigo());
			f.setCodigoConsultora(criteria.get("codigoCliente").toString());
			f.setCodigoTipoLogro(result.get("codigoTipoLogro").toString());
			if (result.get("codigoMedioCaptura") != null)
				f.setCodigoMedioCaptura(result.get("codigoMedioCaptura")
						.toString());
			f.setMonto(result.get("montoLogro").toString());
			f.setCodigoPeriodoInicio(result.get("campanaInicio").toString());
			f.setCodigoPeriodoFin(result.get("campanaFin").toString());
			if (result.get("descripcionLarga") != null)
				f.setDescripcionLarga(result.get("descripcionLarga").toString());
			criteria.put("codCliente", f.getCodigoConsultora());
			f.setDescripcionConsultora(procesoSTOEjecucionValidacionesService
					.getNombreConsultora(criteria));
			// Variable oculta con el valor de la campaña inicial
			criteria.put("codCliente", f.getCodigoConsultora());
			f.setPeriodoInicioConsultora(service.getPeriodoIngresoConsultora(f
					.getCodigoConsultora()));

			// Variable oculta con el valor de la campaña final (inicial + 3)
			MantenimientoOCRPedidoControlFacturacionService servicePeriodo = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			criteria.put("numCampanhas", Constants.CUP_LOGROS_NUMERO_PERIODOS);
			criteria.put("codigoPeriodo", f.getPeriodoInicioConsultora());
			f.setPeriodoFinConsultora(servicePeriodo
					.getPedidosNSiguienteCampanha(criteria));

			f.setCodigoPeriodoInicioBD(result.get("campanaInicio").toString());
			f.setCodigoPeriodoFinBD(result.get("campanaFin").toString());

			this.codigoTipoLogroBD = f.getCodigoTipoLogro();

			f.setIndicadorEdit(Constants.NUMERO_UNO);
		} else {
			f.setIndicadorEdit(Constants.NUMERO_CERO);
		}

		return f;
	}

	/**
	 * @return the cupTipoLogrosList
	 */
	public List getCupTipoLogrosList() {
		return cupTipoLogrosList;
	}

	/**
	 * @param cupTipoLogrosList
	 *            the cupTipoLogrosList to set
	 */
	public void setCupTipoLogrosList(List cupTipoLogrosList) {
		this.cupTipoLogrosList = cupTipoLogrosList;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 *            the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the cupMediosCapturaList
	 */
	public List getCupMediosCapturaList() {
		return cupMediosCapturaList;
	}

	/**
	 * @param cupMediosCapturaList
	 *            the cupMediosCapturaList to set
	 */
	public void setCupMediosCapturaList(List cupMediosCapturaList) {
		this.cupMediosCapturaList = cupMediosCapturaList;
	}

	/**
	 * @return the codigoTipoLogroBD
	 */
	public String getCodigoTipoLogroBD() {
		return codigoTipoLogroBD;
	}

	/**
	 * @param codigoTipoLogroBD
	 *            the codigoTipoLogroBD to set
	 */
	public void setCodigoTipoLogroBD(String codigoTipoLogroBD) {
		this.codigoTipoLogroBD = codigoTipoLogroBD;
	}

	/**
	 * @return the mostrarPopupConsultora
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora
	 *            the mostrarPopupConsultora to set
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
	 * @param busquedaConsultoraPOPUPSearchAction
	 *            the busquedaConsultoraPOPUPSearchAction to set
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

	/**
	 * @return the popupConsultoraSearch
	 */
	public static String getPopupConsultoraSearch() {
		return POPUP_CONSULTORA_SEARCH;
	}

	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}

	/**
	 * @param nombreConsultora
	 *            the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	/**
	 * @return the validacionRegistro
	 */
	public String getValidacionRegistro() {
		return validacionRegistro;
	}

	/**
	 * @param validacionRegistro
	 *            the validacionRegistro to set
	 */
	public void setValidacionRegistro(String validacionRegistro) {
		this.validacionRegistro = validacionRegistro;
	}

	/**
	 * @return the periodoConsultora
	 */
	public String getPeriodoConsultora() {
		return periodoConsultora;
	}

	/**
	 * @param periodoConsultora
	 *            the periodoConsultora to set
	 */
	public void setPeriodoConsultora(String periodoConsultora) {
		this.periodoConsultora = periodoConsultora;
	}

	/**
	 * @return the mostrarCalendars
	 */
	public Boolean getMostrarCalendars() {
		return mostrarCalendars;
	}

	/**
	 * @param mostrarCalendars
	 *            the mostrarCalendars to set
	 */
	public void setMostrarCalendars(Boolean mostrarCalendars) {
		this.mostrarCalendars = mostrarCalendars;
	}

}
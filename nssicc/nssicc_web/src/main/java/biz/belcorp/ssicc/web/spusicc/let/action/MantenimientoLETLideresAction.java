package biz.belcorp.ssicc.web.spusicc.let.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.management.InvalidAttributeValueException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pej.ConsultaPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaMAEClientePopupSearchAction;
import biz.belcorp.ssicc.web.spusicc.let.form.MantenimientoLETLideresForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoLETLideresAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List letLideresList = new ArrayList();
	private String letIndicadorZonBasPais = "";
	private boolean letTipoRegion;
	private boolean letTipoZona;
	private boolean letTipoSeccion;
	private boolean mostrarTodosRadioButtons;
	private String letCodigoRegionElegido = "";
	private String letCodigoZonaElegido = "";
	private String letListaLideresCodigo = "";
	private Integer indicadorProgramaLet = 0;
	private boolean pintarScrollLideres;
	private String tipobusquedaSession = "";
	private String indRegionCerrada = "";
	private String beanMensaje = "";
	private String codigoCliente = "";
	private String codigoRegionZona = "";
	private boolean mostrarPopUpCliente;
	private boolean mostrarBotones;
	private static final String POPUP_CLIENTES = "CLIENTES";
	private boolean mostrarPanelCliente;
	private boolean flag;
	private String mensajeLider;

	@ManagedProperty(value = "#{busquedaMAEClientePopupSearchAction}")
	private BusquedaMAEClientePopupSearchAction busquedaMAEClientePopupSearchAction;

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CLIENTES)) {
			this.busquedaMAEClientePopupSearchAction.verificarRegistro(event);
			if (this.busquedaMAEClientePopupSearchAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map) this.busquedaMAEClientePopupSearchAction
						.getBeanRegistroSeleccionado();
				MantenimientoLETLideresForm f = (MantenimientoLETLideresForm) this.formBusqueda;
				f.setCodigoClienteBuscar(((String) clienteHipMap.get("codigo")));
				this.busquedaMAEClientePopupSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}
	}

	/**
	 * Metodo que se ejecuta al hacer click en el Boton Busqueda de Popup para
	 * mostrar el popup respectivo
	 * 
	 * @param event
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
		this.busquedaMAEClientePopupSearchAction
				.setBeanRegistroSeleccionado(null);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoLETLideresForm f = new MantenimientoLETLideresForm();
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		MantenimientoLETLideresForm f = (MantenimientoLETLideresForm) formBusqueda;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) this
				.getBean("sisicc.interfazSiCCService");
		MantenimientoZONDirectorioService zonService = (MantenimientoZONDirectorioService) this
				.getBean("spusicc.mantenimientoZonDirectorioService");
		f.setPermitirIngresoCodigoCliente(true);
		List lideresList = new ArrayList();
		// -- Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(f);

		if (StringUtils
				.equals(f.getTipo(), Constants.LET_UA_RESPONSABLE_REGION)) {
			letCodigoZonaElegido = "";
		}

		Map map = new HashMap();
		map.put("tipo", f.getTipo());

		// Seleccion de Region
		letCodigoRegionElegido = f.getCodigoRegion();
		f.setCodigoRegion(f.getCodigoRegion().equals("T") ? null : f
				.getCodigoRegion());
		map.put("codigoRegion", f.getCodigoRegion());

		// Seleccion de Zona
		if (StringUtils.isNotBlank(f.getCodigoZona())) {
			letCodigoZonaElegido = f.getCodigoZona();
			f.setCodigoZona(f.getCodigoZona().equals("T") ? null : f
					.getCodigoZona());
			map.put("codigoZona", f.getCodigoZona());
		}

		if (StringUtils
				.equals(f.getTipo(), Constants.LET_UA_RESPONSABLE_REGION)) {
			letListaLideresCodigo = Constants.NUMERO_UNO;
			lideresList = zonService.getResponsableUA(map);
		} else if (StringUtils.equals(f.getTipo(),
				Constants.LET_UA_RESPONSABLE_ZONA)) {
			letListaLideresCodigo = Constants.NUMERO_DOS;
			lideresList = zonService.getResponsableUA(map);

		} else if (StringUtils.equals(f.getTipo(),
				Constants.LET_UA_RESPONSABLE_SECCION)) {

			// -- Capturar Periodo Actual
			List lista = interfazSiCCService
					.getPeriodoFechaProcesoActual(criteria);
			String periodoActual = null;
			if (lista.size() > 0) {
				periodoActual = ((HashMap) lista.get(0)).get("cod_peri")
						.toString();
				criteria.put("codigoPeriodoActual", periodoActual);
			}

			MantenimientoLETLideresService service = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");

			Integer indicadorProgramaLet = service.getIndicadorProgramaLet(this
					.getmPantallaPrincipalBean().getCurrentCountry()
					.getCodigo());
			/* INI JJ PER-SiCC-2012-0201 */
			this.indicadorProgramaLet = indicadorProgramaLet;
			/* FIN JJ PER-SiCC-2012-0201 */
			if (indicadorProgramaLet != null
					&& indicadorProgramaLet.intValue() == 2
					&& service.getEncontrarConcursoLet(periodoActual)
							.intValue() == 0) {
				throw new Exception(
						this.getResourceMessage("mantenimientoLETLideresForm.mensaje.error.NoEncontroConcurso"));
			}
			if (indicadorProgramaLet != null
					&& indicadorProgramaLet.intValue() == 3
					&& service
							.getEncontrarProgramaLetCorporativo(periodoActual)
							.intValue() == 0) {
				throw new Exception(
						this.getResourceMessage("mantenimientoLETLideresForm.mensaje.error.NoEncontroConcurso"));
			}

			/* INI JJ PER-SiCC-2012-0201 */
			Map criteriaOid = new HashMap();
			criteriaOid.put("codigoPais", this.getmPantallaPrincipalBean()
					.getCurrentCountry().getCodigo());
			criteriaOid.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteriaOid.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

			String oidPais = service.getOidPaisByCodigoPaisLET(criteriaOid);
			String oidMarca = service
					.getOidMarcaByCodigoMarcaGenericoLET(criteriaOid);
			String oidCanal = service
					.getOidCanalByCodigoCanalGenericoLET(criteriaOid);

			criteria.put("oidPais", oidPais);
			criteria.put("oidMarca", oidMarca);
			criteria.put("oidCanal", oidCanal);
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoZona", f.getCodigoZona());
			/* FIN JJ PER-SiCC-2012-0201 */
			// -- Obtenemos listado general
			lideresList = service.getSeccionesByCriteria(criteria);

			if (lideresList.size() > 10)
				this.pintarScrollLideres = new Boolean(true);
			else
				this.pintarScrollLideres = new Boolean(false);

			f.setPermitirIngresoCodigoCliente(true);

			String[] items = {};
			f.setSelectedItems(items);
			f.setIndicadorReingreso(Constants.NO);
			f.setMostrarMensajeReingreo(false);
			f.setIndicadorNoValidaUnicoLider(Constants.NO);
			f.setIndicadorUnicoLiderSeccion(null);

			log.info("Salio MantenimientoLETLideresAction - setFindAttributes");
		}
		this.letLideresList = lideresList;
		this.tipobusquedaSession = f.getTipo();
		f.setSelectedItems(null);
		return lideresList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		return super.setValidarConfirmar(accion);
	}

	/**
	 * @param evt
	 */
	public void confirmarLiderGuardar(ActionEvent evt) {
		try {
			String ventana = "confirmDialogCerrar";
			String ventanaConfirmar = "PF('" + ventana
					+ "_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}
	}

	/**
	 * @param evt
	 */
	public void confirmarLider(ActionEvent evt) {
		try {
			AjaxService ajax = (AjaxService) this.getBeanService("ajaxService");
			MantenimientoLETLideresForm f = (MantenimientoLETLideresForm) this.formBusqueda;
			String  codigoClienteBuscar = f.getCodigoClienteBuscar();
			if (StringUtils.isBlank(codigoClienteBuscar)) {
				String mensajeAlert = "Código de cliente se encuentra vacio";
				this.setMensajeAlertaDefault(mensajeAlert);
				RequestContext.getCurrentInstance().update("principalFormAlert:textoMensajeAlerta");
				String ventana = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventana);
				return;
			}

			Map mapLider = (Map) this.beanRegistroSeleccionado;
			String unidadAdministrativa = (String) mapLider
					.get("unidadAdministrativa");
			String codigoLider = (String) mapLider.get("codigoLider");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			String valorZR = (String) mapLider.get("codigo");
			String indicadorNombramiento = "0";
			f.setIndicadorNombramiento(indicadorNombramiento);
			String validandoLider = ajax.getValidarAsignarLiderIndicadorPrograma(unidadAdministrativa, f.getCodigoClienteBuscar(), pais.getCodigo());
			log.info("Valor que devuelve el ajax cuando valida al lider");
			log.info(validandoLider);
			log.info("Se continua con el proceso cuando es 1 o -1");
			if(StringUtils.isNotBlank(validandoLider)){
				String codigoRetorno = validandoLider.substring(0, 1);
				if (StringUtils.equals(codigoRetorno, "4")) {
					indicadorNombramiento = validandoLider.substring(1, 2);
					f.setIndicadorNombramiento(indicadorNombramiento);
				}
				
				String[] split = validandoLider.split(";");
				String codigoMensaje = "";
				String mensaje = "";
				codigoMensaje = split[0];
				if(StringUtils.equalsIgnoreCase(codigoMensaje, "1") ||
				   StringUtils.equalsIgnoreCase(codigoMensaje, "-1") || 
				   StringUtils.equalsIgnoreCase(codigoMensaje, "41") ||
				   StringUtils.equalsIgnoreCase(codigoMensaje, "42")){
					log.info("Paso las validaciones, se dirige al otro confirmar.");
					String ventana = "confirmDialogCerrar";
					String ventanaConfirmar = "PF('" + ventana
							+ "_confirmationDialogConfirmar').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					return;
				}else{
					mensaje = split[1];

				}
				
				
				this.mensajeLider = null;
				
				if (codigoMensaje.equals("43")) {
					this.mensajeLider = mensaje;
					String ventana = "confirmarLiderDialog";
					String ventanaConfirmar = "PF('" + ventana
							+ "_confirmationDialogConfirmar').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					return;

				} else if (codigoMensaje.equals("44")) {
					this.mensajeLider = mensaje;
					String ventana = "confirmarLiderDialog";
					String ventanaConfirmar = "PF('" + ventana
							+ "_confirmationDialogConfirmar').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					return;
				} else {
					String ventana = "confirmDialogCerrar";
					String ventanaConfirmar = "PF('" + ventana
							+ "_confirmationDialogConfirmar').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					return;
				}
				
			}
				
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}

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
			log.debug("setSaveAttributes");
		}
		MantenimientoLETLideresForm f = (MantenimientoLETLideresForm) formBusqueda;
		MantenimientoLETLideresService service = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ConsultaPEJProgramaEjecutivasService servicePEJ = (ConsultaPEJProgramaEjecutivasService) getBean("spusicc.consultaPEJProgramaEjecutivasService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoZONDirectorioService mantenimientoService = (MantenimientoZONDirectorioService) this
				.getBean("spusicc.mantenimientoZonDirectorioService");

		boolean bGrabarCab = false;
		boolean validaPrev = true;
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();

		try {

			// -- recuperamos la seccion seleccionada ----------------
			Map mapLider = (Map) this.beanRegistroSeleccionado;
			String unidadAdministrativa = (String) mapLider
					.get("unidadAdministrativa");
			String codigoLider = (String) mapLider.get("codigoLider");

			String valorZR = (String) mapLider.get("codigo");

			if (StringUtils.isBlank(valorZR)) {
				valorZR = unidadAdministrativa;
			}
			mapLider.put("codigoZona", valorZR);
			if (valorZR.length() == 2) {
				// Codigo de Region
				f.setCodigoRegion(valorZR);
				f.setCodigoZona("");
			} else if (valorZR.length() == 4) {
				// Codigo de Zona
				List listaRegion = interfazSiCCService.getLista(
						"getRegionByZona", mapLider);
				if (listaRegion != null && listaRegion.size() > 0) {
					Base base = (Base) listaRegion.get(0);
					f.setCodigoRegion(base.getCodigo());
				}
				f.setCodigoZona(valorZR);
			} else if (valorZR.length() == 9) {
				f.setCodigoRegion(valorZR.substring(2, 4));
				f.setCodigoZona(valorZR.substring(4, 8));
			}

			// -- Obtenemos las propiedades del bean como un 'Map' ---
			log.debug("codigoLider " + codigoLider);
			Map params = new HashMap();
			params.put("codigoLider", codigoLider);
			params.put("codigoPais", f.getCodigoPais());
			params.put("codigoMarca", mapLider.get("codigoMarca"));
			params.put("codigoCanal", mapLider.get("codigoCanal"));
			params.put("codigoCliente", f.getCodigoClienteBuscar());
			params.put("indicadorReingreso", f.getIndicadorReingreso());
			params.put("indicadorNoValidaUnicoLider",
					f.getIndicadorNoValidaUnicoLider());
			params.put("numeroActivasFinalesZona",
					mapLider.get("numeroActivasFinalesZona"));
			params.put("promedioActFinalesSeccion",
					mapLider.get("promedioActivasFinalesSeccion"));
			params.put("unidadAdministrativa",
					mapLider.get("unidadAdministrativa"));
			params.put("oidSeccion", mapLider.get("oidSeccion"));
			params.put("codigoUsuario", usuario.getCodigo());
			params.put("indRealizarValidaciones", "S");
			String indicadorZona = (String) mapLider.get("indicadorZona");
			String indicadorSeccion = (String) mapLider.get("indicadorSeccion");
			/* INI JJ PER-SiCC-2012-0201 */
			params.put("indicadorProgramaLet", this.indicadorProgramaLet);
			params.put("realizarEliminacionClasificacion", Constants.SI);
			params.put("indicadorNombramiento", f.getIndicadorNombramiento());

			/*
			 * Validación de Posibles Lideres, solo se hace para el acceso desde
			 * WEB
			 */
			List lista = interfazSiCCService
					.getPeriodoFechaProcesoActual(params);
			String periodoActual = ((HashMap) lista.get(0)).get("cod_peri")
					.toString();
			params.put("codigoPeriodoActual", periodoActual);

			String oidPais = service.getOidPaisByCodigoPaisLET(params);
			String oidMarca = service
					.getOidMarcaByCodigoMarcaGenericoLET(params);
			String oidCanal = service
					.getOidCanalByCodigoCanalGenericoLET(params);
			params.put("oidPais", oidPais);
			params.put("oidMarca", oidMarca);
			params.put("oidCanal", oidCanal);

			// Ajuste PER-SiCC-2014-0253 Mantenimiento Responsables - Adecuación
			// Nuevo LEC
			if (this.indicadorProgramaLet == 4) {
				Map result = new HashMap();
				result = service
						.getEncontrarProgramaLecCorporativo(periodoActual);
				String codProg = result.get("codigoPrograma").toString();

				Integer iiport = Integer.parseInt(result.get("indInscPort")
						.toString());
				Integer iicome = Integer.parseInt(result.get("indInscCome")
						.toString());
				Integer iplide = Integer.parseInt(result.get("indPosiLide")
						.toString());

				if (StringUtils.isBlank(codProg)) {

					
					Object[] objetoError = new Object[1];
					objetoError[0] = f.getCodigoClienteBuscar();	
					validaPrev = false;
					throw new Exception(this.getResourceMessage("mantenimientoLETLideresForm.msg.clienteNoExiste",objetoError));
				
							
					
					

				} else if (iiport == 0) {
					this.addError(
							"Error:",
							this.getResourceMessage("mantenimientoLECLideresForm.mensaje.error.NoNombramientosWeb"));
					validaPrev = false;
					return false;

				} else if (iicome == 0) {
					this.addError(
							"Error:",
							this.getResourceMessage("mantenimientoLECLideresForm.mensaje.error.NoNombramientosCome"));
					validaPrev = false;
					return false;
				}

			}

			// if(validaPrev){

			if (f.getTipo().equals("S")) {
				/* FIN JJ PER-SiCC-2012-0201 */
				// -- Capturar Periodo Actual ----------------------------
				/*
				 * List lista =
				 * interfazSiCCService.getPeriodoFechaProcesoActual(params);
				 * String periodoActual =
				 * ((HashMap)lista.get(0)).get("cod_peri").toString();
				 * params.put("codigoPeriodoActual", periodoActual);
				 */

				// -- Colocar Fecha y Periodo de Proceso -----------------
				Map obtenerFechaPeriodoProceso = (HashMap) interfazSiCCService
						.getPeriodoFechaProcesoActual(params).get(0);
				String codigoPeriodoProceso = (String) obtenerFechaPeriodoProceso
						.get("cod_peri");
				String fechaProceso = (String) obtenerFechaPeriodoProceso
						.get("fec_proc");
				params.put("fechaProceso", fechaProceso);
				params.put("codigoPeriodoProceso", codigoPeriodoProceso);
				params.put("codigoUsuario", usuario.getLogin());

				// -- Validar Indicador Asignar Lider --------------------
				int indicadorAsignarLider = Integer.valueOf(
						f.getIndicadorAsignarLider()).intValue();
				String codigoPeriodoProcesoSgte = servicePEJ.getObtienePeriodo(
						f.getCodigoPais(), codigoPeriodoProceso, 1);

				// -- captura periodo de parametria pais
				// punto 28
				// String periodoAsignarLider = (indicadorAsignarLider == 0) ?
				// codigoPeriodoProceso : codigoPeriodoProcesoSgte;
				String periodoAsignarLider = "";

				if (indicadorAsignarLider == 0) {
					periodoAsignarLider = codigoPeriodoProceso;
				} else if (indicadorAsignarLider == 1) {
					periodoAsignarLider = codigoPeriodoProcesoSgte;
				} else if (indicadorAsignarLider == 2) {
					boolean esRegionCerrada = false;
					Map criteria = new HashMap();
					criteria.put("codigoPeriodoActual", codigoPeriodoProceso);
					criteria.put("codigoRegion", f.getCodigoRegion());

					esRegionCerrada = clienteService
							.esRegionCerradaxSeccion(criteria);

					if (esRegionCerrada) {
						periodoAsignarLider = codigoPeriodoProcesoSgte;
						params.put("realizarEliminacionClasificacion",
								Constants.NO);
					} else {
						periodoAsignarLider = codigoPeriodoProceso;
					}
				}

				String codigoPeriodoQuiebre18 = servicePEJ.getObtienePeriodo(
						f.getCodigoPais(), periodoAsignarLider, -18);

				params.put("periodoAsignarLider", periodoAsignarLider);
				params.put("periodoAsignarLiderSgte", codigoPeriodoProcesoSgte);

				// -- Obtener oid campaña anterior -----------------------
				Map obtenerPeriodoAnterior = new HashMap();
				obtenerPeriodoAnterior.put("codigoPeriodo",
						codigoPeriodoProceso);
				obtenerPeriodoAnterior.put("oidPais", oidPais);
				obtenerPeriodoAnterior.put("oidMarca", oidMarca);
				obtenerPeriodoAnterior.put("oidCanal", oidCanal);
				params.put(
						"oidPeriodoAnterior",
						service.getOidPeriodoAnteriorByCodigoPeriodo(obtenerPeriodoAnterior));

				// -- realizamos las validaciones
				// ------------------------------------------------------
				String respuesta = "0__OK";
				// if(!f.getCodigoClienteBuscar().equals(""))
				params.put("codigoSubgerencia", valorZR.substring(0, 2));
				params.put("codigoRegion", f.getCodigoRegion());
				params.put("codigoZona", f.getCodigoZona());
				params.put("codigoSeccion", valorZR.substring(8, 9));
				params.put("indicadorWEB", Constants.NO);
				respuesta = service.validarAsignacionLiderSeccion(params);

				StringTokenizer stRespuesta = new StringTokenizer(respuesta,
						"__");
				log.debug("Respuesta Validacion: " + respuesta);
				String codigoRespuesta = stRespuesta.nextToken();
				String valorRespuesta = stRespuesta.nextToken();
				Object[] objetoError = new Object[1];
				objetoError[0] = f.getCodigoClienteBuscar();
				// --De acuerdo a las validaciones realizadas, para ver si el
				// cliente ingresado se puede asignar
				// --a la seccion como lider, y si todo esta ok,
				// codigoRespuesta=0, se actualiza la seccion
				if (codigoRespuesta.equals("1")) {
						
					bGrabarCab = false;
					throw new Exception(this.getResourceMessage("mantenimientoLETLideresForm.msg.clienteNoExiste",objetoError));
			
					

				} else if (codigoRespuesta.equals("2")) {
					bGrabarCab = false;
					throw new Exception(this.getResourceMessage("mantenimientoLETLideresForm.msg.tipoClienteConsultora",objetoError));
				} else if (codigoRespuesta.equals("3")) {

					log.debug("Respuesta Validacion 3: " + valorRespuesta);
					String arrCodigo[] = valorRespuesta.trim().split(";");
					String codigo = arrCodigo[0];
					String valor = arrCodigo[1];

					Map paramsDesvincular = new HashMap();
					paramsDesvincular.put("loginUsuario", usuario.getLogin());
					paramsDesvincular.put("codigoPais", this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo());

					if (codigo.equals("n")) {

						Map criteriaDes = new HashMap();
						criteriaDes.put("codigoPais", f.getCodigoPais());
						criteriaDes.put("codigoMarca",
								Constants.CODIGO_MARCA_DEFAULT);
						criteriaDes.put("codigoCanal",
								Constants.CODIGO_CANAL_DEFAULT);
						criteriaDes.put("oidPais", params.get("oidPais"));
						criteriaDes.put("oidMarca", params.get("oidMarca"));
						criteriaDes.put("oidCanal", params.get("oidCanal"));
						criteriaDes.put("codigoPeriodo", f.getCodigoPeriodo());
						criteriaDes.put("codigoZona", "");
						criteriaDes.put("codigoPeriodoActual",
								params.get("codigoPeriodoActual"));
						criteriaDes.put("codigoCliente",
								f.getCodigoClienteBuscar());
						// -- Obtenemos listado general filtrado por codigo
						// cliente
						List lideresListDes = service
								.getSeccionesByCriteria(criteriaDes);
						Map mapLiderDes = (Map) lideresListDes.get(0);
						String unidadAdminEval = (String) mapLiderDes
								.get("unidadAdministrativa");
						String valorZREval = (String) mapLiderDes.get("codigo");
						if (StringUtils.isBlank(valorZREval)) {
							valorZREval = unidadAdminEval;
						}
						valorZR = valor;

						paramsDesvincular.put("codigoRZ", valorZR);
						boolean desvincular = realizaDesvinculacionLider(
								paramsDesvincular, interfazSiCCService,
								servicePEJ, service, clienteService, f);

						if (desvincular) {
							return setSaveAttributes();
						}

					} else if (codigo.equals("n2")) {
						String valor2 = arrCodigo[2];
						log.debug("Respuesta Validacion 3 substr: " + codigo
								+ "-" + valor + "-" + valor2);
						String[] datos = new String[] {
								f.getCodigoClienteBuscar(), valor, valor2 };
						service.deleteHistoricoGerente(Integer.parseInt(valor2));

						// valorZR = valor;

						paramsDesvincular.put("codigoRZ", valorZR);
						boolean desvincular = realizaDesvinculacionLider(
								paramsDesvincular, interfazSiCCService,
								servicePEJ, service, clienteService, f);

						if (desvincular) {
							return setSaveAttributes();
						}

					} else {

						String valor2 = arrCodigo[2];
						String valor3 = null;
						String valor4 = null;

						periodoAsignarLider = valor;
						params.put("periodoAsignarLider", valor);

						if (StringUtils.isNotBlank(f.getCodigoClienteBuscar())) {
							if (arrCodigo.length == 5) {
								valor3 = arrCodigo[3];
								valor4 = arrCodigo[4];
							} else {
								valor3 = "0";
								valor4 = "0";
							}

							params.put("minActivasFinalesZona", valor3);
							params.put("minActivasFinalesSeccion", valor4);

						}

						log.debug("Respuesta Validacion 3 substr: " + codigo
								+ "-" + valor + "-" + valor2 + "-" + valor3
								+ "-" + valor4);

						codigoRespuesta = "0";
						valorRespuesta = valor2;
					}

				} else if (codigoRespuesta.equals("3B")) {
					String[] datos = new String[] { f.getCodigoClienteBuscar(),
							valorRespuesta };
					String texto = this
							.getResourceMessage(
									"mantenimientoLETLideresForm.msg.liderOtraSeccion.asignacion",
									datos);
					// saveErrors(request, messages);
					f.setMensajeUnicoLiderSeccion(texto);
					f.setIndicadorUnicoLiderSeccion(Constants.NUMERO_CERO);
					bGrabarCab = false;

				} else if (codigoRespuesta.equals("4")) {
					/* INI JJ PER-SiCC-2012-0201 */

					String respuestas[] = valorRespuesta.split(";");
					String texto;
					if (respuestas[1].equals("0")) {
						texto = this
								.getResourceMessage(
										"mantenimientoLETLideresForm.msg.campanasReingreso",
										new Object[] { respuestas[0] });

						f.setMensajeReingreo(texto);
						f.setMostrarMensajeReingreo(true);
					} else {
						this.addError(
								"Error:",
								this.getResourceMessage(
										"mantenimientoLETLideresForm.msg.campanasReingresoError",
										new Object[] { respuestas[0] }));
						return false;
					}
					/* FIN JJ PER-SiCC-2012-0201 */
					bGrabarCab = false;

				} else if (codigoRespuesta.equals("5")) {
					String[] datos = new String[] { unidadAdministrativa,
							f.getCodigoClienteBuscar() };

					String mensajeZona = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxZona";
					if (indicadorZona.equals("0"))
						mensajeZona = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxZonaAux";

					this.addError("Error:",
							this.getResourceMessage(mensajeZona, datos));
					bGrabarCab = false;

				} else if (codigoRespuesta.equals("6A")
						|| codigoRespuesta.equals("6B")
						|| codigoRespuesta.equals("6C")) {
					String[] datos = new String[] { unidadAdministrativa,
							valorRespuesta, f.getCodigoClienteBuscar() };
					String mensajeSeccion = "";
					if (codigoRespuesta.equals("6A")) {
						mensajeSeccion = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxSeccion";
						if (indicadorSeccion.equals("0"))
							mensajeSeccion = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxSeccionAux";

					} else {
						mensajeSeccion = "mantenimientoLETLideresForm.msg.minimoActivasFinalesxSeccion";
					}

					this.addError("Error:",
							this.getResourceMessage(mensajeSeccion, datos));
					bGrabarCab = false;
					return false;

				} else if (codigoRespuesta.equals("7")) {
					this.getResourceMessage(
							"mantenimientoLETLideresForm.msg.estatusLider",
							new Object[] { valorRespuesta });
					bGrabarCab = false;
					return false;
				} else if (codigoRespuesta.equals("8")) {
					this.addError(
							"Error:",
							this.getResourceMessage("mantenimientoLECLideresForm.mensaje.error.NoLiderEnLista"));
					bGrabarCab = false;
					return false;
				} else if (codigoRespuesta.equals("9")) {
					this.getResourceMessage(
							"mantenimientoLETLideresForm.msg.errorValidacion",
							new Object[] { valorRespuesta });
					bGrabarCab = false;
					return false;
				}

				if (codigoRespuesta.equals("0")) {
					if (!f.getCodigoClienteBuscar().equals(""))
						params.put("oidCliente", valorRespuesta);
					else
						params.put("oidCliente", "");

					// -- obtenemos el periodo actual
					LabelValue[] periodos = clienteService
							.getPeriodosVigentesByPaisMarcaCanal(params);

					// -- seteamos el periodo actual
					if (periodos != null && periodos.length > 0) {
						LabelValue base = periodos[periodos.length - 1];
						params.put("oidPeriodo", base.getValue());
					}

					// -- Setea la clasificacion de lideres
					params.put("codigoClasificacionLideres",
							Constants.CODIGO_CLASIFICACION_LIDERES);

					// -- captura fecha inicial por periodo de parametria pais
					Map criteriaOperacion = new HashMap();
					criteriaOperacion.put("codigoPais", f.getCodigoPais());
					criteriaOperacion.put("codigoMarca",
							Constants.CODIGO_MARCA_DEFAULT);
					criteriaOperacion.put("codigoCanal",
							Constants.CODIGO_CANAL_DEFAULT);
					criteriaOperacion.put("codigoPeriodo", periodoAsignarLider);
					String fechaAsignarLider = ((Base) reporteService
							.getListaGenerico(
									"getFechaInicioPeriodoByPaisMarcaCanal",
									criteriaOperacion).get(0)).getCodigo();

					String fechaFinal = periodoAsignarLider
							.equals(codigoPeriodoProceso) ? fechaProceso
							: fechaAsignarLider;
					if (StringUtils.equals(periodoAsignarLider,
							codigoPeriodoProceso)) {
						params.put("fechaFinal", fechaFinal);
						params.put("fechaFinalPrAl", Constants.NUMERO_UNO);
					} else {
						params.put("fechaFinal", fechaFinal);
						params.put("fechaFinalPrAl", null);
					}

					params.put("codigoSubgerencia", valorZR.substring(0, 2));
					params.put("codigoRegion", f.getCodigoRegion());
					params.put("codigoZona", f.getCodigoZona());
					params.put("codigoSeccion", valorZR.substring(8, 9));
					params.put("indOrigRegi", "C");
					// -- ejecutamos la asignacion del lider a la seccion
					service.execAsignacionLiderSeccion(params);

					if (f.getCodigoClienteBuscar().equals("")) {
						this.addInfo(
								"Info:",
								this.getResourceMessage(
										"mantenimientoLETLideresForm.msg.deasignacionCorrecta",
										new Object[] { unidadAdministrativa }));
					} else {
						String[] datos = new String[] {
								f.getCodigoClienteBuscar(),
								unidadAdministrativa, periodoAsignarLider };
						this.addInfo(
								"Info:",
								this.getResourceMessage(
										"mantenimientoLETLideresForm.msg.asignacionCorrecta",
										datos));
					}

					bGrabarCab = true;
				}

			} else {
				Map criteria = new HashMap();

				criteria.put("tipo", f.getTipo());
				criteria.put("codigoPais", f.getCodigoPais());
				Map obtenerFechaPeriodoProceso = (HashMap) interfazSiCCService
						.getPeriodoFechaProcesoActual(criteria).get(0);
				String codigoPeriodoProceso = (String) obtenerFechaPeriodoProceso
						.get("cod_peri");
				String fechaProceso = (String) obtenerFechaPeriodoProceso
						.get("fec_proc");

				criteria.put("codigoPeriodoProceso", codigoPeriodoProceso);
				criteria.put("fechaProceso", fechaProceso);
				criteria.put("codigoCliente", f.getCodigoClienteBuscar());
				criteria.put("codigoRegion", f.getCodigoRegion());
				criteria.put("codigoZona", f.getCodigoZona());
				criteria.put("usuario", usuario.getLogin());
				criteria.put("resultado", "");

				service.executeValidarAsignacionLiderRegionZona(criteria);

				String mensaje = (String) criteria.get("resultado");

				if (StringUtils.isBlank(mensaje)) {
					this.addError(
							"Info:",
							this.getResourceMessage(
									"mantenimientoLETLideresForm.mensaje.correcto.vinculacion",
									new Object[] { f.getCodigoClienteBuscar() }));
					f.setCodigoClienteBuscar("");
					f.setPermitirIngresoCodigoCliente(false);
				} else {
					this.addError("Error:", this.getResourceMessage(
							"mantenimientoLETLideresForm.mensaje.nocorrecto",
							new Object[] { mensaje }));
				}

			}

			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			// }

		} catch (InvalidAttributeValueException ex) {
			this.addError(
					"Error:",
					this.getResourceMessage("mantenimientoLETLideresForm.msg.errorInsertarClasificacion"));
		} catch (Exception e) {
			// e.printStackTrace();
			bGrabarCab = false;
			this.addError("Error:", this.getResourceMessage(
					"mantenimientoEDULocal.cabecera.error",
					new Object[] { e.getMessage() }));
		}

		if (bGrabarCab) {
			log.debug("info");

		}
		f.setPermitirIngresoCodigoCliente(false);

		if (StringUtils.equals(f.getIndicadorReingreso(), Constants.SI)) {
			f.setCodigoClienteBuscar("");
		}
		log.info("Salio MantenimientoLETLideresAction - setSaveAttributes");
		this.getRequestContext().execute("PF('showOpenPopupMotDes').hide");
		return bGrabarCab;
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
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;
		this.mostrarPanelCliente = false;
		this.mostrarBotones = true;

		MantenimientoLETLideresForm f = (MantenimientoLETLideresForm) formBusqueda;

		// -- Inicializar atributos ----------------------------------
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry()
				.getCodigo());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setPermitirIngresoCodigoCliente(true);
		f.setMostrarMensajeReingreo(false);
		f.setIndicadorNoValidaUnicoLider(Constants.NO);
		f.setIndicadorUnicoLiderSeccion(null);
		f.setCodigoRegion(Constants.OPCION_TODOS);
		f.setCodigoZona(Constants.OPCION_TODOS);

		// -- La busqueda solo la realizaremos en los sistemas activos

		// Captura de Periodo de Proceso
		MantenimientoOCRPedidoControlFacturacionService serviceCF = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = null;

		Map criteriaPeriodo = new HashMap();

		criteriaPeriodo.put("codigoPais", this.getmPantallaPrincipalBean()
				.getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.NUMERO_UNO);

		controlFacturacion = serviceCF
				.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		// /////////////////////////////////////////////////////////
		// Punto 1
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais paramPais = new ParametroPais();

		paramPais.setCodigoPais(this.getmPantallaPrincipalBean()
				.getCurrentCountry().getCodigo());
		paramPais.setCodigoSistema(Constants.ZON_CODIGO_SISTEMA);
		paramPais.setCodigoParametro(Constants.ZON_CODIGO_PARAM);
		paramPais.setNombreParametro(Constants.ZON_NOMB_PARAM);

		List lstParametros = genericoService.getParametrosPais(paramPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais result = (ParametroPais) lstParametros.get(0);
			this.letIndicadorZonBasPais = result.getValorParametro();
			if (StringUtils.equals(result.getValorParametro(),
					Constants.ZON_VALOR_PARAM)) {
				// Habilitar Region, Zonas y Seccion (Radio Button)
				this.letTipoRegion = Constants.LET_TIPO_HABILITADO;
				this.letTipoZona = Constants.LET_TIPO_HABILITADO;
				this.letTipoSeccion = Constants.LET_TIPO_HABILITADO;
				this.mostrarTodosRadioButtons = true;
			} else {
				// Solamente Habilitar Seccion (Redio Button)
				this.letTipoRegion = Constants.LET_TIPO_DESHABILITADO;
				this.letTipoZona = Constants.LET_TIPO_DESHABILITADO;
				this.letTipoSeccion = Constants.LET_TIPO_HABILITADO;
				this.mostrarTodosRadioButtons = false;
			}
		}

		// Punto 5.1

		paramPais = new ParametroPais();
		paramPais.setCodigoPais(this.getmPantallaPrincipalBean()
				.getCurrentCountry().getCodigo());
		paramPais.setCodigoSistema(Constants.LET_CODIGO_SISTEMA);
		paramPais.setCodigoParametro(Constants.LET_CODIGO_PARAM_001);
		paramPais.setNombreParametro(Constants.LET_NOMBRE_PARAM_001);

		List lstParametrosLET = genericoService.getParametrosPais(paramPais);

		if (lstParametrosLET != null && lstParametrosLET.size() > 0) {
			ParametroPais result = (ParametroPais) lstParametrosLET.get(0);
			f.setIndicadorAsignarLider(result.getValorParametro());
		} else {
			f.setIndicadorAsignarLider(Constants.NUMERO_CERO);
		}

		f.setTipo(null);
		f.setCodigoClienteBuscar("");
		log.info("Saliendo de MantenimientoLETLideresAction - view");
		inicializar();
		this.mostrarPanelCliente = false;
	}

	/**
	 * @param e
	 */
	public void guardarAsignacion(ActionEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("guardarAsignacion");
		}

	}

	/**
	 * @param e
	 */
	public void validarAsignarLideres(ActionEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("asignar");
		}
		AjaxService ajaxService = (AjaxService) this
				.getBeanService("ajaxService");
		MantenimientoLETLideresForm f = (MantenimientoLETLideresForm) this.formBusqueda;
		f.setCodigoClienteBuscar("");
		Map criteria = (HashMap) this.beanRegistroSeleccionado;
		if (criteria == null) {
			this.addError("Error:",
					this.getResourceMessage("errors.select.item"));
			return;
		} else {
			String codZona = MapUtils.getString(criteria, "codigo");
			if (StringUtils.isBlank(codZona)) {
				codZona = MapUtils.getString(criteria, "unidadAdministrativa");
			}
			String valor = ajaxService.validarAsignarLideres(f.getCodigoPais(),
					Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, codZona);
			if (!StringUtils.equals(valor, "1")) {
				this.addError("Error:", valor);
			} else {
				this.mostrarPopUpCliente = true;
				this.mostrarPanelCliente = true;
				this.mostrarBotones = false;
			}
		}
	}

	/**
	 * @param e
	 */
	public void validarDesvinculacion(ActionEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("validarDesvinculacion");
		}
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		MantenimientoLETLideresForm f = (MantenimientoLETLideresForm) this.formBusqueda;
		AjaxService ajaxService = (AjaxService) this
				.getBeanService("ajaxService");
		Map criteria = (HashMap) this.beanRegistroSeleccionado;
		f.setCodigoMotivoDesvinculacion("0");
		if (criteria == null) {
			this.addError("Error:",
					this.getResourceMessage("errors.select.item"));
			return;
		} else {
			String codUA = this.devuelveCodigoZonaRegionSeleccionado();
			if (codUA.length() == 9) {
				if (this.validarDesvincularLideres()) {
					// abrir popup
					if (this.indRegionCerrada.equals("1")) {
						String codigoCliente = devuelveClienteSeleccionado();
						if (StringUtils.isBlank(codigoCliente)) {
							this.addError(
									"Error:",
									this.getResourceMessage("mantenimientoLETLideresForm.msg.error.codigoCliente.empty"));
							return;
						}
						codigoCliente = codigoCliente + "";
						String codigoClienteSiguiente = devuelveClienteSiguienteSeleccionado();
						codigoClienteSiguiente = codigoClienteSiguiente + "";
						String datosCS[] = codigoClienteSiguiente.split("-");
						String codCS = datosCS[0];
						String nomCS = datosCS[1];

						boolean flag = false;

						String codigoZR = devuelveCodigoZonaRegionSeleccionado();
						String codigoRegion = "";
						String codigoZona = "";
						String codigoSeccion = "";
						if (codigoZR.length() == 2) {
							codigoRegion = codigoZR;
						} else if (codigoZR.length() == 4) {
							codigoZona = codigoZR;
						} else if (codigoZR.length() == 9) {
							codigoRegion = codigoZR.substring(2, 4);
							codigoZona = codigoZR.substring(4, 8);
							codigoSeccion = codigoZR.substring(8, 9);
						}

						if (codigoCliente == "null"
								&& codigoClienteSiguiente != "null") {
							codigoCliente = codCS.trim();
						}
						if (codigoCliente != null) {
							String indiAsignarLider = this.letIndicadorZonBasPais;
							String resultado = ajaxService.validarDesvinculo(
									f.getCodigoPais(), codigoRegion,
									codigoZona, codigoSeccion, f.getTipo(),
									codigoCliente, indiAsignarLider);

							codigoCliente = devuelveClienteSeleccionado();
							String codigoRegionZona = devuelveCodigoZonaRegionSeleccionado();

							if (codigoCliente == null) {
								codigoClienteSiguiente = devuelveClienteSiguienteSeleccionado();
								String patternString = "-";
								Pattern pattern = Pattern
										.compile(patternString);
								String codCSAux[] = pattern
										.split(codigoClienteSiguiente);
								codCS = codCSAux[0];
								nomCS = codCSAux[1];
								codigoCliente = codCS.trim();
							} else {
								this.codigoCliente = codigoCliente;
								this.codigoRegionZona = codigoRegionZona;
								beanMensaje = resultado;
								String ventanaConfirmar = "PF('idConfirmDialogMotDes_confirmDialogOpenPopup').show()";
								this.getRequestContext().execute(
										ventanaConfirmar);
								this.flag = true;
							}
						}
					}
				}
			}
		}
	}

	public boolean validarNextDesvinculacion(Map criteria) {
		if (log.isDebugEnabled()) {
			log.debug("validarDesvinculacion");
		}
		MantenimientoLETLideresForm f = (MantenimientoLETLideresForm) this.formBusqueda;
		f.setCodigoMotivoDesvinculacion("0");
		String codUA = MapUtils.getString(criteria, "codigo");
		if (StringUtils.isBlank(codUA)) {
			codUA = MapUtils.getString(criteria, "unidadAdministrativa");
		}
		if (codUA.length() == 9) {
			validarDesvincularLideres();
		} else {
			this.indRegionCerrada = "1";
		}
		if (StringUtils.equals(this.indRegionCerrada, "1")) {
			String codigoCliente = devuelveClienteSeleccionado();
			codigoCliente = codigoCliente + "";

			String codigoClienteSiguiente = devuelveClienteSiguienteSeleccionado();
			codigoClienteSiguiente = codigoClienteSiguiente + "";
			String patternString = "-";
			Pattern pattern = Pattern.compile(patternString);
			String datosCS[] = pattern.split(codigoClienteSiguiente);
			String codCS = datosCS[0];
			String nomCS = datosCS[1];

			boolean flag = false;
			if (codigoCliente == "null") {
				if (codigoClienteSiguiente == "null") {
					this.getResourceMessage("mantenimientoLETLideresForm.msg.error.codigoCliente.empty");
					return false;
				}
			}

		}
		return false;
	}

	public void openMotivoDesvinculacionPopup() {
		if (log.isDebugEnabled()) {
			log.debug("openMotivoDesvinculacionPopup");
		}
		if (this.isFlag()) {
			this.setFlag(false);
			this.getRequestContext().execute("PF('showOpenPopupMotDes').show");
		}
	}

	public String devuelveClienteSeleccionado() {
		if (log.isDebugEnabled()) {
			log.debug("devuelveClienteSeleccionado");
		}
		String valor = MapUtils.getString(
				(HashMap) this.beanRegistroSeleccionado, "codigoLider");
		return valor;
	}

	public String devuelveClienteSiguienteSeleccionado() {
		if (log.isDebugEnabled()) {
			log.debug("devuelveClienteSiguienteSeleccionado");
		}
		Map criteria = (HashMap) this.beanRegistroSeleccionado;
		return MapUtils.getString(criteria, "codigoNombreLiderSiguienteCamp");
	}

	public void valorSeleccionado(ValueChangeEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("valorSeleccionado");
		}
		// MantenimientoLETLideresForm f = (MantenimientoLETLideresForm)
		// this.formBusqueda;
		// String valor = (String)e.getNewValue();
		// if(StringUtils.isNotBlank(valor)
		// || StringUtils.isNotEmpty(valor)){
		// f.setCodigoMotivoDesvinculacion(valor);
		// log.debug("Codigo motivo seleccionado: " + valor);
		// }
	}

	public boolean validarDesvincularLideres() {
		if (log.isDebugEnabled()) {
			log.debug("validarDesvincularLideres");
		}
		AjaxService ajaxService = (AjaxService) this
				.getBeanService("ajaxService");

		String codZona = this.devuelveCodigoZonaRegionSeleccionado();
		String valor = ajaxService.validarAsignarLideres(this
				.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT,
				codZona);
		if (!StringUtils.equals(valor, "1")) {
			this.addError("Error:", valor);
			this.indRegionCerrada = "0";
			return false;
		} else {
			this.indRegionCerrada = "1";
			return true;
		}
	}

	public String devuelveCodigoZonaRegionSeleccionado() {
		if (log.isDebugEnabled()) {
			log.debug("devuelveCodigoZonaRegionSeleccionado");
		}
		String codigoUA = MapUtils.getString(
				(HashMap) this.beanRegistroSeleccionado, "codigo");
		if (StringUtils.isBlank(codigoUA)) {
			codigoUA = MapUtils.getString(
					(HashMap) this.beanRegistroSeleccionado,
					"unidadAdministrativa");
		}
		return codigoUA;
	}

	public void inicializar() {
		this.loadRegiones();
	}

	public void loadRegiones() {
		if (log.isDebugEnabled()) {
			log.debug("loadRegiones");
		}
		AjaxService ajaxService = (AjaxService) this
				.getBeanService("ajaxService");
		LabelValue[] listado = ajaxService.getRegionesByPais(this
				.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		this.setSiccRegionList(listado);
	}

	public void loadZonas(ValueChangeEvent value) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String valor = (String) value.getNewValue();
		if (valor.trim().length() > 0) {
			AjaxService ajaxService = (AjaxService) this
					.getBeanService("ajaxService");
			LabelValue[] listado = ajaxService.getZonasByPaisRegion(this
					.getmPantallaPrincipalBean().getCurrentCountry()
					.getCodigo(), valor);
			this.setSiccZonaList(listado);
		}
	}

	public void guardar(ActionEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("save");
		}
		try {
			this.setSaveAttributes();
			this.mostrarPanelCliente = false;
			this.mostrarBotones = true;
		} catch (Exception e1) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e1));
		}
	}

	private boolean realizaDesvinculacionLider(Map map,
			InterfazSiCCService interfazSiCCService,
			ConsultaPEJProgramaEjecutivasService servicePEJ,
			MantenimientoLETLideresService service,
			MantenimientoMAEClienteService clienteService,
			MantenimientoLETLideresForm f) throws Exception {

		boolean resultado = false;
		Map params = new HashMap();
		params.put("usuario", map.get("loginUsuario"));

		String codigo = (String) map.get("codigoRZ");
		String codigoZona = "";
		String codigoSeccion = "";
		if (codigo.length() == 4) {
			codigoZona = codigo;

			Map criteriaRegion = new HashMap();
			criteriaRegion.put("codigoPais", map.get("codigoPais"));
			criteriaRegion.put("codigoZona", codigoZona);
			List lista = interfazSiCCService.getLista("getRegionByZona",
					criteriaRegion);

			Base base = (Base) lista.get(0);
			codigo = base.getCodigo();
		} else if (codigo.length() == 9) {
			codigoSeccion = codigo.substring(8, 9);
			codigoZona = codigo.substring(4, 8);
			codigo = codigo.substring(2, 4);
		}
		// inicializando valor de codigo de motivo de vinculacion
		f.setCodigoMotivoDesvinculacion("0");
		params.put("codigoCliente", map.get("codigoClienteDes"));
		params.put("codigoPais", f.getCodigoPais());
		// params.put("codigoRegion",
		// session.getAttribute(Constants.LET_CODIGO_REGION_ELEGIDO));
		params.put("codigoRegion", codigo);
		params.put("codigoZona", codigoZona);
		params.put("codigoSeccion", codigoSeccion);
		params.put("tipo", f.getTipo());
		params.put("subGer", "01");
		// -- Capturar Periodo Actual ----------------------------
		List lista = interfazSiCCService.getPeriodoFechaProcesoActual(params);
		String periodoActual = ((HashMap) lista.get(0)).get("cod_peri")
				.toString();
		params.put("codigoPeriodoActual", periodoActual);

		// -- Colocar Fecha y Periodo de Proceso -----------------
		Map obtenerFechaPeriodoProceso = (HashMap) interfazSiCCService
				.getPeriodoFechaProcesoActual(params).get(0);
		String codigoPeriodoProceso = (String) obtenerFechaPeriodoProceso
				.get("cod_peri");
		String fechaProceso = (String) obtenerFechaPeriodoProceso
				.get("fec_proc");
		params.put("fechaProceso", fechaProceso);

		// -- Validar Indicador Asignar Lider --------------------
		int indicadorAsignarLider = Integer.valueOf(
				f.getIndicadorAsignarLider()).intValue();

		String codigoPeriodoProcesoSgte = servicePEJ.getObtienePeriodo(
				f.getCodigoPais(), codigoPeriodoProceso, indicadorAsignarLider);

		// -- captura periodo de parametria pais
		// String periodoAsignarLider = (indicadorAsignarLider == 0) ?
		// codigoPeriodoProceso : codigoPeriodoProcesoSgte;
		String periodoAsignarLider = "";
		boolean realizarEliminacionClasificacion = true;

		if (indicadorAsignarLider == 0) {
			periodoAsignarLider = codigoPeriodoProceso;
		} else if (indicadorAsignarLider == 1) {
			periodoAsignarLider = codigoPeriodoProcesoSgte;
		} else if (indicadorAsignarLider == 2) {
			boolean esRegionCerrada = false;
			Map criteria = new HashMap();
			criteria.put("codigoPeriodoActual", codigoPeriodoProceso);
			criteria.put("codigoRegion", f.getCodigoRegion());

			esRegionCerrada = clienteService.esRegionCerradaxSeccion(criteria);

			if (esRegionCerrada) {
				codigoPeriodoProcesoSgte = servicePEJ.getObtienePeriodo(
						f.getCodigoPais(), codigoPeriodoProceso, 1);
				periodoAsignarLider = codigoPeriodoProcesoSgte;
				realizarEliminacionClasificacion = false;
			} else {
				periodoAsignarLider = codigoPeriodoProceso;
			}
		}

		params.put("periodoAsignarLider", periodoAsignarLider);
		// params.put("periodoAsignarLiderSgte",codigoPeriodoProcesoSgte);
		params.put("periodoAsignarLiderSgte", periodoAsignarLider);
		params.put("indDesvAuto", MapUtils.getString(map,
				"codigoMotivoDesvinculacion", Constants.NUMERO_CERO));

		service.executeProcesoDesvinculacion(params);

		resultado = true;
		if (realizarEliminacionClasificacion)
			map.put("realizarEliminacionClasificacion", Constants.SI);
		else
			map.put("realizarEliminacionClasificacion", Constants.NO);
		return resultado;
	}

	public void reset(ActionEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("reset");
		}
		this.mostrarPanelCliente = false;
		this.mostrarBotones = true;
	}

	public void desvincular(ActionEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("desvincular");
		}
		MantenimientoLETLideresForm f = (MantenimientoLETLideresForm) this.formBusqueda;
		MantenimientoLETLideresService service = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ConsultaPEJProgramaEjecutivasService servicePEJ = (ConsultaPEJProgramaEjecutivasService) getBean("spusicc.consultaPEJProgramaEjecutivasService");

		Map params = new HashMap();

		try {
			Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
			params.put("loginUsuario", usuario.getLogin());
			params.put("codigoPais", this.getmPantallaPrincipalBean()
					.getCurrentCountry().getCodigo());
			params.put("codigoClienteDes", this.getCodigoCliente());
			params.put("codigoRZ", this.getCodigoRegionZona());
			params.put("realizarEliminacionClasificacion", Constants.SI);
			params.put("codigoMotivoDesvinculacion",
					f.getCodigoMotivoDesvinculacion());
			this.realizaDesvinculacionLider(params, interfazSiCCService,
					servicePEJ, service, clienteService, f);
			// -- Verifica si el responsable anterior tiene la clasificacion de
			// lider
			String realizarEliminacionClasificacion = (String) params
					.get("realizarEliminacionClasificacion");
			if (Constants.SI.equals(realizarEliminacionClasificacion)) {
				params.put("codigoLider", this.getCodigoCliente());
				service.deleteClasificacionLiderConsultoraAnterior(params);
			}
			this.addInfo(
					"Info:",
					this.getResourceMessage("mantenimientoLETLideresForm.mensaje.correcto"));
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.getRequestContext().execute("PF('showOpenPopupMotDes').hide");
		} catch (Exception e1) {
			this.addError("Ereror:", this.obtieneMensajeErrorException(e1));
		}
	}

	public List getLetLideresList() {
		return letLideresList;
	}

	public void setLetLideresList(List letLideresList) {
		this.letLideresList = letLideresList;
	}

	public String getLetIndicadorZonBasPais() {
		return letIndicadorZonBasPais;
	}

	public void setLetIndicadorZonBasPais(String letIndicadorZonBasPais) {
		this.letIndicadorZonBasPais = letIndicadorZonBasPais;
	}

	public boolean isLetTipoRegion() {
		return letTipoRegion;
	}

	public void setLetTipoRegion(boolean letTipoRegion) {
		this.letTipoRegion = letTipoRegion;
	}

	public boolean isLetTipoZona() {
		return letTipoZona;
	}

	public void setLetTipoZona(boolean letTipoZona) {
		this.letTipoZona = letTipoZona;
	}

	public boolean isLetTipoSeccion() {
		return letTipoSeccion;
	}

	public void setLetTipoSeccion(boolean letTipoSeccion) {
		this.letTipoSeccion = letTipoSeccion;
	}

	public boolean isMostrarTodosRadioButtons() {
		return mostrarTodosRadioButtons;
	}

	public void setMostrarTodosRadioButtons(boolean mostrarTodosRadioButtons) {
		this.mostrarTodosRadioButtons = mostrarTodosRadioButtons;
	}

	public String getLetCodigoRegionElegido() {
		return letCodigoRegionElegido;
	}

	public void setLetCodigoRegionElegido(String letCodigoRegionElegido) {
		this.letCodigoRegionElegido = letCodigoRegionElegido;
	}

	public String getLetCodigoZonaElegido() {
		return letCodigoZonaElegido;
	}

	public void setLetCodigoZonaElegido(String letCodigoZonaElegido) {
		this.letCodigoZonaElegido = letCodigoZonaElegido;
	}

	public String getLetListaLideresCodigo() {
		return letListaLideresCodigo;
	}

	public void setLetListaLideresCodigo(String letListaLideresCodigo) {
		this.letListaLideresCodigo = letListaLideresCodigo;
	}

	public Integer getIndicadorProgramaLet() {
		return indicadorProgramaLet;
	}

	public void setIndicadorProgramaLet(Integer indicadorProgramaLet) {
		this.indicadorProgramaLet = indicadorProgramaLet;
	}

	public boolean isPintarScrollLideres() {
		return pintarScrollLideres;
	}

	public void setPintarScrollLideres(boolean pintarScrollLideres) {
		this.pintarScrollLideres = pintarScrollLideres;
	}

	public String getTipobusquedaSession() {
		return tipobusquedaSession;
	}

	public void setTipobusquedaSession(String tipobusquedaSession) {
		this.tipobusquedaSession = tipobusquedaSession;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public String getIndRegionCerrada() {
		return indRegionCerrada;
	}

	public void setIndRegionCerrada(String indRegionCerrada) {
		this.indRegionCerrada = indRegionCerrada;
	}

	public String getBeanMensaje() {
		return beanMensaje;
	}

	public void setBeanMensaje(String beanMensaje) {
		this.beanMensaje = beanMensaje;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoRegionZona() {
		return codigoRegionZona;
	}

	public void setCodigoRegionZona(String codigoRegionZona) {
		this.codigoRegionZona = codigoRegionZona;
	}

	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}

	public boolean isMostrarBotones() {
		return mostrarBotones;
	}

	public void setMostrarBotones(boolean mostrarBotones) {
		this.mostrarBotones = mostrarBotones;
	}

	public boolean isMostrarPanelCliente() {
		return mostrarPanelCliente;
	}

	public void setMostrarPanelCliente(boolean mostrarPanelCliente) {
		this.mostrarPanelCliente = mostrarPanelCliente;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public BusquedaMAEClientePopupSearchAction getBusquedaMAEClientePopupSearchAction() {
		return busquedaMAEClientePopupSearchAction;
	}

	public void setBusquedaMAEClientePopupSearchAction(
			BusquedaMAEClientePopupSearchAction busquedaMAEClientePopupSearchAction) {
		this.busquedaMAEClientePopupSearchAction = busquedaMAEClientePopupSearchAction;
	}

	/**
	 * @return the mensajeLider
	 */
	public String getMensajeLider() {
		return mensajeLider;
	}

	/**
	 * @param mensajeLider
	 *            the mensajeLider to set
	 */
	public void setMensajeLider(String mensajeLider) {
		this.mensajeLider = mensajeLider;
	}

}

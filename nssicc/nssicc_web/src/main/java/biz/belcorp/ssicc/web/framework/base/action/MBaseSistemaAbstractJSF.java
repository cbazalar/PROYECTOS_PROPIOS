package biz.belcorp.ssicc.web.framework.base.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.AccesoCanal;
import biz.belcorp.ssicc.dao.sisicc.model.Actividad;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.dao.sisicc.model.Subacceso;
import biz.belcorp.ssicc.service.HistoricoAuditoriaService;
import biz.belcorp.ssicc.service.MenuService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.MPantallaPrincipalBean;

/**
 * @author cbazalar
 * 
 */
public abstract class MBaseSistemaAbstractJSF extends MBaseAbstractJSF {

	private static final long serialVersionUID = -3009427289377537151L;
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	// Map que contendra los parametros ingresados por request a la pantalla la
	// cual se cargaran en el
	// Post constructor del manage
	protected Map<String, String> parametrosPantalla;

	@ManagedProperty(value = "#{mPantallaPrincipalBean}")
	protected MPantallaPrincipalBean mPantallaPrincipalBean;

	protected String nombreManageBean;

	/* Indica la accion a tomar por el manage */
	protected String accion;
	protected String codigoMenu;
	protected String codigoProcesoBatch;

	protected String ACCION_INGRESAR = "INGRESO";
	protected String ACCION_BUSCAR = "BUSCAR";
	protected String ACCION_NUEVO = "NUEVO";
	protected String ACCION_MODIFICAR = "MODIFICAR";
	protected String ACCION_ELIMINAR = "ELIMINAR";
	protected String ACCION_CONSULTAR = "CONSULTAR";
	protected String ACCION_GRABAR = "GRABAR";
	protected String ACCION_EJECUTAR = "EJECUTAR";
	protected String ACCION_REPORTE = "REPORTE";
	protected String ACCION_OTRAS = "OTRAS";

	protected String accionFuncional = null;

	protected boolean mostrarProcesoBatch = true;
	protected boolean mostrarAyuda = false;
	protected boolean mostrarAuditoria = false;
	
	protected boolean activarHotkeyEstandar = true;
	protected boolean activarHotkeyEstandarInicial = true;
	protected boolean activarHotkeyEscape = true;
	protected boolean activarHotkeyBuscarPopupBusqueda = false;
	
	protected String anyoPeriodo;

	protected boolean mostrarCabeceraPantalla = true;
	protected boolean mostrarCabeceraFija = false; // Utilizado para mostrar los
													// criterios de busqueda en
													// la parte fija de la
													// pagina para que siempre
													// se muestre aunque se haga
													// scroll

	private String keyMensajeAlertaDefault = "mensaje.alert.default";

	private String mensajeAlertaDefault;
	private String mensajeAlertaDefaultAction;
	protected boolean validacionPrevia = false;
	protected boolean mostrarMantenimientoEnPopup = false;
	protected boolean mostrarBotonBuscar = true;
	protected boolean validarParametriaMenu = true;
	protected boolean mostrarBotonLimpiar = true;
	
	protected String paginaRutaCompletaXHTML;

	//url del popup de consulta, que debe de ser invocado desde la grilla
	protected String urlPopupConsulta;
	
	protected boolean exportarDataTableBusqueda = true;
	
	protected String cantidadRowsDefaultDatatable = "10";
	
	protected boolean limpiarFindDatatable = false;
	
    protected boolean activarGrabarWindowClose = false;
    protected boolean activarVentanaConfirmacionSave = true;
    protected boolean activarRefrescarPantallaLuegoAlertAction = false;
    
    private String nroSessionManage;
	
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		try {
			Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
			this.parametrosPantalla = new HashMap<String, String>();
			this.parametrosPantalla.putAll(parametros);
			this.adicionarManageListaSession();
			this.viewLogicaNegocio();
			

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}


	/**
	 * Adiciona Manage Bean a la lista de Manage Beans con su nro de Session
	 */
	private void adicionarManageListaSession() {
		List listaManageBeanSession = this.mPantallaPrincipalBean.getListaManageBeanSession();
		String nroSession = (String) this.parametrosPantalla.get("nroSession");
		this.nroSessionManage = nroSession;
		
		String manage = getClass().getSimpleName().toLowerCase().trim();
		
		if (StringUtils.isBlank(nroSession)) return;
		boolean adicionar = true;
		for (int i=1; i<listaManageBeanSession.size(); i++) {
			Base beanSession = (Base) listaManageBeanSession.get(i);
			String bnroSession = beanSession.getCodigo();
			String bmanage = beanSession.getDescripcion();
			
			if (StringUtils.equals(nroSession, bnroSession) && StringUtils.equals(manage, bmanage)) {
				adicionar = false;
			}
		}
		
		if (adicionar) {
			Base mapaSession = new Base();
			mapaSession.setCodigo(nroSession);
			mapaSession.setDescripcion(manage);
			listaManageBeanSession.add(mapaSession);
			this.mPantallaPrincipalBean.setListaManageBeanSession(listaManageBeanSession);
		}
	}
	
	
    /**
     * 
     */
    public void setLimpiarFind() {
		
	}
	
	/**
	 * Metodo con la logica de negocio del Metodo View
	 */
	public final void viewLogicaNegocio() throws Exception {
		this.accion = (String) this.parametrosPantalla.get("accion");
		this.codigoMenu = (String) this.parametrosPantalla
				.get("codigoMenu");
		this.codigoProcesoBatch = (String) this.parametrosPantalla
				.get("codigoProcesoBatch");
		this.mostrarProcesoBatch = true;
		this.mostrarAyuda = false;
		this.mostrarAuditoria = false;
		this.validacionPrevia = false;
		this.mPantallaPrincipalBean.setCurrentMenu(this.codigoMenu);
		log.debug("this.parametrosPantalla PARAMETROS PANTALLA:"
				+ this.parametrosPantalla);
		log.debug("this.accion:" + this.accion);

		this.nombreManageBean = this.getClass().getSimpleName();
		this.obtenerPaginaAyudaPantalla();
		this.paginaRutaCompletaXHTML = this.mPantallaPrincipalBean.getPaginaRutaCompletaXHTML();
		if (this.mPantallaPrincipalBean != null) {
			this.setAnyoPeriodo(this.mPantallaPrincipalBean
					.getAnyoActualperiodo());
		}
		
		
		this.setAntesBeforeViewAtributes();
		this.setBeforeViewAtributes();
		this.setViewAtributes();
		
		this.activarHotkeyEstandarInicial = this.activarHotkeyEstandar;
		
	}
	
	/**
	 * Ejecuta Limpiar Formulario
	 */
	public void limpiar(ActionEvent actionEvent) {
		try {
			
			this.mPantallaPrincipalBean.ingresarOpcionMenu03();
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Ejecuta Limpiar Formulario
	 */
	public String limpiar() {
		this.mPantallaPrincipalBean.setNroSessionManageLimpiar(this.nroSessionManage);
		String retorno = this.mPantallaPrincipalBean.ingresarOpcionMenu03();
		this.setLimpiarAtributes();
		return retorno;
	}
	
	/**
	 * Metodo de Inicializacion Personalizada de la Clase ManageBeans, cuando se hace click en Boton Limpiar
	 * 
	 * @throws Exception
	 */
	protected void setLimpiarAtributes()  {
	
	}

	/**
	 * Metodo que libera memoria una vez que se cierra el ManageBean
	 * 
	 * @param nombreManageBean
	 */
	public void salirManageBean(String nombreManageBean) {
		this.mPantallaPrincipalBean.removeSessionManageBeanTodos();
	}

	
	/**
	 * Metodo de Inicializacion Personalizada de la Clase ManageBeans, cuando se
	 * ingresa a la Opción
	 * 
	 * @throws Exception
	 */
	protected abstract void setViewAtributes() throws Exception;

	
	/**
	 * @throws Exception
	 */
	protected void setAntesBeforeViewAtributes() throws Exception {
		
	}
	
	/**
	 * @throws Exception
	 */
	protected void setBeforeViewAtributes() throws Exception {

	}

	/**
	 * Metodo que agrega la opcion del Menu a la lista de Opciones Favoritas del
	 * Usuario
	 */
	public void agregarFavoritos(ActionEvent actionEvent) {
		log.debug("this.accion: MBaseSistemaAbstractJSF.agregarFavoritos");
		try {

			String currentMenu = this.codigoMenu; // this.mPantallaPrincipalBean.getCurrentMenu();
			log.debug("currentMenu : " + currentMenu);

			if (StringUtils.isNotBlank(currentMenu)) {
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

				MenuService service = (MenuService) this
						.getBeanService("menuService");
				Menu menu = service.getMenuJSF(currentMenu, usuario.getIdioma()
						.getCodigo());
				boolean existe = this.mPantallaPrincipalBean
						.getListaMenuFavoritos().contains(menu);
				if (!existe)
					this.mPantallaPrincipalBean.getListaMenuFavoritos().add(
							menu);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo para establecer la logica del RowSelect de un DataWindows
	 */
	public void ejecutarRowSelect(String accion) {
		log.debug("parametroEjecutarRowSelect:" + accion);
		this.setEjecutarRowSelect(accion);
	}

	/**
	 * Metodo Hook a sobreescribir para establecer la logica del RowSelect de un
	 * DataWindows
	 * 
	 * @param accion
	 */
	protected void setEjecutarRowSelect(String accion) {

	}

	/**
	 * Metodo para establecer la logica del DBClick de un DataWindows
	 * 
	 * @param accion
	 */
	public void ejecutarDBClick(String accion) {
		log.debug("parametroEjecutarDBClick:" + accion);
		this.setEjecutarDBClick(accion);
	}

	/**
	 * Metodo Hook a sobreescribir para establecer la logica del DBCick de un
	 * DataWindows
	 * 
	 * @param accion
	 */
	protected void setEjecutarDBClick(String accion) {

	}

	/**
	 * Mensaje Estandar de Eliminacion
	 * 
	 * @return
	 */
	protected String devuelveMensajeKeyEliminarOK() {
		return "datos.delete.ok";
	}

	/**
	 * Metodo que se ejecuta al hacer click en el Boton Aceptar de un Popup de
	 * Busqueda
	 * 
	 * @param event
	 */
	public void aceptarPopup(ActionEvent event) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			String accion = externalContext.getRequestParameterMap().get(
					"parametroAccion");
			log.debug("parametroAccion:" + accion);
			RequestContext.getCurrentInstance().reset("principalForm");
			this.setAceptarPopup(event, accion);
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
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
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String accion = externalContext.getRequestParameterMap().get("parametroAccion");
			this.mPantallaPrincipalBean.setPantallaAdicionalesXhtml("/templates/templatePopupCabeceraEast.xhtml");
			log.debug("parametroAccion:" + accion);
			this.setInvocarPopup(accion);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo que se ejecuta al hacer click en el Boton Salir del Popup para
	 * mostrar el popup respectivo
	 * 
	 * @param event
	 */
	public void salirPopup(ActionEvent event) {
		try {
			this.setSalirPopup();
			this.mPantallaPrincipalBean
					.setPantallaAdicionalesXhtml(this.PAGINA_ADICIONAL_BASE);
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo Hook que se debe sobreescribir con la logica respectiva al hacer
	 * click en el boton Aceptar del Popup
	 * 
	 * @param accion
	 */
	protected void setAceptarPopup(ActionEvent event, String accion) {

	}

	/**
	 * Metodo Hook que se debe sobreescribir con la logica respectiva al hacer
	 * click en el boton de Busqueda de Popup
	 * 
	 * @param accion
	 */
	protected void setInvocarPopup(String accion) {

	}

	/**
	 * Metodo Hook que se debe sobreescribir con la logica respectiva al hacer
	 * click en el boton Salir del Popup
	 * 
	 * @param accion
	 */
	protected void setSalirPopup() {

	}

	/**
	 * Muestra Contenido con referencia a la Ayuda de la Opcion Seleccionada
	 * 
	 * @param event
	 */
	public void mostrarContenidoAyudaOpcion(ActionEvent event) {
		this.mPantallaPrincipalBean.setPantallaAdicionalesXhtml(this.paginaAyudaPantalla);
	}

	/**
	 * Muestra Contenido con referencia a la Auditoria del Usuario logueado
	 * 
	 * @param event
	 */
	public void mostrarContenidoAuditoriaOpcion(ActionEvent event) {

		/* Se verifica que el usuario haya emitido reportes durante el dia */
		this.mPantallaPrincipalBean.generarListaReportesEmitidos();

		/* Se verifica que el usuario haya ingresado a opciones durante el dia */
		this.mPantallaPrincipalBean.generarListaHistoricoAuditoria();

		this.mPantallaPrincipalBean
				.setPantallaAdicionalesXhtml(this.PAGINA_AUDITORIA_SISTEMA);
	}

	/**
	 * Metodo que registra la Auditoria de los procesos ejecutados
	 * 
	 * @param historicoAuditoria
	 * @throws Exception TODO
	 */
	public void grabarAuditoriaUsuarioIni(HistoricoAuditoria historicoAuditoria) throws Exception {
		    String codigoMenu = this.mPantallaPrincipalBean.getCurrentMenu();
		    this.grabarAuditoriaUsuarioIni(historicoAuditoria, codigoMenu);
	}
	
	/**
	 * @param historicoAuditoria
	 * @param codigoMenu
	 * @throws Exception
	 */
	public void grabarAuditoriaUsuarioIni(HistoricoAuditoria historicoAuditoria, String codigoMenu) throws Exception {
	    if(StringUtils.isBlank(codigoMenu)) return;

        String ip = this.mPantallaPrincipalBean.getCurrentUser().getIpMaquinaRemota();
	    String codigoMenu02 = codigoMenu.trim();
        historicoAuditoria.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		historicoAuditoria.setCodigoMenu(codigoMenu02);
		historicoAuditoria.setIpMaquina(ip);
		historicoAuditoria.setFechaInicio(new Timestamp(System.currentTimeMillis()));
		historicoAuditoria.setCodigoUsuario(this.mPantallaPrincipalBean.getCurrentUser().getLogin());

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) this.getBeanService("sisicc.interfazSiCCService");
		historicoAuditoria.setCodigoPeriodo(interfazSiCCService
				.getPeriodoDefaultByPaisCanal(
						historicoAuditoria.getCodigoPais(),
						Constants.CODIGO_CANAL_DEFAULT));
		
		HistoricoAuditoriaService historicoAuditoriaService = (HistoricoAuditoriaService) this.getBeanService("sisicc.historicoAuditoriaService");
		historicoAuditoriaService.insertHistoricoAuditoria(historicoAuditoria);
    }

	/**
	 * Metodo que actualiza la hora final y el mensaje de exito/error del
	 * proceso
	 * 
	 * @param historicoAuditoria
	 */
	public void grabarAuditoriaUsuarioFin(HistoricoAuditoria historicoAuditoria)  {
        String codigoMenu = historicoAuditoria.getCodigoMenu();
        if(StringUtils.isBlank(codigoMenu)) return;
		HistoricoAuditoriaService historicoAuditoriaService = (HistoricoAuditoriaService) this
				.getBeanService("sisicc.historicoAuditoriaService");
		historicoAuditoria
				.setFechaFin(new Timestamp(System.currentTimeMillis()));
		historicoAuditoria
				.setDuracionSegundos((long) (Math.round((historicoAuditoria
						.getFechaFin().getTime() - historicoAuditoria
						.getFechaInicio().getTime()) / 1000)));

		historicoAuditoriaService.updateHistoricoAuditoria(historicoAuditoria);

	}

	/**
	 * Metodo que prepara los parametros a pasar al Service para la ejecucion.
	 * Remueve parametros innecesarios generados en la capa web, y setea los
	 * parametros necesarios para la capa service. Este metodo puede ser
	 * sobreescrito en caso se requieran mas parametros.
	 * 
	 * @param params
	 * @param form
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		// Paso todos los parametros al map
		params = BeanUtils.describe(form);

		// Agrego los parametros necesarios
		params.put("usuario", mPantallaPrincipalBean.getCurrentUser());
		params.put("codigoUsuario", mPantallaPrincipalBean.getCurrentUser()
				.getLogin());
		params.put("pais", mPantallaPrincipalBean.getCurrentCountry());
		params.put("descripcionPais", mPantallaPrincipalBean
				.getCurrentCountry().getDescripcion());

		// Remuevo los parametros que no son necesarios del Validator
		params.remove("resultValueMap");
		params.remove("validatorResults");
		params.remove("servletWrapper");
		params.remove("multipartRequestHandler");
		params.remove("class");
		params.remove("page");
		params.remove("parametrosPantalla");
		
		return params;
	}

	// Descripcion Múltiple para imprimir en los reportes
	// variables String[],List
	public String descripcionMultipleLista(String[] datos, List lista) {
		String descripcionLista = "";
		String descripcion = "";
		String[] wdatos = datos;
		for (int i = 0; i < wdatos.length; i++) {
			String dato = wdatos[i];
			for (int j = 0; j < lista.size(); j++) {
				Base base = (Base) lista.get(j);
				String codigo = base.getCodigo();
				if (dato.equals(codigo)) {
					descripcion = base.getDescripcion();
					descripcionLista = descripcionLista + descripcion + "\n";
					break;
				}
			}
		}
		return descripcionLista;
	}

	// Descripcion Múltiple para imprimir en los reportes
	// variables String[],LabelValue[]
	public String descripcionMultipleLista(String[] datos, LabelValue[] lista) {
		String descripcionList = "";
		String descripcion = "";
		String[] wdatos = datos;
		for (int i = 0; i < wdatos.length; i++) {
			String dato = wdatos[i];
			for (int j = 0; j < lista.length; j++) {
				String codigo = lista[j].getValue();
				if (dato.equals(codigo)) {
					descripcion = lista[j].getLabel();
					descripcionList = descripcionList + descripcion + "\n";
					break;
				}
			}

		}
		return descripcionList;
	}

	// Método para obtener la lista de Acceso
	public List getAccesoList(String valor) {
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List listaAcceso = new ArrayList();
		List lista = new ArrayList();
		listaAcceso = svc.getAccesosByCanalByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		String cod = "";
		for (int j = 0; j < listaAcceso.size(); j++) {
			cod = ((AccesoCanal) listaAcceso.get(j)).getCodigoCanal();
			if (cod.equals(valor)) {
				lista.add(listaAcceso.get(j));
			}

		}
		return lista;
	}

	// Descripcion Simple para imprimir en los reportes
	// variables String[],LabelValue[]
	public String descripcionSimpleLista(String dato, LabelValue[] lista) {
		String descripcionList = "";
		String descripcion = "";
		for (int j = 0; j < lista.length; j++) {
			String codigo = lista[j].getValue();
			if (dato.equals(codigo)) {
				descripcion = lista[j].getLabel();
				descripcionList = descripcionList + descripcion;
				break;
			}
		}
		return descripcionList;
	}

	// Descripcion SIMPLE para imprimir en los reportes
	// variables String[],List
	public String descripcionSimpleLista(String dato, List lista) {
		String descripcionLista = "";
		String descripcion = "";
		for (int j = 0; j < lista.size(); j++) {
			Base base = (Base) lista.get(j);
			String codigo = base.getCodigo();
			if (dato.equals(codigo)) {
				descripcion = base.getDescripcion();
				descripcionLista = descripcionLista + descripcion;
				break;
			}
		}
		return descripcionLista;
	}

	// Método para obtener lista Actividad que depende del país, marca, canal
	// variables String pais,String marca, String canal
	public List getActividadByPaisByMarcaByCanal(String pais, String marca,
			String canal) {
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List lista = new ArrayList();
		List siccActividadList = new ArrayList();
		siccActividadList = svc.getActividadesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		for (int j = 0; j < siccActividadList.size(); j++) {
			String codpaisAct = ((Actividad) siccActividadList.get(j))
					.getCodigoPais();
			String codmarcaAct = ((Actividad) siccActividadList.get(j))
					.getCodigoMarca();
			String codcanalAct = ((Actividad) siccActividadList.get(j))
					.getCodigoCanal();

			if ((codpaisAct.equals(pais) || codpaisAct.equals(StringUtils
					.isBlank(pais)))
					&& (codmarcaAct.equals(marca) || codmarcaAct
							.equals(StringUtils.isBlank(marca)))
					&& (codcanalAct.equals(canal) || codcanalAct
							.equals(StringUtils.isBlank(canal)))) {
				lista.add(siccActividadList.get(j));
			}

		}
		return lista;
	}

	// Método para obtener la lista de SubAcceso
	public List getSubAccesoList(String valor) {
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List listaSubAcceso = new ArrayList();
		List lista = new ArrayList();
		listaSubAcceso = svc.getSubaccesosByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		String cod = "";
		for (int j = 0; j < listaSubAcceso.size(); j++) {
			cod = ((Subacceso) listaSubAcceso.get(j)).getCodigoAcceso();
			if (cod.equals(valor)) {
				lista.add(listaSubAcceso.get(j));
			}

		}
		return lista;
	}

	/**
	 * Metodo que permite mostrar el mensaje personalizado.
	 */
	public void mostrarDialogoGeneral() {
		this.getRequestContext().execute("PF('idblockEspereBody').unblock()");
		String ventana = "PF('principalFormAlert_alertDialog').show()";
		this.getRequestContext().execute(ventana);
	}
	
	/**
	 * @param paginaXhtml
	 * @param options
	 */
	public void mostrarPaginaEnDialogPopup(String paginaXhtml, Map<String,Object> options) {
		if (options == null) {
			options = new HashMap<String, Object>();
			options.put("modal", true);
			options.put("draggable", true);
			options.put("resizable", true);
			options.put("closable", false);
			options.put("contentHeight", 220);
			options.put("contentWidth", 820);
		}
		RequestContext.getCurrentInstance().openDialog(paginaXhtml, options, null);
	}
	
	/**
	 * Validacion de Email
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
	
	/**
	 * @param actionEvent
	 */
	public void confirmar(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'confirmar' method");
		}
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String accion = externalContext.getRequestParameterMap().get("parametroAccion");
			String ventana = externalContext.getRequestParameterMap().get("parametroVentana");
			if(!this.validarConfirmar(accion)){
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return ;
			}
			String alertas = this.setAlertasAntesdeConfirmar(accion);
			if (StringUtils.isNotBlank(alertas)) {
				this.addWarn("Información:", alertas);
			}
			String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventanaConfirmar);
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		return;
	}	
	
	/**
	 * @param actionEvent
	 */
	public void reconfirmar(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'reconfirmar' method");
		}
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("parametroAccion");
		String ventana = externalContext.getRequestParameterMap().get("parametroVentana");
		
		String []ventanas = StringUtils.split(ventana, "|");
		
		String ventana1 = ventanas[0];
		String ventana2 = ventanas[1];
		
		if(!this.validarConfirmar(accion)){
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return ;
		}
		
		if(!this.validarReconfirmar(accion)){
			String ventanaConfirmar = "PF('" + ventana2 + "_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return;
		}
		
		String ventanaConfirmar = "PF('" + ventana1 + "_confirmationDialogConfirmar').show()";
		this.getRequestContext().execute(ventanaConfirmar);
		return;
	}	
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar alguna operacion
	 * @return
	 */
	private boolean validarConfirmar(String accion){
		boolean validacion = true;	
		String lsMensajeError = this.setValidarConfirmar(accion);
		if (StringUtils.isNotBlank(lsMensajeError)) {
			validacion = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return validacion;
	}

	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar alguna operacion
	 * @return
	 */
	private boolean validarReconfirmar(String accion){
		boolean validacion = true;	
		String lsMensajeError = this.setValidarReconfirmar(accion);
		if (StringUtils.isNotBlank(lsMensajeError)) {
			validacion = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return validacion;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar alguna operacion
	 * Devuelve Mensaje de error personalizado de validacion extra antes de realizar alguna operacion
	 * @return
	 */
	public String setValidarConfirmar(String accion){
		return "";
	}

	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar alguna operacion
	 * Devuelve Mensaje de error personalizado de validacion extra antes de realizar alguna operacion
	 * @return
	 */
	public String setValidarReconfirmar(String accion){
		return "";
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar alguna operacion, igual MUESTRA LA VENTANA DE CONIFRMACION
	 * Devuelve Mensaje de error personalizado de validacion extra antes de realizar alguna operacion
	 * @return
	 */
	public String setAlertasAntesdeConfirmar(String accion){
		return "";
	}
	
	
	/**
	 * @param actionEvent
	 */
	public void confirmarAlertDialogAction(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'confirmarAlertDialogAction' method");
		}
		
		/* Redireccionando a la pagina respectiva */
		try {
			
			this.setConfirmarAlertDialogAction();
			
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'confirmarAlertDialogAction' method");
		}
		return;
	}
	
	
	/**
	 * 
	 */
	public void setConfirmarAlertDialogAction() throws Exception {
		
	}
	
	/**
	 * @param actionEvent
	 */
	public void activarEnterBusqueda(ActionEvent actionEvent) {
		try {
			
			this.activarHotkeyEstandar = this.activarHotkeyEstandarInicial;
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	

	/* GET - SET ATRIBUTOS */
	/**
	 * @return the mPantallaPrincipalBean
	 */
	public MPantallaPrincipalBean getmPantallaPrincipalBean() {
		return mPantallaPrincipalBean;
	}

	/**
	 * @param mPantallaPrincipalBean
	 *            the mPantallaPrincipalBean to set
	 */
	public void setmPantallaPrincipalBean(
			MPantallaPrincipalBean mPantallaPrincipalBean) {
		this.mPantallaPrincipalBean = mPantallaPrincipalBean;
	}

	/**
	 * @return the parametrosPantalla
	 */
	public Map<String, String> getParametrosPantalla() {
		return parametrosPantalla;
	}

	/**
	 * @param parametrosPantalla
	 *            the parametrosPantalla to set
	 */
	public void setParametrosPantalla(Map<String, String> parametrosPantalla) {
		this.parametrosPantalla = parametrosPantalla;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion
	 *            the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the codigoMenu
	 */
	public String getCodigoMenu() {
		return codigoMenu;
	}

	/**
	 * @param codigoMenu
	 *            the codigoMenu to set
	 */
	public void setCodigoMenu(String codigoMenu) {
		this.codigoMenu = codigoMenu;
	}

	/**
	 * @return the nombreManageBean
	 */
	public String getNombreManageBean() {
		return nombreManageBean;
	}

	/**
	 * @param nombreManageBean
	 *            the nombreManageBean to set
	 */
	public void setNombreManageBean(String nombreManageBean) {
		this.nombreManageBean = nombreManageBean;
	}

	/**
	 * @return the mostrarProcesoBatch
	 */
	public boolean isMostrarProcesoBatch() {
		return mostrarProcesoBatch;
	}

	/**
	 * @param mostrarProcesoBatch
	 *            the mostrarProcesoBatch to set
	 */
	public void setMostrarProcesoBatch(boolean mostrarProcesoBatch) {
		this.mostrarProcesoBatch = mostrarProcesoBatch;
	}

	/**
	 * @return the codigoProcesoBatch
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch
	 *            the codigoProcesoBatch to set
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	/**
	 * @return the mostrarAyuda
	 */
	public boolean isMostrarAyuda() {
		return mostrarAyuda;
	}

	/**
	 * @param mostrarAyuda
	 *            the mostrarAyuda to set
	 */
	public void setMostrarAyuda(boolean mostrarAyuda) {
		this.mostrarAyuda = mostrarAyuda;
	}

	public boolean isMostrarAuditoria() {
		return mostrarAuditoria;
	}

	public void setMostrarAuditoria(boolean mostrarAuditoria) {
		this.mostrarAuditoria = mostrarAuditoria;
	}

	/**
	 * @return the accionFuncional
	 */
	public String getAccionFuncional() {
		return accionFuncional;
	}

	/**
	 * @param accionFuncional
	 *            the accionFuncional to set
	 */
	public void setAccionFuncional(String accionFuncional) {
		this.accionFuncional = accionFuncional;
	}

	/**
	 * @return the anyoPeriodo
	 */
	public String getAnyoPeriodo() {
		return anyoPeriodo;
	}

	/**
	 * @param anyoPeriodo
	 *            the anyoPeriodo to set
	 */
	public void setAnyoPeriodo(String anyoPeriodo) {
		this.anyoPeriodo = anyoPeriodo;
	}

	public boolean isMostrarCabeceraPantalla() {
		return mostrarCabeceraPantalla;
	}

	public void setMostrarCabeceraPantalla(boolean mostrarCabeceraPantalla) {
		this.mostrarCabeceraPantalla = mostrarCabeceraPantalla;
	}

	/**
	 * @return the keyMensajeAlertaDefault
	 */
	public String getKeyMensajeAlertaDefault() {
		return keyMensajeAlertaDefault;
	}

	/**
	 * @param keyMensajeAlertaDefault
	 *            the keyMensajeAlertaDefault to set
	 */
	public void setKeyMensajeAlertaDefault(String keyMensajeAlertaDefault) {
		this.keyMensajeAlertaDefault = keyMensajeAlertaDefault;
		this.mensajeAlertaDefault = this
				.getResourceMessage(this.keyMensajeAlertaDefault);
	}

	/**
	 * @return the mensajeAlertaDefault
	 */
	public String getMensajeAlertaDefault() {
		return mensajeAlertaDefault;
	}

	public void setMensajeAlertaDefault(String mensajeAlertaDefault) {
		this.mensajeAlertaDefault = mensajeAlertaDefault;
	}

	public boolean isMostrarCabeceraFija() {
		return mostrarCabeceraFija;
	}

	public void setMostrarCabeceraFija(boolean mostrarCabeceraFija) {
		this.mostrarCabeceraFija = mostrarCabeceraFija;
	}

	/**
	 * @return the activarHotkeyEstandar
	 */
	public boolean isActivarHotkeyEstandar() {
		return activarHotkeyEstandar;
	}

	/**
	 * @param activarHotkeyEstandar
	 *            the activarHotkeyEstandar to set
	 */
	public void setActivarHotkeyEstandar(boolean activarHotkeyEstandar) {
		this.activarHotkeyEstandar = activarHotkeyEstandar;
	}

	/**
	 * @return the validacionPrevia
	 */
	public boolean isValidacionPrevia() {
		return validacionPrevia;
	}

	/**
	 * @param validacionPrevia
	 *            the validacionPrevia to set
	 */
	public void setValidacionPrevia(boolean validacionPrevia) {
		this.validacionPrevia = validacionPrevia;
	}
	
	/**
	 * @return the mostrarMantenimientoEnPopup
	 */
	public boolean isMostrarMantenimientoEnPopup() {
		return mostrarMantenimientoEnPopup;
	}


	/**
	 * @param mostrarMantenimientoEnPopup the mostrarMantenimientoEnPopup to set
	 */
	public void setMostrarMantenimientoEnPopup(boolean mostrarMantenimientoEnPopup) {
		this.mostrarMantenimientoEnPopup = mostrarMantenimientoEnPopup;
	}

	/**
	 * @return the mostrarBotonBuscar
	 */
	public boolean isMostrarBotonBuscar() {
		return mostrarBotonBuscar;
	}

	/**
	 * @param mostrarBotonBuscar the mostrarBotonBuscar to set
	 */
	public void setMostrarBotonBuscar(boolean mostrarBotonBuscar) {
		this.mostrarBotonBuscar = mostrarBotonBuscar;
	}
	
	/**
	 * @return the urlPopupConsulta
	 */
	public String getUrlPopupConsulta() {
		return urlPopupConsulta;
	}

	/**
	 * @param urlPopupConsulta the urlPopupConsulta to set
	 */
	public void setUrlPopupConsulta(String urlPopupConsulta) {
		this.urlPopupConsulta = urlPopupConsulta;
	}

	/**
	 * @return the validarParametriaMenu
	 */
	public boolean isValidarParametriaMenu() {
		return validarParametriaMenu;
	}

	/**
	 * @param validarParametriaMenu the validarParametriaMenu to set
	 */
	public void setValidarParametriaMenu(boolean validarParametriaMenu) {
		this.validarParametriaMenu = validarParametriaMenu;
	}

	/**
	 * @return the mensajeAlertaDefaultAction
	 */
	public String getMensajeAlertaDefaultAction() {
		return mensajeAlertaDefaultAction;
	}

	/**
	 * @param mensajeAlertaDefaultAction the mensajeAlertaDefaultAction to set
	 */
	public void setMensajeAlertaDefaultAction(String mensajeAlertaDefaultAction) {
		this.mensajeAlertaDefaultAction = mensajeAlertaDefaultAction;
	}

	/**
	 * @return the paginaRutaCompletaXHTML
	 */
	public String getPaginaRutaCompletaXHTML() {
		return paginaRutaCompletaXHTML;
	}

	/**
	 * @param paginaRutaCompletaXHTML the paginaRutaCompletaXHTML to set
	 */
	public void setPaginaRutaCompletaXHTML(String paginaRutaCompletaXHTML) {
		this.paginaRutaCompletaXHTML = paginaRutaCompletaXHTML;
	}

	/**
	 * @return the mostrarBotonLimpiar
	 */
	public boolean isMostrarBotonLimpiar() {
		return mostrarBotonLimpiar;
	}

	/**
	 * @param mostrarBotonLimpiar the mostrarBotonLimpiar to set
	 */
	public void setMostrarBotonLimpiar(boolean mostrarBotonLimpiar) {
		this.mostrarBotonLimpiar = mostrarBotonLimpiar;
	}

	/**
	 * @return the exportarDataTableBusqueda
	 */
	public boolean isExportarDataTableBusqueda() {
		return exportarDataTableBusqueda;
	}

	/**
	 * @param exportarDataTableBusqueda the exportarDataTableBusqueda to set
	 */
	public void setExportarDataTableBusqueda(boolean exportarDataTableBusqueda) {
		this.exportarDataTableBusqueda = exportarDataTableBusqueda;
	}


	/**
	 * @return the activarHotkeyEscape
	 */
	public boolean isActivarHotkeyEscape() {
		return activarHotkeyEscape;
	}


	/**
	 * @param activarHotkeyEscape the activarHotkeyEscape to set
	 */
	public void setActivarHotkeyEscape(boolean activarHotkeyEscape) {
		this.activarHotkeyEscape = activarHotkeyEscape;
	}


	/**
	 * @return the activarHotkeyBuscarPopupBusqueda
	 */
	public boolean isActivarHotkeyBuscarPopupBusqueda() {
		return activarHotkeyBuscarPopupBusqueda;
	}


	/**
	 * @param activarHotkeyBuscarPopupBusqueda the activarHotkeyBuscarPopupBusqueda to set
	 */
	public void setActivarHotkeyBuscarPopupBusqueda(
			boolean activarHotkeyBuscarPopupBusqueda) {
		this.activarHotkeyBuscarPopupBusqueda = activarHotkeyBuscarPopupBusqueda;
	}

	
	/**
	 * @return the cantidadRowsDefaultDatatable
	 */
	public String getCantidadRowsDefaultDatatable() {
		return cantidadRowsDefaultDatatable;
	}

	/**
	 * @param cantidadRowsDefaultDatatable the cantidadRowsDefaultDatatable to set
	 */
	public void setCantidadRowsDefaultDatatable(String cantidadRowsDefaultDatatable) {
		this.cantidadRowsDefaultDatatable = cantidadRowsDefaultDatatable;
	}

	/**
	 * @return the limpiarFindDatatable
	 */
	public boolean isLimpiarFindDatatable() {
		return limpiarFindDatatable;
	}

	/**
	 * @param limpiarFindDatatable the limpiarFindDatatable to set
	 */
	public void setLimpiarFindDatatable(boolean limpiarFindDatatable) {
		this.limpiarFindDatatable = limpiarFindDatatable;
	}


	/**
	 * @return the activarGrabarWindowClose
	 */
	public boolean isActivarGrabarWindowClose() {
		return activarGrabarWindowClose;
	}


	/**
	 * @param activarGrabarWindowClose the activarGrabarWindowClose to set
	 */
	public void setActivarGrabarWindowClose(boolean activarGrabarWindowClose) {
		this.activarGrabarWindowClose = activarGrabarWindowClose;
	}


	/**
	 * @return the activarHotkeyEstandarInicial
	 */
	public boolean isActivarHotkeyEstandarInicial() {
		return activarHotkeyEstandarInicial;
	}


	/**
	 * @param activarHotkeyEstandarInicial the activarHotkeyEstandarInicial to set
	 */
	public void setActivarHotkeyEstandarInicial(boolean activarHotkeyEstandarInicial) {
		this.activarHotkeyEstandarInicial = activarHotkeyEstandarInicial;
	}


	/**
	 * @return the activarVentanaConfirmacionSave
	 */
	public boolean isActivarVentanaConfirmacionSave() {
		return activarVentanaConfirmacionSave;
	}


	/**
	 * @param activarVentanaConfirmacionSave the activarVentanaConfirmacionSave to set
	 */
	public void setActivarVentanaConfirmacionSave(
			boolean activarVentanaConfirmacionSave) {
		this.activarVentanaConfirmacionSave = activarVentanaConfirmacionSave;
	}


	/**
	 * @return the activarRefrescarPantallaLuegoAlertAction
	 */
	public boolean isActivarRefrescarPantallaLuegoAlertAction() {
		return activarRefrescarPantallaLuegoAlertAction;
	}


	/**
	 * @param activarRefrescarPantallaLuegoAlertAction the activarRefrescarPantallaLuegoAlertAction to set
	 */
	public void setActivarRefrescarPantallaLuegoAlertAction(
			boolean activarRefrescarPantallaLuegoAlertAction) {
		this.activarRefrescarPantallaLuegoAlertAction = activarRefrescarPantallaLuegoAlertAction;
	}


	/**
	 * @return the nroSessionManage
	 */
	public String getNroSessionManage() {
		return nroSessionManage;
	}


	/**
	 * @param nroSessionManage the nroSessionManage to set
	 */
	public void setNroSessionManage(String nroSessionManage) {
		this.nroSessionManage = nroSessionManage;
	}


	
	
	

}


package biz.belcorp.ssicc.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.extensions.model.layout.LayoutOptions;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.springframework.orm.ObjectRetrievalFailureException;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.MenuRol;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.ReporteEmitido;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.HistoricoAuditoriaService;
import biz.belcorp.ssicc.service.HistoricoReporteService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.LookupService;
import biz.belcorp.ssicc.service.MenuService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.MBaseAbstractJSF;
import biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

import com.google.gson.Gson;

@ManagedBean
@SessionScoped
public class MPantallaPrincipalBean extends MBaseAbstractJSF {

    private static final long serialVersionUID = -2403138958014741653L;
    protected final static String LOGIN_FAILURE_PAGE = "/index.xhtml";
    protected final String PAQUETE_WEB = "biz.belcorp.ssicc.web";
    
    protected String pantallaBodyXhtml ;  
    protected String pantallaAdicionalesXhtml = this.PAGINA_ADICIONAL_BASE;  
    protected String ACCION_INGRESAR = "INGRESO";
    
   
    /* Datos del Usuario Logueado */
    protected String codigoUsuario;
    protected String password; 
    protected String countryCode;
    protected Usuario currentUser;
    protected Pais currentCountry;
    protected Locale localeKey;
    protected Map currentUserAccess;
     
    /* Datos del Menu en base al Rol del Usuario Logueado */
    
    private List arbolMenues;
    private Map accesosMap;
	
    private String codigoProcesoBatch;
    private String codigoSistema;
    
    /* Datos de configuracion del Sistema */
    private boolean mostrarTextoBotones = false;  //Para mostrar texto o no en los botones
    private boolean salirGrabarPantallaPadre = true;  //Si esta en true retorna luego de Grabar a la pantalla padre
    private boolean multiVentana = false; 
    private String  targetMain;
    private String  theme="cupertino";  //"pepper-grinder";  //afternoon "cupernico"; 
    private boolean mostrarOcultarFiltrosDatatable = true;
    private boolean mostrarCabeceraFija = true;
    private boolean mostrarSelectCheckboxMenu = true;
    private boolean cambiarPasswordUsuario = false;
    
    private List<String> imagesLogin;
    private BaseSearchForm criteriosBusqueda;
    private MBaseSistemaAbstractJSF manageBeanPadre;
    
    /* Datos usados como base en los Reportes */
    private Idioma currentIdioma;
    private Map parametrosReporteGeneral;
    private String oidIdiomaIso;    
    private String currentMenu;
    
	private List<String> listaEjecucionReportes =  new ArrayList<String>();
	private List<ReporteEmitido> listaReportesEmitidos = new ArrayList<ReporteEmitido>();
	private List<HistoricoAuditoria> listaHistoricoAuditoria = new ArrayList<HistoricoAuditoria>();
	protected ReporteEmitido currentReporteEmitido;
		
	private Boolean isMobile = false;
	
	
	/* Consulta Reportes x Usuario */
	private TimelineModel reporteTimelines; 
	private boolean mostrarReporteTimelines;
	private String jsonReporteUsuario;
	private boolean viewPDFMedia = true;
	
	/* Layout del Sistema */
	private LayoutOptions layoutSistemaOptions;
	private LayoutOptions layoutPrincipalOptions;

	/* Datos Generales de Sistema */
	private List siccRegionList = new ArrayList(); 
	private List siccZonaList = new ArrayList();
	private List siccTipoDocumentoList = new ArrayList();
	private String locale = new String("es") ; 
	private List<Menu> listaMenuFavoritos = new ArrayList<Menu>();
    private List listaCanales = new ArrayList(); // Lista de canales asociados al usuario 
	private String anyoActualperiodo; // Año actual del periodo(Campaña) 
	private String codigoPeriodoActual;
	
	private String mensajeErrorSistema ;
	
	
	private String codigoAccionRetorno;
	private MatrizFacturacion pedMatrizSeleccionada;
	private List pedProductoAsociadoOfertaList = new ArrayList();
	private List pedProductoAsociadoOfertaGrupoList = new ArrayList();
	private DataTableModel listaModelPedProductoAsociadoOfertaGrupoList = new DataTableModel();
	
	private String paginaRutaCompletaXHTML;
	
	//Mensajes de Expiración
	private String mostrarMensajeExpiracion;
	private String mostrarMensajeExpiracionPaginaAcceso;
	
	private boolean mostrarComboPaises = true;
	protected String countryName;
	protected String hostName ="";
	
	private List allPoliticasSeguridadContrasenia;
	
	//Variables para cambiar clave
	private String j_user;
	private String j_claveActual;
	private String j_claveNueva;
	
	private int nroSession = 0;
	private List<Base> listaManageBeanSession = new ArrayList<Base>();
	private String nroSessionManageLimpiar;
	
	private List preProductoAsociadoGrupoOfertaList = new ArrayList();
	private DataTableModel listaModelPreProductoAsociadoGrupoOfertaList = new DataTableModel();
	
	private List preProductoAsociadoCompuestaFijaList = new ArrayList();
	private List preProductoAsociadoCompuestaFijaGlobalList = new ArrayList();
	private DataTableModel listaModelPreProductoAsociadoCompuestaFijaList = new DataTableModel();
	
	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the mostrarComboPaises
	 */
	public boolean isMostrarComboPaises() {
		return mostrarComboPaises;
	}

	/**
	 * @param mostrarComboPaises the mostrarComboPaises to set
	 */
	public void setMostrarComboPaises(boolean mostrarComboPaises) {
		this.mostrarComboPaises = mostrarComboPaises;
	}

	@PostConstruct
	public void init() {
		this.targetMain = "main";
		
        this.initializeLayoutSistema();
        this.mostrarReporteTimelines = false;
        
        /* obteniendo Host */
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			address =null;
		}
		if (address!=null) 	hostName = address.getHostName();
		
		log.debug("Host Name: " + hostName);
		
        
        String contextRoot = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
		log.debug(contextRoot);
		PaisService paisService = (PaisService) this.getBeanService("paisService");
		Pais pais = paisService.getPaisByContextRoot(contextRoot);
		
		this.currentCountry =null;
		
		if (pais!=null) {
			mostrarComboPaises = false;
			log.debug("Pais = " + pais.getCodigo());
			this.countryCode = pais.getCodigo();
			this.currentCountry = pais;
			this.countryName = pais.getDescripcion();
			
		}
		
		
    }  
	
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}

	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}


	
	/**
     * Ingresar al Sistema SSiCC cuando el usuario hizo aceptar en el Login  
     * @return
     */
    public void ingresarSistema(ActionEvent actionEvent) {
    	String retorno = this.ingresarSistema();
    	
    	try {
	    	if (!StringUtils.equals(retorno, this.LOGIN_FAILURE_PAGE)) {
	    		this.redireccionarPagina(retorno);
	    	}
    	}
    	catch(Exception e) {
    		this.errorIngresoLogin();
    	}
    }
	
    
    
    
	/**
     * Ingresar al Sistema SSiCC cuando el usuario hizo aceptar en el Login  
     * @return
     */
    public String ingresarSistema() {
    	if (this.isMobile) this.pantallaBodyXhtml = "mainMobile";
    	else this.pantallaBodyXhtml = "main"; 
    	
    	this.pantallaAdicionalesXhtml = PAGINA_ADICIONAL_BASE;
    	
    	log.debug("countryCode=" + countryCode);
    	PaisService paisService = (PaisService) this.getBeanService("paisService");
    	Pais pais = paisService.getPais(this.countryCode);
    	MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
    	Map criteriaPais = new HashMap();
    	criteriaPais.put("codigoPais", pais.getCodigo());
    	String oidPais = clienteService.getOidPais(criteriaPais);
    	long loidPais = new Long(oidPais).longValue();
    	pais.setOidPais(loidPais);
		this.currentCountry = pais;
		
		/* Obteniendo correo del Usuario Administrador de Politicas de Seguridad */
		GenericoService genericoService = (GenericoService)this.getBeanService("genericoService");	
		String correoAdminPoliticaSeguridad = "";
		try {
			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoSistema(Constants.SISTEMA_SEG);
			parametroPais.setNombreParametro(Constants.SEG_CORREO_ADMINISTRADOR_POLITICA_SEGURIDAD);
			
			List lstParametros = genericoService.getParametrosPais(parametroPais);
			if(lstParametros != null && lstParametros.size() > 0){			
				ParametroPais ps = (ParametroPais)lstParametros.get(0);
				correoAdminPoliticaSeguridad = ps.getValorParametro();
			}
		}
		catch(Exception e) {
			correoAdminPoliticaSeguridad = "";
		}
		
		
		/* Obteniendo Usuario */
        UsuarioService usuarioService = (UsuarioService)this.getBeanService("usuarioService");
        Usuario usuario=null;
        boolean usuarioEliminadoPoliticaSeguridad = false;
		try {
			usuario = usuarioService.getUsuarioByUsername(this.codigoUsuario);
		}
		catch(Exception e) {
			usuario=null;	
			Map criteria = new HashMap();
			criteria.put("login", this.codigoUsuario);
			Integer existe = usuarioService.getVerificarUsuarioEliminadoPoliticaSeguridad(criteria);
			if (existe.intValue() > 0)
				usuarioEliminadoPoliticaSeguridad = true;
		}
		this.cambiarPasswordUsuario = false;  
		
		//USUARIO NO EXISTE
		if(usuario == null)	{
			if (usuarioEliminadoPoliticaSeguridad) {
				Map criteria = new HashMap();
				criteria.put("codigoPolitica", "007");
				String valor = usuarioService.getPoliticaByCodigo(criteria);
				return this.errorIngresoLogin("usuario.eliminadoPolitica.error", new Object[]{valor, correoAdminPoliticaSeguridad}  );
			}
			else {
			   return this.errorIngresoLogin();
			}
		}
		
		//USUARIO INACTIVO
		if (usuario.getEstado().equals(Constants.ESTADO_INACTIVO)) return this.errorIngresoLogin("usuario.eliminado.error");
		if (usuario.getEstado().equals(Constants.ESTADO_BLOQUEO_USUARIO)) {
			Map criteria = new HashMap();
			criteria.put("codigoPolitica", "006");
			String valor = usuarioService.getPoliticaByCodigo(criteria);
			return this.errorIngresoLogin("usuario.bloqueadoPolitica.error", new Object[]{valor, correoAdminPoliticaSeguridad}  );
		}
		if (usuario.getEstado().equals(Constants.ESTADO_USUARIO_CAMBIAR_CLAVE)) 
			this.cambiarPasswordUsuario = true;    
		
		//POLITICA DE REINTENTOS
		Map criterias = new HashMap();
		criterias.put("codigoPais", usuario.getCodigoPais());
		criterias.put("codigoPolitica", Constants.SEG_POLITICA_CANTIDAD_MAXIMA_REINTENTOS);
		String valPoliticaReintentos = usuarioService.getPoliticaByCodigo(criterias);
		
		int reintentosUsuario = 0;
		if(usuario.getIntentosFallidosClave() != null) reintentosUsuario = Integer.parseInt(usuario.getIntentosFallidosClave());
		if (reintentosUsuario >= Integer.parseInt(valPoliticaReintentos)) {
			Map criteria = new HashMap();
			criteria.put("codigoPolitica", "004");
			String valor = usuarioService.getPoliticaByCodigo(criteria);
			return this.errorIngresoLogin("usuario.bloqueado.maximoReintentos.error", new Object[]{valor} );
		}
		
		//POLITICA DE EXPIRACION DE CLAVE
		Map criteria = new HashMap();
		criteria.put("codigoPais", usuario.getCodigoPais());
		criteria.put("codigoPolitica", Constants.SEG_POLITICA_DIAS_EXPIRACION_CLAVE);
		String valPoliticaExpiracion = usuarioService.getPoliticaByCodigo(criteria);
		
		int diferenciaDias = diferenciasDeFechas(usuario.getUltimaModifiacionClave(), new Date());
		if(diferenciaDias > Integer.parseInt(valPoliticaExpiracion)) return this.errorIngresoLogin("usuario.bloqueado.error.politicaExpiracion");
			
		//CLAVE INCORRECTA
		if (!StringUtils.equals(StringUtil.encodePassword(this.password,Constants.ENC_ALGORITHM), usuario.getClave())) {
			log.warn("Clave incorrecta: " + usuario.getClave());
			usuario.setIntentosFallidosClave("" +(reintentosUsuario+1));
			usuarioService.updateIntentosFallidosClaveUsuario(usuario, usuario);	
			return this.errorIngresoLogin();
		}
		
		//INICIO DE SESION DE USUARIO
		usuario.setIntentosFallidosClave(Constants.NUMERO_CERO);
		usuario.setLastLogin(DateUtil.getDateTimeNow(Constants.DEFAULT_DATE_TIME_FORMAT, new Date()));
		usuario.setClave(null);//Se coloca clave nula para evitar que se actualice la clave
		usuarioService.updateUsuario(usuario, usuario);					
		
		IdiomaService idiomaService = (IdiomaService)this.getBeanService("idiomaService");
        String language = "";
        this.localeKey = Locale.ENGLISH;
        if (StringUtils.isNotBlank(language)) {
			Idioma idioma = idiomaService.getIdioma(language);
			this.localeKey = new Locale(idioma.getCodigoISO());
			usuario.setIdioma(idioma);					
		} else {
			this.localeKey = new Locale(usuario.getIdioma().getCodigoISO());
		}
        
    	this.currentIdioma = idiomaService.getIdiomaByCodigoISO(this.currentCountry.getCodigoIdiomaIso());
		log.debug("Estableciendo el idioma: "+ locale.toString());
		
		ReporteService reporteService = (ReporteService) this.getBeanService("scsicc.reporteService");
		this.parametrosReporteGeneral = reporteService.getParametrosReporteGeneral(null); 
		
		Map criteriaIdioma = new HashMap();
		criteriaIdioma.put("codigoIdiomaIso", this.currentIdioma.getCodigoISO());
		this.oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", criteriaIdioma);
		
		//Seteo de la lista de canales asociadas al idioma del usuario
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService)this.getBeanService("sisicc.interfazSiCCService");   		
		this.listaCanales = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		int anyoActual = Calendar.getInstance().get(Calendar.YEAR);
		this.anyoActualperiodo = Integer.toString(anyoActual);

		String ip= this.getRequest().getRemoteAddr();
		log.debug("maquina remota ip "+ip);
        usuario.setIpMaquinaRemota(ip);
		this.currentUser = usuario;

		log.debug("Iniciando carga de las opciones de menú del usuario.");
		
		/* Construyendo Menu del Sistema */
		this.nroSession = this.nroSession + 1 ;
        List menues;
		MenuService menuService = (MenuService) this.getBeanService("menuService");
		menues = menuService.getMenuesByUsuario(pais.getCodigo(), usuario.getLogin(), usuario.getIdioma().getCodigo(), this.nroSession);
		
		// Organizamos el resultado obtenido a manera de arbol
		this.arbolMenues = organizarMenues(menues);
		log.debug("Cargando Accesos a Opciones de Menu");
				 
		this.accesosMap = organizarAccesos(menues);
		this.currentUserAccess = accesosMap;
		
		//MENSAJES DE EXPIRAXION DE CLAVE
		int restoDias =  Integer.parseInt(valPoliticaExpiracion) - diferenciaDias;
		this.mostrarMensajeExpiracion = "";
		this.mostrarMensajeExpiracionPaginaAcceso = "";
		if(restoDias <= Constants.SEG_DIAS_MOSTRAR_MENSAJE_EXPIRACION_CLAVE) this.mostrarMensajeExpiracion = "Tu contraseña caducará en " + restoDias + " día(s). ";
		if(restoDias == 0) this.mostrarMensajeExpiracion = "Hoy es el último día para cambiar tu contraseña. ";
		
		if (StringUtils.isNotEmpty(this.mostrarMensajeExpiracion)) 
			this.mostrarMensajeExpiracionPaginaAcceso = "Cámbiala en la página de acceso en la opción 'Cambiar Contraseña' de lo contrario tu cuenta se bloqueará";
	   
		
		/* Activar Popup de Cambio de Clave */
		if (this.cambiarPasswordUsuario)
			this.mostrarPopupCambiarClaveUsuarioLogueado();
		
		/* Final de IngresoSistema */
		log.debug("FIN ingresarSistema");
		String ambienteAplicacion = this.currentCountry.getAmbienteAplicacion();
		if (StringUtils.equals(ambienteAplicacion, "qas")) {
			this.theme = "bluesky"; // "delta";//"redmond" ;//"start"; 
		}
		if (StringUtils.equals(ambienteAplicacion, "pdp")) {
			this.theme = "pepper-grinder"; // "sam"; //"smoothness";  //"overcast";
			
		}
		//this.theme = "none"; 
		//this.theme = "cupertino"; 
		return this.pantallaBodyXhtml;
    }
    

    /**
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public static int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {
	 	
    	 DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
         String fechaInicioString = df.format(fechaInicial);
         try {
             fechaInicial = df.parse(fechaInicioString);
         } catch (Exception ex) {
         }

         String fechaFinalString = df.format(fechaFinal);
         try {
             fechaFinal = df.parse(fechaFinalString);
         } catch (Exception ex) {
         }

         long fechaInicialMs = fechaInicial.getTime();
         long fechaFinalMs = fechaFinal.getTime();
         long diferencia = fechaFinalMs - fechaInicialMs;
         double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
         int diasDiferencia = (int) dias; 
         
 		return diasDiferencia;
    }
   
    
    /**
     * Inicializa los layout del Sistema
     */
    protected void initializeLayoutSistema() {  
         
        // LAYOUT PANTALLA PRINCIPAL
        this.layoutPrincipalOptions = new LayoutOptions();  
        LayoutOptions panes1 = new LayoutOptions();  
        panes1.addOption("slidable", false);  
        panes1.addOption("spacing", 6);  
        panes1.addOption("resizeWhileDragging", false);  
        this.layoutPrincipalOptions.setPanesOptions(panes1);  
  
        // options for center pane  
        LayoutOptions center1 = new LayoutOptions();  
        center1.addOption("minWidth", 600); 
        this.layoutPrincipalOptions.setCenterOptions(center1);  
  
        // options for west pane  
        LayoutOptions west = new LayoutOptions();  
        west.addOption("size", 200); 
        west.addOption("resizable", true); 
        west.addOption("minWidth", "60%");
        west.addOption("maxWidth", "100%");
        west.addOption("initClosed", false);
        west.addOption("spacing_closed", 30);
        west.addOption("togglerLength_open", 60);
        west.addOption("togglerLength_closed", 60);
        
        this.layoutPrincipalOptions.setWestOptions(west);  
  
        
       	// LAYOUT BODYCENTER
    	this.layoutSistemaOptions = new LayoutOptions(); 
        LayoutOptions panes = new LayoutOptions();  
        panes.addOption("slidable", false);  
        panes.addOption("spacing", 6);  
        panes.addOption("resizeWhileDragging", false);  
        this.layoutSistemaOptions.setPanesOptions(panes);  
  
        // options for center pane  
        LayoutOptions center = new LayoutOptions();  
        center.addOption("minWidth", 0); 
        this.layoutSistemaOptions.setCenterOptions(center);  
  
        // options for east pane  
        LayoutOptions east = new LayoutOptions();  
        east.addOption("size", "100%"); 
        east.addOption("resizable", true); 
        east.addOption("minWidth", "100%");
        east.addOption("maxWidth", "100%");
        east.addOption("initClosed", true);
        east.addOption("spacing_closed", 30);
        east.addOption("spacing_open", 30);
        east.addOption("togglerLength_open", 50);
        east.addOption("togglerLength_closed", 50);
        this.layoutSistemaOptions.setEastOptions(east); 
        
        LayoutOptions south = new LayoutOptions();  
        south.addOption("size", "10%"); 
        south.addOption("resizable", true); 
        south.addOption("minWidth", "100%");
        south.addOption("maxWidth", "100%");
        south.addOption("initClosed", true);
        south.addOption("spacing_closed", 30);
        south.addOption("spacing_open", 30);
        south.addOption("togglerLength_open", 60);
        south.addOption("togglerLength_closed", 60);
        this.layoutSistemaOptions.setSouthOptions(south); 
        
         
  
    }  
    
    
    /**
	 * En caso hubo Error de Logueo por lo que no se pudo Ingresar al Sistema
	 * @return
	 */
    protected String errorIngresoLogin() {
		this.addError("Error: ", this.getResourceMessage("errors.password.mismatch"));
		this.getSession(false).invalidate();
		return this.LOGIN_FAILURE_PAGE;
	}
    
    /**
     * @param mensaje
     * @return
     */
    protected String errorIngresoLogin(String keymensaje) {
		this.addError("Error: ", this.getResourceMessage(keymensaje));
		this.getSession(false).invalidate();
		return this.LOGIN_FAILURE_PAGE;
	}
    
    /**
     * @param keymensaje
     * @param obj
     * @return
     */
    protected String errorIngresoLogin(String keymensaje, Object[] obj) {
		this.addError("Error: ", this.getResourceMessage(keymensaje, obj));
		this.getSession(false).invalidate();
		return this.LOGIN_FAILURE_PAGE;
	}
    
    /**
	 * En caso hubo Error de Logueo por lo que no se pudo Ingresar al Sistema
	 * @return
	 */
    protected String errorIngresoLogin(Exception e) {
    	String error = this. obtieneMensajeErrorException(e);
		this.addError("Error: ", error);
		this.getSession(false).invalidate();
		return this.LOGIN_FAILURE_PAGE;
	}
	
   
    
    /**
     * Salir del Sistema
     * @return
     */
    public String salirSistema() {
    	String retorno = "/index.xhtml?faces-redirect=true";
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    	FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
    	FacesContext.getCurrentInstance().renderResponse();
		return retorno;
	}
	
	
    
    
    /**
     * En base a la lista del menu obtenida de Base de Datos se reorganiza en 
     * forma de Arbol para su visualizacion
     * @param menues
     * @return
     */
    private List organizarMenues(List menues) {
		List result = new ArrayList();

		if (menues != null && menues.size() > 0) {
			// Armamos la estructura de menues
			for (int i = 0; i < menues.size(); i++) {
				Menu menuPadre = (Menu) menues.get(i);
				for (int j = 0; j < menues.size(); j++) {
					if (i != j) {
						Menu menu = (Menu) menues.get(j);
						if (menuPadre.getCodigo().equals(menu.getCodigoPadre())) {
							menuPadre.getSubmenues().add(menu);
						}
					}
				}
			}

			// Añadimos a la lista resultado los menues de
			// más alto nivel, es decir los que no tienen padre
			for (int k = 0; k < menues.size(); k++) {
				Menu m = (Menu) menues.get(k);
				if (m.getMenuPadre() == null) {
					result.add(m);
				}
			}
		}

		return result;
	}
	
    
	/**
	 * Organizar Accesos
	 * @param menues
	 * @return
	 */
	private Map organizarAccesos(List menues) {
        Map accesosMap = new HashMap();
        Iterator i = menues.iterator();
        while (i.hasNext()) {
            Menu menu = (Menu) i.next();
            String pagina=menu.getPagina()!=null?menu.getPagina():null;
            
            if (pagina!=null) {
               menu.setArregloActivos(menu.getActivos()!=null?menu.getActivos().split(","):null);
 	           menu.setArregloDisponibles(menu.getDisponibles()!=null?menu.getDisponibles().split(","):null);
 	           accesosMap.put(menu.getCodigoMenu(), menu); 	
            }
            }
        return accesosMap;
	}
	
    /**
     * En base a la lista del menu rol obtenida de Base de Datos se reorganiza en 
     * forma de Arbol para su visualizacion
     * @param listaMenuRol
     * @return
     */
    public List organizarMenuRole(List listaMenuRol) {
		List<MenuRol> listaMenuRolReturn = new ArrayList<MenuRol>();

		if (listaMenuRol != null && listaMenuRol.size() > 0) {
			// Armamos la estructura de menues
			for (int i = 0; i < listaMenuRol.size(); i++) {
				MenuRol menuPadre = (MenuRol) listaMenuRol.get(i);
				for (int j = i+1; j < listaMenuRol.size(); j++) {					
					MenuRol menu = (MenuRol) listaMenuRol.get(j);
					if (menuPadre.getCodigo().equals(menu.getCodigoPadre())) {
						menuPadre.getSubmenues().add(menu);					
					}
				}
				if (menuPadre.getCodigoPadre() == null) {
					listaMenuRolReturn.add(menuPadre);
				}
			}
		}

		return listaMenuRolReturn;
	}
    
	
	
	
	/**
	 * Cambia la visualizacion de los botones (Muestra / oculta texto Botones
	 * @param event
	 */
	public void cambiarTextoBoton(ValueChangeEvent event){
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'cambiarTextoBoton' method");
	        }        
	        boolean status = StringUtil.convertirABoolean(event.getNewValue());
	        this.mostrarTextoBotones = status;
	}
	
	/**
	 * Activa / Desactiva el Salir al Grabar
	 * @param event
	 */
	public void cambiarSalirGrabarPantallaPadre(ValueChangeEvent event){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'cambiarSalirGrabarPantallaPadre' method");
        }        
        boolean status = StringUtil.convertirABoolean(event.getNewValue());
        this.salirGrabarPantallaPadre = status;
    }
	
	
	/**
	 * Cambia si el Sistema es MultiVentana o no
	 * @param event
	 */
	public void cambiarMultiVentana(ValueChangeEvent event){
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'cambiarMultiVentana' method");
	        }        
	        boolean status = StringUtil.convertirABoolean(event.getNewValue());
	        this.multiVentana = status;
	       	this.targetMain="main";
	}
	
	
	
	/**
	 * Cambia Thema del Sistema
	 * @param event
	 */
	public void cambiarTheme(AjaxBehaviorEvent ajaxBehaviorEvent){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'cambiarTheme' method");
        }        
        /*String valor = (String)ajaxBehaviorEvent.getNewValue();
        this.theme = valor;*/
    }
	
	/**
	 * Metodo que setea Pantalla de Login
	 * @param actionEvent
	 */
	public void setearPantallaLogin(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'setearPantallaLogin' method");
        } 
		this.codigoUsuario = "";
		this.password = "";
		return;
		
	}
	
	
	/**
	 * Metodo que setea Pantalla de Login
	 * @param actionEvent
	 */
	public void setearPantallaLogin() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'setearPantallaLogin' method");
        } 
		this.codigoUsuario = "";
		this.password = "";
		return;
		
	}
	
	
	/**
	 * Metodo que se ejecuta cuando se hace click en el Menu
	 * @param actionEvent
	 */
	public void ingresarOpcionMenu(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ingresarOpcionMenu' method");
        } 
		this.targetMain="main";
		this.removeSessionManageBeanTodos(actionEvent);	
		this.pantallaAdicionalesXhtml = PAGINA_ADICIONAL_BASE; 
		return;
		
	}
	
	
	/**
	 * Metodo que se ejecuta cuando se hace click en el Menu
	 * @param actionEvent
	 */
	public String ingresarOpcionMenu02() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ingresarOpcionMenu02' method");
        } 
		this.targetMain="main";
		this.removeSessionManageBeanTodos();	
		this.pantallaAdicionalesXhtml = PAGINA_ADICIONAL_BASE; 
		String parametroXHTML = "";
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			parametroXHTML = externalContext.getRequestParameterMap().get("parametroXHTML");
			String codigoMenuIngresar = externalContext.getRequestParameterMap().get("codigoMenu");
			HistoricoAuditoria historicoAuditoria = new HistoricoAuditoria();
			historicoAuditoria.setCodigoAccion(this.ACCION_INGRESAR);
			this.grabarAuditoriaUsuarioIni(historicoAuditoria, codigoMenuIngresar);		
			FacesContext.getCurrentInstance().getExternalContext().redirect(parametroXHTML);
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return null;
		}
		this.paginaRutaCompletaXHTML = parametroXHTML;
		return parametroXHTML;
		
	}
	
	/**
	 * Metodo que se ejecuta cuando se hace click en el Menu
	 * @param actionEvent
	 */
	public String ingresarOpcionMenu03() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ingresarOpcionMenu03' method");
        } 
		this.removeSessionManageBeanTodos();	
		this.pantallaAdicionalesXhtml = PAGINA_ADICIONAL_BASE; 
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(this.paginaRutaCompletaXHTML );
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return null;
		}
		return this.paginaRutaCompletaXHTML;
		
	}
	
	
		
	/**
	 * Remueve los manageBeans en SessionScoped a excepcion del Manage ingresado como parametro
	 * @param manageActivo
	 */
	public void removeSessionManageBeanExcepto(String manageActivo) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'removeSessionManageBeanExcepto' method");
		}
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Iterator itr = sessionMap.entrySet().iterator();
		manageActivo = manageActivo.toUpperCase();
		log.debug("manage ini: " + manageActivo);
		while (itr.hasNext()) {
			Map.Entry e = (Map.Entry)itr.next();
			String key = e.getKey().toString().trim();
			String valor = e.getValue().toString().trim();
			boolean remover = true;
			log.debug("clave: "+e.getKey()+"valor:"+e.getValue());
			log.debug("clave: "+e.getKey()+"valor:"+e.getValue());
			if (StringUtils.equals("mPantallaPrincipalBean", key)) remover= false;
			if (StringUtils.equals(manageActivo, key.toUpperCase())) remover= false;
			if (StringUtils.contains(manageActivo, key.toUpperCase())) remover= false;
			if (remover) {
				if (StringUtils.contains(valor, PAQUETE_WEB)) {
					removeManagedBean(key);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
					log.debug(" REMOVIDO");
				}	
			}
			
		}
	}
	
	
	/**
	 * Remueve los manageBeans en SessionScoped
	 * @param actionEvent
	 */
	public void removeSessionManageBeanTodos(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'removeSessionManageBeanTodos' method");
		}
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Iterator itr = sessionMap.entrySet().iterator();
		String pantallaPrincipal  = "mPantallaPrincipalBean";
		String pantallaAplicacion = "mAplicacionBean";
		String nroSessionActual = new Integer(this.nroSession).toString().trim();
		while (itr.hasNext()) {
			boolean remover = true;
			Map.Entry e = (Map.Entry)itr.next();
			String key = e.getKey().toString().trim();
			String valor = e.getValue().toString().trim();
			log.debug("clave: "+e.getKey()+"  valor:"+e.getValue());
			
			if (StringUtils.equals(pantallaPrincipal, key)) remover= false;
			if (StringUtils.equals(pantallaAplicacion, key)) remover= false;
		    if (remover) {
				if (StringUtils.contains(valor, PAQUETE_WEB)) {
					this.verificarRemoverManage(nroSessionActual, key);
				}	
			}
			
		}		
	}
	
	/**
	 * Remueve los manageBeans en SessionScoped
	 */
	public void removeSessionManageBeanTodos() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'removeSessionManageBeanTodos' method");
		}
		Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
		Map parametrosPantalla = new HashMap<String, String>();
		parametrosPantalla.putAll(parametros);
		String nroSessionActual = (String) parametrosPantalla.get("nroSession");
		if (StringUtils.isBlank(nroSessionActual)) {
		    nroSessionActual = this.nroSessionManageLimpiar;
		}
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Iterator itr = sessionMap.entrySet().iterator();
		String pantallaPrincipal  = "mPantallaPrincipalBean";
		String pantallaAplicacion = "mAplicacionBean";
		while (itr.hasNext()) {
			boolean remover = true;
			Map.Entry e = (Map.Entry)itr.next();
			String key = e.getKey().toString().trim();
			String valor = e.getValue().toString().trim();
			log.debug("clave: "+e.getKey()+"  valor:"+e.getValue());
			
			if (StringUtils.equals(pantallaPrincipal, key)) remover= false;
			if (StringUtils.equals(pantallaAplicacion, key)) remover= false;
			if (remover) {
				if (StringUtils.contains(valor, PAQUETE_WEB)) {
					this.verificarRemoverManage(nroSessionActual, key);
				}	
			}
			
		}		
	}

	/**
	 * @param nroSessionActual
	 * @param key
	 */
	private void verificarRemoverManage(String nroSessionActual, String key) {
		int contador = -1;
		for (int i=0; i < this.listaManageBeanSession.size(); i++) {
			Base beanSession = (Base) this.listaManageBeanSession.get(i);
			String manage = beanSession.getDescripcion();
			String nroSession = beanSession.getCodigo();
			if (StringUtils.equals(nroSession, nroSessionActual)) {
				if (StringUtils.equals(key.toLowerCase(), manage)) {
					contador = i;
					break;
				}
			}
		}
		
		boolean removerManage = true;
		for (int i=0; i < this.listaManageBeanSession.size(); i++) {
			Base beanSession = (Base) this.listaManageBeanSession.get(i);
			String manage = beanSession.getDescripcion();
			String nroSession = beanSession.getCodigo();
			if (!StringUtils.equals(nroSession, nroSessionActual)) {
				if (StringUtils.equals(key.toLowerCase(), manage)) {
					removerManage = false;
				}
			}
		}
		
		if (removerManage) {
			removeManagedBean(key);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
			if (contador > -1) {
				this.listaManageBeanSession.remove(contador);
			}
			log.debug(" REMOVIDO");
		}
	}
	
	
	
	
	
	 /**
	 * VER SI BORRAR METODO!!!!
	 * @param event
	 */
	public void onTabChange(TabChangeEvent event) {
		 log.debug("Entering 'onTabChange' method"); 
		 log.debug("listaMenuFavoritos size : "+ this.listaMenuFavoritos.size());
		 //requestPartialRendering();
		 /*
		 RequestContext context = RequestContext.getCurrentInstance();
		 context.update(":menuForm:menuTabView:favoritosTab, :menuForm:listaMenuFavoritos");
		 	*/	  
	 }
	
	 
	 /**
	 * Muestra Lista de Reportes Emitidos por el usuario logueado
	 */
	public void generarListaReportesEmitidos(){
			if (log.isDebugEnabled()) {
				log.debug("Entering 'generarListaReportesEmitidos' method");
			}
			try {
				this.listaReportesEmitidos = new ArrayList();
				this.jsonReporteUsuario = new String("");
				this.mostrarReporteTimelines = false;
				MenuService menuService = (MenuService)this.getBeanService("menuService");		
				HistoricoReporteService historicoReporteService = (HistoricoReporteService)this.getBeanService("sisicc.historicoReporteService");
				
				Map criteria = new HashMap();
				criteria.put("codigoUsuario", this.currentUser.getLogin());						
				criteria.put("fechaInicio", DateUtil.getDate(new Date()));			
				List<HistoricoReporte> listaHistorico = (List<HistoricoReporte>)historicoReporteService.getHistoricoReporteByUser(criteria);
				
				if(listaHistorico != null && listaHistorico.size() > 0){
					Gson gson = new Gson();
					this.jsonReporteUsuario = gson.toJson(listaHistorico);	
					log.debug("JSON Reporte: " + this.jsonReporteUsuario);
					this.reporteTimelines = new TimelineModel();    
			       
					for(HistoricoReporte historicoReporte : listaHistorico){
						ReporteEmitido reporteEmitido = new ReporteEmitido();
						reporteEmitido.setFileName(historicoReporte.getNombreArchivoReporte());
						reporteEmitido.setNombreReporte(historicoReporte.getCodigoReporte());					
						reporteEmitido.setFormato(historicoReporte.getFormatoReporte());	
						reporteEmitido.setCodigoMenu(historicoReporte.getCodigoMenu());
						reporteEmitido.setFechaInicio(historicoReporte.getFechaInicio());
						reporteEmitido.setFechaFin(historicoReporte.getFechaFin());
						reporteEmitido.setDuracionSegundos(historicoReporte.getDuracionSegundos());
						if(historicoReporte.getCodigoMenu() != null){
							Menu menu = menuService.getMenu(historicoReporte.getCodigoMenu(), this.currentIdioma.getCodigo());								
							reporteEmitido.setDescripcionMenu((menu!=null)?menu.getDescripcion():"");							
						}
						reporteEmitido.setEnvioMail(historicoReporte.getEnvioMail());
						reporteEmitido.setMultiReporte(historicoReporte.getMultiReporte());
						this.listaReportesEmitidos.add(reporteEmitido);
						
						/* Colocar timelines */
					    TimelineEvent timelineEvent;
					    Calendar calIni = Calendar.getInstance();      
					    calIni.setTime(historicoReporte.getFechaInicio());
				      			       
					    Calendar calFin = Calendar.getInstance();      
					    calFin.setTime(historicoReporte.getFechaFin());
					    timelineEvent = new TimelineEvent(
					    		             "(" + historicoReporte.getDuracionSegundos() + "seg)" ,
					    		             calIni.getTime(),
					    		             calFin.getTime(),
					    		             true,
					    		             reporteEmitido.getDescripcionMenu()
					    		             );
					    if (historicoReporte.getDuracionSegundos().doubleValue() >= new Double(8))
						    timelineEvent.setStyleClass("timeline_red");
					    else	   
						    timelineEvent.setStyleClass("timeline_green");
					    this.reporteTimelines.add(timelineEvent);
					    this.mostrarReporteTimelines = true;
					}
				}
							
			} catch (Exception e) {
				log.error("Exception al almacenar informacion de Reporte: " + e.getMessage());		
			}
			
			if (log.isDebugEnabled()) {
				log.debug("Finish 'generarListaReportesEmitidos' method");
			}
	 } 
	 
	 
	/**
	 * Lista de Historico de Auditoria
	 */
	public void generarListaHistoricoAuditoria(){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'generarListaHistoricoAuditoria' method");
		}
		try {
			
			this.listaHistoricoAuditoria = new ArrayList();
			MenuService menuService = (MenuService)this.getBeanService("menuService");	
			HistoricoAuditoriaService historicoAuditoriaService = (HistoricoAuditoriaService)this.getBeanService("sisicc.historicoAuditoriaService");
			
			Map criteria = new HashMap();
			criteria.put("codigoUsuario", this.currentUser.getLogin());						
			criteria.put("fechaInicio", DateUtil.getDate(new Date()));			
			
			
			List<HistoricoAuditoria> listaHistoricoAuditoria = (List<HistoricoAuditoria>)historicoAuditoriaService.getHistoricoReporteByUser(criteria);
					
			if(listaHistoricoAuditoria != null && listaHistoricoAuditoria.size() > 0){
				for(HistoricoAuditoria historicoAuditoria : listaHistoricoAuditoria){
					if(historicoAuditoria.getCodigoMenu() != null){
						Menu menu = menuService.getMenu(historicoAuditoria.getCodigoMenu(), this.currentIdioma.getCodigo());								
						historicoAuditoria.setDescripcionMenu((menu!=null)?menu.getDescripcion():"");							
					}
					this.listaHistoricoAuditoria.add(historicoAuditoria);
					
				}
				
			}
						
		} catch (Exception e) {
			log.error("Exception al almacenar auditoria: " + e.getMessage());		
		}
		
		if (log.isDebugEnabled()) {
			log.debug("Finish 'generarListaHistoricoAuditoria' method");
		}
		
	}
	
	/**
	 * Setea check de Mostrar / Ocultar Filtros Datatable
	 * @param e
	 */
	public void setearFiltrosDatatable(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("setearFiltrosDatatable");
		
		this.mostrarOcultarFiltrosDatatable = !this.mostrarOcultarFiltrosDatatable;
	}
	
	
	/**
	 * Setea check de Mostrar Cabecera Fija en las Pantallas
	 * @param e
	 */
	public void setearCabeceraFija(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())http://www.identi.li/index.php?topic=235196http://www.identi.li/index.php?topic=235196http://www.identi.li/index.php?topic=235196update=":principalForm:mostrarOcultarFiltrosDatatable :principalForm:panelPrincipalForm"
			log.debug("setearFiltrosDatatable");
		
		this.mostrarCabeceraFija = !this.mostrarCabeceraFija;
	}
	
	/**
	 * @param event
	 */
	public void mostrarContenidoAyudaOpcion(ActionEvent event){
		this.pantallaAdicionalesXhtml = this.paginaAyudaPantalla;
	}
	
	/**
	 * @param event
	 */
	public void mostrarContenidoAuditoriaOpcion(ActionEvent event){
		this.pantallaAdicionalesXhtml = this.PAGINA_AUDITORIA_SISTEMA;
		return;
		
		// Se verifica que el usuario haya emitido reportes durante el dia 
		/*	log.debug("Estableciendo ListaReportesEmitidos INI ");
		this.generarListaReportesEmitidos();
		log.debug("Estableciendo ListaReportesEmitidos FIM "); 
		
		// Se verifica que el usuario haya ingresado a opciones durante el dia 
		log.debug("Estableciendo generarListaHistoricoAuditoria INI ");
		this.generarListaHistoricoAuditoria();
		log.debug("Estableciendo generarListaHistoricoAuditoria FIN ");
		*/
	}
	
	/**
	 * Genera data para mostrar Popup de Cambio de Clave 
	 * @param event
	 */
	public void mostrarPopupCambiarClave(ActionEvent event){
		this.cambiarPasswordUsuario = false;
		this.getRequestContext().execute("PF('viewCambiarClave').show()");
		LookupService service = (LookupService)this.getBeanService("lookupService");
		try{
			List listPoliticasSeguridad = service.getAllPoliticasSeguridadContrasenia();
			this.allPoliticasSeguridadContrasenia=listPoliticasSeguridad;
		    this.j_user="";
		    this.j_claveActual="";
		    this.j_claveNueva="";
		}catch(Exception ex){
			log.error("ERROR (cargaPoliticasNoTerminado):" + ex.getMessage());
		}
	}
	
    /**
     * Genera data para mostrar Popup de Cambio de Clave para Usuario con Estado Por Cambiar Clave
     * @param event
     */
    public void mostrarPopupCambiarClaveUsuarioLogueado(){
		LookupService service = (LookupService)this.getBeanService("lookupService");
		try{
			List listPoliticasSeguridad = service.getAllPoliticasSeguridadContrasenia();
			this.allPoliticasSeguridadContrasenia=listPoliticasSeguridad;
		    this.j_user= this.currentUser.getLogin();
		    this.j_claveActual="";
		    this.j_claveNueva="";
		}catch(Exception ex){
			log.error("ERROR (cargaPoliticasNoTerminado):" + ex.getMessage());
		}
	}
	
	
	/**
	 * @param event
	 * @throws Exception
	 */
	public void validaCampos(ActionEvent event)  {
	    int nMay = 0;
	    int nNum = 0 ;
		String t1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  
		String t2 = "0123456789";
		String _obj0="";
		String _obj1="";
		String _obj2="";
		for (int i = 0; i < allPoliticasSeguridadContrasenia.size(); i++) {			
			LabelValue base= (LabelValue)allPoliticasSeguridadContrasenia.get(i);
			if(i==0)
			   _obj0 = base.getLabel().split(":")[1].trim();
			if(i==1)
			   _obj1 = base.getLabel().split(":")[1].trim();	
			if(i==2)
			   _obj2 = base.getLabel().split(":")[1].trim();			
		}
		
		int objParametros0 = Integer.parseInt(_obj0);
		int objParametros1 = Integer.parseInt(_obj1);
		int objParametros2 = Integer.parseInt(_obj2);
		
		try {

			if(StringUtils.isBlank(j_user))
				throw new Exception(this.getResourceMessage("cambioClave.usuario")+" es un campo requerido.");
			
			if(StringUtils.isBlank(j_claveActual))
				throw new Exception(this.getResourceMessage("cambioClave.claveActual")+" es un campo requerido.");
			
			if(StringUtils.isBlank(j_claveNueva))
				throw new Exception(this.getResourceMessage("cambioClave.claveNueva")+" es un campo requerido.");
	
	        String tx = j_claveNueva;
	        
		    if (tx.length() < objParametros0) 
		        throw new Exception(this.getResourceMessage("cambioClave.claveNueva")+" debe tener al menos " +objParametros0+" caracteres.");
				
		        
		    for (int j=0;j<tx.length();j++) { 
				if ( t1.indexOf(tx.charAt(j)) != -1 ) {nMay++;} 
				if ( t2.indexOf(tx.charAt(j)) != -1 ) {nNum++;}
			} 
		    
			if (nMay < objParametros1) 
		        throw new Exception(this.getResourceMessage("cambioClave.claveNueva")+" debe tener al menos " +objParametros1+" Mayúsculas.");
	
			if (nNum < objParametros2) 
		        throw new Exception(this.getResourceMessage("cambioClave.claveNueva")+" debe tener al menos " +objParametros2+" números.");
			
			grabarCambiarClave();
			
		} catch (Exception e) {
			// TODO: handle exception
			this.addError("Error", obtieneMensajeErrorException(e));
			return;
		}
		return;
	}
	
	
     /**
     * @throws Exception
     */
    protected void grabarCambiarClave() throws Exception {
		
		String username = j_user;
		String claveActual = j_claveActual;
		String claveNueva = j_claveNueva;
		boolean cambioClave = false;
			
		if (log.isDebugEnabled()) {
				log.debug("username: " + username);
				log.debug("Clave Actual: " + claveActual);
				log.debug("Clave Nueva: " + claveNueva);
		}
    	UsuarioService usuarioService = (UsuarioService)this.getBeanService("usuarioService");
			
			try {
				Usuario usuarioActual = usuarioService.getUsuarioByUsername(username);
				//Usuario usuarioAdmin = usuarioService.getUsuarioByUsername(Constants.USUARIO_ADMIN);
				
				String pwdActualEncode = StringUtil.encodePassword(claveActual, Constants.ENC_ALGORITHM);
				String pwdUsuarioActual = usuarioActual.getClave();
				log.debug(pwdActualEncode);
				log.debug(pwdUsuarioActual);
				
				if(pwdActualEncode.equals(pwdUsuarioActual)){
					usuarioActual.setClave(claveNueva);
					usuarioActual.setEstado(Constants.ESTADO_USUARIO_ACTIVO);
					usuarioService.updateUsuario(usuarioActual, usuarioActual);
					//Se actualiza la fecha de Modificación de la clave
					usuarioService.updateFechaModificacionClaveUsuario(usuarioActual, usuarioActual);
					//Se resetea los intentos fallidos a cero
					usuarioActual.setIntentosFallidosClave(Constants.NUMERO_CERO);
					usuarioService.updateIntentosFallidosClaveUsuario(usuarioActual, usuarioActual);
					cambioClave = true;
				}else
					addError("Error", "Contraseña Actual no coincide");			
			} 
			catch(ObjectRetrievalFailureException orfe) {
				addError("Error", "Usuario no existe");
			}
			catch (Exception e) {
				cambioClave = false;
			}
			
			if (cambioClave){ 
				this.j_user="";
			    this.j_claveActual="";
			    this.j_claveNueva="";
				addInfo("mensajeOk", "Clave cambiada correctamente");
				this.getRequestContext().execute("PF('viewCambiarClave').show()");
			}
		
	}
	
     /**
 	 * @param historicoAuditoria
 	 * @param codigoMenu
 	 * @throws Exception
 	 */
 	public void grabarAuditoriaUsuarioIni(HistoricoAuditoria historicoAuditoria, String codigoMenu) throws Exception {
 	    if(StringUtils.isBlank(codigoMenu)) return;

        String ip = this.getCurrentUser().getIpMaquinaRemota();
 	    String codigoMenu02 = codigoMenu.trim();
        historicoAuditoria.setCodigoPais(this.getCurrentCountry().getCodigo());
 		historicoAuditoria.setCodigoMenu(codigoMenu02);
 		historicoAuditoria.setIpMaquina(ip);
 		historicoAuditoria.setFechaInicio(new Timestamp(System.currentTimeMillis()));
 		historicoAuditoria.setCodigoUsuario(this.getCurrentUser().getLogin());

 		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) this.getBeanService("sisicc.interfazSiCCService");
 		historicoAuditoria.setCodigoPeriodo(interfazSiCCService
 				.getPeriodoDefaultByPaisCanal(
 						historicoAuditoria.getCodigoPais(),
 						Constants.CODIGO_CANAL_DEFAULT));
 		
 		HistoricoAuditoriaService historicoAuditoriaService = (HistoricoAuditoriaService) this.getBeanService("sisicc.historicoAuditoriaService");
 		historicoAuditoriaService.insertHistoricoAuditoria(historicoAuditoria);

     }
	
	
 	
 	
	
	/* SET - GET */
 
	/**
	 * @param pantallaBodyXhtml the pantallaBodyXhtml to set
	 */
	public String getPantallaBodyXhtml() {
		return pantallaBodyXhtml;
	}

	
	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the currentUser
	 */
	public Usuario getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(Usuario currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the currentCountry
	 */
	public Pais getCurrentCountry() {
		return currentCountry;
	}

	/**
	 * @param currentCountry the currentCountry to set
	 */
	public void setCurrentCountry(Pais currentCountry) {
		this.currentCountry = currentCountry;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	/**
	 * @param pantallaBodyXhtml the pantallaBodyXhtml to set
	 */
	public void setPantallaBodyXhtml(String pantallaBodyXhtml) {
		this.pantallaBodyXhtml = pantallaBodyXhtml;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	/**
	 * @return the arbolMenues
	 */
	public List getArbolMenues() {
		return arbolMenues;
	}


	/**
	 * @param arbolMenues the arbolMenues to set
	 */
	public void setArbolMenues(List arbolMenues) {
		this.arbolMenues = arbolMenues;
	}


	/**
	 * @return the accesosMap
	 */
	public Map getAccesosMap() {
		return accesosMap;
	}


	/**
	 * @param accesosMap the accesosMap to set
	 */
	public void setAccesosMap(Map accesosMap) {
		this.accesosMap = accesosMap;
	}

	

	/**
	 * @return the imagesLogin
	 */
	public List<String> getImagesLogin() {
		return imagesLogin;
	}


	/**
	 * @param imagesLogin the imagesLogin to set
	 */
	public void setImagesLogin(List<String> imagesLogin) {
		this.imagesLogin = imagesLogin;
	}
	
	

	/**
	 * @return the localeKey
	 */
	public Locale getLocaleKey() {
		return localeKey;
	}

	/**
	 * @param localeKey the localeKey to set
	 */
	public void setLocaleKey(Locale localeKey) {
		this.localeKey = localeKey;
	}

	/**
	 * @return the currentUserAccess
	 */
	public Map getCurrentUserAccess() {
		return currentUserAccess;
	}

	/**
	 * @param currentUserAccess the currentUserAccess to set
	 */
	public void setCurrentUserAccess(Map currentUserAccess) {
		this.currentUserAccess = currentUserAccess;
	}

	/**
	 * @return the criteriosBusqueda
	 */
	public BaseSearchForm getCriteriosBusqueda() {
		return criteriosBusqueda;
	}

	/**
	 * @param criteriosBusqueda the criteriosBusqueda to set
	 */
	public void setCriteriosBusqueda(BaseSearchForm criteriosBusqueda) {
		this.criteriosBusqueda = criteriosBusqueda;
	}

	/**
	 * @return the mostrarTextoBotones
	 */
	public boolean isMostrarTextoBotones() {
		return mostrarTextoBotones;
	}

	/**
	 * @param mostrarTextoBotones the mostrarTextoBotones to set
	 */
	public void setMostrarTextoBotones(boolean mostrarTextoBotones) {
		this.mostrarTextoBotones = mostrarTextoBotones;
	}

	/**
	 * @return the manageBeanPadre
	 */
	public MBaseSistemaAbstractJSF getManageBeanPadre() {
		return manageBeanPadre;
	}

	/**
	 * @param manageBeanPadre the manageBeanPadre to set
	 */
	public void setManageBeanPadre(MBaseSistemaAbstractJSF manageBeanPadre) {
		this.manageBeanPadre = manageBeanPadre;
	}

	/**
	 * @return the salirGrabarPantallaPadre
	 */
	public boolean isSalirGrabarPantallaPadre() {
		return salirGrabarPantallaPadre;
	}

	/**
	 * @param salirGrabarPantallaPadre the salirGrabarPantallaPadre to set
	 */
	public void setSalirGrabarPantallaPadre(boolean salirGrabarPantallaPadre) {
		this.salirGrabarPantallaPadre = salirGrabarPantallaPadre;
	}
	    
    /**
	 * @return the currentIdioma
	 */
	public Idioma getCurrentIdioma() {
		return currentIdioma;
	}

	/**
	 * @param currentIdioma the currentIdioma to set
	 */
	public void setCurrentIdioma(Idioma currentIdioma) {
		this.currentIdioma = currentIdioma;
	}	

	/**
	 * @return the parametrosReporteGeneral
	 */
	public Map getParametrosReporteGeneral() {
		return parametrosReporteGeneral;
	}

	/**
	 * @param parametrosReporteGeneral the parametrosReporteGeneral to set
	 */
	public void setParametrosReporteGeneral(Map parametrosReporteGeneral) {
		this.parametrosReporteGeneral = parametrosReporteGeneral;
	}
	
	/**
	 * @return the oidIdiomaIso
	 */
	public String getOidIdiomaIso() {
		return oidIdiomaIso;
	}

	/**
	 * @param oidIdiomaIso the oidIdiomaIso to set
	 */
	public void setOidIdiomaIso(String oidIdiomaIso) {
		this.oidIdiomaIso = oidIdiomaIso;
	}
	
	/**
	 * @return the currentMenu
	 */
	public String getCurrentMenu() {
		return currentMenu;
	}

	/**
	 * @param currentMenu the currentMenu to set
	 */
	public void setCurrentMenu(String currentMenu) {
		this.currentMenu = currentMenu;
	}

	/**
	 * @return the listaEjecucionReportes
	 */
	public List<String> getListaEjecucionReportes() {
		return listaEjecucionReportes;
	}

	/**
	 * @param listaEjecucionReportes the listaEjecucionReportes to set
	 */
	public void setListaEjecucionReportes(List<String> listaEjecucionReportes) {
		this.listaEjecucionReportes = listaEjecucionReportes;
	}	
	
	/**
	 * @return the multiVentana
	 */
	public boolean isMultiVentana() {
		return multiVentana;
	}

	/**
	 * @param multiVentana the multiVentana to set
	 */
	public void setMultiVentana(boolean multiVentana) {
		this.multiVentana = multiVentana;
	}

	/**
	 * @return the targetMain
	 */
	public String getTargetMain() {
		return targetMain;
	}

	/**
	 * @param targetMain the targetMain to set
	 */
	public void setTargetMain(String targetMain) {
		this.targetMain = targetMain;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	
	
	/**
	 * Obtiene Lista de Reportes Emitidos
	 * @return
	 */
	public List<ReporteEmitido> getListaReportesEmitidos() {
		return listaReportesEmitidos;
	}

	/**
	 * Setea lista de reportes emitidos
	 * @param listaReportesEmitidos
	 */
	public void setListaReportesEmitidos(List<ReporteEmitido> listaReportesEmitidos) {
		this.listaReportesEmitidos = listaReportesEmitidos;
	}
	
	/**
	 * @return the listaCanales
	 */
	public List getListaCanales() {
		return listaCanales;
	}

	/**
	 * @param listaCanales the listaCanales to set
	 */
	public void setListaCanales(List listaCanales) {
		this.listaCanales = listaCanales;
	}
	
	/**
	 * @return the anyoActualperiodo
	 */
	public String getAnyoActualperiodo() {
		return anyoActualperiodo;
	}

	/**
	 * @param anyoActualperiodo the anyoActualperiodo to set
	 */
	public void setAnyoActualperiodo(String anyoActualperiodo) {
		this.anyoActualperiodo = anyoActualperiodo;
	}
	
	/**
	 * @return the listaMenuFavoritos
	 */
	public List<Menu> getListaMenuFavoritos() {
		return listaMenuFavoritos;
	}

	/**
	 * @param listaMenuFavoritos the listaMenuFavoritos to set
	 */
	public void setListaMenuFavoritos(List<Menu> listaMenuFavoritos) {
		this.listaMenuFavoritos = listaMenuFavoritos;
	}
	
	/**
	 * @return the viewPDFMedia
	 */
	public boolean isViewPDFMedia() {
		return viewPDFMedia;
	}

	/**
	 * @param viewPDFMedia the viewPDFMedia to set
	 */
	public void setViewPDFMedia(boolean viewPDFMedia) {
		this.viewPDFMedia = viewPDFMedia;
	}
	
	/**
	 * @return the currentReporteEmitido
	 */
	public ReporteEmitido getCurrentReporteEmitido() {
		return currentReporteEmitido;
	}

	/**
	 * @param currentReporteEmitido the currentReporteEmitido to set
	 */
	public void setCurrentReporteEmitido(ReporteEmitido currentReporteEmitido) {
		this.currentReporteEmitido = currentReporteEmitido;
	}
	

	/**
	 * @return the reporteTimelines
	 */
	public TimelineModel getReporteTimelines() {
		return reporteTimelines;
	}
	
	/**
	 * @param reporteTimelines the reporteTimelines to set
	 */
	public void setReporteTimelines(TimelineModel reporteTimelines) {
		this.reporteTimelines = reporteTimelines;
	}

	/**
	 * @return the mostrarReporteTimelines
	 */
	public boolean isMostrarReporteTimelines() {
		return mostrarReporteTimelines;
	}


	/**
	 * @param mostrarReporteTimelines the mostrarReporteTimelines to set
	 */
	public void setMostrarReporteTimelines(boolean mostrarReporteTimelines) {
		this.mostrarReporteTimelines = mostrarReporteTimelines;
	}

	/**
	 * @return the jsonReporteUsuario
	 */
	public String getJsonReporteUsuario() {
		return jsonReporteUsuario;
	}

	/**
	 * @param jsonReporteUsuario the jsonReporteUsuario to set
	 */
	public void setJsonReporteUsuario(String jsonReporteUsuario) {
		this.jsonReporteUsuario = jsonReporteUsuario;
	}

	/**
	 * @return the layoutSistemaOptions
	 */
	public LayoutOptions getLayoutSistemaOptions() {
		return layoutSistemaOptions;
	}

	/**
	 * @param layoutSistemaOptions the layoutSistemaOptions to set
	 */
	public void setLayoutSistemaOptions(LayoutOptions layoutSistemaOptions) {
		this.layoutSistemaOptions = layoutSistemaOptions;
	}
	
	/**
	 * @return the listaHistoricoAuditoria
	 */
	public List<HistoricoAuditoria> getListaHistoricoAuditoria() {
		return listaHistoricoAuditoria;
	}

	/**
	 * @param listaHistoricoAuditoria the listaHistoricoAuditoria to set
	 */
	public void setListaHistoricoAuditoria(
			List<HistoricoAuditoria> listaHistoricoAuditoria) {
		this.listaHistoricoAuditoria = listaHistoricoAuditoria;
	}

	/**
	 * @return the layoutPrincipalOptions
	 */
	public LayoutOptions getLayoutPrincipalOptions() {
		return layoutPrincipalOptions;
	}

	/**
	 * @param layoutPrincipalOptions the layoutPrincipalOptions to set
	 */
	public void setLayoutPrincipalOptions(LayoutOptions layoutPrincipalOptions) {
		this.layoutPrincipalOptions = layoutPrincipalOptions;
	}

	/**
	 * @return the isMobile
	 */
	public Boolean getIsMobile() {
		return isMobile;
	}

	/**
	 * @param isMobile the isMobile to set
	 */
	public void setIsMobile(Boolean isMobile) {
		this.isMobile = isMobile;
	}

	

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccTipoDocumentoList
	 */
	public List getSiccTipoDocumentoList() {
		return siccTipoDocumentoList;
	}

	/**
	 * @param siccTipoDocumentoList the siccTipoDocumentoList to set
	 */
	public void setSiccTipoDocumentoList(List siccTipoDocumentoList) {
		this.siccTipoDocumentoList = siccTipoDocumentoList;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the mostrarOcultarFiltrosDatatable
	 */
	public boolean isMostrarOcultarFiltrosDatatable() {
		return mostrarOcultarFiltrosDatatable;
	}

	/**
	 * @param mostrarOcultarFiltrosDatatable the mostrarOcultarFiltrosDatatable to set
	 */
	public void setMostrarOcultarFiltrosDatatable(
			boolean mostrarOcultarFiltrosDatatable) {
		this.mostrarOcultarFiltrosDatatable = mostrarOcultarFiltrosDatatable;
	}

	public boolean isMostrarCabeceraFija() {
		return mostrarCabeceraFija;
	}

	public void setMostrarCabeceraFija(boolean mostrarCabeceraFija) {
		this.mostrarCabeceraFija = mostrarCabeceraFija;
	}

	public boolean isMostrarSelectCheckboxMenu() {
		return mostrarSelectCheckboxMenu;
	}

	public void setMostrarSelectCheckboxMenu(boolean mostrarSelectCheckboxMenu) {
		this.mostrarSelectCheckboxMenu = mostrarSelectCheckboxMenu;
	}

	/**
	 * @return the pantallaAdicionalesXhtml
	 */
	public String getPantallaAdicionalesXhtml() {
		return pantallaAdicionalesXhtml;
	}

	/**
	 * @param pantallaAdicionalesXhtml the pantallaAdicionalesXhtml to set
	 */
	public void setPantallaAdicionalesXhtml(String pantallaAdicionalesXhtml) {
		this.pantallaAdicionalesXhtml = pantallaAdicionalesXhtml;
	}

	/**
	 * @return the mensajeErrorSistema
	 */
	public String getMensajeErrorSistema() {
		return mensajeErrorSistema;
	}

	/**
	 * @param mensajeErrorSistema the mensajeErrorSistema to set
	 */
	public void setMensajeErrorSistema(String mensajeErrorSistema) {
		this.mensajeErrorSistema = mensajeErrorSistema;
	}

	/**
	 * @return the codigoAccionRetorno
	 */
	public String getCodigoAccionRetorno() {
		return codigoAccionRetorno;
	}

	/**
	 * @param codigoAccionRetorno the codigoAccionRetorno to set
	 */
	public void setCodigoAccionRetorno(String codigoAccionRetorno) {
		this.codigoAccionRetorno = codigoAccionRetorno;
	}

	/**
	 * @return the pedMatrizSeleccionada
	 */
	public MatrizFacturacion getPedMatrizSeleccionada() {
		return pedMatrizSeleccionada;
	}

	/**
	 * @param pedMatrizSeleccionada the pedMatrizSeleccionada to set
	 */
	public void setPedMatrizSeleccionada(MatrizFacturacion pedMatrizSeleccionada) {
		this.pedMatrizSeleccionada = pedMatrizSeleccionada;
	}

	/**
	 * @return the pedProductoAsociadoOfertaList
	 */
	public List getPedProductoAsociadoOfertaList() {
		return pedProductoAsociadoOfertaList;
	}

	/**
	 * @param pedProductoAsociadoOfertaList the pedProductoAsociadoOfertaList to set
	 */
	public void setPedProductoAsociadoOfertaList(List pedProductoAsociadoOfertaList) {
		this.pedProductoAsociadoOfertaList = pedProductoAsociadoOfertaList;
	}

	/**
	 * @return the pedProductoAsociadoOfertaGrupoList
	 */
	public List getPedProductoAsociadoOfertaGrupoList() {
		return pedProductoAsociadoOfertaGrupoList;
	}

	/**
	 * @param pedProductoAsociadoOfertaGrupoList the pedProductoAsociadoOfertaGrupoList to set
	 */
	public void setPedProductoAsociadoOfertaGrupoList(
			List pedProductoAsociadoOfertaGrupoList) {
		this.pedProductoAsociadoOfertaGrupoList = pedProductoAsociadoOfertaGrupoList;
	}

	/**
	 * @return the listaModelPedProductoAsociadoOfertaGrupoList
	 */
	public DataTableModel getListaModelPedProductoAsociadoOfertaGrupoList() {
		return listaModelPedProductoAsociadoOfertaGrupoList;
	}

	/**
	 * @param listaModelPedProductoAsociadoOfertaGrupoList the listaModelPedProductoAsociadoOfertaGrupoList to set
	 */
	public void setListaModelPedProductoAsociadoOfertaGrupoList(
			DataTableModel listaModelPedProductoAsociadoOfertaGrupoList) {
		this.listaModelPedProductoAsociadoOfertaGrupoList = listaModelPedProductoAsociadoOfertaGrupoList;
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
	 * @return the codigoProcesoBatch
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch the codigoProcesoBatch to set
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	/**
	 * @return the codigoSistema
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema the codigoSistema to set
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public List getAllPoliticasSeguridadContrasenia() {
		return allPoliticasSeguridadContrasenia;
	}

	public void setAllPoliticasSeguridadContrasenia(
			List allPoliticasSeguridadContrasenia) {
		this.allPoliticasSeguridadContrasenia = allPoliticasSeguridadContrasenia;
	}

	public String getJ_user() {
		return j_user;
	}

	public void setJ_user(String j_user) {
		this.j_user = j_user;
	}

	public String getJ_claveActual() {
		return j_claveActual;
	}

	public void setJ_claveActual(String j_claveActual) {
		this.j_claveActual = j_claveActual;
	}

	public String getJ_claveNueva() {
		return j_claveNueva;
	}

	public void setJ_claveNueva(String j_claveNueva) {
		this.j_claveNueva = j_claveNueva;
	}

	/**
	 * @return the nroSession
	 */
	public int getNroSession() {
		return nroSession;
	}

	/**
	 * @param nroSession the nroSession to set
	 */
	public void setNroSession(int nroSession) {
		this.nroSession = nroSession;
	}

	/**
	 * @return the listaManageBeanSession
	 */
	public List<Base> getListaManageBeanSession() {
		return listaManageBeanSession;
	}

	/**
	 * @param listaManageBeanSession the listaManageBeanSession to set
	 */
	public void setListaManageBeanSession(List<Base> listaManageBeanSession) {
		this.listaManageBeanSession = listaManageBeanSession;
	}

	/**
	 * @return the nroSessionManageLimpiar
	 */
	public String getNroSessionManageLimpiar() {
		return nroSessionManageLimpiar;
	}

	/**
	 * @param nroSessionManageLimpiar the nroSessionManageLimpiar to set
	 */
	public void setNroSessionManageLimpiar(String nroSessionManageLimpiar) {
		this.nroSessionManageLimpiar = nroSessionManageLimpiar;
	}

	/**
	 * @return the mostrarMensajeExpiracion
	 */
	public String getMostrarMensajeExpiracion() {
		return mostrarMensajeExpiracion;
	}

	/**
	 * @param mostrarMensajeExpiracion the mostrarMensajeExpiracion to set
	 */
	public void setMostrarMensajeExpiracion(String mostrarMensajeExpiracion) {
		this.mostrarMensajeExpiracion = mostrarMensajeExpiracion;
	}

	/**
	 * @return the mostrarMensajeExpiracionPaginaAcceso
	 */
	public String getMostrarMensajeExpiracionPaginaAcceso() {
		return mostrarMensajeExpiracionPaginaAcceso;
	}

	/**
	 * @param mostrarMensajeExpiracionPaginaAcceso the mostrarMensajeExpiracionPaginaAcceso to set
	 */
	public void setMostrarMensajeExpiracionPaginaAcceso(
			String mostrarMensajeExpiracionPaginaAcceso) {
		this.mostrarMensajeExpiracionPaginaAcceso = mostrarMensajeExpiracionPaginaAcceso;
	}

	/**
	 * @return the cambiarPasswordUsuario
	 */
	public boolean isCambiarPasswordUsuario() {
		return cambiarPasswordUsuario;
	}

	/**
	 * @param cambiarPasswordUsuario the cambiarPasswordUsuario to set
	 */
	public void setCambiarPasswordUsuario(boolean cambiarPasswordUsuario) {
		this.cambiarPasswordUsuario = cambiarPasswordUsuario;
	}

	/**
	 * @return the preProductoAsociadoGrupoOfertaList
	 */
	public List getPreProductoAsociadoGrupoOfertaList() {
		return preProductoAsociadoGrupoOfertaList;
	}

	/**
	 * @param preProductoAsociadoGrupoOfertaList the preProductoAsociadoGrupoOfertaList to set
	 */
	public void setPreProductoAsociadoGrupoOfertaList(
			List preProductoAsociadoGrupoOfertaList) {
		this.preProductoAsociadoGrupoOfertaList = preProductoAsociadoGrupoOfertaList;
	}

	/**
	 * @return the listaModelPreProductoAsociadoGrupoOfertaList
	 */
	public DataTableModel getListaModelPreProductoAsociadoGrupoOfertaList() {
		return listaModelPreProductoAsociadoGrupoOfertaList;
	}

	/**
	 * @param listaModelPreProductoAsociadoGrupoOfertaList the listaModelPreProductoAsociadoGrupoOfertaList to set
	 */
	public void setListaModelPreProductoAsociadoGrupoOfertaList(
			DataTableModel listaModelPreProductoAsociadoGrupoOfertaList) {
		this.listaModelPreProductoAsociadoGrupoOfertaList = listaModelPreProductoAsociadoGrupoOfertaList;
	}

	/**
	 * @return the preProductoAsociadoCompuestaFijaList
	 */
	public List getPreProductoAsociadoCompuestaFijaList() {
		return preProductoAsociadoCompuestaFijaList;
	}

	/**
	 * @param preProductoAsociadoCompuestaFijaList the preProductoAsociadoCompuestaFijaList to set
	 */
	public void setPreProductoAsociadoCompuestaFijaList(List preProductoAsociadoCompuestaFijaList) {
		this.preProductoAsociadoCompuestaFijaList = preProductoAsociadoCompuestaFijaList;
	}

	/**
	 * @return the listaModelPreProductoAsociadoCompuestaFijaList
	 */
	public DataTableModel getListaModelPreProductoAsociadoCompuestaFijaList() {
		return listaModelPreProductoAsociadoCompuestaFijaList;
	}

	/**
	 * @param listaModelPreProductoAsociadoCompuestaFijaList the listaModelPreProductoAsociadoCompuestaFijaList to set
	 */
	public void setListaModelPreProductoAsociadoCompuestaFijaList(DataTableModel listaModelPreProductoAsociadoCompuestaFijaList) {
		this.listaModelPreProductoAsociadoCompuestaFijaList = listaModelPreProductoAsociadoCompuestaFijaList;
	}

	/**
	 * @return the preProductoAsociadoCompuestaFijaGlobalList
	 */
	public List getPreProductoAsociadoCompuestaFijaGlobalList() {
		return preProductoAsociadoCompuestaFijaGlobalList;
	}

	/**
	 * @param preProductoAsociadoCompuestaFijaGlobalList the preProductoAsociadoCompuestaFijaGlobalList to set
	 */
	public void setPreProductoAsociadoCompuestaFijaGlobalList(List preProductoAsociadoCompuestaFijaGlobalList) {
		this.preProductoAsociadoCompuestaFijaGlobalList = preProductoAsociadoCompuestaFijaGlobalList;
	}
	
	
}
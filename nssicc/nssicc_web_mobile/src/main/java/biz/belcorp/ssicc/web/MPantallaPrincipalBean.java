package biz.belcorp.ssicc.web;

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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.ResizeEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.extensions.model.layout.LayoutOptions;
import org.primefaces.extensions.model.timeline.TimelineModel;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.MenuRol;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.HistoricoAuditoriaService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.MenuService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.MBaseAbstractJSF;
import biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

@ManagedBean
@SessionScoped
public class MPantallaPrincipalBean extends MBaseAbstractJSF {

    private static final long serialVersionUID = -2403138958014741653L;
    protected final static String LOGIN_FAILURE_PAGE = "index";
    protected final String PAQUETE_WEB = "biz.belcorp.ssicc.web";
    
    protected String pantallaBodyXhtml ;  
    
    /* Datos usados para los manageBeans de Session */
    protected List listaManageSession;
    
    /* Datos del Usuario Logueado */
    protected String codigoUsuario;
    protected String password; 
    protected String countryCode;
    protected Usuario currentUser;
    protected Pais currentCountry;
    protected Locale localeKey;
    protected Map currentUserAccess;
     
    /* Datos del Menu en base al Rol del Usuario Logueado */
    private List menues;
    private List arbolMenues;
    private Map accesosMap;
	
    /* Datos usados en la Consulta de Procesos Batch */
    private List listaSistemas;
    private String codigoSistema;
    private List listaProcesoBatch = new ArrayList();
    private String codigoProcesoBatch;
    private List listaProcesoBatchActual;
    
    /* Datos de configuracion del Sistema */
    private boolean usuarioProcesoBatch = true;  //Visualiza Pestana de Consulta de Procesos Batch
    private boolean mostrarTextoBotones = true;  //Para mostrar texto o no en los botones
    private boolean salirGrabarPantallaPadre = false;  //Si esta en true retorna luego de Grabar a la pantalla padre
    private boolean multiVentana = false; 
    private String  targetMain;
    private String theme="pepper-grinder";  //afternoon
    
    private List<String> imagesLogin;
    private BaseSearchForm criteriosBusqueda;
    private MBaseSistemaAbstractJSF manageBeanPadre;
    
    /* Datos usados como base en los Reportes */
    private Idioma currentIdioma;
    private Map parametrosReporteGeneral;
    private String oidIdiomaIso;    
    private String currentMenu;
	private List<String> listaEjecucionReportes =  new ArrayList<String>();
	private List<HistoricoAuditoria> listaHistoricoAuditoria = new ArrayList<HistoricoAuditoria>();

	
	private List listaCanales = new ArrayList(); // Lista de canales asociados al usuario 
	
	private String anyoActualperiodo; // Año actual del periodo(Campaña) 
	
	private List<Menu> listaMenuFavoritos = new ArrayList<Menu>();

	private boolean viewPDFMedia = true;
		
	private List<String> images;
	private Integer screenWidth;
	private Integer screenWidthCenter;
	private Integer screenWidthEast;
	private Integer screenWidthWest = new Integer(300);
	private Boolean isMobile = false;
	
	
	/* Consulta Reportes x Usuario */
	private TimelineModel reporteTimelines; 
	private boolean mostrarReporteTimelines;
	private String jsonReporteUsuario;
	
	/* Layout del Sistema */
	private LayoutOptions layoutSistemaOptions;
	private LayoutOptions layoutPrincipalOptions;

	/* Datos Generales de Sistema */
	private List siccRegionList = new ArrayList(); 
	private List siccZonaList = new ArrayList();
	
	private List siccTipoDocumentoList = new ArrayList();
	
	private String locale = new String("es") ; 
	private int nroSession = 0;
	
	@PostConstruct
	public void init() {
		String manageInicial = "mPantallaPrincipalBean";
		this.listaManageSession = new ArrayList();
		this.listaManageSession.add(manageInicial);
		this.targetMain = "main";
    	this.images = new ArrayList<String>();  
        for(int i=1;i<=9;i++) {  
            images.add("carruselIngreso0" + i + ".png");  
        }  
        
        this.mostrarReporteTimelines = false;
    }  
	
    
	/**
     * Ingresar al Sistema SSiCC cuando el usuario hizo aceptar en el Login  
     * @return
     */
    public String ingresarSistema() {
    	if (this.isMobile)
    		this.pantallaBodyXhtml = "mainMobile";
    	else	
    		this.pantallaBodyXhtml = "main"; 
        
        /* Construyendo Menu del Sistema */
        String language = "";
        Locale locale = Locale.ENGLISH;
        
        UsuarioService usuarioService = (UsuarioService)this.getBeanService("usuarioService");
        PaisService paisService = (PaisService) this.getBeanService("paisService");
        Usuario usuario=null;
        
		try {
			usuario = usuarioService.getUsuarioByUsername(this.codigoUsuario);
		}
		catch(Exception e) {
			usuario=null;	
			return this.errorIngresoLogin();
		}
		if(usuario == null){
			return this.errorIngresoLogin();
		}
		
		try {
			if(usuario != null){
				Pais pais = paisService.getPais(this.countryCode);
				if (!StringUtils.equals(StringUtil.encodePassword(this.password,
						Constants.ENC_ALGORITHM), usuario.getClave())) {
					log.debug("usuario Clave: " + usuario.getClave());
					return this.errorIngresoLogin();
				}
				
				//Iniciamos una nueva session de usuario
				HttpSession sesion = this.getSession(true);
				
				// Seteamos el valor del lenguaje
				if (StringUtils.isNotBlank(language)) {
					IdiomaService idiomaService = (IdiomaService)this.getBeanService("idiomaService");
					Idioma idioma = idiomaService.getIdioma(language);
					locale = new Locale(idioma.getCodigoISO());
					usuario.setIdioma(idioma);					
				} else {
					locale = new Locale(usuario.getIdioma().getCodigoISO());
				}
				
				if (log.isDebugEnabled()) {
					log.debug("INI Cargando las opciones de menú del usuario.");
				}
				
				//obteniendo ip remota
				String ip= this.getRequest().getRemoteAddr();
				log.debug("maquina remota ip "+ip);
				usuario.setIpMaquinaRemota(ip);
				
				// Guardamos el objeto usuario en la sesion
				this.currentUser = usuario;
				// Guardamos el objeto del pais en la sesion
				this.currentCountry = pais;

				if (log.isDebugEnabled()) {
					log.debug("Cargando las opciones de menú del usuario.");
				}
				
				// Obtenemos la instancia de MenuService para obtener las
				// opciones de menu a las cuales tiene acceso el usuario
				this.nroSession += this.nroSession;
				MenuService menuService = (MenuService) this.getBeanService("menuService");
				this.menues = menuService.getMenuesByUsuario(pais
						.getCodigo(), usuario.getLogin(), usuario
						.getIdioma().getCodigo(), this.nroSession);
	
				log.debug("FIN Cargando las opciones de menú del usuario: " + menues.size());
				
				// Organizamos el resultado obtenido a manera de arbol
				this.arbolMenues = organizarMenues(this.menues);
				log.debug("Cargando las opciones de menú del usuario.");
				
				this.accesosMap = organizarAccesos(this.menues);
	           	if (log.isDebugEnabled()) {
					log.debug("Estableciendo el idioma: "+ locale.toString());
				}	
	           	
 	             // Creamos el map conteniendo las opciones a las que tiene
                // acceso el
                // usuario en cada pantalla
				
               	Map accesosMap = this.organizarAccesos(menues);
               	this.currentUserAccess = accesosMap;
				// Una vez obtenido el locale lo colocamos en la sesion
				// usando los 'keys' usados por los frameworks MVC
				if (log.isDebugEnabled()) {
					log.debug("Estableciendo el idioma: "
							+ locale.toString());
				}	
					           	
	            // Para la aplicación
	           	this.localeKey = locale;
	           	
	           	//Obteniendo lista de Consulta de Proceso Batch
	           	SistemaService sistemaService = (SistemaService) this.getBeanService("sisicc.sistemaService");
	           	Map criteria = new HashMap();
	            criteria.put("codigoPais", this.countryCode);
	    		this.listaSistemas = sistemaService.getSistemasByCriteria(criteria);
	    		
	    		ProcesoBatchService service = (ProcesoBatchService) this.getBeanService("scsicc.procesoBatchService");    		
	    		this.listaProcesoBatchActual = service.getProcesoBatchActuByCriteria(criteria);
	    		
	    		//Seteamos el idioma del Usuario
	    		IdiomaService idiomaService = (IdiomaService) this.getBeanService("idiomaService");
	    		this.currentIdioma = idiomaService.getIdiomaByCodigoISO(this.currentCountry.getCodigoIdiomaIso());
	    					    		
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
	    		
		    		
			}
		}
		catch (Exception e) {
			return this.errorIngresoLogin();
		}
		
		return this.pantallaBodyXhtml;
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
        center.addOption("minWidth", 200); 
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
        east.addOption("togglerLength_open", 60);
        east.addOption("togglerLength_closed", 60);
        
        this.layoutSistemaOptions.setEastOptions(east);  
  
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
     * Salir del Sistema
     * @return
     */
    public String salirSistema() {
		this.getSession(true).invalidate();
		return this.LOGIN_FAILURE_PAGE;
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
 	           accesosMap.put(menu.getPagina(), menu); 	
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
     * Metodo que realiza la busqueda de Procesos Batch 
     */
    private void buscar() {
		Map criteria = new HashMap();
        criteria.put("codigoPais", this.countryCode);
        criteria.put("codigoSistema", this.codigoSistema);
        criteria.put("codigoProcesoBatch", this.codigoProcesoBatch);
		ProcesoBatchService service = (ProcesoBatchService) this.getBeanService("scsicc.procesoBatchService");    		
		this.listaProcesoBatchActual = service.getProcesoBatchActuByCriteria(criteria);
	}
    
    /**
	 * Busqueda de Procesos Batch
	 * @param event
	 */
	public void find(ActionEvent event) {
		this.buscar();
	}
	
	/**
	 * Busqueda de Procesos Batch en forma automatica
	 */
	public void findPoll() {
		this.buscar();
	}
	
	/**
	 * Obtiene Cambio Combo de Proceso Batch 
	 * @param event
	 */
	public void procesosBatchChangeListener(ValueChangeEvent event){
        log.debug("procesosBatchChangeListener");
        this.codigoSistema = (String) event.getNewValue();
        Map criteria = new HashMap();
        criteria.put("codigoPais", this.countryCode);
        criteria.put("codigoSistema", this.codigoSistema);
        ProcesoBatchService service = (ProcesoBatchService) this.getBeanService("scsicc.procesoBatchService");    		
		this.listaProcesoBatch = service.getProcesoBatchActuByCriteria(criteria);
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
	 * Adiciona manageBean a la lista de ManageBean Activos por Session
	 * @param manageBean
	 */
	public void adicionarListaManageSession(String manageBean) {
		if (this.listaManageSession.contains(manageBean.toUpperCase())) return;
		this.listaManageSession.add(manageBean.toUpperCase());
	}
	
	
	/**
	 * Remueve manageBean a la lista de ManageBean Activos por Session
	 * @param manageBean
	 */
	public void removerListaManageSession(String manageBean) {
		this.listaManageSession.remove(manageBean.toUpperCase());
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
	 * Metodo que se ejecuta cuando se hace click en el Menu
	 * @param actionEvent
	 */
	public void ingresarOpcionMenu(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ingresarOpcionMenu' method");
        } 
		this.targetMain="main";
    	log.debug("Valor Ventana FALSE target="+ this.targetMain);
		this.removeSessionManageBeanTodos(actionEvent);	
		
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
			
			if (this.multiVentana) {
				if (this.listaManageSession.contains(key.toUpperCase())) remover= false;
			}
			
			if (remover) {
				if (StringUtils.contains(valor, PAQUETE_WEB)) {
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
		while (itr.hasNext()) {
			boolean remover = true;
			Map.Entry e = (Map.Entry)itr.next();
			String key = e.getKey().toString().trim();
			String valor = e.getValue().toString().trim();
			log.debug("clave: "+e.getKey()+"valor:"+e.getValue());
			
			if (StringUtils.equals("mPantallaPrincipalBean", key)) remover= false;
			if (remover) {
				if (StringUtils.contains(valor, PAQUETE_WEB)) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
					log.debug(" REMOVIDO");
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
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Iterator itr = sessionMap.entrySet().iterator();
		while (itr.hasNext()) {
			boolean remover = true;
			Map.Entry e = (Map.Entry)itr.next();
			String key = e.getKey().toString().trim();
			String valor = e.getValue().toString().trim();
			log.debug("clave: "+e.getKey()+"valor:"+e.getValue());
			
			if (StringUtils.equals("mPantallaPrincipalBean", key)) remover= false;
			if (this.multiVentana) {
				if (this.listaManageSession.contains(key.toUpperCase())) remover= false;
			}
			if (remover) {
				if (StringUtils.contains(valor, PAQUETE_WEB)) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
					log.debug(" REMOVIDO");
				}	
			}
			
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
	 * @return the menues
	 */
	public List getMenues() {
		return menues;
	}


	/**
	 * @param menues the menues to set
	 */
	public void setMenues(List menues) {
		this.menues = menues;
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
	 * @return the listaSistemas
	 */
	public List getListaSistemas() {
		return listaSistemas;
	}

	/**
	 * @param listaSistemas the listaSistemas to set
	 */
	public void setListaSistemas(List listaSistemas) {
		this.listaSistemas = listaSistemas;
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
		
	/**
	 * @return the listaProcesoBatchActual
	 */
	public List getListaProcesoBatchActual() {
		return listaProcesoBatchActual;
	}

	/**
	 * @param listaProcesoBatchActual the listaProcesoBatchActual to set
	 */
	public void setListaProcesoBatchActual(List listaProcesoBatchActual) {
		this.listaProcesoBatchActual = listaProcesoBatchActual;
	}

	/**
	 * @return the listaProcesoBatch
	 */
	public List getListaProcesoBatch() {
		return listaProcesoBatch;
	}

	/**
	 * @param listaProcesoBatch the listaProcesoBatch to set
	 */
	public void setListaProcesoBatch(List listaProcesoBatch) {
		this.listaProcesoBatch = listaProcesoBatch;
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
	 * @return the usuarioProcesoBatch
	 */
	public boolean isUsuarioProcesoBatch() {
		return usuarioProcesoBatch;
	}

	/**
	 * @param usuarioProcesoBatch the usuarioProcesoBatch to set
	 */
	public void setUsuarioProcesoBatch(boolean usuarioProcesoBatch) {
		this.usuarioProcesoBatch = usuarioProcesoBatch;
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
	 * @return the listaManageSession
	 */
	public List getListaManageSession() {
		return listaManageSession;
	}

	/**
	 * @param listaManageSession the listaManageSession to set
	 */
	public void setListaManageSession(List listaManageSession) {
		this.listaManageSession = listaManageSession;
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
	 * @return the images
	 */
	public List<String> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<String> images) {
		this.images = images;
	}

	/**
	 * @return the screenWidth
	 */
	public Integer getScreenWidth() {
		return screenWidth;
	}

	/**
	 * @param screenWidth the screenWidth to set
	 */
	public void setScreenWidth(Integer screenWidth) {
		this.screenWidth = screenWidth;
	}

	/**
	 * @return the screenWidthCenter
	 */
	public Integer getScreenWidthCenter() {
		return screenWidthCenter;
	}

	/**
	 * @param screenWidthCenter the screenWidthCenter to set
	 */
	public void setScreenWidthCenter(Integer screenWidthCenter) {
		this.screenWidthCenter = screenWidthCenter;
	}

	/**
	 * @return the screenWidthEast
	 */
	public Integer getScreenWidthEast() {
		return screenWidthEast;
	}

	/**
	 * @param screenWidthEast the screenWidthEast to set
	 */
	public void setScreenWidthEast(Integer screenWidthEast) {
		this.screenWidthEast = screenWidthEast;
	}

	/**
	 * @return the screenWidthWest
	 */
	public Integer getScreenWidthWest() {
		return screenWidthWest;
	}

	/**
	 * @param screenWidthWest the screenWidthWest to set
	 */
	public void setScreenWidthWest(Integer screenWidthWest) {
		this.screenWidthWest = screenWidthWest;
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
	
	

}
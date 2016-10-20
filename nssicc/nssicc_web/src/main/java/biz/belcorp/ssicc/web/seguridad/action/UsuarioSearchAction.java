package biz.belcorp.ssicc.web.seguridad.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Perfil;
import biz.belcorp.ssicc.dao.model.Rol;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.RolService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.seguridad.form.UsuarioForm;
import biz.belcorp.ssicc.web.seguridad.form.UsuarioSearchForm;

/**
 * Action invocado desde la pantalla de mantenimiento del objeto Usuario.
 * <p>
 * <a href="UsuarioSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 * 
 */
@ManagedBean
@SessionScoped
public class UsuarioSearchAction extends BaseMantenimientoSearchAbstractAction {
       
    private static final long serialVersionUID = 2651220801765152455L;

    protected String password;
	
	protected String confirmPassword;
	
	private List usuarioListAll = new ArrayList();
	
	private boolean disCodigoUsuarioRol = false;
	
	protected List listPerfilUsuarioRol = new ArrayList();
	private DataTableModel tableModelListPerfilUsuarioRol = new DataTableModel();
	private List selectionPerfilUsuarioList = new ArrayList();
	
	protected List perfilesConsultar = new ArrayList();
	private DataTableModel dataModelPerfilesConsultar = new DataTableModel();
	private List selectionPerfilesConsultar = new ArrayList();
	
	
	@ManagedProperty(value="#{usuarioPermisoBloqueoAction}")
	protected UsuarioPermisoBloqueoAction usuarioPermisoBloqueoAction;
	
	@ManagedProperty(value="#{usuarioOpcionHiperconsultaAction}")
	protected UsuarioOpcionHiperconsultaAction usuarioOpcionHiperconsultaAction;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "usuarioForm";
	}
		
	/**
	 * Envia de la pagina padre a la pagina bloqueo
	 * 
	 * @return
	 * @throws Exception
	 */
	protected String getPaginaMantenimientoBloqueo() throws Exception {
		return "usuarioPermisoBloqueoForm";
	}
	
	/**
	 * Envia de la pagina padre a la pagina hiperConsulta
	 * 
	 * @return
	 * @throws Exception
	 */
	protected String getPaginaMantenimientoHiperconsulta() throws Exception {
		return "usuarioOpcionHiperConsultaForm";
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		UsuarioSearchForm searchForm = new UsuarioSearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'setFindAttributes' method");
        }

        UsuarioSearchForm searchForm = (UsuarioSearchForm)this.formBusqueda;

        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(searchForm);
        
        // Modificamos los valores que requieren el caracter '%'
        if(StringUtils.isNotBlank(searchForm.getNombresUsuario())) {
            criteria.put("nombresUsuario", searchForm.getNombresUsuario() + "%");
        }
        if(StringUtils.isNotBlank(searchForm.getApellidosUsuario())) {
            criteria.put("apellidosUsuario", searchForm.getApellidosUsuario() + "%");
        }
        
        if(StringUtils.isNotBlank(searchForm.getEstadoUsuario())) {
            criteria.put("estadoUsuario", searchForm.getEstadoUsuario());
        }

        if (this.log.isDebugEnabled()) {
            this.log.debug(criteria.toString());
        }

        UsuarioService service = (UsuarioService) this.getBeanService("usuarioService");
        List lista = service.getUsuariosByCriteria(criteria);
        return lista;
	}
	
		
	
	/**
	 * Metodo que va a la Pagina de Mantenimiento para el Bloqueo del usuario
	 * @return
	 */
	public void bloqueos(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'bloqueos' method");
		}
		if (!this.verificarRegistroSeleccionado())
			return;
		
		/* Redireccionando a la pagina respectiva */
		try{
			this.accion = this.ACCION_MODIFICAR;
			Usuario usuariobusqueda = (Usuario)this.beanRegistroSeleccionado;
			this.usuarioPermisoBloqueoAction.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
			this.usuarioPermisoBloqueoAction.setUsuarioBusqueda(usuariobusqueda);
			this.usuarioPermisoBloqueoAction.edit(actionEvent);
			this.redireccionarPagina(this.getPaginaMantenimientoBloqueo());
			this.beanRegistroSeleccionado = new ArrayList();
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
		return;
	}	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {

		return "usuario.deleted";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
	       
        if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'delete' method");
        }

        // Creamos las instancias de los objetos a usar        
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        
        Usuario usuarioActual = (Usuario)this.beanRegistroSeleccionado;
        
        if (this.log.isDebugEnabled()) {
            this.log.debug("Id seleccionado de la lista: " + usuarioActual.getCodigo());
        }
        // Todas las excepciones son capturadas por ActionExceptionHandler
        UsuarioService service = (UsuarioService) this.getBeanService("usuarioService");        		
        service.removeUsuario(usuarioActual.getCodigo(), usuario);
        return true;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.salirGrabarPantallaPadre = true;
		this.esSeleccionMultiple = true;
		this.mostrarBotonEliminar = false;
	}

	@Override
	protected String getSalirForward() {
		return "usuarioList";
	}
	
	
	/**
	 * Metodo para el Bloqueo de usuarios seleccionados
	 * @return
	 */
	public void bloqueoPoliticaSeguridad(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'bloqueoPoliticaSeguridad' method");
		}
		UsuarioSearchForm searchForm = (UsuarioSearchForm)this.formBusqueda;
		if (!this.verificarRegistroSeleccionadoMultiple()) {
			searchForm.setNroRCRBloqueo("");
			return;
		}
		
		/* Redireccionando a la pagina respectiva */
		try{
			Map criteria = new HashMap();
			String nroRCR = searchForm.getNroRCRBloqueo();
			if (StringUtils.isBlank(nroRCR)) {
				String mensaje = this.getResourceMessage("usuarioList.errorNroRCRBloqueo");
				throw new Exception(mensaje);
			}
			criteria.put("nroRCRBloqueo", nroRCR);
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			for(int i=0; i < this.beanListaSeleccionado.length; i++) {
				Usuario usuario = (Usuario) this.beanListaSeleccionado[i];
				String estado = usuario.getEstado();
				if (StringUtils.equals(estado, Constants.ESTADO_BLOQUEO_USUARIO)) {
					String error = this.getResourceMessage("error.listaUsuario.esDesBloqueado");
					throw new Exception(error);
				}
				
				listaUsuario.add(usuario);
			}
			
			criteria.put("listaUsuarios", listaUsuario);
			UsuarioService service = (UsuarioService) this.getBeanService("usuarioService");        		
	        service.bloqueoManualUsuario(criteria);
	        this.mostrarErrorNoExistenRegistroBusqueda = false;
			this.find(actionEvent);
			this.mostrarErrorNoExistenRegistroBusqueda = true;
			String mensajeOK = this.getResourceMessage("usuarioForm.bloqueoPoliticaSeguridad.procesoOK");
	        this.addInfo(null, mensajeOK);
			
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		searchForm.setNroRCRBloqueo("");
		return;
	}	
	
	
	/**
	 * Metodo para el DesBloqueo de usuarios seleccionados
	 * @return
	 */
	public void desbloqueoPoliticaSeguridad(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'desbloqueoPoliticaSeguridad' method");
		}
		UsuarioSearchForm searchForm = (UsuarioSearchForm)this.formBusqueda;
		if (!this.verificarRegistroSeleccionadoMultiple()) {
			searchForm.setNroRCRDesbloqueo("");
			return;
		}
		
		/* Redireccionando a la pagina respectiva */
		try{
			Map criteria = new HashMap();
			String nroRCR = searchForm.getNroRCRDesbloqueo();
			if (StringUtils.isBlank(nroRCR)) {
				String error = this.getResourceMessage("usuarioList.errorNroRCRDesbloqueo");
				throw new Exception(error);
			}
			criteria.put("nroRCRDesbloqueo", nroRCR);
			
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			for(int i=0; i < this.beanListaSeleccionado.length; i++) {
				Usuario usuario = (Usuario) this.beanListaSeleccionado[i];
				String estado = usuario.getEstado();
				if (StringUtils.equals(estado, Constants.ESTADO_BLOQUEO_USUARIO)) {
				}
				else {
					String error = this.getResourceMessage("error.listaUsuario.esDesBloqueado");
					throw new Exception(error);
				}
				listaUsuario.add(usuario);
			}
			
			criteria.put("listaUsuarios", listaUsuario);
			UsuarioService service = (UsuarioService) this.getBeanService("usuarioService");        		
	        service.desbloqueoManualUsuario(criteria);
	        this.mostrarErrorNoExistenRegistroBusqueda = false;
			this.find(actionEvent);
			this.mostrarErrorNoExistenRegistroBusqueda = true;
	        
	        String mensajeOK = this.getResourceMessage("usuarioForm.desbloqueoPoliticaSeguridad.procesoOK");
	        this.addInfo(null, mensajeOK);
			
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		searchForm.setNroRCRDesbloqueo("");
		return;
	}	
	
	
	
	/**
	 * Metodo para Eliminación Fisica de Usuarios
	 * @return
	 */
	public void eliminarFisico(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminarFisico' method");
		}
		UsuarioSearchForm searchForm = (UsuarioSearchForm)this.formBusqueda;
		if (!this.verificarRegistroSeleccionadoMultiple()) {
			searchForm.setNroRCREliminacion("");
			return;
		}
		
		
		/* Redireccionando a la pagina respectiva */
		try{
			Map criteria = new HashMap();
			String nroRCREliminacion = searchForm.getNroRCREliminacion();
			if (StringUtils.isBlank(nroRCREliminacion)) {
				String error = this.getResourceMessage("usuarioList.errorNroRCREliminacion");
				throw new Exception(error);
			}
			
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			for(int i=0; i < this.beanListaSeleccionado.length; i++) {
				Usuario usuario = (Usuario) this.beanListaSeleccionado[i];
				listaUsuario.add(usuario);
			}
			
			criteria.put("nroRCREliminacion", nroRCREliminacion);
			criteria.put("listaUsuarios", listaUsuario);
			UsuarioService service = (UsuarioService) this.getBeanService("usuarioService");        		
	        service.eliminarFisicoManualUsuario(criteria);
			
	        this.mostrarErrorNoExistenRegistroBusqueda = false;
			this.find(actionEvent);
			this.mostrarErrorNoExistenRegistroBusqueda = true;
	        String mensajeOK = this.getResourceMessage("usuarioForm.eliminarFisico.procesoOK");
	        this.addInfo(null, mensajeOK);
			
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		searchForm.setNroRCREliminacion("");
		return;
	}	
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		UsuarioForm usuarioForm = (UsuarioForm) this.formMantenimiento;
		boolean isNew = usuarioForm.isNewRecord();
		if(isNew){
					
			 if(StringUtils.isNotEmpty(usuarioForm.getCorreoElectronico())){	
				 return "usuario.mail.sentOk";
			 }
			 
			 return "usuario.added";	
			 
		}else{
			return "usuario.updated";
		}			
	}
	
	

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'save' method");
        }

        // Extraemos atributos y parámetros a usar	       
        UsuarioForm usuarioForm = (UsuarioForm)this.getFormMantenimiento();
        String nombres = usuarioForm.getNombres();
        String apellidos = usuarioForm.getApellidos();
        usuarioForm.setNombres(nombres.toUpperCase());
        usuarioForm.setApellidos(apellidos.toUpperCase());
        
        boolean isNew = usuarioForm.isNewRecord();
        boolean envioNuevaClave = false;   
        
        // Extreamos el usuario de la sesión
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        		
        // Creamos la instancia del servicio y le asignamos
        // el usuario que va a realizar las operaciones
        UsuarioService service = (UsuarioService) this.getBeanService("usuarioService");
        		
        Usuario obj = new Usuario();
        BeanUtils.copyProperties(obj, usuarioForm);
                
        // Si el checkbox de clave temporal esta activo modificamos
        // el estado del usuario para que la modifique en su próximo
        // ingreso a la aplicación
        if (usuarioForm.isClaveTemporal()) {
            obj.setEstado(Constants.ESTADO_USUARIO_CAMBIAR_CLAVE);
        }
        else {
            obj.setEstado(Constants.ESTADO_USUARIO_ACTIVO);
        }
        
        List rolesSeleccionados = new ArrayList();
        rolesSeleccionados = this.selectionPerfilUsuarioList;
        
        if(rolesSeleccionados != null && rolesSeleccionados.size() > 0) {
        	List perfilesUsuario = new ArrayList();
        	for (int i = 0; i < this.listPerfilUsuarioRol.size(); i++) {
        		Rol rol = (Rol) this.listPerfilUsuarioRol.get(i);
				String codigoPaisRol = rol.getCodigoPais();
				String codigoRol = rol.getCodigo();
				String estado = Constants.ESTADO_INACTIVO;
				
				for (int j = 0; j < rolesSeleccionados.size(); j++) {
					Rol rolSeleccionado = (Rol) rolesSeleccionados.get(j);
					String codigoPaisRolSeleccionado = rolSeleccionado.getCodigoPais();
					String codigoRolSeleccionado = rolSeleccionado.getCodigo();
					
					if(StringUtils.equals(codigoPaisRol, codigoPaisRolSeleccionado) && 
							StringUtils.equals(codigoRol, codigoRolSeleccionado)){
						estado = Constants.ESTADO_ACTIVO;
						rolesSeleccionados.remove(j);
						break;
					}					
				}

				Perfil perfil = new Perfil();
                perfil.setCodigoPais(codigoPaisRol);
                perfil.setCodigoRol(codigoRol);
                perfil.setEstado(estado);
                
                perfilesUsuario.add(perfil);
			}
        	
        	// Actualizamos los accesos al objeto rol
            obj.setPerfiles(perfilesUsuario);
        }

        try {	            
        	if (this.accion.equals(this.ACCION_NUEVO)) {
        
        		Map criteriaLogin = new HashMap();
        		String login = usuarioForm.getLogin();
        		criteriaLogin.put("login", login);	
        		String mensaje = this.verificarLogin(service, criteriaLogin);
				if (StringUtils.isNotBlank(mensaje)) {
        			throw new Exception (this.getResourceMessage(mensaje));
         		}
        		
            	String correoUsuario = "";
                if (usuarioForm.getCorreoElectronico() != null && usuarioForm.getCorreoElectronico() != "") {
                	correoUsuario = usuarioForm.getCorreoElectronico();
        		}
                //Se genera una nueva contraseña alfanumérica aleatoria 
        		String claveNuevaAleatoria = StringUtil.generarClaveAleatoriaSegura(2, 2, 8);
        		log.debug("Nueva clave Aleatoria >> " + claveNuevaAleatoria);		
        		
        		//se ingresa la contraseña del usuario
        		obj.setClave(claveNuevaAleatoria);        
                //Se ingresa el número de reintentos fallidos de la contraeña en Cero 
                obj.setIntentosFallidosClave(Constants.NUMERO_CERO);                
        		
                service.insertUsuario(obj, usuario);
              
                //Se envía al correo del usuario los datos registrados con la clave generada de manera aleatoria
	            if(StringUtils.isNotEmpty(correoUsuario)){					
					//se envía el correo conla nueva contraseña
					login = obj.getLogin();
					String codigoPais = obj.getCodigoPais();
					String nombreCompleto = obj.getNombres() + " " + obj.getApellidos();
					Map criteria = new HashMap();
					criteria.put("codigoPais", codigoPais);
					criteria.put("nombreCompleto", nombreCompleto);
					criteria.put("login", login);	
					criteria.put("password", claveNuevaAleatoria);					
					criteria.put("nombreReporte", "procesoEnviarNuevaClave");
					
					ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
					Map paramCorreo = reporteService.getParametrosReporte(criteria);
					if(paramCorreo != null)
					{
						paramCorreo.put("correosDestinos",correoUsuario);
						String subject = (String)paramCorreo.get("subject");						
						paramCorreo.put("subject",subject);
						String enviarCorreo = (String) paramCorreo.get("enviarCorreo");			
						log.debug("enviar correo "+enviarCorreo);
						criteria.put("enviarCorreo", enviarCorreo);
						if (Constants.SI.equals(enviarCorreo)) {
											MailParams mailParams = new MailParams();
											
											paramCorreo.put("parameterMap",criteria);
											
											String bodyTxt = (String) paramCorreo.get("bodyTxt");
											String bodyHtml = (String) paramCorreo.get("bodyHtml");
											mailParams.setQueryParams(paramCorreo);
											
											BaseMailService mailService = (BaseMailService) this.getBean(this.getMailService()); 
											criteria.put("bodyTxt", bodyTxt);
											criteria.put("bodyHtml", bodyHtml);
											try {
												mailService.enviarMail(mailParams);
											} catch (Exception e) {
												envioNuevaClave = false;
											}	
								
						envioNuevaClave = true;
						}
					}		
				}
                
            }
            else {
                service.updateUsuario(obj, usuario);	             
            }
        }
        catch (Exception e){
    		this.addError("Error: ", this.obtieneMensajeErrorException(e));
    		return false;
    	}
        
        return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {

		UsuarioForm usuarioForm = (UsuarioForm)this.formMantenimiento;
		String mensaje = null;		

		if(!validateEmail(usuarioForm.getCorreoElectronico())){
			mensaje = Constants.MESSAGE_MAE_EMAIL_SIN_FORMATO;
			return mensaje;
		}
		
		return mensaje;
	}
	
	/**
	 * Verifica existencia de login
	 * 
	 * @param service
	 * @param criteriaLogin
	 * @return
	 */
	private String verificarLogin(UsuarioService service, Map criteriaLogin) {
		String mensaje = "";		
		Integer verificarLogin =  service.verificarExisteLogin(criteriaLogin);
		
		if (verificarLogin.intValue() > 0) {
			mensaje = "usuario.error.loginExistente";
		}
		return mensaje;
	}

	/**
	 * Devuelve Service a trabajar para el envio de correo del reporte
	 * Dicho metodo debe ser sobreescrito para que devuelva el Service correspondiente al reporte en
	 * ejecución
	 * @return
	 */
	public String getMailService () {
		String service = "sisicc.mailUtil";// "sisicc.baseMailAbstractService";		
		return service;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Usuario usuariobusqueda = (Usuario)this.beanRegistroSeleccionado;
		// Creamos algunos de los objetos y servicios a usar
		RolService rolService = (RolService)this.getBeanService("rolService");
		UsuarioForm usuarioForm = new UsuarioForm();
		List perfiles = new ArrayList();
		usuarioForm.setClaveTemporal(true);	
		this.mostrarBotonSave = true;
		usuarioForm.setEditable(true); 
		
		// Limpiamos cualquier residuo que haya quedado en la sesion
        this.listPerfilUsuarioRol = new ArrayList(); 
        this.tableModelListPerfilUsuarioRol = new DataTableModel(this.listPerfilUsuarioRol);
        this.selectionPerfilUsuarioList = new ArrayList();
        List selectionAux = new ArrayList();
        
		//Añadimos la lista de usuario		
		Map criteria = new HashMap();
        UsuarioService service = (UsuarioService) this.getBean("usuarioService");
        this.usuarioListAll = service.getUsuariosByCriteria(criteria);
        //combo usuario deshabilitado
        this.disCodigoUsuarioRol = true;
        		
        if (this.accion.equals(this.ACCION_MODIFICAR) || this.accion.equals(this.ACCION_CONSULTAR) ) {    	            
            String id = usuariobusqueda.getCodigo();         		
            // Si el id ha sido enviado, buscamos la informacion
            // en caso contrario, no hacemos nada, se esta insertando
            // un nuevo registro a la aplicación
            if (id != null) {
                if (log.isDebugEnabled()) {
                    log.debug("Id seleccionado de la lista: " + id);
                }
             
                Usuario obj = service.getUsuario(id);
                BeanUtils.copyProperties(usuarioForm, obj);
                usuarioForm.setLoginActual(usuarioForm.getLogin());
                
                // Reseteamos la contraseña del usuario
//                usuarioForm.setClave(null);
                
                // Actualizamos la lista con los perfiles
                perfiles = obj.getPerfiles();
                             
                usuarioForm.setCodigoPaisRol(usuarioForm.getCodigoPais());
                usuarioForm.setNewRecord(false);
                
                if(this.accion.equals(this.ACCION_CONSULTAR)){
 
                	this.perfilesConsultar = new ArrayList();
                	this.dataModelPerfilesConsultar = new DataTableModel(this.perfilesConsultar);
                	this.mostrarBotonSave = false;
                	usuarioForm.setEditable(false);                	
                }
                	
            }            
        }else{
        	usuarioForm.setCodigoPaisRol(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        	usuarioForm.setIndicadorUsuarioSistema(Constants.NUMERO_CERO);
        }      
         
        seleccionableRol(perfiles);
        if(!this.accion.equals(this.ACCION_CONSULTAR)){
        	generarListaRol(rolService, usuarioForm);                  	
        }else{
        	this.perfilesConsultar = this.selectionPerfilUsuarioList;
        	this.dataModelPerfilesConsultar = new DataTableModel(this.perfilesConsultar);
        }
        
        return usuarioForm;
	}
	
	/**
	 * Convierte la lista perfil seleccionable  a 
	 * lista rol seleccionable
	 * 
	 * @param selectionAux
	 */
	private void seleccionableRol(List selectionAux){
		
		RolService rolService = (RolService)this.getBeanService("rolService");		
		List aux = rolService.getRoles(null);
		List selectionAuxFinal = new ArrayList();
		if(selectionAux != null && selectionAux.size() > 0)
        {
        	
        	for (int i = 0; i < aux.size(); i++) {
        		Rol rol = (Rol) aux.get(i);
        		String rolCodigoPais = rol.getCodigoPais();
        		String rolCodigo = rol.getCodigo();
        		
        		
        		for (int j = 0; j < selectionAux.size(); j++) {
        			Perfil perfil = (Perfil) selectionAux.get(j);
        			String perfilCodigoPais = perfil.getCodigoPais();
        			String perfilCodigoRol = perfil.getCodigoRol();
        			String estado = perfil.getEstado();
        			if(StringUtils.equals(rolCodigoPais, perfilCodigoPais) && 
        				StringUtils.equals(rolCodigo, perfilCodigoRol) && 
        				StringUtils.equals(estado, Constants.ESTADO_ACTIVO)){
	        				selectionAuxFinal.add(rol);
	        				selectionAux.remove(j);
	        				break;
        			}					
				}
        		
        		if(selectionAux.size() == 0)
        			break;
			}
        }	
		this.selectionPerfilUsuarioList = selectionAuxFinal;
	}
	
	/**
	 * Genera lista de roles,
	 * dependiendo del codigo pais seleccionado
	 * @param rolService
	 * @param usuarioForm
	 */
	private void generarListaRol(RolService rolService, UsuarioForm usuarioForm){
		
		// Obtenemos la relacion de opciones de menu
        Rol rolaMostrar = new Rol();
        rolaMostrar.setCodigoPais(usuarioForm.getCodigoPaisRol());
               
        this.listPerfilUsuarioRol = rolService.getRoles(rolaMostrar);
        this.tableModelListPerfilUsuarioRol = new DataTableModel(this.listPerfilUsuarioRol);
       
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setAddAttributes()
	 */
	@Override
	protected void setAddAttributes() throws Exception {
		
		UsuarioForm usuarioForm = (UsuarioForm) this.formMantenimiento;
		usuarioForm.setCodigoPais("");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setEditAttributes()
	 */
	@Override
	protected void setEditAttributes() throws Exception {

		Usuario usuariobusqueda = (Usuario)this.beanRegistroSeleccionado;
		String codigoPais = usuariobusqueda.getCodigoPais();
		UsuarioForm usuarioForm = (UsuarioForm) this.formMantenimiento;
		usuarioForm.setCodigoPais(codigoPais);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setConsultarAttributes()
	 */
	@Override
	protected void setConsultarAttributes() throws Exception {
		Usuario usuariobusqueda = (Usuario)this.beanRegistroSeleccionado;
		String codigoPais = usuariobusqueda.getCodigoPais();
		UsuarioForm usuarioForm = (UsuarioForm) this.formMantenimiento;
		usuarioForm.setCodigoPais(codigoPais);
	}

	/**
	 * Desactiva el check Copiar y actualiza 
	 * la lista de roles, segun el codigo pais seleccionado 
	 *
	 * @param event
	 */
	public void verListaRol(ValueChangeEvent event){
		
		UsuarioForm usuarioForm = (UsuarioForm) this.formMantenimiento;
		RolService rolService = (RolService) this.getBean("rolService");
		String codigoPaisRol = (String) event.getNewValue();
		usuarioForm.setCodigoPaisRol(codigoPaisRol);
		
		if (!StringUtils.isBlank(usuarioForm.getCodigoUsuarioRol())) {
    		usuarioForm.setCopiarUsuario(true);
		}else {
			usuarioForm.setCopiarUsuario(false);
			
			ocultaUsuarios(usuarioForm.isCopiarUsuario());
		}
		generarListaRol(rolService, usuarioForm);		
	}
	
	/**
	 * Habilita o deshabilita lista de usuarios
	 * 
	 * @param bol
	 */
	private void ocultaUsuarios(Boolean bol){
		UsuarioForm usuarioForm = (UsuarioForm) this.formMantenimiento;
		if(bol==false){
			this.disCodigoUsuarioRol = true;
			usuarioForm.setCodigoUsuarioRol("");
			eliminaSeleccionLista();
		}else{
			this.disCodigoUsuarioRol = false;
		}
	}
		
	/**
	 * Seleccion de roles, los deja en blanco
	 * y setea el CodigoPaisRol
	 */
	private void eliminaSeleccionLista (){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'eliminaSeleccionLista'");
		}
		RolService rolService = (RolService) this.getBean("rolService");
        UsuarioForm usuarioForm = (UsuarioForm) this.formMantenimiento;
        
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		List perfiles = new ArrayList();
		this.selectionPerfilUsuarioList = perfiles;
		this.listPerfilUsuarioRol = new ArrayList();
		this.tableModelListPerfilUsuarioRol = new DataTableModel(this.listPerfilUsuarioRol);
				
		usuarioForm.setCodigoPaisRol(pais.getCodigo());
		generarListaRol(rolService, usuarioForm);
		log.debug("Consultando Roles ... ");
		
	}
	
	/**
	 *Habilita o deshabilita lista de usuarios
	 * @param event
	 */
	public void ocultaUsuarios(ValueChangeEvent event){
		
		UsuarioForm usuarioForm = (UsuarioForm) this.formMantenimiento;
		boolean bol = (Boolean) event.getNewValue();
		if(bol==false){
			this.disCodigoUsuarioRol = true;
			usuarioForm.setCodigoUsuarioRol("");
			eliminaSeleccionLista();
		}else{
			this.disCodigoUsuarioRol = false;
		}
		
	}

	/**
	 * Obtiene los perfiles por usuario
	 * 
	 * @param event
	 */
	public void consultarRolesxUsuario(ValueChangeEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'consultarRoles'");
		}
		UsuarioForm usuarioForm = (UsuarioForm) this.formMantenimiento;
    	List perfiles = new ArrayList();
    	UsuarioService service = (UsuarioService) this.getBean("usuarioService");
    	
    	String codigoUsuarioRol = (String) event.getNewValue();
       
        
        if(!StringUtils.isBlank(codigoUsuarioRol)){
        	 Usuario obj = service.getUsuario(codigoUsuarioRol);
             usuarioForm.setCopiarUsuario(true);
             perfiles = obj.getPerfiles();
             
             seleccionableRol(perfiles);
        }    	
	}
	
	/**
	 * Metodo que se ejecuta al dar click en boton hiperconsulta
	 * inicializa los valores de la pagina hiperconsulta
	 * 
	 * @param event
	 */
	public void opcionHiperconsultaUsuario(ActionEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'bloqueos' method");
		}
		
		if (!this.verificarRegistroSeleccionado())
			return;
		
		
		/* Redireccionando a la pagina respectiva */
		try {
		
			Usuario usuariobusqueda = (Usuario)this.beanRegistroSeleccionado;
			this.usuarioOpcionHiperconsultaAction.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
			this.usuarioOpcionHiperconsultaAction.setUsuarioBusqueda(usuariobusqueda);
			this.usuarioOpcionHiperconsultaAction.edit(event);
			this.redireccionarPagina(this.getPaginaMantenimientoHiperconsulta());
			this.beanRegistroSeleccionado = new ArrayList();
			
		} catch (Exception e) {

			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		return;
	}
	
	/**
	 * @return the usuarioPermisoBloqueoAction
	 */
	public UsuarioPermisoBloqueoAction getUsuarioPermisoBloqueoAction() {
		return usuarioPermisoBloqueoAction;
	}

	/**
	 * @param usuarioPermisoBloqueoAction the usuarioPermisoBloqueoAction to set
	 */
	public void setUsuarioPermisoBloqueoAction(
			UsuarioPermisoBloqueoAction usuarioPermisoBloqueoAction) {
		this.usuarioPermisoBloqueoAction = usuarioPermisoBloqueoAction;
	}

	/**
	 * @return the listPerfilUsuarioRol
	 */
	public List getListPerfilUsuarioRol() {
		return listPerfilUsuarioRol;
	}

	/**
	 * @param listPerfilUsuarioRol the listPerfilUsuarioRol to set
	 */
	public void setListPerfilUsuarioRol(List listPerfilUsuarioRol) {
		this.listPerfilUsuarioRol = listPerfilUsuarioRol;
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
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the usuarioListAll
	 */
	public List getUsuarioListAll() {
		return usuarioListAll;
	}

	/**
	 * @param usuarioListAll the usuarioListAll to set
	 */
	public void setUsuarioListAll(List usuarioListAll) {
		this.usuarioListAll = usuarioListAll;
	}

	/**
	 * @return the disCodigoUsuarioRol
	 */
	public boolean isDisCodigoUsuarioRol() {
		return disCodigoUsuarioRol;
	}

	/**
	 * @param disCodigoUsuarioRol the disCodigoUsuarioRol to set
	 */
	public void setDisCodigoUsuarioRol(boolean disCodigoUsuarioRol) {
		this.disCodigoUsuarioRol = disCodigoUsuarioRol;
	}

	/**
	 * @return the tableModelListPerfilUsuarioRol
	 */
	public DataTableModel getTableModelListPerfilUsuarioRol() {
		return tableModelListPerfilUsuarioRol;
	}

	/**
	 * @param tableModelListPerfilUsuarioRol the tableModelListPerfilUsuarioRol to set
	 */
	public void setTableModelListPerfilUsuarioRol(
			DataTableModel tableModelListPerfilUsuarioRol) {
		this.tableModelListPerfilUsuarioRol = tableModelListPerfilUsuarioRol;
	}

	/**
	 * @return the selectionPerfilUsuarioList
	 */
	public List getSelectionPerfilUsuarioList() {
		return selectionPerfilUsuarioList;
	}

	/**
	 * @param selectionPerfilUsuarioList the selectionPerfilUsuarioList to set
	 */
	public void setSelectionPerfilUsuarioList(List selectionPerfilUsuarioList) {
		this.selectionPerfilUsuarioList = selectionPerfilUsuarioList;
	}

	/**
	 * @return the perfilesConsultar
	 */
	public List getPerfilesConsultar() {
		return perfilesConsultar;
	}

	/**
	 * @param perfilesConsultar the perfilesConsultar to set
	 */
	public void setPerfilesConsultar(List perfilesConsultar) {
		this.perfilesConsultar = perfilesConsultar;
	}

	/**
	 * @return the dataModelPerfilesConsultar
	 */
	public DataTableModel getDataModelPerfilesConsultar() {
		return dataModelPerfilesConsultar;
	}

	/**
	 * @param dataModelPerfilesConsultar the dataModelPerfilesConsultar to set
	 */
	public void setDataModelPerfilesConsultar(
			DataTableModel dataModelPerfilesConsultar) {
		this.dataModelPerfilesConsultar = dataModelPerfilesConsultar;
	}

	/**
	 * @return the selectionPerfilesConsultar
	 */
	public List getSelectionPerfilesConsultar() {
		return selectionPerfilesConsultar;
	}

	/**
	 * @param selectionPerfilesConsultar the selectionPerfilesConsultar to set
	 */
	public void setSelectionPerfilesConsultar(List selectionPerfilesConsultar) {
		this.selectionPerfilesConsultar = selectionPerfilesConsultar;
	}

	/**
	 * @return the usuarioOpcionHiperconsultaAction
	 */
	public UsuarioOpcionHiperconsultaAction getUsuarioOpcionHiperconsultaAction() {
		return usuarioOpcionHiperconsultaAction;
	}

	/**
	 * @param usuarioOpcionHiperconsultaAction the usuarioOpcionHiperconsultaAction to set
	 */
	public void setUsuarioOpcionHiperconsultaAction(
			UsuarioOpcionHiperconsultaAction usuarioOpcionHiperconsultaAction) {
		this.usuarioOpcionHiperconsultaAction = usuarioOpcionHiperconsultaAction;
	}
	
}
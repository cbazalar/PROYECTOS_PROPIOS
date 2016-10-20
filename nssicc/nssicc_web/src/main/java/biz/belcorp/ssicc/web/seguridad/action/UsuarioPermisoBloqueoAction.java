package biz.belcorp.ssicc.web.seguridad.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.model.UsuarioBloqueo;
import biz.belcorp.ssicc.service.LookupService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.seguridad.form.UsuarioBloqueoForm;
import biz.belcorp.ssicc.web.seguridad.form.UsuarioPermisoBloqueoForm;

/**
 * Action que maneja los los Bloqueos de los usuarios
 * 
 */

@ManagedBean
@SessionScoped
public class UsuarioPermisoBloqueoAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7791943097227897785L;
	
	protected List bloqueosUsuarioForm;
	private Usuario usuarioBusqueda;
	
	
    //Bloqueos del Usuario seleccionado en el DataTable de Bloqueos del Usuario
  	protected UsuarioBloqueoForm[] bloqueosUsuarioSeleccionado ;

  	//Atributo DataTableModel usado en el Datatable la cual contiene la lista de bloqueos del usuario
	protected DataTableModel dataTableBloqueosUsuario;

	protected List<LabelValue> listaTipoBloqueoUsuario;
	protected List<LabelValue> listaTipoAcciones;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
        this.salirGrabarPantallaPadre = true;
        this.invocarFindLuegoGrabar = false;
	}
	
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'setObtenerRegistroAttributes' method");
        }
		
		Usuario usuariobusqueda = this.usuarioBusqueda;
		
        UsuarioPermisoBloqueoForm usuarioPermisoBloqueoForm = new UsuarioPermisoBloqueoForm();                
      
        	
    	String id = usuariobusqueda.getCodigo();       			
    	// Si el id ha sido enviado, buscamos la informacion
    	// en caso contrario, no hacemos nada, se esta insertando
    	// un nuevo registro a la aplicación
    	
    	this.bloqueosUsuarioForm = new ArrayList();
    	this.dataTableBloqueosUsuario = new DataTableModel(this.bloqueosUsuarioForm);  
    	
    	if (id != null) {
    		if (this.log.isDebugEnabled()) {
    			this.log.debug("Id seleccionado de la lista: " + id);
    		}
    		UsuarioService usuarioService = (UsuarioService) this.getBeanService("usuarioService");        				
    		PaisService paisService = (PaisService) this.getBeanService("paisService");
    		
    		Usuario usuario = usuarioService.getUsuario(id);
    		Pais pais = paisService.getPais(usuario.getCodigoPais());
    		
    		usuarioPermisoBloqueoForm.setCodigoPais(usuario.getCodigoPais());
    		usuarioPermisoBloqueoForm.setCodigoUsuarioBloqueo(usuario.getCodigo());            
    		usuarioPermisoBloqueoForm.setNombrePais(pais.getDescripcion());
    		usuarioPermisoBloqueoForm.setLoginUsuario(usuario.getLogin());
    		
    		//Obtenemos los bloqueos del usuario            
    		List bloqueos = usuarioService.getBloqueosByUsuario(id);
    		
    		if (bloqueos != null && bloqueos.size() > 0) {
    			this.dataTableBloqueosUsuario = null;
    			this.bloqueosUsuarioForm = new ArrayList();
    			UsuarioBloqueoForm[] permisoBloqueoForm = new UsuarioBloqueoForm[bloqueos.size()];
    			for (int i = 0; i < bloqueos.size(); i++) {
    				UsuarioBloqueoForm usuarioBloqueoForm = new UsuarioBloqueoForm();
    				BeanUtils.copyProperties(usuarioBloqueoForm, bloqueos.get(i));
    				this.bloqueosUsuarioForm.add(usuarioBloqueoForm);
    			}
    			this.dataTableBloqueosUsuario = new DataTableModel(this.bloqueosUsuarioForm);       		
    		}   		
    		
    	}
       
    	LookupService lookupService = (LookupService)this.getBeanService("lookupService"); 
    	this.listaTipoAcciones = (List<LabelValue>)lookupService.getAllTiposAcciones(); 
        this.listaTipoBloqueoUsuario = (List<LabelValue>)lookupService.getAllTiposBloqueoUsuario();
        
        return usuarioPermisoBloqueoForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		 	
			if (this.log.isDebugEnabled()) {
	            this.log.debug("Entering 'setSaveAttributes' method");
	        }
	        
	        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	        UsuarioService service = (UsuarioService) this.getBeanService("usuarioService");
	        UsuarioPermisoBloqueoForm usuarioPermisoBloqueoForm = (UsuarioPermisoBloqueoForm) this.formMantenimiento; 
	        
	        List bloqueos = new ArrayList();	        
	        if ( this.bloqueosUsuarioForm != null && this.bloqueosUsuarioForm.size() > 0 ){	        		
	            for(int i = 0 ; i < this.bloqueosUsuarioForm.size() ; i++){
	            	UsuarioBloqueo usuarioBloqueo = new UsuarioBloqueo();
	            	BeanUtils.copyProperties(usuarioBloqueo, (UsuarioBloqueoForm)this.bloqueosUsuarioForm.get(i));
	            	bloqueos.add(usuarioBloqueo);	            	
	            }	        	        	            
	        }

	        UsuarioBloqueoForm[] permisosBloqueoEliminarForm = usuarioPermisoBloqueoForm.getPermisosBloqueoEliminarForm();	        		
	        List bloqueosEliminar = new ArrayList();
	        if (permisosBloqueoEliminarForm != null && permisosBloqueoEliminarForm.length > 0) {            
	            for (int i = 0; i < permisosBloqueoEliminarForm.length; i++) {
	                UsuarioBloqueo usuarioBloqueo = new UsuarioBloqueo();
	                BeanUtils.copyProperties(usuarioBloqueo, permisosBloqueoEliminarForm[i]);
	                bloqueosEliminar.add(usuarioBloqueo);
	            }
	        }
	        
	        try{
	        	//Ya tenemos todos los valores en una lista
	        	//Lo que hacemos es enviarlo a la Base de datos
	        	service.removeBloqueosUsuario(bloqueosEliminar, usuario);
	        	service.insertBloqueosUsuario(bloqueos, usuario);
	        }catch(Exception e){
	        	return false;
	        }
	        return true;

	}

	@Override
	protected String getSalirForward() {
		return "usuarioList";
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {				
		return "usuarioPermisoBloqueo.updated";		
	}
	
	/**
	 * Adiciona Parametros
	 */
	public void addParametros(){
       
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'addParametros' method");
        }
        
        // Extraemos atributos y parámetros a usar
        UsuarioPermisoBloqueoForm usuarioPermisoBloqueoForm = (UsuarioPermisoBloqueoForm) this.formMantenimiento;
        
        if(usuarioPermisoBloqueoForm.getCodigoTipoBloqueo() != null && !usuarioPermisoBloqueoForm.getCodigoTipoBloqueo().isEmpty() &&
           usuarioPermisoBloqueoForm.getCodigoAccion() != null && !usuarioPermisoBloqueoForm.getCodigoAccion().isEmpty()){
        	
        	UsuarioBloqueoForm[] permisosBloqueoForm = usuarioPermisoBloqueoForm.getPermisosBloqueoForm();
        	UsuarioBloqueoForm usuarioBloqueoForm = new UsuarioBloqueoForm();
        	
            try {
				BeanUtils.copyProperties(usuarioBloqueoForm, usuarioPermisoBloqueoForm);
        		LabelValue labelValue = new LabelValue();        		
        		for(int i = 0; i<listaTipoBloqueoUsuario.size(); i++){
        			labelValue = listaTipoBloqueoUsuario.get(i);
        			if(usuarioPermisoBloqueoForm.getCodigoTipoBloqueo().equals(labelValue.getValue())){
        				usuarioBloqueoForm.setNombreTipoBloqueo(labelValue.getLabel());
        				break;
        			}
        		}        		
        		for(int i = 0; i<listaTipoAcciones.size(); i++){
        			labelValue = listaTipoAcciones.get(i);
        			if(usuarioPermisoBloqueoForm.getCodigoAccion().equals(labelValue.getValue())){
        				usuarioBloqueoForm.setNombreAccion(labelValue.getLabel());
        				break;
        			}
        		}
			} catch (Exception e) {				
				e.printStackTrace();
			} 
        	
        	if (this.bloqueosUsuarioForm != null && this.bloqueosUsuarioForm.size() > 0) {
        		//Buscamos si ya existe el tipo
        		boolean existe = false;
        		UsuarioBloqueoForm usuarioBloqueoFormAux = new UsuarioBloqueoForm();
        		for(int i=0; i < this.bloqueosUsuarioForm.size(); i++){
        			usuarioBloqueoFormAux = (UsuarioBloqueoForm)this.bloqueosUsuarioForm.get(i);
        			if(usuarioPermisoBloqueoForm.getCodigoTipoBloqueo().equals(usuarioBloqueoFormAux.getCodigoTipoBloqueo())){        					
        				existe = true;
        				break;
        			}        		
        		}

        		//Si no existe lo agregamos
        		if(!existe) {        			
        			this.bloqueosUsuarioForm.add(usuarioBloqueoForm);
        			this.addInfo("Info: ", this.getResourceMessage("usuarioPermisoBloqueo.added"));
        		}
        		else
        		{	
        			this.addError("Error: ", this.getResourceMessage("usuarioPermisoBloqueo.already.exists"));	        			
        			return;
        		}        	
        	}else {
        		this.bloqueosUsuarioForm = new ArrayList();
        		this.bloqueosUsuarioForm.add(usuarioBloqueoForm);        		
        	}
        	
        	if (this.log.isDebugEnabled()) {
        		this.log.debug("Nro de bloqueos: " + (this.bloqueosUsuarioForm == null ? 0 : this.bloqueosUsuarioForm.size()));
        	}
        	
        	//Limpiamos los valores agregados
        	usuarioPermisoBloqueoForm.setCodigoTipoBloqueo("");
        	usuarioPermisoBloqueoForm.setCodigoAccion("");
        	usuarioPermisoBloqueoForm.setNombreTipoBloqueo("");
        	usuarioPermisoBloqueoForm.setNombreAccion("");
        	
        	//
        	this.formMantenimiento = usuarioPermisoBloqueoForm;
        	this.dataTableBloqueosUsuario = new DataTableModel(this.bloqueosUsuarioForm);
        			
        }else{
        	this.addError("Error: ", this.getResourceMessage("usuarioPermisoBloqueoForm.error.tipo.accion.required"));
        }
        
	}
	
	/**
	 * Remueve Parametros
	 */
	public void removeParametros(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'removeParametros' method");
        }        

        if(verificarRegistrosSeleccionado()){
        	 // Extraemos atributos y parámetros a usar
            UsuarioPermisoBloqueoForm usuarioPermisoBloqueoForm = (UsuarioPermisoBloqueoForm) this.formMantenimiento;
                        
            UsuarioBloqueoForm[] permisosBloqueoEliminarForm = usuarioPermisoBloqueoForm.getPermisosBloqueoEliminarForm();
            
        	UsuarioBloqueoForm[] usuarioBloqueoFormSeleccionado = this.bloqueosUsuarioSeleccionado;
        	
        	for(int i=0; i < usuarioBloqueoFormSeleccionado.length; i++){
   				this.bloqueosUsuarioForm.remove(usuarioBloqueoFormSeleccionado[i]);            	
            	//Salvamos la lista de permisos a eliminar
            	permisosBloqueoEliminarForm = (UsuarioBloqueoForm[])ArrayUtils.add(permisosBloqueoEliminarForm, usuarioBloqueoFormSeleccionado[i]);
        	}
        	
        	usuarioPermisoBloqueoForm.setPermisosBloqueoEliminarForm(permisosBloqueoEliminarForm);
        	this.addInfo("Info: ", this.getResourceMessage("usuarioPermisoBloqueo.removed"));
        }
        this.bloqueosUsuarioSeleccionado = null;               
        this.dataTableBloqueosUsuario = new DataTableModel(this.bloqueosUsuarioForm);
	}


	/**
	 * Metodo que verifica si se ha seleccionado algun registro del Datatable de Parametros de Menu
	 * @return
	 */
	protected final boolean verificarRegistrosSeleccionado() {
		boolean verificar= true;		 
		try {
			if (this.bloqueosUsuarioSeleccionado.length <= 0)															
				verificar = false;
		}	
		catch (Exception e) {		
			verificar = false;
		}
		if (!verificar) 
			this.addWarn("Warning: ", this.getResourceMessage("usuarioPermisoBloqueoForm.error.elento.required"));
		return verificar;
	}
	
	/**
	 * @return the bloqueosUsuarioForm
	 */
	public List getBloqueosUsuarioForm() {
		return bloqueosUsuarioForm;
	}

	/**
	 * @param bloqueosUsuarioForm the bloqueosUsuarioForm to set
	 */
	public void setBloqueosUsuarioForm(List bloqueosUsuarioForm) {
		this.bloqueosUsuarioForm = bloqueosUsuarioForm;
	}
	
	/**
	 * @return the bloqueosUsuarioSeleccionado
	 */
	public UsuarioBloqueoForm[] getBloqueosUsuarioSeleccionado() {
		return bloqueosUsuarioSeleccionado;
	}

	/**
	 * @param bloqueosUsuarioSeleccionado the bloqueosUsuarioSeleccionado to set
	 */
	public void setBloqueosUsuarioSeleccionado(
			UsuarioBloqueoForm[] bloqueosUsuarioSeleccionado) {
		this.bloqueosUsuarioSeleccionado = bloqueosUsuarioSeleccionado;
	}		

	/**
	 * @return the dataTableBloqueosUsuario
	 */
	public DataTableModel getDataTableBloqueosUsuario() {
		return dataTableBloqueosUsuario;
	}

	/**
	 * @param dataTableBloqueosUsuario the dataTableBloqueosUsuario to set
	 */
	public void setDataTableBloqueosUsuario(DataTableModel dataTableBloqueosUsuario) {
		this.dataTableBloqueosUsuario = dataTableBloqueosUsuario;
	}


	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * @return the usuarioBusqueda
	 */
	public Usuario getUsuarioBusqueda() {
		return usuarioBusqueda;
	}


	/**
	 * @param usuarioBusqueda the usuarioBusqueda to set
	 */
	public void setUsuarioBusqueda(Usuario usuarioBusqueda) {
		this.usuarioBusqueda = usuarioBusqueda;
	}


	/**
	 * @return the listaTipoBloqueoUsuario
	 */
	public List<LabelValue> getListaTipoBloqueoUsuario() {
		return listaTipoBloqueoUsuario;
	}


	/**
	 * @param listaTipoBloqueoUsuario the listaTipoBloqueoUsuario to set
	 */
	public void setListaTipoBloqueoUsuario(List<LabelValue> listaTipoBloqueoUsuario) {
		this.listaTipoBloqueoUsuario = listaTipoBloqueoUsuario;
	}


	/**
	 * @return the listaTipoAcciones
	 */
	public List<LabelValue> getListaTipoAcciones() {
		return listaTipoAcciones;
	}


	/**
	 * @param listaTipoAcciones the listaTipoAcciones to set
	 */
	public void setListaTipoAcciones(List<LabelValue> listaTipoAcciones) {
		this.listaTipoAcciones = listaTipoAcciones;
	}

	
	
}

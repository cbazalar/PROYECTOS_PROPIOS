/*
 * biz.belcorp.privilege.web.action.MenuSearchAction
 */
package biz.belcorp.ssicc.web.seguridad.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.MenuOpciones;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroMenu;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.MenuService;
import biz.belcorp.ssicc.service.RolService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.seguridad.form.MenuForm;
import biz.belcorp.ssicc.web.seguridad.form.MenuSearchForm;
import biz.belcorp.ssicc.web.seguridad.form.ParametroMenuForm;

/**
 * Action invocado desde la pantalla de mantenimiento del objeto Menu.
 * <p>
 * <a href="MenuSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

@ManagedBean
@SessionScoped
public class MenuSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 7827415821190623655L;
	
	private DualListModel<MenuOpciones> listMenuOpciones;  

    protected List parametrosMenuForm;
    
    //Parametro de Menu seleccionado en el DataTable de Parametros de Menu
  	protected ParametroMenuForm[] parametrosMenuSeleccionado ;

	//Atributo DataTableModel usado en el Datatable la cual contiene la lista guardada en listaBusqueda
	protected DataTableModel dataTableParametros;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "menuForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		MenuSearchForm menuSearchForm = new MenuSearchForm();
		return menuSearchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
       
		if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }

        MenuSearchForm searchForm = (MenuSearchForm) this.formBusqueda;

        Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(searchForm);
        // Aadimos el valor del codigo de lenguaje ya que Canal es una tabla
        // multilenguaje
        criteria.put("codigoIdioma", usuario.getIdioma().getCodigo());
        
        // Modificamos los valores que requieren el caracter '%'
        if (StringUtils.isNotBlank(searchForm.getDescripcionMenu())) {
            criteria.put("descripcionMenu", searchForm.getDescripcionMenu() + "%");
        }
        if (StringUtils.isNotBlank(searchForm.getDescripcionMenuPadre())) {
            criteria.put("descripcionMenuPadre", searchForm.getDescripcionMenuPadre() + "%");
        }

        if (log.isDebugEnabled()) {
            log.debug(criteria.toString());
        }
        
        MenuService service = (MenuService)this.getBeanService("menuService");        		
        List lista = service.getMenuesByCriteria(criteria);        
        return lista;
	        
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
        
		if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        // Creamos las instancias de los objetos a usar        
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser(); 

        Menu menu = (Menu)this.beanRegistroSeleccionado;
        String id=menu.getCodigo();
                		        
        if (log.isDebugEnabled()) {
            log.debug("Id seleccionado de la lista: " + menu.getCodigo());
        }
        
        MenuService service = (MenuService)this.getBeanService("menuService");	       		
        service.removeMenu(id, usuario);
        return true;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {
		return "menu.deleted";
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		MenuSearchForm searchForm = (MenuSearchForm) this.formBusqueda;
		this.mostrarBotonConsultar = false;		
		searchForm.setCodigoMenu(this.mPantallaPrincipalBean.getCurrentMenu());
		super.find();
		searchForm.setCodigoMenu(null);
		this.salirGrabarPantallaPadre = true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
	
        // Extraemos atributos y parmetros a usar    
        MenuForm menuForm = (MenuForm)this.formMantenimiento;
        boolean isNew = menuForm.isNewRecord();
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        Pais pais=(Pais)usuario.getPais();
        
        RolService service1 = (RolService)this.getBeanService("rolService");     		

        // Creamos la instancia del servicio y le asignamos
        // el usuario que va a realizar las operaciones
        MenuService service = (MenuService)this.getBeanService("menuService");
        
        Menu menu = new Menu();
        BeanUtils.copyProperties(menu, menuForm);
        menu.setCodPais(pais.getCodigo());       
    	try {
    		
            if (this.parametrosMenuForm != null && this.parametrosMenuForm.size()>0) {
            	List<ParametroMenu> parametros = new ArrayList<ParametroMenu>();
    			for(ParametroMenuForm parametroMenuForm : (List<ParametroMenuForm>)this.parametrosMenuForm){
    				ParametroMenu parametroMenu = new ParametroMenu();
            		BeanUtils.copyProperties(parametroMenu, parametroMenuForm);
            		parametros.add(parametroMenu);        		
    			} 
    			menu.setParametros(parametros);
    		}else{
    			menu.setParametros(null);
    		}
    		                       
    		if (this.listMenuOpciones.getTarget() != null && this.listMenuOpciones.getTarget().size() > 0){
    			String[] botonesAsignados = new String[this.listMenuOpciones.getTarget().size()];
    			for(int i= 0; i < this.listMenuOpciones.getTarget().size(); i++ ){
    				MenuOpciones menuOpciones = (MenuOpciones)this.listMenuOpciones.getTarget().get(i);
    				botonesAsignados[i] = menuOpciones.getCodigoOpciones();
    				menu.setBotonesAsignados(botonesAsignados);
    			}
    		}else{
    			menu.setBotonesAsignados(new String[]{});
    		}
    		
    		if(this.listMenuOpciones.getSource() != null && this.listMenuOpciones.getSource().size() > 0){
    			String[] botonesNoAsignados = new String[this.listMenuOpciones.getSource().size()];
    			for(int i= 0; i < this.listMenuOpciones.getSource().size(); i++ ){
    				MenuOpciones menuOpciones = (MenuOpciones)this.listMenuOpciones.getSource().get(i);
    				botonesNoAsignados[i] = menuOpciones.getCodigoOpciones();
    			}
    			menu.setBotonesNoAsignados(botonesNoAsignados);
    		}else{
    			menu.setBotonesNoAsignados(new String[]{});
    		}
    		    		
    		if (isNew) {
                service.insertMenu(menu, usuario);                
            }
            else {
                service.updateMenu(menu, usuario);                
            }      
    		
        }
        catch (Exception e){
        	this.addError("Error: ", this.obtieneMensajeErrorException(e));
    		return false;
    	}
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Menu menubusqueda = (Menu)this.beanRegistroSeleccionado;		
		//Seteo de valores por default de nuevos registros
		MenuForm menuForm = new MenuForm();
		MenuService service = (MenuService)this.getBeanService("menuService");		
		
		this.dataTableParametros = null;
    	this.parametrosMenuForm = new ArrayList();
    	
		//Seteo de valores del registro seleccionado	
        if (this.accion.equals(this.ACCION_MODIFICAR) ) {
            String id = menubusqueda.getCodigo();
            
            // Si el id ha sido enviado, buscamos la informacion
            // en caso contrario, no hacemos nada, se esta insertando
            // un nuevo registro a la aplicación
            if (id != null) {
                if (log.isDebugEnabled()) {
                    log.debug("Id seleccionado de la lista: " + id);
                }
                
                Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
                Menu menu = service.getMenu(id,usuario.getIdioma().getCodigo());                		
                
                // Copiamos los atributos del bean al form
                BeanUtils.copyProperties(menuForm, menu);
                                        
                // Obtenemos los parametros
        		List listaParametros = menu.getParametros();
                if (listaParametros != null && listaParametros.size()>0) {                	
                	for(int i=0; i<listaParametros.size(); i++){
                		ParametroMenuForm parametroMenuForm = new ParametroMenuForm();
                		BeanUtils.copyProperties(parametroMenuForm, listaParametros.get(i));
                		this.parametrosMenuForm.add(parametroMenuForm);
                	}
                	this.dataTableParametros = new DataTableModel(this.parametrosMenuForm);                 	
                }                                               
               
                List<MenuOpciones> menuOpcionesTarget = (List<MenuOpciones>)service.getMenuOpcionesAsignadas(menu.getCodigo());                                
                List<MenuOpciones> menuOpcionesSource = (List<MenuOpciones>)service.getMenuOpcionesFaltantes(menu.getCodigo());                
                this.listMenuOpciones = new DualListModel<MenuOpciones>(menuOpcionesSource, menuOpcionesTarget);                
                menuForm.setNewRecord(false);
            }
        }else{
        	//Menu Opciones : configuracion de Botones 
        	List<MenuOpciones> menuOpcionesTarget = (List<MenuOpciones>)service.getMenuOpcionesAsignadas(null);                                
        	List<MenuOpciones> menuOpcionesSource = (List<MenuOpciones>)service.getMenuOpcionesFaltantes(null);                
        	this.listMenuOpciones = new DualListModel<MenuOpciones>(menuOpcionesSource, menuOpcionesTarget);
       }        
        return menuForm;    
	}

	@Override
	protected String getSalirForward() {		
		return "menuList";
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MenuForm menuForm = (MenuForm) this.formMantenimiento;
		boolean isNew = menuForm.isNewRecord();
		if(isNew){
			return "menu.added";
		}else{
			return "menu.updated";
		}	
	}

	/**
	 * @param ajaxBehaviorEvent
	 */
	public void findDescripcionMenuPadre(){	
		
		log.debug("Enter method - findDescripcionMenuPadre");
		MenuForm menuForm = (MenuForm)this.formMantenimiento;
		
		try {					
			MenuService service = (MenuService)this.getBeanService("menuService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			if(StringUtils.isNotBlank(menuForm.getCodigoPadre()))
			{
				Menu menu = service.getMenu(menuForm.getCodigoPadre(), usuario.getIdioma().getCodigo());			
				if(menu != null)
					menuForm.setDescripcionPadre(menu.getDescripcion());				
			}
			
		} catch (Exception e) {
			menuForm.setDescripcionPadre("");	
			String mensaje = "menuForm.validacion.codigo.menu";
			this.addError("Error: ", this.getResourceMessage(mensaje));
		}					 
	}
	
	/**
	 * Adiciona Parametros
	 */
	public void addParametros(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'addParametrosMenu' method");
        }
        MenuForm menuForm = (MenuForm)this.formMantenimiento;  
        // Extraemos atributos y parámetros a usar  
        ParametroMenuForm parametroMenuForm = new ParametroMenuForm();
        parametroMenuForm.setNombre(menuForm.getNombreParametro());
        parametroMenuForm.setValor(menuForm.getValorParametro());
        parametroMenuForm.setCodigoMenu(menuForm.getCodigo());
        parametroMenuForm.setEstado(Constants.ESTADO_ACTIVO);
                        
        if (this.parametrosMenuForm != null) {
        	int numero = this.parametrosMenuForm.size()+1;        		
            parametroMenuForm.setNumero(numero+"");
        	this.parametrosMenuForm.add(parametroMenuForm);            
        }
        else {
        	this.parametrosMenuForm = new ArrayList();
        	parametroMenuForm.setNumero("1");
        	this.parametrosMenuForm.add(parametroMenuForm);            
        }
        
        if (log.isDebugEnabled()) {
            log.debug("Nro de Parametros: " + (this.parametrosMenuForm == null ? 0 : this.parametrosMenuForm.size()));
        }
        
        menuForm.setNombreParametro("");
        menuForm.setValorParametro("");
        
        this.formMantenimiento = menuForm;
    	this.dataTableParametros = new DataTableModel(this.parametrosMenuForm);

	}
	
	/**
	 * Remueve Parametros
	 */
	public void removeParametros(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'removeParametrosMenu' method");
        }        

        if(verificarRegistrosSeleccionado()){        	
        	ParametroMenuForm[] parametrosMenuSeleccionado = this.parametrosMenuSeleccionado;
        	for(int i=0; i < parametrosMenuSeleccionado.length; i++){
   				this.parametrosMenuForm.remove(parametrosMenuSeleccionado[i]);        				
        	}        	
        }
        this.parametrosMenuSeleccionado = null;               
        this.dataTableParametros = new DataTableModel(this.parametrosMenuForm);
	}


	/**
	 * Metodo que verifica si se ha seleccionado algun registro del Datatable de Parametros de Menu
	 * @return
	 */
	protected final boolean verificarRegistrosSeleccionado() {
		boolean verificar= true;		 
		try {
			if (this.parametrosMenuSeleccionado.length <= 0)										
				verificar = false;
		}	
		catch (Exception e) {		
			verificar = false;
		}
		if (!verificar) 
			this.addWarn("Warning: ", this.getResourceMessage("menuForm.parametros.eliminar"));
		return verificar;
	}
	
	
	/**
	 * @return the listMenuOpciones
	 */
	public DualListModel<MenuOpciones> getListMenuOpciones() {
		return listMenuOpciones;
	}

	/**
	 * @param listMenuOpciones the listMenuOpciones to set
	 */
	public void setListMenuOpciones(DualListModel<MenuOpciones> listMenuOpciones) {
		this.listMenuOpciones = listMenuOpciones;
	}

	/**
	 * @return the parametrosMenuForm
	 */
	public List getParametrosMenuForm() {
		return parametrosMenuForm;
	}

	/**
	 * @param parametrosMenuForm the parametrosMenuForm to set
	 */
	public void setParametrosMenuForm(List parametrosMenuForm) {
		this.parametrosMenuForm = parametrosMenuForm;
	}

	/**
	 * @return the parametrosMenuSeleccionado
	 */
	public ParametroMenuForm[] getParametrosMenuSeleccionado() {
		return parametrosMenuSeleccionado;
	}

	/**
	 * @param parametrosMenuSeleccionado the parametrosMenuSeleccionado to set
	 */
	public void setParametrosMenuSeleccionado(
			ParametroMenuForm[] parametrosMenuSeleccionado) {
		this.parametrosMenuSeleccionado = parametrosMenuSeleccionado;
	}

	/**
	 * @return the dataTableParametros
	 */
	public DataTableModel getDataTableParametros() {
		return dataTableParametros;
	}

	/**
	 * @param dataTableParametros the dataTableParametros to set
	 */
	public void setDataTableParametros(DataTableModel dataTableParametros) {
		this.dataTableParametros = dataTableParametros;
	}	
	
	
	
	
	
}
/*
 * biz.belcorp.ssicc.web.action.RolSearchAction
 */
package biz.belcorp.ssicc.web.seguridad.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModelListener;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Acceso;
import biz.belcorp.ssicc.dao.model.MenuRol;
import biz.belcorp.ssicc.dao.model.Rol;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.MenuService;
import biz.belcorp.ssicc.service.RolService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.framework.util.MenuDataModel;
import biz.belcorp.ssicc.web.seguridad.form.RolForm;
import biz.belcorp.ssicc.web.seguridad.form.RolSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RolSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 2245274831778743054L;
	private TreeNode rootMenu;
	private TreeNode[] selectedNodes;
	private List<MenuRol> menuRols;
	private List accesosRol;
	
	private DataTableModel menuDataModel = new DataTableModel();
	private List listMenuDataModel = new ArrayList();
	private List selectedMenuRol = new ArrayList();
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "rolForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		RolSearchForm rolSearchForm = new RolSearchForm();
		return rolSearchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }

        RolSearchForm searchForm = (RolSearchForm) this.formBusqueda;

        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(searchForm);

        // Modificamos los valores que requieren el caracter '%'
        if (StringUtils.isNotBlank(searchForm.getDescripcionRol())) {
            criteria.put("descripcionRol", searchForm.getDescripcionRol() + "%");
        }

        if (log.isDebugEnabled()) {
            log.debug(criteria.toString());
        }

        RolService service = (RolService) this.getBeanService("rolService");
        		
        List lista = service.getRolesByCriteria(criteria);
        
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
        
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser(); 

        Rol rol = (Rol) this.beanRegistroSeleccionado;

        if (log.isDebugEnabled()) {
            log.debug("Codigo seleccionado de la lista: " + rol.getCodigo()+"	Codigo Pais: "+rol.getCodigoPais());
        }
        // Todas las excepciones son capturadas por ActionExceptionHandler
        RolService service = (RolService) getBean("rolService");
        service.removeRol(rol.getCodigoPais(), rol.getCodigo(), usuario);
        return true;
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {

		return "rol.deleted";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;		
		this.salirGrabarPantallaPadre = true;
		this.mostrarBotonSave = false;
		super.find();
	}

	@Override
	protected String getSalirForward() {		
		return "rolList";
	}  
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		RolForm rolForm = (RolForm) this.formMantenimiento;
		boolean isNew = rolForm.isNewRecord();
		if(isNew){
			return "rol.added";
		}else{
			return "rol.updated";
		}	
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
	            log.debug("Entering 'save' method");
        }

		log.debug("tamanio: " + this.getSelectedMenuRol().size());
		
        RolForm rolForm = (RolForm) this.formMantenimiento;
        Rol rolbusqueda = (Rol)this.beanRegistroSeleccionado;
        //boolean isNew = rolForm.isNewRecord();

        // Extreamos el usuario de la sesión
        if(rolbusqueda != null){
            rolForm.setCodigoPais(rolbusqueda.getCodigoPais());
        }
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        
        // Creamos la instancia del servicio y le asignamos
        // el usuario que va a realizar las operaciones
        RolService service = (RolService) this.getBeanService("rolService"); 
        		
        Rol rol = new Rol();
        BeanUtils.copyProperties(rol, rolForm);

        try{
        	
        	List accesosRol = new ArrayList();
        	MenuRol menuRol = new MenuRol();
        	MenuRol menuRolSeleccionado = new MenuRol();
        	if(this.selectedMenuRol != null && this.selectedMenuRol.size()>0){
        		
        		for(int i = 0; i < this.listMenuDataModel.size(); i++) {
                	menuRol = (MenuRol) this.listMenuDataModel.get(i);
                    String codigoMenu =  menuRol.getCodigo();	
                    String estado = menuRol.getEstadoActivo();	
                    estado = Constants.ESTADO_INACTIVO;
                    for (int j = 0; j < this.selectedMenuRol.size(); j++) {
                    	menuRolSeleccionado = (MenuRol) this.selectedMenuRol.get(j);
                    	String codigoMenuSeleccionado = menuRolSeleccionado.getCodigo();
                    	if(StringUtils.equals(codigoMenu, codigoMenuSeleccionado)){
                    		estado = Constants.ESTADO_ACTIVO;
                    		this.selectedMenuRol.remove(j);
                    		break;
                    	}					
    				}                                
                    Acceso acceso = new Acceso();
                    acceso.setCodigoMenu(codigoMenu);
                    acceso.setEstado(estado);
                    
                    accesosRol.add(acceso);
                }
        		
        	}else{
        		
        		for(int i = 0; i < this.listMenuDataModel.size(); i++) {
                	menuRol = (MenuRol) this.listMenuDataModel.get(i);
                    String codigoMenu =  menuRol.getCodigo();	
                    String estado = menuRol.getEstadoActivo();	
                    estado = Constants.ESTADO_INACTIVO;
                                              
                    Acceso acceso = new Acceso();
                    acceso.setCodigoMenu(codigoMenu);
                    acceso.setEstado(estado);
                    
                    accesosRol.add(acceso);
                }
        		
        	}
            
            // Actualizamos los accesos al objeto rol
            rol.setAccesos(accesosRol);
	        
	        // agregamos los mensajes de exito
	        if (this.accion.equals(this.ACCION_NUEVO)) {
	        	log.debug("Nuevo rol...");
	            service.insertRol(rol, usuario);
	        }
	        else {
	        	log.debug("Actualizar rol...");
	            service.updateRol(rol, usuario);
	        }
        }catch(Exception e){
        	this.addError("Error: ", this.obtieneMensajeErrorException(e));
    		return false;
        }
        
        return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Rol rolbusqueda = (Rol)this.beanRegistroSeleccionado;
		
//		this.menuDataModel = new DataTableModel();
//		this.listMenuDataModel = new ArrayList();
//		this.selectedMenuRol = new ArrayList();
		
		// Creamos algunos de los objetos y servicios a usar
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser(); 
		RolForm rolForm = new RolForm(); 
		String codigoRol= null;		
		
		if(this.getAccion().equals(this.ACCION_MODIFICAR)){
			// Obtenemos el codigo seleccionado
			String codigo = rolbusqueda.getCodigo(); 
			String codigoPais = rolbusqueda.getCodigoPais();
			
            // Si el id ha sido enviado, buscamos la informacion
            // en caso contrario, no hacemos nada, se esta insertando
            // un nuevo registro a la aplicación
			if( codigo != null){
				RolService service = (RolService)this.getBeanService("rolService");				
				Rol rol = service.getRol(codigoPais, codigo, usuario.getIdioma().getCodigo());
				// Copiamos los atributos del bean al form
				BeanUtils.copyProperties(rolForm, rol);
				// y copiamos la descripcion del pais
                rolForm.setDescripcionPais(rol.getPais().getDescripcion());                
                rolForm.setNewRecord(false);
                codigoRol = rol.getCodigo();
			}
		}
		
		// Colocamos la lista de accesos en la sesion, ya sea la lista vacia o la lista
		// con accesos en caso se este editanto un rol
		//request.getSession().setAttribute(Constants.ACCESO_ROL_LIST, accesosRol);
		
		// Obtenemos la relacion de opciones de menu
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigo());
		criteria.put("codigoRol", codigoRol);
		criteria.put("codigoPais", rolForm.getCodigoPais());
		
		MenuService menuService = (MenuService)this.getBeanService("menuService");
		
		List menues = menuService.getMenuesByCriteriaRol(criteria);
		MenuRol menuRolEspacios = new MenuRol();
		List aux = new ArrayList();
		String espaciado = "";
		MenuRol menuRol = new MenuRol();
		List listaAux = new ArrayList();
		for (int i = 0; i < menues.size(); i++) {
			menuRolEspacios = (MenuRol) menues.get(i);
			menuRol = (MenuRol) menues.get(i);					
			if(StringUtils.equals(menuRol.getEstadoActivo(), Constants.ESTADO_ACTIVO)){
				menuRol.setSubmenues(new ArrayList());
				listaAux.add(menuRol);
				
			}
			// Espacias para mostrar en la vista
			int nivel = Integer.parseInt(menuRolEspacios.getNivel());
			for (int j = 0; j < nivel; j++) {
				espaciado = "&nbsp&nbsp&nbsp&nbsp&nbsp"+espaciado;	
			}			
			menuRolEspacios.setDescripcion(espaciado+menuRolEspacios.getDescripcion());
			aux.add(menuRolEspacios);
			espaciado = "";
			
		}		
		
		this.listMenuDataModel = aux;

		this.menuDataModel = new DataTableModel(this.listMenuDataModel);
		
		this.selectedMenuRol = listaAux;
		this.formMantenimiento = rolForm;
		return rolForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setAddAttributes()
	 */
	@Override
	protected void setAddAttributes() throws Exception {
		
		RolForm rolForm = (RolForm) this.formMantenimiento;
		rolForm.setCodigoPais("");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setEditAttributes()
	 */
	@Override
	protected void setEditAttributes() throws Exception {

		Rol rol = (Rol) this.beanRegistroSeleccionado;
		
		RolForm rolForm = new RolForm();
		rolForm.setCodigoPais(rol.getCodigoPais());
	}

	/**
	 * @return the rootMenu
	 */
	public TreeNode getRootMenu() {
		return rootMenu;
	}

	/**
	 * @param rootMenu the rootMenu to set
	 */
	public void setRootMenu(TreeNode rootMenu) {
		this.rootMenu = rootMenu;
	}
	
	
	/**
     * En base a la lista del menu rol obtenida de Base de Datos se reorganiza en 
     * forma de Arbol para su visualizacion
     * @param listaMenuRol
     * @return
     */
    public List organizarMenuRole(List listaMenuRol) {
    	if (log.isDebugEnabled()) {
            log.debug("Entering 'organizarMenuRole' method");
        } 
    	
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
		if (log.isDebugEnabled()) {
            log.debug("Finish 'organizarMenuRole' method");
        }
		return listaMenuRolReturn;
	}
	
	
	/**
     * Obtiene el treeArbol en base al List del Menu
     * @param menues
     */
//    private void organizarTreeMenues(List<MenuRol> menues) {
//    	if (log.isDebugEnabled()) {
//            log.debug("Entering 'organizarTreeMenues' method");
//        }
//    	
//    	this.rootMenu = new CheckboxTreeNode("rootMenu", null);
//    	this.rootMenu.setExpanded(true);
//    	this.menuRols = new ArrayList();
//    	for (int x = 0; x < menues.size(); x++) {
//    		MenuRol m = (MenuRol) menues.get(x);    		
//			TreeNode tmenu;	
//			if (m.getMenuPadre() == null) {
//				tmenu = new CheckboxTreeNode(m, rootMenu);
//				tmenu.setExpanded(true);
//				List<MenuRol> listasubmenu = m.getSubmenues();
//				getMenuRols().add(m);
//				for (int y = 0; y < listasubmenu.size(); y++) {
//					MenuRol submenu = (MenuRol) listasubmenu.get(y);					
//					TreeNode tsubmenu = new CheckboxTreeNode(submenu, tmenu);
//					tsubmenu.setSelected(submenu.getEstadoActivo().equals(Constants.ESTADO_ACTIVO)?true:false);
//					tsubmenu.setExpanded(true);
//					List<MenuRol> listasubsubmenu = submenu.getSubmenues();
//					getMenuRols().add(submenu);
//					for (int z = 0; z < listasubsubmenu.size(); z++) {
//						MenuRol subsubmenu = (MenuRol) listasubsubmenu.get(z);						
//						TreeNode tsubsubmenu = new CheckboxTreeNode(subsubmenu, tsubmenu);
//						tsubsubmenu.setSelected(subsubmenu.getEstadoActivo().equals(Constants.ESTADO_ACTIVO)?true:false);
//						tsubsubmenu.setExpanded(true);						
//						List<MenuRol> listasubsubsubmenu = subsubmenu.getSubmenues();
//						getMenuRols().add(subsubmenu);
//						for (int k = 0; k < listasubsubsubmenu.size(); k++) {
//							MenuRol subsubsubmenu = (MenuRol) listasubsubsubmenu.get(k);							
//							TreeNode tsubsubsubmenu = new CheckboxTreeNode(subsubsubmenu, tsubsubmenu);
//							tsubsubsubmenu.setSelected(subsubsubmenu.getEstadoActivo().equals(Constants.ESTADO_ACTIVO)?true:false);
//							tsubsubsubmenu.setExpanded(true);
//							getMenuRols().add(subsubsubmenu);
//						}
//					}	
//				}	
//			}
//		}
//    	
//    	this.menuDataModel = new MenuDataModel(this.getMenuRols());
//    	
//    	List<MenuRol> listaAux = new ArrayList<MenuRol>();
//    	for (MenuRol menuRol : this.getMenuRols()) {
//    		if(menuRol.getEstadoActivo().equals(Constants.ESTADO_ACTIVO)){
//    			menuRol.setSubmenues(new ArrayList());
//    			listaAux.add(menuRol);
//    		}
//		}
//    	    	
//    	selectedMenuRol = listaAux.toArray(new MenuRol[listaAux.size()]);
//    	
//    	if (log.isDebugEnabled()) {
//            log.debug("Finish 'organizarTreeMenues' method");
//        }
//    	return;
//    }
    
	/**
     * Setea el estado de acceso del Menu segun los accesos del Rol respectivo
     * @param listamenuRol
     */
    private void obtenerEstadoAcceso(List<MenuRol> listamenuRol){
    	if(listamenuRol!=null && listamenuRol.size()>0){
    		for(MenuRol menuRol : listamenuRol){
    			boolean status = menuRol.getEstadoActivo().equals(Constants.ESTADO_ACTIVO)?true:false;   					
    			menuRol.setEstadoAcceso(status);
    		}    		
    	}   	
    }
    
	/**
     * Metodo que envia el nodo raiz de los menus para recuperar el acceso seteado el el form
     * @param treeNodeMenu
     */    
    private void obtenerListaMenu(TreeNode treeNodeMenu){    	
    	this.accesosRol = new ArrayList();
    	obtenerNodoMenuRol(treeNodeMenu.getChildren());
    }
    
    
    /**
     * Metodo que envia una coleccion de elementos para cambiar el valor del acceso de
     * acuerdo a su estado actual. 
     * @param listaMenus
     */  
    private void obtenerListaMenu(List<MenuRol> listaMenus){    	
    	this.accesosRol = new ArrayList();
    	for(MenuRol menuRol : listaMenus){    		
    		Acceso acceso = new Acceso();
    		acceso.setCodigoMenu(menuRol.getCodigo());
    		boolean status = menuRol.isEstadoAcceso();
    		acceso.setEstado(status?Constants.ESTADO_ACTIVO:Constants.ESTADO_INACTIVO);                 
    		this.accesosRol.add(acceso);    		
    	}
    }
    
    /**
     * Metodo que recorre recursivamente el arbol de menues para obtener la lista de accesos de los menues 
     * @param listaNodos
     */
    private void obtenerNodoMenuRol(List<TreeNode> listaNodos){    	
    	for(TreeNode treeNode : listaNodos){
    		MenuRol menuRol = (MenuRol)treeNode.getData();
    		Acceso acceso = new Acceso();
    		acceso.setCodigoMenu(menuRol.getCodigo());
    		boolean status = menuRol.isEstadoAcceso();
    		acceso.setEstado(status?Constants.ESTADO_ACTIVO:Constants.ESTADO_INACTIVO);                 
    		this.accesosRol.add(acceso);
    		if(treeNode.getChildren()!=null && treeNode.getChildren().size()>0){
    			obtenerNodoMenuRol(treeNode.getChildren());
    		}
    	}
    }
    
    
    /**
     * Metodo selecciona todos los accesos de los menus cuando se elige seleccionar todos 
     * @param event
     */
    public void selectAll(ValueChangeEvent event){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'selectAll' method");
        }        
        boolean status = StringUtil.convertirABoolean(event.getNewValue());
        setSeleccionarTodo(this.rootMenu.getChildren(),status);   	
    }
        
    
    /**
     * Metodo que recorre recursivamente el arbol de menues para setear true o false a todo los menus 
     * @param listaNodos
     */
    private void setSeleccionarTodo(List<TreeNode> listaNodos, boolean status){
    	for(TreeNode treeNode : listaNodos){
    		MenuRol menuRol = (MenuRol)treeNode.getData();
    		menuRol.setEstadoAcceso(status);    		
    		if(treeNode.getChildren()!=null && treeNode.getChildren().size()>0){
    			setSeleccionarTodo(treeNode.getChildren(), status);
    		}
    	}
    }

	/**
	 * @return the accesosRol
	 */
	public List getAccesosRol() {
		return accesosRol;
	}

	/**
	 * @param accesosRol the accesosRol to set
	 */
	public void setAccesosRol(List accesosRol) {
		this.accesosRol = accesosRol;
	}

	public TreeNode[] getSelectedNodes() {		
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}
	
	public List<MenuRol> getMenuRols() {
		return menuRols;
	}

	public void setMenuRols(List<MenuRol> menuRols) {
		this.menuRols = menuRols;
	}

	/**
	 * @return the menuDataModel
	 */
	public DataTableModel getMenuDataModel() {
		return menuDataModel;
	}

	/**
	 * @param menuDataModel the menuDataModel to set
	 */
	public void setMenuDataModel(DataTableModel menuDataModel) {
		this.menuDataModel = menuDataModel;
	}

	/**
	 * @return the selectedMenuRol
	 */
	public List getSelectedMenuRol() {
		return selectedMenuRol;
	}

	/**
	 * @param selectedMenuRol the selectedMenuRol to set
	 */
	public void setSelectedMenuRol(List selectedMenuRol) {
		this.selectedMenuRol = selectedMenuRol;
	}

	/**
	 * @return the listMenuDataModel
	 */
	public List getListMenuDataModel() {
		return listMenuDataModel;
	}

	/**
	 * @param listMenuDataModel the listMenuDataModel to set
	 */
	public void setListMenuDataModel(List listMenuDataModel) {
		this.listMenuDataModel = listMenuDataModel;
	}
	
	

	
//	public void setSelectedMenuRol(MenuRol[] selectedMenuRol) {
//		this.selectedMenuRol = selectedMenuRol;
//		if(selectedMenuRol.length>0){
//			List<MenuRol> listado1 = (List<MenuRol>)this.getMenuDataModel().getWrappedData();
//			List<MenuRol> listado2 = Arrays.asList(selectedMenuRol);
//			int valor = existeCambioInListas(listado1,listado2); 
//			if(valor>0){
//				actualizarListadoMenu(listado1,listado2);
//			}
//		}
//	}
	
	/**
	 * Metodo que se encarga de comparar los elementos de ambos listados, 
	 * verifica el tamanio de los parametros si la lista 1 es mayor a la lista2
	 * @param lista1
	 * @param lista2
	 * @return int, valor de resultado
	 */
//	public int existeCambioInListas(List<MenuRol> lista1, List<MenuRol> lista2){
//		if(lista1.size() > lista2.size()){
//			return 1;
//		}		
//		return 0;
//	}
	
	/**
	 * Metodo que se encarga de actualizar cada elemento de las colecciones de los parametros. 
	 * @param listado1
	 * @param listado2
	 */
//	public void actualizarListadoMenu(List<MenuRol> listado1, List<MenuRol> listado2){
//		log.debug("actualizarListadoMenu");
//		List<MenuRol> listIntersection = new ArrayList<MenuRol>();
//		List<MenuRol> listSubtract = new ArrayList<MenuRol>();
//		listIntersection = ListUtils.intersection(listado1, listado2);
//		listSubtract =  ListUtils.subtract(listado1, listado2);
//		
//		for (MenuRol menuRol : listIntersection) {
//			menuRol.setEstadoActivo(Constants.ESTADO_ACTIVO);
//			menuRol.setEstadoAcceso(true);
//		}
//		
//		for (MenuRol menuRol : listSubtract) {
//			menuRol.setEstadoActivo(Constants.ESTADO_INACTIVO);
//			menuRol.setEstadoAcceso(false);
//		}
//	}

}
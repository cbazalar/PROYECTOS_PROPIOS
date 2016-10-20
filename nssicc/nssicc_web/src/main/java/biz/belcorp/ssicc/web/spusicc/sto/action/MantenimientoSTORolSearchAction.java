package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Rol;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccesoRolSTO;
import biz.belcorp.ssicc.service.LookupService;
import biz.belcorp.ssicc.service.RolService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTORolForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTORolSearchForm;

/**
 * The Class MantenimientoSTORolSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 11/02/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoSTORolSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -6440455835637455566L;
	private List allPaises;
	private List stoAccesoRolList;
	
	private List listMenuDataModel = new ArrayList();
	private DataTableModel menuDataModel = new DataTableModel();
	private List selectedMenuRol = new ArrayList();
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTORolForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTORolSearchForm searchForm = new MantenimientoSTORolSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		LookupService service = (LookupService) getBean("lookupService");
		this.allPaises = service.getAllPaises();
		find();
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }
        
        MantenimientoSTORolSearchForm searchForm = (MantenimientoSTORolSearchForm) this.formBusqueda;

        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(searchForm);
       
        // Modificamos los valores que requieren el caracter '%'
        if (StringUtils.isNotBlank(searchForm.getDescripcionRol())) {
            criteria.put("descripcionRol", searchForm.getDescripcionRol() + "%");
        }
       
        if (log.isDebugEnabled()) {
            log.debug(criteria.toString());
        }
        
        RolService service = (RolService) getBean("rolService");
        List lista = service.getRolesByCriteria(criteria);        
        return lista;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSTORolSearchForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
        ProcesoSTOLevantamientoErroresValidacionService procesoSTOLevantamientoErroresValidacionService = (ProcesoSTOLevantamientoErroresValidacionService) 
        																				getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
        // Extraemos atributos y parámetros a usar
        MantenimientoSTORolForm rolForm = (MantenimientoSTORolForm) this.formMantenimiento;

        // Extreamos el usuario de la sesión
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
       
        Rol rol = new Rol();
        BeanUtils.copyProperties(rol, rolForm);
        
        List accesosRol = new ArrayList();
        AccesoRolSTO accesoRolSTO = new AccesoRolSTO();
        AccesoRolSTO accesoRolSTOSeleccionado = new AccesoRolSTO();
        
        if(this.selectedMenuRol != null && this.selectedMenuRol.size()>0){
        	for(int i = 0; i < this.listMenuDataModel.size(); i++) {
        		accesoRolSTO = (AccesoRolSTO) this.listMenuDataModel.get(i);
        		String codigoTipoDoc = accesoRolSTO.getCodigoTipoDocumento();
        		String codigoAccion = accesoRolSTO.getCodigoAccion();
        		String estado = accesoRolSTO.getEstadoAccion();
        		estado = Constants.NUMERO_CERO;
        		
        		for (int j = 0; j < this.selectedMenuRol.size(); j++) {
        			accesoRolSTOSeleccionado = (AccesoRolSTO) this.selectedMenuRol.get(j);
        			String codigoTipoDocSeleccionado = accesoRolSTOSeleccionado.getCodigoTipoDocumento();
        			String codigoAccionSeleccionado = accesoRolSTOSeleccionado.getCodigoAccion();
        			if(StringUtils.equals(codigoTipoDoc, codigoTipoDocSeleccionado) && StringUtils.equals(codigoAccion, codigoAccionSeleccionado)){
                		estado = Constants.ESTADO_ACTIVO;
                		this.selectedMenuRol.remove(j);
                		break;
                	}
        		}
        		AccesoRolSTO accesoRolSTOList = new AccesoRolSTO();
        		accesoRolSTOList.setCodigoAccion(codigoAccion);
        		accesoRolSTOList.setCodigoTipoDocumento(codigoTipoDoc);
        		accesoRolSTOList.setEstadoAccion(estado);
        		accesoRolSTOList.setCodigoPais(rolForm.getCodigoPais());
        		accesosRol.add(accesoRolSTOList);
        		
        	}
        }else{
        	for(int i = 0; i < this.listMenuDataModel.size(); i++) {
        		accesoRolSTO = (AccesoRolSTO) this.listMenuDataModel.get(i);
        		String codigoTipoDoc = accesoRolSTO.getCodigoTipoDocumento();
        		String codigoAccion = accesoRolSTO.getCodigoAccion();
        		String estado = accesoRolSTO.getEstadoAccion();
        		estado = Constants.NUMERO_CERO;
        		
        		AccesoRolSTO accesoRolSTOList = new AccesoRolSTO();
        		accesoRolSTOList.setCodigoAccion(codigoAccion);
        		accesoRolSTOList.setCodigoTipoDocumento(codigoTipoDoc);
        		accesoRolSTOList.setEstadoAccion(estado);
        		accesoRolSTOList.setCodigoPais(rolForm.getCodigoPais());
        		accesosRol.add(accesoRolSTOList);
        	}
        }
       
        procesoSTOLevantamientoErroresValidacionService.insertRol(accesosRol, rol.getCodigo(), usuario.getLogin());
        
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoSTORolForm mantenimientoForm = new MantenimientoSTORolForm();
		this.stoAccesoRolList = new ArrayList();
		if (!this.accion.equals(this.ACCION_NUEVO)) {  	   
			
			ProcesoSTOLevantamientoErroresValidacionService procesoSTOLevantamientoErroresValidacionService = (ProcesoSTOLevantamientoErroresValidacionService)
																								getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
	        // Obtenemos el id seleccionado
	        Rol rol = (Rol) this.beanRegistroSeleccionado;
	        
            // Copiamos los atributos del bean al form
            BeanUtils.copyProperties(mantenimientoForm, rol);
            // y copiamos la descripcion del pais
            mantenimientoForm.setDescripcionPais(rol.getPais().getDescripcion());

            String codigoRol = rol.getCodigo();
	        
	        // Obtenemos la relacion de opciones de menu
	        Map criteria = new HashMap();
	        criteria.put("codigoIdioma", usuario.getIdioma().getCodigo());
	        criteria.put("codigoRol", codigoRol);
	        criteria.put("codigoPais", mantenimientoForm.getCodigoPais());
	        
	        List accionesList = procesoSTOLevantamientoErroresValidacionService.getAccionesByRol(criteria);
	        AccesoRolSTO accesoRolEspacios = new AccesoRolSTO();
	        List aux = new ArrayList();
			String espaciado = "";
			AccesoRolSTO accesoRol = new AccesoRolSTO();
			List listaAux = new ArrayList();
			for (int i = 0; i < accionesList.size(); i++) {
				accesoRolEspacios = (AccesoRolSTO) accionesList.get(i);
				accesoRol = (AccesoRolSTO) accionesList.get(i);
				if(StringUtils.equals(accesoRol.getEstadoAccion(), Constants.ESTADO_ACTIVO)){
					listaAux.add(accesoRol);
				}
				// Espacias para mostrar en la vista
				int nivel = accesoRolEspacios.getNivelAccion();
				for (int j = 0; j < nivel; j++) {
					espaciado = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+espaciado;
				}
				accesoRolEspacios.setDesAccion(espaciado+accesoRolEspacios.getDesAccion());
				aux.add(accesoRolEspacios);
				espaciado = "";
			}
	        
			this.listMenuDataModel = aux;
			this.menuDataModel = new DataTableModel(this.listMenuDataModel);
			
			this.selectedMenuRol = listaAux;
			
			mantenimientoForm.setNewRecord(false);
		}		
		
		return mantenimientoForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		return "rol.updated";	
	}
	

	/**
	 * @return the allPaises
	 */
	public List getAllPaises() {
		return allPaises;
	}

	/**
	 * @param allPaises the allPaises to set
	 */
	public void setAllPaises(List allPaises) {
		this.allPaises = allPaises;
	}

	/**
	 * @return the stoAccesoRolList
	 */
	public List getStoAccesoRolList() {
		return stoAccesoRolList;
	}

	/**
	 * @param stoAccesoRolList the stoAccesoRolList to set
	 */
	public void setStoAccesoRolList(List stoAccesoRolList) {
		this.stoAccesoRolList = stoAccesoRolList;
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
}
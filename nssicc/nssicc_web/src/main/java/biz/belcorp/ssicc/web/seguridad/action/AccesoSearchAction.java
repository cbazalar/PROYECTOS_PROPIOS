package biz.belcorp.ssicc.web.seguridad.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Acceso;
import biz.belcorp.ssicc.dao.model.Rol;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.RolService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.seguridad.form.AccesoForm;
import biz.belcorp.ssicc.web.seguridad.form.AccesoSearchForm;
import biz.belcorp.ssicc.web.util.CommonUtilValue;

/**
 * TODO Include class description here.
 * <p>
 * <a href="AccesoSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:dhinostroza@belcorp.biz">David Hinostroza Vintes </a>
 */
@ManagedBean
@SessionScoped
public class AccesoSearchAction extends BaseMantenimientoSearchAbstractAction {
		
    private static final long serialVersionUID = -6487884854356024063L;
	
	private ArrayList displayitems;
	private List collist;
	private Map<String, Boolean> accesosSelecionados = new HashMap<String, Boolean>();		
	
	public ArrayList getDisplayitems() {
		return displayitems;
	}

	public void setDisplayitems(ArrayList displayitems) {
		this.displayitems = displayitems;
	}

	public List getCollist() {
		return collist;
	}

	public void setCollist(List collist) {
		this.collist = collist;
	}

	public Map<String, Boolean> getAccesosSelecionados() {
		return accesosSelecionados;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "accesoForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		AccesoSearchForm searchForm = new AccesoSearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }

        AccesoSearchForm searchForm = (AccesoSearchForm) this.formBusqueda;
        Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(searchForm);
        // Aadimos el valor del codigo de lenguaje ya que Canal es una tabla
        // multilenguaje
        criteria.put("codigoLenguaje", usuario.getIdioma().getCodigo());

        
        // Se realiza la busqueda por Pais
        
        if (StringUtils.isNotBlank(searchForm.getCodigoPais())){
        	criteria.put("codigoPaisRol", searchForm.getCodigoPais());
        }
        
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
		// TODO Auto-generated method stub
		return true;
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		AccesoSearchForm searchForm = (AccesoSearchForm) this.formBusqueda;
		searchForm.setCodigoPais("");
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		super.find();
		this.salirGrabarPantallaPadre = true;
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		AccesoForm accesoForm = (AccesoForm) this.formMantenimiento;
		boolean isNew = accesoForm.isNewRecord();
		if(isNew){
			return "acceso.added";
		}else{
			return "acceso.updated";
		}	
	}

	@Override
	protected String getSalirForward() {
		return "accesoList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'setSaveAttributes' method"); 
        }        

        // Extraemos atributos y parametros a usar
        AccesoForm accesoForm = (AccesoForm) this.formMantenimiento;

        // Extreamos el usuario de la sesion
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
 
        // Creamos la instancia del servicio y le asignamos
        // el usuario que va a realizar las operaciones
        RolService service = (RolService) this.getBeanService("rolService");

    	Acceso acceso = new Acceso(); 
    	List accesosRol = new ArrayList();
    	    	
    	
//    	
    	List list_Roles = new ArrayList();     	
    	Iterator it = accesosSelecionados.keySet().iterator();
    	while (it.hasNext()) {
    	String key = (String) it.next();
        	
    	Boolean seleccionado = accesosSelecionados.get(key);
	    	if(!seleccionado.equals(false)){
	    		
	    		String[] arrCodigos = key.split("_");
	    		HashMap map = new HashMap();
	    		map.put("codigoMenu", arrCodigos[0]);
	    		map.put("activos", arrCodigos[1]);
	    		list_Roles.add(map);
	    		
	    	}
    	}    	
    	
    	if(accesosSelecionados!=null)
    	{
    		try
    		{    			
        		acceso.setCodigoPais(accesoForm.getCodigoPais());
                acceso.setCodigoRol(accesoForm.getCodigo());
                
                accesosRol.add(0, list_Roles);
                accesosRol.add(1, acceso);
                
                service.updateAccesosAsignados(accesosRol, usuario);
    		}
    		catch(Exception e)
    		{
        		this.addError("Error: ", this.obtieneMensajeErrorException(e));
        		return false;
    		}
    	}
		 
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Rol rolbusqueda = (Rol)this.beanRegistroSeleccionado;
		
		// Creamos algunos de los objetos y servicios a usar
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        AccesoForm accesoForm = new AccesoForm();

        if (this.accion.equals(this.ACCION_MODIFICAR)) 
        {
            // Obtenemos el id seleccionado
            String codigo = rolbusqueda.getCodigo();
            String codigoPais = rolbusqueda.getCodigoPais();
                      
            // Si el id ha sido enviado, buscamos la informacion
            // en caso contrario, no hacemos nada, se esta insertando
            // un nuevo registro a la aplicacin
            if (StringUtils.isNotBlank(codigo) && StringUtils.isNotBlank(codigoPais)) {
                if (log.isDebugEnabled()) {
                    log.debug("Id seleccionado de la lista: " + codigo + " " + codigoPais);
                }
                
                RolService service = (RolService) this.getBeanService("rolService");

                Rol rol = service.getRol(codigoPais,codigo,usuario.getIdioma().getCodigo());
                
                // Copiamos los atributos del bean al form
                BeanUtils.copyProperties(accesoForm, rol);
                // Obtenemos la relacion de opciones de menu
                
               List accesos = service.getAccesosAsignados(rol.getCodigo(), rol.getCodigoPais());

               Map valores = CommonUtilValue.armandoDisplaytag(accesos);
               
               displayitems = (ArrayList)valores.get("displayitems");
               collist = (List)valores.get("collist");
               
               for(int i=0; i<displayitems.size(); i++)
               {
            	   Rol []roles = (Rol[])displayitems.get(i);
            	   for(int j=0; j<roles.length; j++)
            	   {
            		   accesosSelecionados.put(roles[j].getCodigo(), (roles[j].getEstado() == Constants.SI)?Boolean.TRUE:Boolean.FALSE);            		   
            	   }
               }
               
               accesoForm.setNewRecord(false);
            }
        }
        
        return accesoForm;
	}


	@Override
	protected String getSaveForward() {
		return "accesoList";
	}

	/**
	 * @param accesosSelecionados the accesosSelecionados to set
	 */
	public void setAccesosSelecionados(Map<String, Boolean> accesosSelecionados) {
		this.accesosSelecionados = accesosSelecionados;
	}
	
	
}

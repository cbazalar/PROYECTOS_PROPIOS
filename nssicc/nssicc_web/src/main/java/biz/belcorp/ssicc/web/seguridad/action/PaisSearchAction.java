
package biz.belcorp.ssicc.web.seguridad.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.seguridad.form.PaisForm;
import biz.belcorp.ssicc.web.seguridad.form.PaisSearchForm;



@ManagedBean
@SessionScoped
public class PaisSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -7036060694922642343L;

	
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "paisForm";	
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		PaisSearchForm paisSearchForm = new PaisSearchForm();
		return paisSearchForm;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		 if (log.isDebugEnabled()) {
	            log.debug("Entering 'search' method");
	        }

	        PaisSearchForm searchForm = (PaisSearchForm) this.formBusqueda;
	        
	        // Obtenemos las propiedades del bean como un 'Map'
	        Map criteria = BeanUtils.describe(searchForm);
	        // Modificamos los valores que requieren el caracter '%'
	        if(StringUtils.isNotBlank(searchForm.getDescripcionPais())) {
	            criteria.put("descripcionPais", searchForm.getDescripcionPais() + "%");
	        }

	        if (log.isDebugEnabled()) {
	            log.debug(criteria.toString());
	        }

	        PaisService service = (PaisService) this.getBeanService("paisService");
	        List lista = service.getPaisesByCriteria(criteria);    		
	        return lista;
	}
	
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		//Eliminar registros seleccionado		
		if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        // Creamos las instancias de los objetos a usar
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		

        Pais paisSeleccionado = (Pais) this.beanRegistroSeleccionado;        		
                
        if (log.isDebugEnabled()) {
            log.debug("Id seleccionado de la lista: " + paisSeleccionado.getCodigo());
        }
    
        PaisService service = (PaisService) getBean("paisService");
        service.removePais(paisSeleccionado.getCodigo(), usuario);
        return true;
               
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {
		return "pais.deleted";
	}

	@Override
	protected void setViewAtributes() throws Exception {
		PaisSearchForm searchForm = (PaisSearchForm) this.formBusqueda;
		searchForm.setCodigoPais(null);
		this.mostrarBotonConsultar = false;		
		this.salirGrabarPantallaPadre = true; 
		super.find();
		
	}	

	@Override
	protected String getSalirForward() {
		return "paisList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
        
		PaisForm paisForm = (PaisForm) this.formMantenimiento;        
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();           		

        // Creamos la instancia del servicio y le asignamos
        // el usuario que va a realizar las operaciones
        PaisService service = (PaisService) getBean("paisService");
        Pais pais = new Pais(); 
        
    	try {   		
    		BeanUtils.copyProperties(pais, paisForm);
            if (this.accion.equals(this.ACCION_NUEVO)) {
                service.insertPais(pais, usuario);                
             }
             else {
                service.updatePais(pais, usuario);                
             }
        }
    	catch (InvalidIdentifierException iie){
    		String codigo = iie.getIdentifier().toString();
    		this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
    		return false;
    	}
    	catch (InvalidDescriptionException ide){
    		String descripcion = ide.getDescription();
    		this.addError("Error: ", this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
    		return false;
    	}
		return true;
	}	

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Pais paisbusqueda = (Pais)this.beanRegistroSeleccionado;
		
		//Seteo de valores por default de nuevos registros
		PaisForm paisForm = new PaisForm();		
		
		//Seteo de valores del registro seleccionado	
        if (this.accion.equals(this.ACCION_MODIFICAR) ) {    	            
            String id = paisbusqueda.getCodigo();           		
            // Si el id ha sido enviado, buscamos la informacion
            // en caso contrario, no hacemos nada, se esta insertando
            // un nuevo registro a la aplicación
            if (id != null) {
                if (log.isDebugEnabled()) {
                    log.debug("Id seleccionado de la lista: " + id);
                }
                PaisService service = (PaisService)this.getBeanService("paisService");                		
                Pais pais = service.getPais(id);
                BeanUtils.copyProperties(paisForm, pais);                
                paisForm.setNewRecord(false);
            }            
        }       
       return paisForm;      
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		PaisForm paisForm = (PaisForm) this.formMantenimiento;
		boolean isNew = paisForm.isNewRecord();
		if(isNew){
			return "pais.added";
		}else{
			return "pais.updated";
		}	
	}
	
	

}
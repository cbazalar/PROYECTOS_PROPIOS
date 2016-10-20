/*
 * Created on 21/11/2005 11:49:58 AM
 * biz.belcorp.ssicc.web.action.IdiomaSearchAction
 */
package biz.belcorp.ssicc.web.seguridad.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.seguridad.form.IdiomaForm;
import biz.belcorp.ssicc.web.seguridad.form.IdiomaSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="IdiomaSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@ManagedBean
@SessionScoped
public class IdiomaSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 201428608148746942L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "idiomaForm";		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		IdiomaSearchForm idiomaSearchForm = new IdiomaSearchForm();
		return idiomaSearchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'search' method");
	        }
	        IdiomaSearchForm searchForm = (IdiomaSearchForm) this.formBusqueda;
	        // Obtenemos las propiedades del bean como un 'Map'
	        Map criteria = BeanUtils.describe(searchForm);
	        // Modificamos los valores que requieren el caracter '%'
	        if (StringUtils.isNotBlank(searchForm.getDescripcionIdioma())) {
	            criteria.put("descripcionIdioma", searchForm.getDescripcionIdioma() + "%");
	        }
	        if (log.isDebugEnabled()) {
	            log.debug(criteria.toString());
	        }
	        IdiomaService service = (IdiomaService)this.getBeanService("idiomaService");
	        List lista = service.getIdiomasByCriteria(criteria);
	        
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
        
        Idioma idioma = (Idioma)this.beanRegistroSeleccionado;
        
        if (log.isDebugEnabled()) {
            log.debug("Id seleccionado de la lista: " + idioma.getCodigo());
        }
        // Todas las excepciones son capturadas por ActionExceptionHandler
        IdiomaService service = (IdiomaService) this.getBeanService("idiomaService");
        service.removeIdioma(idioma.getCodigo(), usuario);
        return true;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {

		return "idioma.deleted";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;	
		this.salirGrabarPantallaPadre = true;
		super.find();
	}

	@Override
	protected String getSalirForward() {		
		return "idiomaList";
	}
    
	@Override
	protected String devuelveMensajeKeySaveOK() {
		IdiomaForm idiomaForm = (IdiomaForm) this.formMantenimiento;
		boolean isNew = idiomaForm.isNewRecord();
		if(isNew){
			return "idioma.added";
		}else{
			return "idioma.updated";
		}	
	}
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }

        // Extraemos atributos y parmetros a usar
        IdiomaForm idiomaForm = (IdiomaForm) this.formMantenimiento;
        boolean isNew = idiomaForm.isNewRecord();

        // Extreamos el usuario de la sesin
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

        // Creamos la instancia del servicio y le asignamos
        // el usuario que va a realizar las operaciones
        IdiomaService service = (IdiomaService) this.getBeanService("idiomaService");

        Idioma idioma = new Idioma();
        BeanUtils.copyProperties(idioma, idiomaForm);

        try {          
        	if (this.accion.equals(this.ACCION_NUEVO)) {
                service.insertIdioma(idioma, usuario);
            }
            else {
                service.updateIdioma(idioma, usuario);
            }
        }
        catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();
            this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
            return false;
        }
        catch (InvalidDescriptionException ide) {
            String descripcion = ide.getDescription();
            this.addError("Error: ", this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
            return false;
        }
        
        return true;
	}

	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'setViewAtributes' method");            
        }
		Idioma idiomabusqueda = (Idioma)this.beanRegistroSeleccionado;
        
		//Seteo de valores por default de nuevos registros
		IdiomaForm idiomaForm = new IdiomaForm();				
		
		//Seteo de valores del registro seleccionado	
        if (this.accion.equals(this.ACCION_MODIFICAR) ) {        	
            String id = idiomabusqueda.getCodigo();            		
            // Si el id ha sido enviado, buscamos la informacion
            // en caso contrario, no hacemos nada, se esta insertando
            // un nuevo registro a la aplicación
            if (id != null) {
                if (log.isDebugEnabled()) {
                    log.debug("Id seleccionado de la lista: " + id);
                }
                IdiomaService service = (IdiomaService)this.getBeanService("idiomaService");                		
                Idioma idioma = service.getIdioma(id);
                BeanUtils.copyProperties(idiomaForm, idioma);                
                idiomaForm.setNewRecord(false);
            }           
        }       

        return idiomaForm;   
	}

}

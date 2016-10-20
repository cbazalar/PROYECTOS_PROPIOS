package biz.belcorp.ssicc.web.seguridad.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Opcion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.OpcionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.seguridad.form.OpcionForm;
import biz.belcorp.ssicc.web.seguridad.form.OpcionSearchForm;

/**
 * Action invocado desde la pantalla de mantenimiento del objeto Opcion.
 * <p>
 * <a href="OpcionSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:dhinostroza@belcorp.biz">David Hinostroza Vintes </a>
 */
@ManagedBean
@SessionScoped
public class OpcionSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 5191154663361647814L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "opcionForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		OpcionSearchForm searchForm = new OpcionSearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}

		OpcionSearchForm opcionForm = (OpcionSearchForm) this.formBusqueda;

		Opcion opcion = new Opcion();
		// Copiamos los atributos del bean al form
		BeanUtils.copyProperties(opcion, opcionForm);
		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(opcionForm.getDescripcion())) {
			opcion.setDescripcion(opcionForm.getDescripcion() + "%");
		}

		if (log.isDebugEnabled()) {
			log.debug(opcion.toString());
		}

		OpcionService service = (OpcionService) this.getBeanService("opcionService");
		List lista = service.getOpcionesByCriteria(opcion);

		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteFindBeforeAttributes()
	 */
	@Override
	public void setDeleteFindBeforeAttributes() throws Exception {

		OpcionSearchForm opcionFormBuqueda = (OpcionSearchForm) this.formBusqueda;
		opcionFormBuqueda.setCodigoOpcion("");
		opcionFormBuqueda.setDescripcion("");
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'setDeleteAttributes' method");
        }

        // Creamos las instancias de los objetos a usar
        Opcion opcionSeleccionada = (Opcion) this.beanRegistroSeleccionado;        		
                
        if (log.isDebugEnabled()) {
            log.debug("Id seleccionado de la lista: " + opcionSeleccionada.getCodigoOpcion());
        }

        // Todas las excepciones son capturadas por ActionExceptionHandler
        OpcionService service = (OpcionService) getBean("opcionService");
        service.deleteOpcion(opcionSeleccionada.getCodigoOpcion());
        return true;
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
		return "opcionList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSalirFindAfterAttributes()
	 */
	@Override
	public void setSalirFindAfterAttributes() throws Exception {

		OpcionSearchForm opcionFormBuqueda = (OpcionSearchForm) this.formBusqueda;
		opcionFormBuqueda.setCodigoOpcion("");
		opcionFormBuqueda.setDescripcion("");
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'setSaveAttributes' method");
        }

        // Extraemos atributos y parmetros a usar
        OpcionForm opcionForm = (OpcionForm) this.formMantenimiento;
        boolean isNew = opcionForm.isNewRecord();

        // Extreamos el usuario de la sesin
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

        // Creamos la instancia del servicio y le asignamos
        // el usuario que va a realizar las operaciones
        OpcionService service = (OpcionService) getBean("opcionService");

        Opcion opciones = new Opcion();
        BeanUtils.copyProperties(opciones, opcionForm);
        
        try
        {
            // agregamos los mensajes de exito
        	if (this.accion.equals(this.ACCION_NUEVO)) {
            	opciones.setEstadoOpcion(Constants.ESTADO_ACTIVO);
            	service.insertOpcion(opciones,usuario);
            }
            else {
                service.updateOpcion(opciones,usuario);
            }
        }
        catch(Exception e)
        {
    		this.addError("Error: ", this.obtieneMensajeErrorException(e));
    		return false;
        }
        
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveFindAttributes()
	 */
	@Override
	public void setSaveFindBeforeAttributes() throws Exception {
		
		OpcionForm opcionForm = (OpcionForm) this.formMantenimiento;
		OpcionSearchForm opcionFormBuqueda = (OpcionSearchForm) this.formBusqueda;
		
		opcionFormBuqueda.setDescripcion("");
		opcionFormBuqueda.setCodigoOpcion("");
		this.beanRegistroSeleccionado = null;
		
		String descripcion = opcionForm.getDescripcion();
		opcionFormBuqueda.setDescripcion(descripcion);
		
		if(StringUtils.equals(this.accion, this.ACCION_MODIFICAR)){
			String codigo = opcionForm.getCodigoOpcion();
			opcionFormBuqueda.setCodigoOpcion(codigo);
		}
		
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Opcion opcionbusqueda = (Opcion)this.beanRegistroSeleccionado;
		// Creamos algunos de los objetos y servicios a usar
        OpcionForm opcionForm = new OpcionForm();
        
        if (this.accion.equals(this.ACCION_MODIFICAR) ) 
        {
            // Obtenemos el id seleccionado
            String id = opcionbusqueda.getCodigoOpcion();
            
            // Si el id ha sido enviado, buscamos la informacion
            // en caso contrario, no hacemos nada, se esta insertando
            // un nuevo registro a la aplicacin
            if (id != null) {
                if (log.isDebugEnabled()) {
                    log.debug("Id seleccionado de la lista: " + id);
                }
                // Separamos los valores del codigo pais y codigo de la opcion enviados
                // como un solo valor de id
                Opcion parametros= new Opcion();
                OpcionService service = (OpcionService) this.getBeanService("opcionService");
                parametros.setCodigoOpcion(id);
                Opcion opcion =(Opcion)service.getOpcionesByCriteria(parametros).get(0);
                
                // Copiamos los atributos del bean al form
                BeanUtils.copyProperties(opcionForm, opcion);
                opcionForm.setNewRecord(false);
            }
            else
            {
            	opcionForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
            }
        }
        return opcionForm;
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		OpcionForm opcionForm = (OpcionForm) this.formMantenimiento;
		boolean isNew = opcionForm.isNewRecord();
		if(isNew){
			return "opcion.added";
		}else{
			return "opcion.updated";
		}	
	}
}

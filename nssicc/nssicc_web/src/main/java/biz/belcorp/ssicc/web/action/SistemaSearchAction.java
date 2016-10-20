package biz.belcorp.ssicc.web.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.dao.sisicc.model.SistemaPK;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.form.SistemaSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@ManagedBean
@SessionScoped
public class SistemaSearchAction extends BaseMantenimientoSearchAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7068423364988878200L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoBASSistemaForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		SistemaSearchForm searchForm = new SistemaSearchForm();
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

		SistemaSearchForm searchForm = (SistemaSearchForm) this.formBusqueda;

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);
		// La busqueda solo la realizaremos en los sistemas activos
		criteria.put("estado", Constants.ESTADO_ACTIVO);

		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(searchForm.getCodigoSistema())) {
			criteria.put("codigoSistema", searchForm.getCodigoSistema() + "%");
		}
		if (StringUtils.isNotBlank(searchForm.getDescripcionSistema())) {
			criteria.put("descripcionSistema", searchForm
					.getDescripcionSistema()
					+ "%");
		}

		if (log.isDebugEnabled()) {
			log.debug(criteria.toString());
		}

		SistemaService service = (SistemaService) this.getBeanService("sisicc.sistemaService");

		List lista = service.getSistemasByCriteria(criteria);
		
		return lista;
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
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		Sistema sistemaSeleccionado = (Sistema)this.beanRegistroSeleccionado;
		
		// Todas las excepciones son capturadas por ActionExceptionHandler
		SistemaService service = (SistemaService) this.getBeanService("sisicc.sistemaService");
		SistemaPK pk = new SistemaPK(sistemaSeleccionado.getCodigoPais(), sistemaSeleccionado.getCodigo());
		
		service.removeSistema(pk, usuario);

		if (log.isDebugEnabled()) {
			log.debug("ID sistema eliminado: " + pk);
		}
		return true;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		SistemaSearchForm searchForm = (SistemaSearchForm) this.formBusqueda;
		searchForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		this.mostrarMantenimientoEnPopup = false;
		this.mostrarBotonConsultar = false;
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoBASSistemaList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}

		// Extraemos atributos y parmetros a usar
		SistemaForm sistemaForm = (SistemaForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();

		// Extreamos el usuario de la sesin
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		SistemaService service = (SistemaService) this.getBeanService("sisicc.sistemaService");

		// Sistema sistema = (Sistema) convert(sistemaForm);
		Sistema sistema = new Sistema();
		BeanUtils.copyProperties(sistema, sistemaForm);
		
		try {
			if (this.accion.equals(this.ACCION_NUEVO)) {
				service.insertSistema(sistema, usuario);
			} else {
				service.updateSistema(sistema, usuario);
			}
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			throw new Exception(this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
			
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			throw new Exception(this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
		}
		
		// PRUEBAS FRAMEWORK INTERFACES
		/*BaseInterfazService pruebaService = (BaseInterfazService) this.getBeanService("sisicc.interfazXXXEnviarConcursoPremioService");
		pruebaService.executePruebaTransaccion();
		*/
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Sistema sistemabusqueda = (Sistema)this.beanRegistroSeleccionado;
		
		SistemaForm sistemaForm = new SistemaForm();
		sistemaForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		
        if (!this.accion.equals(this.ACCION_NUEVO) ) {    	            
            String codigo = sistemabusqueda.getCodigo();  		
            String codigoPais = sistemabusqueda.getCodigoPais();
            
            if(codigo != null && codigoPais != null)
            {
    			if (log.isDebugEnabled()) {
    				log.debug("Id seleccionado de la lista: " + codigo + " " + codigoPais);
    			}

    			SistemaService service = (SistemaService) this.getBeanService("sisicc.sistemaService");
    			SistemaPK pk = new SistemaPK(codigoPais, codigo);
    			
    			Sistema sistema = service.getSistema(pk);
    			BeanUtils.copyProperties(sistemaForm, sistema);
    			sistemaForm.setNewRecord(false);
            }
        }
        
        return sistemaForm;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		SistemaForm sistemaForm = (SistemaForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if(isNew){
			return "sistema.added";
		}else{
			return "sistema.updated";
		}	
	}
}

package biz.belcorp.ssicc.web.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.service.LookupService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.web.form.BusquedaUsuarioPOPUPSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class BusquedaUsuarioPOPUPSearchAction extends BasePopupAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3942365828204906756L;
	
	private List allPaises;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		BusquedaUsuarioPOPUPSearchForm searchForm = new BusquedaUsuarioPOPUPSearchForm();
		return searchForm;
	}


	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'search' method");
        }

        BusquedaUsuarioPOPUPSearchForm f = (BusquedaUsuarioPOPUPSearchForm) this.formBusqueda;

        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(f);
        // Modificamos los valores que requieren el caracter '%'
        if(StringUtils.isNotBlank(f.getNombresUsuario())) {
            criteria.put("nombresUsuario", f.getNombresUsuario() + "%");
        }
        if(StringUtils.isNotBlank(f.getApellidosUsuario())) {
            criteria.put("apellidosUsuario", f.getApellidosUsuario() + "%");
        }

        if (this.log.isDebugEnabled()) {
            this.log.debug(criteria.toString());
        }

        UsuarioService service = (UsuarioService) this.getBean("usuarioService");
        List lista = service.getUsuariosByCriteria(criteria);
               
		return lista;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'viewMaestro' method");
		}
		
		BusquedaUsuarioPOPUPSearchForm f = (BusquedaUsuarioPOPUPSearchForm) this.formBusqueda;
		
		f.setLogin("");
		f.setNombresUsuario("");
		f.setApellidosUsuario("");	
		//Map criteria = new HashMap();
        //UsuarioService service = (UsuarioService) this.getBean("usuarioService");
        /*
        request.getSession().setAttribute(Constants.USUARIO_LIST,
                service.getUsuariosByCriteria(criteria));
          */     		
		 LookupService service = (LookupService) getBean("lookupService");
		 this.allPaises = service.getAllPaises();
	}


	public List getAllPaises() {
		return allPaises;
	}


	public void setAllPaises(List allPaises) {
		this.allPaises = allPaises;
	}
}

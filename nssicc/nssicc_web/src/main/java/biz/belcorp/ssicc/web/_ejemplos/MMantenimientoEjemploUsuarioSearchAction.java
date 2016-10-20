package biz.belcorp.ssicc.web._ejemplos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.RolService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.web._ejemplos.form.MMantenimientoEjemploUsuarioForm;
import biz.belcorp.ssicc.web._ejemplos.form.MMantenimientoEjemploUsuarioSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
 
@ManagedBean
@SessionScoped
public class MMantenimientoEjemploUsuarioSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -6310566677302709800L;
	
	private Usuario[] listaUsuario;
	private List perfilUsuarioList;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MMantenimientoEjemploUsuarioSearchForm form = new MMantenimientoEjemploUsuarioSearchForm();
		return form;
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
        UsuarioService usuarioService = (UsuarioService)this.getBeanService("usuarioService");
		Map criteria = new HashMap();
		String countryCode = ((MMantenimientoEjemploUsuarioSearchForm) this.formBusqueda).getCodigoPais();
		
		criteria.put("codigoPaisUsuario", countryCode);
		log.debug("countryCode: " + countryCode);		
		
		return usuarioService.getUsuariosByCriteria(criteria);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setInitAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Ingresando setViewAtributes");
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Ingresando setDeleteAttributes");
		log.debug("setDeleteAttributes Login=" + ((Usuario)this.beanRegistroSeleccionado).getLogin());
		log.debug("Fin setDeleteAttributes");
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoEjemploUsuario";
	}
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoEjemploUsuarioList";
	}
	
	

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Usuario usuariobusqueda = (Usuario)this.beanRegistroSeleccionado;
		// Creamos algunos de los objetos y servicios a usar
        RolService rolService = (RolService) this.getBeanService("rolService");
        MMantenimientoEjemploUsuarioForm usuarioForm = new MMantenimientoEjemploUsuarioForm();
        List perfiles = new ArrayList();


        String id = usuariobusqueda.getCodigo();
        
        // Si el id ha sido enviado, buscamos la informacion
        // en caso contrario, no hacemos nada, se esta insertando
        // un nuevo registro a la aplicación
        if (!this.accion.equals(this.ACCION_NUEVO) ) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Id seleccionado de la lista: " + id);
            }

            UsuarioService service = (UsuarioService) this.getBean("usuarioService");
            Usuario obj = service.getUsuario(id);
            BeanUtils.copyProperties(usuarioForm, obj);

            // Reseteamos la contraseña del usuario
            usuarioForm.setClave(null);

            // Actualizamos la lista con los perfiles
            perfiles = obj.getPerfiles();

            usuarioForm.setNewRecord(false);
            
            usuarioForm.setCodigoPaisRol(usuarioForm.getCodigoPais());
        }    
        else {
        	
        	usuarioForm.setCodigoPaisRol(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        }
        		
        
        // Colocamos la lista de perfiles en la sesion, ya sea la lista vacia o
        // la lista con perfiles en caso se este editanto un usuario
        this.perfilUsuarioList = perfiles;

        return usuarioForm;
	}



	/* GET/ SET ATRIBUTOS */
	/**
	 * @return the listaUsuario
	 */
	public Usuario[] getListaUsuario() {
		return listaUsuario;
	}


	/**
	 * @param listaUsuario the listaUsuario to set
	 */
	public void setListaUsuario(Usuario[] listaUsuario) {
		this.listaUsuario = listaUsuario;
	}



	/**
	 * @return the perfilUsuarioList
	 */
	public List getPerfilUsuarioList() {
		return perfilUsuarioList;
	}



	/**
	 * @param perfilUsuarioList the perfilUsuarioList to set
	 */
	public void setPerfilUsuarioList(List perfilUsuarioList) {
		this.perfilUsuarioList = perfilUsuarioList;
	}

	
	
	
}

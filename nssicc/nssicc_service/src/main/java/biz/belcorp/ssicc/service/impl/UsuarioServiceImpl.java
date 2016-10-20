/*
 * Created on 22/03/2005 06:03:45 PM
 * biz.belcorp.ssicc.service.impl.UsuarioServiceImpl
 */
package biz.belcorp.ssicc.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.PerfilDAO;
import biz.belcorp.ssicc.dao.UsuarioDAO;
import biz.belcorp.ssicc.dao.model.Perfil;
import biz.belcorp.ssicc.dao.model.PerfilPK;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.model.UsuarioBloqueo;
import biz.belcorp.ssicc.dao.model.UsuarioOpcionHiperConsulta;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.util.StringUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UsuarioServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("usuarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class UsuarioServiceImpl extends BaseService implements UsuarioService {

	@Resource(name="perfilDAO")
    private PerfilDAO perfilDAO;

	@Resource(name="usuarioDAO")
    private UsuarioDAO usuarioDAO;
	
	private String rutaPath;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#getBloqueosByUsuario(java.lang.String)
	 */
	public List getBloqueosByUsuario(String codigoUsuario) {
		return this.usuarioDAO.getBloqueosByUsuario(codigoUsuario);
	}

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.UsuarioService#getUsuario(java.lang.String)
     */
    public Usuario getUsuario(String codigo) {
        // Obtenemos el objeto usuario de la base de datos
        Usuario usuario = this.usuarioDAO.getUsuario(codigo);
        ;

        // Obtenemos los perfiles del usuario
        List perfiles = this.perfilDAO.getPerfilesByUsuario(codigo, null);
        usuario.setPerfiles(perfiles);

        return usuario;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.UsuarioService#getUsuarioByUsername(java.lang.String)
     */
    public Usuario getUsuarioByUsername(String username) {
        return this.usuarioDAO.getUsuarioByUsername(username);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.UsuarioService#getUsuarios(biz.belcorp.ssicc.model.Usuario)
     */
    public List getUsuarios(Usuario usuario) {
        return this.usuarioDAO.getUsuarios(usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.UsuarioService#getUsuariosByCriteria(java.util.Map)
     */
    public List getUsuariosByCriteria(Map criteria) {
        return this.usuarioDAO.getUsuariosByCriteria(criteria);
    }

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#insertBloqueosUsuario(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertBloqueosUsuario(List bloqueos, Usuario usuario) {
		
		if(bloqueos != null && bloqueos.size() > 0)
		{
			for(int i=0; i<bloqueos.size(); i++)
			{
				UsuarioBloqueo ub = (UsuarioBloqueo)bloqueos.get(i);
				ub.setEstado(Constants.ESTADO_ACTIVO);
				
				
				//Buscamos al bloqueo, si esiste ya no lo insertamos, solo actualizamos
				Map criteria = new HashMap();
				criteria.put("codigoPais", ub.getCodigoPais());
				criteria.put("codigoUsuarioBloqueo", ub.getCodigoUsuarioBloqueo());
				criteria.put("codigoTipo", ub.getCodigoTipoBloqueo());
				criteria.put("codigoAccion", ub.getCodigoAccion());
				
				UsuarioBloqueo ubExistente = this.usuarioDAO.getBloqueoUsuarioByCriteria(criteria);
				
				if(ubExistente == null)
				{
					//Insertamos, si existe, en caso contrario no se hace nada
					this.usuarioDAO.insertBloqueoUsuario(ub, usuario);
				}
			}
		}
	}

	

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.UsuarioService#insertUsuario(biz.belcorp.ssicc.model.Usuario,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertUsuario(Usuario obj, Usuario usuario) {
        // Validamos que no exista un usuario con el mismo username
        try {
            this.usuarioDAO.getUsuarioByUsername(obj.getLogin());
            throw new InvalidIdentifierException(Usuario.class, obj.getLogin());
        }
        catch (ObjectRetrievalFailureException ignore) {
        }
        // Encriptamos el valor de la clave
        obj.setClave(StringUtil.encodePassword(obj.getClave(), Constants.ENC_ALGORITHM));
        this.usuarioDAO.insertUsuario(obj, usuario);

        // Insertamos los perfiles seleccionados
        List perfiles = obj.getPerfiles();
        if (perfiles != null) {
            Iterator i = perfiles.iterator();
            while (i.hasNext()) {
                Perfil perfil = (Perfil) i.next();
                // Seteamos los valores por defecto
                perfil.setCodigoUsuario(obj.getCodigo());
                this.perfilDAO.insertPerfil(perfil, usuario);
            }
        }
    }

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#removeBloqueosUsuario(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeBloqueosUsuario(List bloqueos, Usuario usuario) {
		if(bloqueos != null && bloqueos.size() > 0)
		{
			for(int i=0; i<bloqueos.size(); i++)
			{
				UsuarioBloqueo ub = (UsuarioBloqueo)bloqueos.get(i);
				ub.setEstado(Constants.ESTADO_INACTIVO);
				this.usuarioDAO.updateBloqueoUsuario(ub, usuario);
			}
		}
	}

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.UsuarioService#removeUsuario(java.lang.String,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void removeUsuario(String codigo, Usuario usuario) {
        // Actualizamos el estado del Usuario
        try {
            Usuario obj = this.usuarioDAO.getUsuario(codigo);
            obj.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos el usuario
            this.usuarioDAO.updateUsuario(obj, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            this.log.warn(orfe.getMessage());
        }
    }

    
	/*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.UsuarioService#updateUsuario(biz.belcorp.ssicc.model.Usuario,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateUsuario(Usuario obj, Usuario usuario) {
        // Encriptamos el valor de la clave
    	if(obj.getClave() != null &&obj.getClave() != "" ){
    		obj.setClave(StringUtil.encodePassword(obj.getClave(), Constants.ENC_ALGORITHM));
    	}
        this.usuarioDAO.updateUsuario(obj, usuario);

        // Insertamos o acualizamos los roles seleccionados
        List perfiles = obj.getPerfiles();
        if (perfiles != null) {
            Iterator i = perfiles.iterator();
            while (i.hasNext()) {
                Perfil perfil = (Perfil) i.next();
                // Seteamos los valores por defecto
                perfil.setCodigoUsuario(obj.getCodigo());

                // Buscamos el perfil por la llave primaria
                try {
                    Perfil p = this.perfilDAO.getPerfil(new PerfilPK(perfil.getCodigoUsuario(), perfil.getCodigoPais(),
                            perfil.getCodigoRol()));
                    p.setEstado(perfil.getEstado());
                    this.perfilDAO.updatePerfil(p, usuario);
                }
                catch (ObjectRetrievalFailureException orfe) {
                    this.perfilDAO.insertPerfil(perfil, usuario);
                }
            }
        }
    }
    
     
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.UsuarioService#updateIntentosFallidosClaveUsuario(biz.belcorp.ssicc.model.Usuario, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateIntentosFallidosClaveUsuario(Usuario obj, Usuario usuario) {
        this.usuarioDAO.updateIntentosFallidosClaveUsuario(obj, usuario);
    }
    
    
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.UsuarioService#updateFechaModificacionClaveUsuario(biz.belcorp.ssicc.model.Usuario, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateFechaModificacionClaveUsuario(Usuario obj, Usuario usuario) {
        this.usuarioDAO.updateFechaModificacionClaveUsuario(obj, usuario);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#getPoliticaByCodigo(java.util.Map)
	 */
	public String getPoliticaByCodigo(Map criteria) {
		 return this.usuarioDAO.getPoliticaByCodigo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#verificarExisteLogin(java.util.Map)
	 */
	public Integer verificarExisteLogin(Map criteria) {
		return this.usuarioDAO.verificarExisteLogin(criteria);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.UsuarioService#getOpcionesConsultaHiperConsulta(java.util.Map)
     */
    public List getOpcionesConsultaHiperConsulta(Map criteria) {
    	return this.usuarioDAO.getOpcionesConsultaHiperConsulta(criteria);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#getOpcionesConsultaHiperConsultaByUsuario(java.lang.String)
	 */
	public List getOpcionesConsultaHiperConsultaByUsuario(final String codigoUsuario) {
		return this.usuarioDAO.getOpcionesConsultaHiperConsultaByUsuario(codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#removeOpcionesConsultaHiperConsultaUsuario(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeOpcionesConsultaHiperConsultaUsuario(Map criteria) {
		this.usuarioDAO.removeOpcionesConsultaHiperConsultaUsuario(criteria);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.UsuarioService#insertOpcionesConsultaHiperConsultaUsuario(java.util.List)
     */
    public void insertOpcionesConsultaHiperConsultaUsuario(List bloqueos) {
		
		if(bloqueos != null && bloqueos.size() > 0)
		{
			for(int i=0; i<bloqueos.size(); i++)
			{
				UsuarioOpcionHiperConsulta ub = (UsuarioOpcionHiperConsulta)bloqueos.get(i);
				
				//Buscamos al bloqueo, si esiste ya no lo insertamos, solo actualizamos
				Map criteria = new HashMap();
				criteria.put("codigoPais", ub.getCodigoPais());
				criteria.put("codigoUsuarioBloqueo", ub.getCodigoUsuarioBloqueo());
				criteria.put("codigoOpcionConsulta", ub.getCodigoOpcionConsulta());
				
				
				//Insertamos, si existe, en caso contrario no se hace nada
				this.usuarioDAO.insertOpcionesConsultaHiperConsultaUsuario(criteria);
				
			}
		}
	}
    
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#bloqueoManualUsuario(java.util.Map)
	 */
	public void bloqueoManualUsuario(Map criteria) {
		this.usuarioDAO.bloqueoManualUsuario(criteria);
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#desbloqueoManualUsuario(java.util.Map)
	 */
	public void desbloqueoManualUsuario(Map criteria) {
		this.usuarioDAO.desbloqueoManualUsuario(criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#eliminarFisicoManualUsuario(java.util.Map)
	 */
	public void eliminarFisicoManualUsuario(Map criteria) {
		this.usuarioDAO.eliminarFisicoManualUsuario(criteria);
	}

    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.UsuarioService#getKeyMessageDAO(java.lang.String)
     */
    public String getKeyMessageDAO(String id) {
    	return (String)this.perfilDAO.getKeyMessage(id);
    }

    
    /* INI NSSICC */
	/**
	 * @return the rutaPath
	 */
	public String getRutaPath() {
		return rutaPath;
	}

	public void setRutaPath(String rutaPath) {
		this.rutaPath = rutaPath;
	}
    
    
    
    /* FIN NSSICC */
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.UsuarioService#getVerificarUsuarioEliminadoPoliticaSeguridad(java.util.Map)
	 */
	public Integer getVerificarUsuarioEliminadoPoliticaSeguridad(Map criteria) {
		return (Integer)this.usuarioDAO.getVerificarUsuarioEliminadoPoliticaSeguridad(criteria);
	}

}

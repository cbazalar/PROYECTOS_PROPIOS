/*
 * Created on 21/03/2005 03:03:14 PM
 *
 * biz.belcorp.ssicc.dao.ibatis.UsuarioDAOiBatis
 */
package biz.belcorp.ssicc.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.UsuarioDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.model.UsuarioBloqueo;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UsuarioDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("usuarioDAO")
public class UsuarioDAOiBatis extends BaseDAOiBatis implements UsuarioDAO {

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#getBloqueosByUsuario(java.lang.String)
	 */
	public List getBloqueosByUsuario(String codigoUsuario) {
        List bloqueos = this.getSqlMapClientTemplate().queryForList(
                "UsuarioSQL.getBloqueosByUsuario", codigoUsuario);
        return bloqueos;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#getBloqueoUsuarioByCriteria(java.util.Map)
	 */
	public UsuarioBloqueo getBloqueoUsuarioByCriteria(Map criteria) {
		UsuarioBloqueo bloqueo = (UsuarioBloqueo) this.getSqlMapClientTemplate().queryForObject(
                "UsuarioSQL.getBloqueoUsuarioByCriteria", criteria);
		
        return bloqueo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#getCorrelativoUsuarioBloqueo()
	 */
	public long getCorrelativoUsuarioBloqueo() {
		Long c = (Long)this.getSqlMapClientTemplate().queryForObject("UsuarioSQL.getCorrelativoUsuarioBloqueo", null);
		
		return c.longValue();
	}

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#getNextPK(java.util.Map)
     */
    public String getNextPK(Map params) {
        return (String) this.getSqlMapClientTemplate().queryForObject(
                "UsuarioSQL.getNextPK", params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#getUsuario(java.lang.String)
     */
    public Usuario getUsuario(String codigo) {
        Usuario usuario = (Usuario) this.getSqlMapClientTemplate().queryForObject(
                "UsuarioSQL.getUsuario", codigo);
        if (usuario == null) {
            throw new ObjectRetrievalFailureException(Usuario.class, codigo);
        }
        return usuario;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#getUsuarioByUsername(java.lang.String)
     */
    public Usuario getUsuarioByUsername(String username) {
        Usuario usuario = (Usuario) this.getSqlMapClientTemplate().queryForObject(
                "UsuarioSQL.getUsuarioByUsername", username);
        if (usuario == null) {
            throw new ObjectRetrievalFailureException(Usuario.class, username);
        }
        return usuario;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#getUsuarios(biz.belcorp.ssicc.model.Usuario)
     */
    public List getUsuarios(Usuario usuario) {
        List usuarios = this.getSqlMapClientTemplate().queryForList(
                "UsuarioSQL.getUsuarios", usuario);
        return usuarios;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#getUsuariosByCriteria(java.util.Map)
     */
    public List getUsuariosByCriteria(Map criteria) {
        List usuarios = this.getSqlMapClientTemplate().queryForList(
                "UsuarioSQL.getUsuariosByCriteria", criteria);
        return usuarios;
    }

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#insertBloqueoUsuario(biz.belcorp.ssicc.model.UsuarioBloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertBloqueoUsuario(UsuarioBloqueo bloqueo, Usuario usuario) {
		bloqueo.setCorrelativo(this.getCorrelativoUsuarioBloqueo());
		this.getSqlMapClientTemplate().update("UsuarioSQL.insertBloqueoUsuario", bloqueo);
	}

	/*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#insertUsuario(biz.belcorp.ssicc.model.Usuario,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertUsuario(Usuario obj, Usuario usuario) {
        obj.setCodigo(this.getNextPK(null));
        this.getSqlMapClientTemplate().update("UsuarioSQL.insertUsuario", obj);
        
        usuario.setCodigo(obj.getCodigo());
        this.getSqlMapClientTemplate().update("UsuarioSQL.updateUsuarioLoginUsuDigi", usuario);
    }

	/*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#removeUsuario(java.lang.String)
     */
    public void removeUsuario(String codigo) {
        this.getSqlMapClientTemplate().update("UsuarioSQL.removeUsuario", codigo);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#updateBloqueoUsuario(biz.belcorp.ssicc.model.UsuarioBloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateBloqueoUsuario(UsuarioBloqueo bloqueo, Usuario usuario) {
		this.getSqlMapClientTemplate().update("UsuarioSQL.updateBloqueoUsuario", bloqueo);
	}

	/*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#updateUsuario(biz.belcorp.ssicc.model.Usuario,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateUsuario(Usuario obj, Usuario usuario) {
    	
        this.getSqlMapClientTemplate().update("UsuarioSQL.updateUsuario", obj);
        usuario.setCodigo(obj.getCodigo());
        this.getSqlMapClientTemplate().update("UsuarioSQL.updateUsuarioLoginUsuModi", usuario);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#updateIntentosFallidosClaveUsuario(biz.belcorp.ssicc.model.Usuario, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateIntentosFallidosClaveUsuario(Usuario obj, Usuario usuario) {
        this.getSqlMapClientTemplate().update("UsuarioSQL.updateIntentosFallidosClaveUsuario", obj);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#updateFechaModificacionClaveUsuario(biz.belcorp.ssicc.model.Usuario, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateFechaModificacionClaveUsuario(Usuario obj, Usuario usuario) {
        this.getSqlMapClientTemplate().update("UsuarioSQL.updateFechaModificacionClaveUsuario", obj);
    }

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#getPoliticaByCodigo(java.util.Map)
	 */
	public String getPoliticaByCodigo(Map criteria) {
		return (String) this.getSqlMapClientTemplate().queryForObject(
                "UsuarioSQL.getPoliticaByCodigo", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#verificarExisteLogin(java.util.Map)
	 */
	public Integer verificarExisteLogin(Map criteria) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("UsuarioSQL.verificarExisteLogin", criteria);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.UsuarioDAO#getOpcionesConsultaHiperConsulta(java.util.Map)
     */
    public List getOpcionesConsultaHiperConsulta(Map criteria) {
    	return this.getSqlMapClientTemplate().queryForList("UsuarioSQL.getOpcionesConsultaHiperConsulta", criteria);
    }
    
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#getOpcionesConsultaHiperConsultaByUsuario(java.lang.String)
	 */
	public List getOpcionesConsultaHiperConsultaByUsuario(final String codigoUsuario) {
		return this.getSqlMapClientTemplate().queryForList("UsuarioSQL.getOpcionesConsultaHiperConsultaByUsuario", codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#removeOpcionesConsultaHiperConsultaUsuario(java.util.Map)
	 */
	public void removeOpcionesConsultaHiperConsultaUsuario(Map criteria) {
		this.getSqlMapClientTemplate().delete("UsuarioSQL.removeOpcionesConsultaHiperConsultaUsuario", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#insertOpcionesConsultaHiperConsultaUsuario(java.util.Map)
	 */
	public void insertOpcionesConsultaHiperConsultaUsuario(Map criteria) {
		this.getSqlMapClientTemplate().insert("UsuarioSQL.insertOpcionesConsultaHiperConsultaUsuario", criteria);
	}
	
	
    /**
     * Inserta en tabla Temporal SEG_GTT_USUAR
     * @param criteria
     */
    private void insertarListaUsuarioGTT(Map criteria) {
    	List<Usuario> listaUsuario = (List<Usuario>) criteria.get("listaUsuarios");
    	for(int i=0 ; i < listaUsuario.size(); i++) {
    		Usuario usuario = (Usuario) listaUsuario.get(i);
    		Map params = new HashMap();
    		params.put("codigoUsuario", usuario.getCodigo());
    		params.put("codigoPais", usuario.getCodigoPais());
    		this.getSqlMapClientTemplate().insert("UsuarioSQL.insertarListaUsuarioGTT", params);
    	}
    }
    
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#bloqueoManualUsuario(java.util.Map)
	 */
	public void bloqueoManualUsuario(Map criteria) {
		this.insertarListaUsuarioGTT(criteria);
		this.getSqlMapClientTemplate().update("UsuarioSQL.executeBloqueoManualUsuario", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#desbloqueoManualUsuario(java.util.Map)
	 */
	public void desbloqueoManualUsuario(Map criteria) {
		this.insertarListaUsuarioGTT(criteria);
		this.getSqlMapClientTemplate().update("UsuarioSQL.executeDesbloqueoManualUsuario", criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#eliminarFisicoManualUsuario(java.util.Map)
	 */
	public void eliminarFisicoManualUsuario(Map criteria) {
		this.insertarListaUsuarioGTT(criteria);
		this.getSqlMapClientTemplate().update("UsuarioSQL.executeEliminarFisicoManualUsuario", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.UsuarioDAO#getVerificarUsuarioEliminadoPoliticaSeguridad(java.util.Map)
	 */
	public Integer getVerificarUsuarioEliminadoPoliticaSeguridad(Map criteria) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("UsuarioSQL.getVerificarUsuarioEliminadoPoliticaSeguridad", criteria);
	}
	
}
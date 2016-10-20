/*
 * Created on 16/11/2005 03:39:13 PM
 *
 * biz.belcorp.ssicc.dao.ibatis.PerfilDAOiBatis
 */
package biz.belcorp.ssicc.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.PerfilDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Perfil;
import biz.belcorp.ssicc.dao.model.PerfilPK;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PerfilDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a> 
 */
@Repository("perfilDAO")
public class PerfilDAOiBatis extends BaseDAOiBatis implements PerfilDAO {

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PerfilDAO#getPerfil(biz.belcorp.ssicc.model.PerfilPK)
     */
    public Perfil getPerfil(PerfilPK primaryKey) {
        Perfil perfil = (Perfil) getSqlMapClientTemplate().queryForObject("PerfilSQL.getPerfil", primaryKey);
        if (perfil == null) {
            throw new ObjectRetrievalFailureException(Perfil.class, primaryKey);
        }
        return perfil;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PerfilDAO#insertPerfil(biz.belcorp.ssicc.model.Perfil, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertPerfil(Perfil perfil, Usuario usuario) {
        getSqlMapClientTemplate().update("PerfilSQL.insertPerfil", perfil);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PerfilDAO#updatePerfil(biz.belcorp.ssicc.model.Perfil, biz.belcorp.ssicc.model.Usuario)
     */
    public void updatePerfil(Perfil perfil, Usuario usuario) {
        getSqlMapClientTemplate().update("PerfilSQL.updatePerfil", perfil);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PerfilDAO#removePerfil(biz.belcorp.ssicc.model.PerfilPK)
     */
    public void removePerfil(PerfilPK primaryKey) {
        getSqlMapClientTemplate().update("PerfilSQL.removePerfil", primaryKey);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PerfilDAO#getPerfilesByUsuario(java.lang.String, java.lang.String)
     */
    public List getPerfilesByUsuario(String codigoUsuario, String codigoIdioma) {
        // Ya que necesitamos enviar el valor del idioma, ademas del pk
        // es que usamos un map con dichos objetos
        Map map = new HashMap();
        map.put("codigoUsuario", codigoUsuario);
        map.put("codigoIdioma", codigoIdioma);

        List perfiles = getSqlMapClientTemplate().queryForList("PerfilSQL.getPerfilesByUsuario", map);

        return perfiles;
    }

}

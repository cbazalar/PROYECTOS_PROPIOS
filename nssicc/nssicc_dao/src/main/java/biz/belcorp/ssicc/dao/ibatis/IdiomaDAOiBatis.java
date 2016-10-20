/*
 * Created on 10/11/2005 04:10:12 PM
 * biz.belcorp.ssicc.dao.ibatis.IdiomaDAOiBatis
 */
package biz.belcorp.ssicc.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.IdiomaDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="IdiomaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

@Repository("idiomaDAO")
public class IdiomaDAOiBatis extends BaseDAOiBatis implements IdiomaDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.IdiomaDAO#getIdiomas(biz.belcorp.ssicc.model.Idioma)
     */
    public List getIdiomas(Idioma idioma) {
        List idiomas = getSqlMapClientTemplate().queryForList("IdiomaSQL.getIdiomas", idioma);
        return idiomas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.IdiomaDAO#getIdiomasByCriteria(java.util.Map)
     */
    public List getIdiomasByCriteria(Map criteria) {
        List idiomas = getSqlMapClientTemplate().queryForList("IdiomaSQL.getIdiomasByCriteria", criteria);
        return idiomas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.IdiomaDAO#getIdioma(java.lang.String)
     */
    public Idioma getIdioma(String codigo) {
        Idioma idioma = (Idioma) getSqlMapClientTemplate().queryForObject("IdiomaSQL.getIdioma", codigo);
        if (idioma == null) {
            throw new ObjectRetrievalFailureException(Idioma.class, codigo);
        }
        return idioma;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.IdiomaDAO#getIdiomaByCodigoISO(java.lang.String)
     */
    public Idioma getIdiomaByCodigoISO(String codigo) {
        Idioma idioma = (Idioma) getSqlMapClientTemplate().queryForObject("IdiomaSQL.getIdiomaByCodigoISO", codigo);
        if (idioma == null) {
            throw new ObjectRetrievalFailureException(Idioma.class, codigo);
        }
        return idioma;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.IdiomaDAO#insertIdioma(biz.belcorp.ssicc.model.Idioma,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertIdioma(Idioma idioma, Usuario usuario) {
        String codigo = getNextPK(null);
        idioma.setCodigo(codigo);
        getSqlMapClientTemplate().update("IdiomaSQL.insertIdioma", idioma);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.IdiomaDAO#updateIdioma(biz.belcorp.ssicc.model.Idioma,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateIdioma(Idioma idioma, Usuario usuario) {
        getSqlMapClientTemplate().update("IdiomaSQL.updateIdioma", idioma);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.IdiomaDAO#removeIdioma(java.lang.String)
     */
    public void removeIdioma(String codigo) {
        getSqlMapClientTemplate().update("IdiomaSQL.removeIdioma", codigo);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.IdiomaDAO#getNextPK(java.util.Map)
     */
    public String getNextPK(Map params) {
        return (String) getSqlMapClientTemplate().queryForObject("IdiomaSQL.getNextPK", params);
    }

}

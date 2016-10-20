package biz.belcorp.ssicc.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.PasesSsiccDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PasesSsiccDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Repository("pasesSsiccDAO")
public class PasesSsiccDAOiBatis extends BaseDAOiBatis implements PasesSsiccDAO {

  
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PasesSsiccDAO#getListaPaisMarca()
     */
    public List getListaPaisMarca() {
        List lista = getSqlMapClientTemplate().queryForList("PasesSsiccSQL.getListaPaisMarca");
        return lista;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PasesSsiccDAO#getResultadoLogErrores(java.util.Map)
     */
    public List getResultadoLogErrores(Map criteria) {
        List lista = getSqlMapClientTemplate().queryForList("PasesSsiccSQL.getResultadoLogErrores", criteria);
        return lista;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PasesSsiccDAO#getResultadoEjecucion(java.util.Map)
     */
    public List getResultadoEjecucion(Map criteria) {
        List lista = getSqlMapClientTemplate().queryForList("PasesSsiccSQL.getResultadoEjecucion", criteria);
        return lista;
    }
}
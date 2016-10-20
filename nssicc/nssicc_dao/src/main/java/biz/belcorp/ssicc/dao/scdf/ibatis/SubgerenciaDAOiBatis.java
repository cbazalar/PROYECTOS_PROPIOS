package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.SubgerenciaDAO;
import biz.belcorp.ssicc.dao.scdf.model.Subgerencia;

@Repository("scdf.subgerenciaDAO")
public class SubgerenciaDAOiBatis extends BaseDAOiBatis implements
        SubgerenciaDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.SubgerenciaDAO#getSubgerencia(biz.belcorp.ssicc.scdf.model.Subgerencia)
     */
    public Subgerencia getSubgerencia(Subgerencia subgerencia) {
        Subgerencia resultado = (Subgerencia) getSqlMapClientTemplate()
                .queryForObject("scdf.SubgerenciaSQL.getSubgerenciaMapByPais",
                        subgerencia);
        return resultado;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.SubgerenciaDAO#getSubgerenciaMapByPais(java.lang.String)
     */
    public List getSubgerenciaMapByPais(String codigoPais) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.SubgerenciaSQL.getSubgerenciaMapByPais", codigoPais);
        return resultados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.SubgerenciaDAO#insertSubgerencia(biz.belcorp.ssicc.scdf.model.Subgerencia,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertSubgerencia(Subgerencia subgerencia, Usuario usuario) {
        getSqlMapClientTemplate().insert(
                "scdf.SubgerenciaSQL.insertSubgerencia", subgerencia);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.SubgerenciaDAO#updateSubgerencia(biz.belcorp.ssicc.scdf.model.Subgerencia,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateSubgerencia(Subgerencia subgerencia, Usuario usuario) {
        getSqlMapClientTemplate().update(
                "scdf.SubgerenciaSQL.updateSubgerencia", subgerencia);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.SubgerenciaDAO#removeSubgerencia(biz.belcorp.ssicc.scdf.model.Subgerencia)
     */
    public void removeSubgerencia(Subgerencia subgerencia) {
        getSqlMapClientTemplate().delete(
                "scdf.SubgerenciaSQL.removeSubgerencia", subgerencia);
    }

}

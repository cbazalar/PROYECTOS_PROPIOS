package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ZonaDAO;
import biz.belcorp.ssicc.dao.scdf.model.Zona;

@Repository("scdf.zonaDAO")
public class ZonaDAOiBatis extends BaseDAOiBatis implements ZonaDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ZonaDAO#getZona(biz.belcorp.ssicc.scdf.model.Zona)
     */
    public Zona getZona(Zona zona) {
        Zona resultado = (Zona) getSqlMapClientTemplate().queryForObject(
                "scdf.ZonaSQL.getZona", zona);
        return resultado;

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ZonaDAO#getZonaMapByPais(java.lang.String)
     */
    public List getZonaMapByPais(String codigoPais) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.ZonaSQL.getZonaMapByPais", codigoPais);
        return resultados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ZonaDAO#insertZona(biz.belcorp.ssicc.scdf.model.Zona,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertZona(Zona zona, Usuario usuario) {
        getSqlMapClientTemplate().insert("scdf.ZonaSQL.insertZona", zona);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ZonaDAO#updateZona(biz.belcorp.ssicc.scdf.model.Zona,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateZona(Zona zona, Usuario usuario) {
        getSqlMapClientTemplate().update("scdf.ZonaSQL.updateZona", zona);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ZonaDAO#removeZona(biz.belcorp.ssicc.scdf.model.Zona)
     */
    public void removeZona(Zona zona) {
        getSqlMapClientTemplate().delete("scdf.ZonaSQL.removeZona", zona);
    }

}

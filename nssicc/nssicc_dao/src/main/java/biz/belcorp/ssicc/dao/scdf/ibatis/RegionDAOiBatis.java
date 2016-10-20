package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.RegionDAO;
import biz.belcorp.ssicc.dao.scdf.model.Region;

@Repository("scdf.regionDAO")
public class RegionDAOiBatis extends BaseDAOiBatis implements RegionDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.RegionDAO#getRegion(biz.belcorp.ssicc.scdf.model.Region)
     */
    public Region getRegion(Region region) {
        Region resultado = (Region) getSqlMapClientTemplate().queryForObject(
                "scdf.RegionSQL.getRegionMapByPais", region);
        return resultado;

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.RegionDAO#getRegionMapByPais(java.lang.String)
     */
    public List getRegionMapByPais(String codigoPais) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.RegionSQL.getRegionMapByPais", codigoPais);
        return resultados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.RegionDAO#insertRegion(biz.belcorp.ssicc.scdf.model.Region,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertRegion(Region region, Usuario usuario) {
        getSqlMapClientTemplate().insert("scdf.RegionSQL.insertRegion", region);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.RegionDAO#updateRegion(biz.belcorp.ssicc.scdf.model.Region,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateRegion(Region region, Usuario usuario) {
        getSqlMapClientTemplate().update("scdf.RegionSQL.updateRegion", region);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.RegionDAO#removeRegion(biz.belcorp.ssicc.scdf.model.Region)
     */
    public void removeRegion(Region region) {
        getSqlMapClientTemplate().delete("scdf.RegionSQL.removeRegion", region);
    }

}

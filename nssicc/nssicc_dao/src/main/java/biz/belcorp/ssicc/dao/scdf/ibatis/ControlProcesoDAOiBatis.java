/*
 * Created on 18/05/2006 12:20:22 PM
 * biz.belcorp.ssicc.scdf.dao.ibatis.ControlProcesoDAOiBatis
 */
package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scdf.ControlProcesoDAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlProcesoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("scdf.controlProcesoDAO")
public class ControlProcesoDAOiBatis extends BaseDAOiBatis implements
        ControlProcesoDAO {

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.ControlProcesoDAO#executeCierreProcesosDiarios(java.lang.String)
     */
    public void executeCierreProcesosDiarios(String codigoPais) {
        Map map = new HashMap();
        map.put("codigoPais", codigoPais);
        getSqlMapClientTemplate().update("scdf.ControlProcesoSQL.executeCierreProcesosDiarios", map);
    }

}

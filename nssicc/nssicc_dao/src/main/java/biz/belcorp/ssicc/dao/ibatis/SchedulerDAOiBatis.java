/*
 * Created on 09/08/2005 12:25:46 PM
 *
 * biz.belcorp.ssicc.dao.ibatis.SchedulerDAOiBatis
 */
package biz.belcorp.ssicc.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.SchedulerDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * TODO Include class description here.
 * <p>
 * <a href="SchedulerDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a> 
 */
@Repository("schedulerDAO")
public class SchedulerDAOiBatis extends BaseDAOiBatis implements SchedulerDAO {

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.SchedulerDAO#executeClientesConsultoras()
     */
    public void executeClientesConsultoras() {
        // Invocamos al store procedure
        getSqlMapClientTemplate().update("SchedulerSQL.executeClientesConsultoras", null);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.SchedulerDAO#executeAbonoCumpleaos()
     */
    public int executeAbonoCumpleaños() {
        int nroClientes = 0;
        
        // Variables a usar
        String clientesAbonados = "0";
        // Creamos el map en el cual enviaremos los parmetros y en el que
        // recibiremos los parmetros de retorno
        Map map = new HashMap();
        map.put("nroClientes", clientesAbonados);
        
        // Invocamos al store procedure
        getSqlMapClientTemplate().update("SchedulerSQL.executeAbonoCumpleaños", map);
        
        nroClientes = MapUtils.getIntValue(map, "nroClientes", 0);
        
        return nroClientes;

    }

}

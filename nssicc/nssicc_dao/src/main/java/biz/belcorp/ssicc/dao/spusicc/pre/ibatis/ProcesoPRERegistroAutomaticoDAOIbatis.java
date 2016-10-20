package biz.belcorp.ssicc.dao.spusicc.pre.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPRERegistroAutomaticoDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoPRERegistroAutomaticoDAO")
public class ProcesoPRERegistroAutomaticoDAOIbatis extends BaseDAOiBatis implements
				ProcesoPRERegistroAutomaticoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPRERegistroAutomaticoDAO#executeRegistroAutomatico(java.util.Map)
	 */
	public void executeRegistroAutomatico(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.MantenimientoPRESQL.executeRegistroAutomatico", params);
	}
	
}

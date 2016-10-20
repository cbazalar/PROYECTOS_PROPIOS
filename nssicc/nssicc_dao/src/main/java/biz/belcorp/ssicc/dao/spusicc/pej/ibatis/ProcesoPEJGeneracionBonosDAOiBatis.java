package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJGeneracionBonosDAO;

/**
 * @author Jesse James Rios Franco
 *
 */

public class ProcesoPEJGeneracionBonosDAOiBatis extends BaseDAOiBatis implements ProcesoPEJGeneracionBonosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJGeneracionBonosDAO#executeProcesoPEJGeneracionBonos(java.util.Map)
	 */
	public void executeProcesoPEJGeneracionBonos(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeProcesoPEJGeneracionBonos", params);
	}
}
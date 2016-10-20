package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJRecuperacionComisionDAO;

/**
 * @author Jesse James Rios Franco
 *
 */

public class ProcesoPEJRecuperacionComisionDAOiBatis extends BaseDAOiBatis implements ProcesoPEJRecuperacionComisionDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJRecuperacionComisionDAO#executeProcesoPEJRecuperacionComision(java.util.Map)
	 */
	public void executeProcesoPEJRecuperacionComision(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeProcesoPEJRecuperacionComision", params);
	}
}
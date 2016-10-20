package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJCargaTarjetaPaycardDAO;

/**
 * @author Jesse James Rios Franco
 *
 */

public class ProcesoPEJCargaTarjetaPaycardDAOiBatis extends BaseDAOiBatis implements ProcesoPEJCargaTarjetaPaycardDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaTarjetaPaycardDAO#executeProcesoPEJCargaTarjetaPaycard(java.util.Map)
	 */
	public void executeProcesoPEJCargaTarjetaPaycard(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeProcesoPEJCargaTarjetaPaycard", params);
	}

}
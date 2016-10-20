package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJDespachoCanastasDAO;

/**
 * @author Jesse James Rios Franco
 *
 */

public class ProcesoPEJDespachoCanastasDAOiBatis extends BaseDAOiBatis implements ProcesoPEJDespachoCanastasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJDespachoCanastasDAO#executeProcesoPEJDespachoCanastas(java.util.Map)
	 */
	public void executeProcesoPEJDespachoCanastas(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeProcesoPEJDespachoCanastas", params);
	}
}
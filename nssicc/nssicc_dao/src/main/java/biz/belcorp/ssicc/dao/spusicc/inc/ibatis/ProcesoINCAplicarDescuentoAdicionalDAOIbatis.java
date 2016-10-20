package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCAplicarDescuentoAdicionalDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCAplicarDescuentoAdicionalDAO")
public class ProcesoINCAplicarDescuentoAdicionalDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCAplicarDescuentoAdicionalDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCAplicarDescuentoAdicionalDAO#executeAplicarDescuentoAdicional(java.util.Map)
	 */
	public void executeAplicarDescuentoAdicional(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeAplicarDescuentoAdicional",params);
		
	}

}

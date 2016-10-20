package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCReversionEntregaPremiosDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCReversionEntregaPremiosDAO")
public class ProcesoINCReversionEntregaPremiosDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCReversionEntregaPremiosDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCReversionEntregaPremiosDAO#executeReversionEntregaPremios(java.util.Map)
	 */
	public void executeReversionEntregaPremios(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeReversionEntregaPremios",params);
		
	}

}

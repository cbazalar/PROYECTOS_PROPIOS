package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCValidacionPremiosElectivosDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCValidacionPremiosElectivosDAO")
public class ProcesoINCValidacionPremiosElectivosDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCValidacionPremiosElectivosDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCValidacionPremiosElectivosDAO#executeValidacionPremiosElectivos(java.util.Map)
	 */
	public void executeValidacionPremiosElectivos(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeValidacionPremiosElectivos",params);
	}

}

package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDespachoPremiosParaTiParaMiDAO;
/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCDespachoPremiosParaTiParaMiDAO")
public class ProcesoINCDespachoPremiosParaTiParaMiDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCDespachoPremiosParaTiParaMiDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDespachoPremiosParaTiParaMiDAO#executeDespachoPremiosParaTiParaMi(java.util.Map)
	 */
	public void executeDespachoPremiosParaTiParaMi(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeDespachoPremiosParaTiParaMi",params);
		
	}

}

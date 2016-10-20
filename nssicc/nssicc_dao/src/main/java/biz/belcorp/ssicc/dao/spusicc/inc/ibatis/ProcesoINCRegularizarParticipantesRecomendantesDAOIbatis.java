package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCRegularizarParticipantesRecomendantesDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCRegularizarParticipantesRecomendantesDAO")
public class ProcesoINCRegularizarParticipantesRecomendantesDAOIbatis extends BaseDAOiBatis implements 
	ProcesoINCRegularizarParticipantesRecomendantesDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCRegularizarParticipantesRecomendantesDAO#executeRegularizarParticipantesRecomendantes(java.util.Map)
	 */
	public void executeRegularizarParticipantesRecomendantes(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeRegularizarParticipantesRecomendantes",params);
		
	}

}

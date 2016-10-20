package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCReversionEntregaPremiosPorUltimoNivelDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCReversionEntregaPremiosPorUltimoNivelDAO")
public class ProcesoINCReversionEntregaPremiosPorUltimoNivelDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCReversionEntregaPremiosPorUltimoNivelDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCReversionEntregaPremiosPorUltimoNivelDAO#executeReversionEntregaPremiosPorUltimoNivel(java.util.Map)
	 */
	public void executeReversionEntregaPremiosPorUltimoNivel(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeReversionEntregaPremiosPorUltimoNivel",params);
		
	}

}

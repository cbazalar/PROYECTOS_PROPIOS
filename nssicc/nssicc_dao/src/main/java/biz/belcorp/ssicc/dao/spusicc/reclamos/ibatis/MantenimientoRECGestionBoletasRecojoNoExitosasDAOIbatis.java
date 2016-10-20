/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECGestionBoletasRecojoNoExitosasDAO;



/**
 * @author peextjcairampoma
 *
 */
@Repository("spusicc.mantenimientoRECGestionBoletasRecojoNoExitosasDAO")
public class MantenimientoRECGestionBoletasRecojoNoExitosasDAOIbatis extends BaseDAOiBatis implements MantenimientoRECGestionBoletasRecojoNoExitosasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECGestionBoletasRecojoNoExitosasDAO#getBoletasRecojoNOExitosasList(java.util.Map)
	 */
	public List getBoletasRecojoNOExitosasList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getBoletasRecojoNOExitosasList",params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECGestionBoletasRecojoNoExitosasDAO#executeProcesoBoletaRecojoNoExitosa(java.util.Map)
	 */
	public void executeProcesoBoletaRecojoNoExitosa(Map params) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.executeProcesoBoletaRecojoNoExitosa",params);
	}
}

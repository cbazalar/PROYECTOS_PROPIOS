/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sac.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sac.ReporteSACFechaRealEntregaDAO;

/**
 * @author Danny Amaro
 *
 */
@Repository("spusicc.reporteSACFechaRealEntregaDAO")
public class ReporteSACFechaRealEntregaDAOIbatis extends BaseDAOiBatis implements ReporteSACFechaRealEntregaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.dao.ReporteSACFechaRealEntregaDAO#executeGenerarReporteFechaRealEntrega(java.util.Map)
	 */
	public void executeGenerarReporteFechaRealEntrega(Map map) {
		getSqlMapClientTemplate().update("spusicc.sac.ProcesoSACGenerarReporteSQL.executeGenerarReporteFechaRealEntrega", map);		
	}

}

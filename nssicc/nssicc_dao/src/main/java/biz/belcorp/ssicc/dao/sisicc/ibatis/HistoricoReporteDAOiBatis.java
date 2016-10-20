/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.HistoricoReporteDAO;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;


/**
 * @author Danny Amaro
 *
 */
@Repository("sisicc.historicoReporteDAO")
public class HistoricoReporteDAOiBatis extends BaseDAOiBatis implements HistoricoReporteDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoReporteDAO#getDevuelveIdSgteCodHistoricoReporte()
	 */
	public Long getDevuelveIdSgteCodHistoricoReporte(){
		Long id = (Long)getSqlMapClientTemplate().
				queryForObject("sisicc.HistoricoReporteSQL.getDevuelveIdSgteCodHistoricoReporte");		
		return id;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoReporteDAO#insertHistoricoReporte(biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte)
	 */	
	public void insertHistoricoReporte(HistoricoReporte historicoReporte) {
		getSqlMapClientTemplate().update("sisicc.HistoricoReporteSQL.insertHistoricoReporte", historicoReporte);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoReporteDAO#updateHistoricoReporte(biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte)
	 */	
	public void updateHistoricoReporte(HistoricoReporte historicoReporte) {
		getSqlMapClientTemplate().update("sisicc.HistoricoReporteSQL.updateHistoricoReporte", historicoReporte);	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoReporteDAO#getHistoricoReporteByUser(java.util.Map)
	 */
	public List getHistoricoReporteByUser(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.HistoricoReporteSQL.getHistoricoReporteByUser", criteria);
	}

}

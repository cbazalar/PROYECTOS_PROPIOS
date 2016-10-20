/**
 * 
 */
package biz.belcorp.ssicc.dao.soa.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.soa.VisitasDAO;

/**
 * @author Danny Amaro
 *
 */
@Repository("soa.visitasDAO")
public class VisitasDAOiBatis extends BaseDAOiBatis implements VisitasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.VisitasDAO#getVisita(java.util.Map)
	 */
	public List getVisita(Map criteria) {
		//String codigoPeriodo = (String)getSqlMapClientTemplate().queryForObject("soa.VisitasSQL.getPeriodoActual", criteria);
		//criteria.put("codigoPeriodo", codigoPeriodo);
		//Long oidPeriodo = (Long)getSqlMapClientTemplate().queryForObject("soa.VisitasSQL.getOidPeriodoActual", criteria);
		//Long oidPeriodoIni = (Long)getSqlMapClientTemplate().queryForObject("soa.VisitasSQL.getOidPeriodoIni", criteria);
		//criteria.put("oidPeriodo", oidPeriodo);
		//criteria.put("oidPeriodoIni", oidPeriodoIni);//3 periodos hacia atras
		return (List)getSqlMapClientTemplate().queryForList("soa.VisitasSQL.getVisita", criteria);

	}
	

}

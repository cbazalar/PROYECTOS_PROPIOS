/**
 * 
 */
package biz.belcorp.ssicc.dao.soa.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.soa.CobranzasDAO;

/**
 * @author Danny Amaro
 *
 */
@Repository("soa.cobranzasDAO")
public class CobranzasDAOiBatis extends BaseDAOiBatis implements CobranzasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.CobranzasDAO#getEstatusCobranzaDeuda(java.util.Map)
	 */
	public List getEstatusCobranzaDeuda(Map criteria) {
		return (List)getSqlMapClientTemplate().queryForList("soa.CobranzasSQL.getEstatusCobranzaDeuda", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.CobranzasDAO#getEstatusCobranzaSeccion(java.util.Map)
	 */
	public List getEstatusCobranzaSeccion(Map criteria) {
		return (List)getSqlMapClientTemplate().queryForList("soa.CobranzasSQL.getEstatusCobranzaSeccion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.CobranzasDAO#getDiasTranscurridosDesdeFacturacion(java.util.Map)
	 */
	public List getDiasTranscurridosDesdeFacturacion(Map criteria) {		
		return getSqlMapClientTemplate().queryForList("soa.CobranzasSQL.getDiasTranscurridosDesdeFacturacion", criteria);
	}

}

/**
 * 
 */
package biz.belcorp.ssicc.dao.soa.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.soa.IndicadorDAO;

/**
 * @author Danny Amaro
 *
 */
@Repository("soa.indicadorDAO")
public class IndicadorDAOiBatis extends BaseDAOiBatis  implements IndicadorDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.IndicadorDAO#getIndicadores(java.util.Map)
	 */
	public List getIndicadores(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.IndicadoresSQL.getIndicadores", criteria);
			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.IndicadorDAO#getIndicadoresPEG(java.util.Map)
	 */
	public List getIndicadoresPEG(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.IndicadoresSQL.getIndicadoresPEG", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.IndicadorDAO#getIndicadoresMetas(java.util.Map)
	 */
	public List getIndicadoresMetas(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.IndicadoresSQL.getIndicadoresMetas", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.IndicadorDAO#getIndicadoresLetsCriticas(java.util.Map)
	 */
	public List getIndicadoresLetsCriticas(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.IndicadoresSQL.getIndicadoresLetsCriticas", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.IndicadorDAO#getIndicadoresLetsProductivas(java.util.Map)
	 */
	public List getIndicadoresLetsProductivas(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.IndicadoresSQL.getIndicadoresLetsProductivas", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.IndicadorDAO#getMontoPedidosWebComercial(java.util.Map)
	 */
	public List getIndicadoresMontoPedWebComercial(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.IndicadoresSQL.getIndicadoresMontoPedWebComercial", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.IndicadorDAO#getPedidosComercial(java.util.Map)
	 */
	public List getIndicadoresPedComercial(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.IndicadoresSQL.getIndicadoresPedComercial", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.IndicadorDAO#getPedidosWebComercial(java.util.Map)
	 */
	public List getIndicadoresPedWebComercial(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.IndicadoresSQL.getIndicadoresPedWebComercial", criteria);
		
	}

}

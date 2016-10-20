package biz.belcorp.ssicc.dao.spusicc.pre.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREValidacionMatrizDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoPREValidacionMatrizDAO")
public class MantenimientoPREValidacionMatrizDAOIbatis extends BaseDAOiBatis implements
	MantenimientoPREValidacionMatrizDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREValidacionMatrizDAO#getListValidacionMatriz(java.util.Map)
	 */
	public List getListValidacionMatriz(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getListValidacionMatriz", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREValidacionMatrizDAO#updateValidacionMatriz(java.util.Map)
	 */
	public void updateValidacionMatriz(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.MantenimientoPRESQL.updateValidacionMatriz", criteria);
	}
	
}

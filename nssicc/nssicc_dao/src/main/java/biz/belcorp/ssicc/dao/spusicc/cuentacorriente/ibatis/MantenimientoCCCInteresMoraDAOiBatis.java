/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCInteresMoraDAO;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.mantenimientoCCCInteresMoraDAO")
public class MantenimientoCCCInteresMoraDAOiBatis extends	BaseDAOiBatis implements MantenimientoCCCInteresMoraDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCInteresMoraDAO#getInteresMontoMora()
	 */
	public Map getInteresMontoMora() {
		Map map =(Map)getSqlMapClientTemplate().queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getInteresMontoMora");
		return map;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCInteresMoraDAO#insertInteresMontoMora(java.util.Map)
	 */
	public void insertInteresMontoMora(Map criteria){
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.insertInteresMontoMora", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCInteresMoraDAO#updateInteresMontoMora(java.util.Map)
	 */
	public void updateInteresMontoMora(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.updateInteresMontoMora", criteria);
	}
}

/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionDatamartDAO;

/**
 * @author Jorge Florencio Arias
 *
 */
@Repository("spusicc.procesoCCCGenerarInformacionDatamartDAO")
public class ProcesoCCCGenerarInformacionDatamartDAOiBatis extends BaseDAOiBatis implements ProcesoCCCGenerarInformacionDatamartDAO {
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCGenerarInformacionDatamartDAO#executeGenerarInformacionDatamart(java.util.Map)
	 */
	public void executeGenerarInformacionDatamart(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazCCCSQL.executeInterfazCCCGenerarInformacionDatamart", criteria);
	}
			
}

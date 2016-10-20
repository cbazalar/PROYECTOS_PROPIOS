/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGeneracionArchivosMorosasDAO;

/**
 * @author Sebastian Guerra
 *
 */
@Repository("spusicc.procesoCCCGeneracionArchivosMorosasDAO")
public class ProcesoCCCGeneracionArchivosMorosasDAOiBatis extends BaseDAOiBatis implements ProcesoCCCGeneracionArchivosMorosasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCGeneracionArchivosMorosasDAO#executeGeneracionArchivosMorosas(java.util.Map)
	 */
	public void executeGeneracionArchivosMorosas(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazCCCSQL.executeProcesoCCCGeneracionArchivosMorosas", criteria);
	}

}

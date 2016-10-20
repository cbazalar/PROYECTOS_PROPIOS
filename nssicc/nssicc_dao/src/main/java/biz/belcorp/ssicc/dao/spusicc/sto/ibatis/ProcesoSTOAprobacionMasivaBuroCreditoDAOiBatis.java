/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOAprobacionMasivaBuroCreditoDAO;

/**
 * @author sguerra
 *
 */
@Repository("spusicc.procesoSTOAprobacionMasivaBuroCreditoDAO")
public class ProcesoSTOAprobacionMasivaBuroCreditoDAOiBatis extends BaseDAOiBatis implements ProcesoSTOAprobacionMasivaBuroCreditoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOAprobacionMasivaBuroCreditoDAO#getValidaNumeroDocumento(java.lang.String)
	 */
	public Integer getValidaNumeroDocumento(String value) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getValidaNumeroDocumento",value);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOAprobacionMasivaBuroCreditoDAO#updateNumeroDocumento(java.util.Map)
	 */
	public void updateNumeroDocumento(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.updateNumeroDocumento", criteria);
	}
	
}

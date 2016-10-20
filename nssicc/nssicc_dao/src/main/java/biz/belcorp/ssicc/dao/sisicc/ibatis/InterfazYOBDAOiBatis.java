/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazYOBDAO;

/**
 * @author Danny Amaro
 *
 */
@Repository("sisicc.interfazYOBDAO")
public class InterfazYOBDAOiBatis extends BaseDAOiBatis implements InterfazYOBDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazYOBDAO#insertYOBCargaLotesTrazabilidad(java.util.Map)
	 */
	public void insertYOBCargaLotesTrazabilidad(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazYOBSQL.insertYOBCargaLotesTrazabilidad",criteria);		
	}

	public String getDevuelvePeriodoFechaFacturacionNumPedido(Map params) {
		String resultado = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazYOBSQL.getDevuelvePeriodoFechaFacturacionNumPedido", 	params);
		return resultado;
	}

}

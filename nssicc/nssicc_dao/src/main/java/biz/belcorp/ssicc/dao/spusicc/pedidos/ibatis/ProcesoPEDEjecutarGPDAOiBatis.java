/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDEjecutarGPDAO;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Repository("spusicc.procesoPEDEjecutarGPDAO")
public class ProcesoPEDEjecutarGPDAOiBatis extends BaseDAOiBatis implements ProcesoPEDEjecutarGPDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDEjecutarGPDAO#getIndicadorEnvioValidaciones()
	 */
	public Integer getIndicadorEnvioValidaciones() {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIndicadorEnvioValidaciones", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDEjecutarGPDAO#getIndicadorEnvioValidacionesSTO(java.util.Map)
	 */
	public Integer getIndicadorEnvioValidacionesSTO(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIndicadorEnvioValidacionesSTO", criteria);
	}
	
}

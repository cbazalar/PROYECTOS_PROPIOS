/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEDesbloquearClienteConPedidoDAO;

/**
 * @author itocto
 *
 */
@Repository("spusicc.procesoMAEDesbloquearClienteConPedidoDAO")
public class ProcesoMAEDesbloquearClienteConPedidoDAOIbatis extends BaseDAOiBatis implements ProcesoMAEDesbloquearClienteConPedidoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEDesbloquearClienteConPedidoDAO#executeDesbloquearClienteConPedido(java.util.Map)
	 */
	public void executeDesbloquearClienteConPedido(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeDesbloquearClienteConPedido",params);
	}

}

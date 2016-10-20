package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCActualizaResumenPedidoDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoINCActualizaResumenPedidoDAO")
public class ProcesoINCActualizaResumenPedidoDAOiBatis extends BaseDAOiBatis implements ProcesoINCActualizaResumenPedidoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCActualizaResumenPedidoDAO#executeActualizaResumenPedido(java.util.Map)
	 */
	public void executeActualizaResumenPedido(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeActualizaResumenPedido", params);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCActualizaResumenPedidoDAO#executeActualizaResumenPedidoDiario(java.util.Map)
	 */
	public void executeActualizaResumenPedidoDiario(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeActualizaResumenPedidoDiario", params);
	}



}
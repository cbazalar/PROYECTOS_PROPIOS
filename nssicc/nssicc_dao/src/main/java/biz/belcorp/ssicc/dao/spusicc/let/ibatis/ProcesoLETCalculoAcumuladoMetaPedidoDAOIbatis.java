package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoAcumuladoMetaPedidoDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.procesoLETCalculoAcumuladoMetaPedidoDAO")
public class ProcesoLETCalculoAcumuladoMetaPedidoDAOIbatis extends BaseDAOiBatis implements ProcesoLETCalculoAcumuladoMetaPedidoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoAcumuladoMetaPedidoDAO#executeProcesoLETCalculoAcumuladoMetaPedido(java.util.Map)
	 */
	public void executeProcesoLETCalculoAcumuladoMetaPedido(Map params) {
		log.info("Entro a ProcesoLETCalculoAcumuladoMetaPedidoDAOIbatis - executeProcesoLETCalculoAcumuladoMetaPedido(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoAcumuladoMetaPedido", params);
		log.info("Salio a ProcesoLETCalculoAcumuladoMetaPedidoDAOIbatis - executeProcesoLETCalculoAcumuladoMetaPedido(java.util.Map)");
	}
	
}

package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO;

@Repository("spusicc.procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO")
public class ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAOIbatis extends BaseDAOiBatis 
	implements ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO#executeEvaluarRecomendaciones2PedidosCierrePeriodo(java.util.Map)
	 */
	public void executeEvaluarRecomendaciones2PedidosCierrePeriodo(Map params) {
		log.debug("Calling to Stor..");
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarRecomendaciones2PedidosCierrePeriodo",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO#executeEvaluarRecomendaciones2PedidosCierreRegion(java.util.Map)
	 */
	public void executeEvaluarRecomendaciones2PedidosCierreRegion(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarRecomendaciones2PedidosCierreRegion",params);
	}
}
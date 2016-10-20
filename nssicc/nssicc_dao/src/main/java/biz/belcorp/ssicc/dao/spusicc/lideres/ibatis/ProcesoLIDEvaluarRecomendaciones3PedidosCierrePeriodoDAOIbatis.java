package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoDAO;

@Repository("spusicc.procesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoDAO")
public class ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoDAOIbatis
		extends BaseDAOiBatis implements
		ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoDAO {

	public void executeEvaluarRecomendaciones3PedidosCierrePeriodo(
			Map params) {
		log.debug("Calling to Stor..");
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarRecomendaciones3PedidosCierrePeriodo",params);
	}

}

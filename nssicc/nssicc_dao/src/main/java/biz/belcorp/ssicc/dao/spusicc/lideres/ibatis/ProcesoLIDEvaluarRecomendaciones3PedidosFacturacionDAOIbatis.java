package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionDAO;

@Repository("spusicc.procesoLIDEvaluarRecomendaciones3PedidosFacturacionDAO")
public class ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionDAOIbatis
		extends BaseDAOiBatis implements
		ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionDAO {

	public void executeEvaluarRecomendaciones3PedidosFacturacion(
			Map params) {
		log.debug("Calling to Stor..");
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarRecomendaciones3PedidosFacturacion",params);

	}

}

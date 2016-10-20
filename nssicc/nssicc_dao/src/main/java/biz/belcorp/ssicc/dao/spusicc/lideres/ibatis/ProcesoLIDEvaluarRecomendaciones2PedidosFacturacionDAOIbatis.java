package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionDAO;

@Repository("spusicc.procesoLIDEvaluarRecomendaciones2PedidosFacturacionDAO")
public class ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionDAOIbatis
		extends BaseDAOiBatis implements
		ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionDAO {

	public void executeEvaluarRecomendaciones2PedidosFacturacion(
			Map params) {
		log.debug("Calling to Stor..");
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarRecomendaciones2PedidosFacturacion",params);

	}

}

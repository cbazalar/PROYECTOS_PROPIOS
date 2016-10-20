package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO;

@Repository("spusicc.procesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO")
public class ProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO{

	public void executeEvaluarNumeroPedidosSeccionCierreRegion(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarNumeroPedidosSeccionCierreRegion",params);
		
	}


}

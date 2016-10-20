package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoOCRActualizacionGruposProcesoDAO;

@Repository("spusicc.procesoOCRActualizacionGruposProcesoDAO")
public class ProcesoOCRActualizacionGruposProcesoDAOiBatis extends BaseDAOiBatis implements ProcesoOCRActualizacionGruposProcesoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoOCRActualizacionGruposProcesoDAO#getTiposSolicitud()
	 */
	public List getTiposSolicitud() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTiposSolicitud");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoOCRActualizacionGruposProcesoDAO#getSolicitudesGP2ByPeriodoTipoSoliFecha(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Integer getSolicitudesGP2ByPeriodoTipoSoliFecha(String periodo,ArrayList tipoSolicitud, String fechaInicio,String fechaFin) {
		
		Map parametros = new HashMap();
		
		parametros.put("periodo", periodo);
		parametros.put("tipoSolicitud", tipoSolicitud);
		parametros.put("fechaInicio", fechaInicio);
		parametros.put("fechaFin", fechaFin);
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getSolicitudesGP2ByPeriodoTipoSoliFecha", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoOCRActualizacionGruposProcesoDAO#updateGrupoProceso(java.util.Map)
	 */
	public void updateGrupoProceso(Map parametros) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateGrupoProceso", parametros);
	}
}
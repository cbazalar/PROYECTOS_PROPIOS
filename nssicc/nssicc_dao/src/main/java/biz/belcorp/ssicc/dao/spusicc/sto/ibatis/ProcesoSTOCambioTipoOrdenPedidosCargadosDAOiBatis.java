package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOCambioTipoOrdenPedidosCargadosDAO;

/**
 * @author peextjrios
 */
@Repository("spusicc.procesoSTOCambioTipoOrdenPedidosCargadosDAO")
public class ProcesoSTOCambioTipoOrdenPedidosCargadosDAOiBatis extends BaseDAOiBatis implements ProcesoSTOCambioTipoOrdenPedidosCargadosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOCambioTipoOrdenPedidosCargadosDAO#getClientesTipoOrdenList(java.util.Map)
	 */
	public List getClientesTipoOrdenList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getClientesTipoOrdenList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOCambioTipoOrdenPedidosCargadosDAO#updateTipoOrdenPedidosCargados(java.util.Map)
	 */
	public void updateTipoOrdenPedidosCargados(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateTipoOrdenPedidosCargados", criteria);
	}
}
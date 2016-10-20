package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPREModificacionesMasivasDAO;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
@Repository("spusicc.procesoPREModificacionesMasivasDAO")
public class ProcesoPREModificacionesMasivasDAOiBatis extends BaseDAOiBatis implements ProcesoPREModificacionesMasivasDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREModificacionesMasivasDAO#deleteTablaModificacionesMasivasTemporal()
	 */
	public void deleteTablaModificacionesMasivasTemporal() {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteTablaModificacionesMasivasTemporal", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREModificacionesMasivasDAO#insertModificacionesMasivasTemporal(java.util.Map)
	 */
	public void insertModificacionesMasivasTemporal(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertModificacionesMasivasTemporal", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREModificacionesMasivasDAO#executeValidarCargaModificacionesMasivas(java.util.Map)
	 */
	public void executeValidarCargaModificacionesMasivas(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeValidarCargaModificacionesMasivas", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREModificacionesMasivasDAO#getCargaModificacionesMasivasList(java.util.Map)
	 */
	public List getCargaModificacionesMasivasList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCargaModificacionesMasivasList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREModificacionesMasivasDAO#executeActualizarCargaModificacionesMasivas(java.util.Map)
	 */
	public void executeActualizarCargaModificacionesMasivas(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeActualizarCargaModificacionesMasivas", params);
	}
}
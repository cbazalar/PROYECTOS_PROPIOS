package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ClasificacionesChequeo;

@Repository("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoDAO")
public class MantenimientoPEDClasificacionesChequeoDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDClasificacionesChequeoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDClasificacionesChequeoDAO#getTipoCliente()
	 */
	public List getTipoCliente() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTipoCliente");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDClasificacionesChequeoDAO#getSubTipoClienteByOidTipoCliente(java.lang.String)
	 */
	public List getSubTipoClienteByOidTipoCliente(String oidTipoCliente) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getSubTipoClienteByOidTipoCliente",oidTipoCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDClasificacionesChequeoDAO#getTipoClasificacionByOidSubTipoCliente(java.lang.String)
	 */
	public List getTipoClasificacionByOidSubTipoCliente(String oidSubTipoCliente) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTipoClasificacionByOidSubTipoCliente",oidSubTipoCliente);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDClasificacionesChequeoDAO#getClasificacionByOidTipoClasificacion(java.lang.String)
	 */
	public List getClasificacionByOidTipoClasificacion(String oidTipoClasificacion){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getClasificacionByOidTipoClasificacion",oidTipoClasificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDClasificacionesChequeoDAO#getClasificacionChequeo(java.util.Map)
	 */
	public List getClasificacionChequeo(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getClasificacionChequeo", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDClasificacionesChequeoDAO#deleteClasificacionChequeo(java.util.Map)
	 */
	public void deleteClasificacionChequeo(Map map) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteClasificacionChequeo", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDClasificacionesChequeoDAO#insertClasificacionChequeo(java.util.Map)
	 */
	public void insertClasificacionChequeo(Map params) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertClasificacionChequeo",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDClasificacionesChequeoDAO#getClasificacionChequeoObject(java.util.Map)
	 */
	public ClasificacionesChequeo getClasificacionChequeoObject(Map map) {
		return (ClasificacionesChequeo)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getClasificacionChequeoObject", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDClasificacionesChequeoDAO#updateClasificacionChequeo(java.util.Map)
	 */
	public void updateClasificacionChequeo(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateClasificacionChequeo", params);
	}
}
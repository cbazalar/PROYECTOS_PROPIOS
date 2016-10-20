package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDFleteDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Flete;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.FleteDetalle;

@Repository("spusicc.mantenimientoPEDFleteDAO")
public class MantenimientoPEDFleteDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDFleteDAO{

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#getTipoDespacho()
	 */
	public List getTipoDespacho() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTipoDespacho");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#getFleteList(java.util.Map)
	 */
	public List getFleteList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getFleteList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#deleteFlete(biz.belcorp.ssicc.spusicc.pedidos.model.Flete)
	 */
	public void deleteFlete(Flete flete, Usuario usuario) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteFlete", flete);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#getNextOidFlete(java.util.Map)
	 */
	public int getNextOidFlete() {
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getNextOidFlete", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#insertFlete(biz.belcorp.ssicc.spusicc.pedidos.model.Flete)
	 */
	public void insertFlete(Flete flete, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertFlete", flete);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#insertFleteAuditoria(biz.belcorp.ssicc.spusicc.pedidos.model.Flete)
	 */
	public void insertFleteAuditoria(Flete flete, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertFleteAudit", flete);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#getFleteObject(java.util.Map)
	 */
	public Flete getFlete(String idFlete) {
		return (Flete)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getFlete", idFlete);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#updateFlete(biz.belcorp.ssicc.spusicc.pedidos.model.Flete)
	 */
	public void updateFlete(Flete flete, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateFlete", flete);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#insertFleteAuditoriaDetalle(biz.belcorp.ssicc.spusicc.pedidos.model.FleteDetalle)
	 */
	public void insertFleteAuditoriaDetalle(FleteDetalle detalle, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertFleteDetalleAudit", detalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#insertFleteDetalle(biz.belcorp.ssicc.spusicc.pedidos.model.FleteDetalle)
	 */
	public void insertFleteDetalle(FleteDetalle detalle, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertFleteDetalle", detalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#getNextOidFleteDetalle()
	 */
	public int getNextOidFleteDetalle() {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getNextOidFleteDetalle");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#getDetalleFleteList(java.lang.String)
	 */
	public List getDetalleFleteList(String idFlete) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetalleFleteList",idFlete);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#deleteFleteDetalle(biz.belcorp.ssicc.spusicc.pedidos.model.FleteDetalle)
	 */
	public void deleteFleteDetalle(FleteDetalle detalle, Usuario usuario) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteFleteDetalle", detalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#updateFleteDetalle(biz.belcorp.ssicc.spusicc.pedidos.model.FleteDetalle)
	 */
	public void updateFleteDetalle(FleteDetalle detalle, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateFleteDetalle", detalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDFleteDAO#getDetalleFlete(java.lang.String)
	 */
	public FleteDetalle getDetalleFlete(String oidDetalleFlete) {
		return (FleteDetalle)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getDetalleFlete", oidDetalleFlete);
	}

}

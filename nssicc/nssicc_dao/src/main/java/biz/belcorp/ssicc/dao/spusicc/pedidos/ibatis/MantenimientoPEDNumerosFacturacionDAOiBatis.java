/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDNumerosFacturacionDAO;

/**
 * @author sguerra
 *
 */
@Repository("spusicc.mantenimientoPEDNumerosFacturacionDAO")
public class MantenimientoPEDNumerosFacturacionDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDNumerosFacturacionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNumerosFacturacionDAO#getSociedadList()
	 */
	public List getSociedadList() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getSociedadList");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNumerosFacturacionDAO#getTipoDocumentoList()
	 */
	public List getTipoDocumentoList() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTipoDocumentoList");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNumerosFacturacionDAO#getNumerosFacturacionList(java.util.Map)
	 */
	public List getNumerosFacturacionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getNumerosFacturacionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNumerosFacturacionDAO#insertNumerosFacturacion(java.util.Map)
	 */
	public void insertNumerosFacturacion(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertNumerosFacturacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNumerosFacturacionDAO#deleteNumerosFacturacion(java.util.Map)
	 */
	public void deleteNumerosFacturacion(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteNumerosFacturacion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNumerosFacturacionDAO#updateNumerosFacturacion(java.util.Map)
	 */
	public void updateNumerosFacturacion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateNumerosFacturacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNumerosFacturacionDAO#insertHistoricoNumerosFacturacion(java.util.Map)
	 */
	public void insertHistoricoNumerosFacturacion(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertHistoricoNumerosFacturacion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNumerosFacturacionDAO#updateHistoricoNumerosFacturacion(java.util.Map)
	 */
	public void updateHistoricoNumerosFacturacion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateHistoricoNumerosFacturacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNumerosFacturacionDAO#getValidarNumerosFacturacion(java.util.Map)
	 */
	public String getValidarNumerosFacturacion(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getValidateNumerosFacturacion", criteria);
	}
	
}

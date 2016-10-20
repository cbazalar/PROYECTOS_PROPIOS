/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDCargaArchivoDesarrolladoraVentasDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DesarrolladoraVenta;

/**
 * @author Danny Amaro
 *
 */
@Repository("spusicc.procesoPEDCargaArchivoDesarrolladoraVentasDAO")
public class ProcesoPEDCargaArchivoDesarrolladoraVentasDAOiBatis extends BaseDAOiBatis implements ProcesoPEDCargaArchivoDesarrolladoraVentasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaArchivoDesarrolladoraVentasDAO#getDesarrolladoraVenta(java.util.Map)
	 */
	public List getDesarrolladoraVenta(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDesarrolladoraVenta", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaArchivoDesarrolladoraVentasDAO#insertDesarrolladoraVenta(biz.belcorp.ssicc.spusicc.pedidos.model.DesarrolladoraVenta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertDesarrolladoraVenta(
			DesarrolladoraVenta desarrolladoraVenta, Usuario usuario) {
		
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.insertDesarrolladoraVenta", desarrolladoraVenta);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaArchivoDesarrolladoraVentasDAO#insertDesarrolladoraVentaHistorico(biz.belcorp.ssicc.spusicc.pedidos.model.DesarrolladoraVenta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertDesarrolladoraVentaHistorico(
			DesarrolladoraVenta desarrolladoraVenta, Usuario usuario) {
		
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.insertDesarrolladoraVentaHistorico", desarrolladoraVenta);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaArchivoDesarrolladoraVentasDAO#removeDesarrolladoraVenta(java.util.Map)
	 */
	public void removeDesarrolladoraVenta(Map criteria) {
		
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.removeDesarrolladoraVenta", criteria);
		
	}

}

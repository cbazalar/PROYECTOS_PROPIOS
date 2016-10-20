package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRReemplazosDAO;

/**
 * @author peextdoliva
 */
@Repository("spusicc.pedidos.mantenimientoOCRReemplazosDAO")
public class MantenimientoOCRReemplazosDAOiBatis extends BaseDAOiBatis implements MantenimientoOCRReemplazosDAO {
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRReemplazosDAO#getReemplazosByPeriodo(java.util.Map)
	 */
	public List getReemplazosByPeriodo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getReemplazosByPeriodo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRReemplazosDAO#deleteReemplazos(java.util.Map)
	 */
	public void deleteReemplazos(Map criteria) {		
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteReemplazos",criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRReemplazosDAO#getInformacionCUV(java.util.Map)
	 */
	public List getInformacionCUV(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getInformacionCUV", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRReemplazosDAO#validaCUVPrincipal(java.util.Map)
	 */
	public String validaCUVPrincipal(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.validaCUVPrincipal",criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRReemplazosDAO#insertOCRReemplazos(java.util.Map)
	 */
	public void insertOCRReemplazos(Map criteria) {
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertOCRReemplazos", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRReemplazosDAO#updateReemplazos(java.util.Map)
	 */
	public void updateReemplazos(Map criteria) {
		//actualizamos el reemlazo
		this.getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateReemplazos", criteria);
		//insertamos en la auditoria de reemplazos la accion realizada
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertOCRReemplazosAuditoria", criteria);
		
	}
}
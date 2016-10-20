package biz.belcorp.ssicc.dao.spusicc.ocr.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ocr.MantenimientoOCRArchivoControlMultihiloDAO;

@Repository("spusicc.mantenimientoOCRArchivoControlMultihiloDAO")
public class MantenimientoOCRArchivoControlMultihiloDAOiBatis extends BaseDAOiBatis implements MantenimientoOCRArchivoControlMultihiloDAO{


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.dao.MantenimientoOCRArchivoControlMultihiloDAO#getArchivoControlMultihilo()
	 */
	public List getArchivoControlMultihilo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getArchivoControlMultihilo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.dao.MantenimientoOCRArchivoControlMultihiloDAO#getArchivoControlMultihiloGeneral()
	 */
	public List getArchivoControlMultihiloGeneral() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getArchivoControlMultihiloGeneral");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.dao.MantenimientoOCRArchivoControlMultihiloDAO#getArchivoControl(java.util.Map)
	 */
	public Map getArchivoControl(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getArchivoControl", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.dao.MantenimientoOCRArchivoControlMultihiloDAO#updateArchivoControl(java.util.Map)
	 */
	public void updateArchivoControl(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateArchivoControl",criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.dao.MantenimientoOCRArchivoControlMultihiloDAO#getBasHistoLotes(java.util.Map)
	 */
	public List getBasHistoLotes(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getBasHistoLotes",criteria);
	}


}

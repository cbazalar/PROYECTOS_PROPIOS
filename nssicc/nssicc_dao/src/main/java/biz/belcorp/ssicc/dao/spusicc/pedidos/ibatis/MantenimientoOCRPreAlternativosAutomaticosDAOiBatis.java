package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPreAlternativosAutomaticosDAO;

/**
 * @author peextcroman
 */
@Repository("spusicc.pedidos.mantenimientoOCRPreAlternativosAutomaticosDAO")
public class MantenimientoOCRPreAlternativosAutomaticosDAOiBatis extends
		BaseDAOiBatis implements MantenimientoOCRPreAlternativosAutomaticosDAO {
		
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPreAlternativosAutomaticosDAO#deleteAlternativosAutomaticos(java.util.Map)
	 */
	public void deleteAlternativosAutomaticos(Map criteria) {
        getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteAlternativosAutomaticos", criteria);
        getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertPreAlternativosAutomaticosAuditoria", criteria);
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPreAlternativosAutomaticosDAO#getListaAlternativosAutomaticos(java.util.Map)
	 */
	public List getListaAlternativosAutomaticos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaAlternativosAutomaticos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPreAlternativosAutomaticosDAO#insertPreAlternativosAutomaticos(java.util.Map)
	 */
	public void insertPreAlternativosAutomaticos(Map criteria){
		String valida=(String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.verificaPreAlternativosAutomaticos", criteria);
		
		if(valida==null){
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertPreAlternativosAutomaticos", criteria);
		  getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertPreAlternativosAutomaticosAuditoria", criteria);
		}  
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPreAlternativosAutomaticosDAO#executeProcesoCrearOfertas(java.util.Map)
	 */
	public void executeProcesoCrearOfertas(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeProcesoCrearOfertas", criteria);	
	}
	
}
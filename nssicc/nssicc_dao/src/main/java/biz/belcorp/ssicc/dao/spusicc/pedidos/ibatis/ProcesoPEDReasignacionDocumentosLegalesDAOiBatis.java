package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDReasignacionDocumentosLegalesDAO;

@Repository("spusicc.procesoPEDReasignacionDocumentosLegalesDAO")
public class ProcesoPEDReasignacionDocumentosLegalesDAOiBatis extends BaseDAOiBatis implements ProcesoPEDReasignacionDocumentosLegalesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDReasignacionDocumentosLegalesDAO#getTipoDocumentoContableAllList()
	 */
	public List getTipoDocumentoContableAllList() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTipoDocumentoContableAllList");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDReasignacionDocumentosLegalesDAO#getCantDocImpr(java.util.Map)
	 */
	public Map getCantDocImpr(Map parametros) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getCantDocImpr", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDReasignacionDocumentosLegalesDAO#executeReasignacionDocumentosLegales(java.util.Map)
	 */
	public void executeReasignacionDocumentosLegales(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeReasignacionDocumentosLegales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDReasignacionDocumentosLegalesDAO#getIndSoporteCaracteres(java.util.Map)
	 */
	public String getIndSoporteCaracteres(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIndSoporteCaracteres", criteria);
	}
	
}
package biz.belcorp.ssicc.dao.spusicc.pre.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPREEliminarMatrizFacturacionDAO;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Repository("spusicc.procesoPREEliminarMatrizFacturacionDAO")
public class ProcesoPREEliminarMatrizFacturacionDAOIbatis extends BaseDAOiBatis implements ProcesoPREEliminarMatrizFacturacionDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPREEliminarMatrizFacturacionDAO#executeEliminarMatrizFacturacion(java.util.Map)
	 */
	@Override
	public void executeEliminarMatrizFacturacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.executeEliminarMatrizFacturacion", params);
	}
}
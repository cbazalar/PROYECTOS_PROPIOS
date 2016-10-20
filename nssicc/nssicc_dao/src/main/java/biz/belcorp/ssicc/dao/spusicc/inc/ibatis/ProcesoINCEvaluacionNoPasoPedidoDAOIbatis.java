package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCEvaluacionNoPasoPedidoDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCEvaluacionNoPasoPedidoDAO")
public class ProcesoINCEvaluacionNoPasoPedidoDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCEvaluacionNoPasoPedidoDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCEvaluacionNoPasoPedidoDAO#executeEvaluacionNoPasoPedido(java.util.Map)
	 */
	public void executeEvaluacionNoPasoPedido(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeEvaluacionNoPasoPedido",params);
		
	}

}

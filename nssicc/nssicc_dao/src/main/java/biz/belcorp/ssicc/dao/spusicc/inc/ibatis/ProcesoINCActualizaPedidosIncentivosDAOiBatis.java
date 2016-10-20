package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCActualizaPedidosIncentivosDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoINCActualizaPedidosIncentivosDAO")
public class ProcesoINCActualizaPedidosIncentivosDAOiBatis extends BaseDAOiBatis implements ProcesoINCActualizaPedidosIncentivosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCActualizaPedidosIncentivosDAO#executeActualizaPedidosIncentivos(java.util.Map)
	 */
	public void executeActualizaPedidosIncentivos(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeActualizaPedidosIncentivos", params);
	}
}
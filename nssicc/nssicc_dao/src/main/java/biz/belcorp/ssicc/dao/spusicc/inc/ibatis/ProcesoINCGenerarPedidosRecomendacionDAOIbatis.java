package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarPedidosRecomendacionDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCGenerarPedidosRecomendacionDAO")
public class ProcesoINCGenerarPedidosRecomendacionDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCGenerarPedidosRecomendacionDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCGenerarPedidosRecomendacionDAO#executeGenerarPedidosRecomendacion(java.util.Map)
	 */
	public void executeGenerarPedidosRecomendacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeGenerarPedidosRecomendacion",params);
		
	}

}

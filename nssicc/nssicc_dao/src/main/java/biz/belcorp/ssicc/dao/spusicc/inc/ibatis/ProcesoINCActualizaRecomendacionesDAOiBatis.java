package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCActualizaRecomendacionesDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoINCActualizaRecomendacionesDAO")
public class ProcesoINCActualizaRecomendacionesDAOiBatis extends BaseDAOiBatis implements ProcesoINCActualizaRecomendacionesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCActualizaRecomendacionesDAO#executeProcesoINCActualizaRecomendaciones(java.util.Map)
	 */
	public void executeProcesoINCActualizaRecomendaciones(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeProcesoINCActualizaRecomendaciones", params);
	}
}
package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCRegularizarRegistroRecomendacionDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Regulariza Registros
 * de Recomendacion
 * 
 * <p>
 * <a href="ProcesoINCRegularizarRegistroRecomendacionDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCRegularizarRegistroRecomendacionDAO")
public class ProcesoINCRegularizarRegistroRecomendacionDAOIbatis extends BaseDAOiBatis implements ProcesoINCRegularizarRegistroRecomendacionDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCRegularizarRegistroRecomendacionDAO#executeRegularizarRegistroRecomendacion(java.util.Map)
	 */
	public void executeRegularizarRegistroRecomendacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeRegularizarRegistroRecomendacion",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCRegularizarRegistroRecomendacionDAO#executeRegularizarRegistroRecomendacionInicioCampana(java.util.Map)
	 */
	public void executeRegularizarRegistroRecomendacionInicioCampana(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeRegularizarRegistroRecomendacionInicioCampana",params);
	}
}
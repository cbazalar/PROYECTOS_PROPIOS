package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDespacharPremiosSolicitudesRetenidasDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Despacha Premios
 * de Solicitudes Retenidas
 * 
 * <p>
 * <a href="ProcesoINCDespacharPremiosSolicitudesRetenidasDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCDespacharPremiosSolicitudesRetenidasDAO")
public class ProcesoINCDespacharPremiosSolicitudesRetenidasDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCDespacharPremiosSolicitudesRetenidasDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDespacharPremiosSolicitudesRetenidasDAO#executeDespacharPremiosSolicitudesRetenidas(java.util.Map)
	 */
	public void executeDespacharPremiosSolicitudesRetenidas(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeDespacharPremiosSolicitudesRetenidas",params);
		
	}

}

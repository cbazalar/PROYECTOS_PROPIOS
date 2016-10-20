package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarSolicitudBolsaFaltantesDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCGenerarSolicitudBolsaFaltantesDAO")
public class ProcesoINCGenerarSolicitudBolsaFaltantesDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCGenerarSolicitudBolsaFaltantesDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCGenerarSolicitudBolsaFaltantesDAO#executeGenerarSolicitudBolsaFaltantes(java.util.Map)
	 */
	public void executeGenerarSolicitudBolsaFaltantes(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeGenerarSolicitudBolsaFaltantes",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCGenerarSolicitudBolsaFaltantesDAO#executeRegistroCierreZona(java.util.Map)
	 */
	public void executeRegistroCierreZona(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeRegistroCierreZona",params);
	
	}

}

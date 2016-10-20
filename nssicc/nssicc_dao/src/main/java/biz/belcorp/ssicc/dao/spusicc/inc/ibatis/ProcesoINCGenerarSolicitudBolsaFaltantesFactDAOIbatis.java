package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarSolicitudBolsaFaltantesFactDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCGenerarSolicitudBolsaFaltantesFactDAO")
public class ProcesoINCGenerarSolicitudBolsaFaltantesFactDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCGenerarSolicitudBolsaFaltantesFactDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCGenerarSolicitudBolsaFaltantesDAO#executeGenerarSolicitudBolsaFaltantesFact(java.util.Map)
	 */
	public void executeGenerarSolicitudBolsaFaltantesFact(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeGenerarSolicitudBolsaFaltantesFact",params);
		
	}

}

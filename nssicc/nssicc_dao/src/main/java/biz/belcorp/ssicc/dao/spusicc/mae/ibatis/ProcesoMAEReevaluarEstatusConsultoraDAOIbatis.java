package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEReevaluarEstatusConsultoraDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoMAEReevaluarEstatusConsultoraDAO")
public class ProcesoMAEReevaluarEstatusConsultoraDAOIbatis extends BaseDAOiBatis implements 
				ProcesoMAEReevaluarEstatusConsultoraDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEReevaluarEstatusConsultoraDAO#existenRegionesCerradas(java.util.Map)
	 */
	public boolean existenRegionesCerradas(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.ProcesosMAESQL.getExistenRegionesCerradas", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoMAEReevaluarEstatusConsultoraDAO#executeGenerarSolicitudBolsaFaltantes(java.util.Map)
	 */
	public void executeReevaluarEstatusConsultora(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeReevaluarEstatusConsultora",params);
		
	}
	
}


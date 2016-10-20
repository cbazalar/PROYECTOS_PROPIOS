package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarEstatusConsultorasRegionDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoMAEActualizarEstatusConsultorasRegionDAO")
public class ProcesoMAEActualizarEstatusConsultorasRegionDAOIbatis extends BaseDAOiBatis implements 
	ProcesoMAEActualizarEstatusConsultorasRegionDAO  {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEActualizarEstatusConsultorasDAO#executeActualizarEstatusConsultorasRegion(java.util.Map)
	 */
	public void executeActualizarEstatusConsultorasRegion(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarEstatusConsultorasRegion",params);
		
	}

}
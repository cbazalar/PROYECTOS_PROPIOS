package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarEstatusConsultorasDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoMAEActualizarEstatusConsultorasDAO")
public class ProcesoMAEActualizarEstatusConsultorasDAOIbatis extends BaseDAOiBatis implements 
	ProcesoMAEActualizarEstatusConsultorasDAO  {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEActualizarEstatusConsultorasDAO#executeActualizarEstatusConsultoras(java.util.Map)
	 */
	public void executeActualizarEstatusConsultoras(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarEstatusConsultoras",params);
		
	}

}

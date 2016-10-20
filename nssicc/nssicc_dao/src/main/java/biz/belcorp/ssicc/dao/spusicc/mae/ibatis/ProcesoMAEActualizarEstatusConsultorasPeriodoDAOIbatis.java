package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarEstatusConsultorasPeriodoDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoMAEActualizarEstatusConsultorasPeriodoDAO")
public class ProcesoMAEActualizarEstatusConsultorasPeriodoDAOIbatis extends BaseDAOiBatis implements 
	ProcesoMAEActualizarEstatusConsultorasPeriodoDAO  {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEActualizarEstatusConsultorasDAO#executeActualizarEstatusConsultorasPeriodo(java.util.Map)
	 */
	public void executeActualizarEstatusConsultorasPeriodo(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarEstatusConsultorasPeriodo",params);
		
	}

}

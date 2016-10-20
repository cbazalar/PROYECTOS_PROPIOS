package biz.belcorp.ssicc.dao.spusicc.mav.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mav.ProcesoMAVGenerarEnviosDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoMAVGenerarEnviosDAO")
public class ProcesoMAVGenerarEnviosDAOIbatis extends BaseDAOiBatis implements
	ProcesoMAVGenerarEnviosDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVGenerarEnviosDAO#executeGenerarEnvios(java.util.Map)
	 */
	public void executeGenerarEnvios(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAVSQL.executeGenerarEnvios", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#existeMAVEnvios(java.util.Map)
	 */
	public boolean existeMAVEnvios(Map criteria) {
		Integer result = (Integer) getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAVSQL.getExisteMAVEnvios", criteria);

		if(result.intValue()>0)
    		return true;
    	else
    		return false;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVGenerarEnviosDAO#getExisteMatrizFacturacion(java.util.Map)
	 */
	public boolean getExisteMatrizFacturacion(Map criteria) {
		Integer result = (Integer) getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAVSQL.getExisteMatrizFacturacion", criteria);

		if(result.intValue()>0)
    		return true;
    	else
    		return false;
    }
	
}

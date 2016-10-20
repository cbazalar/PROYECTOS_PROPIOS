package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaNivelRiesgoDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoMAECargaNivelRiesgoDAO")
public class ProcesoMAECargaNivelRiesgoDAOIbatis extends BaseDAOiBatis implements
	ProcesoMAECargaNivelRiesgoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAECargaNivelRiesgoDAO#insertCargaNivelRiesgo(java.util.Map)
	 */
	public void insertCargaNivelRiesgo(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.ProcesosMAESQL.insertCargaNivelRiesgo", params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaNivelRiesgoDAO#executeValidarCargaNivelRiesgo(java.util.Map)
	 */
	public void executeValidarCargaNivelRiesgo(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeValidarCargaNivelRiesgo", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaNivelRiesgoDAO#getCargaNivelRiesgoList(java.util.Map)
	 */
	public List getCargaNivelRiesgoList(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getCargaNivelRiesgoList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaNivelRiesgoDAO#executeActualizarCargaNivelRiesgo(java.util.Map)
	 */
	public void executeActualizarCargaNivelRiesgo(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarCargaNivelRiesgo", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaNivelRiesgoDAO#getNumeroCarga()
	 */
	public String getNumeroCarga() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getNumeroCargaRiesgo");
	}

}

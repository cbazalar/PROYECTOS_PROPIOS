package biz.belcorp.ssicc.dao.spusicc.mic.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mic.ProcesoMICGeneracionAptasDAO;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.procesoMICGeneracionAptasDAO")
public class ProcesoMICGeneracionAptasDAOIbatis extends BaseDAOiBatis implements ProcesoMICGeneracionAptasDAO {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.micro.dao.ProcesoMICGeneracionAptasDAO#executeGeneracionAptasMicroSeguros(java.util.Map)
	 */
	public void executeGeneracionAptasMicroSeguros(Map map) {
		getSqlMapClientTemplate()
		.update(
				"spusicc.microseguro.ProcesoMICSQL.executeGeneracionAptasMicroSeguros",
				map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.ProcesoMICGeneracionAptasDAO#getParametrosMicroSeguro()
	 */
	public Map getParametrosMicroSeguro() {
		Map map =(Map)getSqlMapClientTemplate().queryForObject("spusicc.microseguro.ProcesoMICSQL.getParametrosMicroSeguro");
		return map;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.ProcesoMICGeneracionAptasDAO#validaEnvioMicroseguros()
	 */
	public Integer validaEnvioMicroseguros(String tipo) {
		Integer result =(Integer)getSqlMapClientTemplate().queryForObject("spusicc.microseguro.ProcesoMICSQL.validaEnvioMicroseguros",tipo);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.ProcesoMICGeneracionAptasDAO#updateParametros(java.util.Map)
	 */
	public void updateParametros(Map micPrams) {
		getSqlMapClientTemplate().update("spusicc.microseguro.ProcesoMICSQL.updateParametros",
										micPrams);
		
	}

	
}

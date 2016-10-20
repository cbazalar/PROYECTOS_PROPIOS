/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCalculoVariablesNivelSeccionCampaniaDAO;

/**
 * @author peextllizana
 *
 */
@Repository("spusicc.procesoCOMCalculoVariablesNivelSeccionCampaniaDAO")
public class ProcesoCOMCalculoVariablesNivelSeccionCampaniaDAOIbatis extends
		BaseDAOiBatis implements
		ProcesoCOMCalculoVariablesNivelSeccionCampaniaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.ProcesoCOMCalculoVariablesNivelSeccionCampaniaDAO#executeCalculoVariablesNivelSeccionCampania(java.util.Map)
	 */
	public void executeCalculoVariablesNivelSeccionCampania(Map params) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.executeCalculoVariablesNivelSeccionCampania", params);

	}

}

package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJCalculoVariablesSeccionCampanaDAO;

/**
 * @author Jesse James Rios Franco
 *
 */

public class ProcesoPEJCalculoVariablesSeccionCampanaDAOiBatis extends BaseDAOiBatis implements ProcesoPEJCalculoVariablesSeccionCampanaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCalculoVariablesSeccionCampaaDAO#executeProcesoPEJCalculoVariablesSeccionCampaa(java.util.Map)
	 */
	public void executeProcesoPEJCalculoVariablesSeccionCampaña(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeProcesoPEJCalculoVariablesSeccionCampaña", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCalculoVariablesSeccionCampaaDAO#validarRegionProcesada(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Integer validarRegionProcesada(String codigoPais,String codigoPeriodo, String codigoRegion) {
		
		Map criteria = new HashMap();
		criteria.put("codigoPais",codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoRegion", codigoRegion);
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.validarRegionProcesada", criteria);
	}
}
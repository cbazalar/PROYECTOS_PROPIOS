package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJCalculaComisionRecuperacionDAO;

/**
 * @author Jesse James Rios Franco
 *
 */

public class ProcesoPEJCalculaComisionRecuperacionDAOiBatis extends BaseDAOiBatis implements ProcesoPEJCalculaComisionRecuperacionDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCalculaComisionRecuperacionDAO#validaRegionComisionCalculada(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Integer validaRegionComisionCalculada(String codigoPais,String codigoPeriodo, String codigoRegion) {
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoRegion", codigoRegion);
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.validaRegionComisionCalculada", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCalculaComisionRecuperacionDAO#executeProcesoPEJCalculaComisionRecuperacion(java.util.Map)
	 */
	public void executeProcesoPEJCalculaComisionRecuperacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeProcesoPEJCalculaComisionRecuperacion", params);
	}
}
package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJGraduacionDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoPEJGraduacionDAO")
public class ProcesoPEJGraduacionDAOiBatis extends BaseDAOiBatis implements ProcesoPEJGraduacionDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJGraduacionDAO#getEtapas()
	 */
	public List getEtapas() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getEtapas");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJGraduacionDAO#validaEtapaProcesada(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Integer validaEtapaProcesada(String codigoPais,String anioInicial,String nroEtapa) {
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("anioInicial", anioInicial);
		criteria.put("nroEtapa", nroEtapa);
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.validaEtapaProcesada", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJGraduacionDAO#executeProcesoPEJGraduacion(java.util.Map)
	 */
	public void executeProcesoPEJGraduacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeProcesoPEJGraduacion", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJGraduacionDAO#executeProcesoPEJCalcularAvanceGestion(java.util.Map)
	 */
	public void executeProcesoPEJCalcularAvanceGestion(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeProcesoPEJCalcularAvanceGestion", params);
	}
}
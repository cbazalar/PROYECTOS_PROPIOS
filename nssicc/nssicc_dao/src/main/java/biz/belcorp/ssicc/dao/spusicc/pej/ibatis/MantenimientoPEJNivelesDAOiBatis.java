package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.MantenimientoPEJNivelesDAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.NivelEjecutiva;

/**
 * @author Jesse James Rios Franco
 *
 */

public class MantenimientoPEJNivelesDAOiBatis extends BaseDAOiBatis implements MantenimientoPEJNivelesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJNivelesDAO#getNivelesList(java.util.Map)
	 */
	public List getNivelesList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getNivelesList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJNivelesDAO#deleteNivelesEjecutivas(java.util.Map)
	 */
	public void deleteNivelesEjecutivas(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosPEJSQL.deleteNivelesEjecutivas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJNivelesDAO#getNivelEjecutiva(java.lang.String, java.lang.String)
	 */
	public NivelEjecutiva getNivelEjecutiva(String codigoPais, String id) {
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoNivel", id);
		
		return (NivelEjecutiva)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getNivelEjecutiva", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJNivelesDAO#insertNivelEjecutiva(biz.belcorp.ssicc.spusicc.pej.model.NivelEjecutiva)
	 */
	public void insertNivelEjecutiva(NivelEjecutiva nivelEjecutiva) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertNivelEjecutiva", nivelEjecutiva);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJNivelesDAO#updateNivelEjecutiva(biz.belcorp.ssicc.spusicc.pej.model.NivelEjecutiva)
	 */
	public void updateNivelEjecutiva(NivelEjecutiva nivelEjecutiva) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updateNivelEjecutiva", nivelEjecutiva);
	}
}
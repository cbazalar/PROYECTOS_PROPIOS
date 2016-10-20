package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaDespachoConcursoRxPDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoINCCargaDespachoConcursoRxPDAO")
public class ProcesoINCCargaDespachoConcursoRxPDAOIbatis extends BaseDAOiBatis implements
		ProcesoINCCargaDespachoConcursoRxPDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaDespachoConcursoRxPDAO#getListConcursoVigentesRxP()
	 */
	public List getListConcursoVigentesRxP() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListConcursoVigentesRxP");	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaDespachoConcursoRxPDAO#validarDespachoConcursoRxP()
	 */
	public String validarDespachoConcursoRxP(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.ProcesoINCSQL.getValidarDespachoConcursoRxP", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaDespachoConcursoRxPDAO#validarnCodigoVentaRxP(java.util.Map)
	 */
	public List validarCodigoVentaRxP(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getValidarCodigoVentaRxP", criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaDespachoConcursoRxPDAO#insertDespachoConcursoRxP(java.util.Map)
	 */
	public void insertDespachoConcursoRxP(Map params) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.ProcesoINCSQL.insertDespachoConcursoRxP", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaDespachoConcursoRxPDAO#updateDespachoConcursoRxP(java.util.Map)
	 */
	public void updateDespachoConcursoRxP(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.updateDespachoConcursoRxP", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaDespachoConcursoRxPDAO#insertDespachoConcursoRxPDetalle(java.util.Map)
	 */
	public void insertDespachoConcursoRxPDetalle(Map params) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.ProcesoINCSQL.insertDespachoConcursoRxPDetalle", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaDespachoConcursoRxPDAO#deleteDespachoConcursoRxPDetalle(java.util.Map)
	 */
	public void deleteDespachoConcursoRxPDetalle(Map params) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.ProcesoINCSQL.deleteDespachoConcursoRxPDetalle", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaDespachoConcursoRxPDAO#getDespachoConcursoRxPDetalle(java.util.Map)
	 */
	public List getDespachoConcursoRxPDetalle(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getDespachoConcursoRxPDetalle", criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaDespachoConcursoRxPDAO#getDespachoConcursoRxP(java.util.Map)
	 */
	public List getDespachoConcursoRxP(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getDespachoConcursoRxP", criteria);	
	}
	
}

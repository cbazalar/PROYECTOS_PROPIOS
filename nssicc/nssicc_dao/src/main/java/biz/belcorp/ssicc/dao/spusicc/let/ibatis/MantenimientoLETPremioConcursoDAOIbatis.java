package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETPremioConcursoDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoLETPremioConcursoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.mantenimientoLETPremioConcursoDAO")
public class MantenimientoLETPremioConcursoDAOIbatis extends BaseDAOiBatis 
	implements MantenimientoLETPremioConcursoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroConcursoDAO#deletePremioCampania(java.util.Map)
	 */
	public void deletePremioConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoDAOIbatis - deletePremioConcurso(java.util.Map)");
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deletePremioConcurso", criteria);
		log.info("Salio a MantenimientoLETPremioConcursoDAOIbatis - deletePremioConcurso(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroConcursoDAO#getPremioCampaniaList(java.util.Map)
	 */
	public List getPremioConcursoList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoDAOIbatis - getPremioConcursoList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getPremioConcursoList", criteria);
		log.info("Salio a MantenimientoLETPremioConcursoDAOIbatis - getPremioConcursoList(java.util.Map) - Resultado:"+lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroConcursoDAO#insertPremioCampania(java.util.Map)
	 */
	public void insertPremioConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoDAOIbatis - insertPremioConcurso(java.util.Map)");
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertPremioConcurso", criteria);
		log.info("Salio a MantenimientoLETPremioConcursoDAOIbatis - insertPremioConcurso(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDPremioConcursoDAO#getNivelConcursoRangoList(java.util.Map)
	 */
	public List getNivelConcursoRangoList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoDAOIbatis - getNivelConcursoRangoList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getNivelConcursoRangoList", criteria);
		log.info("Salio a MantenimientoLETPremioConcursoDAOIbatis - getNivelConcursoRangoList(java.util.Map) - Resultado:"+lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETPremioConcursoDAO#getValidaPremioConcursoExiste(java.util.Map)
	 */
	public String getValidaPremioConcursoExiste(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoDAOIbatis - getValidaPremioConcursoExiste(java.util.Map)");
		String resultado = (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaPremioConcursoExiste", criteria);
		log.info("Entro a MantenimientoLETPremioConcursoDAOIbatis - getValidaPremioConcursoExiste(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}
	
	
	
}
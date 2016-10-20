package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETPremioCampaniaDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoLETPremioCampaniaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.mantenimientoLETPremioCampaniaDAO")
public class MantenimientoLETPremioCampaniaDAOIbatis extends BaseDAOiBatis 
	implements MantenimientoLETPremioCampaniaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroConcursoDAO#deletePremioCampania(java.util.Map)
	 */
	public void deletePremioCampania(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaDAOIbatis - deletePremioCampania(java.util.Map)");
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deletePremioCampania", criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaDAOIbatis - deletePremioCampania(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroConcursoDAO#getNivelRangoList(java.util.Map)
	 */
	public List getNivelRangoList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaDAOIbatis - getNivelRangoList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getNivelRangoList", criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaDAOIbatis - getNivelRangoList(java.util.Map) - Resultado:"+lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroConcursoDAO#getNumeroConcursoList(java.util.Map)
	 */
	public List getNumeroConcursoList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaDAOIbatis - getNumeroConcursoList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getNumConcursoList", criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaDAOIbatis - getNumeroConcursoList(java.util.Map) - Resultado:"+lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroConcursoDAO#getPremioCampaniaList(java.util.Map)
	 */
	public List getPremioCampaniaList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaDAOIbatis - getPremioCampaniaList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getPremioCampaniaList", criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaDAOIbatis - getPremioCampaniaList(java.util.Map) - Resultado:"+lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroConcursoDAO#getValidaCodigoVenta(java.util.Map)
	 */
	public int getValidaCodigoVenta(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaDAOIbatis - getValidaCodigoVenta(java.util.Map)");
		int lista = ((Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaCodigoVenta", criteria)).intValue();
		log.info("Salio a MantenimientoLETPremioCampaniaDAOIbatis - getValidaCodigoVenta(java.util.Map) - Resultado:"+lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroConcursoDAO#insertPremioCampania(java.util.Map)
	 */
	public void insertPremioCampania(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaDAOIbatis - insertPremioCampania(java.util.Map)");
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertPremioCampania", criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaDAOIbatis - insertPremioCampania(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETPremioCampaniaDAO#getValidaPremioCampaniaExiste(java.util.Map)
	 */
	public String getValidaPremioCampaniaExiste(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaDAOIbatis - getValidaPremioCampaniaExiste(java.util.Map)");
		String resultado = (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaPremioCampaniaExiste", criteria);
		log.info("Entro a MantenimientoLETPremioCampaniaDAOIbatis - getValidaPremioCampaniaExiste(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}
	
	
}
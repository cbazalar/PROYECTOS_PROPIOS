package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPRYProyeccionFaltanteDiaDAO;
import biz.belcorp.ssicc.dao.spusicc.proyeccion.model.PorcentajeFaltante;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Repository("spusicc.procesoPRYProyeccionFaltanteDiaDAO")
public class ProcesoPRYProyeccionFaltanteDiaDAOiBatis extends BaseDAOiBatis
 implements ProcesoPRYProyeccionFaltanteDiaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#executeProyeccionFaltante(java.util.Map)
	 */
	public void executeProyeccionFaltante(Map criteria) {
		log.debug(criteria.get("codigoPais"));
		getSqlMapClientTemplate().update("spusicc.ProcesosPRYSQL.executeProyeccionFaltante", criteria);
        return;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#existeIndicadorValorCritico(java.util.Map)
	 */
	public Integer existeIndicadorValorCritico(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
		     queryForObject("spusicc.ProcesosPRYSQL.existeIndicadorValorCritico", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#existeIndicadorValorCriticoVersion2(java.util.Map)
	 */
	public Integer existeIndicadorValorCriticoVersion2(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
		     queryForObject("spusicc.ProcesosPRYSQL.existeIndicadorValorCriticoVersion2", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#getProyeccionFaltanteGrupo(java.util.Map)
	 */
	public List getProyeccionFaltanteGrupo(Map criteria) {
		return (List)getSqlMapClientTemplate().
		     queryForList("spusicc.ProcesosPRYSQL.getProyeccionFaltanteGrupo", criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#getProyeccionFaltanteProducto(java.util.Map)
	 */
	public List getProyeccionFaltanteProducto(Map criteria) {
		return (List)getSqlMapClientTemplate().
		     queryForList("spusicc.ProcesosPRYSQL.getProyeccionFaltanteProducto", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#getProyeccionFaltanteProductoTodos(java.util.Map)
	 */
	public List getProyeccionFaltanteProductoTodos(Map criteria) {
		return (List)getSqlMapClientTemplate().
		     queryForList("spusicc.ProcesosPRYSQL.getProyeccionFaltanteProductoTodos", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#getPeriodoActual(java.util.Map)
	 */
	public String getPeriodoActual(Map criteria) {
		return (String)getSqlMapClientTemplate().
		     queryForObject("spusicc.ProcesosPRYSQL.getPeriodoActual", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#getTotalOC(java.util.Map)
	 */
	public Integer getTotalOC(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
		     queryForObject("spusicc.ProcesosPRYSQL.getTotalOC", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#getMaximoVersionProyeccionFaltanteDia(java.util.Map)
	 */
	public String getMaximoVersionProyeccionFaltanteDia(Map criteria) {
		return (String)getSqlMapClientTemplate().
		     queryForObject("spusicc.ProcesosPRYSQL.getMaximoVersionProyeccionFaltanteDia", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#getPorcentajeFaltante(biz.belcorp.ssicc.spusicc.ventas.model.PorcentajeFaltante)
	 */
	public List getPorcentajeFaltante(PorcentajeFaltante porcentaje) {
		return (List)getSqlMapClientTemplate().
	     queryForList("spusicc.ProcesosPRYSQL.getPorcentajeFaltante", porcentaje);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#updatePorcentajeFaltante(biz.belcorp.ssicc.spusicc.proyeccion.model.PorcentajeFaltante, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePorcentajeFaltante(PorcentajeFaltante porcentaje, Usuario usuario){
		getSqlMapClientTemplate().update("spusicc.ProcesosPRYSQL.updatePorcentajeFaltante", porcentaje);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#insertPorcentajeFaltante(biz.belcorp.ssicc.spusicc.proyeccion.model.PorcentajeFaltante, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPorcentajeFaltante(PorcentajeFaltante porcentaje, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.ProcesosPRYSQL.insertPorcentajeFaltante", porcentaje);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#deletePorcentajeFaltante(biz.belcorp.ssicc.spusicc.proyeccion.model.PorcentajeFaltante, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deletePorcentajeFaltante(PorcentajeFaltante porcentaje, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.ProcesosPRYSQL.deletePorcentajeFaltante", porcentaje);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoPRYProyeccionFaltanteDiaDAO#getDevuelveDescripcionPeriodo(java.util.Map)
	 */
	public String getDevuelveDescripcionPeriodo(Map criteria) {
		return (String)getSqlMapClientTemplate().
	     		queryForObject("spusicc.ProcesosPRYSQL.getDevuelveDescripcionPeriodo", criteria);
		
	}
}

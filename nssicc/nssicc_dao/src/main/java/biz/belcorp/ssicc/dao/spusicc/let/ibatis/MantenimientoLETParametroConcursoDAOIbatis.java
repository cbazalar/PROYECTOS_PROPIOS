package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETParametroConcursoDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoLETParametroConcursoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.mantenimientoLETParametroConcursoDAO")
public class MantenimientoLETParametroConcursoDAOIbatis extends BaseDAOiBatis implements MantenimientoLETParametroConcursoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getParametroConcursoList(java.util.Map)
	 */
	public List getParametroConcursoList(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - getParametroConcursoList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getParametroConcursoList", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - getParametroConcursoList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getNivelCampaniaList()
	 */
	public List getNivelCampaniaList() {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - getNivelCampaniaList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getNivelCampaniaList");	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - getNivelCampaniaList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getNivelConcursoList()
	 */
	public List getNivelConcursoList() {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - getNivelConcursoList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getNivelConcursoList");	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - getNivelConcursoList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getRangoPedidoList()
	 */
	public List getRangoPedidoList() {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - getRangoPedidoList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getRangoPedidoList");	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - getRangoPedidoList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#insertNivelCampania(java.util.Map)
	 */
	public void insertNivelCampania(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - insertNivelCampania(java.util.Map)");
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertNivelCampania", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - insertNivelCampania(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#insertNivelConcurso(java.util.Map)
	 */
	public void insertNivelConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - insertNivelConcurso(java.util.Map)");
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertNivelConcurso", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - insertNivelConcurso(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#insertParametroConcurso(java.util.Map)
	 */
	public void insertParametroConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - insertParametroConcurso(java.util.Map)");
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertParametroConcurso", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - insertParametroConcurso(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#insertRangoPedido(java.util.Map)
	 */
	public void insertRangoPedido(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - insertRangoPedido(java.util.Map)");
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertRangoPedido", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - insertRangoPedido(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getCodigoConcurso(java.util.Map)
	 */
	public String getCodigoConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - getCodigoConcurso(java.util.Map)");
		String codigo = (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getCodigoConcurso", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - getCodigoConcurso(java.util.Map) + Resultado:"+codigo);
		return codigo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#deleteNivelCampania(java.util.Map)
	 */
	public void deleteNivelCampania(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - deleteNivelCampania(java.util.Map)");
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteNivelCampania", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - deleteNivelCampania(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#deleteNivelConcurso(java.util.Map)
	 */
	public void deleteNivelConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - deleteNivelConcurso(java.util.Map)");
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteNivelConcurso", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - deleteNivelConcurso(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#deleteRangoPedido(java.util.Map)
	 */
	public void deleteRangoPedido(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - deleteRangoPedido(java.util.Map)");
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteRangoPedido", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - deleteRangoPedido(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getNivelCampaniaConsult(java.util.Map)
	 */
	public List getNivelCampaniaConsult(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - getNivelCampaniaConsult(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getNivelCampaniaConsult", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - getNivelCampaniaConsult(java.util.Map) + Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getNivelConcursoConsult(java.util.Map)
	 */
	public List getNivelConcursoConsult(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - getNivelConcursoConsult(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getNivelConcursoConsult", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - getNivelConcursoConsult(java.util.Map) + Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getParametroConcursoConsult(java.util.Map)
	 */
	public List getParametroConcursoConsult(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - getParametroConcursoConsult(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getParametroConcursoConsult", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - getParametroConcursoConsult(java.util.Map) + Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getRangoPedidoConsult(java.util.Map)
	 */
	public List getRangoPedidoConsult(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - getRangoPedidoConsult(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getRangoPedidoConsult", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - getRangoPedidoConsult(java.util.Map) + Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#updateParametroConcurso(java.util.Map)
	 */
	public void updateParametroConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoDAOIbatis - updateParametroConcurso(java.util.Map)");
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.updateParametroConcurso", criteria);	
		log.info("Salio a MantenimientoLETParametroConcursoDAOIbatis - updateParametroConcurso(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getValidaPeriodoExiste(java.util.Map)
	 */
	public String getValidaPeriodoExiste(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getValidaPeriodoExiste(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaPeriodoExiste", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getValidaPeriodoExiste(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getValidaNivelCampaEditable(java.util.Map)
	 */
	public String getValidaNivelCampaEditable(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getValidaNivelCampaEditable(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaNivelCampaEditable", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getValidaNivelCampaEditable(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getValidaNivelConcuEditable(java.util.Map)
	 */
	public String getValidaNivelConcuEditable(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getValidaNivelConcuEditable(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaNivelConcuEditable", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getValidaNivelConcuEditable(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getValidaPeriodoEditable(java.util.Map)
	 */
	public String getValidaPeriodoEditable(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getValidaPeriodoEditable(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaPeriodoEditable", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getValidaPeriodoEditable(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getValidaRangoPedidoEditable(java.util.Map)
	 */
	public String getValidaRangoPedidoEditable(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getValidaRangoPedidoEditable(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaRangoPedidoEditable", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getValidaRangoPedidoEditable(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getEliminaParametroConcursoLider(java.util.Map)
	 */
	public void getEliminaParametroConcursoLider(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getEliminaParametroConcursoLider(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.getEliminaParametroConcursoLider", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getEliminaParametroConcursoLider(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getEliminaParametroNivelCampania(java.util.Map)
	 */
	public void getEliminaParametroNivelCampania(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getEliminaParametroNivelCampania(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.getEliminaParametroNivelCampania", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getEliminaParametroNivelCampania(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getEliminaParametroNivelConcurso(java.util.Map)
	 */
	public void getEliminaParametroNivelConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getEliminaParametroNivelConcurso(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.getEliminaParametroNivelConcurso", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getEliminaParametroNivelConcurso(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getEliminaParametroRangoPedido(java.util.Map)
	 */
	public void getEliminaParametroRangoPedido(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getEliminaParametroRangoPedido(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.getEliminaParametroRangoPedido", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getEliminaParametroRangoPedido(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getTipoVariablesList()
	 */
	public List getTipoVariablesList() {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getTipoVariablesList");
	}
	
	/* INI JJ PER-SiCC-2012-0201 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#getDescripcionNivelesCampaniaList()
	 */
	public List getDescripcionNivelesCampaniaList() {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getDescripcionNivelesCampaniaList");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETParametroConcursoDAO#updateNivelCampania(java.util.Map)
	 */
	public void updateNivelCampania(Map criteria_incremento) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateNivelCampania", criteria_incremento);
	}
	/* FIN JJ PER-SiCC-2012-0201 */
}
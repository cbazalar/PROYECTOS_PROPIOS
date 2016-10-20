package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ConsultaLETResultadosConcursosDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.consultaLETResultadosConcursosDAO")
public class ConsultaLETResultadosConcursosDAOiBatis extends BaseDAOiBatis implements ConsultaLETResultadosConcursosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getParametroConcursoDescCompletaList(java.util.Map)
	 */
	public List getParametroConcursoDescCompletaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getParametroConcursoDescCompletaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getConsultaLETResultadosConcursos(java.util.Map)
	 */
	public List getConsultaLETResultadosConcursos(Map criterios) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getConsultaLETResultadosConcursos", criterios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getResultadoConcursoByCriteria(java.util.Map)
	 */
	public Map getResultadoConcursoByCriteria(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getResultadoConcursoByCriteria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getHistoricoClasificacionByCriteria(java.util.Map)
	 */
	public Map getHistoricoClasificacionByCriteria(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getHistoricoClasificacionByCriteria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getUltimaSeccionCargoConcursoByCriteria(java.util.Map)
	 */
	public Map getUltimaSeccionCargoConcursoByCriteria(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getUltimaSeccionCargoConcursoByCriteria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getGestionSeccionActualByCriteria(java.util.Map)
	 */
	public Map getGestionSeccionActualByCriteria(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getGestionSeccionActualByCriteria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getDetalleResultadosConcursosListByCriteria(java.util.Map)
	 */
	public List getDetalleResultadosConcursosListByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getDetalleResultadosConcursosListByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getRecomendacionesListByCriteria(java.util.Map)
	 */
	public List getRecomendacionesListByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getRecomendacionesListByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getTotalRecomendadasByCriteria(java.util.Map)
	 */
	public Integer getTotalRecomendadasByCriteria(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getTotalRecomendadasByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getTotalIngresosEfectivos(java.util.Map)
	 */
	public Integer getTotalIngresosEfectivos(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getTotalIngresosEfectivos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ConsultaLETResultadosConcursosDAO#getRotacionesListByCriteria(java.util.Map)
	 */
	public List getRotacionesListByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getRotacionesListByCriteria", criteria);
	}
}
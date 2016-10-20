package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCargaPedidosObjetivosRezonificacionDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoLETCargaPedidosObjetivosRezonificacionDAO")
public class ProcesoLETCargaPedidosObjetivosRezonificacionDAOiBatis extends BaseDAOiBatis implements ProcesoLETCargaPedidosObjetivosRezonificacionDAO{

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosRezonificacionDAO#getValidaExisteConcursoByCodigoPeriodo(java.util.Map)
	 */
	public Integer getValidaExisteConcursoByCodigoPeriodo(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getValidaExisteConcursoByCodigoPeriodo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosRezonificacionDAO#getZonasRezonificacion(java.util.Map)
	 */
	public List getZonasRezonificacion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.ProcesosLETSQL.getZonasRezonificacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosRezonificacionDAO#getRegionCerradaByCodigoZona(java.util.Map)
	 */
	public String getRegionCerradaByCodigoZona(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getRegionCerradaByCodigoZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosRezonificacionDAO#insertZonasRezonificadasGTT(java.util.Map)
	 */
	public void insertZonasRezonificadasGTT(Map parametros) {
		getSqlMapClientTemplate().insert("spusicc.let.ProcesosLETSQL.insertZonasRezonificadasGTT", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosRezonificacionDAO#executeCargaPedidosObjetivosRezonificacion(java.util.Map)
	 */
	public void executeCargaPedidosObjetivosRezonificacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeCargaPedidosObjetivosRezonificacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosRezonificacionDAO#getZonaNuevaRezonificada(java.util.Map)
	 */
	public Integer getZonaNuevaRezonificada(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getZonaNuevaRezonificada", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosRezonificacionDAO#getZonaNueva(java.util.Map)
	 */
	public Integer getZonaNueva(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getZonaNueva", criteria);
	}
}
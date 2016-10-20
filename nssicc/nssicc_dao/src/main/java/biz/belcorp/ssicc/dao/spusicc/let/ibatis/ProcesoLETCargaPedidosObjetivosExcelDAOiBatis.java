package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCargaPedidosObjetivosExcelDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoLETCargaPedidosObjetivosExcelDAO")
public class ProcesoLETCargaPedidosObjetivosExcelDAOiBatis extends BaseDAOiBatis implements ProcesoLETCargaPedidosObjetivosExcelDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#getExisteSeccionByZona(java.util.Map)
	 */
	public Integer getExisteSeccionByZona(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getExisteSeccionByZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#getExisteZona(java.util.Map)
	 */
	public Integer getExisteZona(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getExisteZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#validaRegionCerrada(java.util.Map)
	 */
	public Integer validaRegionCerrada(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.validaRegionCerrada", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#getPedidosObjetivosAll()
	 */
	public List getPedidosObjetivosAll() {
		return getSqlMapClientTemplate().queryForList("spusicc.let.ProcesosLETSQL.getPedidosObjetivosAll");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#getCodigoConcursoByPeriodo(java.util.Map)
	 */
	public String getCodigoConcursoByPeriodo(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getCodigoConcursoByPeriodo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#insertPedidoObjetivo(java.util.Map)
	 */
	public void insertPedidoObjetivo(Map temp) {
		getSqlMapClientTemplate().insert("spusicc.let.ProcesosLETSQL.insertPedidoObjetivo", temp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#updatePedidoObjetivo(java.util.Map)
	 */
	public void updatePedidoObjetivo(Map temp) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.updatePedidoObjetivo", temp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#executeCalculoMetasLideresCampana(java.util.Map)
	 */
	public void executeCalculoMetasLideresCampana(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeCalculoMetasLideresCampana", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#getZonaNuevaRezonificada(java.util.Map)
	 */
	public Integer getZonaNuevaRezonificada(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getZonaNuevaRezonificada", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#getSeccioNueva(java.util.Map)
	 */
	public Integer getSeccioNueva(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getSeccioNueva", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCargaPedidosObjetivosExcelDAO#getCampanaFinConcursoByCodigoConcurso(java.lang.String)
	 */
	public String getCampanaFinConcursoByCodigoConcurso(String codigoConcurso){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getCampanaFinConcursoByCodigoConcurso", codigoConcurso);
	}
}
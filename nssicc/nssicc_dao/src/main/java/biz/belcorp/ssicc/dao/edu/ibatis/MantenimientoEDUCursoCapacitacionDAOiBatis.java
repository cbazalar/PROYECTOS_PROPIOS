package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.MantenimientoEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.dao.edu.model.CursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.CursoCapacitacionAmbito;
import biz.belcorp.ssicc.dao.edu.model.DespachoProductoCurso;
import biz.belcorp.ssicc.dao.edu.model.EquivalenciaMatrizServicio;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPedido;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPlanillaInstructora;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ZonaCursoCapacitacion;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * @author peextrvela
 *
 */
@Repository("edu.mantenimientoEDUCursoCapacitacionDAO")
public class MantenimientoEDUCursoCapacitacionDAOiBatis extends 
	BaseDAOiBatis implements MantenimientoEDUCursoCapacitacionDAO{

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacion)
	 */
	public CursoCapacitacion getCursoCapacitacion(CursoCapacitacion cursoCapacitacion) {
		return (CursoCapacitacion) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUSQL.getCursoCapacitacion",cursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getCursosCapacitacionByCriteria(java.util.Map)
	 */
	public List getCursosCapacitacionByCriteria(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getCursosCapacitacionByCriteria",
				criterios);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getProductosxCurso(biz.belcorp.ssicc.edu.dao.model.DespachoProducto)
	 */
	public List getProductosxCurso(DespachoProductoCurso despachoProducto) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getProductosxCurso",
				despachoProducto);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getProductoxCurso(biz.belcorp.ssicc.edu.dao.model.DespachoProducto)
	 */
	public DespachoProductoCurso getProductoxCurso(DespachoProductoCurso despachoProducto) {
		return (DespachoProductoCurso) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUSQL.getProductoxCurso",despachoProducto);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCursoCapacitacion(CursoCapacitacion cursoCapacitacion, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertCursoCapacitacion",
				cursoCapacitacion);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertDespachoProducto(biz.belcorp.ssicc.edu.dao.model.DespachoProducto, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertDespachoProducto",
				despachoProducto);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeDespachoProducto(biz.belcorp.ssicc.edu.dao.model.DespachoProducto, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removeDespachoProducto",
				despachoProducto);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeDespachoProducto(biz.belcorp.ssicc.edu.dao.model.DespachoProducto, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeLogicoDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removeLogicoDespachoProducto",
				despachoProducto);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateRemoveDespachoProducto(biz.belcorp.ssicc.edu.dao.model.DespachoProducto, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRemoveCursoCapacitacion(CursoCapacitacion cursoCapacitacion, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateRemoveCursoCapacitacion",
				cursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCursoCapacitacion(CursoCapacitacion cursoCapacitacion, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateCursoCapacitacion",
				cursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateDespachoProducto(biz.belcorp.ssicc.edu.dao.model.DespachoProducto, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateDespachoProducto",
				despachoProducto);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getMaxCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.DespachoProductoCurso)
	 */
	public String getMaxCursoCapacitacion(CursoCapacitacion cursoCapacitacion) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUSQL.getMaxCursoCapacitacion",
				cursoCapacitacion);
		if (codigo==null) codigo="00";
		return codigo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getRegion(biz.belcorp.ssicc.edu.dao.model.Region)
	 */
	public List getRegion(RegionCursoCapacitacion region) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getRegion",
				region);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getZona(biz.belcorp.ssicc.edu.dao.model.ZonaCursoCapacitacion)
	 */
	public List getZona(ZonaCursoCapacitacion zona) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getZona",
				zona);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getZonaByRegionSelected(java.util.Map)
	 */
	public List getZonaByRegionSelected(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getZonaByRegionSelected",
				criterios);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getCursoCapacitacionAmbito(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacionAmbito)
	 */
	public List getCursoCapacitacionAmbito(CursoCapacitacionAmbito cursoCapacitacionAmbito) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getCursoCapacitacionAmbito",
				cursoCapacitacionAmbito);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertCursoCapacitacionAmbito(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacionAmbito, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCursoCapacitacionAmbito(CursoCapacitacionAmbito cursoCapacitacionAmbito, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertCursoCapacitacionAmbito",
				cursoCapacitacionAmbito);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeCursoCapacitacionAmbito(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacionAmbito)
	 */
	public void removeCursoCapacitacionAmbito(CursoCapacitacionAmbito cursoCapacitacionAmbito) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removeCursoCapacitacionAmbito",
				cursoCapacitacionAmbito);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getRegionCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.RegionCursoCapacitacion)
	 */
	public List getRegionCursoCapacitacion(RegionCursoCapacitacion region) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getRegionCursoCapacitacion",
				region);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getZonaCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.ZonaCursoCapacitacion)
	 */
	public List getZonaCursoCapacitacion(ZonaCursoCapacitacion zonaCursoCapacitacion) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getZonaCursoCapacitacion",
				zonaCursoCapacitacion);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateRegion(biz.belcorp.ssicc.edu.dao.model.RegionCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRegion(RegionCursoCapacitacion region, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateRegion",
				region);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getHistoricoPedido(biz.belcorp.ssicc.edu.dao.model.HistoricoPedido)
	 */
	public List getHistoricoPedido(HistoricoPedido historicoPedido) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoPedido",
				historicoPedido);
		return resultado;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getServicioCapacitacionByCriteria(java.util.Map)
	 */
	public List getServicioCapacitacionByCriteria(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getServicioCapacitacion",
				criterios);
		return resultado;
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getEquivalenciaMatrizServicios(java.util.Map)
	 */
	public List getEquivalenciaMatrizServiciosByCriteria(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getEquivalenciaMatrizServiciosByCriteria",
				criterios);
		return resultado;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getEquivalenciaMatrizServiciosById(java.util.Map)
	 */
	public EquivalenciaMatrizServicio getEquivalenciaMatrizServiciosById(Map criterios) {
		EquivalenciaMatrizServicio resultado = (EquivalenciaMatrizServicio)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUSQL.getEquivalenciaMatrizServiciosByCriteria",
				criterios);
		return resultado;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertEquivalenciaMatrizServicio(biz.belcorp.ssicc.edu.dao.model.EquivalenciaMatrizServicio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEquivalenciaMatrizServicio(EquivalenciaMatrizServicio equivalenciaMatrizServicio, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertEquivalenciaMatrizServicio",
				equivalenciaMatrizServicio);
	}
	
	public Integer getValidaRangoCodigoServicio(Map criterios) {
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUSQL.getValidaRangoCodigoServicio", criterios);
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getRegionByInstructora(biz.belcorp.ssicc.edu.dao.model.RegionCursoCapacitacion)
	 */
	public List getRegionByInstructora(RegionCursoCapacitacion region) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getRegionByInstructora",
				region);
		return resultado;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updatePlanillaInstructoraByRegion(biz.belcorp.ssicc.edu.dao.model.HistoricoPlanillaInstructora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePlanillaInstructoraByRegion(HistoricoPlanillaInstructora historicoPlanillaInstructora, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updatePlanillaInstructoraByRegion",
				historicoPlanillaInstructora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getCursosParaPrereq(java.util.Map)
	 */
	public List getCursosParaPrereq(Map map) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getCursosParaPrereq",
				map);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertCursoCapacitacionPrerequisito(java.util.Map)
	 */
	public void insertCursoCapacitacionPrerequisito(Map map) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertCursoCapacitacionPrerequisito",
				map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#deleteCursoCapacitacionPrerequisito(java.util.Map)
	 */
	public void deleteCursoCapacitacionPrerequisito(Map criteria) {
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUSQL.deleteCursoCapacitacionPrerequisito",
				criteria);	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getPreRequsitos(java.util.Map)
	 */
	public List getPreRequsitos(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getPreRequsitos",
				criteria);
		return resultado;
	}

}

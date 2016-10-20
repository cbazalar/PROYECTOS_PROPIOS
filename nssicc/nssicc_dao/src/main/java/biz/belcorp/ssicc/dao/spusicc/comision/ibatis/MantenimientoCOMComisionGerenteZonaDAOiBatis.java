package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMComisionGerenteZonaDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ComisionPeriodoGerenteZona;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroTramoComision;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a>
 *
 */
@Repository("spusicc.mantenimientoCOMComisionGerenteZonaDAO")
public class MantenimientoCOMComisionGerenteZonaDAOiBatis extends BaseDAOiBatis 
    implements MantenimientoCOMComisionGerenteZonaDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getCursoCapacitacion(biz.belcorp.ssicc.spusicc.comision.dao.model.ComisionPeriodoGerenteZona)
	 */
	public ComisionPeriodoGerenteZona getComisionPeriodoGerenteZona(ComisionPeriodoGerenteZona bean) {
		return (ComisionPeriodoGerenteZona) this.getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getComisionPeriodoGerenteZona",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComisionPeriodoGerenteZonaByCriteria(java.util.Map)
	 */
	public List getComisionPeriodoGerenteZonaByCriteria(Map criteria) {
		List resultado = this.getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosCOMSQL.getComisionPeriodoGerenteZonaByCriteria",criteria);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComisionPeriodoLideresByCriteria(java.util.Map)
	 */
	public List getComisionPeriodoLideresByCriteria(Map criteria) {
		List resultado = this.getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosCOMSQL.getComisionPeriodoLideresByCriteria",criteria);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getDevuelveIDComision(java.util.Map)
	 */
	public Integer getDevuelveIDComision(Map criteria) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getDevuelveIDComision", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getIndicadoresComision(java.util.Map)
	 */
	public Map getIndicadoresComision(Map criteria) {
		return (HashMap) this.getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getIndicadoresComision", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getMantenimientoComisionPeriodoGerenteZonaByCriteria(java.util.Map)
	 */
	public List getMantenimientoComisionPeriodoGerenteZonaByCriteria(Map criteria) {
		List resultado = this.getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosCOMSQL.getMantenimientoComisionPeriodoGerenteZonaByCriteria",criteria);
		return resultado;
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getParametrosTramoComision(java.util.Map)
	 */
	public ParametroTramoComision getParametrosTramoComision(Map criteria) {
		// TODO Auto-generated method stub
		return (ParametroTramoComision) this.getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getParametrosTramoComision", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#updateComisionPeriodoGerenteZona(biz.belcorp.ssicc.spusicc.comision.dao.model.ComisionPeriodoGerenteZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateComisionPeriodoGerenteZona(ComisionPeriodoGerenteZona bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.updateComisionPeriodoGerenteZona", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComisionPeriodoGerenteZonaByCriteria(java.util.Map)
	 */
	public List getComisionPeriodoGerenteZonaEscalonadaByCriteria(Map criteria) {
		List resultado = this.getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosCOMSQL.getComisionPeriodoGerenteZonaEscalonadaByCriteria",criteria);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComisionPeriodoGerenteRegion(java.util.Map)
	 */
	public List getComisionPeriodoGerenteRegion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComisionPeriodoGerenteRegion",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComisionVal()
	 */
	public List getComisionVal(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComisionVal",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComisionRegionList(java.util.Map)
	 */
	public List getComisionRegionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComisionRegionList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComGerenteZonaList(java.util.Map)
	 */
	public List getComGerenteZonaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComGerenteZonaList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComArchivoNominaList(java.util.Map)
	 */
	public List getComArchivoNominaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComArchivoNominaList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComisionGerenteRegionObjetivo(java.util.Map)
	 */
	public List getComisionGerenteRegionObjetivo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComisionGerenteRegionObjetivo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComisionRetail(java.util.Map)
	 */
	public List getComisionRetail(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComisionRetail",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#getComisionBase(java.util.Map)
	 */
	public List getComisionBase(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComisionBase",criteria);
	}
}

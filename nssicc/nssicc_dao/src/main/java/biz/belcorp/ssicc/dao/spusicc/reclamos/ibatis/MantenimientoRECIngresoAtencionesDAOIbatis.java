/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECIngresoAtencionesDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;

/**
 * @author peextrramirez
 *
 */
@Repository("spusicc.mantenimientoRECIngresoAtencionesDAO")
public class MantenimientoRECIngresoAtencionesDAOIbatis extends BaseDAOiBatis implements  MantenimientoRECIngresoAtencionesDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getCodigoConsultora(java.util.Map)
	 */
	public String getCodigoConsultora(Map criteria){
		List l = getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCodigoConsultora", criteria);
		if(l.size()!=0)
			return l.get(0).toString();
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getReclamosPremio(java.util.Map)
	 */
	public List getReclamosPremio(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getReclamosPremio", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getReclamosMatriz(java.util.Map)
	 */
	public List getReclamosMatriz(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getReclamosMatriz", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#insertTemporalDetallesIngresoAtencion(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoDetalle)
	 */
	public void insertTemporalDetallesIngresoAtencion(BoletaRecojoDetalle boletaRecojoDetalle){
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertTemporalDetallesIngresoAtencion",boletaRecojoDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#procesarIngresoAtenciones(java.util.Map)
	 */
	public String procesarIngresoAtenciones(Map params){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.procesarIngresoAtenciones",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getCodigosParamIngreAtenc(java.util.Map)
	 */
	public List getCodigosParamIngreAtenc(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCodigosParamIngreAtenc",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getTipoOperacionList(java.util.Map)
	 */
	public List getTipoOperacionList(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getTipoOperacionList",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getNumeroLote()
	 */
	public String getNumeroLote() {
		return (String)getSqlMapClientTemplate().
						queryForObject("spusicc.reclamos.ReclamosSQL.getNumeroLoteAtenciones");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getAnulacion()
	 */
	public List getAnulacion(Map params) {
		return getSqlMapClientTemplate().
		             queryForList("spusicc.reclamos.ReclamosSQL.getAnulacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getAtencion()
	 */
	public List getAtencion(Map params) {
		return getSqlMapClientTemplate().
		      queryForList("spusicc.reclamos.ReclamosSQL.getAtencion", params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getDetalleAnulacion(java.util.Map)
	 */
	public List getDetalleAnulacion(Map map) {
		return getSqlMapClientTemplate().
	      queryForList("spusicc.reclamos.ReclamosSQL.getDetalleAnulacion",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getDetalleAtencion(java.util.Map)
	 */
	public List getDetalleAtencion(Map map) {
		return getSqlMapClientTemplate().
	      queryForList("spusicc.reclamos.ReclamosSQL.getDetalleAtencion",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#validarEliminacionLote(java.util.Map)
	 */
	public void validarEliminacionLote(Map map) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.validarEliminacionLote", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#eliminarLoteAtencion(java.util.Map)
	 */
	public void eliminarLoteAtencion(Map map) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.eliminarLoteAtencion", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getObtenerCampahniaActual(java.util.Map)
	 */
	public String getObtenerCampahniaActual(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getObtenerCampahniaActual", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getOidCargaCliente()
	 */
	public String getOidCargaCliente(){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getOidCargaCliente");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#insertaClienteFile(java.util.Map)
	 */
	public void insertaClienteFile(Map map) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertaClienteFile", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getCargaClienteList(java.util.Map)
	 */
	public List getCargaClienteList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCargaClienteList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getResumenCargaClienteList(java.util.Map)
	 */
	public List getResumenCargaClienteList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getResumenCargaClienteList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getOidCliente(java.util.Map)
	 */
	public String getOidCliente(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getOidCliente", criteria);
	}

	public void updateRegistroSinError(Map params) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.setRegistroSinError",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getCodigoConsultoraPorDocumentoIdentidad(java.util.Map)
	 */
	public String getCodigoConsultoraPorDocumentoIdentidad(Map criteria) {
		List l = getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCodigoConsultoraPorDocumentoIdentidad", criteria);
		if(l.size()!=0)
			return l.get(0).toString();
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getLongitudTipoDocumento(java.util.Map)
	 */
	public String getLongitudTipoDocumento(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getLongitudTipoDocumento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoAtencionesDAO#getObtenerCampahniaActiva(java.util.Map)
	 */
	public String getObtenerCampahniaActiva(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getObtenerCampahniaActiva", criteria);
	}
	
}
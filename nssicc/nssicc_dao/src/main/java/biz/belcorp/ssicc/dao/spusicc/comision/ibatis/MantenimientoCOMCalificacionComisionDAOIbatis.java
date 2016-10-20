/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMCalificacionComisionDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DatosComision;
import biz.belcorp.ssicc.dao.spusicc.comision.model.OrdenEstadisticoPorZona;


/**
 * @author peextllizana
 *
 */
@Repository("spusicc.mantenimientoCOMCalificacionComisionDAO")
public class MantenimientoCOMCalificacionComisionDAOIbatis extends
		BaseDAOiBatis implements MantenimientoCOMCalificacionComisionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.MantenimientoCOMCalificacionComisionDAO#getCalificacionesComisionesList(biz.belcorp.ssicc.spusicc.comision.web.form.MantenimientoCOMCalificacionComisionSearchForm)
	 */
	public List getCalificacionesComisionesList(CalificacionComisionCabecera calificacionComisionCabecera){
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getCalificacionesComisionesList",calificacionComisionCabecera);
	}

	public void deleteCalificacionComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteCalificacionComisionCabecera", calificacionComisionCabecera);	
		
	}

	public void deleteCalificacionComisionDetalle(
			CalificacionComisionCabecera calificacionComisionCabecera) {
			getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteCalificacionComisionDetalle", calificacionComisionCabecera);
		
	}

	public List getNivelList(String codigo) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getNivelList",codigo);
		
	}

	public void deleteCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteCalificacionComisionDetal", calificacionComisionDetalle);
		
	}

	public String getSecuenciaNextValue() {
		 return (String) getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getSecuenciaNextValue", null);
	}

	public void insertCalificacionComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertCalificacionComisionCabecera", calificacionComisionCabecera);
	}

	public void insertCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertCalificacionComisionDetalle", calificacionComisionDetalle);
		
	}

	public List getCalificacionComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getCalificacionComisionDetalleList",calificacionComisionCabecera);
	}

	public void updateCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateCalificacionComisionDetalle", calificacionComisionDetalle);
		
	}

	public void updateCalificacionComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateCalificacionComisionCabeceraAndDetalle", calificacionComisionCabecera);
		
	}

	public List getDatosComisionList(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getDatosComisionList", params);
	}

	public DatosComision getDatosComision(DatosComision datos) {
		return (DatosComision)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOMSQL.getDatosComision", datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMCalificacionComisionDAO#getEnviosSAP(java.util.Map)
	 */
	public List getEnviosSAP(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getEnviosSAP",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMCalificacionComisionDAO#desmarcarEnvio(java.util.Map)
	 */
	public void desmarcarEnvio(Map criteria){
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.desmarcarEnvio",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMCalificacionComisionDAO#getOrdenEstadisticoPorZonaList(biz.belcorp.ssicc.spusicc.comision.dao.model.OrdenEstadisticoPorZona)
	 */
	public List getOrdenEstadisticoPorZonaList(OrdenEstadisticoPorZona ordenEstadisticoPorZona){
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getOrdenEstadisticoPorZona",ordenEstadisticoPorZona);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMCalificacionComisionDAO#updateOrdenEstadisticoPorZona(biz.belcorp.ssicc.spusicc.comision.dao.model.OrdenEstadisticoPorZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateOrdenEstadisticoPorZona(OrdenEstadisticoPorZona ordenEstadisticoPorZona,Usuario usuario){
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateOrdenEstadisticoPorZona",ordenEstadisticoPorZona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMCalificacionComisionDAO#insertOrdenEstadisticoPorZona(biz.belcorp.ssicc.spusicc.comision.dao.model.OrdenEstadisticoPorZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertOrdenEstadisticoPorZona(OrdenEstadisticoPorZona ordenEstadisticoPorZona,Usuario usuario){
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertOrdenEstadisticoPorZona",ordenEstadisticoPorZona);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMCalificacionComisionDAO#deleteOrdenEstadisticoPorZona(biz.belcorp.ssicc.spusicc.comision.dao.model.OrdenEstadisticoPorZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteOrdenEstadisticoPorZona(OrdenEstadisticoPorZona ordenEstadisticoPorZona, Usuario usuario){
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.deleteOrdenEstadisticoPorZona",ordenEstadisticoPorZona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMCalificacionComisionDAO#getNivelActualEjecutiva(java.util.Map)
	 */
	public String getNivelActualEjecutiva(Map params) {
		 return (String) getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getNivelActualEjecutiva", params);
	}
	
}

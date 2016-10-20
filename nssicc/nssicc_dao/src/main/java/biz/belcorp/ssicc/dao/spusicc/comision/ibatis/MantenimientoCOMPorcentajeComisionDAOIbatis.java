/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMPorcentajeComisionDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;


/**
 * @author <a href="mailto:avillavicencio@csigcomt.com">Alexander Villavicencio</a> 
 *
 */
@Repository("spusicc.mantenimientoCOMPorcentajeComisionDAO")
public class MantenimientoCOMPorcentajeComisionDAOIbatis extends
		BaseDAOiBatis implements MantenimientoCOMPorcentajeComisionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.MantenimientoCOMPorcentajeComisionDAO#getPorcentajesComisionesList(biz.belcorp.ssicc.spusicc.comision.web.form.MantenimientoCOMPorcentajeComisionSearchForm)
	 * retorna la lista de porcentajes de comisiones
	 */
	public List getPorcentajesComisionesList(CalificacionComisionCabecera calificacionComisionCabecera){
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getPorcentajesComisionesList",calificacionComisionCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#getNivelList(java.lang.String)
	 * retorna la lista de niveles
	 */
	public List getNivelList(String codigo) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getNivelList",codigo);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#deletePorcentajeComisionCabecera(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * Borra la cabecera del porcentaje de la comisin 
	 */
	public void deletePorcentajeComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deletePorcentajeComisionCabecera", calificacionComisionCabecera);	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#deletePorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * Borra el detalle del porcentaje de la comisin 
	 */
	public void deletePorcentajeComisionDetalle(
			CalificacionComisionCabecera calificacionComisionCabecera) {
			getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deletePorcentajeComisionDetalle", calificacionComisionCabecera);
		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#getPorcentajeComisionDetalleList(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * retorna la lista de detalle del porcentaje de la comisin
	 * 
	 */
	public List getPorcentajeComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getPorcentajeComisionDetalleList",calificacionComisionCabecera);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#getSecuenciaNextValue()
	 * Valido para el codigo del porcentaje de la comisin - se autogenera un secuencial
	 */
	public String getSecuenciaNextValue() {
		 return (String) getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getSecuenciaNextValue", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#updatePorcentajeComisionCabeceraAndDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * actualiza cabecera y detalle del porcentaje de la comisin
	 */
	public void updatePorcentajeComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updatePorcentajeComisionCabeceraAndDetalle", calificacionComisionCabecera);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#insertPorcentajeComisionCabecera(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * Agrega cabecera del porcentaje de la comisin
	 */
	public void insertPorcentajeComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertPorcentajeComisionCabecera", calificacionComisionCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#insertPorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Agrega detalle del porcentaje de la comisin
	 */
	public void insertPorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertPorcentajeComisionDetalle", calificacionComisionDetalle);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#updatePorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Actualiza detalle del porcentaje de la comisin
	 */
	public void updatePorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updatePorcentajeComisionDetalle", calificacionComisionDetalle);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#deletePorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Borra detalle del porcentaje de la comisin
	 */
	public void deletePorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deletePorcentajeComisionDetal", calificacionComisionDetalle);
		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMPorcentajeComisionDAO#getMaximoNumeroItem(java.util.Map)
	 * Obtiene el mximo nmero de item
	 */
	public Integer getMaximoNumeroItem(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getMaximoNumeroItem", criteria);
		 
	}
}

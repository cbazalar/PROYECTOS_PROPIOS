/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;



/**
 * @author 
 *
 */
public interface MantenimientoCOMPorcentajeComisionDAO extends DAO {

	/**
	 *  retorna la lista de porcentajes de comisiones
	 * @param calificacionComisionCabecera
	 * @return
	 */
	public List getPorcentajesComisionesList(CalificacionComisionCabecera calificacionComisionCabecera);

	/**
	 * retorna la lista de niveles
	 * @param codigo
	 * @return
	 */
	public List getNivelList(String codigo);
	
	/**
	 * Borra la cabecera del porcentaje de la comisin 
	 * @param calificacionComisionCabecera
	 */
	public void deletePorcentajeComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera);

	/**
	 * Borra detalle del porcentaje de la comisin
	 * @param calificacionComisionCabecera
	 */
	public void deletePorcentajeComisionDetalle(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * retorna la lista de detalle del porcentaje de la comisin
	 * @param calificacionComisionCabecera
	 * @return
	 */
	public List getPorcentajeComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * Valido para el codigo del porcentaje de la comisin - se autogenera un secuencial
	 * @return
	 */
	public String getSecuenciaNextValue();
	
	/**
	 * actualiza cabecera y detalle del porcentaje de la comisin
	 * @param calificacionComisionCabecera
	 */
	public void updatePorcentajeComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * Agrega cabecera del porcentaje de la comisin
	 * @param calificacionComisionCabecera
	 */
	public void insertPorcentajeComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * Agrega detalle del porcentaje de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void insertPorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 * Actualiza detalle del porcentaje de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void updatePorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 * Elimina detalle del porcentaje de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void deletePorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 * Valido para el numero de item de la comisin - se autogenera un secuencial
	 * @return
	 */
	public Integer getMaximoNumeroItem(Map criteria);	
}

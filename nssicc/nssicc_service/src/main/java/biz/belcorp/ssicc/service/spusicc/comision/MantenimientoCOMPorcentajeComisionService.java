package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoCOMPorcentajeComisionService extends Service {

	/**
	 * retorna la lista de porcentajes de comisiones
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
	 * Borra la cabecera del porcentaje de la comisin y todo lo que pertenece a dicha comisin
	 * @param calificacionComisionCabecera
	 */
	public void deletePorcentajeComisionCabeceraAndChild(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * retorna la lista de detalle del porcentaje de comisin
	 * @param calificacionComisionCabecera
	 * @return
	 */
	public List getPorcentajeComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * actualiza cabecera y detalle del porcentaje de la comisin
	 * @param calificacionComisionDetalle
	 * @param detalList
	 */
	public void updatePorcentajeComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera,List detalList);

	/**
	 * Agrega cabecera y detalle del porcentaje de la comisin
	 * @param calificacionComisionCabecera
	 * @param detalList
	 */
	public void insertPorcentajeComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera,List detalList);
	
	/**
	 * Agrega detalle del porcentaje de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void insertPorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle, Integer numeroItem);
	
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
	 * Borra detalle del porcentaje de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void deletePorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	
	/**
	 * Consigue el maximo numero de item
	 * @return
	 */
	public Integer getNumeroItem(Map criteria);
	
}

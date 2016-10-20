package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.PorcentajeComisionesCabecera;
import biz.belcorp.ssicc.dao.spusicc.pej.model.PorcentajeComisionesDetalle;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoPEJPorcentajeComisionDAO extends DAO{

	/**
	 * Retorna la lista de niveles
	 * @param codigoPais
	 * @return
	 */
	public List getNivelList(String codigoPais);

	/**
	 * retorna la lista de porcentajes de comisiones
	 * @param criteria
	 * @return
	 */
	public List getPorcentajesComisionesList(Map criteria);

	/**
	 * Devuelve los datelles de Porcentajes de comisiones
	 * @param porcentajeComisionesCabecera
	 * @return
	 */
	public List getPorcentajeComisionDetalleList(PorcentajeComisionesCabecera porcentajeComisionesCabecera);

	/**
	 * Actualiza la cabecera del porcentaje de la comisin
	 * @param porcentajeComisionesCabecera
	 * @param detalList
	 */
	public void updatePorcentajeComisionCabeceraAndDetalle(PorcentajeComisionesCabecera porcentajeComisionesCabecera);

	/**
	 * Valido para el codigo del porcentaje de la comisin - se autogenera un secuencial
	 * @return
	 */
	public String getSecuenciaNextValue();

	/**
	 * Agrega cabecera del porcentaje de la comisin
	 * @param porcentajeComisionesCabecera
	 */
	public void insertPorcentajeComisionCabecera(PorcentajeComisionesCabecera porcentajeComisionesCabecera);

	/**
	 * Agrega detalle del porcentaje de la comisin
	 * @param porcentajeComisionesDetalle
	 */
	public void insertPorcentajeComisionDetalle(PorcentajeComisionesDetalle porcentajeComisionesDetalle);

	/**
	 * extrae el maximo numero de nivel
	 * @param criteria
	 * @return
	 */
	public Integer getMaximoNumeroNivel(Map criteria);

	/**
	 * Actualiza detalle del porcentaje de la comisin
	 * @param porcentajeComisionesDetalle
	 */
	public void updatePorcentajeComisionDetalle(PorcentajeComisionesDetalle porcentajeComisionesDetalle);

	/**
	 * Borra detalle del porcentaje de la comisin
	 * @param porcentajeComisionesDetalle
	 */
	public void deletePorcentajeComisionDetalle(PorcentajeComisionesDetalle porcentajeComisionesDetalle);

	/**
	 * Borra detalle del porcentaje de la comisin
	 * @param porcentajeComisionesCabecera
	 */
	public void deletePorcentajeComisionDetalle(PorcentajeComisionesCabecera porcentajeComisionesCabecera);

	/**
	 * Borra cabeera del porcentaje de la comisin
	 * @param porcentajeComisionesCabecera
	 */
	public void deletePorcentajeComisionCabecera(PorcentajeComisionesCabecera porcentajeComisionesCabecera);
}
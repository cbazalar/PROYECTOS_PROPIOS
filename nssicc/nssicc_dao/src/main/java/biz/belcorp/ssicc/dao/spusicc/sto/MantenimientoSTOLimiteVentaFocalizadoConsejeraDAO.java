package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 */
public interface MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO extends DAO{

	/**
	 * borra un registro de Limite Venta Focalizado por Consejera
	 * @param parametros
	 */
	public void deleteLimiteVentaFocalizadoConsejera(Map parametros);

	/**
	 * Retorna la lista de Limite Venta Focalizada
	 * @param criteria
	 * @return
	 */
	public List getLimiteVentaFocalizadoConsejeraList(Map criteria);

	/**
	 * Inserta un registro de Limite Venta Focalizado por Consejera
	 * @param criteria
	 */
	public void insertSTOLimiteVentaFocalizadoConsejera(Map criteria);

	/**
	 * Verifica si el registro a ser insertado ya existe en la entidad
	 * @param criteria
	 * @return
	 */
	public HashMap getExisteSTOLimiteVentaFocalizadoConsejera(Map criteria);

	/**
	 * Actializa el estado y la unidad limite de un registro ya previamente insertado 
	 * @param criteria
	 */
	public void updateSTOLimiteVentaFocalizadoConsejeraEstadoUnidadLimite(Map criteria);

	/**
	 * Obtiene un registro de Limite Venta Focalizado por Consejera
	 * @param criteria
	 * @return
	 */
	public Map getObjectoSTOLimiteVentaFocalizadoConsejera(Map criteria);

	/**
	 * Actualiza un registro de Limite Venta Focalizado por Consejera
	 * @param criteria
	 */
	public void updateObjetoSTOLimiteVentaFocalizadoConsejera(Map criteria);
}
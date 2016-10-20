package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETActualizacionClasificacionLideresDAO extends DAO{

	/**
	 * Ejecuta el proceso de Actualizacion de clasificacion Lideres
	 * @param params
	 */
	public void executeProcesoLETActualizacionClasificacionLideres(Map params);

	/**
	 * Ejecuta el Proceso de Clasificacion Lideres
	 * @param params
	 */
	public void executeProcesoLETProcesarClasificacionLideres(Map params);
}
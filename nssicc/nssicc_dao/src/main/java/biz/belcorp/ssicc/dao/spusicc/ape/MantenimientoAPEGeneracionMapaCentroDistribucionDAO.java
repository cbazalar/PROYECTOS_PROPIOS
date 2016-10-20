package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPEGeneracionMapaCentroDistribucionDAO extends DAO{
	
	/**
	 * Obtiene el siguiente valor del codigo del Mapa de Centro de Distribucion
	 * @return
	 */
	public String getNextCodigoMapaCentroDistribucion();

	/**
	 * Genera el Mapa del Centro de Distribucion 
	 * @param criteria
	 */
	public void executeGenerarMapaCentroDistribucion(Map criteria);
}
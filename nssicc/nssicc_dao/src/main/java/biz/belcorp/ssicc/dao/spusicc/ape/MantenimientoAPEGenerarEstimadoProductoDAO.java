package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPEGenerarEstimadoProductoDAO extends DAO{
	
	/**
	 * Genera el Estimado para los producto por campaa
	 * @param criteria
	 */
	public void executeGenerarEstimadoProducto(Map criteria);
	
	/**
	 * VAlida si ya se a generado la estimacion para la campaa y linea seleccionadas
	 * @param criteria
	 * @return
	 */
	public String validaExisteGeneraEstimado(Map criteria);
	
	/**
	 * Genera el numero de OLAS
	 * @param criteria
	 */
	public void executeGenerarOlas(Map criteria);
}
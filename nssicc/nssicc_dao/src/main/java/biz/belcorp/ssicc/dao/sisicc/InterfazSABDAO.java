/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;


import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazSABDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */

public interface InterfazSABDAO extends DAO {

	/**
	 * Proceso que ejecuta Envo de Transacciones Fuente Ventas
	 * @param params
	 */
	void executeInterfazVENEnviarFuenteVentasReal(Map params);

	/**
	 * Proceso que ejecuta Envo de Maestro de zonas
	 * @param params
	 */
	void executeInterfazVENEnviarMaestroZonas(Map params);

	/**
	 * Proceso que ejecuta Envo de Maestro Regiones
	 * @param params
	 */
	void executeInterfazVENEnviarMaestroRegiones(Map params);

	/**
	 * Proceso que ejecuta Envo Maestro Territorios
	 * @param params
	 */
	void executeInterfazVENEnviarMaestroTerritorios(Map params);
	
	/* INI SA PER-SiCC-2012-0648 */
	/**
	 * Proceso que ejecuta Envo Pedidos Totales
	 * @param params
	 */
	void executeInterfazSABEnviarPedidosTotales(Map params);

	/**
	 * Proceso que ejecuta Envo Pedidos Por Marca
	 * @param params
	 */
	void executeInterfazSABEnviarPedidosPorMarca(Map params);

	/**
	 * Proceso que ejecuta Envo Venta Diaria Por Marca
	 * @param params
	 */
	void executeInterfazSABEnviarVentaDiaria(Map params);

	/**
	 * Proceso que ejecuta Envo Maestro Territorios 
	 * @param params
	 */
	void executeInterfazSABEnviarMaestroTerritorios(Map params);
	
	/**
	 * Proceso que ejecuta Envo Demanda Anormal
	 * @param params
	 */
	void executeInterfazSABEnviarDemandaAnormal(Map params);
	
	/**
	 * Proceso que ejecuta Envo Totales al Cierre
	 * @param params
	 */
	void executeInterfazSABEnviarTotalesCierre(Map params);
	/* FIN SA PER-SiCC-2012-0648 */
	
}

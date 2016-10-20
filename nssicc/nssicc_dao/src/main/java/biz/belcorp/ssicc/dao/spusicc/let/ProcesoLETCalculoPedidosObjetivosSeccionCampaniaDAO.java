/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
public interface ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAO extends DAO{

	/**
	 * Mtodo que realiza el calculo de pedidos Objetvos por Seccion y Campania
	 * @param params
	 */
	public void executeProcesoLETCalculoPedidosObjetivosSeccionCampania(Map params);
	
}

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
 * <a href="ProcesoLETCalculoObjetivoRetencion33SeccionDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
public interface ProcesoLETCalculoObjetivoRetencion33SeccionDAO extends DAO{
	
	/**
	 * Mtodo que realiza el calculo Objetivo Retencion 3/3 Seccion
	 * @param params
	 */
	public void executeProcesoLETCalculoObjetivoRetencion33Seccion(Map params);

}

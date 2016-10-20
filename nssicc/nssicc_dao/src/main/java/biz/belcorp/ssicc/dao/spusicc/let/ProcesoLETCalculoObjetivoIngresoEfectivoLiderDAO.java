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
 * <a href="ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
public interface ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAO extends DAO{
	
	/**
	 * Mtodo que realiza el calculo Objetivo Ingresos Efectivos Lder
	 * @param params
	 */
	public void executeProcesoLETCalculoObjetivoIngresoEfectivoLider(Map params);

}

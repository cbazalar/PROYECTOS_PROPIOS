/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc;


import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazRUVService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */


public interface InterfazRUVService extends Service {



	/**
	 * Ejecuta el reproceso de las interfaces RUV
	 * @param params
	 */
	public void ejecutarReprocesoInterfazRUV(Map params);
	
}

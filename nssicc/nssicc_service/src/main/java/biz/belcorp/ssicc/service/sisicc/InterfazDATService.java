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
 * <a href="InterfazDATService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristian Roman</a>
 * 
 */


public interface InterfazDATService extends Service {

	/**
	 * Carga la data en la tabla de datamart antes de ejecutar las interfaces
	 * @param params
	 */
	public void executeCargarTablaInterfaz(Map params);
	
	/**
	 * Ejecuta validacion DATAMART
	 * @param params
	 */
	public String getValidacionInterfazDatamart(Map params);

	/**
	 * Validacion de cierre de campaña pendiente
	 * @param criteria
	 * @return
	 */
	public String getValidacionCierreCampanyaPendiente(Map criteria);
	
}

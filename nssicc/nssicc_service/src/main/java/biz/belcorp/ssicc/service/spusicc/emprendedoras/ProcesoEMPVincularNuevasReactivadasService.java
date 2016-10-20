package biz.belcorp.ssicc.service.spusicc.emprendedoras;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */

public interface ProcesoEMPVincularNuevasReactivadasService extends Service {

	/**
	 * @param params
	 */
	public void executeStoreProcedure(Map params);
}
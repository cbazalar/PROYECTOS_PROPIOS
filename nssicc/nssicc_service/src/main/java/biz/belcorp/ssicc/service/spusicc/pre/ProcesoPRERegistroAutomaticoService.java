package biz.belcorp.ssicc.service.spusicc.pre;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoPRERegistroAutomaticoService  extends Service {

	/**
	 * Ejecura el proceso de Reemplazo de premios en bolsa de faltantes
	 * 
	 * @param params
	 */
	void executeRegistroAutomatico(Map params);

}

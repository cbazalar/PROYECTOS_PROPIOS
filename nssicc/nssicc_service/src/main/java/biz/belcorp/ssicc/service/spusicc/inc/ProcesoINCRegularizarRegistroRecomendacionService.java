package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCRegularizarRegistroRecomendacionService  extends Service{

	/**
	 * Ejecuta el proceso de regularizar registro de recomendacion
	 * 
	 * @param params
	 */
	void executeRegularizarRegistroRecomendacion(Map params);
	
}


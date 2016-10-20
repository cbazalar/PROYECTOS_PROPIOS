package biz.belcorp.ssicc.service.spusicc.mav;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAVFacturacionGerentesService extends Service {
	
	/**
	 * Ejecura el proceso de Facturacion Gerentes MAV
	 * 
	 * @param params
	 */
	void executeFacturacionGerentes(Map params);

}

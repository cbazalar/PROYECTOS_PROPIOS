package biz.belcorp.ssicc.service.spusicc.mav;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAVGenerarEnviosService extends Service {
	
	/**
	 * Ejecura el proceso de Generar Envios MAV
	 * 
	 * @param params
	 */
	void executeGenerarEnvios(Map params);

	/**
	 * Verifica si existe MAV configurados a Ser Enviados
	 * 
	 * @param criteria
	 * @return
	 */
	boolean existeMAVEnvios(Map criteria);
	
	/**
	 * Verifica si existe Matriz de Facturacin
	 * @param criteria
	 * @return
	 */
	public boolean getExisteMatrizFacturacion(Map criteria);
	
}

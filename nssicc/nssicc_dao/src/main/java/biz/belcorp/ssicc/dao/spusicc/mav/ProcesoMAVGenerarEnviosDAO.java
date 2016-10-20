package biz.belcorp.ssicc.dao.spusicc.mav;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAVGenerarEnviosDAO extends DAO {

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

package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

/**
 * DAO de la Interfaz de Cuenta Corriente
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio</a>
 */
public interface InterfazECMDAO {
	
	/**
	 * Procedimiento que ejecuta la interfaz de envio de Matriz de Productos con oferta de Cumpleaos
	 * @param map
	 */
	public void executeInterfazECMEnviarMatrizProductosOfertaCumpleanhos(
			Map params);
	
	/**
	 * Ejecuta la interfaz de Envio de Clientes
	 * @param params
	 */
	public void executeInterfazECMEnviarClientes(Map params);
}

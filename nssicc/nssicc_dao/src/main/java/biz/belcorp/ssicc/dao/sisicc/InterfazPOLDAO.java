package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazPOLDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:dtorres@sigcomt.com">Diego Torres Loyola</a>
 * 
 */

public interface InterfazPOLDAO extends DAO {

	/**
	 * Proceso que ejecuta Envío Consultora
	 * @param params
	 */
	void executeInterfazPOLEnviarConsultora(Map params);
	
	/**
	 * Proceso que ejecuta Envío Recomendaciones
	 * @param params
	 */
	void executeInterfazPOLEnviarRecomendaciones(Map params);
	
	/**
	 * Proceso que ejecuta Envío Zonas
	 * @param params
	 */
	void executeInterfazPOLEnviarZonas(Map params);
	
	/**
	 * Proceso que ejecuta Envío Estadistico por zona
	 * @param params
	 */
	void executeInterfazPOLEnviarEstadisticoPorZona(Map params);
	
	/**
	 * Proceso que ejecuta Envío Consolidado Pedidos
	 * @param params
	 */
	void executeInterfazPOLEnviarConsolidadoPedidos(Map params);
	
	/**
	 * Proceso que ejecuta Envío Matriz Facturacion
	 * @param params
	 */
	void executeInterfazPOLEnviarMatrizFacturacion(Map params);
	
	/**
	 * Proceso que ejecuta Envío Fuera Caja
	 * @param params
	 */
	void executeInterfazPOLEnviarFueraCaja(Map params);
	
	/**
	 * Proceso que ejecuta Envío Cargos Cuenta Corriente
	 * @param params
	 */
	void executeInterfazPOLEnviarCargosCuentaCorriente(Map params);
	
	/**
	 * Proceso que ejecuta Envío Abonos Cuenta Corriente
	 * @param params
	 */
	void executeInterfazPOLEnviarAbonosCuentaCorriente(Map params);
	
	/**
	 * Proceso que ejecuta Envío Cantidad Intereses Procesados
	 * @param params
	 */
	void executeInterfazPOLEnviarCantidadInteresesProcesados(Map params);
	
	/**
	 * Proceso que ejecuta Envío Archivo Control Modulo Local
	 * @param params
	 */
	void executeInterfazPOLEnviarArchivoControlModuloLocal(Map params);
}

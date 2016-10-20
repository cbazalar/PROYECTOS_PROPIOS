/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
  * <p>
 * <a href="InterfazIVRDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
public interface InterfazIVRDAO extends DAO {
	/**
	 * 
	 */
	public void deleteTablaControlIVRCorporativo();
	
	/**
	 * @param params
	 */
	public void executeIVREnviarTablaClientesCorporativo(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaConcursosCorporativo(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaControlCorporativo(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaPostVentaCorporativo(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaRechazosPostVentaCorporativo(
			Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaLlamadasSalidaCorporativo(
			Map params);  
	
	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaResultadosCampanaCorporativo(
			Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaProgramaDuplaCyzoneCorporativo(
			Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaMaestroDuplaCyzoneCorporativo(
			Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaEstadoPedidoCorporativo(
			Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaBonosSalidaCorporativo(
			Map params);
		
	/**
	 * Ejecuta la interface de cronograma corporativo
	 * @param params
	 */
	public void executeIVREnviarTablaCronogramaCorporativo(Map params);

	
	/**Realiza el envio de motivos de rechazo corporativo
	 * @param params
	 */
	public void executeInterfazIVREnviarMotivosRechazoCorporativo(Map params);

	/**Realiza el envio de tabla control corporativo
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaControlCorp(Map params);

	/**
	 * Realiza el envio de bonos de salida cororativo
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaBonosSalidaCorp(Map params);

	/**Realiza el envio de cronograma de actividades
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaCronogramaActividades(Map params);

	/**Realiza el envio de tabla de clientes
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaClienteCorporativo(Map params);

	/**Realiza el envio de tabla estado de pedido
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaEstadoPedido(Map params);

	/**Realiza el envio de matriz de campanha
	 * @param params
	 */
	public void executeInterfazIVREnviarTablaMatrizCampanha(Map params);
	
	/**
	 * Inserta en la tabla temporal los pedidos IVR
	 * @param params
	 */
	public void insertInterfazIVRRecepcionarPedidos(Map params);
	
	/**
	 * Registra el pedido que llega por IVR
	 * @param params
	 */
	public void executeRegistraPedidoIVR(Map params);
		
	/**
	 * Inserta la Data en las tablas de SOA
	 * @param params
	 */
	public void executeInterfazIVREnviarDataSOA(Map params);
	
}

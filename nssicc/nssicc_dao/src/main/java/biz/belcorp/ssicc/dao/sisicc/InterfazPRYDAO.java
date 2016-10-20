package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

/**
 * DAO de la Interfaz Proyeccion Parcial.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public interface InterfazPRYDAO {

	public List getInterfazPRYEnviarDatosProyeccionParcial(Map params);

	public List getInterfazPRYEnviarCronogramaFacturacion(Map params);

	public List getInterfazPRYEnviarInformacionVentaProyeccionParcial(Map params);

	public List getInterfazPRYEnviarPedidosDiaPedidosAcumulados(Map params);
	
	/**
	 * Ejecuta la interfaz EnvioDatosProyeccionParcialCentro
	 * @param params
	 */
	public void executeEnvioDatosProyeccionParcialCentro(Map params);
	
	/**
	 * Ejecuta la interfaz EnvioCronogramaFacturacionCentro
	 * @param params
	 */
	public void executeEnvioCronogramaFacturacionCentro(Map params);
	
	/**
	 * Ejecuta la interfaz EnvioInformacionVentaProyeccionParcialCentro
	 * @param params
	 */
	public void executeEnvioInformacionVentaProyeccionParcialCentro(Map params);
	
	/**
	 * Ejecuta la interfaz EnvioPedidosDiaPedidosAcumuladosCentro
	 * @param params
	 */
	public void executeEnvioPedidosDiaPedidosAcumuladosCentro(Map params);
	
	public void executeInterfazPRYEnviarInformacionNuevosFaltantes(Map params);
	
	/**
	 * * Ejecuta la interfaz EnvioEnvioDemandaCodigoCerrado
	 * @param params
	 */
	public void executeEnvioDemandaCodigoCerrado(Map params);
}
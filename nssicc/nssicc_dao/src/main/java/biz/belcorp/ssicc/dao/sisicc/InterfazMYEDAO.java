package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

/**
 * DAO de la Interfaz de MYE
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
public interface InterfazMYEDAO {
	
	/**
	 * Elimina la informacion de la tabla INT_MYE_ACTUA_DATOS_CONSU
	 */
	public void deleteInterfazMYERecepcionarActualizacionDatosConsultora();

	/**
	 * Carga la informacion del Archivo en la tabla INT_MYE_ACTUA_DATOS_CONSU 
	 * @param map
	 */
	public void executeInterfazMYERecepcionarActualizacionDatosConsultora(
			Map map);

	/**
	 * Procesa la informacion recepcionada
	 * @param map
	 */
	public void executeInterfazMYEProcesarActualizacionDatosConsultora(Map map);
	
	/**
	 * Ejecuta Interfaz Faltante Anunciado Limite de Venta
	 * @param params
	 */
	public void executeInterfazMYEEnviarFaltantesAnunciadosLimiteVenta(Map params);

	/**
	 * ejecutar Interfaz enviar clientes web
	 * @param params
	 */
	public void executeInterfazMYEEnviarClientesWeb(Map params);
	
	/**
	 * Ejecuta la interfaz de envio de maestros incobrables
	 * @param params
	 */
	public void executeInterfazMYEEnviarMaestrosIncobrables(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazMYERecepcionarActivacionFlexipagoWeb(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazMYERecepcionarActivacionFlexipagoWebFLX(Map params);
	
	/**
	 * @param criteria
	 * @return
	 */
	public int getExisteMaestroClientes(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void updateInterfazMYERecepcionarClientesIPUnica(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void updateInterfazMYERecepcionarClientesIPUnica2(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public int getExisteTipoLogro(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public int getExisteConsultoraMaestro(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCampañaPrimerUltimoPedidoConsultora(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCampañasInicioFinLogroConsultora(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public int getIndicadorNuevasLogro(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void insertInterfazMYERecepcionarMetas(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void updateInterfazMYERecepcionarMetas(Map criteria);
	
	/**
	 * @param params
	 */
	public void executeInterfazMYERecepcionarPremiosWeb(Map params);
}

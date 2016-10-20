/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;


import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazLARDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */

public interface InterfazLARDAO extends DAO {

	/**
	 * Proceso que ejecuta Envo de Estimados a Yobel
	 * @param params
	 */
	void executeInterfazLAREnvioEstimadosYobel(Map params);
	
	/**
	 * Proceso que ejecuta Envio de Documentos Cabecera Complemento
	 * @param params
	 */
	void executeInterfazLAREnvioDocumentosCabeceraComplemento(Map params);
	
	/**
	 * Proceso que elimina el primer periodo encontrado en el archivo de carga 
	 * para la interfaz de Recepcin Estimados Leader
	 * @param codigoPeriodo
	 */
	void deleteInterfazLARRecepcionarEstimadosLeader(String codigoPeriodo);
	
	/**
	 * Proceso que ejecuta la recepcion de la carga de estimados leader
	 * @param params
	 */
	void insertInterfazLARRecepcionarEstimadosLeader(Map params);
	
	/**
	 * Se realiza el proceso de generacion de pedidos a chequear
	 * @param params
	 */
	public void executeGenerarPedidosChequear(Map params);
	
	/**
	 * Se envia la interfaz de Tipo de Chequeo
	 * @param params
	 */
	public void executeInterfazLAREnviarTipoChequeo(Map params);
	
	/**
	 *  Se envia la interfaz de Origen de Chequeo
	 * @param params
	 */
	public void executeInterfazLAREnviarOrigenChequeo(Map params);
	
	/**
	 * Se envia la interfaz de Resultado de Chequeo
	 * @param params
	 */
	public void executeInterfazLAREnviarResultadoChequeo(Map params);
	
	/**
	 * Se envia la interfaz de Pedidos a Chequear
	 * @param params
	 */
	public void executeInterfazLAREnviarPedidosChequear(Map params);
	
	/**
	 * Se actualiza el indicador de envio a Yobel para los pedidos generados
	 * en el proceso de pedidos a chequear
	 * @param params
	 */
	public void executeActualizarPedidosChequear(Map params);
	
	/**
	 * Borra los registros de la tabla temporal
	 */
	public void deleteInterfazLARResultadoChequeoCabecera();
	
	/**
	 * Se recibe las interfaces para la cabecera
	 * @param params
	 */
	public void executeInterfazLARResultadoChequeoCabecera(Map params);
	
	/**
	 * Borra los registros de la tabla temporal
	 */
	public void deleteInterfazLARResultadoChequeoDetalle();
	
	/**
	 * Se recibe las interfaces para el detalle
	 * @param params
	 */
	public void executeInterfazLARResultadoChequeoDetalle(Map params);
	
	/**
	 * Inserta en las tablas finales de cabecera y detalle del resultado de chequeo
	 * @param params
	 */
	public void executeResultadoChequeo(Map params);	
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 1 
	 */
	public void executeInterfazLAREnvioLAR1(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 2
	 */
	public void executeInterfazLAREnvioLAR2(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 3 
	 */
	public void executeInterfazLAREnvioLAR3(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 4 
	 */
	public void executeInterfazLAREnvioLAR4(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 5 
	 */
	public void executeInterfazLAREnvioLAR5(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 7C 
	 */
	public void executeInterfazLAREnvioLAR7Cabecera(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 7D
	 */	
	public void executeInterfazLAREnvioLAR7Detalle(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 9 
	 */
	public void executeInterfazLAREnvioLAR9(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 10
	 */
	public void executeInterfazLAREnvioLAR10(Map params);
	
	/**
	 * Actualiza la informacion luego de generar las interfaces
	 * @param params
	 */
	public void updateLARDocumentosCabeceraComplemento(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 11
	 */
	public void executeInterfazLAREnvioLAR11(Map params);
	
	/**
	 * @param params
	 * Se envia la interfaz LAR 8
	 */
	public void executeInterfazLAREnvioLAR8(Map params);
	
	/**
	 * Almacena temporalmente los parametros para la generacion de LAR 8
	 * @param params
	 */
	void insertInterfazLAR8Parametros(Map params);
	
	/**
	 * Elimina los parametros para la generacion de LAR 8
	 */
	public void deleteInterfazLAR8Parametros();

	/**
	 * 
	 * @param params
	 */
	void executeInterfazLAREnviarArchivoTIS2(Map params);
	
	/**
	 * Verifica si existe la interfaz en Historico de lotes con Error
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean existeHistoricoInterfazError(Map criteria);
	
	/**
	 * Verifica si el archivo ha sido procesado para la LAR-37 Cargar Información Tracking
	 * @param criteria
	 * @return
	 */
	public Integer getExisteLARControlCargarInformacionTracking(Map criteria);
	
	/**
	 * Inserta en la tabla de Control de LAR-37 Cargar Información Tracking
	 * @param criteria
	 * @return
	 */
	public void insertLARControlCargarInformacionTracking(Map criteria);
	
	/**
	 * Actualiza en la tabla de Control de LAR-37 Cargar Información Tracking
	 * @param criteria
	 * @return
	 */
	public void updateLARControlCargarInformacionTracking(Map criteria);
	
	
	/**
	 * Actualiza tabla PED_SEGUID_PEDID de LAR-37 Cargar Información Tracking
	 * @param criteria
	 */
	public void executeLARCargarInformacionTracking(Map criteria);
	
}


package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;


/**
 * <p>
 * <a href="ProcesoSTODAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
public interface ProcesoSTODAO extends DAO {

	/**
	 * Obtiene la lista de Validaciones del tipo de documento eniado como parametro
	 * @param tipoDocumentoDigitado
	 * @return
	 */
	List getValidacionesProceso(HistoricoTipoDocumento historico);
	
	/**
	 * Prepra los registros para la ejecucionde validaciones
	 * @param queryParams
	 */
	void inicializeRegistrosProcesados(Map queryParams);

	/**
	 * Ejecuta la validacion con los parametros de Map
	 * @param queryParams
	 * @param namespaceSTO Archivo en el que se encuentra su implementacion
	 */
	void executeValidacion(Map queryParams, String namespaceSTO);

	/**
	 * Ejecuta el envio a SSICC de los registros que pasaron las validaciones
	 * @param queryParams
	 * @param namespaceSTO 
	 * 	Archivo en el que se encuentra su implementacion
	 */
	void executeCargaRegistrosValidos(Map queryParams, String namespaceSTO);

	/**
	 * Actualiza un registro con un numero de lote para que pueda ser procesado
	 * @param queryParams
	 */
	void updateDocumentoForValidate(Map queryParams);

	/**
	 * Obtuine los datos del tipo de documento en base a su PK
	 * @param tipoDocumentoDigitadoPK
	 * @return
	 */
	TipoDocumentoDigitado getTipoDocumentoDigitado(
			TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK);

	/**
	 * Obtiene el parametro de la tabla STO_PARAM_GENER_OCCRR
	 * @param criteria
	 * @return
	 */
	String getParametroSTO(Map criteria);

	/**
	 * @param criteria
	 * Carga los valores en una tabla para el posterior 
	 * envio de Mails de Solicitud de Credito
	 */
	public void executeEnvioMailSCC(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devueleve la lista de Emails a enviar
	 */
	public List getEnvioMailsSCCList(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve los parametros de Envio de Correo
	 */
	public List getEnvioMailsSCCParams(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina los registros de los mails enviados
	 */
	public void deleteEnvioMailsSCC(Map criteria);

	/**
	 * procedimiento a Ejecutarse al Aprobar una Validacion 
	 * @param queryParams
	 * @param namespaceSTO
	 */
	public void approveValidacionDocumentoSTO(Map queryParams, String namespaceSTO);

	/**
	 * procedimiento a Ejecutarse al Desaprobar una Validacion 
	 * @param queryParams
	 * @param namespaceSTO
	 */
	public void disapproveValidacionDocumentoSTO(Map queryParams, String namespaceSTO);

	/**
	 * Procedimientoa Ejecutarse al Rechazar un Documento
	 * @param queryParams
	 * @param namespaceSTO
	 */
	public void rejectDocumentoSTO(Map queryParams);

	/**
	 * @param criteria
	 */
	public void executeAuditoriaProcesoSTO(Map criteria);
	
	
	/**
	 * Funcion que devuelve los Origenes por Tipo de Documento
	 * @param params
	 */
	public List getOrigenSTOByTipoDocumento(Map params);
	
	/**
	 * Funcion que devuelve la lista de Documentos para un lote
	 * @param params
	 */
	public List getDocumentoDigitadoPKByLote(Map params);
	
	/**
	 * Elimina los pedidos seleccionados
	 * @param criteria
	 */
	public void executeEliminarPedidosOrdenCompra(Map criteria);
	
	/**
	 * Metodo que invoca a las rutinas de eliminacion de CDRs
	 * @param selectedItems
	 */
	public void executeEliminarCDR(Map criteria);
		
	/**
	 * Metodo que devuelve el indicador de modificacion de codigo de cliente 
	 * parametrizado para una validacion determinada
	 * @param criteria
	 * @return
	 */
	public String getIndicadorModificacionCodigoCliente(Map criteria);
	
	/**
	 * Metodo que devuelve la lista de Zonas de arribo en base al tipo de documento
	 * @param params
	 * @return
	 */
	public List getZonaArriboSTOByTipoDocumento(Map params);
	
	/**
	 * Metodo que devuelve si se esta ejecutando o no una carga de pedidos
	 * @param criteria
	 * @return
	 */
	public List getCargaEjecucionByDocumento(Map criteria);
	

	/**
	 * Metodo que pule la data actualizada 
	 * @param criteria
	 */
	public void executePulirDataActualizada(Map criteria);
	
	/**
	 * Metodo que devuelve los datos del pedido
	 * @param criteria
	 * @return
	 */
	public List getConsultaPedidoPostVenta(Map criteria);

	/**
	 * @return
	 */
	public String getSecuenciaConsultaDocumento();

	/**
	 * @param criteria
	 * @return
	 */
	List getConsultaPedidosList(Map criteria);

	/**
	 * @param queryParams
	 */
	void executeEliminarPedido(Map criteria);

	/**
	 * @param criteria
	 */
	void executeBloquearEliminarDocumento(Map criteria);

	/**
	 * @param criteria
	 */
	void updateEliminarPedido(Map criteria);
	
	/**
	 * @param params
	 * @return
	 */
	public List getConsultaPedidosGP1List(Map criteria);
	
	/**
	 * Realiza la reversa de los pedidos que estan en GP1
	 * @param criteria
	 */
	public void reversarPedidosGP1(Map criteria);
	
	/**
	 * Devuelve el indicador para desmarcar o no las agrupaciones
	 * @param criteria
	 * @return
	 */
	public String getindicadorDesmarcarAgrupacion(Map criteria);
	
	/**
	 * Devuelve la lista de polizas
	 * @param params
	 * @return
	 */
	public List getConsultaPolizasFamiliaSeguraList(Map criteria);
	
	/**
	 * Elimina la poliza de Familia Segura
	 * @param criteria
	 */
	void updateEliminarPoliza(Map criteria);

	/**
	 * Invoca al proceso de elimiar poliza de Familia Segura
	 * @param criteria
	 */
	void executeEliminarPoliza(Map criteria);
	
	/**
	 * @param criteria
	 * @return Valor de Parametro
	 */
	public String getParametroGenericoSistema(Map criteria);
	
	
	/**
	 * Obtiene el lote actual de STO y actualiza al siguiente valor
	 * @param tipoDocumentoDigitadoPK
	 * @return
	 */
	public String updateLoteSTO(TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK);
	
	/**
	 * Obtiene la lista de pedidos online de la consultora
	 * @param criteria
	 * @return
	 */
	public List getConsultaPedidosOnlineList(Map criteria);

	/**
	 * Actualiza los registros de la Tabla STO_DOCUM_DIGIT para procesarlos, 
	 * evitando que otros procesos puedan tomarlos	 *
	 * @param queryParams
	 */
	public void updateDocumentoForProcess(Map queryParams);

	/**
	 * @param list
	 * @param queryParams
	 */
	void updateDocumentoForDelete(List stoList, Map queryParams);

	/**
	 * @param queryParams
	 */
	void executeDeletePedido(Map queryParams);

	/**
	 * @param pedidoList
	 * @param queryParams
	 * @return
	 */
	List getSTOListByPedidoList(List pedidoList, Map queryParams);

	/**
	 * @param polizaList
	 * @param queryParams
	 * @return
	 */
	List getSTOListByPolizaList(List polizaList, Map queryParams);

	/**
	 * retorna 1 si existe cupon para el cliente en el periodo, caso contraio
	 * retorna 0
	 * @param params
	 * @return
	 */
	Integer getValidarCuponPeriodoCliente(Map params);

	/**
	 * @param params
	 * @return
	 */
	Integer getValidarDeudaCliente(Map params);
	
	/**Retorna la lista que coincide con el criterio (Codigo de Cliente)
	 * @param params
	 * @return
	 */
	public List getConsultaDetallePedidoGP1_GP2(Map criteria);
	
	/**Retorna la lista que coincide con el criterio (Codigo de Cliente)
	 * @param params
	 * @return
	 */
	public List getConsultaDetallePedidoGP3_GP4_GP5(Map criteria);
	
	/**Retorna la lista que coincide con el criterio (Codigo de Cliente)
	 * @param params
	 * @return
	 */
	public String getRegionZonaTemporal (Map criteria);
	
	/**Retorna el codigo del Tipo de Documento segun el codigo de validacion
	 * @param params
	 * @return
	 */
	public String getTipoDocumentoByValidacion (Map criteria);
	
	/**
	 * Retorna lista de Seguimiento de Pedido
	 * @param criteria
	 * @return
	 */
	public List getPedidoSeguidoSTOList(Map criteria);
	
	/**
	 * Retorna la fecha de Entrega
	 * @param criteria
	 * @return
	 */
	public String getFechaEntregaConfirmada(Map criteria);
	
	/**
	 * Carga la tabla de Seguimiento de pedidos
	 * @param params
	 */
	public void executeCargaSeguimientoPedidos(Map params);

	/**
	 * @param historico
	 */
	public void executeDeleteErrores(Map params);
	
	/**
	 * @param params
	 * @return
	 */
	public List getReclamosEliminados(Map params);
	
	/**
	 * Verifica si se ha ingresado cupon para la consultora
	 * @param criteria
	 * @return
	 */
	public String getPedidoCuponExistenteByCriteria(Map criteria);
	

	/**
	 * @param map
	 * @return
	 */
	public List getMensajesRechazo(Map criteria);

	/**
	 * @param queryParams
	 */
	public void updateRechazoOCC(Map queryParams);

	/**
	 * @param queryParams
	 */
	public void executeRecoverRejectDocumentoSTO(Map queryParams);

	/**
	 * @param queryParams
	 */
	public void executeRecoverRejectOCC(Map queryParams);
	
	
	/**
	 * @param queryParama
	 * @return
	 */
	public List getDocumentoDigitadoPKByProceso(Map queryParama);
	
	/**
	 * @param queryParams
	 */
	public void executeBeforeValidation(Map queryParams);

	/**
	 * @param queryParams
	 */
	public void executeAfterValidation(Map queryParams);

	/**
	 * @param queryParams
	 * @param namespaceSTO
	 * @return
	 */
	public List getListForValidate(String namespaceSTO, Map queryParams);

	/**
	 * @param queryParams
	 */
	public void updateValidRecord(Map queryParams);
	
	/**
	 * @param namespaceSTO
	 * @param row
	 */
	void updateSTOData(String namespaceSTO, Map row);
	
	/**
	 * @param namespaceSTO
	 * @param queryParams
	 * @return
	 */
	public List getListOCCDataCrediticiaConsultora(String namespaceSTO, Map queryParams);
	
	/**
	 * @param namespaceSTO
	 * @param row
	 */
	public void updateOCCDataCrediticiaConsultora(String namespaceSTO, Map row);
	
	
}
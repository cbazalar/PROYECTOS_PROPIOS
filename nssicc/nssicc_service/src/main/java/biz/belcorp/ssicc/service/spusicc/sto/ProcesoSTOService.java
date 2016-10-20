package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * interface de validacion de STO.
 * <p>
 * <a href="ProcesoSTOService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

public interface ProcesoSTOService extends Service {

	/**
     * Obtiene la informacion del Tipo de Documento en base a su llave primaria. La
     * excepcion.
	 * @param tipoDocumentoDigitadoPK
	 * @return
	 */
	public TipoDocumentoDigitado getTipoDocumentoDigitado(TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK);
	
	/**
	 * Obtiene la lista de Documentos Digitados por Lote
	 * @param params
	 * @return
	 */
	public List getDocumentoDigitadoPKByLote(Map params);

	/**
	 * Actualiza
	 * @param queryParams
	 */
	public void updateDocumentoForValidate(Map queryParams);


	/**
	 * Metodo que devuelve el indicador de modificacion de codigo de cliente 
	 * parametrizado para una validacion determinada
	 * @param criteria
	 * @return
	 */
	public String getIndicadorModificacionCodigoCliente(Map criteria);
	
	/**
	 * Metodo que devuelve si se esta ejecutando o no una carga de pedidos
	 * @param criteria
	 * @return
	 */
	public List getCargaEjecucionByDocumento(Map criteria);
	
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
	 * @param params
	 * @return
	 */
	public List getConsultaPedidosList(Map criteria);



	/**
	 * @param criteria
	 */
	public void executeEliminarPedido(Map criteria);



	/**
	 * @param criteria
	 */
	public void updateEliminarPedido(Map criteria);



	/**
	 * @param criteria
	 */
	public void executeBloquearEliminarDocumento(Map criteria);
	
	
	/**
	 * @param params
	 * @return
	 */
	public List getConsultaPedidosGP1List(Map criteria);
	
	/**
	 * Realiza la reversa de los pedidos que estan en GP1
	 * @param lista
	 */
	public void reversarPedidosGP1(List lista);
	
	/**
	 * Devuelve el indicador para desmarcar o no las agrupaciones
	 * @param criteria
	 * @return
	 */
	public String getindicadorDesmarcarAgrupacion(Map criteria);

	/**
	 * @param queryParams
	 */
	public void inicializeRegistrosProcesados(Map criteria);
	
	/**
	 * Devuelve la lista de polizas
	 * @param params
	 * @return
	 */
	public List getConsultaPolizasFamiliaSeguraList(Map criteria);
	
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
	 * Acatualiza los registros de la Tabla STO_DOCUM_DIGIT para procesarlos, 
	 * evitando que otros procesos puedan tomarlos	 * 
	 * @param queryParams
	 */
	public void updateDocumentoForProcess(Map queryParams);

	/**
	 * @param lista
	 * @param queryParams 
	 */
	public void updateDocumentoForDelete(List stoList, Map queryParams);

	/**
	 * @param pedidoList
	 * @param queryParams 
	 * @return
	 */
	public List getSTOListByPedidoList(List pedidoList, Map queryParams);
	
	
	/**
	 * @param pedidoList
	 * @param queryParams 
	 * @return
	 */
	public List getSTOListByPolizaList(List pedidoList, Map queryParams);

	/**
	 * retorna 1 si existe cupon para el cliente en el periodo, caso contraio
	 * retorna 0
	 * @param params
	 * @return
	 */
	public Integer getValidarCuponPeriodoCliente(Map params);

	/**
	 * @param params
	 * @return
	 */
	public Integer getValidarDeudaCliente(Map params);
	
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
	 * @param queryParama
	 * @return
	 */
	public List getDocumentoDigitadoPKByProceso(String codTipoDocu, String numProc);
	

	
}

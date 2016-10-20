package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.Usuario;

public interface MantenimientoOCRCapturaPedidoDAO extends DAO {

	/**
	 * @param objDatosConsultora
	 * @param usuario
	 * inserta la cabecera del pedido
	 */
	public void insertcabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario);

	/**
	 * @param objPedidoConsultora
	 * @param usuario
	 * inserta detalle del pedido
	 */
	public void insertDetallePedido(LabelPedidosValue objPedidoConsultora, Usuario usuario);

	/**
	 * @param objPedidoConsultora
	 * @param usuario
	 * actualiza detalle del pedido
	 */
	public void updateDetallePedido(LabelPedidosValue objPedidoConsultora, Usuario usuario);

	/**
	 * @param params
	 * @return
	 * devuelve el codigo de consultora
	 */
	public String getCodConsultora(Map params);

	/**
	 * @param detallePedidos
	 * elimina detalle del pedido
	 */
	public void deleteDetallePedido(Map detallePedidos);

	/**
	 * @param objDatosConsultora
	 * @param usuario
	 * Actualiza la cabecera del pedido
	 */
	public void updateCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario);

	/**
	 * @param criteria
	 * @return
	 * Devuelve la lista de codigos de venta digitables que se encuentran en la matriz de SSiCC
	 */
	public List getListaCodigosVentaMatriz(Map criteria);

	/**
	 * Valida el Bloqueo en digitacion de Pedidos
	 * @param criteria
	 * @return
	 */
	public String validarBloqueoDigitacionPedidos(Map criteria);

	/**
	 * Obtiene la lista con el detalle del pedido de una consultora
	 * @param criteria
	 * @return
	 */
	public List getListaDetallePedido(Map criteria);
	
	/**
	 * Obtiene Suma de Totales de los Pedidos del detalle del pedido de una consultora
	 * @param criteria
	 * @return
	 */
	public String getSumaTotalPedidoListaDetallePedido(Map criteria);
	
	/**
	 * Elimina un o o varios detalles de un pedido
	 * @param criteria
	 */
	public void deleteDetallePedidoOnLine(Map criteria);
	
	/**
	 * Verifica si el pedido tiene detalles
	 * @param criteria
	 * @return
	 */
	public String verificarDetallePedido(Map criteria);
	
	/**
	 * Elimina la cabecera del pedido
	 * @param criteria
	 */
	public void deleteCabeceraPedidoOnLine(Map criteria);
	
	/**
	 * Devuelve 1 si la consultora tiene un bloqueo o excepcion
	 * @param codigoCliente
	 * @return
	 */
	public Integer getBloqueoOnline(Map criteria);
	
	/**
	 * Actualiza el indicador de envio OCS en la tabla cabecera
	 * @param criteria
	 */
	public void actualizaIndicadorOCS(Map criteria);
	
	/**
	 * Metodo que obtiene la cantidad de pedidos para el numero de lote 
	 * @return
	 */
	public String getNumeroLoteByPk(Map criteria);
	
	/**
	 * Metodo que obtiene la fecha de facturacion
	 * @param criteria
	 * @return
	 */
	public String getFechaFacturacion(Map criteria);
	
	/**
	 * Obtiene el numero de secuencia
	 * @param criteria
	 * @return
	 */
	public String getNumeroSecuenciaDocumento(Map criteria);
	
	/**
	 * Obtiene el oid Solicitud
	 * @param criteria
	 * @return
	 */
	public String getOidSolicitud(Map criteria);
	
	/**
	 * Actualiza el indicador de envio OCS en la tabla cabecera
	 * @param criteria
	 */
	public void actualizaIndicadorProcDetal(Map criteria);
	
	/**
	 * Verifica si ya existe un pedido digitado
	 * @param criteria
	 * @return
	 */
	public int existePedido(Map criteria);
	
	/**
	 * Obtiene el oid del Pedido Sicc
	 * @param criteria
	 * @return
	 */
	public String getOidSolicitudPROL(Map criteria);
}
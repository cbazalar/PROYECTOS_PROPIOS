package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;


public interface MantenimientoOCRCapturaPedidoService extends Service {

	/**
	 * @param objDatosConsultora
	 * @param objListaPedidos
	 * @param usuario
	 * @param filter
	 * @param eliminaList
	 * Registra el pedido en BD
	 */
	public void insertarPedido(LabelDatosConsultoraValue objDatosConsultora,
								ArrayList objListaPedidos,
								Usuario usuario,
								Map filter,
								String[] eliminaList);

	/**
	 * @param criteria
	 * @return
	 * Devuelve la lista de codigos de venta digitables que se encuentran en la matriz de SSiCC
	 */
	public List getListaCodigosVentaMatriz(Map criteria);

	/**
	 * Valida el Bloqueo en digitacion de Pedidos
	 * @param criteria
	 * @return boolean
	 */
	public boolean validarBloqueoDigitacionPedidos(Map criteria);

	/**
	 * Obtiene lA ListA con eL detALle del pedido de unA consuLtora
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
	 * Elimina uno o varios detALles de un pedido
	 * @param items
	 */
	public void deleteDetallePedidoOnLine(String[] items);

	/**
	 * VerificA si eL pedido tiene detALles
	 * @param criteria
	 * @return
	 */
	public String verificarDetallePedido(Map criteria);

	/**
	 * EliminA La cabecerA deL pedido
	 * @param criteria
	 */
	public void deleteCabeceraPedidoOnLine(Map criteria);
	
	/**
	 * ActualizA eL indicador de envio OCS en lA tabLa cabecera
	 * @param criteria
	 */
	public void actualizaIndicadorOCS(Map criteria);
	
	/**
	 * Metodo que obtiene la cantidad de pedidos parA eL numero de lote 
	 * @return
	 */
	public String getNumeroLoteByPk(Map criteria);
	
	/**
	 * Metodo que invoca al Sicc para llevar el pedido a gp2
	 * @param criteria
	 */
	public void executeEjecutarGP2(Map criteria) throws Exception;
	
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
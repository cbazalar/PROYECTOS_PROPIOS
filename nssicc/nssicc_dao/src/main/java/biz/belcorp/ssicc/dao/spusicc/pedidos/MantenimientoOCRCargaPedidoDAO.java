package biz.belcorp.ssicc.dao.spusicc.pedidos;


import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.Usuario;


public interface MantenimientoOCRCargaPedidoDAO extends DAO {

    public void insertcabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario);
    public void insertDetallePedido(LabelPedidosValue objPedidoConsultora, Usuario usuario);
    public List getSearchPedidosByCriteria(Map criteria);
    
    public List getDetallePedidosConsultoraByCriteria(Map criteria);    
 
    public void updateDetallePedido(LabelPedidosValue objPedidoConsultora, Usuario usuario);
    
	public void deleteDetallePedido(Map detallePedidos);
	
	public void updateRemoveCabeceraPedido(LabelDatosConsultoraValue consultoraValue, Usuario usuario);
	
	public List getDetallePedidoByFilter(Map filter);
	
	public void executeEnviarDetallesDigitados(Map params);
	
	public String getCodConsultora(Map params);
	
	public void updateCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario);
	
	public List getSearchDetallesByCriteria(Map criteria);
	
	public String getCuponEquivalente(Map criteria);
	
	public void execute(List lista);
	
	public List getDatosTipoConsultora(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve la lista de codigos de venta de la matriz de facturacion en ssicc
	 */
	public List getCodigoVentaList(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Valida el codigo de venta en la matriz 
	 */
	public LabelPedidosValue validaCodigoVenta(Map criteria);
	/**
	 * @return
	 * Devuelve la lista de Tipos de Recepcion disponibles para Web DD
	 */
	public List getTiposRecepcionMICAOCSWebDD();
	
	/**
	 * Devuelve la lista de Tipos de Recepcion disponibles para Deamanda Web DD
	 * @return
	 */
	public List getTiposRecepcionMICAOCSDemandaWebDD();
	
	/**
	 * Devuelve la lista de detalles enviados y no enviados
	 * @param filter
	 * @return
	 */
	public List getTodosDetallesPedidoByFilter(Map filter);
	
	/**
	 * Inserta las solicitudes que se han trasferido
	 * @param map
	 */
	public void saveSolicitudesTransferidas(Map map);
	
	/**
	 * Consolida el pedido online
	 * @param map
	 */
	public void executeConsolidaPedidoOnline(Map map);
	
	public String getOidTerritorioAdministrativo(String codigoCliente);
	
	public Integer validarCUVFaltanteAnunciado(Map map);
	
	/**
	 * Obtiene el monto de una oferta compuesta; devuleve cero si no es una compuesta
	 * @param map
	 * @return
	 */
	public String validarCUVCompuesta(Map map);	
	
	public Integer validarCUVLimiteVenta(Map map);
	
	/**
	 * Obtiene el Monto Minimo de una Consultora
	 * @param criteria
	 */
	public void executeGetMontoMinimoConsultora(Map criteria);
}

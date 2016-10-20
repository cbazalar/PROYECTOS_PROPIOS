package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;


public interface MantenimientoOCRCargaPedidoService extends Service {
	
    /**
     * Obtiene un listado de todos los  Archivos de Control de Facturacion  
     * los cuales son enviados a travs de un Map.
     * 
     * @param criteria
     *            Objeto Map cuyos atributos son usados como criterios de
     *            bsqueda.
     * 
     * @return Lista de objetos Map, poblados.
     */
 
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
     * Inserta un objeto de Pedido
     * @param
     */
    public void insertCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario);
    public void insertDetallePedido(ArrayList objListaPedidos, Usuario usuario);    
    public List getSearchPedidosByCriteria(Map criteria);

    public List getDetallePedidosConsultoraByCriteria(Map criteria);
 
	public void updateRemoveCabeceraPedido(LabelDatosConsultoraValue consultoraValue, Usuario usuario);
	
	public List getDetallePedidoByFilter(Map filter);
	
	public void executeEnviarDetallesDigitados(Map params);
	
	public String getCodConsultora(Map params);
	
	public void updateCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario);
	
	public List getSearchDetallesByCriteria(Map criteria);
	
	public void execute(List lista);
	
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve la lista de codigos de venta de la matriz de facturacion de ssicc
	 */
	public List getCodigoVentaList(Map criteria);

	/**	 * 
	 * @return
	 * Devuel la lista de Tipos de Recepcion disponibles para WEB DD
	 */
	public List getTiposRecepcionMICAOCSWebDD();

	/**
	 * Devuel la lista de Tipos de Recepcion disponibles para Demanda WEB DD
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
}
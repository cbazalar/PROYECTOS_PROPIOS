package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;


public interface ConsultaOCRRecepcionPedidosService extends Service {
	
	 /**
	  * Metodo que devuelve la lista de recepcion de pedidos actual
	 * @param criteria
	 * @return
	 */
	public List getListaRecepcionPedidosActual(Map criteria);
	   
	/**
	 * Metodo que devuelve la lista de recepcion de pedidos historico
	 * @param criteria
	 * @return
	 */
	public List getListaRecepcionPedidosHistorico(Map criteria);
	   /**
	    * Metodo que de devuelve el detalle de los pedidos historicos
	 * @param criteria
	 * @return
	 */
	public List getDetalleRecepcionPedidosHistorico(Map criteria);
	
	/**
	 * Metodo que de devuelve el detalle de los pedidos actuales
	 * @param criteria
	 * @return
	 */
	public List getDetalleRecepcionPedidosActual(Map criteria);
	
}
package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ConsultaOCRRecepcionPedidosDAO extends DAO {
	
	
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
     *  Metodo que de devuelve el detalle de los pedidos historicos
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

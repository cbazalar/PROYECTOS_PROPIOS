package biz.belcorp.ssicc.dao.soa;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Danny Amaro
 *
 */
public interface PedidoDAO extends DAO{
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getInformePedidos (Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getResumenPedidos (Map criteria);

	/**
	 * Consolida los pedidos de datmart con los de sicc
	 * @param list
	 * @param criteria
	 * @return
	 */
	public List getConsolidaPedidos(List list, Map criteria);

	/**
	 * Retona el detalle de los peididos segun num documento
	 * @param criteria
	 * @return
	 */
	public List getDetalleInformePedidos(Map criteria);
	
	/**
	 * inserta los pedidos de datamart
	 * @param list
	 * @param criteria
	 */
	public void insertPedidosDatamart(final List list,final Map criteria)throws Exception ;

	/**retorna la fecha de actualizacoion de sicc
	 * @return
	 */
	public Date getFechaUltimaActualizacion();

	/**
	 * verificacmos si existe el peidido ya registrado
	 * @param map
	 * @return
	 */
	public Integer getPedidoDatam(Map map);

	/**
	 * inserta el pedido
	 * @param map
	 */
	public void insertPedidoDatam(Map map);

	/**Actualiza el pedido
	 * @param map
	 */
	public void updatePedidoDatam(Map map);

	/**
	 * actualiza la fecha de actualizacion del sicc
	 */
	public void updateFechaUltimaActualizacion(Map map) throws Exception;

	/**
	 * Retorna la moneda de un pas
	 * @param criteria
	 * @return
	 */
	public List getMoneda(Map criteria);


}

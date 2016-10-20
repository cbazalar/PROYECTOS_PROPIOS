package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO extends DAO {

	/**
	 * Verifica si una region ha sido procesada para un periodo dado.
	 * 
	 * @param params
	 * @return
	 */
	public boolean verificaRegionProcesada(Map params);
	
	/**
	 * Proceso que Genera numero de Pedidos x Seccion al cierre de Region
	 * 
	 * @param params
	 */
	public void executeGenerarNumeroPedidosSeccionPeriodo(Map params);

	/**
	 * Devulve la lista de regiones que han cerrado en el peridod
	 * @param params
	 * @return
	 */
	public List getRegionesCerradas(Map params);
	/**
	 * Retorna 1: si la seccion es valida y 0 si no existe o esta inactiva
	 * @param mapRow
	 * @return
	 */
	public Integer getIndicadorValidoSeccion(Map mapRow);

	/**Retorna 1: si la zona es valida y 0 si no existe o esta inactiva
	 * @param criteria
	 * @return
	 */
	public Integer getIndicadorValidoZona(Map criteria);

	/**
	 * Retorna 1 si hay registro caso contario 0
	 * @param mapRow
	 * @return
	 */
	public Integer getPedidoObjetivo(Map mapRow);

	/**
	 * Inserta la seccion de numero de pedido
	 * @param mapRow
	 */
	public void insertSeccionNumeroPedido(Map mapRow);

	/**
	 * Retorna 1: si la zona es nueva o  rezonificada y 0 si no existe
	 * @param mapRow
	 * @return
	 */
	public Integer getZonaNuevaRezonificada(Map mapRow);
	
	/**
	 * Retorna 1: si la seccion es nueva y 0 si no existe
	 * @param mapRow
	 * @return
	 */
	public Integer getSeccioNueva(Map mapRow);

	/**
	 * actualiza la seccion de numero de pedido
	 * @param mapRow
	 */
	public void updateSeccionNumeroPedido(Map mapRow);

	/**
	 * Limpia la tabla temporal que se usa en la validacion de la carga de pedidos objetivos
	 */
	public void deleteTemporalCargaPedidosObjetivos();

	/**
	 * Inserta los pedidos objetivos que pasaron la validacion del excel en la tabla temporal que se usa en la validacion de la carga de pedidos objetivos
	 * @param params
	 */
	public void insertTemporalCargaPedidosObjetivos(Map params);

	/**
	 * Ejecuta la validacion de los pedidos estimados
	 * @param params
	 */
	public void executeValidacionPedidosEstimados(Map params);

}

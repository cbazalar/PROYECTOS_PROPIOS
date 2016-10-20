package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextjrios
 */

public interface ProcesoSTOCambioTipoOrdenPedidosCargadosDAO extends DAO{
	
	/**
	 * Retorna la lista de clientes que cumplen con la restriccion de numero de lote y lista de clientes
	 * @param criteria
	 * @return
	 */
	public List getClientesTipoOrdenList(Map criteria);
	
	/**
	 * Actualiza los tipos de Orden de Pedido segun los filtros seleccionados
	 * @param criteria
	 * @return
	 */
	public void updateTipoOrdenPedidosCargados(Map criteria);
}
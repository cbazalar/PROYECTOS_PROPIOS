package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextjrios
 */

public interface ProcesoSTOCambioTipoOrdenPedidosCargadosService extends Service{

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
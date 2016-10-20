package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ListaBlancaProductos;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoRECListaBlancaProductosService extends Service{

	/**
	 * Metodo que devuelve la lista de Motivo de Devolucin
	 * @return
	 */
	public List getListMotivoDevolucion();
	
	/**
	 * Metodo que devuelve la lista Blanca de los Productos
	 * @param criteria
	 * @return
	 */
	public List getListaBlancaProductosList(Map criteria);
	
	/**
	 * Metodo que inserta en tabla STO_LISTA_BLANC
	 * @param criteria
	 * @return
	 */
	public String insertListaBlancaProductos(Map criteria);
	
	/**
	 * Metodo que actualiza la tabla STO_LISTA_BLANC
	 * @param criteria
	 * @return
	 */
	public String updateListaBlancaProductos(Map criteria);
	
	/**
	 * Metodo que captura los datos de la tabla STO_LISTA_BLANC
	 * @param criteria
	 * @return
	 */
	public ListaBlancaProductos consultarListaBlancaProductos(Map criteria);
	
	/**
	 * Metodo que elimina el datos de la tabla STO_LISTA_BLANC
	 * @param criteria
	 * @return
	 */
	public void deleteListaBlancaProductos(Map criteria);

	/**
	 * Valida existencia del cdigo del cliente 1: existe, 0: no existe
	 * @author sguerra
	 * @param trim
	 * @return
	 */
	public Integer getValidaCodigoCliente(String value);
}

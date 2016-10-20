package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ListaBlancaProductos;

public interface MantenimientoRECListaBlancaProductosDAO extends DAO{

	/**
	 * Metodo que devuelve la lista de Motivo de Devolucin
	 * @return
	 */
	public List getListMotivoDevolucion();
	
	/**
	 * Metodo que devuelve la lista de Motivo de Devolucin
	 * @param criteria
	 * @return
	 */
	public List getListaBlancaProductosList(Map criteria);
	
	/**
	 * Metodo que devuelve la cantidad que encontr 
	 * deacuerdo a los parametros enviados
	 * @param criteria
	 * @return
	 */
	public int getValidaListaBlancaProductos(Map criteria);
	
	/**
	 * Metodo que inserta en la tabla STO_LISTA_BLANC
	 * @param criteria
	 * @return
	 */
	public void insertListaBlancaProductos(Map criteria);
	
	/**
	 * Metodo que actualiza la tabla STO_LISTA_BLANC
	 * @param criteria
	 * @return
	 */
	public void updateListaBlancaProductos(Map criteria);
	
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
	 * Retorna la cantidad de la lista que coinciden con los parametros
	 * @param criteria
	 * @return
	 */
	public int getValidaListaBlanca(Map criteria);
	
	public int getValidaRegionxZona(Map criteria);
	
	/**
	 * Valida existencia del código del cliente 1: existe, 0: no existe
	 * @author sguerra
	 * @param trim
	 * @return
	 */
	public Integer getValidaCodigoCliente(String value);

}

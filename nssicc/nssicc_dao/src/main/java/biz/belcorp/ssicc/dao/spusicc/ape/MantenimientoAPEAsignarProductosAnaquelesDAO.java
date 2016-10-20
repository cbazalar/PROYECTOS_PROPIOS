package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPEAsignarProductosAnaquelesDAO extends DAO{

	/**
	 * Devuelve la lista de Productos Anaqueles Asignados
	 * @param criteria
	 * @return
	 */
	public List getProductosAnaquelesList(Map criteria);
	
	/**
	 * Devuelve el oid de la asignacion de Producto a Anaquel segun el codigo de 
	 * version ingresado
	 * @param criteria
	 * @return
	 */
	public String getOidAsignacionVersion(Map criteria);
	
	/**
	 * Desasigna un Anaquel de un producto
	 * @param criteria
	 */
	public void desasignarProductoAnaquel(Map criteria);
	
	/**
	 * Obtiene la lista de Anaqueles del Mapa Centro de Distribucion
	 * @param criteria
	 * @return
	 */
	public List getAnaquelesDestinoList(Map criteria);
	
	/**
	 * Asigna un Anaquel de un producto
	 * @param criteria
	 */
	public void asignarProductoAnaquel(Map criteria);
	
	/**
	 * Realiza las validaciones antes de asignar el producto al anaquel
	 * @param criteria
	 */
	public void validaProductoAnaquel(Map criteria);
	
	/**
	 * Realiza el Intercambio de los Porductos entrre dos anaqueles
	 * @param criteria
	 */
	public void intercambioProductoAnaquel(Map criteria);
	
	/**
	 * Devuelve el oid del porducto que se encuentra asignado al Anaquel
	 * Seleccionado
	 * @param map
	 * @return
	 */
	public String getOidProductoAsignacionDetalle(Map map);
	
	/**
	 * Obtiene el oid del Producto
	 * @param criteria
	 * @return
	 */
	public String getOidProductoByCodigoyPais(Map criteria);
	
	/**
	 * Obtiene el Mapa Centro Detalle y la SubLinea
	 * @param criteria
	 * @return
	 */
	public String getMapaCentroSubLineaList(Map criteria);
	
	/**
	 * Obtiene la SubLinea del Mapa Detalle
	 * @param criteria
	 * @return
	 */
	public String getObtenerSubLineaxOidMapaCDDet(Map criteria);
	
	/**
	 * Obtiene la Asignacion detalle
	 * @param criteria
	 * @return
	 */
	public String getOidAsignacionProdAnaDet(Map criteria);
}
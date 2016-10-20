package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEAsignarProductosAnaquelesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface MantenimientoAPEAsignarProductosAnaquelesService extends Service{

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
	 * Realiza la carga del archivo excel
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public List executeCargaArchivoExcel(Map criteria) throws Exception ;	
}
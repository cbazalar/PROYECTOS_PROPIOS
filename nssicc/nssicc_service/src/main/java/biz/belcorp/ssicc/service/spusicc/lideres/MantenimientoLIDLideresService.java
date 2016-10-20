package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.List;
import java.util.Map;

import javax.naming.directory.InvalidAttributeValueException;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextsapaza
 *
 */
public interface MantenimientoLIDLideresService extends Service {
	
	/**
	 * Obtiene datos de las secciones de acuerdo a los criterios de busqueda pasados
	 * como parametro
	 * 
	 * @param criteria
	 * @return
	 */
	public List getSeccionesByCriteria(Map criteria) throws Exception;

	/**
	 * Ejecuta las diferentes validaciones para saber si se puede realizar la asignacion
	 * de la lider a una respectiva seccion
	 * 
	 * @param params
	 * @return
	 */
	public String validarAsignacionLiderSeccion(Map params);

	/**
	 * Realiza la asignacion/desasignacion de la lider a la seccion.
	 * 
	 * @param criteria
	 */
	public void execAsignacionLiderSeccion(Map criteria) throws InvalidAttributeValueException ;
	
	/**
	 * Retorna la lista de condicon de despachos activas 
	 * @return
	 */
	public List getListCondicionesLideres();

	/**
	 * Retorna la lista de Objeto de Condicion de Despacho de la Canasta
	 * @param codigoPeriodo
	 * @return
	 */
	public List getListCondicionDespachoCanasta(String codigoPeriodo);

	/**
	 * Inserta la Condicion del Despacho de la Canasta
	 * @param map
	 */
	public void insertCondicionDespachoCanasta(Map map);

	/**
	 * Actualiza la Condicion del Despacho de la Canasta
	 * @param map
	 */
	public void updateCondicionDespachoCanasta(Map map);

	/**
	 * Retorna la lista de productos de la canasta
	 * @param codigoPeriodo
	 * @return
	 */
	public List getListProductosCanasta(String codigoPeriodo);
	
	/**
	 * Inserta los Productos  de la Canasta
	 * @param map
	 */
	public void insertProductosCanasta(Map map);

	/**
	 * Actualiza los Productos de la Canasta
	 * @param map
	 */
	public void updateProductosCanasta(Map map);

	/**
	 * Retorna la lista de tipo de ofertas
	 * @return
	 */
	public List getListTipoOferta();

	/**
	 * Recorre la lista de producto para insertarlo en la cansta de lideres
	 * @param map
	 */
	public void insertListProductosCanasta(Map map);


}

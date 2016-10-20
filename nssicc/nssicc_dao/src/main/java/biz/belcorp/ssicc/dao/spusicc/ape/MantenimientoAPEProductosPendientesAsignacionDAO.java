package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author cgonzales@csigcomt.com
 *
 */

public interface MantenimientoAPEProductosPendientesAsignacionDAO extends DAO {

	
	/**
	 * Recupera la lista de productos pendientes de asignacion
	 * 
	 * @param criteria
	 * @return
	 */
	public List getProductosPendientesAsignacionList(Map criteria);

	
	/**
	 * Devuelve el oid del periodo por Marca y Canal
	 * @param criteria
	 * @return
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria);


	/**
	 * Devuelve una lista de anaqueles sin productos asignadi
	 * @param criteria
	 * @return
	 */
	public List getAnaquelesSinProductosAsignados(Map criteria);
	
	/**
	 * Devueleve la descripcion del producto
	 * @param criteria
	 * @return
	 */
	public String getDescripconProductoAPE(Map criteria);
	
}

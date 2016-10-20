package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEProductosPendientesAsignacionForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cgonzales@csigcomt.com">Christian Gonzales</a>
 */

public interface MantenimientoAPEProductosPendientesAsignacionService extends Service {

	/**
	 * Recupera los productos pendientes por asignacion
	 * 
	 * @param criteria
	 * @return List
	 */
	
	public List getProductosPendientesAsignacionList(Map criteria);
	
	
	/**
	 * Devuelve el oid del periodo por Marca y Canal
	 * @param criteria
	 * @return
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria);

	
	/**
	 * Devuelve la lista de anaqueles sin productos asignados
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

/**
 *
 */
package biz.belcorp.ssicc.service.spusicc.sessionexperte;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoPeriodo;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="MantenimientoSSEProgramaSessionExperteService.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 * 
 */
public interface MantenimientoSSEProductoPeriodoService extends Service {
	
	/**
	 * Obtiene un listado de todos los productos de los periodos de los programas session experte con sus respectivos productos segun criteria.
	 * @param criteria Objeto ProductoPeriodo cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos ProductoPeriodo, poblados.
	 */
    public List getProductosPeriodosByCriteria(ProductoPeriodo producto);
    
	/**
	 * Obtiene un listado de todos los periodos de los programas session experte con sus respectivos productos segun criteria.
	 * @param criteria Objeto ProductoPeriodo cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos BaseGenerico (codigo y descripcion).
	 */
	public List getBaseProductosPeriodosByCriteria(ProductoPeriodo producto);
    
	/**
	 * Obtiene un producto de Periodo de Programa Session Experte indicado.
	 * @param producto Objeto de tipo Producto de Periodo de Programa session experte.
	 * @return  ProductoPeriodo.
	 * */
	public ProductoPeriodo getFirstFromProductosPeriodosByCriteria(ProductoPeriodo producto);
	
	/**
	 * Inserta un ProductoPeriodo
	 * @param producto ProductoPeriodo.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProductoPeriodo(ProductoPeriodo producto, Usuario usuario);
	
	/**
	 * Actualiza un ProductoPeriodo
	 * @param producto ProductoPeriodo.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProductoPeriodo(ProductoPeriodo producto, Usuario usuario);
	
	/**
	 * Cambia el estado a INACTIVO del ProductoPeriodo (No lo elimina).
	 * @param producto ProductoPeriodo.
	 * @param usuario Usuario que elimina.
	 */
	public void deleteProductoPeriodo(ProductoPeriodo producto, Usuario usuario);
	
	/**
	 * Ejecuta el proceso principal de cierre de programas de consultoras session experte. 
	 */
	public void executeProcesoSSEProcesaCierreConsultoraSessionExperte(Map queryParams) ;
}

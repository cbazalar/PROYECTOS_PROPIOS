package biz.belcorp.ssicc.dao.spusicc.sessionexperte;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.PeriodoPrograma;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoPeriodo;

/**
 * TODO Include class description here.
 *
 * <p>
 * <a href="MantenimientoSSEProductoDeProgramaSSEDAO.java.html"> <i>View Source</i> </a>
 * </p>
 *
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 *
 */
public interface MantenimientoSSEProductoPeriodoDAO extends DAO {


	/**
	 * Obtiene un listado de todos los periodos de los programas session experte con sus respectivos productos segun criteria.
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
	 * Inserta un Programa SessionExperte.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProductoPeriodo(ProductoPeriodo producto, Usuario usuario);

	/**
	 * Actualiza un Programa SessionExperte.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProductoPeriodo(ProductoPeriodo producto, Usuario usuario);

	/**
	 * Actualiza un Programa SessionExperte a estado INACTIVO.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateInactivarProductoPeriodo(ProductoPeriodo producto, Usuario usuario);
	
	/**
	 * Eliminar una lista de productos del periodo.
	 * @param periodo PeriodoPrograma con  lista de objetos a eliminar.
	 * @param usuario Usuario que actualiza.
	 */
	public void deleteListaProductosNoAsignadosPeriodo(PeriodoPrograma periodo);

	
	/**
	 * Obtiene un listado de todos los codigos de productos segun criteria.
	 * @param periodo Objeto PeriodoPrograma cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de codigos de producto.
	 */
	public List getCodigoProductosPeriodosByCriteria(PeriodoPrograma periodo);
	
	/**
	 * Obtiene el codigo de venta de un producto segun codigo SAP de producto y periodo.
	 * @param periodo Objeto PeriodoPrograma cuyos atributos son usados como criterios de bsqueda.
	 * @return Producto del Periodo con el codigo de venta lleno.
	 */
	public ProductoPeriodo getCodigoVentaProductoByCriteria(ProductoPeriodo producto);

	/**
	 * Ejecuta el proceso principal de cierre de programas de consultoras session experte. 
	 */
	public void executeProcesoSSEProcesaCierreConsultoraSessionExperte(Map queryParams);

}

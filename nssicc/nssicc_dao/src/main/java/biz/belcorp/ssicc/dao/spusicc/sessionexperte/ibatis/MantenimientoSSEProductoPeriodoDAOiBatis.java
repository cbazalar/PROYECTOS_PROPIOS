package biz.belcorp.ssicc.dao.spusicc.sessionexperte.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProductoPeriodoDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.PeriodoPrograma;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoPeriodo;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoCUPProgDsctoDAOiBatis.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 * 
 */
@Repository("spusicc.sessionexperte.mantenimientoSSEProductoPeriodoDAO")
public class MantenimientoSSEProductoPeriodoDAOiBatis extends BaseDAOiBatis 
	implements MantenimientoSSEProductoPeriodoDAO {
	
	/**
	 * Obtiene un listado de todos los periodos de los programas session experte con sus respectivos productos segun criteria.
	 * @param criteria Objeto ProductoPeriodo cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos ProductoPeriodo, poblados.
	 */
	public List getProductosPeriodosByCriteria(ProductoPeriodo producto) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.ProductoPeriodoSQL.getProductosPeriodosByCriteria", producto);
	}
	
	/**
	 * Obtiene un listado de todos los periodos de los programas session experte con sus respectivos productos segun criteria.
	 * @param criteria Objeto ProductoPeriodo cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos BaseGenerico (codigo y descripcion).
	 */
	public List getBaseProductosPeriodosByCriteria(ProductoPeriodo producto) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.ProductoPeriodoSQL.getBaseProductosPeriodosByCriteria", producto);
	}
	
	/**
	 * Inserta un Programa SessionExperte.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProductoPeriodo(ProductoPeriodo producto, Usuario usuario) {
		producto.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		getSqlMapClientTemplate().insert(
				"spusicc.sessionexperte.ProductoPeriodoSQL.insertProductoPeriodo", producto);
	}

	/**
	 * Actualiza un Programa SessionExperte.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProductoPeriodo(ProductoPeriodo producto, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.ProductoPeriodoSQL.updateProductoPeriodo", producto);
	}

	/**
	 * Actualiza un Programa SessionExperte a estado INACTIVO.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateInactivarProductoPeriodo(ProductoPeriodo producto, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.ProductoPeriodoSQL.deleteProductoPeriodo", producto);
	}

	/**
	 * Eliminar una lista de productos del periodo.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void deleteListaProductosNoAsignadosPeriodo(PeriodoPrograma periodo) {
		log.debug("DAO: deleteListaProductosNoAsignadosPeriodo: Eliminar productos NO ASIGNADOS");
		getSqlMapClientTemplate().delete(
				"spusicc.sessionexperte.ProductoPeriodoSQL.deleteListaProductosNoAsignadosPeriodo", periodo);
	}
	
	/**
	 * Obtiene un listado de todos los codigos de productos segun criteria.
	 * @param periodo Objeto PeriodoPrograma cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de codigos de producto.
	 */
	public List getCodigoProductosPeriodosByCriteria(PeriodoPrograma periodo){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.ProductoPeriodoSQL.getCodigoProductosPeriodosByCriteria", periodo);
	}
	
	/**
	 * Obtiene el codigo de venta de un producto segun codigo SAP de producto y periodo.
	 * @param periodo Objeto PeriodoPrograma cuyos atributos son usados como criterios de bsqueda.
	 * @return Producto del Periodo con el codigo de venta lleno.
	 */
	public ProductoPeriodo getCodigoVentaProductoByCriteria(ProductoPeriodo producto){
		return (ProductoPeriodo)getSqlMapClientTemplate().queryForObject(
				"spusicc.sessionexperte.ProductoPeriodoSQL.getCodigoVentaProductoByCriteria", producto);
	}

	/**
	 * Ejecuta el proceso principal de cierre de programas de consultoras session experte. 
	 */
	public void executeProcesoSSEProcesaCierreConsultoraSessionExperte(Map queryParams) {
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.ProductoPeriodoSQL.executeProcesoSSEProcesaCierreConsultoraSessionExperte",
				queryParams);
	}

}

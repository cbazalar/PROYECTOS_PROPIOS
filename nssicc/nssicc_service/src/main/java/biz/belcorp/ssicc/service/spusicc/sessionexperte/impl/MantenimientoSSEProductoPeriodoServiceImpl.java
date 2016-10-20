package biz.belcorp.ssicc.service.spusicc.sessionexperte.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProductoPeriodoDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoPeriodo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sessionexperte.MantenimientoSSEProductoPeriodoService;

/**
 * <p>
 * <a href="MantenimientoSSEProgramaSessionExperteService.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 */
@Service("spusicc.mantenimientoSSEProductoPeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSSEProductoPeriodoServiceImpl extends BaseService implements MantenimientoSSEProductoPeriodoService {

	@Resource(name="spusicc.sessionexperte.mantenimientoSSEProductoPeriodoDAO")
	MantenimientoSSEProductoPeriodoDAO mantenimientoDAO;

	/**
	 * Obtiene un listado de todos los productos de los periodos de los programas session experte con sus respectivos productos segun criteria.
	 * @param criteria Objeto ProductoPeriodo cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos ProductoPeriodo, poblados.
	 */
    public List getProductosPeriodosByCriteria(ProductoPeriodo producto) {
        return mantenimientoDAO.getProductosPeriodosByCriteria(producto);
    }

	/**
	 * Obtiene un listado de todos los periodos de los programas session experte con sus respectivos productos segun criteria.
	 * @param criteria Objeto ProductoPeriodo cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos BaseGenerico (codigo y descripcion).
	 */
	public List getBaseProductosPeriodosByCriteria(ProductoPeriodo producto){
        return mantenimientoDAO.getBaseProductosPeriodosByCriteria(producto);
	}
    
	/**
	 * Obtiene un producto de Periodo de Programa Session Experte indicado.
	 * @param producto Objeto de tipo Producto de Periodo de Programa session experte.
	 * @return  ProductoPeriodo.
	 * */
	public ProductoPeriodo getFirstFromProductosPeriodosByCriteria(ProductoPeriodo producto){
		return (ProductoPeriodo)(mantenimientoDAO.getProductosPeriodosByCriteria(producto)).get(0);
	}
    
	/**
	 * Inserta un ProductoPeriodo
	 * @param producto ProductoPeriodo.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProductoPeriodo(ProductoPeriodo producto, Usuario usuario){
		producto.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		mantenimientoDAO.insertProductoPeriodo(producto, usuario);
	}
	
	/**
	 * Actualiza un ProductoPeriodo
	 * @param producto ProductoPeriodo.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProductoPeriodo(ProductoPeriodo producto, Usuario usuario){
		mantenimientoDAO.updateProductoPeriodo(producto, usuario);
	}
	
	/**
	 * Cambia el estado a INACTIVO del ProductoPeriodo (No lo elimina).
	 * @param producto ProductoPeriodo.
	 * @param usuario Usuario que elimina.
	 */
	public void deleteProductoPeriodo(ProductoPeriodo producto, Usuario usuario){
		producto.setEstadoRegistro(Constants.ESTADO_INACTIVO);
		mantenimientoDAO.updateInactivarProductoPeriodo(producto, usuario);
	}

	/**
	 * Ejecuta el proceso principal de cierre de programas de consultoras session experte. 
	 */
	public void executeProcesoSSEProcesaCierreConsultoraSessionExperte(Map queryParams) {
        mantenimientoDAO.executeProcesoSSEProcesaCierreConsultoraSessionExperte(queryParams);
	}
}

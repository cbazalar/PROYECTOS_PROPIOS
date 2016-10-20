/*
 * Created on 25-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.spusicc.sessionexperte.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProductoDeProgramaSSEDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.LabelDatosProductoValue;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoDeProgramaSessionExperte;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoSSEProductoDeProgramaSSEDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 * 
 */
@Repository("spusicc.sessionexperte.mantenimientoSSEProductoDeProgramaSSEDAO")
public class MantenimientoSSEProductoDeProgramaSSEDAOiBatis extends BaseDAOiBatis 
	implements MantenimientoSSEProductoDeProgramaSSEDAO {

	/**
	 * Obtiene un listado de los productos del Programa Session Experte indicado.
	 * @param producto Objeto de tipo Programa session experte.
	 * */
	public List getProductosDeProgramaSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.getProductosDeProgramaSessionExperte", producto);
	}

	/**
	 * Obtiene un listado de los productos del Programa Session Experte indicado.
	 * @param producto Objeto de tipo ProductoDeProgramaSessionExperte.
	 * @return Lista de objetos de tipo BaseGenerico (codigo y descripcion).
	 * */
	public List getBaseProductosDeProgramaSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.getBaseProductosDeProgramaSessionExperte", producto);
	}
	
	/**
	 * Inserta un Producto de Programa SessionExperte
	 * @param producto  Producto de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProductoDeProgramaSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.insertProductoDeProgramaSessionExperte", producto);
	}

	/**
	 * Actualiza un Producto de Programa SessionExperte
	 * @param producto Producto de Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProductoDeProgramaSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario){
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.updateProductoDeProgramaSessionExperte", producto);
	}
	
	/**
	 * Cambia el estado a INACTIVO del Producto de Programa SessionExperte (No lo elimina).
	 * @param producto Producto de Programa SessionExperte.
	 * @param usuario Usuario que elimina.
	 */
	public void updateInactivarProductoDeProgramaSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario){
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.deleteProductoDeProgramaSessionExperte", producto);
	}
	
	public void insertProductoDeProgramaCicloSSE(ProductoDeProgramaSessionExperte cabecera, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.insertProductoDeProgramaCicloSSE", cabecera);
	}
	
	public void updateProductoDeProgramaCicloSSE(ProductoDeProgramaSessionExperte producto, Usuario usuario){
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.updateProductoDeProgramaCicloSSE", producto);
	}

	public void deleteProductoDeProgramaCicloSessionExperte(ProductoDeProgramaSessionExperte productoCiclo, Usuario usuario){
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.deleteProductoDeProgramaCicloSessionExperte", productoCiclo);
	}
	
	public List getCiclosDeProductoSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.getCiclosDeProductoSessionExperteByCriteria", producto);
	}
	
	public ProductoDeProgramaSessionExperte getFirstFromCiclosDeProductoSSEByCriteria(ProductoDeProgramaSessionExperte producto){
		return (ProductoDeProgramaSessionExperte) getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.getCiclosDeProductoSessionExperteByCriteria", producto).get(0);

	}
	
	/**
	 * Obtiene los datos de un producto del Programa Session Experte indicado buscando en la tabla maestra de Productos.
	 * @param producto Objeto de tipo Producto de Programa session experte.
	 * @return Descripcin de ProductoDeProgramaSessionExperte.
	 * */
	public LabelDatosProductoValue getDatosProductoByCriteria(ProductoDeProgramaSessionExperte producto){
		return (LabelDatosProductoValue)getSqlMapClientTemplate().queryForObject(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.getDatosProductoByCriteria", producto);
	}

	public String getCodigoProductoPrograma(ProductoDeProgramaSessionExperte producto){
		return (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.sessionexperte.ProductoSessionExperteSQL.getCodigoProductoPrograma", producto);
	}
}

/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author 
 * Cristhian Roman
 */
public interface MantenimientoRECProductosFFNNEEDAO extends DAO {

	
	/**
	 * Metodo que devuelve la lista de productos
	 * @param map
	 * @return
	 */
	public List getProductosFFNNEEList(Map map);
	

	/**
	 * Metodo que devuelve la descripcion del producto
	 * @param map
	 * @return
	 */
	public String getDescripcionProducto(Map map);
	
	
	/**
	 * Metodo para devolver el oid del producto
	 * @param map
	 * @return
	 */
	public String getOidProducto(Map map);
	
	
	/**
	 * Metodo que borra los registros de la tabla REC_PRODU_FM
	 * @param map
	 */
	public void deleteProductosFFNNEE(Map map);

	/**
	 * Metodo que inserta en REC_PRODU_FM
	 * @param map
	 */
	public void insertProductosFFNNEE(Map map);
	
	/**
	 * Metodo que valida si el producto ya fue insertado
	 * @param map
	 * @return
	 */
	public String validaProductoFFNNEE(Map map);
	
	/**
	 * Devuelve la ruta para guardar el archivo 
	 * @param codigoPais
	 * @return
	 */
	public String obtenerPathUpload(String codigoPais);
	
	/**
	 * Devuelve codigo CUV Ficticio en base al oid del producto
	 * @param map
	 * @return
	 */
	public String getCodigoCUVFicticioProducto(Map map);
}

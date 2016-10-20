package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoCajaProducto;

/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPETipoCajaProductoDAO extends DAO{

	/**
	 * Obtiene la Lista de Tipo de Cajas Producto
	 * @param criteria
	 * @return
	 */
	public List getTipoCajaProdList(Map criteria);
	
	/**
	 * Obtiene el objeto Tipo Caja Producto
	 * @param criteria
	 * @return
	 */
	public TipoCajaProducto getTipoCajaProductoObject(Map criteria);
	
	/**
	 * Inserta un Tipo de Caja Producto
	 * @param criteria
	 */
	public void insertTipoCajaProducto(Map criteria);
	
	/**
	 * Valida si ya existe el codigo ingresado
	 * @param criteria
	 * @return
	 */
	public int getExisteCodTipoCajaProducto(Map criteria);
	
	/**
	 * Obtiene el siguiente oid de Tipo Caja Producto
	 * @param criteria
	 * @return
	 */
	public int getNextOidTipoCajaProducto(Map criteria);
	
	/**
	 * Elimina un Tipo de Caja Producto
	 * @param map
	 */
	public void deleteTipoCajaProducto(Map criteria);
	
	/**
	 * Metodo que valida que no existan registros en las tablas hijas de la tabla APP_TIPO_CAJA_PRODU
	 * @param criteria
	 */
	public void executeValidarRegistrosTipoCajaProducto(Map criteria);
	
	/**
	 * Elimina de la tabla de idiomas
	 * @param criteria
	 */
	public void deleteIdiomaTipoCajaProducto(Map criteria);
}
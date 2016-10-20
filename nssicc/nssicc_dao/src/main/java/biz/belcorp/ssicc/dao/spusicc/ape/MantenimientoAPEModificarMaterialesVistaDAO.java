package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.MaterialesVista;
/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPEModificarMaterialesVistaDAO extends DAO{

	/**
	 * Retorna los Codigos de Tipo deDispensacion
	 * @return
	 */
	public List getTipoDispensacionList(Map criteria);

	/**
	 * Devuelve la lista de Artculos a Modificar en APE
	 * @param criteria
	 * @return
	 */
	public List getMaterialesVistaList(Map criteria);
	
	/**
	 * Obtiene el Objeto Materiales Vista APE
	 * @param criteria
	 * @return
	 */
	public MaterialesVista getMaterialesVistaObject(Map criteria);
	
	/**
	 * Retorna los de Tipo Caja Producto
	 * @return
	 */
	public List getCodTipoCajaProductoList(Map criteria);
	
	/**
	 * Actualiza en la tabla de maestro productos de APE
	 * @param criteria
	 */
	public void updateMaterialesVista(Map criteria);
	
	/**
	 * Obtiene el oid del Tipo de Dispensacion
	 * @param criteria
	 * @return
	 */
	public String getOidTipoDispensacionByCodigo(Map criteria);
	
	/**
	 * Obtiene el oid de Tipo de Anaquel
	 * @param criteria
	 * @return
	 */
	public String getOidTipoAnaquelbyCodigo(Map criteria);
	
	/**
	 * Obtiene la lista de Tipo de Anaquel por Producto
	 * @param criteria
	 * @return
	 */
	public List getTipoAnaquelProductoList(Map criteria);
	
	/**
	 * Inserta un Tipo de Anaquel al Producto Seleccionado
	 * @param criteria
	 */
	public void insertTipoAnaquelProducto(Map criteria);
	
	/**
	 * Elimina un Tipo de Anaquel al Producto Seleccionado
	 * @param criteria
	 */
	public void deleteTipoAnaquelProducto(Map criteria);
	
	/**
	 * Actualiza los campos del Tipo de Anaquel para el producto 
	 * Seleccionado
	 * @param criteria
	 */
	public void updateTipoAnaquelProducto(Map criteria);
	
	/**
	 * Elimina un Tipo de Anaquel al Producto Seleccionado por el Oid
	 * @param OidSap
	 */
	public void deleteTipoAnaquelbyOidProducto(String OidSap);
	
	/**
	 * Valida si ya existe el Tipo de Anaquel para el Producto seleccionado
	 * @param criteria
	 * @return
	 */
	public String getExisteTipoAnaquelProducto(Map criteria);
	
	/**
	 * Devuelve el numero de Prioridad Maximo para el producto seleccionado
	 * @param criteria
	 * @return
	 */
	public int getMaxNumeroPrioridad(Map criteria);
	
	/**
	 * Devuelve el oid del producto de APE
	 * @param criteria
	 * @return
	 */
	public String getOidProductoAPE(Map criteria);
	
	/**
	 * Realiza las validaciones antes de asignar el producto al anaquel
	 * @param criteria
	 */
	public void validaProductos(Map criteria);
	
	/**
	 * Devuelve el oid de la caja maestra
	 * @param criteria
	 * @return
	 */
	public String getOidTipoCajaProducto(Map criteria);
	
	/**
	 * Actuliza el numero de Prioridad del Tipo de Anaquel
	 * @param criteria
	 */
	public void executeActulizaNumeroPrioridad(Map criteria);
	
	/**
	 * Obtiene la Descripcion de la Unidad de Medida del Producto
	 * @param criteria
	 * @return
	 */
	public String getDesUnidadMedidaAPE(Map criteria);
}

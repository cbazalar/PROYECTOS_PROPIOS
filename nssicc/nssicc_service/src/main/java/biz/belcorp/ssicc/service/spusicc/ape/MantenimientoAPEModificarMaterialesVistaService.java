package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.MaterialesVista;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEModificarMaterialesVistaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface MantenimientoAPEModificarMaterialesVistaService extends Service{

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
	 * @param OidSap
	 * @param NumeroPrioridad
	 * @param OidTipoAnaquel
	 * @param listAnaqueles
	 */
	public void insertTipoAnaquelProducto(String OidSap, String NumeroPrioridad, String OidTipoAnaquel, List listAnaqueles);
	
	/**
	 * Elimina un Tipo de Anaquel al Producto Seleccionado
	 * @param criteria
	 */
	public void deleteTipoAnaquelProducto(Map criteria);
	
	/**
	 * Actualiza los campos del Tipo de Anaquel para el producto 
	 * Seleccionado
	 * @param listAnaqueles
	 */
	public void updateTipoAnaquelProducto(List listAnaqueles);
	
	/**
	 * Valida si ya existe el Tipo de Anaquel para el Producto seleccionado
	 * @param criteria
	 * @return
	 */
	public String getExisteTipoAnaquelProducto(Map criteria);
	
	/**
	 * Inserta un Tipo de Anaquel al Producto Seleccionado
	 * @param OidSap
	 * @param listAnaqueles
	 */
	public void insertTipoAnaquelProducto(String OidSap, List listAnaqueles);
	
	/**
	 * Devuelve el numero de Prioridad Maximo para el producto seleccionado
	 * @param criteria
	 * @return
	 */
	public int getMaxNumeroPrioridad(Map criteria);
	
	/**
	 * Realiza la carga del archivo excel
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public List executeCargaArchivoExcel(Map criteria) throws Exception ;
	
	/**
	 * Obtiene la Descripcion de la Unidad de Medida del Producto
	 * @param criteria
	 * @return
	 */
	public String getDesUnidadMedidaAPE(Map criteria);
}
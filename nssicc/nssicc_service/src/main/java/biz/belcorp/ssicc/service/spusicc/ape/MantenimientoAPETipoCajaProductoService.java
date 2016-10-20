package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoCajaProducto;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPETipoCajaProductoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface MantenimientoAPETipoCajaProductoService extends Service{

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
	 * @param criteria
	 * @param items
	 * @return
	 */
	public String deleteTipoCajaProducto(Map criteria, String[] items);
}
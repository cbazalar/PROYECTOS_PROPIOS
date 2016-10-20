package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEErroresEnCubicajeService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPEErroresEnCubicajeService extends Service{

	/**
	 * @param criteria
	 * @return La lista de Consulta de Errores de Cubicaje
	 */
	public List getConsultaErroresCubicajeList(Map criteria);
	
	/**
	 * @param items
	 * @param criteria
	 * Elimina el error de cubicaje
	 */
	public void eliminarErrorCubicaje(String[] items, Map criteria);

	/**
	 * @param criteria
	 * @return Indicador de si falta valor de Volumen en el Producto para el proceso de Facturacin.
	 */
	public String getValidaValoresProducto(Map criteria);
	
	/**
	 * @param criteria
	 * @return Indicador de si falta valor de Unidad de Medida de Volumen en el Producto para el proceso de Facturacin.
	 */
	public String getValidaValorUndMedVolProducto(Map criteria);
	
	/**
	 * @param criteria
	 * @return Indicador de si falta valor de Alto del Producto para el proceso de Facturacin.
	 */
	public String getValidaValorAltoProducto(Map criteria);
	
	/**
	 * @param criteria
	 * @return Indicador de si falta valor de Largo del Producto para el proceso de Facturacin.
	 */
	public String getValidaValorLargoProducto(Map criteria);
	
	/**
	 * @param criteria
	 * @return Indicador de si falta valor de Ancho del Producto para el proceso de Facturacin.
	 */
	public String getValidaValorAnchoProducto(Map criteria);
	
	/**
	 * Procede a Eliminar el registro en la tabla de Error de Cubicaje
	 * @param items
	 * @param criteria
	 */
	public void deleteErrorCubicaje(String[] items, Map criteria);
 
}
package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Nicols Lpez
 *
 */

public interface MantenimientoAPEErroresEnCubicajeDAO extends DAO{

	/**
	 * @param criteria
	 * @return La lista de Consulta de Errores de Cubicaje
	 */
	public List getConsultaErroresCubicajeList(Map criteria);
	
	/**
	 * Procede a eliminar el error de Cubicaje
	 * @param criteria
	 */
	public void eliminarErrorCubicaje(Map criteria);

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
	 * @param criteria
	 */
	public void deleteErrorCubicaje(Map criteria);
	
}
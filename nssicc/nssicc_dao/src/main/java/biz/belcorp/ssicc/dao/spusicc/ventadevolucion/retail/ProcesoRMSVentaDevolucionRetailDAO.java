/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.ventadevolucion.retail;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Richar Cruzado
 * @date   30/12/2015 
 */
public interface ProcesoRMSVentaDevolucionRetailDAO extends DAO{
	
	
	/**
	 * Inserta una venta de devolución
	 * @param criteria
	 */
	public void insertaVentaDevolucion(Map criteria);
	

}

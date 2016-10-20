/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.ventadevolucion.retail;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Richar Cruzado
 * @date   30/12/2015
 */
public interface ProcesoRMSVentaDevolucionRetailService extends Service {
		
	
	/**
	 * Ejecuta proceso de Grabar Venta Devolución
	 * @param criteria
	 */
	public void insertaVentaDevolucion(Map criteria);
	 

}

/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.precioproducto.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Richar Cruzado
 * @date   28/12/2015
 */
public interface ProcesoRMSPrecioProductoRetailService extends Service {
	
	/**
	 * Obtiene lista con información correspondiente a Consulta de precios de un producto. 
	 * @param map
	 * @return 
	 */
	public List getPrecioProductoRetail(Map map);		
	 

}

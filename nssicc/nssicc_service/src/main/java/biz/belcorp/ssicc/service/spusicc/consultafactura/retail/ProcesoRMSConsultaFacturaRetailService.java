/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.consultafactura.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Richar Cruzado
 *
 */
public interface ProcesoRMSConsultaFacturaRetailService extends Service { 	
	
	
	/**
	* Obtiene lista con información correspondiente a consulta de facturas
	* @param map
	* @return 
	*/
	public List getConsultaFacturaRetail(Map map);
	

}

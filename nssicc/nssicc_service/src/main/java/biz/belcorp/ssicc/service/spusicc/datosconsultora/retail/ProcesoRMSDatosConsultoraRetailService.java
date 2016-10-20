/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.datosconsultora.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Richar Cruzado
 *
 */
public interface ProcesoRMSDatosConsultoraRetailService extends Service { 	
			
	
	/**
	 * Obtiene lista con información correspondiente a consulta de datos de consultora 
	 * @param map
	 * @return 
	 */
	public List getDatosConsultoraRetail(Map map);		
	
	
	/**
	 * Proceso que 
	 * @param criteria
	 */	
	public void executeDatosConsultoraRetail(Map criteria);
	
	
}
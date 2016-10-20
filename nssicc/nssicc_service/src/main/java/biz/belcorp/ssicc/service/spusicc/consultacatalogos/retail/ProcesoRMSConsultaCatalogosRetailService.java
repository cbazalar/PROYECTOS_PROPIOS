/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.consultacatalogos.retail;

import java.util.List;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Richar Cruzado Vallejos
 *
 */
public interface ProcesoRMSConsultaCatalogosRetailService extends Service { 	
			
	
	/**
	 * Obtiene lista con información correspondiente a consulta de catalogos.
	 * @param map
	 * @return 
	 */
	public List getConsultaCatalogosRetail();		
			
	
	
}
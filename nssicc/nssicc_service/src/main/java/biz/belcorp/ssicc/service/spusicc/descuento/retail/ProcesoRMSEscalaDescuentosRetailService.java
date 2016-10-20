/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.descuento.retail;

import java.util.List;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Richar Cruzado
 *
 */
public interface ProcesoRMSEscalaDescuentosRetailService extends Service { 	
	
	
	/**
	 * Obtiene lista con información correspondiente a escalas de descuento
	 * @param bean
	 * @return
	 */
	public List getEscalaDescuentoRetail();
	
	
	
}
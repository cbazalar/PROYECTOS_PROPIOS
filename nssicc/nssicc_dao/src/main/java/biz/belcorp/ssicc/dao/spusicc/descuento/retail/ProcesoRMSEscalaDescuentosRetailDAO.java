/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.descuento.retail;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Richar Cruzado
 *
 */
public interface ProcesoRMSEscalaDescuentosRetailDAO extends DAO {
	
		   	
	/**
	 * Obtiene lista con información correspondiente a consulta de escalas de descuento
	 * @param map
	 * @return
	 */
	public List getEscalaDescuentoRetail();
	   

}

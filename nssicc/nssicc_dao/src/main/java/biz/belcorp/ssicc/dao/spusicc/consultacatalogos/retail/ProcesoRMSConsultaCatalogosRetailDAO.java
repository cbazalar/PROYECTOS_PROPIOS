/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.consultacatalogos.retail;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Richar Cruzado Vallejos
 * @date   21/01/2016
 */
public interface ProcesoRMSConsultaCatalogosRetailDAO extends DAO {
	
		   	
	/**
	 * Obtiene lista con información correposdiente a consulta de catalogos
	 * @param map
	 * @return
	 */	
	public List getConsultaCatalogosRetail();	
	
	
}

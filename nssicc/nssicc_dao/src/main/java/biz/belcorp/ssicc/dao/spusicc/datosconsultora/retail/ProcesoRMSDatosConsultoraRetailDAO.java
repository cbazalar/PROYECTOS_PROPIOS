/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.datosconsultora.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Richar Cruzado
 *
 */
public interface ProcesoRMSDatosConsultoraRetailDAO extends DAO {
	
		   	
	/**
	 * Obtiene lista con información correspondiente a consulta de datos de consultora
	 * @param map
	 * @return
	 */	
	public List getDatosConsultoraRetail(Map map);	
	
	
	/**
	 * Proceso que obtiene datos de consultora.
	 * @param criteria
	 */
	public void executeDatosConsultoraRetail(Map criteria);

}

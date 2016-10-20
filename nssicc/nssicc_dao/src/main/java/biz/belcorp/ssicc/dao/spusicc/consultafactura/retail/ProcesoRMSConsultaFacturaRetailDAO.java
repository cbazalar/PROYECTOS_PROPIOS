/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.consultafactura.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Richar Cruzado
 * @date   29/12/2015
 */
public interface ProcesoRMSConsultaFacturaRetailDAO extends DAO {
	
		   	
	/**
	 * Obtiene lista con información correposdiente a consulta de facturas
	 * @param map
	 * @return
	 */	
	public List getConsultaFacturaRetail(Map map);	
	
	
}

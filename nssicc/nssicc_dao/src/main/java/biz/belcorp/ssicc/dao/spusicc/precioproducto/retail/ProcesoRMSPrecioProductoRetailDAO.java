/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.precioproducto.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Richar Cruzado
 * @date   28/12/2015
 */
public interface ProcesoRMSPrecioProductoRetailDAO extends DAO{
	
	/**
	 * Obtiene lista con información correspondiente a Consulta de Precios de un producto
	 * @param map
	 * @return
	 */	
	public List getPrecioProductoRetail(Map map);	

}

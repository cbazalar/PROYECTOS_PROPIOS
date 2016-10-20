/**
 * 
 */
package biz.belcorp.ssicc.dao.soa;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Danny Amaro
 *
 */
public interface CDRDAO extends DAO{
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getListaCDR(Map criteria);

}

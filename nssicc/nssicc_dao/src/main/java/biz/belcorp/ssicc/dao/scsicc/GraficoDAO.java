/**
 * 
 */
package biz.belcorp.ssicc.dao.scsicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="GraficoDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Carlos Bazalar La Rosa</a>
 * 
 */

public interface GraficoDAO extends DAO {

	/**
	 * @param params
	 * @return
	 */
	public List getResumenConsultorasxEstadoPie(Map params) throws Exception;

	public List getResumenConsultorasxEstadoBar(Map params) throws Exception;

}

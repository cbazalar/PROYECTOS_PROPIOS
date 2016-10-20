/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;



/**
 * @author peextllizana
 *
 */
public interface MantenimientoRECGestionBoletasRecojoNoExitosasDAO extends DAO {

	
	/**
	 * @param params
	 * @return
	 */
	public List getBoletasRecojoNOExitosasList(Map params);

	/**
	 * @param params
	 */
	public void executeProcesoBoletaRecojoNoExitosa(Map params);

}

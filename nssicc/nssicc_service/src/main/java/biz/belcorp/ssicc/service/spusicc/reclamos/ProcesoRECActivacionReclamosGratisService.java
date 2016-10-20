package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Aurelio Oviedo
 *
 */
public interface ProcesoRECActivacionReclamosGratisService extends Service {
	
	/**
	 * @param params
	 * @return
	 */
	public List getActivacionReclamosGratisList(Map params);

	/**
	 * @param criteria
	 */
	public void executeProcesoActivacionReclamosGratis(Map params);
	
	/**
	 * @param params
	 * @return
	 */
	public List getDataMemoriaNByCriteria(Map params);
	
	/**
	 * @param params
	 * @return
	 */
	public List getDataMemoriaSByCriteria(Map params);
	
	/**
	 * @param params
	 */
	public Map executarProcesoRECActivacionReclamosGratisAsignar(Map params);
	
}
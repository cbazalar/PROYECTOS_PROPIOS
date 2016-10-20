package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Aurelio Oviedo
 *
 */
public interface ProcesoRECActivacionReclamosGratisDAO extends DAO {

	/**
	 * @param params
	 * @return
	 */
	public List getActivacionReclamosGratisList(Map params);

	public String getBuscarCUVReemplazo(Map criteria);
	
	public String getBuscarSAPReemplazo(Map criteria);
	
	/**
	 * @param params
	 */
	public void executeProcesoActivacionReclamosGratis(Map params);
	
	public List getDataMemoriaNByCriteria(Map params);
	
	public List getDataMemoriaSByCriteria(Map params);
	
	public void updateActivacionReclamosGratisAsignar(Map criteria);
	
}
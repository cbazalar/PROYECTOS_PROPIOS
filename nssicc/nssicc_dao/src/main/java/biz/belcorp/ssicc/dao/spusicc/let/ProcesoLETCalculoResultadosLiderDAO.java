package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoLETCalculoResultadosLiderDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Aurelio Oviedo
 *         
 */
public interface ProcesoLETCalculoResultadosLiderDAO extends DAO{
	
	/**
	 * Mtodo que realiza el calculo de resultados de la lider
	 * @param params
	 */
	public void executeProcesoLETCalculoResultadosLider(Map params);
}
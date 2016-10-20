package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoLETDespachoPremiosDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Aurelio Oviedo
 *         
 */
public interface ProcesoLETDespachoPremiosDAO extends DAO{
	
	/**
	 * Mtodo que realiza el despacho de premios
	 * @param params
	 */
	public void executeProcesoLETDespachoPremios(Map params);
}
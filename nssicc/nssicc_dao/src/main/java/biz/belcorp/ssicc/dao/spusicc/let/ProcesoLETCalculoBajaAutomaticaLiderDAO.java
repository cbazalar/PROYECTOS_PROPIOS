package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoLETCalculoBajaAutomaticaLiderDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas Luna
 *         
 */
public interface ProcesoLETCalculoBajaAutomaticaLiderDAO extends DAO{
	
	/**
	 * Mtodo que realiza el calculo de baja Lider.
	 * @param params
	 */
	public void executeProcesoLETCalculoBajaAutomaticaLider(Map params);
}
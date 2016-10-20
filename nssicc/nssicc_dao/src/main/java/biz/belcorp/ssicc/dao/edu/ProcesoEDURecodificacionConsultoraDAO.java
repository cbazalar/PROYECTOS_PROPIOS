package biz.belcorp.ssicc.dao.edu;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDURecodificacionConsultoraDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 */


public interface ProcesoEDURecodificacionConsultoraDAO extends DAO {

	/**
	 * Realiza el proceso de Recodifcacion Clientes
	 * @param criteria
	 */
	public void executeRecodificacionConsultora(Map criteria);
	
}

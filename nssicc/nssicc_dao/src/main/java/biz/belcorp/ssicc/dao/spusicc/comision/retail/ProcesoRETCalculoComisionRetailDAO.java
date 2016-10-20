package biz.belcorp.ssicc.dao.spusicc.comision.retail;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoRETCalculoComisionRetailDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public interface ProcesoRETCalculoComisionRetailDAO extends DAO {

	/**
	 * Calculo de Comision Retail a gerentes de Zona
	 * 
	 * @param criteria
	 */
	public void executeCalculoComisionRetail(Map criteria);

}


package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoCCCRegistroIncobrablesDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */

public interface ProcesoCCCRegistroIncobrablesDAO extends DAO {

	/**
	 * Ejecuta el proceso de Registro de Incobrables
	 * @param criteria
	 */
	public void executeRegistroIncobrables (Map criteria);
		
}
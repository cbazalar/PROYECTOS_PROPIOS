/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="procesoPERProcesarMovimientoForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public interface ProcesoPERProcesarMovimientoDAO extends DAO {

	public void executeProcesarMovimiento(Map criteria);
	
}
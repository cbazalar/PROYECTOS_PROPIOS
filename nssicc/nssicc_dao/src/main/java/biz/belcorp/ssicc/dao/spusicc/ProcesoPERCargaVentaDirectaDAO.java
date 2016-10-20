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
 * <a href="ProcesoPERCargaVentaDirectaDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
public interface ProcesoPERCargaVentaDirectaDAO extends DAO {

	public String executeCargaVentaDirecta(Map criteria);
	
	public String getVerificaBloqueoControlAnual(Map criteria);
	
	public void updateBloqueoControlAnual(Map criteria);
	
}
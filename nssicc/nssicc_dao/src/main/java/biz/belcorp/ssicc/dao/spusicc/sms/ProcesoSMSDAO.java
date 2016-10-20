/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sms;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * @author Danny Amaro
 *
 */
public interface ProcesoSMSDAO extends DAO{
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getSMSList(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void insertSMS(Map criteria, Usuario usuario);
	
	/**
	 * @param criteria
	 */
	public void removeSMS(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void activarSMS(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void desactivarSMS(Map criteria);

}

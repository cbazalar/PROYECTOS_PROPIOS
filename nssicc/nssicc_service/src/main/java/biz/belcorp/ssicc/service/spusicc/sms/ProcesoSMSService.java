/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sms;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Danny Amaro
 *
 */
public interface ProcesoSMSService extends Service{
	
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

/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.OperacionCDRUsuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Danny Amaro
 *
 */
public interface MantenimientoOperacionCDRUsuarioService extends Service {
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getOperacionesCDRxUsuario(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getOperacionesUsuario(Map criteria);
	
	/**
	 * @param OperacionCDRUsuario
	 * 
	 */
	public void insertOperacionCDRUsuario(OperacionCDRUsuario operacionCDRUsuario, Usuario usuario);
	
	/**
	 * @param OperacionCDRUsuario
	 */
	public void removeOperacionCDRUsuario(Map criteria);
	
	/**
	 * @param OperacionCDRUsuario
	 * 
	 */
	public void insertOperacionCDRUsuarioAudit(OperacionCDRUsuario operacionCDRUsuario, Usuario usuario);

}

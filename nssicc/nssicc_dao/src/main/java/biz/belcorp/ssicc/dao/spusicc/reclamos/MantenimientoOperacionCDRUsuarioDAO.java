/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;



import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.OperacionCDRUsuario;

/**
 * @author Danny Amaro
 *
 */
public interface MantenimientoOperacionCDRUsuarioDAO extends DAO{

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
	 * @param criteria
	 * @return
	 */
	public List getTipoOperacionesByOperacion(Map criteria);
	
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

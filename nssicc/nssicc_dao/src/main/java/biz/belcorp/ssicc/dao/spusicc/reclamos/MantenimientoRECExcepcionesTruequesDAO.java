/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="MantenimientoRECExcepcionesTruequesDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 * 
 */
public interface MantenimientoRECExcepcionesTruequesDAO extends DAO{
	/**
	 * Inserta una excepcion de trueque
	 * @param criteria
	 */
	public void insertExcepcionTrueque(Map criteria);
	
	/**
	 * Elimina una excepcion de trueque  
	 * @param criteria
	 */
	public void deleteExcepcionTrueque(Map criteria);
	
	/**
	 * Se obtienen la lisa de excepciones de trueques
	 * @param criteria
	 */
	public List getExcepcionesTrueques(Map criteria);
}

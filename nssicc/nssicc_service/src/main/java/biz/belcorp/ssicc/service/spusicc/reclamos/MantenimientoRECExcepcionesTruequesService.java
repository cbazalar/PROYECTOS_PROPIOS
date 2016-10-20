package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="MantenimientoRECExcepcionesTruequesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public interface MantenimientoRECExcepcionesTruequesService extends Service{
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

package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCCCParametrosGeneralesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com"> </a>
 */
public interface MantenimientoCCCParametrosGeneralesService extends Service {
			
	/**
	 * Obtiene en una lista de los Paramatros Generales segun Filtro
	 * @param criteria
	 * @return
	 */
	public List getParametroGeneralByCriteria(Map criteria);
			
	/**
	 * Inserta un Parametro General
	 * @param criteria
	 */
	public void insertParametroGeneral(Map criteria);
	
	/**
	 * Actualiza un Parametro General
	 * @param criteria
	 */
	public void updateParametroGeneral(Map criteria);
	
	/**
	 * Elimina un Parametro General
	 * @param criteria
	 */
	public void deleteParametroGeneral(Map criteria);
}
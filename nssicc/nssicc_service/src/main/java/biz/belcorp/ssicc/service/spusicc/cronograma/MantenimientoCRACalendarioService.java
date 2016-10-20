package biz.belcorp.ssicc.service.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCRACalendarioService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */

public interface MantenimientoCRACalendarioService extends Service {

	/**
	 * @param criteria
	 */
	public int insertFeriados(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void deleteFeriado(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getCalendarioFeriados(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public void insertCalendarioDiaNoLaborable(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void deleteDiaNoLaborable(Map criteria);

	/**
	 * @param criteria
	 */
	public void copiarCalendarioPorActividad(Map criteria);

}

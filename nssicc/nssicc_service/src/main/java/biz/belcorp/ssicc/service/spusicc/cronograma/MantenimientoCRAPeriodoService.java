package biz.belcorp.ssicc.service.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCRAPeriodoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez</a>
 */

public interface MantenimientoCRAPeriodoService extends Service {

	/**
	 * Metodo que devuelve los periodos corporativos del SEG_PERIO_CORPO
	 * @param anho
	 * @return
	 */
	public List getPeriodoCorporativoList(String anho);

	/**
	 * @param anhio
	 * @return
	 */
	public List getPeriodoCronogramaList(String anhio);

	/**
	 * @param criteria
	 * @throws Exception
	 */
	public void insertPeriodoCronograma(Map criteria) throws Exception;

	/**
	 * @param anhio
	 */
	public List insertPeriodoCorporativo(Map criteria);
	
}

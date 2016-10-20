package biz.belcorp.ssicc.dao.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cronograma.model.PeriodoCronograma;

/**
 * <p>
 * <a href="MantenimientoCRAPeriodoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramrez</a>
 */

public interface MantenimientoCRAPeriodoDAO extends DAO{

	/**
	 * @param anhio
	 * @return
	 */
	public List getPeriodoCorporativoList(String anhio);
	
	public List getPeriodoCronogramaList(String anhio);
	
	/**
	 * @param periodo
	 * @throws Exception
	 */
	public void insertPeriodoCronograma(PeriodoCronograma periodo) throws Exception;
	
	/**
	 * @param periodo
	 * @throws Exception
	 */
	public void modificaPeriodoCronograma(PeriodoCronograma periodo) throws Exception;

	/**
	 * @param anhio
	 */
	public void insertPeriodoCorporativo(Map criteria);

	/**
	 * @return
	 */
	public List getPeriodoNuevoCronogramaList();

}

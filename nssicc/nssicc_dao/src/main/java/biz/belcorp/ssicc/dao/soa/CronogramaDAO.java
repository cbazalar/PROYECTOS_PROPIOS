package biz.belcorp.ssicc.dao.soa;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDURegistroCalificacionEjecutivaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */


public interface CronogramaDAO extends DAO {

	  /**
	 * @param criteria
	 * @return
	 */
	List getCronograma(Map criteria);
	
	/**
	 * retorna el dia de factlantes para la factuaracion de una zona y periodo
	 * @param criteria
	 * @return
	 */
	Integer getDiasFaltantesCierreFacturacion(Map criteria); 

}

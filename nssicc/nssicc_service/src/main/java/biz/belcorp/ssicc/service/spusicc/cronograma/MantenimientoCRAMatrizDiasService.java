package biz.belcorp.ssicc.service.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */

public interface MantenimientoCRAMatrizDiasService extends Service {
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCargaMatrizDias(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getMatrizDias(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void updateMatrizDias(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getMatrizDiasFueraPeriodo(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void insertActividadMatrizDias(List lista);

	/**
	 * @param map
	 */
	public void updateMatrizUpdateActividad(Map criteria);

	/**
	 * @param map
	 */
	public void updateMatrizDeleteActividad(Map map);
}
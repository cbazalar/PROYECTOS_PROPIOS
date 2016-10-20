package biz.belcorp.ssicc.service.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */

public interface MantenimientoCRACronogramaFase1Service extends Service {
	
	/**
	 * @param criteria
	 */
	public List generarCronogramaFase1(Map criteria);
	/**
	 * @param criteria
	 * @return
	 */
	public List getCargaCronogramaFase1(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void updateCronogramaFase1(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getDatosCronoFase1(Map criteria);

	/**
	 * @param params
	 * @return
	 */
	public List getZonasCronograma(Map params);

	/**
	 * @param criteria
	 * @return
	 */
	public List getCargaCronogramaFase2(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getDatosCronoFase2(Map criteria);

	/**
	 * @param criteria
	 */
	public void updateCronogramaFase2(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List generarCronogramaFase2(Map criteria);

	/**
	 * @param criteria
	 */
	public void deleteCronogramaFase2(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void copiaCronogramaPorZonaFase2(Map criteria);
	
}
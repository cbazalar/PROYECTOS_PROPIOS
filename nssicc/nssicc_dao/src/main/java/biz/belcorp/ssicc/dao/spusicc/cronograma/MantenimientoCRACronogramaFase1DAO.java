package biz.belcorp.ssicc.dao.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */

public interface MantenimientoCRACronogramaFase1DAO extends DAO {
	
	public void getGenerarCronogramaFase1(Map criteria);
	/**
	 * @param criteria
	 * @return
	 */
	public void getCargaCronogramaFase1(Map criteria);
	
	/**
	 * @return
	 */
	public List getPintaCronogramaFase1();

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
	 * @param criteria
	 * @return
	 */
	public List getZonasCronograma(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public void getCargaCronogramaFase2(Map criteria);

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
	public void generarCronogramaFase2(Map criteria);

	/**
	 * 
	 */
	public void deleteTemporalFase2();

	/**
	 * @param criteria
	 * @return
	 */
	public List getPintaCronogramaFase2();

	/**
	 * @param criteria
	 */
	public void deleteCronogramaFase2(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void copiaCronogramaPorZonaFase2(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getActuaFechaFase1(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getActuaFechaFase2(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public int getCronogramaFase1Existente(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getFechaCronoFase2(Map criteria);
}
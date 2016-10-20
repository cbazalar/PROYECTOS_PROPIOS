package biz.belcorp.ssicc.dao.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */

public interface MantenimientoCRAMatrizDiasDAO extends DAO {
	
	/**
	 * Metodo que carga la tabla con la informacion de la matriz de dias del cronograma
	 * y devuelve la lista de actividades por grupo de zona
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
	public void insertActividadMatrizDias(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void updateMatrizUpdateActividad(Map criteria);

	/**
	 * @param criteria
	 */
	public void updateMatrizDeleteActividad(Map criteria);
	
}
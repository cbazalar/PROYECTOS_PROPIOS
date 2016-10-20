package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextdoliva
 */

public interface MantenimientoSTOExcepcionesPaseFueraFechaDAO extends DAO{
	
	/**
	 * retorna la lista de Excepciones de Pase Fuera de Fecha
	 * @param criteria
	 * @return
	 */
	public List getExcepcionesPaseFueraFecha(Map criteria) ;

	/**
	 * Elimina las excepciones seleccionadas
	 * @param criteria
	 */
	public void deleteExcepciones(Map criteria);
	
	/**
	 * Inserta una excepciones fuera de fecha
	 * @param criteria
	 */
	public void insertExcepciones(Map criteria);
}
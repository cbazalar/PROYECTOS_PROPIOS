package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextdoliva
 */

public interface MantenimientoSTOExcepcionesPaseFueraFechaService extends Service{

	/**
	 * retorna la lista de Excepciones de Pase Fuera de Fecha
	 * @param criteria
	 * @return
	 */
	public List getExcepcionesPaseFueraFecha(Map criteria);
	
	/**
	 * Elimina las excepciones seleccionadas
	 * @param items
	 */
	public void deleteExcepciones(String[] items);
	
	/**
	 * Inserta una excepciones fuera de fecha
	 * @param criteria
	 */
	public void insertExcepciones(Map criteria);
}
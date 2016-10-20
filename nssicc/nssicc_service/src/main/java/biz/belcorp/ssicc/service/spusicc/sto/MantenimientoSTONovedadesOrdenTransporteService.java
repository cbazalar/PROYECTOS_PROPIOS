package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextdoliva
 */

public interface MantenimientoSTONovedadesOrdenTransporteService extends Service{

	/**
	 * retorna la lista de Novedades de orden de transporte
	 * @param criteria
	 * @return
	 */
	public List getNovedadesOrdenTransporte(Map criteria);
	
	/**
	 * Elimina registros de la tabla de Novedades de orden de transporte
	 * @param items
	 */
	public void deleteNovedadesOrdenTransporte(String[] items);
	
	/**
	 * Inserta registro en la tabla de Novedades de orden de transporte
	 * @param criteria
	 */
	public void insertNovedadesOrdenTransporte(Map criteria);
	
	/**
	 * Actualiza registro de la tabla de Novedades de orden de transporte
	 * @param criteria
	 */
	public void updateNovedadesOrdenTransporte(Map criteria);
	
}
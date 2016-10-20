package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextdoliva
 */

public interface MantenimientoSTONovedadesOrdenTransporteDAO extends DAO{
	
	/**
	 * retorna la lista de Novedades de orden de transporte
	 * @param criteria
	 * @return
	 */
	public List getNovedadesOrdenTransporte(Map criteria);
	
	
	/**
	 * Elimina registros de la tabla de Novedades de orden de transporte
	 * @param criteria
	 */
	public void deleteNovedadesOrdenTransporte(Map criteria);
	
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
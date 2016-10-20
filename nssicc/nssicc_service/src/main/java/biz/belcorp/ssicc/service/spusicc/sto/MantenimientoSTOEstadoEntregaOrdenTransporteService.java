package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextdoliva
 */

public interface MantenimientoSTOEstadoEntregaOrdenTransporteService extends Service{

	/**
	 * retorna la lista de Tipos de Orden de transporte
	 * @return
	 */
	public List getTiposOrdenTransporte();
	
	/**
	 * retorna la lista de Estados de orden de transporte
	 * @param criteria
	 * @return
	 */
	public List getEstadosOrdenTransporte(Map criteria);
	
	/**
	 * Inserta el registro en la tabla de Estados de orden de transporte
	 * @param criteria
	 */
	public void insertEstadoOrdenTransporte(Map criteria);
	
	/**
	 * Actualiza el registro en la tabla de Estados de orden de transporte
	 * @param criteria
	 */
	public void updateEstadoOrdenTransporte(Map criteria);
	
	/**
	 * Elimina registros de la tabla de Estados de orden de transporte
	 * @param items
	 */
	public void deleteEstadoOrdenTransporte(String[] items);
	
}
package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextdoliva
 */

public interface MantenimientoSTOEstadoEntregaOrdenTransporteDAO extends DAO{
	
	/**
	 * retorna la lista de Tipos de orden de transporte
	 * @return
	 */
	public List getTiposOrdenTransporte() ;

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
	 * @param criteria
	 */
	public void deleteEstadoOrdenTransporte(Map criteria);
	
}
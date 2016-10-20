/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jose Luis Rodriguez
 *
 */
public interface ProcesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO extends DAO {

	
	/**
	 * Metodo que ejecuta la actualizacion de Saldos de Seguimientos de Levantamiento
	 * @param criteria
	 */
	public void executeProcesarSaldosSeguimientoLevantamientos(Map criteria);
	
}

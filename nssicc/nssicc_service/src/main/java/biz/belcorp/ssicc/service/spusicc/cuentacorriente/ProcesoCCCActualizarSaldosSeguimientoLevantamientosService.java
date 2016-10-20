package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jose Luis Rodriguez
 *
 */
public interface ProcesoCCCActualizarSaldosSeguimientoLevantamientosService extends Service {

	/**
	 * Metodo que ejecuta la actualizacion de Saldos de Seguimientos de Levantamiento
	 * de deuda.
	 * @param criteria
	 */
	public void executeProcesarSaldosSeguimientoLevantamientos(Map criteria);

}

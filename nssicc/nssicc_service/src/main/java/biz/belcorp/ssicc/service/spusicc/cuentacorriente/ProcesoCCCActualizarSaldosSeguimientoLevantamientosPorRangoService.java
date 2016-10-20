package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Diego Torres
 *
 */
public interface ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService extends Service {

	/**
	 * Metodo que ejecuta la actualizacion de Saldos de Seguimientos de Levantamiento
	 * por rango.
	 * @param criteria
	 */
	public void executeProcesarSaldosSeguimientoLevantamientosPorRango(Map criteria);
	
	public List getRangoPeriodos(Map criteria);
	
	/**
	 * Metodo que ejecuta Saldo actual
	 * @param criteria
	 */
	public void executeProcesarSaldosActual(Map criteria);

}

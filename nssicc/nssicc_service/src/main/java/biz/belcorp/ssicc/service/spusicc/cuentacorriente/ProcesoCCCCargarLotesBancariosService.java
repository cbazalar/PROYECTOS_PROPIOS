package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jorge Florencio Arias
 *
 */
public interface ProcesoCCCCargarLotesBancariosService extends Service {

	/**
	 * Metodo que ejecuta la Carga de los Lotes Bancarios
	 * @param criteria
	 */
	public void executeCargarLotesBancarios(Map criteria);

}

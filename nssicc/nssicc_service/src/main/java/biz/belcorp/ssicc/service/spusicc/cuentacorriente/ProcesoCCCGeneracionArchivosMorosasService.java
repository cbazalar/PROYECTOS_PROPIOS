/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Sebastian Guerra
 *
 */
public interface ProcesoCCCGeneracionArchivosMorosasService extends Service {

	/**
	 * Metodo que genera los archivos con informacin de morosos
	 * @param criteria
	 */
	public void executeGeneracionArchivosMorosas(Map criteria);

}

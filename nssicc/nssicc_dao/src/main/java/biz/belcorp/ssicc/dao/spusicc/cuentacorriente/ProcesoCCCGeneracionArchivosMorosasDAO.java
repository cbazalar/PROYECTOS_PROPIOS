/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Sebastian Guerra
 *
 */
public interface ProcesoCCCGeneracionArchivosMorosasDAO extends DAO {

	/**
	 * Metodo que genera los archivos con informacin de morosos
	 * @param criteria
	 */
	public void executeGeneracionArchivosMorosas(Map criteria);

}

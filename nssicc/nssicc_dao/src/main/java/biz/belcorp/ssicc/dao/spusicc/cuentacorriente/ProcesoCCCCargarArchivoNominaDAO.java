/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Gonzalo Huertas
 *
 */
public interface ProcesoCCCCargarArchivoNominaDAO extends DAO {

	public void executeProcesoCCCCargarArchivoNominaFTP();
	
	public void executeProcesoCCCCargarArchivoNominaMAIL();

}

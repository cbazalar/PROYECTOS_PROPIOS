/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jorge Florencio Arias
 *
 */
public interface ProcesoCCCCargarLotesBancariosDAO extends DAO {

	
	/**
	 * Metodo que ejecuta la carga de los Lotes Bancarios
	 * @param criteria
	 */
	public void executeCargarLotesBancarios(Map criteria);
	
	/**
	 * @param criteria
	 * Obtiene la parametria para envio de correos.
	 */
	public List getEnvioMailsConfirmacionCargaLotesBancariosParams(Map criteria);
	
	/**
	 * Obtiene los lotes bancarios cargados por el proceso.
	 */
	public List getLotesCargados(Map criteria);
}

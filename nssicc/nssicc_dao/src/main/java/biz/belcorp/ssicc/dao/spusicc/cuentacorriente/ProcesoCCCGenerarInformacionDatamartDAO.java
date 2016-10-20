/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jorge Florencio Arias
 *
 */
public interface ProcesoCCCGenerarInformacionDatamartDAO extends DAO {

	
	/**
	 * Metodo que Genera la Informacion para Datamart
	 * @param criteria
	 */
	public void executeGenerarInformacionDatamart(Map criteria);
	
}

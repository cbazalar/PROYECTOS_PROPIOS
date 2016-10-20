package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoCOMGenerarBaseRecuperacionCobranzaDAO extends DAO {

	/**
	 * Proceso que genera Base de Recuperacion de Cobranza
	 * 
	 * @param params
	 */
	public void executeGenerarBaseRecuperacionCobranza(Map params);
	
}

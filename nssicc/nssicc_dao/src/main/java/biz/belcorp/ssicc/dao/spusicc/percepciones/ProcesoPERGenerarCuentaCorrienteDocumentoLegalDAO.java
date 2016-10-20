/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.percepciones;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoPERGenerarCuentaCorrienteDocumentoLegalDAO extends DAO {

	    
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeGenerarCuentaCorrienteDocumentoLegal(Map datos);
	
}

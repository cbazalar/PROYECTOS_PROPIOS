/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author pejflorencio
 *
 */
public interface MantenimientoCCCInteresMoraDAO extends DAO {

	
	/**
	 * @return 
	 * Devuelve los parametros de interes de mora activos
	 */
	public Map getInteresMontoMora() ;
		
	/**
	 * @param criteria
	 * Inserta un nuevo regitro con los datos seteados en el formulario
	 */
	public void insertInteresMontoMora(Map criteria);
	
	/**
	 * @param criteria
	 * Actualiza estados.
	 */
	public void updateInteresMontoMora(Map criteria);
			
}

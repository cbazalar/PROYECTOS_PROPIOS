/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author pejflorencio
 *
 */
public interface MantenimientoCCCDigitacionCargosDirectosDAO extends DAO {

	/**
	 * @return
	 * Devuelve los Tipos De Cargos Directos Digitable
	 */
	public List getTiposCargosDirectosDigitables();
	
	/**
	 *  * @param criteria
	 * Inserta el Cargo Abono Directo digitado
	 */
	public void insertCargoAbonoDirectoDigitado(Map criteria);
			
}

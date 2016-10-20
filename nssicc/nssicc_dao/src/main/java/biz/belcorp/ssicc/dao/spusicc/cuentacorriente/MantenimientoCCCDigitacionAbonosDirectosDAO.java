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
public interface MantenimientoCCCDigitacionAbonosDirectosDAO extends DAO {

	/**
	 * @return
	 * Devuelve los Tipos De Abonos Directos Digitables
	 */
	public List getTiposAbonosDirectosDigitables();
	
	/**
	 *  * @param criteria
	 * Inserta el Cargo Abono Directo digitado
	 */
	public void insertCargoAbonoDirectoDigitado(Map criteria);
			
}

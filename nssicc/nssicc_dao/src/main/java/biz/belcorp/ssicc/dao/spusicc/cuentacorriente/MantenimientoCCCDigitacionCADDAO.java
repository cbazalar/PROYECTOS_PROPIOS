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
public interface MantenimientoCCCDigitacionCADDAO extends DAO {

	/**
	 * @return
	 * Devuelve los Tipos De Cargos y Abonos Directos
	 */
	public List getTiposCargoAbonoDirectos();
	
	/**
	 *  * @param criteria
	 * Inserta el Cargo Abono Directo digitado
	 */
	public void insertCargoAbonoDirectoDigitado(Map criteria);
	
	/**
	 * Devuelve el valor del parametro según mapa.
	 * @param datos
	 * @return
	 */
	public String getIndicadorParametro(Map datos);
			
}

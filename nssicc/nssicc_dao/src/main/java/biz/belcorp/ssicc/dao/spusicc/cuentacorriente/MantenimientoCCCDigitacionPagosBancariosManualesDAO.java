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
public interface MantenimientoCCCDigitacionPagosBancariosManualesDAO extends DAO {
	
	
	/**
	 *  * @param criteria
	 * Inserta el Pago Bancario Manual Digitado
	 */
	public void generarPagoBancarioManual(Map criteria);
	
	/**
	 *  * @param criteria
	 * Registra el Lote Bancario
	 */
	public void registrarLoteBancario(Map criteria);
			
}

/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoBancarioExterno;

/**
 * @author pejflorencio
 *
 */
public interface MantenimientoCCCDigitacionExternaPagosBancariosDAO extends DAO {
	
	
	/**
	 *  * @param criteria
	 * Inserta el Pago Bancario Externo
	 */
	public void insertarPagoBancarioExterno(PagoBancarioExterno estructura);
	
	/**
	 *  * @param criteria
	 * Registra el Lote Bancario
	 */
	public void generarLoteBancarioExterno(Map criteria);
			
}

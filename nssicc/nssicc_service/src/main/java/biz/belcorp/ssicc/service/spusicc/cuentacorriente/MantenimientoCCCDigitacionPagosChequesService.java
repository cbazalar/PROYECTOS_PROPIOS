/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCCCDigitacionPagosChequesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
public interface MantenimientoCCCDigitacionPagosChequesService extends Service {

	/**
	 * Devuelve la lista de Bancos donde se realiza el pago se cheques
	 * @param criteria
	 * @return
	 */
	public List getBancosCheques(Map criteria);	
	
	/**
	 * Devuelve la lista de Sucursales de los Bancos
	 * @param criteria
	 * @return
	 */
	public List getSucursalesCheques(Map criteria);
	
	/**
	 * Generar pago cheque
	 * @param detallesPagosChequesList
	 * @throws Exception 
	 */
	public void generarPagoCheque( Map criteria,List detallesPagosChequesList) throws Exception;
}

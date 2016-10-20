/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.ErrorPagoBancario;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCCCGestionarErroresPagosBancariosService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"> </a>
 */
public interface MantenimientoCCCGestionarErroresPagosBancariosService extends Service {

		
	/**
	 * Obtiene en una lista de los errores en los pagos bancarios
	 * @param criteria
	 * @return
	 */
	public List getPagosBancariosPorGestionarList (Map criteria);
			
	/**
	 * Obtiene en un objeto Error Pago Bancario
	 * @param oidMovimientoBancario
	 * @return
	 */
	ErrorPagoBancario getErrorPagoBancarioById(String oidMovimientoBancario);	
	
	/**
	 * Actualiza el Error Pago Bancario
	 * @param criteria
	 */
	public void gestionarErrorPagoBancario(Map criteria);	
	
	/**
	 * Elimina registros de la tabla de Movimientos Bancarios
	 * @param items
	 */
	public void aprobarErrorPagoBancario(String[] items);
	
	/**
	 * Elimina registros de la tabla de Movimientos Bancarios
	 * @param items
	 */
	public void deleteErrorPagoBancario(String[] items);


}

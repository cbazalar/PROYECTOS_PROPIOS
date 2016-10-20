/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.ErrorPagoBancario;


/**
 * <p>
 * <a href="MantenimientoCCCGestionarErroresPagosBancariosDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"> </a>
 */

public interface MantenimientoCCCGestionarErroresPagosBancariosDAO extends DAO { 
	
		
	/**
	 * Obtiene una Lista de los Pagos Bancarios por gestionar en base a los parametros recibidos en el map.
	 * @param criteria
	 * @return Lista con los pagos bancarios a gestionar
	 */
	public List getPagosBancariosPorGestionarList (Map criteria);
		
    
	/**
	 * Obtiene un objeto ErrorPagoBancario especifico en base a los parametro rebicidos.
	 * @param oidMovimientoBancario
	 * @return
	 */
	public ErrorPagoBancario getErrorPagoBancarioById(String oidMovimientoBancario);
	
	/**
	 * Actualiza el Error Pago Bancario que se gestiono
	 * @param criteria
	 */
	public void gestionarErrorPagoBancario(Map criterias);
	
	/**
	 * Elimina registros de la tabla de Estados de orden de transporte
	 * @param criteria
	 */
	public void aprobarErrorPagoBancario(Map criteria);
	
	/**
	 * Elimina registros de la tabla de Estados de orden de transporte
	 * @param criteria
	 */
	public void deleteErrorPagoBancario(Map criteria);
}

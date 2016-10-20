/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.RegularizacionPagoBancario;


/**
 * <p>
 * <a href="MantenimientoCCCRegularizacionPagosBancariosDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"> </a>
 */

public interface MantenimientoCCCRegularizacionPagosBancariosDAO extends DAO { 
	
	
	
	/**
	 * Obtiene el numero de lote
	 * @param criteria
	 */
	public void getNumeroLote(Map criteria);
	
	/**
	 * Obtiene una Lista de los Pagos Bancarios por regularizar en base a los parametros recibidos en el map.
	 * @param criteria
	 * @return Lista con los pagos  a Regularizar
	 */
	public List getPagosBancariosPorRegularizarList (Map criteria);
		
    
	/**
	 * Obtiene un objeto regularizacionPagoBancario especifico en base a los parametro rebicidos.
	 * @param regularizacionPagoBancario
	 * @return
	 */
	public RegularizacionPagoBancario getRegularizacionPagoBancarioById(RegularizacionPagoBancario regularizacionPagoBancario);
	
	/**
	 * Actualiza el Pago Bancario a Regularizar
	 * @param criteria
	 */
	public void updatePagoBancarioPorRegularizar(Map criteria);
	
	/**
	 * Elimina el Pago Bancario a Regularizar
	 * @param criteria
	 */
	public void deletePagoBancarioPorRegularizar(Map criteria);
	
	/**
	 * Archivar el Pago Bancario a Regularizar
	 * @param criteria
	 */
	public void archivarPagoBancarioPorRegularizar(Map criteria);
	
	/**
	 * Registrar el Lote Bancario a Regularizar
	 * @param criteria
	 */
	public void executeRegistrarLoteBancario (Map criteria);
	
	/**
	 * Actualiza los cupones en trmite a Depuracin
	 * @param criteria
	 */
	public void updateListCuponesTramite(Map criteria);
	
	/**
	 * @param Params
	 * Obtiene Datos Cabecera Pagos Bancarios Por Regularizar Dividir Pago
	 */
	public Map getPagosBancariosPorRegularizarDividirPagoByFilter(Map criteria);
	
	

}

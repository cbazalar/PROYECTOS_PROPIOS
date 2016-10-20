/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.RegularizacionPagoBancario;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCCCRegularizacionPagosBancariosService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"> </a>
 */
public interface MantenimientoCCCRegularizacionPagosBancariosService extends Service {

	
	/**
	 *  Obtiene en el numero de lote
	 * @param criteria
	 */
	public void getNumeroLote(Map criteria);
	
	/**
	 * Obtiene en una lista de los Pagos Bancarios por Regularizar
	 * @param criteria
	 * @return
	 */
	public List getPagosBancariosPorRegularizarList (Map criteria);
	
	/**
	 * Obtiene en un objeto Regularizacion Pago Bancario
	 * @param cronogramaCartera
	 * @return
	 */
	RegularizacionPagoBancario getRegularizacionPagoBancarioById(RegularizacionPagoBancario regularizacionPagoBancario);	
	
			
	/**
	 * Actualiza el pago bancario a regularizar
	 * @param criteria
	 */
	public void updatePagoBancarioPorRegularizar(Map criteria);		

	/**
	 * @param params
	 * Actualiza el pago bancario a regularizar para una lista
	 */
	public void updateListPagoBancarioPorRegularizar(List params);
	
	/**
	 * @param params
	 * Elimina los pagos bancarios a regularizar para una lista
	 */
	public void deleteListPagoBancarioPorRegularizar(List params);
	
	/**
	 * @param params
	 * Archiva los pagos bancarios a regularizar para una lista
	 */
	public void archivarListPagoBancarioPorRegularizar(List params);
	
	/**
	 * @param params
	 * Regitra el lote bancario
	 */
	public void executeRegistrarLoteBancario (Map criteria);
	
	/**
	 * @param Params
	 * Actualiza los cupones en trmite a Depuracin
	 */
	public void updateListCuponesTramite (List Params);
	
	/**
	 * @param Params
	 * Obtiene Datos Cabecera Pagos Bancarios Por Regularizar Dividir Pago
	 */
	public Map getPagosBancariosPorRegularizarDividirPagoByFilter(Map criteria);
	

}
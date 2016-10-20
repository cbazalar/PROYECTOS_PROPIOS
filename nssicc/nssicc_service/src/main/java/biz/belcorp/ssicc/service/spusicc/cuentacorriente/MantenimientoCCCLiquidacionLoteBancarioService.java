/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;



/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCCCLiquidacionLoteBancarioService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"> </a>
 */
public interface MantenimientoCCCLiquidacionLoteBancarioService extends Service {
			
	/**
	 * Obtiene en una lista de los Lotes Bancarios segun Filtro
	 * @param datos
	 * @return
	 */
	public List getLotesBancariosList (Map datos);				
			
	/**
	 * Ejecuta el proceso de Liquidacion de Lote Bancario
	 * @param datos
	 */
	public void executeLiquidarLoteBancario(Map datos);		
	
	/**
	 * Eliminar el Lote Bancario
	 * @param datos
	 */
	public void executeEliminarLoteBancario(Map datos);
	
}

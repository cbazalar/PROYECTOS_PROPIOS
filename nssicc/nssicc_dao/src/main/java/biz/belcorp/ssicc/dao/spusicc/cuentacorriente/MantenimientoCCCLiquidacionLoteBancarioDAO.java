package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * <p>
 * <a href="MantenimientoCCCLiquidacionLoteBancarioDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"> </a>
 */

public interface MantenimientoCCCLiquidacionLoteBancarioDAO extends DAO { 
			
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
	 * Elimina un Lote Bancario
	 * @param datos
	 */
	public void executeEliminarLoteBancario(Map datos);		
}

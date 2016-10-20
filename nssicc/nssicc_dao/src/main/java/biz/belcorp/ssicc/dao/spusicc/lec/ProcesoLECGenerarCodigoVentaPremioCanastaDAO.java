package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * The Interface ProcesoLECGenerarCodigoVentaPremioCanastaDAO.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 31/07/2014
 */
public interface ProcesoLECGenerarCodigoVentaPremioCanastaDAO extends DAO{
	
	/**
	 * Execute proceso lec generar codigo venta premio canasta.
	 *
	 * @param params the params
	 */
	public void executeProcesoLECGenerarCodigoVentaPremioCanasta(Map params);
}
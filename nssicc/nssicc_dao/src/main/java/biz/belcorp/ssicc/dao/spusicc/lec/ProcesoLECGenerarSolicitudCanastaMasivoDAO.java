package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * The Interface ProcesoLECGenerarSolicitudCanastaMasivoDAO.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 31/07/2014
 */
public interface ProcesoLECGenerarSolicitudCanastaMasivoDAO extends DAO{
	
	
	/**
	 * Execute proceso lec generar solicitud canasta masivo.
	 *
	 * @param params the params
	 */
	public void executeProcesoLECGenerarSolicitudCanastaMasivo(Map params);
}
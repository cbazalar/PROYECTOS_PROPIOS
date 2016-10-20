package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCGenerarSolicitudBolsaFaltantesDAO
		extends DAO {

	/**
	 * Proceso que va a generar Solicitudes de Servicio de bolsa faltantes
	 * 
	 * @param params
	 */
	public void executeGenerarSolicitudBolsaFaltantes(Map params);

	/**
	 * Proceso que registra los cierres de zona
	 * @param params
	 */
	public void executeRegistroCierreZona(Map params);
	

}


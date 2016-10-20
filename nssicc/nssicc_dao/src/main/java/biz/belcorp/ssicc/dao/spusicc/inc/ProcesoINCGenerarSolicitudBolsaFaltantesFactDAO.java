package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCGenerarSolicitudBolsaFaltantesFactDAO
		extends DAO {

	/**
	 * Proceso que va a generar Solicitudes de Servicio de bolsa faltantes de facturacion
	 * 
	 * @param params
	 */
	public void executeGenerarSolicitudBolsaFaltantesFact(Map params);

}

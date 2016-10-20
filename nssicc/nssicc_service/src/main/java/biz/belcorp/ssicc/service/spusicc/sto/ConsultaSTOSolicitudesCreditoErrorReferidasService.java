package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Dennys Oliva Iriarte
 */

public interface ConsultaSTOSolicitudesCreditoErrorReferidasService extends Service{

	/**
	 * Devuelve la lista de las solicitudes de credito con errores de referidas
	 * @param criteria
	 * @return
	 */
	public List getSolicitudesCreditoErrorReferidosList(Map criteria);

}
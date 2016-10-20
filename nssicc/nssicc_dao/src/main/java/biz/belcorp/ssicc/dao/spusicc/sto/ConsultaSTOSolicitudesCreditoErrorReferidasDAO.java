package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Dennys Oliva Iriarte
 *
 */
public interface ConsultaSTOSolicitudesCreditoErrorReferidasDAO extends DAO{
	
	/**
	 * Devuelve la lista de las solicitudes de credito con errores de referidas
	 * @param criteria
	 * @return
	 */
	public List getSolicitudesCreditoErrorReferidosList(Map criteria);


}
/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCOBAsignacionCarteraService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCOBAsignacionCarteraService extends Service {

	/**
	 * Ejecuta el proceso de Asignacion de Cartera (Cobranzas)
	 * @param criteria
	 */
	public void executeAsignacionCartera (Map criteria);

	/**
     * @param criteria
     * Envio de correos de confirmacion
     */
    public void executeEnvioMail(Map criteria);
}

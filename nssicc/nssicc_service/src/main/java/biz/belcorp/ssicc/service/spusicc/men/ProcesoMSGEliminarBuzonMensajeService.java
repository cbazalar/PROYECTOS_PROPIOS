/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.men;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoMSGEliminarBuzonMensajeService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
public interface ProcesoMSGEliminarBuzonMensajeService extends Service {

	/**
	 * Proceso que realiza la eliminacin del buzon de mensajes
	 * 
	 * @param criteria
	 */
	public void executeProcesarEliminarBuzonMensaje(Map criteria);

}
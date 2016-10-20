/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCCCGenerarInformacionRecaudadorasService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCCCGenerarInformacionRecaudadorasService extends Service {

	/**
	 * Genera la informacion para la interface Recaudadoras
	 * @param criteria
	 */
	public void executeGenerarInformacionRecaudadoras (Map criteria);
	
}

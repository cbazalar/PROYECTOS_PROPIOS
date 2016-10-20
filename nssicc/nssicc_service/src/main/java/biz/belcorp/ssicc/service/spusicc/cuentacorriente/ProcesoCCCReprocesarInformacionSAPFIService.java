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
 * <a href="ProcesoCCCReprocesarInformacionSAPFIService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCCCReprocesarInformacionSAPFIService extends Service {

	/**
	 * Reprocesa la informacion para la interface SAPFI
	 * @param criteria
	 */
	public void executeReprocesarInformacionSAPFI (Map criteria);
	
	/**
	 * Enviar informacin a SAPFI para Colombia
	 * @param criteria
	 */
	public void executeCierreFacturacion (Map criteria);
	
}

/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoPERCargaVentaDirectaService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
public interface ProcesoPERCargaVentaDirectaService extends Service {

	public String executeCargaVentaDirecta(Map criteria);
	
	public String bloqueoControlAnual(Map criteria);
	
	public void desbloqueoControlAnual(Map criteria);
	
}
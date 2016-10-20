/**
 * 
 */
package biz.belcorp.ssicc.service.spncd;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoPERMovimientosBancariosService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
public interface ProcesoPERCargaVentaDirectaService extends Service {

	public String executeCargaVentaDirecta(Map criteria);
	
}
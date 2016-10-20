package biz.belcorp.ssicc.service.spusicc.comision.retail;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoRETCalculoComisionRetailService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public interface ProcesoRETCalculoComisionRetailService extends Service {

	/**
	 * Calculo de Comision Retail a gerentes de Zona
	 * 
	 * @param criteria
	 */
	public void executeCalculoComisionRetail(Map criteria);
	
}

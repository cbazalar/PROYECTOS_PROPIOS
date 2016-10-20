package biz.belcorp.ssicc.service.spusicc.comision.retail;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoRETReasignacionGerenteZonaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface ProcesoRETReasignacionGerenteZonaService extends Service {
	
	/**
	 * Reasignacion de los Gerentes de Zona
	 * 
	 * @param criteria
	 */
	public void executeReasignacionGerenteZona(Map criteria);
	
}

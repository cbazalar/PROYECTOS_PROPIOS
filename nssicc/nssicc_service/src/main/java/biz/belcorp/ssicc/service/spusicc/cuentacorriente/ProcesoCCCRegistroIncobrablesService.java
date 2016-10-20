package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="ProcesoCCCRegistroIncobrablesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */

public interface ProcesoCCCRegistroIncobrablesService extends Service {

	/**
	 * Ejecuta el proceso de Registro de Incobrables
	 * @param criteria
	 */
	public void executeRegistroIncobrables (Map criteria);
	
}
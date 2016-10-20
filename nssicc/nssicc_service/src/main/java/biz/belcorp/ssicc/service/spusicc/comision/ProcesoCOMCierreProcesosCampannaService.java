package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:cbazalar@csigcomt.com">Carlos Bazalar</a>
 *
 */
/**
 * @author <a href="mailto:cbazalar@csigcomt.com">Carlos Bazalar</a>
 *
 */
public interface ProcesoCOMCierreProcesosCampannaService  extends Service {

	
	/**
	 * Actualiza Cierre Proceso Campaa Comision
	 * @param criteria
	 * @throws Exception
	 */
	public void executeCierreProcesosCampanna(Map criteria)throws Exception;

}

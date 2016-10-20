package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Clase de la declaracin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETEvaluacionProductividadCierreRegionCampaniaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETEvaluacionProductividadCierreRegionCampaniaService extends Service{
	
	/**
	 * Mtodo que realiza el proceso de Evaluacin de productividad al cierre de regin y campaa
	 * @param params
	 */
	public void executeProcesoLETEvaluacionProductividadCierreRegionCampania(Map params);

}

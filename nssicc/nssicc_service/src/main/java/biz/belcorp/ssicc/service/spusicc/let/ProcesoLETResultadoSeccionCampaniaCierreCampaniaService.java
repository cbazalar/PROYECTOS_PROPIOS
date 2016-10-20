package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Clase de la declaracin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETResultadoSeccionCampaniaCierreCampaniaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETResultadoSeccionCampaniaCierreCampaniaService extends Service{
	
	/**
	 * Mtodo que realiza el proceso Resultados de secciones por campaa al cierre de campaa
	 * @param params
	 */
	public void executeProcesoLETResultadoSeccionesCampaniaCieCam(Map params);

}

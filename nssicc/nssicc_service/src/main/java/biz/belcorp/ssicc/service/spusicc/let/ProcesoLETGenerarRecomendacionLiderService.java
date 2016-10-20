package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Clase de la declaracin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETGenerarRecomendacionLiderService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETGenerarRecomendacionLiderService extends Service{
	
	/**
	 * Mtodo que realiza el proceso Generar Recomendaciones de Lideres
	 * @param params
	 */
	public void executeProcesoLETGenerarRecomendacionesLider(Map params);

}

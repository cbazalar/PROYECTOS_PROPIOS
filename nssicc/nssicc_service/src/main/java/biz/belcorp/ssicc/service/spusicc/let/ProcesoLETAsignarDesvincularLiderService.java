package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Clase de la declaracin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETAsignarDesvincularLiderService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETAsignarDesvincularLiderService extends Service{
	
	/**
	 * Mtodo que realiza el proceso Asignar o Desasginar Lideres al inicio de campaa
	 * @param params
	 */
	public void executeAsignarDesvincularLider(Map params);

}

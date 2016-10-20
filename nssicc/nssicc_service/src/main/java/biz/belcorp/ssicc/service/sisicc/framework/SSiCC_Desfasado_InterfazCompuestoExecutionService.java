package biz.belcorp.ssicc.service.sisicc.framework;

import java.util.HashMap;
import java.util.Map;

import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;

/**
 * Service de ejecución de las Interfaces SiSiCC, este Service funciona como
 * interface entre la capa web (invocado por el
 * <code>BaseInterfazAbstractAction</code>) y las implementaciones de los
 * services específicos de cada interfaz.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * DESFASADO
 */
public interface SSiCC_Desfasado_InterfazCompuestoExecutionService {

	/**
	 * Ejecuta la Interfaz SiCC.
	 * 
	 * @param params
	 *            parametros de la interfaz
	 * @return InterfazExecutionResult con los resultados de la ejecucion.
	 */
	public SSiCC_Desfasado_InterfazExecutionResult executeInterfaz(HashMap params) throws Exception;
	
	
}
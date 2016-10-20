package biz.belcorp.ssicc.service.edu;

import java.util.Map;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
public interface ProcesoEDUActualizarPlanillaProgramacionService {
	
	/**
	 * Realiza el proceso de Actualizacin de Planilla Programacin
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeActualizarPlanillaProgramacion(String codigoPais, Map params) throws Exception;
	
	
}

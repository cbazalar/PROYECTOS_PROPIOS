package biz.belcorp.ssicc.service.edu;

import java.util.Map;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
public interface ProcesoEDUInicioProcesosDiariosService {
		
	/**
	 * Realiza Inicio de Procesos Diarios
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeInicioProcesosDiarios(String codigoPais, Map params) throws Exception;

	public void executeProcesoConsultoraRezagadas(String codigoPais, Map params)throws Exception;
	
	
		
}

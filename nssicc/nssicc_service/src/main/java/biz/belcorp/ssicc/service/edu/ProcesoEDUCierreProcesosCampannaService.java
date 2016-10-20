package biz.belcorp.ssicc.service.edu;

import java.util.Map;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
public interface ProcesoEDUCierreProcesosCampannaService {
	
	/**
	 * Realiza el proceso de Cierre Procesos Campaa
	 * @param params
	 * @throws Exception
	 */
	public void executeCierreProcesosCampanna(String codigoPais, Map params) throws Exception ;
	
	
}

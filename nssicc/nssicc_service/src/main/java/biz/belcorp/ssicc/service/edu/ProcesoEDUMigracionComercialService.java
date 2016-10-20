package biz.belcorp.ssicc.service.edu;

import java.util.Map;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
public interface ProcesoEDUMigracionComercialService {
	
		
	/**
	 * Realiza el proceso de Migracion del Sistema Comercial
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeMigracionComercial(String codigoPais, Map params) throws Exception;
	
	
	
}

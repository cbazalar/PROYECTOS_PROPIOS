package biz.belcorp.ssicc.service.edu;

import java.util.Map;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 *
 */
public interface ProcesoEDURecodificacionConsultoraService {
	
	/**
	 * Realiza el proceso de Recodificacion de clientes
	 * @param params
	 * @throws Exception
	 */
	public void executeRecodificacionConsultora(Map params) ;
	
}

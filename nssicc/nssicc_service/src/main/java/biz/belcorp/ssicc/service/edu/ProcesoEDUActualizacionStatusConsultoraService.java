package biz.belcorp.ssicc.service.edu;

import java.util.Map;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 *
 */
public interface ProcesoEDUActualizacionStatusConsultoraService {
	
	/**
	 * Realiza el proceso de Actualizacin de status Consulora
	 * @param params
	 * @throws Exception
	 */
	public void executeActualizacionStatusConsultora(Map params) throws Exception;
	
}

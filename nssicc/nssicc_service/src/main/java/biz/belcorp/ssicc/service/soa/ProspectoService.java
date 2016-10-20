/**
 * 
 */
package biz.belcorp.ssicc.service.soa;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;





/**
 * @author peextsbuchelli
 *
 */
public interface ProspectoService extends Service{

	/**
	 * obtine cronograma para envio a SOA
	 * @param criteria
	 * @return
	 */
	public List getValidacionCrediticiaUsuario(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public int getLengthDocumento(Map criteria); 

	/**
	 * Obtiene los motivos de Bloqueo de una consultora
	 * @param criteria
	 * @return
	 */
	public List getMotivosBloqueoXConsultora(Map criteria); 
	
}

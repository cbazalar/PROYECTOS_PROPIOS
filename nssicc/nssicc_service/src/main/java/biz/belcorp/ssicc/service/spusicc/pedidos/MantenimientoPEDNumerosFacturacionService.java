/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author sguerra
 *
 */
public interface MantenimientoPEDNumerosFacturacionService extends Service {

	/**
	 * Obtiene la lista de sociedades
	 * @param criteria
	 * @return
	 */
	public List getSociedadList();
	
	/**
	 * Obtiene la lista de tipos de documentos
	 * @param criteria
	 * @return
	 */
	public List getTipoDocumentoList();
	
	/**
	 * Obtiene la lista de n�meros de facturaci�n 
	 * @param criteria
	 * @return
	 */
	public List getNumerosFacturacionList(Map criteria);

	/**
	 * Elimina los datos del n�mero de facturaci�n capturados
	 * @param criteria
	 */
	public void deleteNumerosFacturacion(Map criteria);

}

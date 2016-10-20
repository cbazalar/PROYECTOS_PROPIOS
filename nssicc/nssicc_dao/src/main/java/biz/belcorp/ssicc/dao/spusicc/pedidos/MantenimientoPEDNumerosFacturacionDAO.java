/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author sguerra
 *
 */
public interface MantenimientoPEDNumerosFacturacionDAO extends DAO {

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
	 * Inserta los datos del n�mero de facturaci�n capturados
	 * @param criteria
	 */
	public void insertNumerosFacturacion(Map criteria);

	/**
	 * Elimina los datos del n�mero de facturaci�n capturados
	 * @param criteria
	 */
	public void deleteNumerosFacturacion(Map criteria);

	/**
	 * Actualiza los datos del n�mero de facturaci�n capturados
	 * @param criteria
	 */
	public void updateNumerosFacturacion(Map criteria);

	/**
	 * Inserta los datos del n�mero de facturaci�n capturados en el hist�rico
	 * @param criteria
	 */
	public void insertHistoricoNumerosFacturacion(Map criteria);

	/**
	 * Actualiza los datos del n�mero de facturaci�n capturados en el hist�rico
	 * @param criteria
	 */
	public void updateHistoricoNumerosFacturacion(Map criteria);

	/**
	 * Metodo que valida si ya se encuentra registrado el n�mero de facturaci�n
	 * @param criteria
	 * @return
	 */
	public String getValidarNumerosFacturacion(Map criteria);
	
}

/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.emprendedoras;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */

public interface MantenimientoEMPEmprendedoraService extends Service {

	/**
	 * Metodo que obtiene el listado de regimenes (Solo Peru)
	 * @param criteria 
	 * @return
	 */
	public List getRegimenes(Map criteria);
	
	/**
	 * Metodo que obtiene el listado de tipo de emprendedora (Fastrack o regular)
	 * @return
	 */
	public List getTipoEmprededoras();	
	
	/**
	 * Metodo que retorna los datos de la consultora
	 * @param criteria
	 * @return
	 */
	public List getDatosConsultora(Map criteria);

	/**
	 * Metodo que valida los requisitos de creacion de emprendedora (Fastrack o regular)
	 * @param datos
	 */
	public List executeValidarEmprendedora(Map datos);

	/**
	 * Metodo que actualiza los datos telefono, mail de la consultora
	 * @param datos
	 */
	public void updateDatosCliente(Map datos);

	/**
	 * Metodo que inserta datos de la emprendedora	 
	 * @param datos
	 */
	public void insertDatosEmprendedora(Map datos);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getTextoComunicacion(Map criteria);
	
	/**
	 * @param clienteComunicacion
	 */
	public void insertClienteComunicacion(ClienteComunicacion clienteComunicacion);
}

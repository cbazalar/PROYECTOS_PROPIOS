package biz.belcorp.ssicc.dao.spusicc.emprendedoras;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;

/**
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */

public interface MantenimientoEMPEmprendedoraDAO extends DAO {

	/**
	 * Metodo que obtiene el listado de regimenes (Solo Peru)
	 * @param criteria 
	 * @return
	 */
	public List getRegimenes(Map criteria);
	
	/**
	 * Metodo que obtiene el listado de tipo de emprendedora (Fustruck o regular)
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
	 * Metodo que valida los requisitos de Emprendedora Regular
	 * @param datos
	 */
	public void executeValidarEmprendedoraRegular(Map datos);

	/**
	 * Metodo que valida los requisitos de Emprendedora Fast-Track
	 * @param datos
	 
	public void executeValidarEmprendedoraFastTrack(Map datos);
    */
	/**
	 * Metodo que devuelve el listado de requisitos que no cumple para la creacion de la emprendedora
	 * @param criteria
	 * @return
	 */
	public List getRequisitosNoCumple(Map criteria);

	/**
	 * Metodo que actualiza los datos de telefonos, mail de la consultora
	 * @param datos
	 */
	public void updateDatosCliente(Map datos);

	/**
	 * Metodo que inserta a la emprendedora
	 * @param datos
	 */
	public void insertDatosEmprendedora(Map datos);

	/**
	 * @param datos
	 */
	public void executeValidarEmprendedoraFastTrack(Map criteria);

	/**
	 * @param datos
	 * @return
	 */
	public List getRecomendadas(Map criteria);
	
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

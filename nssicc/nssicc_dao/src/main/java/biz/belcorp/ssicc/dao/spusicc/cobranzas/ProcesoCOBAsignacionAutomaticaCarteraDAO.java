/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * <p>
 * <a href="ProcesoCOBAsignacionAutomaticaCarteraDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCOBAsignacionAutomaticaCarteraDAO extends DAO {

	/**
	 * Ejecuta proceso de Asignacion Automatica de Cartera (Cobranzas)
	 * @param criteria
	 */
	public void executeAsignacionAutomaticaCartera (Map criteria);

	/**
	 * @param criteria
	 * Obtiene la parametria para envio de correos
	 */
	public List getEnvioMailsAsignacionAutomaticaCarteraParams(Map criteria);
	
	/**
	 * Obtiene las carteras asignadas por el proceso de Asignacion de Carteras
	 */
	public List getCarterasAsignadasProceso();
	
	/**
	 * Obtiene los parametros para el envio de emails
	 * @param criteria
	 * @return
	 */
	public List getEnvioMailsAsignacionCarteraParams(Map criteria);
	
	/**
	 * Obtiene la lista de cobradores a los que se les va informar por email
	 * @param criteria
	 * @return
	 */
	public List getListaCobradores(Map criteria);
	
	/**
	 * Obtiene la lista de los supervisores a los que se les va informar por email
	 * @param criteria
	 * @return
	 */
	public List getListaSupervisores(Map criteria);
	
	/**
	 * Obtiene el cuerpo del mensaje del cobrador
	 * @param criteria
	 * @return
	 */
	public List getListaCuerpoEmailCobrador(Map criteria);
	
	/**
	 * Obtiene el cuerpo del mensaje del supervisor
	 * @param criteria
	 * @return
	 */
	public List getListaCuerpoEmailSupervisor(Map criteria);
}

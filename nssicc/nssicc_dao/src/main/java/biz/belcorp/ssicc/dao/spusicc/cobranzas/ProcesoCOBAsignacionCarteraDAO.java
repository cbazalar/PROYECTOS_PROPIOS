/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * <p>
 * <a href="ProcesoCOBAsignacionCarteraDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCOBAsignacionCarteraDAO extends DAO {

	/**
	 * Ejecuta proceso de Asignacion de Cartera (Cobranzas)
	 * @param criteria
	 */
	public void executeAsignacionCartera (Map criteria);

	/**
	 * @param criteria
	 * Obtiene la parametria para envio de correos
	 */
	public List getEnvioMailsAsignacionCarteraParams(Map criteria);
	
	/**
	 * Obtiene las carteras asignadas por el proceso de Asignacion de Carteras
	 */
	public List getCarterasAsignadasProceso();
}

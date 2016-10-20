/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoCOBActualizarCarteraDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCOBActualizarCarteraDAO extends DAO {

	/**
	 * Ejecuta proceso de Actualizar cartera (Cobranzas)
	 * @param criteria
	 */
	public void executeActualizarCartera (Map criteria);
	
	/**
	 * @param criteria
	 * Obtiene la parametria para envio de correos
	 */
	public List getEnvioMailsActualizarCarteraParams(Map criteria);
}

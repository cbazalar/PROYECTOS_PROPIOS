/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoCOBActualizarIncobrableDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCOBActualizarIncobrableDAO extends DAO {

	/**
	 * Ejecuta proceso de Actualizar Incobrable (Cobranzas)
	 * @param criteria
	 */
	public void executeActualizarIncobrable (Map criteria);
	
	
}

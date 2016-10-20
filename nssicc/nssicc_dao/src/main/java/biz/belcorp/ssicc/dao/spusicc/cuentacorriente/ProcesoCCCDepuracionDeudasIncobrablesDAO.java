/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoCCCDepuracionDeudasIncobrablesDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
public interface ProcesoCCCDepuracionDeudasIncobrablesDAO extends DAO {

	/**
	 * Ejecuta proceso de Depuracion de Deudas Incobrables
	 * @param criteria
	 */
	public void executeDepuracionDeudasIncobrables (Map criteria);
		
}

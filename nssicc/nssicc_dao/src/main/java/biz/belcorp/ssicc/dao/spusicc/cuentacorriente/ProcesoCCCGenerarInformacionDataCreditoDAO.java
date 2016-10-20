/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * <p>
 * <a href="ProcesoCCCGenerarInformacionDataCreditoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCCCGenerarInformacionDataCreditoDAO extends DAO {

	/**
	 * Ejecuta proceso de Bloqueo Financiero Por Dias  De Atraso
	 * @param criteria
	 */
	public void executeGenerarInformacionDataCredito (Map criteria);
	
}

/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoCCCDepuracionSaldosMenoresAcreedoresDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
public interface ProcesoCCCDepuracionSaldosMenoresAcreedoresDAO extends DAO {

	/**
	 * Ejecuta proceso de Depuracion de Saldos Menores Acreedores en Cuenta Corriente
	 * @param criteria
	 */
	public void executeDepuracionSaldosMenoresAcreedores (Map criteria);
		
}

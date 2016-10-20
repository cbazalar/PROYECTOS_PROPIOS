package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCContabilizarPagoAbonoBancarioDAO
		extends DAO {

	/**
	 * Proceso que va a Contabilizar Pago Abono Bancario
	 * 
	 * @param params
	 */
	public void executeContabilizarPagoAbonoBancario(Map params);
	
}


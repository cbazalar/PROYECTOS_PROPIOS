package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCGenerarAbonoCuentaCorrienteDAO
		extends DAO {

	/**
	 * Proceso que va a Generar Abono Cuenta Corriente
	 * 
	 * @param params
	 */
	public void executeGenerarAbonoCuentaCorriente(Map params);

}

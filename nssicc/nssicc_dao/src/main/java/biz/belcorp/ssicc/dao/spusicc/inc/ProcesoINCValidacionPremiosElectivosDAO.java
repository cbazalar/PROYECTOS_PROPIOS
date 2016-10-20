package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCValidacionPremiosElectivosDAO
		extends DAO {

	/**
	 * Proceso que realizara la validacion de premios electivos
	 * 
	 * @param params
	 */
	public void executeValidacionPremiosElectivos(Map params);

}

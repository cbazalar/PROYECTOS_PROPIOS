package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCGenerarCuponesElectronicosDAO
		extends DAO {

	/**
	 * Proceso que realiza la generacion de Cupones Electronicos
	 * 
	 * @param params
	 */
	public void executeGenerarCuponesElectronicos(Map params);

}


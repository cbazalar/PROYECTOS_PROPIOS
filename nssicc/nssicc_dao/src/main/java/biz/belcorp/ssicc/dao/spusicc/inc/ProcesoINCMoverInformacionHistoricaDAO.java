package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author cbazalar
 *
 */
public interface ProcesoINCMoverInformacionHistoricaDAO
		extends DAO {

	/**
	 * Proceso que Mueve Informacion Historica
	 * 
	 * @param params
	 */
	public void executeMoverInformacionHistorica(Map params);

}


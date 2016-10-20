package biz.belcorp.ssicc.dao.spusicc.love;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLOVAtencionPremiosDAO
		extends DAO {

	/**
	 * Proceso que Genera las solicitudes de premiacion del programa Love
	 * 
	 * @param params
	 */
	public void executeAtencionPremios(Map params);

}

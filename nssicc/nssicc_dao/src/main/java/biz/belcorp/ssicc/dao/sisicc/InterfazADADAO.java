package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface InterfazADADAO extends DAO{

	/**
	 * Ejecuta el proceso de envio de Gerenets de Region y Zona
	 * @param params
	 */
	public void executeInterfazADAEnviarGerentesRegionZona(Map params);

}
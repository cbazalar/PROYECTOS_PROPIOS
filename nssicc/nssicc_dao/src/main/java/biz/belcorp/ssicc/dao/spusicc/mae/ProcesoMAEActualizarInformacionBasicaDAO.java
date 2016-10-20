package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Ivan Tocto
 *
 */
public interface ProcesoMAEActualizarInformacionBasicaDAO extends DAO {

	/**
	 * Proceso que va a actualizar la informacion basica de las consultoras
	 * 
	 * @param params
	 */
	public void executeActualizarInformacionBasica(Map params);

}

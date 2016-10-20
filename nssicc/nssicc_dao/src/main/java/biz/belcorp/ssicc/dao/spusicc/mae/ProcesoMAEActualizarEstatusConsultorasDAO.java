package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoMAEActualizarEstatusConsultorasDAO
		extends DAO {

	/**
	 * Proceso que va a actualizar los estatus de las consultoras
	 * 
	 * @param params
	 */
	public void executeActualizarEstatusConsultoras(Map params);

}

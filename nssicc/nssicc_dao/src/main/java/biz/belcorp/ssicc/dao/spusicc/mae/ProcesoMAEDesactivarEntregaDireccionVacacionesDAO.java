package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoMAEDesactivarEntregaDireccionVacacionesDAO
		extends DAO {

	/**
	 * Proceso que permite desactivar entrega en Direccin de Vacaciones
	 * 
	 * @param params
	 */
	public void executeDesactivarEntregaDireccionVacaciones(Map params);

}


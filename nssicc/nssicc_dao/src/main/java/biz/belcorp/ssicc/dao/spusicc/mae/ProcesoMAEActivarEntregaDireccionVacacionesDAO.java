package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoMAEActivarEntregaDireccionVacacionesDAO
		extends DAO {

	/**
	 * Proceso que permite activar entrega en Direccin de Vacaciones
	 * 
	 * @param params
	 */
	public void executeActivarEntregaDireccionVacaciones(Map params);

}


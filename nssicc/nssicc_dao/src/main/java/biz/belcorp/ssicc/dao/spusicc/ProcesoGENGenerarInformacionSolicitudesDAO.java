package biz.belcorp.ssicc.dao.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoGENGenerarInformacionSolicitudesDAO extends DAO{

	/**
	 * Ejecuta el Proceso de Generacion de Informacion de Solicitudes
	 * @param params
	 */
	public void executeProcesoGENGenerarInformacionSolicitudes(Map params);

}
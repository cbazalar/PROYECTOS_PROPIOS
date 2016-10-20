package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLIDDesvinculacionAutomaticaLideresDAO
		extends DAO {

	/**
	 * Proceso que Permite desasignar una lider de una seccion cuando cumpla
	 * con ciertos criterios
	 * 
	 * @param params
	 */
	public void executeDesvinculacionAutomaticaLideres(Map params);

}
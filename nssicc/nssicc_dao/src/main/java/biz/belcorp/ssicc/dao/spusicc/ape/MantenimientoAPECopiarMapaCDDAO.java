package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Nicols Lpez
 *
 */
public interface MantenimientoAPECopiarMapaCDDAO extends DAO{
	
	/**
	 * Realiza la generacion de una nueva copia de un Mapa de Centro de Distribucin con su detalle
	 * @param criteria
	 */
	public void executeGenerarMapaCDDetalle(Map criteria);

}

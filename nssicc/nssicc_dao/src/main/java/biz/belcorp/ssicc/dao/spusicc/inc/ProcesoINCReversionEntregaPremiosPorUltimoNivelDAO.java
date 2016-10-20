package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCReversionEntregaPremiosPorUltimoNivelDAO
		extends DAO {

	/**
	 * Proceso que realiza la reversion de entrega de premios 
     * por sobrepasar lmite de ltimo nivel
	 * 
	 * @param params
	 */
	public void executeReversionEntregaPremiosPorUltimoNivel(Map params);

}


package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCReversionEntregaPremiosDAO
		extends DAO {

	/**
	 * Proceso que realiza la reversion de entrega de premios de Cupones Electronicos
	 * 
	 * @param params
	 */
	public void executeReversionEntregaPremios(Map params);

}


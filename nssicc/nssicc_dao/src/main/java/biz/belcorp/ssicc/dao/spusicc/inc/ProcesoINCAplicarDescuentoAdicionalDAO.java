package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCAplicarDescuentoAdicionalDAO
		extends DAO {

	/**
	 * Proceso que Aplica descuento adicional en la facturacin
	 * 
	 * @param params
	 */
	public void executeAplicarDescuentoAdicional(Map params);

}


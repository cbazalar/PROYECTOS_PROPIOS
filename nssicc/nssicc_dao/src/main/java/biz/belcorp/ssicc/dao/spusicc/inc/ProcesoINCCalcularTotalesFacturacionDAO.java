package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCCalcularTotalesFacturacionDAO
		extends DAO {

	/**
	 * Proceso que va a calcular el total de demanda de las ordenes de compra
	 * 
	 * @param params
	 */
	public void executeCalcularTotalesFacturacion(Map params);

}


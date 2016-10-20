package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextsapaza
 *
 */
public interface MantenimientoLIDParametroService extends Service {

	/**
	 * Obtenemos el parametro de lideres configurado para el pais
	 * 
	 * @param codigoPais
	 * @return
	 */
	public Map getParametro(String codigoPais);
		
	/**
	 * Actualiza el parametro de lideres
	 * 
	 * @param map
	 */
	public void updateParametro(Map params);
	
}

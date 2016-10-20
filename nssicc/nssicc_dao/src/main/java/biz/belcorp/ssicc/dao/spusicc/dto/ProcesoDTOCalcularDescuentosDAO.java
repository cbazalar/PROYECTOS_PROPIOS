package biz.belcorp.ssicc.dao.spusicc.dto;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoDTOCalcularDescuentosDAO
		extends DAO {

	/**
	 * Proceso que calcula y asignan descuentos
	 * 
	 * @param params
	 */
	public void executeCalcularDescuentos(Map params);

}

package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface MantenimientoLIDParametroDAO extends DAO {

	/**
	 * Obtenemos el parametro de lideres configurado para el pais
	 * 
	 * @param codigoPais
	 * @return
	 */
	public Map getParametro(String codigoPais);
		
	/**
	 * Inserta el parametro de lideres 
	 * 
	 * @param map
	 */
	public void insertParametro(Map params);
	
	/**
	 * Actualiza el parametro de lideres
	 * 
	 * @param map
	 */
	public void updateParametro(Map params);
	
}

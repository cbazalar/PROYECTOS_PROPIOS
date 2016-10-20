package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoLECCalcularObjetivosBonosDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 *         
 */
public interface ProcesoLECCalcularObjetivosBonosDAO extends DAO{
	
	/**
	 * Mtodo que permite Calcular los objetivos 
	 *  para obtener Bonos
	 * @param params
	 */
	public void executeProcesoLECCalcularObjetivosBonos(Map params);
}
package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoLETCalculoGananciaMasivoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Juan Altamirano
 *         
 */
public interface ProcesoLETCalculoGananciaMasivoDAO extends DAO{
	
	/**
	 * Mtodo que realiza el calculo de ganancias masivo
	 * @param params
	 */
	public void executeProcesoLETCalculoGananciaMasivo(Map params);

}

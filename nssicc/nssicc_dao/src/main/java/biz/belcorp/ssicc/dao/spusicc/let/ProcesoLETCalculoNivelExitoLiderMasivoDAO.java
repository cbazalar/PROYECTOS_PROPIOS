package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoLETCalculoNivelExitoLiderMasivoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Aurelio Oviedo
 *         
 */
public interface ProcesoLETCalculoNivelExitoLiderMasivoDAO extends DAO{
	
	/**
	 * Mtodo que realiza el calculo de nivel de exito de lider masivo
	 * @param params
	 */
	public void executeProcesoLETCalculoNivelExitoLiderMasivo(Map params);
}
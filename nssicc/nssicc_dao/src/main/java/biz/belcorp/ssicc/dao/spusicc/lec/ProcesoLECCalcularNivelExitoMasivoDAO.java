package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoLECCalcularNivelExitoMasivoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 *         
 */
public interface ProcesoLECCalcularNivelExitoMasivoDAO extends DAO{
	
	/**
	 * Mtodo que permite calcular masivamente el Nivel de xito de las Lderes.
	 * @param params
	 */
	public void executeProcesoLECCalcularNivelExitoMasivo(Map params);
}
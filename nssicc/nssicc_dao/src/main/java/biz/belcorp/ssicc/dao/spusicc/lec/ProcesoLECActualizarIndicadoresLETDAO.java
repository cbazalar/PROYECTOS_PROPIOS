package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLECActualizarIndicadoresLETDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Ivan Tocto.
 *         
 */
public interface ProcesoLECActualizarIndicadoresLETDAO extends DAO{
	
	/**
	 * Mtodo que realiza la Actualizacion de Clasificacion de Lider Masivo
	 * @param params
	 */
	public void executeProcesoLECActualizarIndicadoresLET(Map params);
}

package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETActualizacionClasificacionLiderMasivoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
public interface ProcesoLETActualizacionClasificacionLiderMasivoDAO extends DAO{
	
	/**
	 * Mtodo que realiza la Actualizacion de Clasificacion de Lider Masivo
	 * @param params
	 */
	public void executeProcesoLETActualizacionClasificacionLiderMasivo(Map params);

}

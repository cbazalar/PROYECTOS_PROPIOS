package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLECActualizaClasificacionLiderMasivoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 *         
 */
public interface ProcesoLECActualizaClasificacionLiderMasivoDAO extends DAO{
	
	/**
	 * Mtodo que realiza la Actualizacion de Clasificacion de Lider Masivo
	 * @param params
	 */
	public void executeProcesoLECActualizaClasificacionLiderMasivo(Map params);

}

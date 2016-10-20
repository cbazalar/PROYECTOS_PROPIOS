package biz.belcorp.ssicc.dao.spusicc.emprendedoras;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.model.EstructuraEMPReasignacionMasiva;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */

public interface ProcesoEMPReasignacionMasivaDAO extends DAO {		
	
	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUpload(Map datos);
	
	/**
	 * Metodo que inserta en la tabla temporal
	 * @param estructura
	 */
	public void insertEstructuraEMPReasignacionMasiva(EstructuraEMPReasignacionMasiva estructura);	
		
	/**
	 * Metodo que borra la tabla temporal de carga
	 */
	public void deleteEstructuraEMPReasignacionMasiva();
	
	/**
	 * Metodo que valida y reasigna las nnuevas empresarias
	 */
	public void executeValidarReasignacionMasiva(Map datos);
	
	/**
	 * @return
	 */
	public List getErroresReasignacionMasiva();
	
	/**
	 * Metodo que realiza la vinculacion al cierre de region
	 * @param datos
	 */
	public void executeVinculacionNuevasReactivadas(Map datos);
}
package biz.belcorp.ssicc.dao.spusicc.emprendedoras;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.model.EstructuraEMPPreEmprendedora;

/**
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */

public interface ProcesoEMPCargarPreEmprendedorasDAO extends DAO {
	
	/**
	 * Metodo que obtiene los programas
	 * @return
	 */
	public List getProgramas();
	
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
	public void insertEstructuraEMPPreEmprendedora(EstructuraEMPPreEmprendedora estructura);	
	
	/**
	 * Metodo que borra la tabla temporal de la Carga de pre emprendedoras
	 */
	public void deleteTablasCargaPreEmprendedoras();
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeValidarPreEmprendedoras(Map criteria);		
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaPreEmprendedoras();    
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeProcesarPreEmprendedoras(Map datos);
}
/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraBloqueoFinancieroMasivo;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoCCCBloqueoFinancieroMasivoDAO extends DAO {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUploadBloqueoFinancieroMasivo(Map datos);
	
	/**
	 * Metodo que inserta en la tabla temporal
	 * @param estructura
	 */
	public void insertEstructuraBloqueoFinancieroMasivo(EstructuraBloqueoFinancieroMasivo estructura);
	
	

	/**
	 * Metodo que borra los registros  en la tabla temporal
	 * @param estructura
	 */
	public void deleteTablasBloqueoFinancieroMasivo(Map datos);
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeValidarBloqueoFinancieroMasivo(Map criteria);
	
	
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresBloqueoFinancieroMasivo(Map datos);
    
	
	/**
	 * Metodo que Proceso los Cargos y Abonos Directos
	 * @param criteria
	 */
	public void executeProcesarBloqueoFinancieroMasivo(Map datos);
	
}

package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EstructuraCronogramaCartera;

/**
 * @author <a href="mailto:jflorencioz@belcorp.biz">Jorge Florencio</a>
 */

public interface ProcesoCOBCargarCronogramaCarteraDAO extends DAO {
			
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
	public void insertEstructuraCronogramaCartera(EstructuraCronogramaCartera estructura);	
	
	/**
	 * Metodo que borra la tabla temporal de la Carga del Cronograma Cartera
	 */
	public void deleteTablasCargaCronogramaCartera(Map datos);
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeValidarCronogramaCartera(Map criteria);		
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaCronogramaCartera();    
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeProcesarCronogramaCartera(Map datos);
}
/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.percepciones;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.percepciones.model.EstructuraPercepcionesPagosBancariosMasivos;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoPERCargarPagosBancariosMasivosDAO extends DAO {

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
	public void insertEstructuraPagosBancariosMasivos(EstructuraPercepcionesPagosBancariosMasivos estructura);
	
	
	/**
	 * Metodo que borra la tabla temporal de la Caga Fuente Venta Prevista
	 */
	public void deleteTablasCargaPagosBancariosMasivos();
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeValidarPagosBancariosMasivos(Map criteria);
	
	
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaPagosBancariosMasivos();
    
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeProcesarPagosBancariosMasivos(Map datos);
	
}

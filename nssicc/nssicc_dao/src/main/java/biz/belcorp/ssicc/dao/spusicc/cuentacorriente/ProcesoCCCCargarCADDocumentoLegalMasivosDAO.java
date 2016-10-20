/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraCADDocumentoLegalMasivos;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoCCCCargarCADDocumentoLegalMasivosDAO extends DAO {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUploadCADDocumentoLegalMasivos(Map datos);
	
	/**
	 * Metodo que inserta en la tabla temporal
	 * @param estructura
	 */
	public void insertEstructuraCADDocumentoLegalMasivos(EstructuraCADDocumentoLegalMasivos estructura);
	
	

	/**
	 * Metodo que borra los registros  en la tabla temporal
	 * @param estructura
	 */
	public void deleteTablasCargaCADDocumentoLegalMasivos(Map datos);
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeValidarCADDocumentoLegalMasivos(Map criteria);
	
	
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaCADDocumentoLegalMasivos(Map datos);
    
	
	/**
	 * Metodo que Proceso los Cargos y Abonos Directos
	 * @param criteria
	 */
	public void executeProcesarCADDocumentoLegalMasivos(Map datos);
	
}

package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoCCCCargarCADDocumentoLegalMasivosService extends Service {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUploadCADDocumentoLegalMasivos(Map datos);
	
	/**
	 * Metodo que borra la tabla temporal de la Carga de Cargos DocumentoLegal Masivos
	 */
	public void deleteTablasCargaCADDocumentoLegalMasivos(Map datos);
	
	/**
	 * Metodo que realiza la validacion del archivo y la carga a la tabla temporal
	 * @param datos
	 * @throws Exception 
	 */
	public void executeValidarCADDocumentoLegalMasivos(Map datos) throws Exception;
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaCADDocumentoLegalMasivos(Map datos);
	
	/**
	 * Metodo que procesa proceso los CAD en SICC
	 * @param datos 
	 */
	public void executeProcesarCADDocumentoLegalMasivos(Map datos);

}

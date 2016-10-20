package biz.belcorp.ssicc.service.spusicc.emprendedoras;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */

public interface ProcesoEMPReasignacionMasivaService extends Service {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUpload(Map datos);
	
	/**
	 * @param datos
	 * @throws Exception
	 */
	public void executeProcesarReasignacionMasiva(Map datos) throws Exception;
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresReasignacionMasiva();
		
}
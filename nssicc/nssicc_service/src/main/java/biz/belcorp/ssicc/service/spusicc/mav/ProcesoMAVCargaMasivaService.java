package biz.belcorp.ssicc.service.spusicc.mav;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAVCargaMasivaService extends Service {
	
	/**
	 * Ejecuta la carga del archivo excel y la validacion de los datos cargados
	 * 
	 * @param params
	 * @return
	 */
	List executeValidarCargaMasiva(Map params) throws Exception;
	
	
	/**
	 * Ejecuta el proceso de Actualizacion de Carga Masiva
	 * 
	 * @param params
	 * @return
	 */
	void executeActualizarCargaMasiva(Map params);

}

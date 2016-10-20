package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCDarPorAtendidoBolsaFaltantesService  extends Service{

	/**
	 * Retorna la lista de Concursos Vigentes
	 * @return
	 */
	List getListConcursoVigentes();
	
	/**
	 * Retorna los registros del excel del archivo cargado 
	 * @param criteria
	 * @return
	 */
	List cargarArchivoExcel(Map criteria) throws Exception;

	/**
	 * Ejecura el proceso de dar por atendido a premios de bolsa de faltantes para que 
	 * no puedan atenderse en el futuro
	 * 
	 * @param params
	 */
	void executeActualizarBolsaFaltantes(Map params);
	
	/**
	 * Valida si el archivo cumple con el formato
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	boolean validarArchivoExcel(Map criteria)throws Exception;


}

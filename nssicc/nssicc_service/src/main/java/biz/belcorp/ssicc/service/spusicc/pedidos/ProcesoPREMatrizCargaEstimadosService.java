package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 *
 */
public interface ProcesoPREMatrizCargaEstimadosService
{
	
	/**
	 * @author sguerra
	 * @param criteria
	 * @return
	 * @throws Exception
	 *
	 * Funcin que carga los datos del archivo excel a la tabla temporal
	 */
    public Map cargarArchivoExcel(Map criteria) throws Exception;

    /**
     * @author sguerra
     * @param params
     * @return
     * 
     * Funcin que validar los datos cargados a la tabla temporal
     */
    public List executeValidarCargaEstimados(Map params);
    
    /**
     * @author sguerra
     * @param params
     * 
     * Procedimiento que actualiza los datos en la tabla de carga de estimados
     */
    public void executeActualizarCargaEstimados(Map params);
}

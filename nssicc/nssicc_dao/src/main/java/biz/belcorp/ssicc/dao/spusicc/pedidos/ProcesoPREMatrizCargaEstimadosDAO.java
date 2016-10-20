package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 *
 */

public interface ProcesoPREMatrizCargaEstimadosDAO extends DAO
{
	/**
	 * @author sguerra
	 * @param codUsuario
	 * 
	 * Procedimiento que elimina registros anteriores de la tabla temporal
	 */
    public void deleteCargaEstimados(String codUsuario);
    
    /**
     * @author sguerra
     * @param params
     * 
     * Procedimiento que inserta registros en la tabla temporal
     */
    public void insertCargaEstimados(Map params);

    /**
     * @author sguerra
     * @param params
     * 
     * Procedimiento que valida los datos cargados en la tabla temporal
     */
    public void executeValidarCargaEstimados(Map params);

    /**
     * @author sguerra
     * @param params
     * @return
     * 
     * Funcin que obtiene una lista de los registros que cumplieron la validacin  
     */
    public List getCargaEstimadosList(Map params);
    
    /**
     * @author sguerra
     * @param params
     * 
     * Procedimiento que actualiza la tabla que almacena la carga de estimados
     */
    public void executeActualizarCargaEstimados(Map params);
    
}

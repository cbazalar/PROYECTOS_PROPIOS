package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */

public interface ProcesoPREModificacionesMasivasDAO extends DAO{
	
	/**
	 * Limpia la tabla de Modificaciones Masivas Temporal
	 */
	public void deleteTablaModificacionesMasivasTemporal();
	
	/**
	 * Metodo que inserta Modificaciones Masivas Temporal
	 * @param criteria
	 */
	public void insertModificacionesMasivasTemporal(Map criteria);
	
	/**
     * @param params
     * @return
     * 
     * Funcin que valida los datos cargados a la tabla temporal
     */
    public void executeValidarCargaModificacionesMasivas(Map params);
	
    /**
     * @param params
     * @return
     * 
     * Funcin que obtiene una lista de los registros que cumplieron la validacin  
     */
    public List getCargaModificacionesMasivasList(Map params);
    
    /**
     * @param params
     * 
     * Procedimiento que actualiza los datos en la tabla de modificaciones masivas
     */
    public void executeActualizarCargaModificacionesMasivas(Map params);   
}
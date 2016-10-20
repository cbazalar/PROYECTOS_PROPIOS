package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public interface ProcesoPREModificacionesMasivasService {
	
	/**
	 * Limpia la tabla de Modificaciones Masivas Temporal
	 */
	public void deleteTablaModificacionesMasivasTemporal();
	
	/**
	 * Metodo que inserta Modificaciones Masivas Temporal
	 * @param lineas
	 */
	public void insertModificacionesMasivasTemporal(List lineas);
	
	/**
     * @param params
     * @return
     * 
     * Funcin que valida los datos cargados a la tabla temporal
     */
    public List executeValidarCargaModificacionesMasivas(Map params);
	
    /**
     * @param params
     * 
     * Procedimiento que actualiza los datos en la tabla de modificaciones masivas
     */
    public void executeActualizarCargaModificacionesMasivas(Map params);
}
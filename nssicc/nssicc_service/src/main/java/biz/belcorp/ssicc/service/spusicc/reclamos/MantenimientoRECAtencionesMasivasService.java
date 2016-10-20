/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.ProcesoRECAtencionesMasivas;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author esanchez@sigcomt.com - Eduardo Snchez
 *
 */
public interface MantenimientoRECAtencionesMasivasService extends Service {
	
	/**
	 * Elimina los registros de GTT de las Atenciones Masivas
	 * @param map
	 */
	public void eliminarAtencionesMasivas();
	
	/**
	 * Inserta en la tabla GTT los registros de las Atenciones Masivas
	 * @param criteria
	 */
	public void insertAtencionesMasivasError(Map criteria);
	
	/**
	 * Metodo que devuelve CERO o UNO, para indicar si existe la Consultora
	 * @param criteria
	 * @return
	 */
	public String getValidarCodigoConsultora(Map criteria);
	
	/**
	 * Obtiene la lista de las Atenciones Masivas por Tipo de Producto Matriz
	 * @param criteria
	 * @return
	 */
	public List getAtencionesMasivasPorMatrizList(Map criteria);
	
	/**
	 * Obtiene la lista de las Atenciones Masivas por Tipo de Producto Premio
	 * @param criteria
	 * @return
	 */
	public List getAtencionesMasivasPorPremioList(Map criteria);

	/**
	 * Inserta en la tabla GTT los registros de las Atenciones Masivas Correctas
	 * @param criteria
	 */
	public void insertAtencionesMasivasConsultoraVenta(Map criteria);
	
	/**
	 * Este metodo obtiene todos las Atenciones Masivas Correctas
	 * @param procesoRECAtencionesMasivas
	 * @throws Exception 
	 */
	public Map getMantenimientoRECAtencionesMasivas(ProcesoRECAtencionesMasivas procesoRECAtencionesMasivas) throws Exception;
	
	/**
	 * Retorna el numero de lote a ejecutar el proceso de atenciones masivas
	 * @return
	 */
	public String getNumeroLoteAtencionesMasivas();

	/**
	 * Metodo que procesa el ingreso de atenciones masivas
	 * @param criteria
	 */
	public String procesarIngresoAtencionesMasivas(Map criteria);

	/**
	 * Obtiene la lista de las Atenciones Masivas de la tabla GTT
	 * @param criteria
	 * @return
	 */
	public List getGTTDetalleIngresoAtencionesMasivasList(Map criteria);
	
	/**
	 * Este metodo ejecuta el proceso de todos las Atenciones Masivas Correctas
	 * @param criteria
	 */
	public Map executeProcesarRECAtencionesMasivas(Map criteria);

	/**
	 * Metodo que devuelve el cdigo de la Consultora
	 * @param documentoIdentidad
	 * @return
	 */
	public String getObtenerCodigoConsultora(String documentoIdentidad);
	
}

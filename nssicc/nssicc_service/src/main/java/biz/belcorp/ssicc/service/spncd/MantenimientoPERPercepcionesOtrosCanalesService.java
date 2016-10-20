/**
 * 
 */
package biz.belcorp.ssicc.service.spncd;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoPERMovimientosBancariosService.java.html"> <i>View
 * Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes
 *         Prncipe</a>
 * 
 */
public interface MantenimientoPERPercepcionesOtrosCanalesService extends Service {

	
	public List getConsolidadoPercepcionesAcumulado(Map criteria);
	/**
	 * 
	 * @param cabecera
	 * @return
	 */
	public List getPercepcionesOtrosCanales(Map criteria);

	/**
	 * 
	 * @param cabecera
	 * @return
	 */
	public List getConsolidadoPercepcion(
			InterfazPERActualizarPercepcionesConsolidado consolidado);

	/**
	 * 
	 * @param cabecera
	 * @param usuario
	 */
	public void updatePercepcionesOtrosCanales(
			InterfazPERActualizarPercepcionesConsolidado consolidado,
			Usuario usuario);

	/**
	 * 
	 * @param cabecera
	 * @param usuario
	 */
	public void removePercepcionesOtrosCanales(
			InterfazPERActualizarPercepcionesConsolidado consolidado,
			Usuario usuario);

	/**
	 * 
	 * @param consolidado
	 * @param usuario
	 */
	public void insertInterfazPERActualizarPercepcionesConsolidado(
			InterfazPERActualizarPercepcionesConsolidado consolidado,
			Usuario usuario);

	/**
	 * 
	 * @return el siguiente numero de Lote para insertar
	 */
	public String getNextCorrelativo(String codigoPais);
	
	/**
	 * Retorna Lista Consolidado Percepciones Acumulado por dia
	 * @param criteria
	 * @return
	 */
}
	
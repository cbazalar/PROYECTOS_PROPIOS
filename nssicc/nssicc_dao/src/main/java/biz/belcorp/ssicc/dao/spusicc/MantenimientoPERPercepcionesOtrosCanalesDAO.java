/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoPERPercepcionesOtrosCanalesDAO.java.html"> <i>View
 * Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes
 *         Prncipe</a>
 * 
 */
public interface MantenimientoPERPercepcionesOtrosCanalesDAO extends DAO {

	
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
	 * @return el siguiente numero de Lote para insertar
	 */
	public String getNextCorrelativo(String codigoPais);

	
	/**
	 * Eliminar  Percepciones de Sistemas externos del Consolidado para reproceso
	 * @param param
	 */
	public void deletePercepcionesSistemasExternos(Map param);
	
	
	/**
	 * Retorna lista Consolidao percepciones Acumulado Dia
	 * @param criteria
	 * @return
	 */
	public List getConsolidadoPercepcionesAcumuladoDia(Map criteria);
	
	/**
	 * Actualiza Numeracion Comprobante Percepcion Autorizado por Sunat
	 * @param map
	 * @param usuario
	 */
	public void updateNumeracionComprobantesSunat(Map map, Usuario usuario);
	
	/**
	 * Inserta Numeracion Comprobante Percepcion Autorizado por Sunat
	 * @param map
	 * @param usuario
	 */
	public void insertNumeracionComprobantesSunat(Map map, Usuario usuario);

	/**
	 * Retorna la lista de numeracin de cpmrobantes autorizados por SUNAT
	 * @param map
	 * @return
	 */
	public List getNumeracionComprobantesSunatList(Map map);
}

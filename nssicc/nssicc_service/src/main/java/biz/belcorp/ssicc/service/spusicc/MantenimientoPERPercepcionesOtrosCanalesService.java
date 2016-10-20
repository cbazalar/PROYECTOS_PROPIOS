package biz.belcorp.ssicc.service.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoPERPercepcionesOtrosCanalesService extends Service{
	
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
	 *  Eliminar Percepciones de Sistemas externos del Consolidado para reproceso
	 * @param param
	 */
	public void deletePercepcionesSistemasExternos(Map param);
	
	
	/**
	 * Retorna Lista Consolidado Percepciones Acumulado por dia
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

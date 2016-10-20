/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;

/**
 * @author peextrramirez - Rosalvina Ramirez
 *
 */
public interface MantenimientoRECIngresoAtencionesDAO extends DAO {
	/**
	 * Metodo que devuelve el Codigo de Consultora
	 * @param criteria
	 * @return
	 */
	public String getCodigoConsultora(Map criteria);
	/**
	 * Metodo que devuelve los premios
	 * @param criteria
	 * @return
	 */
	public List getReclamosPremio(Map criteria);
	/**
	 * Metodo que devuelve la Matriz de Facturacin
	 * @param criteria
	 * @return
	 */
	public List getReclamosMatriz(Map criteria);
	/**
	 * Metodo que inserta el detalle del ingreso de atencion en una tabla temporal
	 * @param boletaRecojoDetalle
	 */
	public void insertTemporalDetallesIngresoAtencion(BoletaRecojoDetalle boletaRecojoDetalle);
	/**
	 * Metodo que procesa el ingreso de atencion
	 * @param params
	 */
	public String procesarIngresoAtenciones(Map params);		
	/**
	 * Mtodo los parametros de la tabla REC_PARAM_INGRE_ATENC
	 * @param params
	 * @return
	 */
	public List getCodigosParamIngreAtenc(Map params);
	
	/**
	 * Mtodo que retorna la lista de los Tipos de Operacin
	 * @param params
	 * @return 
	 */
	public List getTipoOperacionList(Map params);
	
	/**
	 * Retorna el numero de lote a ejecutar el proceso
	 * @return
	 */
	public String getNumeroLote();
	
	/**
	 * Retorna la lista que han sido atendidas
	 * @return
	 */
	public List getAtencion(Map params);
	
	/**
	 * Retorna la lista de anulaciones
	 * @return
	 */
	public List getAnulacion(Map params);
	
	/**Retorna la lista de detalle de atencion
	 * @param map
	 * @return
	 */
	public List getDetalleAtencion(Map map);
	
	/**
	 * Retorna la lista de detalle de anulacion
	 * @param map
	 * @return
	 */
	public List getDetalleAnulacion(Map map);
	
	/**
	 * Ejecuta validaciones previas a la eliminacin de lote de atencion
	 * @param map
	 */
	public void validarEliminacionLote(Map map);
	
	/**
	 * Elimina lote de atencion
	 * @param map
	 */
	public void eliminarLoteAtencion(Map map);
	
	/**
	 * @param criteria
	 * @return La Campaa Actual
	 */
	public String getObtenerCampahniaActual(Map criteria);
	
	/**
	 * Devuele el oid de la Carga de Cliente
	 * @return
	 */
	public String getOidCargaCliente();

	/**
	 * Inserta en la tabla temporal el archivo a cargar
	 * @param map
	 */
	public void insertaClienteFile(Map map);
	
	/**
	 * Devuelve la lista de cliente cargados
	 * @param criteria
	 * @return
	 */
	public List getCargaClienteList(Map criteria);

	/**
	 * Obtiene el cuadro resumen de la carga de cliente
	 * @param criteria
	 * @return
	 */
	public List getResumenCargaClienteList(Map criteria);
	
	/**
	 * Devuelve el oid de cliente
	 * @param criteria
	 * @return
	 */
	public String getOidCliente(Map criteria);
	/**
	 * Metodo que procesa el ingreso de atencion
	 * @param params
	 */
	public void updateRegistroSinError(Map params);
		
	/**
	 * Metodo que devuelve el Codigo de Consultora de acuerdo al documento de identidad
	 * @param criteria
	 * @return
	 */
	public String getCodigoConsultoraPorDocumentoIdentidad(Map criteria);
	
	/**
	 * Devuelve la longitud de tipo documento
	 * @param criteria
	 * @return
	 */
	public String getLongitudTipoDocumento(Map criteria);
	
	/**
	 * @param criteria
	 * @return La Campana Actual y activa
	 */
	public String getObtenerCampahniaActiva(Map criteria);
}
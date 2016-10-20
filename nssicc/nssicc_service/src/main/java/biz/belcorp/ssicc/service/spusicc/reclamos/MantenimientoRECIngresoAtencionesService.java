package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;


public interface MantenimientoRECIngresoAtencionesService extends Service {
	/**
	 * Metodo que devuelve el Codigo de Consultora
	 * @param criteria
	 * @return
	 */
	public String getCodigoConsultora(Map criteria);
	/**
	 * Metodo que devuelve los Premios
	 * @param criteria
	 * @return
	 */
	public List getReclamosPremio(Map criteria);
	/**
	 * Metodo que devuelve la Matriz de Facturacion
	 * @param criteria
	 * @return
	 */
	public List getReclamosMatriz(Map criteria);
	/**
	 * Metodo que procesa las atenciones
	 * @param criteria
	 * @param procesarList
	 */
	public String procesarAtenciones(Map criteria, List procesarList);
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
	 * Inserta en la tabla temporal el archivo a cargar
	 * @param clienteList
	 * @param criteria
	 */
	public void insertaClienteFile(List clienteList, Map criteria);
	
	/**
	 * Devuele el oid de la Carga de Cliente
	 * @return
	 */
	public String getOidCargaCliente();
	
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
	 * Metodo que actualiza registro sin error
	 * @param criteria
	 * @param procesarList 
	 */
	public void actualizarRegistroSinError(Map criteria, List procesarList);
	
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
}

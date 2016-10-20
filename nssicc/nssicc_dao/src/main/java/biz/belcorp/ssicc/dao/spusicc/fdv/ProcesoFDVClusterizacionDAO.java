package biz.belcorp.ssicc.dao.spusicc.fdv;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * <p>
 * <a href="ProcesoFDVClusterizacionDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ProcesoFDVClusterizacionDAO extends DAO{

	/**
     * Invoca al stored procedure que realizara los calculos de capitalizacion, percentiles,
     * grupo poblacional, calculo de penetracion, jerarquizacion de variables, y actualizacion
     * del estado del proceso a "En proceso de clusterizacion"
     */
	public String executeProcedureClusterizacion(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception;

	/**
	 * Invoca al stored procedure que realizara los calculos para la
	 * distribucin de metas
	 * 
	 * @param codProc
	 *            el codigo del proceso
	 * @throws Exception 
	 */
	public String executeProcedureDistMetas(String codProc, String codPais) throws Exception;

	/**
	 * Invoca al stored procedure que realizara los calculos de inicializacin para la
	 * distribucin de metas
	 * 
	 * @param codProc
	 *            el codigo del proceso
	 * @throws Exception 
	 */
	public String executeProcedureIniDistMetasSecciones(String codProc) throws Exception;

	/**
     * Obtiene un listado de los clusters que asigno el sistema a las zonas a considerar, para que estos sean asignados
     * a las zonas que no son a considerar
     */
	public List getCluAsigSistList(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception;

	/**
     * Obtiene el listado de parametros de distribucion
	 * @param codProc
	 * @throws Exception  
     */
	public List getParamDistribucionList(String codProc) throws Exception;

	/**
     * Obtiene la informacion del proceso cluster en base a su codigo. La excepcion
     * ObjectRetrievalFailureException Runtime Exception es lanzada si no es
     * encontrada.
     * 
     * @param codigo
     *            el codigo del proceso
     * @return Proceso Cluster
     */
	public ProcesoFDVClusterizacion getProcesoCluster(final String codigo);

	/**
     * Obtiene un listado de todos los procesos cluster en base a ciertos criterios los
     * cuales son enviados a travs de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            bsqueda
     * @return Lista de objetos Procesos Cluster
     */
	public List getProcesosClusterByCriteria(Map criteria);
		
	/**
     * Obtiene el listado de las zonas con sus secciones
	 * @param codProc
	 * @throws Exception  
	 */
	public List getZonaSeccionList(String codProc) throws Exception;

	/**
     * Obtiene todas las zonas indicando en una columna si son zonas sin data confiable 
     * enviados a traves de un Map
     * Para el proceso de Clusterizacion - Variables Exogeneas
     */
	public List getZoneNoReliableDataList(String codProc);

	/**
     * Obtiene todas las zonas indicando en una columna si son zonas sin data confiable
     * enviados a traves de un Map
     * Para el proceso de Potencial - Variables de venta
     */
	public List getZoneNoReliableDataVarVentaList(String codProc);

	/**
     * Obtiene todas las zonas indicando en una columna si son zonas oficinas
     * enviados a traves de un Map
     */
	public List getZoneOfficeList(String codProc);

	/**
     * Obtiene todas las zonas que no son a considerar del proceso indicado, y muestra el cluster asignado
     * por el pais
     */
	public List getZonesAsigPaisList(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception;

	/**
     * Obtiene todas las zonas a considerar del proceso indicado, y muestra el cluster asignado
     * por el sistema
     */
	public List getZonesAsigSistList(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception;

	/**
	 * Obtenemos la ruta del directorio temporal donde subiremos 
	 * nuestros archivos en base al codigo de Pais
	 * 
	 * @param codigoPais
	 *            el codigo del Pais seleccionado al momento de loguearse
     * @param cadena
     *            la ruta del directorio temporal
	 */
	public String obtenerPathUpload(String codigoPais);

	/**
     * Actualiza el cluster (asig. x pais) a las zonas a considerar y a las que no son a considerar
	 * @throws Exception 
     */
	public void updateClusterAsigPais(String[] arrayCountryAsigSist,
			String[] arrayCountryAsigPais, String[] arrayZoneAsigPais,
			String[] arrayZoneAsigSist, String codProc) throws Exception;

	/**
	 * Actualiza los nombres de los archivos que lograron adjuntarse, solo los campos que vengan con valor
	 * 
	 * @param procesoFDVClusterizacion
	 * 			objeto con los datos para actualizar
	 * @throws Exception 
	 */
	public void updateFinalUpload(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;
	
	/**
     * Actualiza la informacion de un proceso cluster
     * 
     * @param codigo
     *            ID del objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo informacin del usuario invocador
     */
	public void updateInactiveProcesoCluster(
			String codigo, Usuario usuario);

	/**
     * Actualiza el valor del porcentaje del listado de parametros de distribucion 
     * con relacion al proceso actual
	 * @param listCodParaDist
	 * @param listValParaDist
	 * @param codProc 
	 * @throws Exception 
     */
	public String updateListaParamDist(final String[] listCodParaDist,
			final String[] listValParaDist, final String codProc) throws Exception;

	/**
	 * Actualiza la informacion de un proceso clusterizacion
	 * 
	 * @param procesoFDVClusterizacion
	 *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la informacin
	 */
	public void updateProcesoClusterizacion(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws DataIntegrityViolationException;

	/**
     * Actualiza algunos campos de la tabla fdv_proc e invoca a un stored procedure
     * para la generacion de la propuesta de clusterizacion
     */
	public void updatePropuestaClusterizacion(
			final String[] listSelectedOffice, final String[] listSelectedNoReliableData,
			final String[] listCodZonaOffice, final String[] listCodZonaNoReliableData,
			final ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;

	/**
	 * Actualiza el estado del proceso por el campo 'stProc' pasado como parametro
	 * 
	 * @param codProc
	 *            el id del proceso a ser actualizado
	 * @param staProc
	 * 			  el estado del proceso a ser actualizado
	 * @param usuario
	 * 			  el usuario actual logueado que esta realizando las modificaciones
	 * @throws Exception 
	 */
	public void updateStatusClusterizacion(String codProc, Usuario usuario, String staProc) throws Exception;

	/**
     * Actualiza el campo VAL_MEVD de la tabla FDV_PROCE 
     * correspondiente al id indicado de proceso
	 * @param codProc
	 * @param valMevd
	 * @param usuario 
	 * @throws Exception 
     */
	public String updateValMevd(BigDecimal valMevd, String codProc, Usuario usuario) throws Exception;

	/**
     * Actualiza las campaas y secciones del listado de zonas a considerar
	 * @param listCodZonaDist
	 * @param listCamCaseDist
	 * @param listNroSecoDist
	 * @param codProc 
	 * @throws Exception 
     */
	public String updateZonaSeccion(final String[] listCodZonaDist,
			final String[] listCamCaseDist, final String[] listNroSecoDist, final String codProc) throws Exception;

	/**
	 * Actualiza las zonas con datos de variables de venta no confiables
	 * @param listSelectedNoReliableDataVarVenta
	 * @param codProc
	 * @throws Exception
	 */
	public void updateZonasNoReliableDataVarVenta(String[] listSelectedNoReliableDataVarVenta, String codProc) throws Exception;
	
	/**
	 * Obtiene un listado de los aos de los procesos existentes
	 * @param codigoPais
	 * @return
	 */
	public List getAnyosProcesosDistribucionRealizadaByPais(final String codigoPais);
	
	/**
	 * Obtiene las regiones de un proceso de FDV
	 * @param codigoProceso
	 * @return
	 */
	public List getRegionesByProceso(final String codigoProceso);
	
	/**
	 * Obtiene las zonas de una region de un proceso de FDV
	 * @param codigoRegion
	 * @param codigoProceso
	 * @return
	 */
	public List getZonasByProcesoRegion(final String codigoRegion, final String codigoProceso);
	
	/**
	 * Obtiene el valor de un parametro de proceso
	 * @param codigoProceso
	 * @param codigoParametro
	 * @return
	 */
	public Double getValorParametroProceso(final String codigoProceso, final String codigoParametro);
	
	/**
	 * Obtiene un listado de todos los parametros de un proceso
	 * @param codigoProceso
	 * @return
	 */
	public List getParametrosProceso(final String codigoProceso);
	
	/**
	 * Obtiene un listado de todas las zonas para el ajuste por el DV
	 * @param codigoProceso
	 * @return
	 */
	public List getZonasAjusteDV(final String codigoProceso);
	
	/**
	 * Actualiza los valores ajustados
	 * 
	 * @param listCodZonaAjus
	 * @param listValZonaAjus
	 * @param codigoProceso
	 */
	public void updateValoresAjustados(final String[] listCodZonaAjus, final String[] listValZonaAjus, final String codigoProceso) throws Exception;
	
	/**
	 * Ejecuta el proceso de ajuste de metas
	 * 
	 * @param codigoProceso
	 */
	public String executeProcesoAjusteMetas(final String codigoProceso) throws Exception;
	
	/**
	 * Obtiene la version del proceso.
	 * 
	 * @param codigoProceso
	 * @param anyoPeriodoProceso
	 * @param periodoProceso
	 * @return
	 */
	public Integer getVersionProceso(final String codigoProceso, final String anyoPeriodoProceso, final String periodoProceso);
	
	public String executeProcedureClusterizacionDesdeArchivo(ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception;
	
	public void updateValorAjustadoArchivo(final List listValAjusteMeta,  final String codigoProceso) throws Exception;
}

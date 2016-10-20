package biz.belcorp.ssicc.service.spusicc.fdv;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVClusterizacionDAO;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="ProcesoFDVClusterizacionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ProcesoFDVClusterizacionService extends Service{

	/**
     * Invoca al stored procedure que realizara los calculos de capitalizacion, percentiles,
     * grupo poblacional, calculo de penetracion, jerarquizacion de variables, y actualizacion
     * del estado del proceso a "En proceso de clusterizacion"
     */
	public void executeProcedureClusterizacion(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception;

	/**
	 * Invoca al stored procedure que realizara los calculos para la
	 * distribucin de metas
	 * 
	 * @param codProc
	 *            el codigo del proceso
	 * @throws Exception 
	 */
	public void executeProcedureDistMetas(String codProc, String codPais) throws Exception;

	/**
	 * Invoca al stored procedure que realizara los calculos de inicializacin para la
	 * distribucin de metas
	 * 
	 * @param codProc
	 *            el codigo del proceso
	 * @throws Exception 
	 */
	public void executeProcedureIniDistMetasSecciones(String codProc) throws Exception;

	/**
     * Obtiene el listado de campaas de acuerdo a la campaa corte del proceso
	 * @param procesoFDVClusterizacion
	 * @throws Exception  
	 */
	public List getCampaniaCambioList(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception;

	
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
	 * Obtiene la informacin del proceso cluster en base al cdigo.
	 * 
	 * @param codigo
	 *            el codigo del proceso
	 * @return proceso 
	 * 			  el proceso cluster
	 */
	public ProcesoFDVClusterizacion getProcesoCluster(final String codigo);

	/**
     * Obtiene todos los procesos cluster tomando como criterios de busqueda los valores
     * enviados a traves de un Map
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
	 * Elimina un proceso cluster de la base de datos en base a su codigo
	 * 
	 * @param codigo
	 *            el codigo del proceso cluster
     * @param usuario
     *            el usuario que elimina la informacin
	 */
	public void removeProcesoCluster(final String codigo, Usuario usuario);

	/**
	 * Setter del DAO, conveniente para pruebas unitarias
	 */
	public void setProcesoFDVClusterizacionDAO(ProcesoFDVClusterizacionDAO procesoFDVClusterizacionDAO);

	/**
     * Actualiza el cluster (asig. x pais) a las zonas a considerar y a las que no son a considerar
	 * @param codProc 
	 * @throws Exception 
     */
	public void updateClusterAsigPais(String[] arrayCountryAsigSist,
			String[] arrayCountryAsigPais, String[] arrayZoneAsigPais,
			String[] arrayZoneAsigSist, String codProc) throws Exception;
	
	
	/**
	 * Actualiza los nombres de los archivos que se subieron nuevamente, 
	 * y actualiza el estado a '2' que quiere decir los archivos que cargaron en su totalidad
	 * 
	 * @param procesoFDVClusterizacion
	 *            el objeto a ser actualizado
	 * @throws Exception 
	 */
	public void updateFinalUpload(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;

	/**
     * Actualiza el valor del porcentaje del listado de parametros de distribucion 
     * con relacion al proceso actual
	 * @param listCodParaDist
	 * @param listValParaDist
	 * @param codProc 
	 * @throws Exception 
     */
	public void updateListaParamDist(String[] listCodParaDist,
			String[] listValParaDist, String codProc) throws Exception;

	/**
	 * Actualiza la informacion de un proceso clusterizacion
	 * 
	 * @param procesoFDVClusterizacion
	 *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la informacin
	 * @throws Exception 
	 */
	public void updateProcesoClusterizacion(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;

	/**
     * Actualiza algunos campos de la tabla fdv_proc e invoca a un stored procedure
     * para la generacion de la propuesta de clusterizacion
     */
	public void updatePropuestaClusterizacion(
			String[] listSelectedOffice, String[] listSelectedNoReliableData,
			String[] listCodZonaOffice, String[] listCodZonaNoReliableData,
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;

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
	public void updateValMevd(BigDecimal valMevd, String codProc, Usuario usuario) throws Exception;

	/**
     * Actualiza las campaas y secciones del listado de zonas a considerar
	 * @param listCodZonaDist
	 * @param listCamCaseDist
	 * @param listNroSecoDist
	 * @param codProc 
	 * @throws Exception 
     */
	public void updateZonaSeccion(String[] listCodZonaDist,
			String[] listCamCaseDist, String[] listNroSecoDist, String codProc) throws Exception;
	
	/**
	 * Actualiza las zonas con datos de variables de venta no confiables
	 * @param listSelectedNoReliableDataVarVenta
	 * @param codProc
	 * @throws Exception
	 */
	public void updateZonasNoReliableDataVarVenta(final String[] listSelectedNoReliableDataVarVenta, final String codProc) throws Exception;
	
	/**
	 * Obtiene un listado de los aos de los procesos existentes
	 * @param codigoPais
	 * @return
	 */
	public List getAnyosProcesosDistribucionRealizadaByPais(final String codigoPais);
	
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
	 * Obtiene los totales de los ajustes
	 * @param lista
	 * @return
	 */
	public Map getTotalesZonasAjusteDV(List lista);
	
	/**
	 * Actualiza los valores ajustados
	 * 
	 * @param listCodZonaAjus
	 * @param listValZonaAjus
	 * @param codigoProceso
	 */
	public void updateValoresAjustados(final String[] listCodZonaAjus, final String[] listValZonaAjus, final String codigoProceso);
	
	/**
	 * Ejecuta el proceso de ajuste de metas
	 * 
	 * @param codigoProceso
	 */
	public void executeProcesoAjusteMetas(final String codigoProceso);
	
	
	public void executeProcedureClusterizacionDesdeArchivo(ProcesoFDVClusterizacion procesoFDVClusterizacion)throws Exception;
	
	public void updateValorAjustadoArchivo(final List listValAjusteMeta,  final String codigoProceso) throws Exception;
}

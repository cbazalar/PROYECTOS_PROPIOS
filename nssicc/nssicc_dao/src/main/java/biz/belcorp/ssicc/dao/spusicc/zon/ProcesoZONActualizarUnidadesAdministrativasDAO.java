package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.ConsultoraATrasvasar;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadAdministrativaACrear;

/**
 * Implementacion del DAO que ejecutara los metodos de proceso de unidades administrativas
 * <p>
 * <a href="ProcesoZONActualizarUnidadesAdministrativasDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public interface ProcesoZONActualizarUnidadesAdministrativasDAO extends DAO {

	/**
	 * Se validan que las regiones seleccionadas no hayan cerrado en el periodo indicado
	 * 
	 * @param params
	 */
	void validarRegionCerrada(Map params);
	
	/**
	 * Se valida que las regiones no hayan iniciado su facturacion
	 * 
	 * @param params
	 */
	void validarRegionFacturando(Map params);
	
	/**
	 * Se inicia las tablas temporales
	 * 
	 * @param params
	 */
	void iniciarTablasUnidadAdministrativa(Map params);
	
	/**
	 * Inserta un registro por cada linea del archivo Excel o DBF
	 * 
	 * @param unidadAdministrativa
	 */
	void insertUnidadAdministrativa(UnidadAdministrativa unidadAdministrativa);
	
	/**
	 * Elimina las registros cuyas regiones no han sido seleccionadas
	 * 
	 * @param params
	 */
	void eliminarRegiones(List params);
	
	/**
	 *  Elimina las registros cuyas zonas no han sido seleccionadas
	 * @param params
	 */
	void eliminarZonas(List params);
	
	/**
	 * En caso no se indica SubGerencia de Ventas, se le completara
	 * 
	 * @param params
	 */
	void completarSGV(Map params);
	
	/**
	 * Se realiza validacion de estructura y de negocio
	 * 
	 * @param params
	 */
	void primeraValidacion(Map params);
	
	/**
	 * Se realiza una segunda validacion, detectando las unidades administrativas a crear, modificar, trasvasar y borrar
	 * 
	 * @param params
	 */
	void ejecutarValidacion(Map params);
	
	/**
	 * Se recupera las nuevas zonas a crear
	 * 
	 * @return
	 */
	List obtenerUniAdmACrear();
	
	/**
	 * Se graban las descripciones de las nuevas zonas
	 * 
	 * @param uniAdm
	 */
	void grabaDescripcionUniAdmACrear(UnidadAdministrativaACrear uniAdm);
	
	/**
	 * Se realiza el proceso de alta, baja, trasvase o modificaciones de las unidades administrativas correspondientes
	 * 
	 * @param params
	 */
	void ejecutarActualizacionUnidadesAdministrativas(Map params);
	
	/**
	 * Se recupera las consultoras cuyos territorios van a ser eliminados
	 * 
	 * @return
	 */
	List obtenerConsultorasATrasvasar();
	
	/**
	 * Se graban los nuevos territorios de las consultoras cuyos territorios van a ser eliminados
	 * 
	 * @param consTrasv
	 */
	void grabaCodigoTerritorioNuevoConsultoraTrasvasada(ConsultoraATrasvasar consTrasv);
	
	/**
	 * Se realiza el trasvase de las consultoras cuyos territorios van a ser eliminados 
	 * 
	 * @param params
	 */
	void ejecutarTrasvaseConsultorasErrorEliminacion(Map params);
	
	/**
	 * Se recupera las zonas activas
	 * 
	 * @param params
	 * @return
	 */
	List obtenerZonasDefinitivas(Map params);

	/**
	 * Se recupera la cantidad de registros a procesar x Operacion (Alta, Baja, Trasvase, Modificacion)
	 * 
	 * @param criteria
	 * @return
	 */
	public String obtenerCantidadRegistrosOperacion(Map criteria);

	/**
	 * Inserta un registro en la entidad temporal [Territorio Consultora]
	 * 
	 * @param params
	 */
	public void insertTerritorioConsultora(Map params);

	/**
	 * Elimina los registros de la entidad temporal [Territorio Consultora]
	 * 
	 * @param params
	 */
	public void deleteTerritorioConsultoraTmp();	

	/**
	 * Ejecuta Validaciones del Proceso de Modificar Territorio a Consultoras
	 * 
	 * @param params
	 */
	public void execValidacionesModTerritorioConsultoras(Map params);

	/**
	 * Ejecuta la Actualizacion del Proceso de Modificar Territorio a Consultoras
	 * 
	 * @param params
	 */	
	public void execActualizarModTerritorioConsultoras(Map params);
	
	/**
	 * Verifica si la region seleccionada se encuentra en el archivo
	 * @param codRegion
	 * @return
	 */
	public String obtenerRegionProcesar(String codRegion);	
	
	/**
	 * Verifica si la Zona seleccionada se encuentra en el archivo
	 * @param codRegion
	 * @return
	 */
	public String obtenerZonaProcesar(String codZona);

	/**
	 * Inserta un registro en la entidad temporal de [Unidad Administrativa Demanda]
	 * 
	 * @param params
	 */
	public void insertarUnidadAdministrativaDemanda(Map params);

	/**
	 * Elimina los registros de la entidad temporal [Unidad Administrativa Demanda]
	 * 
	 * @param params
	 */	
	public void deleteUnidadAdministrativaDemanda();

	/**
	 * Obtiene la lista de regiones demanda por Pais
	 * 
	 * @param params
	 * @return
	 */
	public List getRegionesDemandaByPais(Map params);

	/**
	 * Obtiene la lista de registros cargados en la entidad temporal [Unidad Administrativa Demanda]
	 * 
	 * @param params
	 * @return
	 */
	public List getUnidadAdministrativaDemanda(Map params);

	/**
	 * Obtiene la lista de Errores encontrados en la validacion de regiones demanda por Pais
	 * 
	 * @param params
	 * @return
	 */
	public List getErroresUnidadAdministrativaDemanda(Map params);
	
	/**
	 * Ejecuta Validaciones del Proceso de Crear Territorios en Regiones Demanda Anticipada
	 * 
	 * @param params
	 */
	public void executeValidarUnidadAdministrativaDemanda(Map params);

	/**
	 * Ejecuta El Proceso de  Crear Territorios en Regiones Demanda Anticipada
	 * 
	 * @param params
	 */	
	public void executeProcesarUnidadAdministrativaDemanda(Map params);
	
	
	/**
	 * Lista Validaciones de Unidades Administrativas x Consultoras
	 * @param params
	 * @return
	 */
	public List getUnidadAdministrativaConsultora(Map params);
	
	
	/**
	 * Retorna total Regsitros de la Tabla de Unidades Administrativas x Consultoras
	 * @param params
	 * @return
	 */
	public Integer getUnidadAdministrativaConsultoraTotal(Map params);
	
	
	/**
	 * Retorna total Regsitros de la Tabla de Unidades Administrativas x Consultoras con error
	 * @param params
	 * @return
	 */
	public Integer getUnidadAdministrativaConsultoraTotalError(Map params);
	
	/**
	 * Retorna total Regsitros de la Tabla de Unidades Administrativas x Consultoras OK
	 * @param params
	 * @return
	 */
	public Integer getUnidadAdministrativaConsultoraTotalOK(Map params); 
	
	
	/**
	 * Elimina los registros del Temporal de Zonas (Directorio de Ventas)
	 */
	public void deleteTemporalZonas();

	/**
	 * Elimina los registros del Temporal de Regiones (Directorio de Ventas)
	 */
	public void deleteTemporalRegiones();

	/**
	 * Elimina los registros del Temporal de Campaas (Directorio de Ventas)
	 */
	public void deleteTemporalCampanha();
	
	/**
	 * Elimina los registros del Temporal de Control de Facturacin (Directorio de Ventas)
	 */
	public void deleteTemporalControlFacturacion();
	
	/**
	 * Elimina los registros del Temporal de Consultoras (Directorio de Ventas)
	 */
	public void deleteTemporalConsultoras();
	
	
	/**
	 *  Inserta Regiones desde el Comercial hacia Temporal Regiones
	 *	@param  
	 */
	public void insertTemporalRegiones(final List list, final Map params);

	/**
	 *  Inserta Zonas desde el Comercial hacia Temporal Zonas
	 *	@param  
	 */
	public void insertTemporalZonas(final List list, final Map params);

	/**
	 *  Inserta Control Facturacion desde el Comercial hacia Temporal Control
	 *	@param  
	 */
	public void insertTemporalControlFacturacion(final List list, final Map params);
	
	
	/**
	 * Inserta Campaa desde el Comercial hacia Temporal Campaas
	 * @param list
	 * @param params
	 */
	public void insertTemporalCampanha(final List list, final Map params);
	
	
	/**
	 * Inserta Consultoras desde el Comercial hacia Temporal Consultoras
	 * @param list
	 * @param params
	 */
	public void insertTemporalConsultoras(final List list, final Map params);
	
	
	/**
	 * Actualiza Regiones desde el temporal (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 */
	public void updateMaestroRegiones(String codigoPais, Map params);
	
	
	/**
	 * Actualiza Zonas desde el temporal (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 */
	public void updateMaestroZonas(String codigoPais, Map params);
	
	/**
	 * Actualiza Campaas desde el temporal (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 */
	public void updateMaestroCampanha(String codigoPais, Map params);
	
	/**
	 * Actualiza Control de Facturacion desde el temporal (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 */
	public void updateMaestroControlFacturacion(String codigoPais, Map params);
	
	/**
	 * Actualiza Consultoras desde el temporal (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 */
	public void updateMaestroConsultoras(String codigoPais, Map params);
	
	/**
	 * Devuelve Lista de Regiones Directorio de Ventas
	 * @param codigoPais
	 * @param params
	 * @return
	 */
	public List getZONRegionesDirectorioVentas(String codigoPais, Map params);
	
	/**
	 * Devuelve Lista de Zonas Directorio de Ventas
	 * @param codigoPais
	 * @param params
	 * @return
	 */
	public List getZONZonasDirectorioVentas(String codigoPais, Map params);
		
	/**
	 * Devuelve Lista de Historial Gerentes Directorio de Ventas
	 * @param codigoPais
	 * @param params
	 * @return
	 */
	public List getZONHistorialGerentesDirectorioVentas(String codigoPais, Map params, int contador, int cantidadRegistros);
	
	/**
	 *  Se validan que las zonas seleccionadas no hayan cerrado en el periodo indicado	
	 */
	public void validarZonaCerrada(Map params);
		
	/**
	 *  Se valida que las regiones no hayan iniciado su facturacion
	 */
	public void validarZonaFacturando(Map params);	
	
}

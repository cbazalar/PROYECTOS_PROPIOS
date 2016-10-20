package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoZONActualizarUnidadesAdministrativasService"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public interface ProcesoZONActualizarUnidadesAdministrativasService extends Service {
	
    /**
     * Se validan que las regiones seleccionadas no hayan cerrado en el periodo indicado y
     * que las regiones no hayan iniciado su facturacion
     * 
     * @param params
     */
    void validarRegiones(Map params);
        
	/**
	 * Se inicia las tablas temporales
	 * 
	 * @param params
	 */
	void iniciarTablas(Map params);
	
	/**
	 * Carga los archivos seleccionados por el usuario a la BD
	 * @param params
	 */
	void cargarArchivos(Map params);
	
	/**
	 * Elimina las registros cuyas regiones no han sido seleccionadas
	 * 
	 * @param params
	 */
	void eliminarRegionesNoEnviadas(List params);
	
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
	 * @param unidaes
	 */
	void grabarDescripcionesUniAdmACrear(List unidaes);
	
	/**
	 * Se graban los nuevos territorios de las consultoras cuyos territorios van a ser eliminados
	 * 
	 * @param consultorasATrasvasar
	 * @param params
	 * @throws Exception
	 */
	public void grabarNuevosTerritorioConsultoraATrasvasar(List consultorasATrasvasar, Map params) throws Exception;
	
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
	 * @param params
	 * @return
	 */
	public String obtenerCantidadRegistrosOperacion(Map params);

	/**
	 * Inserta un registro en la entidad temporal [Territorio Consultora]
	 * 
	 * @param params
	 */
	public void insertTerritorioConsultora(Map params) throws Exception;

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
	 * Valida Formato del archivo Excel
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validarFormatoArchivoExcel(Map criteria) throws Exception;
	
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
	 * Carga el archivo Excel y Ejecuta validaciones del Proceso de Crear Territorios en Regiones Demanda Anticipada
	 * 
	 * @param params
	 */
	public boolean executeValidarUnidadAdministrativaDemanda(Map params) throws Exception;

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
	 * Ejecuta los Procesos de Directorio de Ventas Recepcionar Maestros
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeProcesoZONProcesoCargaRecepcionMaestros(String codigoPais, Map params) throws Exception;
	
	
	/**
	 * Ejecuta los Procesos de Directorio de Ventas Enviar Entidades Directorio
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeProcesoZONProcesoEnviarEntidadesDirectorio(String codigoPais, Map params) throws Exception;
	
	/**
	 * Se validan que las zonas seleccionadas no hayan cerrado en el periodo indicado y
     * que las zonas no hayan iniciado su facturacion
	 * @param params
	 */
	public void validarZonas(Map params);
	
	
	
}

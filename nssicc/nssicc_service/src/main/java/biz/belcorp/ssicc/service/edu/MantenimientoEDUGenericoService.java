package biz.belcorp.ssicc.service.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.ConexionExterna;
import biz.belcorp.ssicc.dao.edu.model.ControlFacturacion;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictado;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictadoZona;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenerico;
import biz.belcorp.ssicc.dao.edu.model.EquivalenciaMatrizServicio;
import biz.belcorp.ssicc.dao.edu.model.EstadoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.Local;
import biz.belcorp.ssicc.dao.edu.model.MaestroCliente;
import biz.belcorp.ssicc.dao.edu.model.MaestroInstructora;
import biz.belcorp.ssicc.dao.edu.model.MensajeEducacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroClasificacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroGenerico;
import biz.belcorp.ssicc.dao.edu.model.ParametroRegistroClasificacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroReporte;
import biz.belcorp.ssicc.dao.edu.model.Sala;
import biz.belcorp.ssicc.dao.edu.model.ServicioCapacitacion;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * @author peextrvela
 *
 */
public interface MantenimientoEDUGenericoService {
	
	/**
	 * Metodo que verifica si esta activado la Conexion Externa al Sistema Comercial mediante la 
	 * Parametrizacion en la tabla respectiva
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @return
	 */
	public boolean verificaConexionExternaSistemaComercial(String codigoPais, String codigoEmpresa);
	
	/**
	 * Obtiene la informacion parametros genèricos
	 * 
	 * @param parametroGenerico
	 * 			Objeto ParametroGenerico, considerar ingresar el atributo estado
	 * @return Lista de Parametros de Frecuencia de Calificaciones
	 */
	public List getFrecuenciaCalificaciones(ParametroGenerico parametroGenerico);
	
	/**
	 * Obtiene la informacion parametros genèricos
	 * 
	 * @param parametroGenerico
	 * 			Objeto ParametroGenerico, considerar ingresar el atributo estado
	 * @return Lista de Parametros de Secuencia de Pedidos
	 */
	public List getSecuenciaPedidos(ParametroGenerico parametroGenerico);

	/**
	 * Obtiene la informacion parametros genèricos
	 * 
	 * @param parametroGenerico
	 * 			Objeto ParametroGenerico, considerar ingresar el atributo estado
	 * @return Lista de Parametros de Ambitos de Dictados
	 */
	public List getAmbitoDictados(ParametroGenerico parametroGenerico);

	/**
	 * Obtiene la informacion parametros genèricos
	 * 
	 * @param parametroGenerico
	 * 			Objeto ParametroGenerico, considerar ingresar el atributo estado
	 * @return Lista de Parametros de Nivel de Ventas
	 */
	public List getNivelVentas(ParametroGenerico parametroGenerico);
	/**
	 * Obtiene la informacion parametros genèricos
	 * 
	 * @param parametroGenerico
	 * 			Objeto ParametroGenerico, considerar ingresar el atributo estado
	 * @return Lista de Parametros de Tipos de Despachos
	 */
	public List getTipoDespachos(ParametroGenerico parametroGenerico);

	/**
	 * Obtiene informacion de Tipos de costo de cursos
	 * @param parametroGenerico
	 * @return
	 */
	public List getTipoCostoCurso(ParametroGenerico parametroGenerico);
	
	/**
	 * Obtiene la informacion parametros genèricos
	 * 
	 * @param parametroGenerico
	 * 			Objeto ParametroGenerico, considerar ingresar el atributo estado
	 * @return Lista de Parametros de Indicadores de Tipos de Despachos
	 */
	public List getTipoIndicadorDespachos(ParametroGenerico parametroGenerico);

	/**
	 * Obtiene la informacion parametros genèricos
	 * 
	 * @param empresaComercializadora
	 * 			Objeto EmpresaComercializadora, considerar ingresar el atributo pais y estado
	 * @return Lista de Empresas Comercializadoras por Pais
	 */
	public List getEmpresasComercializadorasByPais(EmpresaComercializadora empresaComercializadora);

	/**
	 * Actualiza el registro del parámetro de curso de capacitación
	 * @param parametroCursoCapacitacion
	 * 			objeto ParametroCursoCapacitacion con datos del pais y empresa comercializadora
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion, Usuario usuario);
	
	/**
	 * Devuelve un registro del parámetro de curso de capacitación
	 * @param getParametroCurso
	 * 			objeto ParametroCursoCapacitacion con datos del pais y empresa comercializadora
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public ParametroCursoCapacitacion getParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion);

	/**
	 * Devuelve un registro del parámetro de curso de capacitación
	 * @param maestroClientes
	 * 			objeto MaestroCliente con datos del cliente
	 */
	public List getMaestroClientes(MaestroCliente maestroClientes);
	
	/**
	 * Devuelve una lista de registros del maestro de instructoras
	 * @param maestroInstructora
	 */
	public List getMaestroInstructoraByCriteria(MaestroInstructora maestroInstructora);

	/**
	 * Devuelve una lista de parámetros de Clasificación
	 * @param parametroClasificacion
	 */
	public List getParametroClasificacionByCriteria(ParametroClasificacion parametroClasificacion);

	/**
	 * Insertar un registro en el Maestro de Instructoras
	 * @param maestroInstructora
	 * 			objeto MaestroInstructora poblada
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void insertMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario) throws Exception;

	/**
	 * Actualiza un registro en el Maestro de Instructoras
	 * @param maestroInstructora
	 * 			objeto MaestroInstructora poblada
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario);

	/**
	 * Elimina Logicamente un registro en el Maestro de Instructoras
	 * @param maestroInstructora
	 * 			objeto MaestroInstructora poblada
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateRemoveMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario);
	
	/**
	 * Insertar un registro en los Parámetros de Clasificación
	 * @param maestroInstructora
	 * 			objeto MaestroInstructora poblada
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void insertParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) throws Exception;

	/**
	 * Actualiza un registro en el Parametro de Clasificaci[on
	 * @param maestroInstructora
	 * 			objeto MaestroInstructora poblada
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) throws Exception;

	/**
	 * Elimina Logicamente un registro en los Parametros de Clasificacion
	 * @param maestroInstructora
	 * 			objeto MaestroInstructora poblada
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateRemoveParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario);

	/**
	 * Devuelve el último código del Maestro de Instructoras registrado para la empresa 
	 * @param maestroInstructora
	 * 			objeto MaestroInstructora poblado de la empresa comercializadora
	 */
	public String getMaxMaestroInstructora(MaestroInstructora maestroInstructora);

	/**
	 * Devuelve el último código de paràmetro de clasificacion registrado para la empresa 
	 * @param parametroClasificacion
	 * 			objeto MarametroClasificacion poblado de la empresa comercializadora
	 */
	public String getMaxParametroClasificacion(ParametroClasificacion parametroClasificacion);

	/**
	 * Obtiene la informacion de Tipos de Asistencia
	 * 
	 * @param parametroGenerico
	 * 			Objeto ParametroGenerico, considerar ingresar el atributo estado
	 * @return Lista de Parametros de Secuencia de Pedidos
	 */
	public List getTipoAsistencia(ParametroGenerico parametroGenerico);

	/**
	 * Obtiene la informacion de los Estado por Tipo de Nivel
	 * 
	 * @param parametroGenerico
	 * 			Objeto ParametroGenerico, considerar ingresar el atributo estado
	 * @return Lista de Parametros de Estado por Tipo Nivel
	 */
	public List getEstadoNivel(ParametroGenerico parametroGenerico);

    /**
     * Obtiene la informacion de un Control de Facturacion en base a su llave primaria.
     * 
     * @param criteria contiene la PK de la tabla 
     * @return Objeto de tipo PedidoControlFacturacion, poblado.
     */
	public ControlFacturacion getControlFacturacionById(Map criteria);
	
	
	/**
	 * Inserta Registro en tabla de Servicio de Capacitacion
	 * @param bean
	 * @param usuario
	 */
	public void insertServicioCapacitacion(ServicioCapacitacion bean, Usuario usuario);
	
	/**
	 * Elimina Registro en tabla de Servicio de Capacitacion
	 * @param bean
	 * @param usuario
	 */
	public void removeServicioCapacitacion(ServicioCapacitacion bean, Usuario usuario);
	
	public List getControlFacturacionByCriteria(Map criteria) ;

    public void updateCerrarCampanaControlFacturacion(ControlFacturacion controlFacturacion, Usuario usuario);

	public void updateDesactivarEquivalenciaMatriz(EquivalenciaMatrizServicio equivalenciaMatrizServicio, Usuario usuario);    
	/**
	 * Inserta un registro del parámetro de curso de capacitación
	 * @param parametroCursoCapacitacion
	 * 			objeto ParametroCursoCapacitacion con datos del pais y empresa comercializadora
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void insertParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion, Usuario usuario);

	/**
	 * Devuelve el nuevo correlativo a registrar 
	 * @param maestroInstructora
	 * 			objeto MaestroInstructora poblado de la empresa comercializadora
	 */
	public String getMaxParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion);

	/**
	 * Elimina Logicamente un registro en Parametro de Curso de Capacitación
	 * @param parametroCurso
	 * 			objeto ParametroCursoCapacitacion poblada
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateEstadoParametroCurso(ParametroCursoCapacitacion parametroCurso, Usuario usuario);

	/**
	 * Devuelve una Lista de Registros de Programas de Capacitaciòn
	 * @param getParametroCurso
	 * 			objeto ParametroCursoCapacitacion con datos del pais y empresa comercializadora
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public List getParametroCursoByCriteria(ParametroCursoCapacitacion parametroCursoCapacitacion);
	
	/**
	 * Devuelve nro actual de lote para interfaces Datamart
	 * @param criteria
	 * @return
	 */
	public String getNroLoteActualInterfazDatamart(Map criteria);
	
	/**
	 * Devuelve nro siguiente de lote para interfaces Datamart
	 * @param criteria
	 * @return
	 */
	public String getNroLoteSiguienteInterfazDatamart(Map criteria);
	
	
	/**
	 * Graba Nro de Lote correspondiente a la Interfaz Datamart
	 * @param criteria
	 * @return
	 */
	public void updateNroLoteInterfazDatamart(Map criteria);
	
	
	/**
	 * Elimina registros de la Tabla temporal de la Interfaz Datamart
	 * @param criteria
	 * @return
	 */
	public void deleteTemporalInterfazDatamart(Map criteria);
	
	
	/**
	 * Procedimiento previo para la ejecucion de la lista devuelta a mostrar de Objetivos de Asistencia 
	 * @param criteria
	 */
	public void executePrevioDevuelveObjetivoAsistencia(Map criteria);
	
	/**
	 * Devuelve lista de Objetivos de Asistencia 
	 * @param criteria
	 */
	public List getObjetivoAsistenciaByCriteria(Map criteria);
	
	/**
	 * Procedimiento que actualiza Objetivos de Asistencia 
	 * @param criteria
	 */
	public void executeActualizaObjetivoAsistencia(Map criteria);
	
	
	/**
	 * Devuelve lista de registros correspondientes a la Parametrizacion de Mensajes
	 * @param MensajeEducacion
	 * 			objeto MensajeEducacion con datos del Mensaje de Educacion
	 */
	public List getMensajeEducacion(MensajeEducacion mensajeEducacion);
	
	/**
	 * Devuelve registro correspondientes a la Parametrizacion de Mensajes
	 * @param MensajeEducacion
	 * 			objeto MensajeEducacion con datos del Mensaje de Educacion
	 */
	public MensajeEducacion getMensajeEducacionById(MensajeEducacion mensajeEducacion);
	
	
	/**
	 * Devuelve lista de Registros correspondientes al Estado de Capacitacion
	 * @param criteria
	 * @return
	 */
	public List getEstadoCapacitacionByCriteria(Map criteria);
	
	
	/**
	 * Elimina Logicamente un registro de Mensaje de Educación
	 * @param mensajeEducacion
	 * 			objeto Mensaje de Educación 
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateEstadoMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario)  throws Exception;
	
	
	/**
	 * Actualiza registro de Mensaje de Educación
	 * @param mensajeEducacion
	 * 			objeto Mensaje de Educación 
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario) throws Exception;
	
	
	/**
	 * Inserta registro de Mensaje de Educación
	 * @param mensajeEducacion
	 * 			objeto Mensaje de Educación 
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void insertMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario) throws Exception;
	
	
	/**
	 * Elimina Logicamente un registro de Empresa Comercializadora
	 * @param mensajeEducacion
	 * 			objeto Empresa Comercializadora 
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateEstadoEmpresaComercializadora(EmpresaComercializadora empresa, Usuario usuario)  throws Exception;
	
	
	/**
	 * Actualiza registro de Empresa Comercializadora
	 * @param mensajeEducacion
	 * 			objeto Empresa Comercializadora 
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateEmpresaComercializadora(EmpresaComercializadora empresa, Usuario usuario) throws Exception;
	
	
	/**
	 * Inserta registro de Empresa Comercializadora
	 * @param mensajeEducacion
	 * 			objeto Empresa Comercializadora 
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void insertEmpresaComercializadora(EmpresaComercializadora empresa, Usuario usuario) throws Exception;
	
	public List getParametroRegistroClasificacionByCriteria(ParametroRegistroClasificacion parametroRegistroClasificacion);
	public void updateParametroRegistroClasificacion(ParametroRegistroClasificacion parametroRegistroClasificacion, Usuario usuario);
	public void insertParametroRegistroClasificacion(ParametroRegistroClasificacion parametroRegistroClasificacion, Usuario usuario);
	/**
	 * Devuelve toda lista de Entidades , donde se hara un 
	 * mantenimiento multiple
	 * @author sbuchelli 
	 * @param 
	 */
	public List getListEntidadMultiple();

	/**
	 * Devuelve los registros de una Entidad selecciona ,segun filtro 
	 * @author sbuchelli 
	 * @param 
	 */
	
	public List getMultiEntidadByCriteria(EntidadGenerico entidadGenerico);
	/**
	 * Actualiza el estado a la entidad Generica 1:activo 9 :eliminado 
	 * @author sbuchelli
	 *  
	 * @param  entidadGenerico
	 * 			objeto entidad Generica
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 * 
	 */
	public void updateEstadoMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario);
	/**
	 * Devuelve los registros de la tabla EDU_TIPO_DESPA   
	 * @author sbuchelli 
	 * @param 
	 */
	public List getListTipoEntidad();
	/**
	 * Actualiza la entidad Generica campo descripcion  
	 * @author sbuchelli
	 *  
	 * @param  entidadGenerico
	 * 			objeto entidad Generica
	 * @param usuario
	 *			objeto Usuario para el registro de Asuditorìa 
	 * @return Cadena de error
	 */
	public String updateMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario);

	/**
	 * Inserta una entidad Generica  
	 * @author sbuchelli
	 *  
	 * @param  entidadGenerico
	 * 			objeto entidad Generica
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 * @return Cadena de error
	 */
	public String insertMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario);

	public EstadoCapacitacion getEstadoCapacitacion(EstadoCapacitacion estadoCapac);

	/**
	 * Devuelve la Campana respectiva de acuerdo a la Campaña Base y el numero de campañas 
	 * ingresados como parametros
	 * @param criteria
	 * @return
	 */
	public String getDevuelveCampanna(Map criteria);
	
	/**
	 * Obtiene lista con la informacion correspondiente a Cronograma de dictado
	 * 
	 * @param cronogramaDictado
	 * 			Objeto CronogramaDictado
	 * @return Lista de Cronogramas Dictados
	 */
	public List getCronogramaDictado(CronogramaDictado cronogramaDictado);
	
	/**
	 * Obtiene informacion correspondiente a Cronograma de dictado seleccionado
	 * 
	 * @param cronogramaDictado
	 * @return
	 */
	public CronogramaDictado getCronogramaDictadoById(CronogramaDictado cronogramaDictado);
	
	/**
	 * Elimina logicamente un Cronograma dictado
	 * @param cronogramaDictado
	 */
	public void updateEliminarCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario) throws Exception ;

	
	/**
	 * Actualiza Cronograma dictado
	 * @param cronogramaDictado
	 */
	public void updateCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario) throws Exception;
	
	/**
	 * Inserta Cronograma dictado
	 * @param cronogramaDictado
	 */
	public void insertCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario) throws Exception;
	
	/**
	 * Envia Cronograma Dictado al Proceso comercial
	 * @param criteria
	 * @throws Exception
	 */
	public void enviarCronogramaDictado(Map criteria) throws Exception;

	public EntidadGenerico getEntidadEstadoCapacitacion(EntidadGenerico entidadGenerico);

	public EntidadGenerico getEntidadFrecuenciaCali(EntidadGenerico entidadGenerico);
	
	/**
	 * Obtiene informacion correspondiente a Cronograma de dictado Zona seleccionado
	 * 
	 * @param cronogramaDictado
	 * @return
	 */
	public List getCronogramaDictadoZonaById(CronogramaDictadoZona cronogramaDictadoZona);

	
	/**
	 * Obtiene informacion correspondiente a los Cronogramas de dictado de una zona 
	 * 
	 * @param cronogramaDictado
	 * @return Lista Cronograma de Dictado
	 */
	
	public List getCronogramaDictadoByZona(CronogramaDictado cronogramaDictado);

	/**
	 * Obtiene informacion correspondiente a los Locales 
	 * 
	 * @param local
	 * @return Lista Local
	 */	
	public List getLocales(Local local);
	/**
	 * Actualiza el estado de registro del Local 
	 * 
	 * @param local
	 * @return 
	 */	
	public void updateEstadoLocal(Local local, Usuario usuario);
	/**
	 * Actualiza el Local 
	 * 
	 * @param local
	 * @return 
	 */	
	public void updateLocal(Local local, Usuario usuario);

	/**
	 * Inserta un nuevo Local 
	 * 
	 * @param local
	 * @return 
	 */	

	public void insertLocal(Local local, Usuario usuario);
	
	/**
	 * Procedimiento que efectua la generación del Reporte de Cronograma Dictado
	 * @param criteria
	 */
	public void executeGenerarReporteCronogramaDictado(Map criteria);

	/**
	 * Devuelve la lista de tipode ejecutivas
	 * 
	 * * @param 
	 * @return 
	 */	

	public List getTipoEjecutivas();

	public String getSiguienteCodigoLocal(Local local);
	
	public String getSiguienteCodigoSala(Sala sala);
	
	public List getSalas(Sala sala);

	public void deleteSala(Sala sala);

	public void insertSala(Sala sala, Usuario usuario);

	public void updateSala(Sala sala, Usuario usuario);
	
	/**
	 * Devuelve Bean de Parametro de Reporte
	 * @param parametro
	 * @return
	 */
	public ParametroReporte getParametroReporte(ParametroReporte parametro);
	
	/**
	 * Devuelve Lista de Bean de Parametro de Reporte
	 * @param parametro
	 * @return
	 */
	public List getParametroReporteLista(ParametroReporte parametro);
	
	/**
	 * Actualiza Parametro de Reporte
	 * @param parametro
	 * @param usuario
	 */
	public void updateParametroReporte(ParametroReporte parametro, Usuario usuario);
	
	/**
	 * Inserta en Parametro de Reporte
	 * @param parametro
	 * @param usuario
	 */
	public void insertParametroReporte(ParametroReporte parametro, Usuario usuario);
	
	/**
	 * Coloca inactivo el registro de Parametro de Reporte
	 * @param parametro
	 * @param usuario
	 */
	public void deleteParametroReporte(ParametroReporte parametro, Usuario usuario);

	public void executeGenerarResumenProgramadasPlanilla(Map params);

	
	public List getLocalByCriteria(Map map);

	public List getIndicadorSeleccion();
		
	List getLocalesByInstructora(Map params);

	public String getMensajeEducacionEquiv(MensajeEducacion mensaje);

	public void updateMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario);

	public void insertMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario);

	public void updateEstadoMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario);

	public String getDevuelveSiguienteNivel(Map criteria);

	public ConexionExterna getConexionExternaByCriteria(Map criteria);

	//ws para reporte cronograma de dictado
	public List getSubReporteCronogramaLocalWs(Map criteria);

	public List getSubReporteCronogramaCursoWs(Map criteria);

	public List getReporteCronogramaDictadoWs(Map criteria);

	public List getListaDocumentos(Map criteria);
	
	public List getCronogramaDictadoProgramadoByZona(CronogramaDictado cronogramaDictado);
	
	public List getMatrizClasificacionByCriteria(Map criteria);
	
	public List getCampanhasActivar(Map criteria);
	
	public List getCampanhasDesactivar(Map criteria);
	
	public List getCampanhasTodas(Map criteria);
	
	public void insertClasificacion(Map criteria, Usuario usuario);
	/**
	 * Retorna el listado de cronograms pro zona
	 * @author sbuchelli
	 *  @param map
	 * */
	public List getZonasCronogramaByRegion(Map map);

	/**
	 * @author sbuchelli
	 * Retorna el listado de clasificaciones 
	 * */
	public List getListClasificaciones(Map map);
}

package biz.belcorp.ssicc.dao.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.ConexionExterna;
import biz.belcorp.ssicc.dao.edu.model.ControlFacturacion;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictado;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictadoZona;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenerico;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenericoDefinicion;
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
import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoEDUGenericoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 */

public interface MantenimientoEDUGenericoDAO extends DAO{

	
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
	 * Obtiene informacion de los Tipos de costo de cursos
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
	 * Devuelve una Lista de Registros de Programas de Capacitaciòn
	 * @param getParametroCurso
	 * 			objeto ParametroCursoCapacitacion con datos del pais y empresa comercializadora
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public List getParametroCursoByCriteria(ParametroCursoCapacitacion parametroCursoCapacitacion);

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
	public void insertMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario);

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
	public void insertParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario);

	/**
	 * Actualiza un registro en el Parametro de Clasificaci[on
	 * @param maestroInstructora
	 * 			objeto MaestroInstructora poblada
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario);

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
	 * Inserta registro en la tabla de Servicio de Capacitacion
	 * @param bean
	 * @param usuario
	 */
	public void insertServicioCapacitacion(ServicioCapacitacion bean, Usuario usuario);
	
	/**
	 * Elimina registro en la tabla de Servicio de Capacitacion
	 * @param bean
	 * @param usuario
	 */
	public void removeServicioCapacitacion(ServicioCapacitacion bean, Usuario usuario);

	public List getControlFacturacionByCriteria(Map criteria);
	
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
	public void updateEstadoMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario);
	
	
	/**
	 * Actualiza registro de Mensaje de Educación
	 * @param mensajeEducacion
	 * 			objeto Mensaje de Educación 
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void updateMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario);
	
	/**
	 * Inserta registro de Mensaje de Educación
	 * @param mensajeEducacion
	 * 			objeto Mensaje de Educación 
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void insertMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario) ;
	
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
	
	
	/**
	 * Devuelve Siguiente Codigo de Mensaje de Educacion 
	 * @param criteria
	 * @return
	 */
	public String getSiguienteCodigoMensaje(Map criteria);
	
	public List getParametroRegistroClasificacionByCriteria(ParametroRegistroClasificacion parametroRegistroClasificacion); 
	public void updateParametroRegistroClasificacion(ParametroRegistroClasificacion parametroRegistroClasificacion, Usuario usuario);
	public void insertParametroRegistroClasificacion(ParametroRegistroClasificacion parametroRegistroClasificacion, Usuario usuario);
	/**
	 * Devuelve lista de Entidades , donde se hara un 
	 * mantenimiento multiple
	 * @author sbuchelli 
	 * @param 
	 */
	public List getListEntidadMultiple();
	
	public List getListTipoEntidad();

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
	 * Obtiene lista con la informacion correspondiente a Cronograma de dictado
	 * @param criteria
	 * @return
	 */
	public List getCronogramaDictadoEnvioByCriteria(Map criteria);
	
	/**
	 * Elimina logicamente un Cronograma dictado
	 * @param cronogramaDictado
	 */
	public void updateEliminarCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario);
	
	/**
	 * Obtiene informacion correspondiente a Cronograma de dictado seleccionado
	 * 
	 * @param cronogramaDictado
	 * @return
	 */
	public CronogramaDictado getCronogramaDictadoById(CronogramaDictado cronogramaDictado);
	
	/**
	 * Actualiza Cronograma dictado
	 * @param cronogramaDictado
	 */
	public void updateCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario);
	
	/**
	 * Inserta Cronograma dictado
	 * @param cronogramaDictado
	 */
	public void insertCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario);
	
	/**
	 * Inserta Cronograma dictado Zona
	 * @param cronogramaDictado
	 */
	public void insertCronogramaDictadoZona(CronogramaDictadoZona cronogramaDictadoZona, Usuario usuario);
	
	/**
	 * Elimina Cronograma dictado Zona
	 * @param cronogramaDictado
	 */
	public void deleteCronogramaDictadoZona(CronogramaDictadoZona cronogramaDictadoZona, Usuario usuario);
	
	
	/**
	 * Devuelve ID sgte para insertar en el Cronograma de Dictado
	 * @return
	 */
	public Long getDevuelveIdSgteCronogramaDictado();

	public EntidadGenerico getEntidadEstadoCapacitacion(EntidadGenerico entidadGenerico);

	public EntidadGenerico getEntidadFrecuenciaCali(EntidadGenerico entidadGenerico);
	
	/**
	 * Devuelve Lista de Locales de Educacion
	 * @param criteria
	 * @return
	 */
	public List getLocalByCriteria(Map criteria);
	
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
	 * Procedimiento que efectua la generación del Reporte de Cronograma Dictado
	 * @param criteria
	 */
	public void executeGenerarReporteCronogramaDictado(Map criteria);

	public List getLocales(Local local);

	public void updateEstadoLocal(Local local, Usuario usuario);

	public void updateLocal(Local local, Usuario usuario);

	public void insertLocal(Local local, Usuario usuario);

	public List getTipoEjecutivas();

	public String getSiguienteCodigoLocal(Local local);

	public List getSalas(Sala sala);

	public String getSiguienteCodigoSala(Sala sala);

	public void deleteSala(Sala sala);

	public void insertSala(Sala sala, Usuario usuario);

	public void updateSala(Sala sala, Usuario usuario);
	
	/**
	 * Obtiene Conexion externa en base al Codigo de Pais
	 * @param criteria
	 * @return
	 */
	public ConexionExterna getConexionExternaByCriteria(Map criteria);

	/**
	 * Obtiene Parametros de Conexion externa 
	 * @param params
	 * @return
	 */
	public List getParamConexionExterna(Map params);
	
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

	public String getLongitudCodCliente(String codigoPais);

	public void executeGenerarResumenProgramadasPlanilla(Map params);

	public String getLongitudCodInstructora(String codigoPais);
	
	/**
	 * Inserta Registro en la tabla de Equivalencia de clasificacion
	 * @param parametroClasificacion
	 * @param usuario
	 */
	public void insertParametroEquivalenciaClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario);
	
	/**
	 * Actualiza Registro en la tabla de Equivalencia de clasificacion
	 * @param parametroClasificacion
	 * @param usuario
	 */
	public void updateParametroEquivalenciaClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario);
	
	/**
	 * Verifca si existe el registro en la tabla de Equivalencia de Clasificaciones
	 * @param parametroClasificacion
	 * @return
	 */
	public Integer getExisteParametroEquivalenciaClasificacion(ParametroClasificacion parametroClasificacion);

        public String getNumPeriodo(Map criteria);

	public List getIndicadorSeleccion();

	public List getLocalesByInstructora(Map params);
	
	public List getListaTipoEntidadGenerico(Map params);

	public List getCamposEntidadGenerico(String nombreEntidad);
	
	public EntidadGenericoDefinicion getDefinicionEntidadGenerico(String nombreEntidad);

	public String getMensajeEducacionEquiv(MensajeEducacion mensaje);

	public void updateMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario);
	
	public void insertMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario);

	public void updateEstadoMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario);

	public List getCodigoEquivalenteClasificacion(String codigoTipoClasEquiv);

	public String getDevuelveSiguienteNivel(Map criteria);
	
	public List getSubReporteCronogramaLocalWs(Map criteria);

	public List getSubReporteCronogramaCursoWs(Map criteria);

	public List getReporteCronogramaDictadoWs(Map criteria);
	
	public List getListaDocumentos(Map criteria);
	
	public List getCronogramaDictadoProgramadoByZona(CronogramaDictado cronogramaDictado) ;

	public List getTipoAsistenciaCapacitadas(ParametroGenerico parametroGenerico);
	
	public List getMatrizClasificacionByCriteria(Map criteria);
	
	public List getCampanhasCriteria(Map criteria);

	public List getCampanhasActivar(Map criteria);
	
	public List getCampanhasDesactivar(Map criteria);
	
	public List getCampanhasTodas(Map criteria);
	
	public void insertClasificacion(Map criteria, Usuario usuario) ;
	
	public void desactivarClasificaciones(Map criteria, Usuario usuario);
						
	public void activarClasificaciones(Map criteria, Usuario usuario) ;
	
	public void insertNuevaClasificacion(Map criteria, Usuario usuario);
	/**
	 * Retorna el listado de cronograms pro zona
	 * @author sbuchelli
	 *  @param map
	 * */
	public List getZonasCronogramaByRegion(Map map);

	/**
	 * Retorna el listado de clasificaciones 
	 * @author sbuchelli
	 * @param map
	 * */
	public List getListClasificaciones(Map map);
}
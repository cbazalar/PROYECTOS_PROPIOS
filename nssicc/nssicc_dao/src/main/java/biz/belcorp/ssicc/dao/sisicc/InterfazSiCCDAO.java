/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.sisicc.model.EstimadoProductos;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazCOMRecepcionarActualizaCodigoProveedor;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosDetalle;

/**
 * <p>
 * <a href="InterfazSiCCDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface InterfazSiCCDAO extends DAO {

	/**
	 * 
	 */
	public void deleteInterfazLLIRecepcionarActualizaEstimadosYobel();

	public void deleteInterfazMovimientoCliente();

	/**
	 * Borra los registros de la tabla temporal.
	 * 
	 * @author Marco Silva
	 */
	public void deleteInterfazOCRConsolidadoOCSCabecera();

	public void deleteInterfazOCRRecepcionarActualizacionDatos();

	public void deleteInterfazOCRRecepcionarDWHOCR();

	public void deleteInterfazOCRRecepcionarFichaInscripcionPrivilege();

	public void deleteInterfazOCRRecepcionarPagare();

	public void deleteInterfazOCRRecepcionarSolicitudCredito();

	public void deleteInterfazOCRRecepcionarSolicitudPremiosPrivilegeCabecera();

	public void deleteInterfazOCRRecepcionarTablaControlMultiformato();

	public void deleteInterfazPRIRecepcionarCalificacion();

	public void deleteInterfazPRIRecepcionarClientes();

	public void deleteInterfazRECEnviarTransferenciaBoletasRecojo();

	public void deleteInterfazRECRecepcionarBoletasRecojoCabecera();

	public void deleteInterfazRECRecepcionarBoletasRecojoDetalle();

	/**
	 * @param queryParams
	 */
	public void deleteInterfazRETProductosReclamados(Map queryParams);

	public void deleteInterfazRETRecepcionarVentasRetailCab();

	public void deleteInterfazRETRecepcionarVentasRetailDet();

	public void deleteInterfazRETRecepcionarVentasRetailDetDet();

	public void deleteParamInterfaz(Map params);

	public void deleteParamTmpInterfaz(Map params);

	public void deleteTablaControlIVR();

	public void deleteVentaBaseConsultoras();

	public void executeActualizaNumeroLoteMica(Map criteria);

	public void executeCambioCodigoVenta(Map parametros);

	public String executeExportarMatrizLBEL(Map parametros);

	public void executeGenerarBoletasRecojo(Map params);

	public void executeGenerarXMLBoletasRecojo(Map params);
	
	public void executeGenerarXMLBoletasRecojoORA(Map params);

	public void executeInterfazBELEnviarDireccionClientes(Map criteria);

	public void executeInterfazBELEnviarFacturasCabecera(Map criteria);

	public void executeInterfazBELEnviarFacturasDetalle(Map criteria);

	public void executeInterfazBELEnviarPorcentajesReferencia(Map criteria);

	public void executeInterfazBELEnviarUbicacionesGeograficas(Map criteria);

	public void executeInterfazBELEnviarUnidadesAtendidas(Map criteria);

	public void executeInterfazCOBCargarDatosInfocorp(Map params);

	public void executeInterfazCOBEnviarDatosInfocorp(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazCOMEnviarArchivoAdam(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazCOMEnviarArchivoEjecutivaNuevaAspirante(
			Map params);

	/**
	 * @param params
	 */
	public void executeInterfazCOMEnviarCargaMasivaEjecutivaNueva(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazCOMEnviarFicheroPagoComisionEjecutiva(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazDATDEbelistaDatosAdic(Map params);

	/**
	 * Interfaz DATAMART para Enviar Objetivos Capacitación
	 * 
	 * @param queryParams
	 */

	public void executeInterfazDATEnviarArchivoControl(Map queryParams);

	public void executeInterfazDATEnviarCanalesVenta(Map params);

	/**
	 * Interfaz DATAMART para Enviar Capacitadas de Aptas
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarCapacitacionApta(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Capacitadas de Aptas (Reenvio)
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarCapacitacionAptaReenvio(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Capacitacion Cliente
	 * 
	 * @param queryParams
	 */
	public void executeInterfazDATEnviarCapacitacionCliente(Map queryParams);

	public void executeInterfazDATEnviarCapacitacionClienteReenvio(
			Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Capacitacion Cursos Clientes
	 * 
	 * @param queryParams
	 */
	public void executeInterfazDATEnviarCapacitacionCursoCliente(Map queryParams);

	public void executeInterfazDATEnviarCapacitacionCursoClienteReenvio(
			Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Capacitacion Programadas
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarCapacitacionProgramada(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Capacitacion Programadas (Reenvio)
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarCapacitacionProgramadaReenvio(
			Map queryParams);

	public void executeInterfazDATEnviarCDRConsultora(Map params);

	/**
	 * Interfaz DATAMART para Enviar Clientes x Geografia
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarClienteGeografia(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Clientes x Geografia Reenvio
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarClienteGeografiaReenvio(Map queryParams);

	/**
	 * Interfaz de Envio de Controles
	 * 
	 * @param queryParams
	 */
	public void executeInterfazDATEnviarControl(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Cursos x Ambito
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarCursoAmbito(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Cursos
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarCursos(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Cursos dictados
	 * 
	 * @param queryParams
	 */
	public void executeInterfazDATEnviarCursosDictados(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Cursos dictados Historico
	 * 
	 * @param queryParams
	 */
	public void executeInterfazDATEnviarCursosDictadosHistoria(Map queryParams);

	public void executeInterfazDATEnviarCursosDictadosHistoriaReenvio(
			Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Cursos dictados (Reenvio)
	 * 
	 * @param queryParams
	 */
	public void executeInterfazDATEnviarCursosDictadosReenvio(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Empresas
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarEmpresa(Map queryParams);

	public void executeInterfazDATEnviarEstadosConsultoraCierrePeriodo(
			Map params);

	public void executeInterfazDATEnviarHistoricoPremiosDespachadosConcurso(
			Map params);

	public void executeInterfazDATEnviarImpuestosProducto(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazDATEnviarIncentivosTiempo(Map params);

	/**
	 * Interfaz DATAMART para Enviar Instructoras x Geografia
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarInstructoraGeografia(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Instructoras
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarInstructoras(Map queryParams);

	public void executeInterfazDATEnviarMaestroConcursos(Map params);

	public void executeInterfazDATEnviarMaestroEbelistasSessionExpert(Map params);

	public void executeInterfazDATEnviarMaestroEstadosCobranza(Map params);

	public void executeInterfazDATEnviarMaestroMarcasProducto(Map params);

	public void executeInterfazDATEnviarMaestroNivelesConcurso(Map params);

	public void executeInterfazDATEnviarMaestroProductos(Map params);

	public void executeInterfazDATEnviarMaestroProductosSessionExpert(Map params);

	public void executeInterfazDATEnviarMaestroTiposDocumentoPedido(Map params);

	public void executeInterfazDATEnviarMaestroZonas(Map params);

	public void executeInterfazDATEnviarObjetivoPaisAnual(Map queryParams);

	public void executeInterfazDATEnviarObjetivoPaisPeriodo(Map queryParams);

	public void executeInterfazDATEnviarObjetivoRegionAnual(Map queryParams);

	public void executeInterfazDATEnviarObjetivoRegionPeriodo(Map queryParams);

	public void executeInterfazDATEnviarObjetivoZonaAnual(Map queryParams);

	public void executeInterfazDATEnviarObjetivoZonaPeriodo(Map queryParams);

	/**
	 * Interfaz DATAMART para Enviar Paises
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarPais(Map queryParams);

	public void executeInterfazDATEnviarParametriaOpcionesPremioConcurso(
			Map params);

	public void executeInterfazDATEnviarParticipantesConcurso(Map params);

	public void executeInterfazDATEnviarPremiosAsignadosTotal(Map params);

	public void executeInterfazDATEnviarPremiosSeleccionados(Map params);

	public void executeInterfazDATEnviarPremiosSolicitados(Map params);

	public void executeInterfazDATEnviarProductosConcurso(Map params);

	/**
	 * Interfaz DATAMART para Enviar Programas
	 * 
	 * @param params
	 * @return
	 */
	public void executeInterfazDATEnviarPrograma(Map queryParams);

	public void executeInterfazDATEnviarPuntajePeriodoConcursoVigente(Map params);

	/**
	 * Proceso de envio Ranking Lideres
	 * 
	 * @param map
	 */
	public void executeInterfazDATEnviarRankingLideres(Map params);

	public void executeInterfazDATEnviarRecomendantesRecomendadasConcurso(
			Map params);

	/**
	 * Proceso de envio Responsable Seccion
	 * 
	 * @param map
	 */
	public void executeInterfazDATEnviarResponsablesSeccion(Map params);

	public void executeInterfazDATEnviarResultadoConcursoLideres(Map params);

	public void executeInterfazDATEnviarSaldoPromedioPeriodo(Map params);

	/**
	 * Ejecuta el proceso de enviartabla clientes
	 * 
	 * @param params
	 */
	public void executeInterfazDATEnviarTablaClientes(Map params);

	/**
	 * Proceso de envio Tipo Clasificacion Programa
	 * 
	 * @param map
	 */
	public void executeInterfazDATEnviarTipoClasificacionPrograma(Map params);

	public void executeInterfazDATEnviarUnidadesAdministrativasConcurso(
			Map params);

	public void executeInterfazDATEnviarVentaSeccionPeriodo(Map params);

	public void executeInterfazECOEnviarPuntajesXCampanya(Map params);

	public void executeInterfazECOEnviarVentaBaseConsultoras(Map criteria);

	public void executeInterfazEVIEnviarCabecerasPedidosDigitados(Map params);

	public void executeInterfazEVIEnviarHistoricoConsultoras(Map params);

	public void executeInterfazEVIEnviarResultadosCobranza(Map params);

	public void executeInterfazEVIEnviarResultadosFacturacion(Map params);

	public void executeInterfazEVIEnviarSaldosConsultora(Map params);

	public void executeInterfazIVREnviarBasesIncumplidas(Map params);

	public void executeInterfazIVREnviarConcursos(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazIVREnviarMaestroClientes(Map params);

	public void executeInterfazIVREnviarNiveles(Map params);

	public void executeInterfazIVREnviarPremios(Map params);

	public void executeInterfazIVREnviarTablaClientes(Map params);

	public void executeInterfazIVREnviarTablaConcursos(Map params);

	public void executeInterfazIVREnviarTablaControl(Map params);

	public void executeInterfazIVREnviarTablaMotivosRechazo(Map params);

	public void executeInterfazIVREnviarTablaPostVenta(Map params);

	public void executeInterfazLLIEnviarVentaPeriodo(Map params);

	/**
	 * Proceso que realiza la equivalencia
	 * 
	 * @param params
	 */
	public void executeInterfazLLIEnviarVentaPeriodoEquivalencia(Map params);

	/**
	 * Enviar información de venta real diaria
	 * 
	 * @param Pais
	 *            , Periodo, fecha de inicio y fin de facturación
	 */
	public void executeInterfazLLIEnviarVentaRealDiariaAcumulada(Map queryParams);

	/**
	 * Proceso que realiza la equivalencia
	 * 
	 * @param queryParams
	 */
	public void executeInterfazLLIEnviarVentaRealDiariaAcumuladaEquivalencia(
			Map queryParams);

	public void executeInterfazMYEEnviarArchivoPremios(Map params);

	public void executeInterfazMYEEnviarCabeceraPedidos(Map params);

	public void executeInterfazMYEEnviarFaltantesAnunciados(Map params);

	/**
	 * Ejecuta el proceso de envio historico Pegs
	 * 
	 * @param params
	 */
	public void executeInterfazMYEEnviarHistoricoPegs(Map params);

	public void executeInterfazMYEEnviarInterfacesDiarias(Map params);

	/**
	 * @return
	 */

	public void executeInterfazMYEEnviarTipoProductoCatalogo(Map params);

	public void executeInterfazMYEEnviarVentaDirectaCabecera(Map queryParams);

	public void executeInterfazMYEEnviarVentaDirectaDetalle(Map queryParams);

	public void executeInterfazMYEMovimientosCuentaCorriente(Map params);

	public void executeInterfazOCREnviarMaestroClientesCorporativo(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazOCREnviarMatriz(Map params);

	public void executeInterfazOCREnviarMatrizFacturacionCorporativo(Map params);

	public void executeInterfazOCREnviarOCSCabecera(Map criteria);

	public void executeInterfazOCREnviarOCSDetalle(Map criteria);

	public void executeInterfazOCREnviarRangoPeriodosVigenteCorporativo(
			Map params);

	public void executeInterfazOCREnviarReclamosPostventa(Map params);

	public void executeInterfazOCREnviarRegionesCorporativo(Map params);

	public void executeInterfazOCREnviarZonasCorporativo(Map params);

	/**
	 * Procesa los consolidados de actualizacion de datos
	 * 
	 * @param map
	 */
	public void executeInterfazOCRProcesarConsolidadoActualizacionDatos(Map map);

	/**
	 * Procesa los consolidados cabecera de los archivos de texto de OCS
	 * 
	 * @param params
	 * 
	 * @author Marco Silva
	 */
	public void executeInterfazOCRProcesarConsolidadoOCS(Map map);

	public void executeInterfazOCRProcesarConsolidadoOCSCabeceraCorporativo(
			Map map);

	public void executeInterfazOCRProcesarConsolidadoOCSDetalleCorporativo(
			Map map);

	public void executeInterfazOCRProcesarConsolidadoPostventaCabecera(Map map);

	public void executeInterfazOCRProcesarConsolidadoPostventaDetalle(Map map);

	public void executeInterfazOCRProcesarConsolidadoPostventaDetalleOCR(
			Map params);

	public void executeInterfazOCRProcesarConsolidadoSolicitudCredito(Map map);

	public void executeInterfazOCRRecepcionarActualizacionDatos(Map map);

	public void executeInterfazOCRRecepcionarArriboZonas(Map map);

	public void executeInterfazOCRRecepcionarConsolidadoOCSCabecera(Map criteria);

	public void executeInterfazOCRRecepcionarConsolidadoOCSCabeceraCorporativo(
			Map map);

	public void executeInterfazOCRRecepcionarConsolidadoOCSDetalle(Map criteria);

	public void executeInterfazOCRRecepcionarConsolidadoOCSDetalleCorporativo(
			Map map);

	public void executeInterfazOCRRecepcionarDWHOCR(Map parametros);

	public void executeInterfazOCRRecepcionarFichaInscripcionPrivilege(
			Map parametros);

	public void executeInterfazOCRRecepcionarPagare(Map map);

	public void executeInterfazOCRRecepcionarSeguimientoPedido(Map map);

	public void executeInterfazOCRRecepcionarServiciosPostventasCabec(
			Map parametros);

	public void executeInterfazOCRRecepcionarServiciosPostventasDetal(
			Map parametros);

	public void executeInterfazOCRRecepcionarSolicitudCredito(Map map);

	public void executeInterfazOCRRecepcionarSolicitudPremiosPrivilegeCabecera(
			Map map);

	public void executeInterfazOCRRecepcionarSolicitudPremiosPrivilegeDetalle(
			Map queryParams);

	public void executeInterfazOCRRecepcionarTablaControlMultiformato(Map map);

	public void executeInterfazPRIEnviarCalificacion(Map criteria);

	public void executeInterfazRECEnviarBoletasRecojoCabecera(Map params);
	
	public void executeInterfazIMPGenerarBanDominicana(Map params);

	public void executeInterfazRECEnviarBoletasRecojoControl(Map params);

	public void executeInterfazRECEnviarBoletasRecojoDetalle(Map params);

	public void executeInterfazRECEnviarTransferenciaBoletasRecojo(Map params);

	public void executeInterfazRECEnviarUnidadesAlmacenVirtual(Map params);

	public void executeInterfazRECProductosReclamados(Map criteria);

	public void executeInterfazRECProductosReclamadosCab(Map criteria);

	public void executeInterfazRECProductosReclamadosDet(Map criteria);

	public void executeInterfazRECProductosReclamadosDetDet(Map criteria);

	public void executeInterfazRECRecepcionarBoletasRecojoCabecera(Map map);

	public void executeInterfazRECRecepcionarBoletasRecojoDetalle(Map map);

	public void executeInterfazRETEnviarComisiones(Map criteria);

	public void executeInterfazRETEnviarCompleFacturaVentaDirecta(
			Map queryParams);

	public void executeInterfazRETEnviarFacturaVentaDirecta(Map queryParams);
	
	public void executeInterfazIMPProgramaPunto(Map params);

	/**
	 * Interfaz de Envio de Informacion de Venta
	 * 
	 * @param queryParams
	 */
	public void executeInterfazRETEnviarInformacionVenta(Map queryParams);

	public void executeInterfazRETEnviarMatrizCampanya(Map criteria);

	public void executeInterfazRETEnviarMatrizPuntajesCalypso(Map criteria);

	public void executeInterfazRETEnviarSolicitudesMonetariasCabecera(Map params);

	public void executeInterfazRETEnviarSolicitudesMonetariasPosicion(Map params);

	/**
	 * JPJC PER-SiCC-2013-0692
	 * @param params
	 */
	public void executeInterfazPEREnviarArchivoPDT(Map params);

	public void executeInterfazREUEnviarConsultoras(Map params);

	public void executeInterfazREUEnviarMatrizCampanya(Map criteria);

	public void executeInterfazSIAPEnviarAnalisisPrecios(Map params);

	public void executeInterfazSICEnviarInscritas(Map queryParams);

	public void executeOCRRecepcionarConsolidadoOCSCargaSTO(Map queryParams);

	/**
	 * Actualiza las unidades devueltas en SICC
	 * 
	 * @param params
	 * @return
	 */
	public void executeProcesoRECActualizaUnidadesDevueltas(Map params);

	public void executeTruncateGenerarXMLBoletasRecojo();

	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de
	 * busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getAccesoByCanal(Map params);

	/**
	 * Obtiene un listado de todos los accesos en base al canal y el codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos AccesoCanal, poblados.
	 */
	public List getAccesosByCanalByCodigoISO(String codigo);

	/**
	 * Obtiene el acceso en base al codigo ISO y codigo de Acceso.
	 * 
	 * @param criteria
	 *            filtro de parametros
	 * 
	 * @return Acceso
	 */

	public Base getAccesosByCodigo(Map criteria);

	/**
	 * Obtiene un listado de todos los accesos disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getAccesosByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los accesos disponibles en base al canal y el
	 * codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos AccesoCanal, poblados.
	 */

	public List getAccesosTodosByCanalByCodigoISO(String codigo);

	/**
	 * Obtiene la lista de actividades disponibles, en base al codigo ISO y al
	 * codigo de marca.
	 * 
	 * @param codigoIsoIdioma
	 *            Codigo ISO del idioma.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getActividades(String codigoIsoIdioma, String codigoMarca);

	/**
	 * Obtiene la lista de actividades disponibles, en base al codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO.
	 * 
	 * @return Lista de objetos Actividad, poblados.
	 */
	public List getActividadesByCodigoISO(String codigo);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getAlmacenByCodigo(Map criteria);

	/**
	 * Obtiene un listado de todos los almacenes disponibles en base al codigo
	 * ISO y el País.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getAlmacenesByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los almacenes disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getAlmacenesByCodigoISOPais(Map params);

	/**
	 * Obtiene un List de los Bancos segun un determinado Pais
	 * 
	 * @param criteria
	 *            Map con el codigo del Pais
	 * @return Lista de objetos Base poblados.
	 */
	public List getBancosByPais(Map criteria);

	public List getBoletaRecojoCabeceraList(Map params);

	public List getBoletaRecojoLineaList(Map params);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getCanalByCodigo(Map criteria);

	/**
	 * Obtiene un listado de todos los canales disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getCanalesByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los canales disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getCanalesRolByCodigoISO(String codigo);

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public String getCantidadClientesByPK(Map criteria);

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public String getCantidadLibretaAhorrobyDNI(String dni);

	/**
	 * Obtiene la cantidad de Periodos por PK.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public String getCantidadPeriodosByPK(Map criteria);

	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de
	 * busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getCentroDistribucionByPais(Map params);

	/**
	 * Obtiene la lista de Clasificaciones en base a Tipo Cliente, Sub-Tipo
	 * Cliente, Tipo Clasificaciones
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getClasificacionesByCriteria(Map params);

	public List getClasificacionesByCriteriaMultiple(Map criteria);

	public List getClasificacionesByCriteriaMultipleOID(Map criteria);
	
	public List getClasificacionesByCriteriaMultipleCodigo(Map criteria);
	

	/**
	 * Obtiene los datos de un cliente en base a su codigo.
	 * 
	 * @param codigo
	 *            Codigo del cliente.
	 * 
	 * @return Objeto de tipo Cliente, poblado
	 */
	public Cliente getClienteSICCByCodigo(Map criteria);

	/**
	 * Obtiene la lista de clientes por actualizar UA, en base a un criterio de
	 * busqueda.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return Lista de objetos de tipo ClientePendiente, poblados.
	 */
	public List getClientesPendientesActualizarUA(Map criteria);

	/**
	 * Obtiene la lista de clientes por actualizar UA, en base a un criterio de
	 * busqueda.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return Lista de objetos de tipo ClientePendiente, poblados.
	 */
	public List getClientesPendientesActualizarXLSUA(Map criteria);

	/**
	 * Obtiene una lista de los clientes que cumplan el los criterios
	 * establecidos.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return Lista de objetos de tipo Cliente, poblados.
	 */
	public List getClientesSICCByCriteria(Map criteria);

	public String getCodigoCompania(Map params);

	public String getCodigoDocumento(Map params);

	/**
	 * Devuelve Codigo de Sociedad
	 * 
	 * @param params
	 *            Parametros de entrada
	 * @return Codigo de Sociedad
	 */
	public String getCodigoSociedadbyCodigoPlanilla(Map params);

	/**
	 * Devuelve Codigo de Sociedad
	 * 
	 * @param params
	 *            Parametros de entrada
	 * @return Codigo de Sociedad
	 */
	public String getCodigoSociedadbyCuentaCorrienteBancaria(Map params);

	/**
	 * Obtiene lista de comisiones
	 * 
	 * @return lista de objetos de Comision
	 */
	public List getComision();

	/**
	 * 
	 * @param criteria
	 */
	public List getComprobantesByPaisBancoCuentaCorriente(Map criteria);

	/**
	 * Obtiene la lista de todos los concursos que existen
	 * 
	 * @param codigo
	 *            Codigo de Consultora
	 * @return lista de concursos
	 */
	public List getConcursos(Map criteria);

	public List getConcursosByPaisMarcaCanal(Map params);

	/**
	 * @param params
	 * @return Lista de Concursos Vigentes y No vigentes
	 */
	public List getConcursosByPaisMarcaCanalAnhioTodos(Map params);

	public List getConcursosByPaisMarcaCanalDellate(Map params);

	/**
	 * @param criteria
	 * @return
	 */
	public List getConcursosByPaisMarcaCanalDetallePremiosElec(Map criteria);

	/**
	 * Obtiene la lista de Tipos de Concursos cuyo rango de duración incluya al
	 * periodo seleccionado
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getConcursosByPaisMarcaCanalPeriodo(Map params);

	public List getConsolidadoTransferenciaBoletasRecojoConAnulacion(Map params);

	public List getConsolidadoTransferenciaBoletasRecojoSinAnulacion(Map params);

	/**
	 * Consulta de todas los productos reclamados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConsultaRECProductosList(Map criteria);

	/**
	 * Devuelve Codigo de cliente
	 * 
	 * @param params
	 * @return
	 */
	public String getConsultoraByNumeroDocumento(Map params);

	public String getConsultoraExistenteByCriteria(Map criteria);

	/**
	 * Este metodo realiza la busqueda de Consultoras
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConsultorasByCriteria(Map criteria);

	/**
	 * Obtiene el contador mayor a 0 si la campaña ingresada a sido cerrada
	 * 
	 * @param codigoPeriodo
	 * @return
	 */
	public Integer getContCierreCampaByCodigoPeriodo(String codigoPeriodo);

	/**
	 * Obtiene un valor numerico > 1, si existe el territorio ingresado para un
	 * cliente especifico
	 * 
	 * @param criteria
	 *            Parametros de busqueda.
	 * 
	 * @return Valor entero.
	 */
	public Integer getCountTerritorioByZona(Map criteria);

	/**
	 * Obtiene un valor numerico > 1, si existe la zona ingresada, para un
	 * cliente especifico
	 * 
	 * @param criteria
	 *            Parametros de busqueda.
	 * 
	 * @return Valor entero.
	 */
	public Integer getCountTerritoriosByCriteria(Map criteria);

	/**
	 * Obtiene un valor numerico > 1, si el territorio ingresado esta en el
	 * distrito del ubigeo
	 * 
	 * @param criteria
	 *            Parametros de busqueda.
	 * 
	 * @return Valor entero.
	 */
	public Integer getCountUbigeoByTerritorio(Map criteria);

	/**
	 * Obtiene un valor numerico > 1, si existe el territorio ingresado, para un
	 * cliente especifico
	 * 
	 * @param criteria
	 *            Parametros de busqueda.
	 * 
	 * @return Valor entero.
	 */
	public Integer[] getCountZonasByCriteria(Map criteria);

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public List getCuentasCorrientesByCodigoPais(String codigo);

	/**
	 * Obtiene un listado de las Cuentas Corrientes que pueden ser filtradas por
	 * Pais, Banco y/o Sociedad
	 * 
	 * @param criteria
	 *            Map con los codigos de Pais, Sociedad o Banco
	 * @return Lista de objetos Base poblados.
	 */
	public List getCuentasCorrientesByPaisBancoSociedad(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCuentasCorrientesPorPaisSociedad(Map criteria);

	/**
	 * Obtiene Datos de un proceso Batch
	 * 
	 * @param params
	 * @return
	 */
	public Map getDatosProcesoBatchSICC(String codigoProcesoBatch);

	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de
	 * busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getDescripcionByProducto(Map params);

	/**
	 * 
	 * @param idCabecera
	 * @return
	 */
	public String getDescuentoEspecifico(int idCabecera);

	/**
	 * 
	 * @param codigoPeriodo
	 * @param codigoTipoCliente
	 * @param codigoSubtipoCliente
	 * @param codigoTipoOferta
	 * @param codigoNegocio
	 * @param codigoUnidadNegocio
	 * @param codigoMarcaProducto
	 * @param exclusionTipoOferta
	 * @return
	 */
	public String getDescuentoVarios(String codigoPeriodo,
			String codigoTipoCliente, String codigoSubtipoCliente,
			String codigoTipoOferta, String codigoNegocio,
			String codigoUnidadNegocio, String codigoMarcaProducto,
			String exclusionTipoOferta);

	/**
	 * Obtiene la lista de Zonas por Region
	 * 
	 * @param params
	 * @return
	 */
	public List getDesZonasByPaisMarcaCanalRegion(Map params);

	public List getDetalleOfertaByPeriodoCUV(Map params);

	/**
	 * Devuelve id de pais
	 * 
	 * @param codigo
	 *            Codigo de Pais
	 * @return
	 */
	public Long getDevuelveIdPais(String codigo);

	/**
	 * Devuelve la cantidad de equivalencias registradas
	 * 
	 * @return
	 */
	public Integer getEquivalenciasCount();

	/**
	 * @param codigoPais
	 * @return
	 */
	public List getEstadosBoletasRecojo(String codigoPais);

	/**
	 * Obtiene un listado de todos los estados de las mercaderias disponibles en
	 * base al codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getEstadosMercaderiaByCodigoISO(String codigo);

	public List getEstadosPedidos();

	/**
	 * Obtiene un listado de todos los canales disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getEstadosRolByCodigoISO(String codigo);

	public String getExisteComisionComercializacion(Map criteria);

	public String getExisteComisionIngreso(Map criteria);

	public String getExisteComisionRecuperacion(Map criteria);
	
	
	/**
	 * Verifica si existe Comision de Recuperacion para Ejecutivas
	 * @param criteria
	 * @return
	 */
	public Integer getExisteComisionRecuperacionEjecutiva(Map criteria);
	
	/**
	 * Verifica si existe Comision de Recuperacion para Gerente de Region
	 * @param criteria
	 * @return
	 */
	public Integer getExisteComisionRecuperacionRegion(Map criteria);
	
	/**
	 * Verifica si existe Comision de Recuperacion para Gerente de Zona
	 * @param criteria
	 * @return
	 */
	public Integer getExisteComisionRecuperacionZona(Map criteria);

	/**
	 * Verifica si existe Consultura
	 * 
	 * @param params
	 *            Parametros de entrada
	 * @return
	 */
	public Integer getExisteConsultora(Map params);

	public Integer getExisteConsultoraByCodigoPais(Map params);

	/**
	 * Verifica si existe Cuenta Corriente bancaria
	 * 
	 * @param params
	 *            Parametros de entrada
	 * @return
	 */
	public Integer getExisteCuentaCorrienteBancaria(Map params);

	public String getExisteProyeccionFaltanteDia(Map criteria);

	/**
	 * Verifica si existe Tipo de Transaccion Bancaria
	 * 
	 * @param params
	 *            Parametros de entrada
	 * @return
	 */
	public Integer getExisteTipoTransaccionBancaria(Map params);

	/**
	 * Obtiene la lista de periodos de un determinado país y canal
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getFacturacion(Map params);

	public String getFormatoFecha(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	// public List getReportePERConsolidadoCobranzasChequearDatos(Map criteria);
	/**
	 * Obtiene un listado de todos los Grupos de Proceso
	 * 
	 * @return
	 */
	public List getGrupoProceso();

	public String getIndicadorAnulacionByCriteria(Map map);

	/**
	 * Devuelve el indicador de equivalencia
	 * 
	 * @param params
	 * @return
	 */
	public String getIndicadorEquivalencia(Map params);

	/**
	 * Obtiene la data para la Interfaz BELDireccionClientes, en base a un
	 * conunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELDireccionClientes, poblados.
	 */
	public List getInterfazBELDireccionClientes(Map params);

	/**
	 * Obtiene la data para la Interfaz BEL Facturas Cabecera, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELFacturasCabecera, poblados.
	 */
	public List getInterfazBELFacturasCabecera(Map params);

	/**
	 * Obtiene la data para la Interfaz BEL Facturas Detalle, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELFacturasDetalle, poblados.
	 */
	public List getInterfazBELFacturasDetalle(Map params);

	/**
	 * Obtiene la data para la Interfaz BELPorcentajesReferencia, en base a un
	 * conunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELPorcentajesReferencia, poblados
	 */
	public List getInterfazBELPorcentajesReferencia(Map params);

	/**
	 * Obtiene la data para la Interfaz BELUbicacionesGeograficas, en base a un
	 * conjunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELUbicacionesGeograficas, poblados
	 */
	public List getInterfazBELUbicacionesGeograficas(Map params);

	/**
	 * Obtiene la data para la Interfaz BELUnidadesAtendidas, en base a un
	 * conjunto de parámetros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELPorcentajesReferencia, poblados
	 */
	public List getInterfazBELUnidadesAtendidas(Map params);

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public List getInterfazCOMEnviarLibretaAhorros(Map params);

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public List getInterfazCOMEnviarLideresNuevas(Map params);

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public List getInterfazCOMEnviarPagoLideres(Map params);

	/**
	 * Obtiene la data para la Interfaz COM Recepción de Códigos Planillas, en
	 * base a un conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazCOMRecopla, poblados.
	 */
	public List getInterfazCOMRecopla(Map params);

	/**
	 * Obtiene listado para la interfaz de DATAMART Enviar Control de Cierre
	 * para registros de error
	 * 
	 * @param params
	 *            Map con parametros del query
	 * @return Lista de Maps con el resultado de control de cierres
	 */
	public List getInterfazDATEnviarControlCierresErrorCierrePeriodo(
			Map criteria);

	/**
	 * Obtiene listado para la interfaz de DATAMART Enviar Control de Cierre
	 * para registros de error
	 * 
	 * @param params
	 *            Map con parametros del query
	 * @return Lista de Maps con el resultado de control de cierres
	 */
	public List getInterfazDATEnviarControlCierresErrorZonasRegiones(
			Map criteria);

	/**
	 * Obtiene listado para la interfaz de DATAMART Enviar Control de Cierre
	 * 
	 * @param params
	 *            Map con parametros del query
	 * @return Lista de Maps con el resultado de control de cierres de periodo
	 */
	public List getInterfazDATEnviarControlCierresPeriodos(Map criteria);

	/**
	 * Obtiene listado para la interfaz de DATAMART Enviar Control de Cierre
	 * 
	 * @param params
	 *            Map con parametros del query
	 * @return Lista de Maps con el resultado de control de cierres de periodo
	 */
	public List getInterfazDATEnviarControlCierresPeriodosSalida(Map criteria);

	/**
	 * Obtiene listado para la interfaz de DATAMART Enviar Control de Cierre
	 * 
	 * @param params
	 *            Map con parametros del query
	 * @return Lista de Maps con el resultado de control de cierres de regiones
	 *         o zonas
	 */
	public List getInterfazDATEnviarControlCierresZonasRegiones(Map criteria);

	/**
	 * Interfaz DATAMART para Enviar Cursos
	 * 
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarCursos(Map params);

	/**
	 * Interfaz DATAMART para Enviar Empresas
	 * 
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarEmpresa(Map params);

	/**
	 * @param params
	 */
	public List getInterfazDATEnviarIncentivosEstadoConcurso(Map params);

	/**
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarIncentivosProductosAlcance(Map params);

	/**
	 * @param params
	 */
	public List getInterfazDATEnviarIncentivosTipoAgrupacion1Concurso(Map params);

	/**
	 * @param params
	 */
	public List getInterfazDATEnviarIncentivosTipoAgrupacion2Concurso(Map params);

	/**
	 * @param params
	 */
	public List getInterfazDATEnviarIncentivosTipoClienteDirigidoConcurso(
			Map params);

	/**
	 * @param params
	 */
	public List getInterfazDATEnviarIncentivosTipoEfectividad(Map params);

	/**
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarIncentivosTipoTiempo(Map params);

	/**
	 * @param params
	 */
	public List getInterfazDATEnviarIncentivosTipoUnidadMedida(Map params);

	/**
	 * Interfaz DATAMART para Enviar Instructoras x Geografia
	 * 
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarInstructoraGeografia(Map params);

	/**
	 * Interfaz DATAMART para Enviar Instructoras
	 * 
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarInstructoras(Map params);

	/**
	 * Interfaz DATAMART para Enviar Paises
	 * 
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarPais(Map params);

	/**
	 * Interfaz DATAMART para Enviar Programas
	 * 
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarPrograma(Map params);

	/**
	 * Obtiene la data para la Interfaz GIS Direccion Consultoras, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazGISEnviarDireccionConsultoras, poblados.
	 */
	public List getInterfazGISEnviarDireccionConsultoras(Map params);

	public List getInterfazLAREnviarSecuenciaPedidos(Map queryParams);

	public List getInterfazLAREnviarVisibilidadCronogramaFacturacion(
			Map queryParams);

	public List getInterfazLLIEnviarVentaPeriodo(Map params);

	/**
	 * Obtiene la data para la Interfaz MyEbelCronogramaFacturacion, en base a
	 * un conunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazMyEbelCronogramaFacturacion, poblados
	 * @deprecated
	 */
	public List getInterfazMyEbelCronogramaFacturacion(Map params);

	/**
	 * Obtiene la data para la Interfaz MovimientosCuentaCorriente, en base a un
	 * conunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazMovimientosCuentaCorriente, poblados.
	 * @deprecated
	 */
	public List getInterfazMyEbelMovimientosCuentaCorriente(Map params);

	public List getInterfazMYECodigoAutorizacionSunat(Map params);

	/**
	 * Obtiene la data para la Interfaz Instructoras, en base a un conjunto de
	 * parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos map, poblados.
	 */
	public List getInterfazMYEEnviarInstructoras(Map params);

	/**
	 * Obtiene la data para la Interfaz Regiones, en base a un conjunto de
	 * parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos map, poblados.
	 */
	public List getInterfazMYEEnviarRegiones(Map params);

	/**
	 * Obtiene la data para la Interfaz Zonas, en base a un conjunto de
	 * parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos map, poblados.
	 */
	public List getInterfazMYEEnviarZonas(Map params);

	/**
	 * Obtiene la data para la Interfaz MovimientosCuentaCorriente, en base a un
	 * conunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos map, poblados.
	 */
	public List getInterfazMYEMovimientosCuentaCorriente(Map params);

	public List getInterfazMYEPercepcionesVentaDirectaCabecera(Map params);

	public List getInterfazMYEPercepcionesVentaDirectaDetalle(Map params);

	/**
	 * Obtiene la cabecera del consolidado de archivos de texto de OCS de la
	 * tabla temporal.
	 * 
	 * @param criteria
	 *            Map con los parametros del query
	 * @return List con Maps con las filas del consolidado
	 * 
	 * @author Lennon Shimokawa
	 */
	public List getInterfazOCRConsolidadoOCSCabecera(Map criteria);

	/**
	 * Obtiene el detalle del consolidado de archivos de texto de OCS de la
	 * tabla temporal.
	 * 
	 * @param criteria
	 *            Map con los parametros del query
	 * @return List con Maps con las filas del consolidado
	 * 
	 * @author Lennon Shimokawa
	 */
	public List getInterfazOCRConsolidadoOCSDetalle(Map criteria);

	public List getInterfazOCREnviarRangosPeriodo(Map params);

	/**
	 * Obtiene la data para la Interfaz OCRRegiones, en base a un conjunto de
	 * parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos map, poblados.
	 */
	public List getInterfazOCREnviarRegiones(Map params);

	/**
	 * Obtiene la data para la Interfaz OCRZonas, en base a un conjunto de
	 * parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos map, poblados.
	 */
	public List getInterfazOCREnviarZonas(Map params);

	/**
	 * Obtiene la cantidad de registros de Percepciones Consolidadas para ver si
	 * se actualiza o registra
	 * 
	 * @param consolidadJo
	 * @return
	 */
	public String getInterfazPERActualizarPercepcionesConsolidadoCantidad(
			InterfazPERActualizarPercepcionesConsolidado consolidado);

	/**
	 * Obtiene la data para el Envio del Archivo PDT de SUNAT, en base a un
	 * conjunto de parametros que son pasados por un map
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getInterfazPERArchivoPDT(Map params);

	/**
	 * Obtiene el periodo a evaluar para la Interfaz PRI Personalizacion de
	 * Sobres.
	 * 
	 * @param params
	 *            Map con parametros del query
	 * @return Periodo a evaluar, null en caso que no exista el periodo
	 * @author Lennon Shimokawa
	 */
	public String getInterfazPRIPeriodoEvaluar(Map params);

	/**
	 * Obtiene los subaccesos de Gerente de Zona seguido de los subaccesos de
	 * los Bellcenters.
	 * 
	 * @param criteria
	 *            Map con los parametros del query
	 * @return List con Maps con los codigos y descripciones de los subaccesos
	 * @author Lennon Shimokawa
	 */
	public List getInterfazRECProductosReclamadosSubAccesos(Map criteria);

	/**
	 * Obtiene la data para la Interfaz RetailComplementoFacturasVentaDirecta,
	 * en base a un conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazRetailComplementoFacturasVentaDirecta,
	 *         poblados.
	 */
	public List getInterfazRETEnviarComplementoFacturasVentaDirecta(Map params);

	/**
	 * Obtiene la data para la Interfaz RetailFacturasVentaDirecta, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazRETEnviarFacturasVentaDirecta, poblados.
	 */
	public List getInterfazRETEnviarFacturasVentaDirecta(Map params);

	/**
	 * Obtiene la data para la Interfaz RetailMatrizCampanya, en base a un
	 * conunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazRetailMatrizCampanya, poblados.
	 */
	public List getInterfazRETEnviarMatrizCampanya(Map params);

	/**
	 * Obtiene la data para la Interfaz MyEbelCronogramaFacturacion, en base a
	 * un conunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos map, poblados
	 */
	public List getInterfazREUCronogramaFacturacion(Map params);

	/**
	 * Obtiene la data para la Interfaz RetailDocumentosAnulados, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos map, poblados.
	 */
	public List getInterfazREUDocumentosAnulados(Map params);

	/**
	 * Obtiene la data para el envio de Consultoras de la Interfaz de
	 * Reutilizacion, en base a un conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazREUEnviarConsultoras, poblados.
	 */
	public List getInterfazREUEnviarConsultoras(Map params);

	/**
	 * Obtiene la data para la Interfaz REUMatrizCampanya, en base a un conunto
	 * de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos map, poblados
	 */
	public List getInterfazREUMatrizCampanya(Map params);

	/**
	 * Obtiene la data para la Interfaz BELPeriodosFacturacion, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazREUPeriodosFacturacion, poblados.
	 */
	public List getInterfazREUPeriodosFacturacion(Map params);

	/**
	 * Obtiene la data para la Interfaz RetailDocumentosAnulados, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazRetailDocumentosAnulados, poblados.
	 * @deprecated
	 */
	public List getInterfazReutilizacionDocumentosAnulados(Map params);

	/**
	 * Obtiene la data para la Interfaz REU Matriz Campanya, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazREUMatrizCampanya, poblados.
	 */
	public List getInterfazReutilizacionMatrizCampanya(Map params);

	/**
	 * Obtiene la data para la Interfaz SAB Fuente Ventas Prevista, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazSABRentabilidadPorZona, poblados.
	 */
	public List getInterfazSABEnviarFuenteVentasPrevista(Map params);

	/**
	 * Obtiene la data para la Interfaz SABIncentivosConsultoras, en base a un
	 * conjunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazSABIncentivosConsultoras, poblados
	 */
	public List getInterfazSABEnviarIncentivosConsultoras(Map params);

	/**
	 * Obtiene la data para la Interfaz SAB Rentabilidad Por Zona, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazSABRentabilidadPorZona, poblados.
	 */
	public List getInterfazSABEnviarRentabilidadPorZona(Map params);

	/**
	 * Obtiene la data para la Interfaz SAM Inicializacion de Stocks, en base a
	 * un conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazSAMInicializacionStocks, poblados.
	 */
	public List getInterfazSAMEnviarInicializacionStocks(Map params);

	public List getInterfazSATEnviarAFPConsultora(Map criteria);

	public List getInterfazSATEnviarAFPEstibados(Map criteria);

	/*
	 * Archivo de Control
	 */
	public List getInterfazSATEnviarArchivoControl(Map criteria);

	public List getInterfazSATEnviarBoletaDespachoA(Map criteria);

	public List getInterfazSATEnviarCostosPorCaja(Map criteria);

	public List getInterfazSATEnviarCronogramaZonas(Map criteria);

	public List getInterfazSATEnviarHojaPicking(Map criteria);

	/*
	 * Interfaz SAT Envia volumenes y valorizacion por seccion dia
	 */

	public List getInterfazSATEnviarVolumenesValorizacionPorSeccionDia(
			Map criteria);

	/**
	 * Obtiene la data para la interfaz Enviar Dupla CyZone - Inscritas
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getInterfazSICDuplaCyZoneInscritasList(Map params);

	/**
	 * Obtiene la data para la Interfaz PRI Personalizacion de Sobres, en base a
	 * un conunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Map con parametros del query
	 * @return List con Maps de InterfazSICEnviarClasificacionConsultorasNuevas
	 * @author Lennon Shimokawa
	 */
	public List getInterfazSICEnviarClasificacionConsultorasNuevas(Map params);

	/**
	 * Obtiene la data para la interfaz Enviar Dupla CyZone - Inscritas
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getInterfazSICInscritasList(Map params);

	/**
	 * Obtiene el tipo de clasificacion para la Interfaz PRI Personalizacion de
	 * Sobres.
	 * 
	 * @param params
	 *            Map con parametros del query
	 * @return Codigo de tipo de clasificacion
	 * @author Lennon Shimokawa
	 */
	public String getInterfazSICTipoClasificacion(Map params);

	/**
	 * Obtiene la data para la interfaz Enviar Dupla CyZone - Venta Base
	 * Consultoras
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getInterfazSICVentaBaseConsultorasList(Map params);

	/**
	 * Obtiene la data para la interfaz Enviar Dupla CyZone - Venta Puntajes por
	 * Campaña
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getInterfazSICVentasPuntajesXCampanyaList(Map params);

	/**
	 * Obtiene la fecha inicio y fecha fin, de un periodo de facturacion.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal, codigoPeriodo.
	 * 
	 * @return Objeto de tipo Base poblado (fechaInicio, fechaFin).
	 */
	public Base getIntervalosFechaComplementoFacturasVentaDirecta(Map params);

	/**
	 * Obtiene la fecha inicio y fecha fin, de un periodo de facturacion.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal, codigoAcceso, codigoPeriodo.
	 * 
	 * @return Objeto de tipo Base poblado (fechaInicio, fechaFin).
	 */
	public Base getIntervalosFechaFacturasVentaDirecta(Map params);

	public List getLinea(Map params);

	public List getLista(String tipoLista, Map params);

	/**
	 * Obtiene la lista de periodos de un determinado país y canal
	 * 
	 * @return Lista de objetos Base, poblados.
	 */

	public List getListaComisionesByPeriodo(Map params);

	public List getListaEstimados(Map params);

	public EstimadoProductos getListaEstimadosProducto(Map datosJSP,
			EstimadoProductos cabecera);

	/**
	 * Obtiene la lista de periodos de la tabla BAS_CTRL_FACT
	 * 
	 * @return Lista de objetos Periodos
	 */
	public List getListaPeriodosByBasCtrlFact(Map params);

	/**
	 * Obtiene la lista de periodos de un determinado país y canal
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getListaPeriodosByPaisCanal(Map params);

	/**
	 * Obtiene un listado de el año con el correlativo de los periodos
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getListaPeriodosInicial(Map params);

	/**
	 * Obtiene la lista de los procesos de Cierre de Facturacion
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListaProcesosCierreFacturacion(Map criteria);

	/**
	 * Obtiene la lista de los procesos de Cierre de Campaña
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListaProcesosGenCierreCampania(Map criteria);

	/**
	 * Obtiene la lista de los procesos de Incentivos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListaProcesosIncentivos(Map criteria);

	/**
	 * Obtiene la lista de los procesos de let
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListaProcesosLet(Map criteria);

	/**
	 * @param codigo
	 * @return
	 */
	public List getListCodComision(String codigo);

	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de
	 * busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getMapaByCentro(Map params);

	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de
	 * busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getMapaZonasByCentro(Map params);

	/**
	 * 
	 * @param codigoMarca
	 * @return
	 */
	public Base getMarcaByCodigo(String codigoMarca);

	/**
	 * Obtiene un listado de todas las marcas disponibles en la tabla SEG_MARCA@SICC
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getMarcas();

	/**
	 * @param codigoPais
	 * @return
	 */
	public List getMotivosNoRecojoBoletasRecojo(String codigoPais);

	public String getNombreArchivoIVR(Map params);

	public List getNumeroLoteByFact(Map criteria);

	public String getNumeroLoteIntHistoLotes(Map params, String interFaz);

	/**
	 * Devuelve el número de periodos cerrados.
	 * 
	 * @param Params
	 *            Codigo de periodo y regiones seleccionadas
	 */
	public Integer getNumeroRegionesCerradas(Map params);

	public List getNumeroVersionesFaltanteDia(Map params);

	public String getNumLoteSTO(Map params);

	public String getOidGenerico(String funcion, Map params);

	/**
	 * @param codigoPais
	 * @return
	 */
	public Integer getOidPaisInterfazLARRecepcionarActualizaResultadoChequeo(
			String codigoPais);

	public String getOidString(String string, Map mapQueryParams);

	/**
	 * Obtiene un listado de todas las Operaciones disponibles en base al codigo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getOperacionesByCodigoPais(Map criteria);

	public String getPercepcionesConsolidadoCorrelativoSiguiente();

	/**
	 * Obtiene el periodo vigente de un determinado país y canal
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public String getPeriodoDefaultByPaisCanal(Map params);

	/**
	 * Obtiene la lista de periodos de un determinado país y canal
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public String getPeriodoDefaultByPaisMarcaCanal(Map params);

	/**
	 * Obtiene la lista de periodos de un determinado país y canal
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public String getPeriodoDefaultByPaisMarcaCanalAcceso(Map params);

	/**
	 * Obtiene La fecha y el periodo de la campaña activa, para ambos esquemas 
	 * 
	 * @return
	 */
	public Map getPeriodoFechaCampanyaActiva();

	public Map getPeriodoFechaCampanyaActivaSF(Map params);
	
	/**
	 * Consulta si es cargo base, por codigo. 
	 * 
	 * @return
	 */
	public Map getCargoBaseById(Map params);

	/**
	 * Capturar Periodo y Fecha Proceso. Activos.
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPeriodoFechaProcesoActual(Map criteria);

	/**
	 * Obtiene el periodo fin de un rango
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public String getPeriodoFin(String codigoRango);

	/**
	 * Obtiene el periodo inicio de un rango
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public String getPeriodoInicio(String codigoRango);

	/**
	 * Obtiene una lista de periodos en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getPeriodosByPaisMarcaCanal(Map params);

	/**
	 * Obtiene la lista de periodos de un determinado país y canal
	 * 
	 * @return Lista de objetos Base, poblados.
	 */

	public List getPeriodosByPaisMarcaCanalAcceso(Map params);

	/**
	 * Obtiene una lista de periodos en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getPeriodosByPaisMarcaCanalOrderFechaFin(Map params);

	/**
	 * Obtiene una lista de periodos en base a criterios de busqueda en donde se
	 * incluyen la cantidad de años hacia atras y hacia adelante a considerar en
	 * el rango de la consulta.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal, offsetAtras, offsetAdelante.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getPeriodosByPaisMarcaCanalRangos(Map params);

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public List getPeriodosByPMC(Map params);

	/**
	 * Obtiene la lista de periodos en base al tipo.
	 * 
	 * @param tipo
	 *            Tipo de periodo.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getPeriodosByTipo(String tipo);

	/**
	 * Obtiene la lista de periodos en base al tipo, por Pais, Marca y Canal.
	 * 
	 * @param tipo
	 *            Tipo de periodo.
	 * 
	 * @return Lista de objetos Periodo, poblados.
	 */
	public List getPeriodosByTipoPMC(String tipo);

	/**
	 * Obtiene la lista de periodos en base al tipo, por Pais, Marca, Canal y
	 * Acceso.
	 * 
	 * @param tipo
	 *            Tipo de periodo.
	 * 
	 * @return Lista de objetos Periodo, poblados.
	 */
	public List getPeriodosByTipoPMCA(String tipo);

	/**
	 * Obtiene la lista de periodos para la interfaz
	 * ComplementoFacturasVentaDirecta.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal.
	 * 
	 * @return Lista de objetos Base poblados (codigo y descripcion).
	 */
	public List getPeriodosComplementoFacturasVentaDirecta(Map params);

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public List getPeriodosDefaultByPMC(Map params);

	/**
	 * Obtiene la lista de periodos para la interfaz RetailFacturasVentaDirecta
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal, codigoAcceso.
	 * 
	 * @return Lista de objetos Base poblados (codigo y descripcion).
	 */
	public List getPeriodosFacturasVentaDirecta(Map params);

	/**
	 * Obtiene la lista de periodos para la interfaz SABRentabilidadPorZona.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal.
	 * 
	 * @return Lista de objetos Base poblados (codigo y descripcion).
	 */
	public List getPeriodosSABRentabilidadPorZona(Map params);

	/**
	 * Obtiene la lista de todas las plantillas - concursos que existen
	 * 
	 * @param codigo
	 *            Codigo de Consultora
	 * @return lista de plantillas
	 */
	public List getPlantillasConcursos();

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getProcesoBatchByInterfaz(Map criteria);

	public List getProductosByCriteria(Map criteria);

	/* Valida si existe Proveedor */
	/**
	 * @param criteria
	 * @return
	 */
	public Integer getProveedorCount(Map criteria);

	/**
	 * 
	 * @param periodo
	 * @return
	 */
	public Base getRangoPeriodoByCodigo(String periodo);

	/**
	 * Obtiene un listado de todas los perídos disponibles en la tabla
	 * CRA_RANGO_PERIO@SICC
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getRangosPeriodo();

	/**
	 * Obtiene la data para la Interfaz PERRecaudosBancarios, en base a un
	 * conjunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos , poblados
	 */
	public List getRecaudosBancarios(Map params);

	public List getRECConsolidadoUnidadesAlmacenVirtual(Map params);

	/**
	 * Obtiene un listado de todas los productos reclamados para tipo ingreso En
	 * Lote
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRECLoteProductosList(Map criteria);

	/**
	 * Obtiene un listado de todas los productos reclamados Cabecera
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRECProductosCabList(Map criteria);

	/**
	 * Obtiene un listado de todas los productos reclamados Detalle Det
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRECProductosDetDetList(Map criteria);

	/**
	 * Obtiene un listado de todas los productos reclamados Detalle
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRECProductosDetList(Map criteria);

	/**
	 * Obtiene un listado de todas los productos reclamados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRECProductosList(Map criteria);

	/**
	 * Obtiene un listado de todas los productos reclamados xLote
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRECProductosListxLote(Map criteria);

	public List getRECProductosSAM7List(Map criteria);

	public List getRegion();

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getRegionByCodigo(Map criteria);

	/**
	 * Obtiene una lista de SubGerencias en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getRegionByPaisCanalSubGerencia(Map params);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public List getRegionByPaisMarcaCanalSubGerencia(Map params);

	/**
	 * Metodo que obtiene la lista de regiones cerradas y activas por medio del
	 * codigo de pais y periodo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRegionesActivasCerradasByPeriodo(Map criteria);

	public List getRegionesByPais(Map params);

	/**
	 * Obtiene una lista de Regiones en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoCanal.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getRegionesByPaisCanal(Map params);

	/**
	 * Devuelve todas las regiones por pais, activos e inactivos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRegionesByPaisLet(Map criteria);

	/**
	 * Obtiene una lista de Regiones en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getRegionesByPaisMarcaCanal(Map params);

	/**
	 * Obtiene una lista de Regiones en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoPeriodo,
	 * 
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getRegionesByPeriodoBasCtrlFact(Map params);

	/**
	 * Obtiene una lista de Regiones en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPeriodo,
	 * 
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getRegionesByPeriodoIntEviPerioRegio(Map params);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReporteBaseRecuperacionCampanhasPais(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReporteBaseRecuperacionCampanhasRegion(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReporteBaseRecuperacionCampanhasZona(Map criteria);

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public List getReporteConsultorasRecomendadasPDF(Map criteria);

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public List getReporteConsultorasRecomendadasXLS(Map criteria);

	/**
	 * Obtiene la informacion para el Reporte de Mica de Recepción de Pedidos
	 * por Region
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReporteEVIMicaRecepcionPedidosRegion(Map criteria);

	/**
	 * Obtiene la informacion para el Reporte de Mica de Recepción de Pedidos
	 * por Zona
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReporteEVIMicaRecepcionPedidosZona(Map criteria);

	public List getReportePERAntiguedadDeudasChequearDatos(Map criteria);

	public List getReportePERAntiguedadDeudasMesesActual(Map criteria);

	public List getReportePERAntiguedadDeudasMesesHaceDosAnhos(Map criteria);

	public List getReportePERAntiguedadDeudasMesesHaceUnAnho(Map criteria);

	public List getReportePERAntiguedadDeudasPeriodosActual(Map criteria);

	public List getReportePERAntiguedadDeudasPeriodosHaceDosAnhos(Map criteria);

	public List getReportePERAntiguedadDeudasPeriodosHaceUnAnho(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERFacturasPendientesSeccion(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERFacturasPendientesSeccionDetalle(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERFacturasPendientesSeccionPedidosRegion(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERFacturasPendientesSeccionPedidosZona(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERFacturasPendientesSeccionResumen(Map criteria);

	public List getReportePERLibroPercepciones(Map criteria);

	/**
	 * Obtiene la data para el Reporte de Liquidacion de cobranzas
	 * 
	 * @param criteria
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getReportePERLiquidacionCobranza(Map criteria);

	public List getReportePERListaAbonosDirectos(Map criteria);

	public List getReportePERListaAbonosDirectosPercepciones(Map criteria);

	public List getReportePERListaAbonosPorCobranza(Map criteria);

	public List getReportePERListaAbonosPorNotaCredito(Map criteria);

	public List getReportePERListaCargosDirectos(Map criteria);

	public List getReportePERListaCargosFacturacion(Map criteria);

	public List getReportePERListaControlCliente(Map criteria);

	public List getReportePERListaGenerica(Map criteria);

	/**
	 * @param codigoPais
	 * @return
	 */
	public List getResultadosBoletasRecojo(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @param codigoCliente
	 * @param fechaDocumentoDesde
	 * @param fechaDocumentoHasta
	 * @param fechaVencimientoDesde
	 * @param fechaVencimientoHasta
	 * @return
	 */
	public Double getSaldoCtaCteCliente(String codigoPais,
			String codigoCliente, String fechaDocumentoDesde,
			String fechaDocumentoHasta, String fechaVencimientoDesde,
			String fechaVencimientoHasta);

	/**
	 * Obtiene la lista de secciones por Pais, Marca, Canal, Region y Zona
	 * 
	 * @param params
	 * @return Lista de objetos Base como secciones
	 */
	public List getSeccionesByPaisMarcaCanalRegionZona(Map params);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getSociedadByCodigo(Map criteria);

	/**
	 * Obtiene la descripcion de la sociedad SAP
	 * 
	 * @param params
	 * @return
	 */
	public String getSociedadEquivalenciaSAP(Map params);

	/**
	 * Obtiene un listado de todas las sociedades disponibles en base al codigo
	 * del pais.
	 * 
	 * @param codigo
	 *            Codigo del pais.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getSociedadesByCodigoPais(String codigo);

	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de
	 * busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getSubaccesoByAcceso(Map params);

	/**
	 * Obtiene un listado de todos los subaccesos disponibles en base al codigo
	 * ISO, codigo de acceso y codigo de subacceso.
	 * 
	 * @param Map
	 *            filtro de criterios
	 * 
	 * @return objeto Subacceso, poblados.
	 */
	public Base getSubaccesosByCodigo(Map criteria);

	/**
	 * Obtiene un listado de todos los subaccesos disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Subacceso, poblados.
	 */
	public List getSubaccesosByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los subaccesos disponibles en base al codigo
	 * ISO y codigo de Acceso.
	 * 
	 * @param Map
	 *            filtro de criterios
	 * 
	 * @return Lista de objetos Subacceso, poblados.
	 */
	public List getSubaccesosByCriteria(Map criteria);

	/**
	 * Obtiene una lista de SubGerencias en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */

	public List getSubGerenciasByPaisMarcaCanal(Map params);

	/**
	 * Obtiene una lista de SubGerencias en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getSubGerenciaxPaisMarcaCanal(Map params);

	public List getSublineaByLinea(Map params);

	public List getSubProcesoByProceso(Map params);

	/**
	 * Obtiene un listado de todos los sub-tipos de clientes disponibles en base
	 * al codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getSubTiposClientesByCriteria(Map criteria);

	public List getSubTiposClientesMultipleByCriteria(Map criteria);

	public List getSubTiposClientesMultipleByCriteriaOID(Map criteria);
	
	public List getSubTiposClientesMultipleByCriteriaCodigo(Map criteria);

	/**
	 * Obtiene la lista de territorios por Pais, Marca, Canal, Region, Zona y
	 * Seccion
	 * 
	 * @param params
	 * @return lista de objetos Base como territorios
	 */
	public List getTerritoriosByPaisMarcaCanalRegionZonaSeccion(Map params);

	public List getTerritoriosByPeriodoRegioZona(Map params);

	public List getTipoComprobantesPago();

	public List getTipoDocumentosByCodigoISO(Map criteria);

	/**
	 * 
	 * 
	 * @return
	 */
	public List getTipoOrigenDatos();

	/**
	 * @param criteria
	 * @return
	 */
	public List getTipoOrigenDatos2(Map criteria);

	/**
	 * Obtiene un listado de los Tipos de Origen de Datos para Recaudos
	 * Bancarios
	 * 
	 * @return Lista con los Tipos de Origen de Datos para Recaudos Bancarios.
	 */
	public List getTipoOrigenDatosRecaudosBancarios();

	/**
	 * Obtiene un listado de todas los productos reclamados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTiposCierres();

	/**
	 * Obtiene la lista de Tipos de Clasificaciones en base a Tipo Cliente,
	 * Sub-Tipo Cliente
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getTiposClasificacionesByCriteria(Map params);

	public List getTiposClasificacionesByCriteriaMultiple(Map criteria);

	public List getTiposClasificacionesByCriteriaMultipleOID(Map criteria);
	
	public List getTiposClasificacionesByCriteriaMultipleCodigo(Map criteria);

	/**
	 * Obtiene un listado de todos los tipos de clientes disponibles en base al
	 * codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getTiposClientesByCodigoISO(String codigo);

	public List getTiposClientesByCodigoISOOID(String codigoISO);

	/**
	 * Obtiene un listado de todos los tipos de ingreso disponibles en base al
	 * codigo ISO.
	 * 
	 * @param criteria
	 *            Map con los parametros del query.
	 * @return Lista de objetos Base, poblados.
	 * @author Lennon Shimokawa
	 */
	public List getTiposIngresoByCodigoISO(Map criteria);

	/**
	 * Retorna la lista de Tipo de Intefaz para el Modulo SMS
	 * 
	 * @return
	 */
	public List getTiposInterfazSMS();

	public List getTipoSol(Map params);

	/**
	 * Obtiene tipo de Recepcion MICA y OCS (de los paquetes)
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTiposRecepcionMICAyOCS();

	public List getTiposSolicitudPais(String codigoISO);

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public List getTiposTransaccionesByCodigoPais(String codigo);

	/**
	 * Obtiene la lista de Tipos de Vinculos por pais
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getTiposVinculosByPais(String codigoPais);

	/**
	 * @param params
	 * @return Lista de Validación Existencia de Productos
	 */
	public List getValidaExistenciaProductos(Map params);

	public List getVersionByMapa(Map params);

	public List getVersionByMapaAnt(Map params);

	public List getVersionSinP(Map params);

	public List getWebServiceLAREnvioSecuenciacionzonasList();

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getZonaByCodigo(Map criteria);

	public List getZonaByPaisZona(Map params);

	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de
	 * busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getZonasByPais(Map params);

	/**
	 * Obtiene una lista de Zonas tanto activas como no Activas
	 * 
	 * @param params
	 * @return
	 */
	public List getZonasByPaisActivasNoActivas(Map params);

	/**
	 * Obtiene una lista de Regiones en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais, codigoMarca,
	 *            codigoCanal.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getZonasByPaisCanalRegion(Map params);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public List getZonasByPaisMarcaCanalRegion(Map params);

	/**
	 * Devuelve todas las zonas por pais y region seleccionada, activos e
	 * inactivos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getZonasByPaisRegionLet(Map criteria);

	/**
	 * Obtiene una lista de Zonas en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la codigoZona, codigoPais, codigoPeriodo.
	 * 
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getZonasByPeriodoBasCtrlFact(Map params);

	/**
	 * Obtiene una lista de Zonas en base a criterios de busqueda, relacionado
	 * con la tabla de pedidos.
	 * 
	 * @param params
	 *            Parametros para la codigoZona, codigoPeriodo.
	 * 
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getZonasByPeriodoIntEviPerioRegioZona(Map params);

	public void inserParamInterfaz(Map params, String string, String string2,
			boolean valorString);

	public void inserParamTmpInterfaz(Map params, String string,
			String codigoParametro, boolean valorString);

	public void insertarInterfazRETRecepcionarCOM1(Map params);

	public void insertarInterfazRETRecepcionatCOM2(Map params);

	public void insertarInterfazRETRecepcionatCOM3(Map params);

	/**
	 * @param Params
	 */
	public void insertInterfazLLIRecepcionarActualizaEstimadosYobel(Map Params);

	/**
	 * Inserta la cabecera de los archivos de texto de OCS en la tabla temporal.
	 * 
	 * @param params
	 *            Map con la data a insertar
	 * @author Marco Silva
	 */
	public void insertInterfazOCRConsolidadoOCSCabecera(Map params);

	/**
	 * Inserta la detalle de los archivos de texto de OCS en la tabla temporal.
	 * 
	 * @param params
	 *            Map con la data a insertar
	 * @author Marco Silva
	 */
	public void insertInterfazOCRConsolidadoOCSDetalle(Map params);

	public void insertInterfazPERActualizarPercepcionesConsolidado(
			InterfazPERActualizarPercepcionesConsolidado consolidado,
			Usuario usuario);

	public void insertInterfazPRIRecepcionarCalificacion(Map params);

	public void insertInterfazPRIRecepcionarClientes(Map params);

	public void insertInterfazRECEnviarTransferenciaBoletasRecojo(Map map);

	public void insertInterfazRECEnviarTransferenciaBoletasRecojoAnulacion(
			Map map);

	public void insertInterfazRECRecepcionarBoletasRecojoCabecera(Map params);

	public void insertInterfazRECRecepcionarBoletasRecojoControl(Map queryParams);

	public void insertInterfazRECRecepcionarBoletasRecojoDetalle(Map params);

	public void insertInterfazRETRecepcionarVentasRetailCab(Map params);

	public void insertInterfazRETRecepcionarVentasRetailDet(Map params);

	public void insertInterfazRETRecepcionarVentasRetailDetDet(Map params);

	/**
	 * 
	 * @param interfaz
	 * @param usuario
	 */
	public void insertPercepcionesCabecera(
			MovimientosBancariosCabecera interfaz, Usuario usuario);

	/**
	 * 
	 * @param interfaz
	 * @param usuario
	 */
	public void insertPercepcionesDetalle(MovimientosBancariosDetalle interfaz,
			Usuario usuario);

	/**
	 * Inserta los productos reclamados Historico de Envios
	 * 
	 * @param criteria
	 * @return
	 */
	public void insertRECHistoricoEnvios(Map criteria);

	/**
	 * Inserta los productos reclamados Detalle Det
	 * 
	 * @param criteria
	 * @return
	 */
	public void insertRECLineaOper(Map criteria);

	/**
	 * Inserta los productos reclamados Operacion Lote Sap
	 * 
	 * @param criteria
	 * @return
	 */
	public void insertRECOperNumLoteSap(Map criteria);

	public void insertRETConsolidadoVentasRetail(Map params);

	public void insertRETConsolidadoVentasRetailxZona(Map params);

	public void insertSATEstimadoProductos(EstimadoProductos cabecera,
			Usuario usuario);

	public void insertSATEstimadoProductos(EstimadoProductos cabecera,
			Usuario usuario, Map datosJSP);

	public void insertVentaBaseConsultoras(Map params);

	/**
	 * Valida si el periodo es valido
	 * 
	 * @param periodo
	 * @return
	 */
	public boolean isPeriodoValido(String periodo);

	List obtenerSeccionesTrasvaseActualizacionUnidadAdministrativa(Map criteria);

	List obtenerTerritoriosTrasvaseActualizacionUnidadAdministrativa(
			Map criteria);

	public void saveXMLBoletasRecojo(Map params);

	/**
	 * @param resumen
	 * @param usuario
	 */
	public void updateInterfazCOMRecepcionarActualizaCodigoProveedor(
			InterfazCOMRecepcionarActualizaCodigoProveedor resumen,
			Usuario usuario);

	/**
	 * @param Params
	 */
	public void updateInterfazLARRecepcionarActualizaResultadoChequeo(Map Params);

	/**
	 * @param Params
	 */
	public void updateInterfazLARRecepcionarActualizaSecuenciacionzonas(
			Map Params);

	/**
	 * Actualiza registro de Percepciones Consolidadas en caso exista registro
	 * alguno
	 * 
	 * @param consolidado
	 * @param usuario
	 */
	public void updateInterfazPERActualizarPercepcionesConsolidado(
			InterfazPERActualizarPercepcionesConsolidado consolidado,
			Usuario usuario);

	/**
	 * Actualiza el codigo de interfaz en la tabla de procesos Batch
	 * 
	 * @param queryParams
	 */
	public void updateInterfazProcesoBatch(Map queryParams);

	/**
	 * Actualiza el flag de transferencia de clientes, para que no sean enviados
	 * la proxima vez que se ejecute la interfaz
	 */
	public void updateInterfazREUIndicadorTransferenciaClientes();

	/**
	 * Obtiene la lista de consultoras.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return
	 */
	public void updateLibretaAhorro(LibretaAhorro libretaAhorro);

	/**
	 * Actualiza Libreta de Ahorrps
	 * 
	 * @param libretaAhorro
	 *            Parametros de Libreta de Ahorros
	 * 
	 * @param usuario
	 *            Usuario
	 * @return
	 */
	public void updateLibretaAhorro(LibretaAhorro libretaAhorro, Usuario usuario);

	public void updateRETConsolidadoVentasRetail(Map params);

	public List getListaAlmacen(Map criteria);

	public List getTiposRecepcionXRX(String codigoInterfaz1,
			String codigoInterfaz2);

	/* INI JJ PER-SiCC-2012-0345 */
	/**
	 * Ejecuta la interfaz para enviar el maestro del programa de nuevas
	 * 
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroProgramaNuevas(Map params);

	/**
	 * Ejecuta la intefaz para enviar los cupones premios kits del programa de
	 * nuevas
	 * 
	 * @param params
	 */
	public void executeInterfazDATEnviarCuponesPremiosKitsProgramaNuevas(
			Map params);

	/**
	 * Ejecuta la interfaz para enviar cupones premios kits CUV del programa de
	 * nuevas
	 * 
	 * @param params
	 */
	public void executeInterfazDATEnviarCuponesPremiosKitsCuvProgramaNuevas(
			Map params);

	/**
	 * Ejecuta la interfaz para enviar las unidades administrativas del programa
	 * de nuevas
	 * 
	 * @param params
	 */
	public void executeInterfazDATEnviarUnidadesAdministrativasProgramaNuevas(
			Map params);

	/**
	 * Ejecuta la interfaz para enviar los cupones usados del programa de nuevas
	 * 
	 * @param params
	 */
	public void executeInterfazDATEnviarCuponesUsadosProgramaNuevas(Map params);

	/* FIN JJ PER-SiCC-2012-0345 */

	/* INI JR PER-SiCC-2012-0444 */
	/**
	 * Recepciona la OC de Flexipago
	 * 
	 * @param criteria
	 */
	public void executeInterfazOCRRecepcionarConsolidadoOCSFlexipago(
			Map criteria);

	/* FIN JR PER-SiCC-2012-0444 */

	/* INI JP PER-SiCC-2012-0445 */
	/**
	 * @param criteria
	 *            Ejecuta la interfaz Facturas Electronicas para enviar las
	 *            cabeceras
	 */
	public void executeInterfazIMPEnviarFacturasCabecera(Map criteria);

	/**
	 * @param criteria
	 *            Ejecuta la interfaz Facturas Electronicas para enviar los
	 *            detalles
	 */
	public void executeInterfazIMPEnviarFacturasDetalle(Map criteria);

	/**
	 * @param criteria
	 *            Ejecuta la interfaz Facturas Electronicas para enviar las
	 *            cabeceras de las notas de credito
	 */
	public void executeInterfazIMPEnviarNotasCreditoCabecera(Map criteria);

	/**
	 * @param criteria
	 *            Ejecuta la interfaz Facturas Electronicas para enviar los
	 *            detalles de las notas de credito
	 */
	public void executeInterfazIMPEnviarNotasCreditoDetalle(Map criteria);

	/**
	 * Ejecuta la interfaz Facturas Electronicas para enviar los productos
	 * 
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarProductos(Map criteria);

	/* FIN JP PER-SiCC-2012-0445 */

	public void executeInterfazDATEnviarCicloEjecucion(Map criteria);

	public void executeInterfazDATEnviarEjecucionNivel(Map criteria);

	public void executeInterfazDATEnviarResultadoEjecucion(Map criteria);

	public Integer getActualDireccionBR(Map criteria);

	/* INI JJ PER-SiCC-2012-0388 */
	/**
	 * Obtiene el contador de cierre de Zona por campaña y oid de zona,si es
	 * mayor a 0 la zona cerro sino no cerro
	 * 
	 * @param codigoPeriodo
	 * @param oidZona
	 * @return
	 */
	public Integer getContCierreZonaByCodigoPeriodoOidZona(
			String codigoPeriodo, Integer oidZona);
	/* FIN JJ PER-SiCC-2012-0388 */
	
	/* INI SA PER-SiCC-2012-1120 */
	/**
	 * Obtiene el contador de cierre de Region por campaña y oid de region,si es
	 * mayor a 0 la region cerro sino no cerro
	 * 
	 * @param codigoPeriodo
	 * @param oidRegion
	 * @return
	 */
	public Integer getContCierreRegionByCodigoPeriodoOidRegion(String codigoPeriodo, Integer oidRegion);
	/* FIN AS PER-SiCC-2012-1120 */
	
	/**
	 * Obtiene el numero de registros asociados al archivo a cargar(Boletas de Venta).
	 * @param criteria
	 * @return
	 */
	public Integer getContRegistrosAsociadosCargaArchivosBolVent(Map criteria);
	
	/**
	 * Obtiene los archivos pendientes de procesar(Boletas de Venta).
	 * @param criteria
	 * @return
	 */
	public List getArchivosPendientesBolVent(Map criteria);
	
	/**
	 * Obtiene el numero de registros asociados al archivo a cargar(Notas de Credito).
	 * @param criteria
	 * @return
	 */
	public Integer getContRegistrosAsociadosCargaArchivosNotasCred(Map criteria);
	
	/**
	 * Obtiene los archivos pendientes de procesar(Notas de Credito).
	 * @param criteria
	 * @return
	 */
	public List getArchivosPendientesNotasCred(Map criteria);
	
	/**
	 * Obtiene una lista de Regiones sin cierre de facturacin
	 * @param criteria
	 * @return
	 */
	public List getRegionesByPaisMarcaCanalPeriodo(Map criteria);

	/**
	 * Obtiene lista de Concursos Faltantes por Marca y/o Canal
	 * @param params
	 * @return
	 */
	public List getConcursosFaltantesByPaisMarcaCanalDetalle(Map criteria);
	
	/**
	 * Obtiene lista de Consultoras por Reporte
	 * @param criteria
	 * @return
	 */
	public List getEstadosConsultoraByReporte(Map criteria);

	/**
	 * Obtiene la maxima fecha de la tabla temporal IMP_TMP_FELEC_CABEC
	 * @author sguerra
	 * @return
	 */
	public String getFechaFacturacion();

	/**
	 * Validacion para pagos en linea
	 * @param detalle
	 * @return
	 */
	public boolean existePercepcionesDetalle(MovimientosBancariosDetalle detalle);

	/**
	 * Actualiza indicador repetido de pagos en linea
	 * @param detalle
	 */
	public void updateIndRepePercepcionesDetalle(MovimientosBancariosDetalle detalle);
	
	/**
	 * @return
	 */
	public List getListaEstadosIncentivos();
	
	
	/**
	 * Devuelve Periodo y Fecha
	 * @param criteria
	 * @return
	 */
	public List getPeriodoFechaProceso(Map criteria);
	
	/**
	 * Devuelve Lista de Asientos
	 * @return
	 */
	public List getListaAsientos();
	
	 /**
	 * Devuelve Lista de Regiones a Cerrar
	 * @param codigoPais
	 * @return
	 */
	public List getRegionesACerrar(Map params);

	/**
	 * Envia la lista de consultoras bloqueadas y desbloqueadas
	 * @param params
	 */
	public void executeInterfazMAEEnviarConsultorasBloqueadasDesbloquedas(Map params);
	
	/**
	 * Actualiza parametros
	 * @param params
	 */
	public void updateInterfazCCCCargasDeudasWeb(Map params);
	
	/**
	 * Devuelve Lista de Pagos Lec
	 * @return
	 */
	public List getListaPagosLec();

	/**
	 * Devuelve un listado de los tipos de reemplazo
	 * @return
	 */
	public List getTipoReemplazo();
	
	/**
	 * Obtiene los tipos de documentos de pago
	 * @return
	 */
	public List getTipoDocumentosPago();

	/**
	 * Obtiene el numero de solicitudes de cabecera de la tabla int_tmp_gener_solic_cabec
	 * @return
	 */
	public String getExisteSolicitudesCabecera();
	
	
	/**
	 * Devuelve si existe conferencia
	 * @param criteria
	 * @return
	 */
	public int getExisteAsistenciaConferencia(HashMap criteria);
	
	/**
	 * Actualiza asistencia conferencia
	 * @param queryParams
	 */
	public void updateAsistenciaConferencia(Map queryParams);
	
	/**
	 * Inserta asistencia conferencia
	 * @param params
	 */
	public void insertAsistenciaConferencia(Map params);
	
	/**
	 * Ejecuta Evaluar Exigencias Descuentos
	 * @param params
	 */
	public void executeEvaluarExigenciasDescuentos(Map params) ;

	/**
	 * Obtiene la lista de periodos que envuelven a fecha de facturacion
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPeriodosDefaultByPMCF(Map criteria);

	/**
	 * Obtiene el oidCliente de un determinado codigo Cliente
	 *
	 * @param criteria
	 * @return
	 */
	public String getOidClienteByCodigoCliente(Map criteria);

	/**
	 * Actualiza Indicador Paquete Documentario
	 * 
	 * @param params
	 */
	public void updateIndicadorPaqueteDocumentario(Map params);
	
	/**
	 * Ejecuta Reestructurar Unidades Administrativas
	 * @param params
	 */
	public void executeReestructurarUnidadAdministrativa(Map params) ;
	
	public String getDescripcionTipoClienteByCodigoTipoClienteCodigoIdioma(Map criteria);
	public String getDescripcionSubTipoClienteByCriteria(Map criteria);
	public String getDescripcionTipoClasificacionByCriteria(Map criteria);
	public String getClasificacionByCriteria(Map criteria); 
	public List getTiposClientesByCodigoISO01(String codigo);
	
	
	/**
	 * Envia informacion SMS para el envio de la primera boleta de recojo
	 * @param params
	 */
	public void executeEnviarSMSBoletaPrimerRecojo(Map params);
	
	/**
	 * Envia informacion SMS para el envio de la segunda boleta de recojo
	 * @param params
	 */
	public void executeEnviarSMSBoletaSegundoRecojo(Map params);
	
	/**
	 * Envia informacion SMS para el envio de la segunda boleta de recojo no exitoso
	 * @param params
	 */
	public void executeEnviarSMSBoletaSegundoRecojoNoExitoso(Map params);

	/**
	 * Elimina los datos de lideres en la tabla ped_descr_produ
	 */
	public void deleteInterfazLLIRecepcionarCargaProductosPlanit();
	
	/**
	 * Carga la lista de lideres a la tabla ped_descr_produ
	 * @param criterio
	 */
	public void insertInterfazLLIRecepcionarCargaProductosPlanit(Map params);
	

	/* INI FRAMRWORK NSSICC PRUEBAS TRANSACCION */
    public void insertHistorico3(Historico historico, Usuario usuario);
	
	public void updateHistorico3(Historico historico, Usuario usuario);
	 /* FIN FRAMRWORK NSSICC PRUEBAS TRANSACCION */
	
	/**
	 * Genera Informacion de Atenciones Inteligentes
	 * 
	 * @param params
	 */
	public void executeGenerarAtencionesInteligentes(Map params);
	
	/**
	 * Elimina Informacion de Atenciones Inteligentes
	 * 
	 * @param params
	 */
	public void executeEliminarAtencionesInteligentes(Map params);
	
	/**
	 * Ejecuta Eliminar Despachos Consultoras No Constantes
	 * @param params
	 */
	public void executeEliminarDespachosConsultorasNoConstantes(Map params) ;

	/**
	 * Obtiene Error de un Proceso Batch
	 * 
	 * @param params
	 * @return
	 */
	public String getErrorProcesoBatchSICC(String codigoProcesoBatch);

	/**
	 * Envía el resumen diario de percepciones a sunat
	 * @param params
	 */
	public void executeInterfazEnviarResumenDiarioPercepcionesSunat(Map params);
	
}
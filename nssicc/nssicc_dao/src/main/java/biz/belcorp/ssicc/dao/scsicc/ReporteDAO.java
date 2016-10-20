/**
 * 
 */
package biz.belcorp.ssicc.dao.scsicc;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.sisicc.model.BaseBlob;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ReporteDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Marco Antonio Agurto Jimenez</a>
 * 
 */

public interface ReporteDAO extends DAO {

	public List getMarcas();

	/**
	 * Obtiene un listado de todas los perídos disponibles en la tabla
	 * CRA_RANGO_PERIO@SICC
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
	 * Obtiene un listado de todos los canales disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getTiposVinculosByPais(String codigoPais);

	/**
	 * Obtiene la lista de Tipos de Concursos cuyo rango de duración incluya al
	 * periodo seleccionado
	 * 
	 * @param params
	 *            Parametros para obtener la data
	 * @return Lista de objetos Map, poblados
	 */
	public List getRangosPeriodo();

	/**
	 * Obtiene un listado de todos los canales disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */

	/**
	 * Ejecuta procedimiento previo a la generacion del Reporte de Facturas
	 * Pendientes por Seccion
	 * 
	 * @param criteria
	 */
	public void executeFacturaPendienteSeccion(Map criteria);
	
	/**
	 * Ejecuta procedimiento previo a la generacion del Reporte Cuadre SAPFI	
	 * 
	 * @param criteria
	 */
	public void executeCuadreSAPFI(Map criteria);
	
	/**
	 * Ejecuta procedimiento previo a la generacion del Reporte de Control de
	 * Asistencia a Conferencia de Ventas
	 * 
	 * @param criteria
	 */
	public void executeControlAsistencia(Map criteria);

	/**
	 * Genera Data para Reporte de Control de Asistencia Triunfadoras por Pantalla
	 * @param criteria
	 */
	public void executeControlAsistenciaTriunfadoras(Map criteria);

	/**
	 * Ejecuta procedimiento previo a la generacion del Reporte de Estado de Cta
	 * Cte a Vendedoras
	 * 
	 * @param criteria
	 */
	public void executeEstadoCtaCteVendedora(Map criteria);

	/**
	 * Ejecuta procedimiento previo a la generacion del Reporte de Detalle de
	 * Cta Cte
	 * 
	 * @param criteria
	 */
	public void executeDetalleCtaCte(Map criteria);

	/**
	 * Ejecuta procedimiento previo a la generacion del Reporte de Facturas
	 * Pendientes por Campaña
	 * 
	 * @param criteria
	 */
	public void executeFacturaPendienteCampana(Map criteria);

	/**
	 * Ejecuta procedimiento previo a la generacion del Reporte de Comision por
	 * Ingresos
	 * 
	 * @param criteria
	 */
	public void executeComisionIngreso(Map criteria);

	/**
	 * Ejecuta procedimiento previo a la generacion del Reporte de Comision por
	 * Ingresos
	 * 
	 * @param criteria
	 */
	public void executeComisionComercializacion(Map criteria);

	/**
	 * Ejecuta procedimiento previo a la generacion del Reporte de Comision por
	 * Recuperacion
	 * 
	 * @param criteria
	 */
	public void executeComisionRecuperacion(Map criteria);

	/**
	 * @param method
	 * @param criteria
	 */
	public void deleteTaleReporte(String method, Map criteria);

	/**
	 * @param method
	 * @param criteria
	 */
	public void insertTableReportesSql(String method, Map criteria);

	/**
	 * @param criteria
	 */
	public void executeBaseRecuperacionCampana(Map criteria);

	/**
	 * @param criteria
	 */
	public void executeVentaVariable(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getListaPeriodosByBasCtrlFact(Map criteria);

	/**
	 * @param getLista
	 * @param criteria
	 * @return
	 */
	public List getListaGenerico(String getLista, Map criteria);

	/**
	 * @param getLista
	 * @param criteria
	 * @return
	 */
	public List getListaReporte(String getLista, Map criteria);

	/**
	 * @param procedure
	 * @param criteria
	 */
	public void executeReporteSQL(String procedure, Map criteria);
	
	/**
	 * Genera Reporte PED Seguimiento Consultora en formato CSV
	 * @param criteria
	 */
	public void executeReportePEDSeguimientoConsultoraCSV(Map criteria);
	
	public Map getDatosConcursosByNumeroConcurso(Map criteria);

	/**
	 * Devuelve parametros del Reporte General
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getParametrosReporteGeneral(Map criteria);

	/**
	 * Devuelve Parametros del reporte en ejecucion
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getParametrosReporte(Map criteria);

	/**
	 * Borra Log de Reportes grabados en Disco
	 * 
	 * @param criteria
	 */
	public void deleteLogReporteDisco(Map criteria);

	/**
	 * Devuelve Lista con el log de los reportes generados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getLogReporteDisco(Map criteria);

	/**
	 * Inserta Registro de Log de Reportes grabados en Disco
	 * 
	 * @param criteria
	 */
	public void insertLogReporteDisco(Map criteria);

	/**
	 * @param string
	 * @param criteria
	 * @return
	 */
	public String getOidString(String string, Map criteria);

	/**
	 * @param string
	 * @param criteria
	 * @return
	 */
	public String getOidConcurso(String string, Map criteria);

	/**
	 * @param string
	 * @param criteriaOperacion
	 * @return
	 */
	public String getStringGenerico(String string, Map criteriaOperacion);

	/**
	 * @param criteria
	 */
	public void executeComisionRecuperacionGZona(Map criteria);

	/**
	 * @param criteria
	 */
	public void devuelvePorcentajeTramoComision(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getConsultaResponsablesUA(Map criteria);

	/**
	 * Ejecuta proceso previo al Reporte de Control de Cliente (Percepciones)
	 * 
	 * @param criteria
	 */
	public void executePrevioReporteControlCliente(Map criteria);

	/**
	 * Devuelve Logo de la empresa
	 * 
	 * @param criteria
	 * @return
	 */
	public BaseBlob getLogoEmpresaEducacion(Map criteria);

	/**
	 * Genera Data para Reporte de Analisis General de Area
	 * 
	 * @param criteria
	 */
	public void executeAnalisisArea(Map criteria);

	public String getSecuenciaNextValue();

	/**
	 * Genera lista de codigos de periodos corporativos segun tipo: CM/Campaa
	 * SE/Semana
	 * 
	 * @param tipoPeriodo
	 * @return
	 */
	public List getPeriodosCorporativosPorTipo(String tipoPeriodo);

	public void executeSeguimientoCalificacionCampana(Map criteria);

	public List getCampanasTramo(Map criteria);

	public List getPeriodosSeguimientoCalificacionCampana(Map criteria);

	public void executeResumenEvaluacionSeccion(Map criteria);

	public void executeResumenEvaluacionZona(Map criteria);

	/**
	 * Ejecuta proceso previo al Reporte de Control de Cliente (Percepciones)
	 * para LBel
	 * 
	 * @param criteria
	 */
	public void executePrevioReporteControlClienteLbel(Map criteria);

	public List devuelvePeriodosporFechas(Map criteria);

	public List getUnidadNegocio();

	public Map getCabeceraSACFaltante(Map params);

	/**
	 * @return
	 */
	public List getCodUsuarios();

	public List getResultadoList();

	public List getMotivoList();

	/**
	 * Se procesa y recupera los concursos seleccionados, agrupados por premio,
	 * obteniendo la cantidad de anulaciones, devoluciones, trueques y canjes
	 * 
	 * @param criteria
	 */
	public void executeIndicadorGestionIncentivosCDR(Map criteria);

	/**
	 * Se recupera los tipos de programa de los concursos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTiposPrograma(Map criteria);

	/**
	 * Se procesa y recupera los concursos seleccionados, agrupados por premio y
	 * programa concurso, obteniendo la cantidad de premios, valor neto, igv e
	 * importe total
	 * 
	 * @param criteria
	 */
	public void executePremiosEntregados(Map criteria);

	/**
	 * Se obtienes las zonas que facturan segun la fecha pasada por parametro,
	 * por defecta el dia de Hoy
	 * 
	 * @param criteria
	 * @return
	 */
	public List getZonasFacturanHoy(Map criteria);

	/**
	 * Obtiene la lista de los Tipo de ofertas
	 * 
	 * @return
	 */
	public List getTipoOfertas();

	/**
	 * Obtiene el valor de un parametro de reporte general
	 * 
	 * @param criteria
	 * @return
	 */
	public String getParamReporGener(Map criteria);

	/**
	 * Se recupera los indicadores SAC
	 * 
	 * @param criteria
	 * @return
	 */
	public List getIndicadoresSAC(Map criteria);

	/**
	 * Obtiene el siguiente Valor de la Secuencia pasada como parametro
	 * 
	 * @param method
	 * @return
	 */
	public String getSecuenciaNextValue(String method);

	/**
	 * Se procesa y calcula los diferentes Indicadores SAC seleccionados
	 * 
	 * @param criteria
	 */
	public void executeIndicadoresSAC(Map criteria);

	/**
	 * Devulve el map de tramo anterior y campanhas actuales
	 * 
	 * @param params
	 * @return
	 */
	public Map getPeriodosResultadosEvaluacionEjecutiva(
			Map<String, String> params);

	/**
	 * @param criteria
	 *            Metodo que carga la tabla para generar el reporte de FNA Soles
	 */
	public void executeCargaReporteFNASoles(Map criteria);

	/**
	 * Obtiene el indicado Caja/Bolsa de un determinado producto
	 * 
	 * @param criteria
	 * @return
	 */
	public List getIndicadorCajaBolsaProducto(Map criteria);

	/**
	 * Devuelve data en caso haya un detalle del reporte que no cumpla con la condicion
	 * @param criteria
	 * @return
	 */
	public String getMensajeReporteRecuperaciones(Map criteria);
	
	/**
	 * Devuelve data en caso haya un detalle del reporte que no cumpla con la condicion
	 * @param criteria
	 * @return
	 */
	public String getMensajeReporteReemplazos(Map criteria);
	
	/**
	 * Devuelve data en caso haya un detalle del reporte que no cumpla con la condicion
	 * @param criteria
	 * @return
	 */
	public String getMensajeReporteGuiaProductos(Map criteria);
	
	/**
	 * Devuelve la lista de Regiones para generar los reportes
	 * @return
	 */
	public List getListaZonasReporteSACAsistencia();
	
	/**
	 * Devuelve la lista de Regiones para generar los reportes
	 * @return
	 */
	public List getListaZonasReporteSACActivasSaldo();
	
	/**
	 * Devuelve la lista de estado de cliente y su equivalencia para el reporte
	 * de pedidos digitados por zona
	 * @return
	 */
	public List getEstadoEquivalenciaCliente();
	
	/**
	 * Devuelve la cantidad de datos encontrados para una region y sus zonas
	 * @return
	 */
	public String getContadorListaRegionesZonas(Map criteria);
	
	/**
	 * Devuelve lisa para cargar el combo de tipo de CDR
	 * @return
	 */
	public List getTiposCDR();
	
	/**
	 * Devuelve lisa para cargar el combo de tipo de Atenciones
	 * @return
	 */
	public List getTiposAtencion();

	/**
	 * Devuelve las fechas inicial y final de un periodo
	 * @param codPeriodo
	 * @return
	 */
	public List getRangoFechaPeriodo(String codPeriodo);
	
	/**
	 * Devuelve maximo valor de oferta de tabla BAS_PAIS
	 * @param criteria
	 * @return
	 */
	public String getMaximoValorOferta(Map criteria); 
	
	/**
	 * Devuelve el numero de registros que pasan el valor  maximo de oferta
	 * @param criteria
	 * @return
	 */
	public String getMensajexValorMaximoOferta(Map criteria);
	
	/**
	 * Se genera la data para el reporte de Consolidados Atencion CDR
	 * @param criteria
	 */
	public void executeSTOConsolidadoAtencionCDR(Map criteria);
	
	
	/**
	 * Retorna el oid de clasificacion de la tabla MAE_CLASI
	 * @param criteria
	 * @return
	 */
	public String getOidClasificacion(Map criteria);

	/**
	 * Ejecuta el Store que generara la data para el reporte de Proyecto de Premios de Concursos
	 * @param params
	 */
	public void executeReporteProyeccionesPremioConcurso(Map params);

	/**
	 * inseta los niveles de concurso seleccionados por pantalla a la tabla temporal
	 * @param params
	 */
	public void insertGloblanTemporaryForNumNivel(Map params);

	/**
	 * Se traen los registros para Estado de Atenciones MAV
	 * @param oidPeriodo
	 * @return
	 */
	public List getEstadoAtencionesMAV(String oidPeriodo);
	
	/**
	 * Se traen los registros para productos que no pasaron pedidos MAV
	 * @param criteria
	 * @return
	 */
	public List getNoPasaronPedidosMAV(Map criteria);
	
	/**
	 * Devuelve lista de Oid de Tipos de Documentos de Facturacion
	 * @param criteria
	 * @return
	 */
	public List getOidFacturacionTipoDocumento(Map criteria);
	
	/**
	 * Se genera la data para el reporte de Pedidos Afectados Detalle FNA
	 * @param criteria
	 */
	public void executeSICPedidosAfectadosDetFNA(Map criteria);
	
	/**
	 * Se traen los oid de tipo de solicitud para el reporte de Consolidado 
	 * Atencion CDR del Modulo STO
	 * @return
	 */
	public List getTiposSolicitudSTO();

	/**
	 * Trae el oid de concursor por numero de concurso
	 * @param codigoConcurso
	 * @return
	 */
	public String getOidConcursoByNumConc(String codigoConcurso);

	
	/**
	 * Se traen la lista de los resultado de chequeo por consultora
	 * @param criteria
	 * @return
	 */
	public List getResultadoChequeoConsultora(Map criteria);
	
	/**
	 * Se obtiene la lista del detalle del resultado de chequeo
	 * @param criteria
	 * @return
	 */
	public List getDetalleResultadoChequeo(Map criteria);
	
	/**
	 * Retorna la lista de avance  de pedido 
	 * @param criteria
	 * @return
	 */
	public List getConsultaInformeAvancePedido(Map criteria);
	
	/**
	 * Retorna la lista de consultoras activas sin pedido 
	 * @param criteria
	 * @return
	 */
	public List getConsultorasActivasSinPedido(Map criteria);

	/**
	 * Retorna fecha de ultima actualizacion de zona sino ha habido retorna vacio 
	 * @param criteria
	 * @return
	 */
	public String getFechaUltimaActualizacionZona(Map criteria);

	/**
	 * Retorna numero de registros
	 * @param criteria
	 * @return
	 */
	public Integer getNumeroRegistros(Map criteria);

	/**
	 * Retora la cunsulta de ocr en un solo campo
	 * @param criteria
	 * @return
	 */
	public List getInformeAvancePedido(Map criteria);
	
	
	   /**
     * Retorna los codigo de venta rechazados para la consultora
	 * @param criteria
     * @return
     * @throws RemoteException
     */
    public List getCodigoVentasRechazados(Map criteria);
    

    
    /**
     * Retorna los detalle de pedidos facturados para la consultora
	 * @param criteria
     * @return
     * @throws RemoteException
     */
    public List getDetallePedidoFacturado(Map criteria);
    
    
    
    /**
     * Retorna el numero de faltantes Anunciados
	 * @param criteria
     * @return
     * @throws RemoteException
     */
    public List getFaltantesAnunciados(Map criteria) ;
    
    /**
     * Genera archivo TXT en Oracle con la informacion de 
     * Reporte Sunat
     * @param criteria
     */
    public Map generarReporteRUVSunatTXT(Map criteria);
    
    
    /**
     * Genera archivo CSV en Oracle con la informacion de 
     * Reporte Sunat
     * @param criteria
     */
    public Map generarReporteRUVSunatCSV(Map criteria);
    
    /**
     * Genera archivo Excel en Oracle con la informacion de
     * @param criteria
     * @return
     */
    public Map generarReporteRUVSunatExcel(Map criteria);
    
    /**
     * Retornar los tipos de bloqueos existentes
     * @return
     */
    public List getTipoBloqueos();
    
    /**
     * Genera archivo TXT en Oracle con la informacion de 
     * Reporte Sunat Errores
     * @param criteria
     */
    public Map generarReporteRUVSunatErrorTXT(Map criteria);
    
    
    /**
     * Genera archivo CSV en Oracle con la informacion de 
     * Reporte Sunat Errores
     * @param criteria
     */
    public Map generarReporteRUVSunatErrorCSV(Map criteria);

    /**
     * Retorna lista del Reporte de Pedidos en GP 1 sin errores  
     * @param criteria
     * @return
     */
    public List getReporteOCRPedidosGP1SinError(Map criteria);
    
    /**
     * Retorna la lista del ReportePRIGenerarSolicitudesPrivilege
     * @param criteria
     * @return
     */
    public List getReportePRIGenerarSolicitudesPrivilege(Map criteria);
    
	/**
	 * @param criteria
	 * @return La secuencia para la tabla Temp de Mapa Anaquel Balanceo Diario
	 */
	public String getObtenerSecuenciaTempMapaAnaquel(Map criteria);
	
	/**
	 * @param criteria
	 * Permite generar el reporte de Mapa Anaqueles Balanceo Diario
	 */
	public void executeGenerarReporteMapaAnaquelBalanceoDiario(Map criteria);	
	
	/**
	 * @param criteria
	 * Elimina los registros de la Tabla Temporal de Generación de Reporte Mapa Anaqueles Balanceo Diario
	 */
	public void eliminarRegistrosTablaTempReporteMapaAnaq(Map criteria);
	
	/**
	 * @param criteria
	 * @return La secuencia para la tabla Temp de Balanceo Línea y Productos
	 */
	public String getObtenerSecuenciaTempBalanceoLinea(Map criteria);
    
	/**
	 * @param criteria
	 * Permite generar el reporte de Balanceo Línea y Productos
	 */
	public void executeGenerarReporteBalanceoLinea(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina los registros de la Tabla Temporal de Generación de Reporte Balanceo de Línea y Productos
	 */
	public void eliminarRegistrosTablaTemporalReporteBalanceoLinea(Map criteria);

	/**
	 * Retorna la campanha activa d ela zona
	 * @param criteriaPeriodo
	 * @return
	 */
	public String getCampanhaActivaByZona(Map criteriaPeriodo);

	/**
	 * ejecuta el proceso retencion de pedidos
	 * @param criteria
	 */
	public void executeRetencionPedidos(Map criteria);

	/**
	 * retorna la lista de retencion de pedidos
	 * @param criteria
	 * @return
	 */
	public List getRetencionPedidos(Map criteria);

	/**
	 * Ejecuta el proceso de pedidos
	 * @param criteria
	 */
	public void executePedidosDigitados(Map criteria);

	/**
	 * retona la lisat de pedidos
	 * @param criteria
	 * @return
	 */
	public List getPedidosDigitados(Map criteria);
	
	/**
	 * Retornar el oid de la region usando como parametros el codigo de pais,marca,canal,region
	 * @param criteria
	 * @return
	 */
	public Integer getOidRegionByPaisMarcaCanal(Map criteria);

	/**
	 * Retornar el oid de la zona usando como parametros el codigo de pais,marca,canal,region,zona
	 * @param criteria
	 * @return
	 */
	public Integer getOidZonaByPaisMarcaCanalRegion(Map criteria);
	
	/**
	 * Retornar el oid de la seccion usando como parametros el codigo de pais,marca,canal,zonay codigo de seccion
	 * @param criteria
	 * @return
	 */
	public Integer getOidSeccionByPaisMarcaCanalZona(Map criteria);

	/**
	 * Retorna las zonas que pertenecen a un grupo de facturacion
	 * @param grupoFacturacion
	 * @return
	 */
	public List getZonasGrupo(String grupoFacturacion);

	/**
	 * Retorna los grupos de facturacion asociados al pais marca y canal
	 * @param params
	 * @return
	 */
	public List getGrupoFacturacion(Map params);

	/**
	 * Retorna las actividades asociados al pais marca y canal
	 * @param params
	 * @return
	 */
	public List getActividad(Map params);
	
	/**
	 * Ejecuta la actualización de Tipo Pedido de la tabla REC_REPOR_OPERA_UNIDA_ADMIN
	 */
	public void executeActualizarTipoPedido();

	/**
	 * Retorna la lista de consultoras activas sin pedido 
	 * @param criteria
	 * @return
	 */	
	public List getConsultorasActivasSinPedidoAct(Map criteria);

	/**
	 * Retorna la consulta de ocr en un solo campo
	 * @param criteria
	 * @return
	 */	
	public List getInformeAvancePedidoAct(Map criteria);
	
	/**
	 * Retorna la lista de solicitudes reclamos
	 * @return
	 */
	public List getTiposSolicitudReclamos();
	
	/**
	 * Obtener oid periodo en base al codigo periodo
	 * @param criteria
	 * @return
	 */
	public int getOidPeriodo(Map criteria);
	
	/**
	 * Retorna la lista de indicadores de caja / bolsa
	 * @return
	 */
	public List getIndicadoresCajaBolsa();

	/**
	 * Obtiene el oid de la actividad
	 * @param params. El metodo espera un oidPais y un codActivdad
	 * @return
	 */
	public String getOidActividad(Map params);
	
	
	/**
	 * @param criteria
	 * Inserta los valores de STO Rechazadas en tabla Global Tempory
	 */
	public void insertTemporalSTOReporteRechazadas(Map criteria);
	
	/**
	 * @param criteria
	 * @return Obtener Secuencia Temporal STO Rechazadas
	 */
	public String getObtenerSecuenciaTempSTORechazadas(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina los registros de la tabla temporal STO Rechazadas 
	 */
	public void eliminarRegistrosTablaTempSTORechazadas(Map criteria);
	
	/**
	 * Capturar el valor tope para validar el cuadro Diferencia del Reporte Comparativo Retail vs SICC
	 * @param criteria
	 * @return
	 */
	public Double getValidarMontoTope(Map criteria);
	
	/**
	 * @param criteria
	 * @return La secuencia para la tabla temporal de Reporte Cierre de Costos Ventas
	 */
	public String getObtenerSecuenciaTempCOSCierreCostoVentas(Map criteria);
	
	/**
	 * @param criteria
	 * Permite Generar el reporte Cierre de Costo - Ventas en la tabla temporal
	 */
	public void executeGenerarReporteCierreCostoVentas(Map criteria);	
	
	/**
	 * @param criteria
	 * Elimina los registros de la tabla temporal de Cierre de Costos Ventas
	 */
	public void eliminarRegistrosTablaTempReporteCierreCostoVentas(Map criteria);
	
	/**
	 * @param criteria
	 * @return La secuencia para la tabla temporal de Reporte Cierre de Costos Devoluciones
	 */
	public String getObtenerSecuenciaTempCOSCierreCostoDevoluciones(Map criteria);
	
	/**
	 * @param criteria
	 * Permite Generar el reporte Cierre de Costo - Devoluciones en la tabla temporal
	 */
	public void executeGenerarReporteCierreCostoDevoluciones(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina los registros de la tabla temporal de Cierre de Costos Devoluciones
	 */
	public void eliminarRegistrosTablaTempReporteCierreCostoDevoluciones(Map criteria);

	/**
	 * Retorna los pedidos ocr
	 * @param criteria
	 * @return
	 */
	public List getProcesoInformeAvancePedido(Map criteria);

	/**
	 * Retorna los pedidos ocr
	 * @param criteria
	 * @return
	 */
	public List getProcesoInformeAvancePedidoAct(Map criteria);

	/**
	 * Genera archivo CSV en Oracle con la informacion de Libro de Ventas Mensual
	 * @param criteria
	 * @return
	 */
	public Map generarReporteRUVLibroVentasMensualCSV(Map criteria);
	
	/**
	 * Genera archivo CSV en Oracle con la informacion de Detalle NC y Marca UN (Bolivia)
	 * @param criteria
	 * @return
	 */
	public Map generarReporteVENDetalleNCMarcaUNCSV(Map criteria);
	
	/**
	 * Genera archivo CSV en Oracle con la informacion de Detalle ND y Marca UN (Bolivia)
	 * @param criteria
	 * @return
	 */
	public Map generarReporteVENDetalleNDebitoMarcaUNCSV(Map criteria);
	
	/**
	 * Genera archivo CSV en Oracle con la informacion de Cabeceras Facturas Anuladas (Bolivia)
	 * @param criteria
	 * @return
	 */
	public Map generarReporteVENCabecerasFacturasAnuladasCSV(Map criteria);
	
	/**
	 * Genera archivo CSV en Oracle con la informacion de Detalle de Productos Atendidos x UN (Bolivia)
	 * @param criteria
	 * @return
	 */
	public Map generarReporteVENDetalleProductosAtendidosUNCSV(Map criteria);
	
	
	/**
	 * Genera archivo CSV en Oracle con la informacion de Reporte Pedidos Bonificacion
	 * @param criteria
	 * @return
	 */
	public Map generarReportePEDBonificacionCSV(Map criteria);

	/**
	 * Obtiene el titulo para el reporte por Pais marca
	 * @param criteria
	 * @return
	 */
	public String getTituloReportePaisMarca(Map criteria);
	
	/**
	 * Se lista los periodos que existan en un concurso seleccionado
	 * @param criteria
	 * @return
	 */
	public List getLIDValidaPeriodosConcurso(Map criteria);
	
	/**
	 * Se lista las regiones que existan en el rango de periodos seleccionados
	 * @param criteria
	 * @return
	 */
	public List getLIDRegion(Map criteria);
	
	/**
	 * Se lista las zonas de las regiones y rango de periodos seleccionados
	 * @param criteria
	 * @return
	 */
	public List getLIDZona(Map criteria);

	/**
	 * Se lista las secciones de las zonas, regiones y rango de periodos seleccionados
	 * @param criteria
	 * @return
	 */
	public List getLIDSeccion(Map criteria);
	
	/**
	 * @param criteriaParam
	 * @return
	 */
	public List getParametroGenericoSistemaMail(Map criteriaParam);
	
	/**
	 * @return
	 * Lista los tipos de documentos
	 */
	public List getTipoDocumentoList();
	
	/**
	 * @return
	 * El OidTipoDocumento
	 */
	public String getOidTipoDocumento(Map criteria);
	
	/**
	 * @param criteria
	 * @return Secuencia para Tabla temporal SAP Descuento Comercial
	 */
	public String getObtenerSecuenciaTempDesctoComercial(Map criteria);

	/**
	 * @param criteria
	 * Ejecuta el proceso de insercion de datos en la tabla temporal
	 */
	public void executeGenerarReporteSAPFiDescuentoComercial(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTempReporteDescuentoComercial(Map criteria);
	
	/**
	 * @param criteria
	 * @return Secuencia de tabla temporal Descuento Volumen
	 */
	public String getObtenerSecTempDsctoVol(Map criteria);
	
	/**
	 * @param criteria
	 * Ejecuta el proceso de insercion de datos en la tabla temporal
	 */
	public void executeGenerarReporteSAPFiDescuentoVolumen(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTempReporteDescuentoVolumen(Map criteria);
	
	/**
	 * @param criteria
	 * @return Oid Secuencia de Tabla Temporal
	 */
	public String getObtenerSecuenciaTempVentaLinea(Map criteria);
	
	/**
	 * @param criteria
	 * Ejecuta el proceso de insercion de datos en la tabla temporal
	 */
	public void executeGenerarReporteSAPFiVentaLinea(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTempReporteVentaLinea(Map criteria);
	
	/**
	 * @param criteria
	 * @return Oid Secuencia de Tabla Temporal
	 */
	public String getObtenerSecuenciaTempOperaReclam(Map criteria);
	
	/**
	 * @param criteria
	 * Ejecuta el proceso de insercion de datos en la tabla temporal
	 */
	public void executeGenerarReporteRECOperaReclaPedidos(Map criteria);

	/**
	 * @param criteria
	 * Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTemporalReporOperaReclaPedidos(Map criteria);
	
	/**
	 * @return
	 * La lista de Tipos de Reportes
	 */
	public List getTipoReporteList();
	
	/**
	 * La lista de Tipos de Reportes
	 * @param criteria
	 * @return
	 */
	public List getTipoReporteList(Map criteria);
	
	/**
	 * @param criteria
	 * Ejecuta el proceso de generar Reporte MAE consultoras bloqueadas y desbloqueadas
	 */
	public void executeGenerarReporteMAEConsultoraBloquedasDesbloq(Map criteria);
	
	/**
	 * @return
	 * Retorna el Id Secuencia de Tabla Temporal
	 */
	public String getObtenerSecuenciaTempConsultBloqDesb();
	
	/**
	 * Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTemporalReporConsultBloqDesb();
	
	/**
	 * Indica el tipo de plantilla de concurso
	 * @param criteria
	 * @return
	 */
	public String getTipoPlantilla(Map criteria);
	
	/**
	 * La lista de almacenes
	 * @param criteria
	 * @return
	 */
	public List getListaAlmacen(Map criteria);
	
	public List getOrigenSTOByTipoDocumento(Map params);
	
	public List getTiposOperaMultipleByOperaxCodigoOid(Map params);

	/**
	 * RETORNA LA LISTA DE CONTROL ASISTENCIA
	 * @param params
	 * @return
	 */
	public List getControlAsistencia(Map params);

	/**
	 * retona el oid proceso para la generacion de reporte
	 * @return
	 */
	public BigDecimal getOidProcesoControlAsistencia();

	/**
	 * retona el oid proceso para la generacion de reporte Control Asistencia Triunfadoras
	 * @return
	 */
	public BigDecimal getOidProcesoControlAsistenciaTriunfadoras();
	

	public String getOidPaisByCodigoPais(Map criteria);
	
	/**
	 * @param criteria
	 * Ejecuta el proceso de generar Reporte MAE Consultoras Nuevas Pedidos, Activas e Inactivas
	 */
	public void executeGenerarReporteMAEConsultorasPedidos(Map criteria);

	/**
	 * retorna detalle de pedido no facturado
	 * @param criteria
	 * @return
	 */
	public List getDetallePedidoNoFacturado(Map criteria);
	
	/* INI SA RCR PER-SiCC-2013-0003 */
	/**
	 * Retornar la diferencia de periodos de un determinado rango
	 * @param criteria
	 * @return
	 */
	public Integer getDiferenciaPeriodos(Map criteria);
	/* FIN SA RCR PER-SiCC-2013-0003 */

	public void executeComisionesCalculadasPorRegion(Map criteria);
	
	public void executeCalcularComisionVentaNetaEfectiva(Map criteria);
	
	public void executeCalcularVentaZona(Map criteria);
	
	public List getTipoReporte();
	
	public List getTipoMav();
	
	public List getActividadByTipoMAV(String codigoTipoMav);
	
	public List getTipoOfertaByTipoMAVActividad(Map criteria);
	
	public List getTipoOferta();
	
	/**
	 * Ejecuta el proceso de Saldo de Campaa generado 
	 * @param criteria
	 */
	public void executeCargarTablasReporteCCCDeudorasConMasUnaCampanha(Map criteria);
	
	/**
	 * Ejecuta el proceso de Eliminacin Lgica de la parametra de Saldo Campaa
	 * @param criteria
	 */
	public void executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanha(Map criteria);
	
	/**
	 * Ejecuta el proceso de Eliminacin Lgica de la parametra de Saldo Campaa
	 * @param criteria
	 */
	public void executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanhaArray(Map criteria);
	
	/**
	 * Genera la data para el reporte de Primeros Pedidos con Deuda
	 * @param map
	 */
	public void executeReporteCCCPrimPedDeud(Map map);
	
	
	/**
	 * Genera la data para el reporte de Primeros y Segundos Pedidos con Deuda
	 * @param map
	 */
	public void executeReporteCCCPrimSegPedDeud(Map map);
	
	
	public void deleteTableReporteCCCPrimPedDeud(Map criteria);
	
	public void deleteTableReporteCCCPrimSegPedDeud(Map criteria);
	
	/**
	 * Ejecuta el proceso de Consultoras a Chequear
	 * @param criteria
	 */
	public void executeCargaTemporalReportePEDConsultorasChequear(Map criteria);
	
	/**
	 * Ejecuta el proceso de Eliminacin en la Tabla Temporal de Reportes de Consultoras a Chequear
	 * @param criteria
	 */
	public void executeLimpiarTablaTemporalReportePEDConsultorasChequear(Map criteria);
	
	/**
	 * Lanza el stored que carga las tablas para el reporte
	 * @param params
	 */
	public void executeCargarTablasReporteCCCDiasCartera(Map params);

	/**
	 * Limpia las tablas que fueron cargadas en el proceso executeCargarTablasReporteCCCDiasCartera
	 * @param params
	 */
	public void deleteTablasReporteCCCDiasCartera(Map params);

	/**
	 * Carga los reportes de nueva y/o rechazadas en tabla temporal para su exportacin a csv
	 * @param criteria
	 * @return
	 */
	public void executeCargaTemporalReporteMAEConsultorasPedidos(Map criteria);
	
	/**
	 * Genera el archivo CSV desde la tabla temporal nueva y/o rechazadas
	 * @param parameterMap
	 * @return
	 */
	public Map generarReporteMAEConsultorasPedidosCSV(Map criteria);
	
	/**
	 * Limpia la tabla temporal de nueva y/o rechazadas para el id proceso generado
	 * @param criteria
	 */
	public void deleteTemporalReporteMAEConsultorasPedidos(Map criteria);
	
	/**
	 * Limpia la tabla temporal de Cuadre SAP
	 * @param criteria
	 */
	public void deleteTemporalReporteRECCuadreSAP(Map params);
	
	/**
	 * Inserta registros en la tabla temporal de Cuadre SAP en Batch
	 * @param criteria
	 */
	public void insertBatchReporteRECCuadreSAP(final List list) throws Exception;
	
	/**
	 * Ejecuta proceso de consolidacion de informacion para el Reporte de Cuadre SAP
	 * @param criteria
	 */
	public void executeConsolidacionReporteRECCuadreSAP(Map params);

	/**
	 * Proceso que pobla las tablas para generar el reporte
	 * @param params
	 */
	public void executeReportePEDExportarDemandaAnticipada(Map params);

	/**
	 * @author Sebastian Guerra
	 * @param params
	 * @return
	 */
	public List getZonasRegionPEJ(Map params);
	
	/**
	 * @author Sebastian Guerra
	 * @param params
	 * @return
	 */
	public List getSeccionesZonaPEJ(Map params);
	
	/**
	 * @param params
	 * @return
	 */
	public List getSeccionesZonaPEJTodos(Map params);

   	
	
	/**
	 * @param criteria
	 * Metodo que carga la tabla para generar el reporte de Centro de Acopio Facturado
	 */
	public void executeCargaReporteCentroAcopioFacturado(Map params);
	

	
	/**
	 * @param criteria
	 * Metodo que registra todos los datos obtenidos en una temportal.
	 *
	 * */
	public void executeReportePedidosEnviados(Map params);
	
	/**
	 * @param criteria
	 * Metodo que registra todos los datos obtenidos en una temportal.
	 * 
	 * */
	public void executeReportePosiblesCandidatas(Map params);

	/**
	 * Carga data en tabla temporal para su visualizacion en el Reporte MAV de Atencion por Campaa
	 * @param criteria
	 */
	public void executeReporteMAVAtencionCampanha(Map criteria);
	
	/**
	 * Verificamos si es primer dia de facturacion de un determinado periodo
	 *
	 * @param criteria
	 * @return
	 */
	public boolean esPrimerDiaFacturacion(String codigoPeriodo);
	
	/**
	 * Obtenemos la cantidad de pedidos facturados de un determinado periodo
	 *
	 * @param criteria
	 * @return
	 */
	public String getPedidosAcumulados(String codigoPeriodo);

	/**
	 * @param criteria
	 * Metodo que carga la tabla para generar el reporte de simulacion de faltantes
	 */
	public void executeCargaReporteSimulacionFaltantes(Map params);

	/**
	 * @param params
	 */
	public void executeReportePedidosRecibidos(Map params);

	/**
	 * @param criteria
	 * Metodo donde se registra en una tabla temporal codigoZona y codigoRegion.
	 */
	public void insertReporteGttFacturaDetalle(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que devuelve la cantidad de regiones by zona
	 */
	public int getCountRegionByCodZona(Map criteria);
	
	/**
	 * @param criteria
	 * Genera la data para el Reporte de Facturacion Detalle SAC atraves de 
	 * lo sleccionado(Algunas zonas y algunas regiones) y todos.
	 */
	public void executeReporteFacturaDetalle1(Map params);
	
	/**
	 * @param criteria
	 * Genera la data para el Reporte de Facturacion Detalle SAC atraves
       de lo sleccionado(Solo Regiones).
	 */
	public void executeReporteFacturaDetalle2(Map params);
	
	/**
     * Genera archivo CSV en Oracle con la informacion de 
     * Reporte ZON Unidades Geograficas
     * @param criteria
     */
    public Map generarReporteZONUnidadesGeograficasCSV(Map criteria);
    
    /**
	 * @param criteria
	 * Metodo que ejecuta el reporte direcciones consultas 
	 */
	public void executeReporteGISDireccionConsulta(Map criteria);
	/**
	 * @param criteria
	 * Metodo que ejecuta consulta de direccion de consulta. 
	 */
	public void insertConsultaDireccionConsulta(Map criteria);
	
	/**
	 * Metodo que elimina los registros de la tabla direccion de consulta. 
	 */
	public void deleteConsultaDireccionConsulta();

	/**
	 * Inserta en una GTT, el codigo de zona o region (si es capacitadora)
	 * @param params
	 */
	public void insertGloblalTemporaryForRegionZonaCapacitadora(Map params);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte direcciones consultas en CSV
	 */
	public Map generarReporteGISEnviarDireccionConsultorasCSV(Map criteria);
	
	/**
	 * Devuelve lista de zonas.
	 * @param criteria
	 * @return
	 */
	public List getListaZonasReporteMAENovedadesZona();
	
	/**
	 * actualiza el reporte de novedades de zona.
	 */
	public void updateReporteMAENovedadesZona(Map criteria);
	
	/**
	 * Indica el tipo de concurso
	 * @param criteria
	 * @return
	 */
	public String getTipoConcurso(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Gerente Con Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Gerente Con 
	 *      Fecha Region Zona en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaRegionZonaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Gerente Detalle 
	 *                    Con Sin Fecha TipoCargo en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaTipoCargoCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Gerente Detalle 
	 *                    Con Sin Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Gerente Sin Fecha Region en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Gerente Sin Fecha Region Zona en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionZonaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Gerente Sin Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Envios Fecha Campanha Con Sin Fecha en CSV
	 */
	public Map generarReporteMAVEnviosFechaCampConSinFechaCSV(Map criteria);
	
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Consultora Con Fecha Region en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionCSV(Map criteria);
	
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Consultora Con Fecha Region Zona en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionZonaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Camapanha Por Consultora Con Fecha CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Consultora Detalle Con Sin Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraDetalleConSinFechaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Consultora Sin Fecha Region en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Consultora Sin Fecha Region Zona en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionZonaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Consultora Sin Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Metodo que ejecuta el reporte Atencion Fecha Campanha Por Gerente Con Fecha Region en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaRegionCSV(Map criteria);
	
	/**
	 * @param criteria
	 * Obtiene el codigo del Period sea anteriores,siguientes, descuento.
	 */
	public String getCodigoPeriodoASD(Map criteria);
	
	/**
	 * Ejecuta calculo de Comisiones Venta Retail
	 * @param criteria
	 */
	public void executeComisionesVentaRetail(Map criteria);
	
	/**
	 * Se genera la data para el Reporte del Programa de Reconocimiento
	 * @param criteria
	 */
	public void executeINCProgramaReconocimiento (Map criteria);
	
	/**
	 * Se genera la data para el Reporte del Programa de Reconocimiento COLOMBIA
	 * @param criteria
	 */
	public void executeINCProgramaReconocimiento2 (Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public Map generarReporteGestionarConsultoraCSV(Map criteria);
	
	/**
	 * Reporte de Zonas a Facturar por Fecha COLOMBIA
	 * @param criteria
	 */
	public void executeReporteCRAZonasFacturaFecha(Map criteria);
	
	/**
	 * Genera Reporte de Porcentaje de Desviacin de Pedidos
	 * @param criteria
	 */
	public void executeReporteIMPPorceDesviPedido(Map criteria);

	/**
	 * Crea la data para el reporte de cupones electronicos
	 * @param params
	 */
	public void executeReporteINCCuponesElectronicos(Map criteria);
	
	/**
	 * Genera el reporte de Cupones Electronicos
	 * @param criteria
	 * @return
	 */
	public Map generarReporteINCCuponesElectronicosCSV(Map criteria);
	/**
	 * Genera el reporte de Nuevas Unidades de Atendidas
	 * @param criteria
	 * @return
	 */
	public Map generarReporteCUPNuevaUnidadAtendidaCSV(Map criteria);
	
	/**
	 * Ejecuta Eliminacion de Gerentes Retiradas para Comision de Base 07
	 * @param params
	 */
	public void executeEliminarComisionesGerenteRetiradas(Map criteria);
	
	/**
	 * Genera data para la tabla temporal ccc_tempo_saldo_diario, para el Reporte Auditora - Saldo de Cuentas por Cobrar  
	 * @param criteria
	 */
	public void executeAuditoriaSaldoCuentasPorCobrar();
	
	/**
	 * Metodo donde se registra en una tabla temporal codigoZona
	 * @param criteria
	 */
	public void insertTablaRegionZona(Map criteria);
	
	/**
	 * Genera la data para el Reporte de Facturacion Detalle SAC atraves de lo sleccionado(Algunas zonas y algunas regiones) y todos.
	 * @param params
	 */
	public Map executeReporteMgpedxdiaCsv1(Map params);
	
	/**
	 *  Metodo donde se elimina los registros de la tabla temporal codigoZona y codigoRegion
	 */
	public void deleteTablaRegionZona();
	
	/**
	 * Eliminar todas las Zonas en Distribucion Facturacion Real
	 */
	public void deleteZonasDistribucionFacturacionReal();
	
	/**
	 * Inserta las Zonas seleccionadas en Distribucion Facturacion Real
	 * @param params
	 */
	public void insertZonasDistribucionFacturacionReal(Map params);
	
	/**
	 * Elimina las Zonas seleccionadas en Reporte Inventario  de Campo
	 */
	public void deleteZonasReporteInventarioCampo();
	
	/**
	 * Inserta las Zonas seleccionadas en Reporte Inventario  de Campo
	 * @param params
	 */
	public void insertZonasReporteInventarioCampo(Map params);
	
	/**
	 * Ejecuta la eliminacion e insercion de las zonas seleccionadas
	 */
	public void executeDeleteInsertZonasDistribucionFacturacionReal(Map params);
	
	
	/**
	 * @param params
	 */
	public void executeDeleteInsertZonasInventarioCampoResumen(Map params);
	
	/**
	 * @param params
	 */
	public void executeDeleteInsertZonasInventarioCampoDetalle(Map params);
	/**
	 * @param params
	 * @return
	 */
	public Map executeReporteAPEDistribucionCdrsFacturacionReal(Map params);
	
	/**
	 * @param params
	 * @return
	 */
	public Map executeReporteAPEIventarioCampoResumen(Map params);
	
	/**
	 * @param params
	 * @return
	 */
	public Map executeReporteAPEIventarioCampoDetalle(Map params);
	
	/**
	 * @param params
	 * @return
	 */
	public Map executeReporteSACFacturacionDetalle(Map params);
	
	/**
	 * Elimina data de tabla temporal en la generacion del Reporte SIC Detalle Unidades
	 * Atendidas Faltantes
	 * @param criteria
	 */
	public void deleteReporteSICDetalleUnidadesAtendidasFaltantes(Map criteria);
	
	/**
	 * Inserta eb tabla temporal la generacion del Reporte SIC Detalle Unidades
	 * Atendidas Faltantes
	 * @param criteria
	 */
	public void insertReporteSICDetalleUnidadesAtendidasFaltantes(Map criteria);
	
	
	/**
	 * Genera Reporte SIC Detalle Unidades Atendidas Faltantes en formato CSV
	 * @param criteria
	 */
	public void executeReporteSICDetalleUnidadesAtendidasFaltantesCSV(Map criteria);
	
	
	/**
	 * Elimina data de tabla temporal en la generacion del Reporte MAE Consejeras
	 * Bloqueadas Desbloqueadas
	 * @param criteria
	 */
	public void deleteReporteMAEConsejerasBloqueadasDesbloqueadas(Map criteria);
	
	/**
	 * Inserta eb tabla temporal la generacion del Reporte MAE Consejeras
	 * Bloqueadas Desbloqueadas
	 * @param criteria
	 */
	public void insertReporteMAEConsejerasBloqueadasDesbloqueadas(Map criteria);
	
	
	/**
	 * Genera Reporte MAE Consejeras Bloqueadas Desbloqueadas en formato CSV
	 * @param criteria
	 */
	public void executeReporteMAEConsejerasBloqueadasDesbloqueadasCSV(Map criteria);
	
	/**
	 * Genera Reporte COB Detallado Recuperacion Cartera Cobradoen en formato CSV
	 * @param criteria
	 */
	public void executeReporteCOBDetalladoRecuperacionCarteraCobradorCSV(Map criteria);

	/**
	 * Genera Reporte COB Carga Masiva Gestion
	 * @param criteria
	 */
	public void executeReporteCOBCargaMasivaGestionCSV(Map criteria);

	/**
	 * Elimina data de tabla temporal en la generacion del Reporte INC Puntos Obtenidos Bolsa Faltantes
	 * 
	 * Atendidas Faltantes
	 * @param criteria
	 */
	public void deleteReporteINCPuntObtenidosBolsaFaltantesInc(Map criteria);
	
	/**
	 * Inserta eb tabla temporal la generacion del Reporte INC Puntos Obtenidos Bolsa Faltantes
	 * 
	 * Atendidas Faltantes
	 * @param criteria
	 */
	public void insertReporteINCPuntObtenidosBolsaFaltantesInc(Map criteria);
	

	/**
	 * Genera Reporte INC Puntos Obtenidos Bolsa Faltantes en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteINCPuntObtenidosBolsaFaltantesIncCSV(Map criteria);
	
	
	/**
	 * Elimina data de tabla temporal usada en el Reporte Detallado Cobranza 31 dias para formato CSV
	 * @param criteria
	 */
	public void deleteReporteCOBDetalladCobranza31dias(Map criteria);
	

	/**
	 * Genera Reporte COB Detallado Cobranza 31 dias en tabla temporal para formato CSV
	 * @param criteria
	 */
	public void insertReporteCOBDetalladCobranza31dias(Map criteria);
	
	
	/**
	 * Elimina data de tabla temporal usada en el Reporte Detallado PED Seguimiento Consultoras para formato CSV
	 * @param criteria
	 */
	public void deleteReportePEDSeguimientoConsultora(Map criteria);
	
	/**
	 * Elimina data de tabla temporal usada en el Reporte de Avance Facturado de Programa de Reconocimiento VZ - Consultoras
	 * @param criteria
	 */
	public void deleteReportePEDAvanceFacturadoProgramaReconocimientoVZConsu(Map criteria);
	
	/**
	 * Inserta data de tabla temporal usada en el Reporte de Avance Facturado de Programa de Reconocimiento VZ - Consultoras
	 * @param criteria
	 */
	public void insertReportePEDAvanceFacturadoProgramaReconocimientoVZConsu(Map criteria);
	
	/**
	 * Ejecuta procedimiento para creacion de la data del ReportePEDAvanceFacturadoProgramaReconocimientoVZ 
	 * @param criteria
	 */
	public void executeReportePEDAvanceFacturadoProgramaReconocimientoVZ(Map criteria);
	
	/**
	 * Genera Reporte PED Seguimiento Consultora en tabla temporal para formato CSV
	 * @param criteria
	 */
	public void insertReportePEDSeguimientoConsultora(Map criteria);
	
	
	/**
	 * Genera Reporte COB Detallado Cobranza 31 dias en formato CSV
	 * @param criteria
	 */
	public void executeReporteCOBDetalladCobranza31diasCSV(Map criteria);
	
	/**
	 * Elimina data de tabla temporal usada en el Reporte Saldos Pendientes para formato CSV
	 * @param criteria
	 */
	public void deleteReporteCOBSaldosPendientes(Map criteria);
	
	/**
	 * Genera Reporte COB Saldos Pendientes en tabla temporal para formato CSV
	 * @param criteria
	 */
	public void insertReporteCOBSaldosPendientes(Map criteria);
	
	
	/**
	 * Genera Reporte COB Saldos Pendientes en formato CSV
	 * @param criteria
	 */
	public void executeReporteCOBSaldosPendientesCSV(Map criteria);
	
	/**
	 * Genera Reporte CCC Liquidacion Cobranzas en formato CSV
	 * @param criteria
	 */
	public void executeReporteCCCLiquidacionCobranzasCSV(Map criteria);

	/**
	 * Genera archivo TXT en Oracle con informacion del Reporte Historico de Operacion Crediticia
	 * @param criteria
	 * @return
	 */
	public Map generarHistoricoOperacionCrediticiaTXT(Map criteria);

	/**
	 * Genera los datos del Reporte Historico de Operacion Crediticia
	 * @param criteria
	 */
	public void executeGenerarHistoricoOperacionCrediticia(Map criteria);

	/**
	 * Elimina data de tabla temporal en la generacion del Reporte COB Detallado Movimiento Recuperacion Incobrable
	 * @param criteria
	 */
	public void deleteReporteCOBDetalladoMovRecuperacionIncobrable(Map criteria);

	/**
	 * Inserta en tabla temporal la generacion del Reporte COB Detallado Movimiento Recuperacion Incobrable
	 * @param criteria
	 */
	public void insertReporteCOBDetalladoMovRecuperacionIncobrable(Map criteria);

	/**
	 * Genera Reporte COB Detallado Movimiento Recuperacion Incobrable en formato CSV
	 * @param criteria
	 */
	public void executeReporteCOBDetalladoMovRecuperacionIncobrableCSV(Map criteria);

	/**
	 * Elimina data de tabla temporal en la generacion del Reporte CCC Medios Magneticos
	 * @param criteria
	 */
	public void deleteReporteCCCArchivosMediosMagneticos(Map criteria);

	/**
	 * Inserta en tabla temporal la generacion del Reporte CCC Medios Magneticos
	 * @param criteria
	 */
	public void insertReporteCCCArchivosMediosMagneticos(Map criteria);

	/**
	 * Genera Reporte CCC Medios Magneticos en formato CSV 
	 * @param criteria
	 */
	public void executeGeneraReporteCCCArchivosMediosMagneticosCSV(Map criteria);
	
	/**
	 * Elimina data de tabla temporal en la generacion del Reporte MAE Vinculos Cliente
	 * @param criteria
	 */
	public void deleteReporteMAEVinculosCliente(Map criteria) ;

	/**
	 * Inserta en tabla temporal la generacion del Reporte MAE Vinculos Cliente
	 * @param criteria
	 */
	public void insertReporteMAEVinculosCliente(Map criteria) ;
	
	/**
	 * Genera Reporte MAE Vinculos Cliente en formato CSV 
	 * @param criteria
	 */
	public void executeReporteMAEVinculosClienteCSV(Map criteria);
	
	/**
	 * Genera Reporte CCC Detalle Cuenta Corriente en formato CSV
	 * @param criteria
	 */
	public void executeReporteCCCDetalleCuentaCorrienteContableCSV(Map criteria);
	
	/**
	 * Genera Reporte CCC Detallado Pago x Regularizar en formato CSV
	 * @param criteria
	 */
	public void executeReporteCCCDetalladoPagoxRegularizarCSV(Map criteria);
	

	/**
	 * Genera data en tabla temporal para el Reporte INC Puntajes Obtenidos y Puntajes Faltantes en formato CSV
	 * @param criteria
	 */
	public void executeBeforeReporteINCPuntObtenidosPuntFaltantes(Map criteria);
	
	/**
	 * Genera Reporte INC Puntajes Obtenidos y Puntajes Faltantes en formato CSV
	 * @param criteria
	 */
	public void executeReporteINCPuntObtenidosPuntFaltantesCSV(Map criteria);
	
	/**
	 * Genera Reporte CCC Auditoria Saldox Cuentas x Cobrar en formato CSV
	 * @param criteria
	 */
	public void executeReporteCCCAuditoriaSaldoCuentasPorCobrarCSV(Map criteria);
	
	/**
	 * Mtodo que se encarga de generar el reporte Detallado IFC en formato CSV.
	 *
	 * @param criteria the criteria
	 */
	public void executeReporteDetalleIFCCSV(Map criteria);
	
	
	/**
	 * Devuelve ID sgte para insertar en el Historico Reporte
	 * @return
	 */
	public Long getDevuelveIdSgteCodHistoricoReporte();
	
	/**
	 * @param historicoReporte
	 */
	public void insertHistoricoReporte(HistoricoReporte historicoReporte);
	
	/**
	 * @param historicoReporte
	 */
	public void updateHistoricoReporte(HistoricoReporte historicoReporte);
	
	/**
	 * Mtodo que se encarga de generar el reporte Clasificaciones por Cliente en formato CSV.
	 *
	 * @param criteria the criteria
	 */
	public void executeReporteMAEClasificacionXClienteCSV(Map criteria);


	
	/**
	 * Verifica si hay lista para el Proceso REC Enviar CDRs Recepcionados
	 * @param criteria
	 * @return
	 */
	public List getExisteListaProcesoRECEnviarCDRRecepcionados(Map criteria);
	
	/**
	 * Devuelve lista en base a los criterios de busqueda (Proceso REC Enviar CDRs Recepcionados
	 * @param criteria
	 * @return
	 */
	public List getListaProcesoRECEnviarCDRRecepcionados(Map criteria);
	
	/**
	 * Mtodo que se encarga de llenar ka tabla temporal de movimientos de territorio
	 * @param criteria
	 */
	public void executeCargaTemporalReporteZONMovimientoTerritorio(Map criteria);
	
	/**
	 * Mtodo que se encarga de generar el reporte de movimientos territorio por campaa en formato CSV
	 * @param criteria
	 * @return
	 */
	public Map generarReporteZONMovimientoTerritorioCSV(Map criteria);
	
	/**
	 * Genera Reporte CCC Gasto Cupon en formato CSV
	 * @param criteria
	 */
	public void executeReporteCCCGastoCuponCSV(Map criteria);
	
	/**
	 * Actualiza los registros no terminados de Auditoria con la fecha actual
	 */
	public void actualizarRegistroAuditoriaReinicioServidor() ;
	
	/**
	 * Ejecuta el reporte de recall por trazabilidad
	 * @param criteria
	 * @return
	 */
	public Map executeReporteSACRecallTrazabilidad(Map criteria);
	
	/**
	 * Mtodo que se encarga de llenar la tabla temporal de Puntos Consultora
	 * 
	 * @param params
	 */
	public void executeBeforeReporteINCPuntosConsultora(Map params);	
	
	/**
	 * Mtodo que se encarga de generar el reporte de Puntos Consultora en formato CSV
	 * 
	 * @param params
	 */
	public void executeReporteINCPuntosConsultoraCSV(Map params);

	/**
	 * Mtodo que se encarga de llenar la tabla temporal de Puntos Campania
	 * @param params
	 */
	public void executeBeforeReporteINCPuntosCampania(Map params);

	/**
	 * Mtodo que se encarga de generar el reporte de Puntos Campania en formato CSV
	 * @param params
	 */
	public void executeReporteINCPuntosCampaniaCSV(Map params);

	/**
	 * Mtodo que se encarga de llenar la tabla del reporte retiradas sin deudas
	 * @param params
	 */
	public void executeReporteCOBRetiradasSinDeuda(Map params);
	
	/**
	 * Ejecuta el proceso de las Egresadas sin deuda
	 */
	public void executeEgresadasSinDeuda(Map params);
	
	/**
	 * Ejecuta el proceso del Primer Pedido con Deuda
	 */
	public void executeReporteCOBPrimerPedidoConDeuda(Map params);

	/**
	 * Mtodo que se encarga de llenar la tabla de pedidos facturados con deuda
	 * @param params
	 */
	public void executeReporteCOBPedidosFacturadosConDeuda(Map params);

	/**
	 * Mtodo que se encarga de llenar la tabla de Primeros y Segundo Pedidos con Deuda
	 * @param params
	 */
	public void executeReporteCOBPrimerosSegundoPedidosConDeuda(Map params);
	
	/**
	 * Ejecuta el proceso del Primer, Segundo y Tercer Pedido con Deuda
	 */
	public void executeReporteCOBPrimerSegundoTercerPedidoConDeuda(Map params);
	
	/**
	 * Inserta data en Tabla Temporal Reporte de ]Liquedacion Cobranzas (Consolidado Zona)
	 * @param criteria
	 */
	public void insertReporteCCCLiquidacionCobranzasCZ(Map criteria);
	
	
	/**
	 * Inserta data en Tabla Temporal Reporte de ]Liquedacion Cobranzas (Detallado Zona)
	 * @param criteria
	 */
	public void insertReporteCCCLiquidacionCobranzasDZ(Map criteria);

	/**
	 * Obtiene el estado del indicador del cliente o cedula
	 * @param criteria
	 * @return
	 */
	public String getIndicadorClienteCedula(Map criteria);
	
	/**
	 * Genera Reporte CUP Nuevas Cupones en tabla temporal
	 * @param criteria
	 */
	public void executeBeforeReporteCUPNuevasCupones(Map criteria);
	
	/**
	 * Genera Reporte CUP Nuevas Cupones en formato CSV 
	 * @param criteria
	 */
	public void executeReporteCUPNuevasCuponesCSV(Map criteria);

	/**
	 * Ejecuta el reporte de Control Facturacion y Entrega de Pedido
	 * @param criteria
	 * @return
	 */
	public Map executeReporteSACControlFacturacionEntregaPedido(Map criteria);

	
	/**
	 * Ejecuta el reporte de resultados
	 * @param criteria
	 * @return
	 */
	public Map executeReporteLETResultado(Map criteria);
	
	/**
	 * Ejecuta el reporte SGR de solicitudes
	 * @param criteria
	 * @return
	 */
	public Map executeReporteSGRSolicitudesTod(Map criteria);
	
	/**
	 * inserta regiones a la tabla temporal
	 * @param criteria
	 */
	public void insertRegionReporteLETResultado(Map criteria);

	/**
	 * Obtiene el siguiente valor de la secuencia como parametro
	 * @return
	 */
	public String getSecuenciaIndicadorControlEntregaNextValue();
	
	
	/**
	 * Inserta en tabla temporal la generacion del Reporte CCC Medios Magneticos Cuenta Corriente Bimensual
	 * @param criteria
	 */
	public void insertReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual(Map criteria);
	
	/**
	 * Limpia tabla del Reporte CCC Medios Magneticos Cuenta Corriente Bimensual
	 */
	public void deleteReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual();
	
	/**
	 * Genera Reporte CCC Medios Magneticos Cuenta Corriente Bimensual en formato CSV 
	 * @param criteria
	 */
	public void executeGeneraReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensualCSV(Map criteria);
	
	/**
	 * Inserta en tabla temporal la generacion del Reporte CCC Medios Magneticos Cuenta Corriente Bimensual
	 * @param criteria
	 */
	public void insertReporteCCCAntiguedadSaldos(Map criteria);
	
	/**
	 * Limpia tabla del Reporte CCC AntiguedadSaldos
	 */
	public void deleteReporteCCCAntiguedadSaldos();
	
	/**
	 * Genera Reporte CCC AntiguedadSaldos en formato CSV 
	 * @param criteria
	 */
	public void executeGeneraReporteCCCAntiguedadSaldosCSV(Map criteria);
	
	/**
	 * Genera la data para el reporte de proyeccion LEC
	 * @param params
	 */
	public void executeReporteLECEnviarReporteProyecion(Map params);

	/**
	 * Obtiene las regiones del reporte de proyeccion LEC
	 * @param params
	 * @return
	 */
	public List getRegionesReporteLECEnviarReporteProyecion(Map params);

	/**
	 * Obtiene las zonas del reporte de proyeccion LEC
	 * @param params
	 * @return
	 */
	public List getZonasReporteLECEnviarReporteProyecion(Map params);
	
	/**
	 * Obtiene la campaña de Bono para una determinada region
	 * 
	 * @param criteria
	 * @return
	 */
	public String getCampanyaBonoRegion(Map criteria);
	
	/**
	 * Obtiene la campaña de Bono para una determinada zona
	 * 
	 * @param criteria
	 * @return
	 */
	public String getCampanyaBonoZona(Map criteria);
	
	/**
	 * Obtiene la campaña de Recaudo para una determinada region
	 * 
	 * @param criteria
	 * @return
	 */
	public String getCampanyaRecaudoRegion(Map criteria);
	
	/**
	 * Obtiene la campaña de Recaudo para una determinada zona
	 * 
	 * @param criteria
	 * @return
	 */
	public String getCampanyaRecaudoZona(Map criteria);

	/**
	 * Obtiene el codigo Periodo anterior
	 * 
	 * @param codigoPeriodo
	 * @return
	 */
	public String getCodigoPeriodoAnterior(String codigoPeriodo);
	
	/**
	 * Genera Reporte CCC Detallado Consultoras Incobrable en formato CSV - LLENA DATA
	 * @param criteria
	 */
	
	public void executeReporteCCCDetalladoConsultorasIncobrable(Map criteria);
	
	/**
	 * Genera Reporte CCC Detallado Consultoras Incobrable en formato CSV 
	 * @param criteria
	 */
	
	public Map executeReporteCCCDetalladoConsultorasIncobrableCSV(Map criteria);
	
	public void deleteReporteCCCDetalladoConsultorasIncobrable(Map criteria);
	
	public Map executeReporteCCCDetalladoProvisionIncobrableCSV(Map criteria);
	
	public Map executeReporteCCCDetalladoProvisionIncobrableMovCSV(Map criteria);
	
	/**
	 * Genera la data para el reporte de Recaudo por Campaña
	 * @param params
	 */
	public void executeReporteCCCConsolidadoRecaudoCampana(Map params);
	
	/**
	 * Elimina la data para el reporte de Recaudo por Campaña
	 * @param params
	 */
	public void deleteReporteCCCConsolidadoRecaudoCampana(Map params);

	/**
	 * 
	 * @param parameterMap
	 */
	public void executeReporteZONTerritorioUnidadGeograficaCSV(Map params);
	

	/**
	 * 
	 * @param params
	 */
	public void executePrepararReporteCOBRecuperacionCobranzaFFVVTXT(Map params);
	
	/**
	 * Genera el reporte de recuperacion de cobranza ffvv
	 * @param params
	 * @return
	 */
	public void executeGenerarReporteCOBRecuperacionCobranzaFFVVTXT(Map params);	
	
	/**
	 * Genera Reporte CCC Registro de Ventas Bolivia en formato CSV 
	 * @param params
	 */
	public void executeGeneraReporteCCCRegistroVentasBoliviaCSV(Map criteria);
	
	/**
	 * Genera Reporte CCC Registro de Abonos Bolivia en formato CSV 
	 * @param params
	 */
	public void executeGeneraReporteCCCRegistroAbonosBoliviaCSV(Map criteria);

	/**
	 * Genera Reporte CCC Buro Credito en formato CSV 
	 * @param criteria
	 */
	
	public Map executeReporteCCCBuroCreditoCSV(Map criteria);

	/**
	 * @param criteria
	 * Elimina los registros de la Tabla Temporal de Generación de Reporte Proyeccion
	 */
	public void eliminarRegistrosTablaTemporalReporProyeccion(Map criteria);

	
	
    /* NSSICC */
	
	/**
	 * @param criteria
	 */
	public void executeReporteCOBDetalladCobranza31diasXLSX(Map criteria) ;

	/**
	 * @param criteria
	 */
	public void executeReporteAntigNotasCredi(Map criteria);

	/**
	 * Ejecuta el proceso del Primer, Segundo, Tercer y Cuarto Pedido con Deuda
	 */
	public void executeReporteCOBPrimerSegundoTercerCuartoPedidoConDeuda(Map params);
	
	/**
	 * Inserta en tabla temporal para el Reporte TIM Bolivia
	 */
	public void executeReporteSACTIMImpositivoAduana(Map params);
	
	
	/**
	 * Inserta en tabla temporal para el Reporte SAC Atendido x Fecha (Consolidado)
	 */
	public void insertarReporteSACAtendidoxFechaConsolidado(Map params);
	
	/**
	 * Inserta en tabla temporal para el Reporte SAC Atendido x Fecha (Detallado)
	 */
	public void insertarReporteSACAtendidoxFechaDetallado(Map params);
	
	public String getCampanyaRecaudoRegionZona(Map criteria);
	
	public int getExisteCUV(Map criteria);
	
	/**
	 * Inserta en tabla temporal para el Reporte Consultoras Puntaje x Ubicación
	 */
	public void executeReporteConsultoraPuntajeUbicacion(Map params);
	
	/**
	 * Inserta en tabla temporal para el Reporte Codigos Inexistentes
	 */
	public void executeReporteCodigosInexistentes(Map params);
	
	/**
	 * Inserta en tabla temporal para el Reporte REC Listado Deuda Pendiente x Periodo Consolidado
	 * @param params
	 */
	public void insertarReporteRECListadoDeudaPendPeriodoConsolidado(Map params);
	
	/**
	 * Inserta en tabla temporal para el Reporte REC Listado Deuda Pendiente x Periodo Detallado
	 * @param params
	 */
	public void insertarReporteRECListadoDeudaPendPeriodoDetallado(Map params);

	/**
	 * delete Codigos SAP a la tabla temporal
	 * @param criteria
	 */
	public void deleteCodigoSAPReportePEDDetallePedidosFacturados();
	
	/**
	 * inserta Codigos SAP a la tabla temporal
	 * @param criteria
	 */
	public void insertCodigoSAPReportePEDDetallePedidosFacturados(Map criteria);
	
	/**
	 * Inserta en tabla temporal para el Reporte Detalle Pedidos Facturados por Código SAP
	 * @param params
	 */
	public void insertReportePEDDetallePedidosFacturadosPorCodigoSAP(Map params);
	
	/**
	 * Inserta en tabla temporal para el Reporte Factura x Auditoria (Consolidado)
	 */
	public void executeReporteVENFacturaAuditoriaConso(Map params);
	
	/**
	 * Inserta en tabla temporal para el Reporte Factura x Auditoria (Detallado)
	 */
	public void executeReporteVENFacturaAuditoriaDetal(Map params);
	
	public void executeReporteCCCPRDetalleCuentaCorriente(Map criteria);
	
	/**
	 * Inserta en tabla temporal para el Reporte Configuración Ofertas (Concurso)
	 */
	public void executeReporteConfiguracionOfertasConcurso(Map params);
	
	/**
	 * Inserta en tabla temporal para el Reporte Configuración Ofertas (N)
	 */
	public void executeReporteConfiguracionOfertasN(Map params);
	
	/**
	 * Inserta en tabla temporal para el Reporte de Número de Pagos por Campaña 
	 */
	public void executeReporteCOBNumeroPagosCampanna(Map params);
	
	
	/**
	 * Genera Reporte de Número de Pagos por Campaña  en CSV
	 */
	public void executeReporteCOBNumeroPagosCampannaCSV(Map params);
	
	public Map executeReporteCCCInteresCCorrienteCSV(Map criteria);
	
	public void executeGenerarReporteCCCInteresCCorriente(Map params);
	
	public void executeReporteINCDetallePuntosRegionZonaByNacionalCSV(Map params);
	
	public void executeReporteINCDetallePuntosRegionZonaByRegionCSV(Map params);

	public void executeReporteINCDetallePuntosRegionZonaByZonaCSV(Map params);
	
	public void executeReporteINCDetallePuntosRegionZonaByConsultoraCSV(Map params);
	
	public void executeReporteINCDetallePuntosRegionZonaByCampaniaCSV(Map params);
	
	public void executeReporteINCProvisionContableIngresosCSV(Map params);
	
	public void executeReporteINCProvisionContableGastosCSV(Map params);
	
	public Map executeReportePERResumenDiarioPercepcionesSunatCSV(Map params);
	
	public Map executeReportePERResumenDiarioPercepcionesSunatTXT(Map params);	

}
package biz.belcorp.ssicc.service.scsicc;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.sisicc.model.BaseBlob;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * Service con metodos comunes para todos los reportes.
 * 
 * @author <a href="">Marco Antonio Agurto Jimenez</a>
 * 
 */
public interface ReporteService extends Service {

	/**
	 * Obtiene un listado de todas las marcas disponibles en la tabla SEG_MARCA@SICC
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getMarcas();

	/**
	 * Obtiene un listado de todas los rangos de perodos disponibles en la tabla
	 * CRA_RANGO_PERIO@SICC
	 * 
	 * @return Lista de objetos Base, poblados.
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
	public List getCanalesByCodigoISO(String codigo);

	public List getTiposVinculosByPais(String codigoPais);

	/**
	 * @return Returns the reporteDAO.
	 */
	public ReporteDAO getReporteDAO();

	/**
	 * @param reporteDAO
	 *            The reporteDAO to set.
	 */
	public void setReporteDAO(ReporteDAO reporteDAO);

	/**
	 * Genera Reporte PED Seguimiento Consultora en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReportePEDSeguimientoConsultoraCSV(Map criteria);

	/**
	 * Genera data para Reporte de comision por Ingresos
	 * 
	 * @param criteria
	 */
	public void executeComisionIngreso(Map criteria);

	/**
	 * Genera data para Reporte de comision por Recuperacion
	 * 
	 * @param criteria
	 */
	public void executeComisionRecuperacion(Map criteria);

	/**
	 * Genera data para Reporte de comision por Comercializacion
	 * 
	 * @param criteria
	 */
	public void executeComisionComercializacion(Map criteria);

	/**
	 * Genera Data para Reporte de Facturas Pendiente x Seccion
	 * 
	 * @param criteria
	 */
	public void executeFacturaPendienteSeccion(Map criteria);

	/**
	 * Genera Data para Reporte de Cuadre SAPFI
	 * 
	 * @param criteria
	 */
	public void executeCuadreSAPFI(Map criteria);

	/**
	 * Genera Data para Reporte de Control de Asistencia a Conferencia de Ventas
	 * 
	 * @param criteria
	 */
	public void executeControlAsistencia(Map criteria);

	/**
	 * Genera Data para Reporte de Control de Asistencia Triunfadoras por
	 * Pantalla
	 * 
	 * @param criteria
	 */
	public void executeControlAsistenciaTriunfadoras(Map criteria);

	/**
	 * Genera Data para Reporte de Estado de CtaCte a Vendedoras
	 * 
	 * @param criteria
	 */
	public void executeEstadoCtaCteVendedora(Map criteria);

	/**
	 * Genera Data para Reporte de Detalle de CtaCte
	 * 
	 * @param criteria
	 */
	public void executeDetalleCtaCte(Map criteria);

	/**
	 * Genera Data para Reporte de Facturas Pendiente x Campaa
	 * 
	 * @param criteria
	 */
	public void executeFacturaPendienteCampana(Map criteria);

	/**
	 * Genera Data para Reporte de Base de Recuperacion Campaas
	 * 
	 * @param criteria
	 */
	public void insertTableReportesSQL(String method, Map criteria);

	public void deleteTableReportesSQL(String method, Map criteria);

	public void executeBaseRecuperacionCampana(Map criteria);

	/**
	 * Genera Data para Reporte de Venta Variables
	 * 
	 * @param criteria
	 */
	public void executeVentaVariable(Map criteria);

	public List getListaPeriodosByBasCtrlFact(String codigoPais,
			String actualHistorio);

	public List getListaGenerico(String getLista, Map criteria);

	public List getListaReporte(String getLista, Map criteria);

	public void executeReporteSQL(String procedure, Map criteria);

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
	 * Inserta Registro de Log de Reportes grabados en Disco
	 * 
	 * @param criteria
	 */
	public void insertLogReporteDisco(Map criteria);

	/**
	 * Devuelve Lista con el log de los reportes generados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getLogReporteDisco(Map criteria);

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
	 * Genera Lista de Responsables UA utilizada en la Consulta respectiva
	 * 
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

	public void metodosReporte(Map criteria);

	/**
	 * Genera Datos para combo de periodo
	 * 
	 * @param tipoPeriodo
	 * @return
	 */
	public List getPeriodosCorporativosPorTipo(String tipoPeriodo);

	public void executeSeguimientoCalificacionCampana(Map criteria);

	public Map getPeriodosSeguimientoCalificacionCampana(Map criteria);

	public Map getTramosEvaluacionResumenPorSeccion(Map criteria);

	public void executeResumenEvaluacionSeccion(Map criteria);

	public void executeResumenEvaluacionZona(Map criteria);

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
	 * Devulve el map de codigos de tramo anteriores
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
	 * Devuelve la lista de Regiones para generar los reportes
	 * 
	 * @return
	 */
	public List getListaZonasReporteSACAsistencia();

	/**
	 * Devuelve la lista de Regiones para generar los reportes
	 * 
	 * @return
	 */
	public List getListaZonasReporteSACActivasSaldo();

	/**
	 * Devuelve la lista de estado de cliente y su equivalencia para el reporte
	 * de pedidos digitados por zona
	 * 
	 * @return
	 */
	public List getEstadoEquivalenciaCliente();

	/**
	 * Devuelve la cantidad de datos encontrados para una region y sus zonas
	 * 
	 * @return
	 */
	public String getContadorListaRegionesZonas(Map criteria);

	/**
	 * Devuelve lisa para cargar el combo de tipo de CDR
	 * 
	 * @return
	 */
	public List getTiposCDR();

	/**
	 * Devuelve lisa para cargar el combo de tipo de Atenciones
	 * 
	 * @return
	 */
	public List getTiposAtencion();

	/**
	 * Devuelve las fechas inicial y final de un periodo
	 * 
	 * @param codPeriodo
	 * @return
	 */
	public List getRangoFechaPeriodo(String codPeriodo);

	/**
	 * Devuelve maximo valor de oferta de tabla BAS_PAIS
	 * 
	 * @param criteria
	 * @return
	 */
	public String getMaximoValorOferta(Map criteria);

	/**
	 * Se genera la data para el reporte de Consolidados Atencion CDR
	 * 
	 * @param criteria
	 */
	public void executeSTOConsolidadoAtencionCDR(Map criteria);

	/**
	 * Retorna el oid de clasificacion de la tabla MAE_CLASI
	 * 
	 * @param criteria
	 * @return
	 */
	public String getOidClasificacion(Map criteria);

	/**
	 * Genera la data para el reporte de Proyecto de Premios de Concursos
	 * 
	 * @param params
	 */
	public void executeReporteProyeccionesPremioConcurso(Map params);

	/**
	 * inseta los niveles de concurso seleccionados por pantalla a la tabla
	 * temporal
	 * 
	 * @param params
	 */
	public void insertGloblanTemporaryForNumNivel(Map params);

	/**
	 * Se traen los registros para Estado de Atenciones MAV
	 * 
	 * @param oidPeriodo
	 * @return
	 */
	public List getEstadoAtencionesMAV(String oidPeriodo);

	/**
	 * Se traen los registros para productos q no pasaron pedidos MAV
	 * 
	 * @param criteria
	 * @return
	 */
	public List getNoPasaronPedidosMAV(Map criteria);

	/**
	 * Devuelve lista de Oid de Tipos de Documentos de Facturacion
	 * 
	 * @param criteria
	 * @return
	 */
	public List getOidFacturacionTipoDocumento(Map criteria);

	/**
	 * Se genera la data para el reporte de Pedidos Afectados Detalle FNA
	 * 
	 * @param criteria
	 */
	public void executeSICPedidosAfectadosDetFNA(Map criteria);

	/**
	 * Se traen los oid de tipo de solicitud para el reporte de Consolidado
	 * Atencion CDR del Modulo STO
	 * 
	 * @return
	 */
	public List getTiposSolicitudSTO();

	/**
	 * Trae el oid de concursor por numero de concurso
	 * 
	 * @param codigoConcurso
	 * @return
	 */
	public String getOidConcursoByNumConc(String codigoConcurso);

	/**
	 * Se traen la lista de los resultado de chequeo por consultora
	 * 
	 * @return
	 */
	public List getResultadoChequeoConsultora(Map criteria);

	/**
	 * Se obtiene la lista del detalle del resultado de chequeo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDetalleResultadoChequeo(Map criteria);

	/**
	 * Retorna la lista de avance de pedido
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConsultaInformeAvancePedido(Map criteria);

	/**
	 * Retorna la lista de consultoras activas sin pedido
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConsultorasActivasSinPedido(Map criteria);

	/**
	 * Retorna fecha de ultima actualizacion de zona sino ha habido retorna
	 * vacio
	 * 
	 * @param criteria
	 * @return
	 */
	public String getFechaUltimaActualizacionZona(Map criteria);

	/**
	 * Retorna numero de registros
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getNumeroRegistros(Map criteria);

	/**
	 * Retora la cunsulta de ocr en un solo campo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getInformeAvancePedido(Map criteria);

	/**
	 * Retorna los codigo de venta rechazados para la consultora
	 * 
	 * @param criteria
	 * @return
	 * @throws RemoteException
	 */
	public List getCodigoVentasRechazados(Map criteria);

	/**
	 * Retorna los detalle de pedidos facturados para la consultora
	 * 
	 * @param criteria
	 * @return
	 * @throws RemoteException
	 */
	public List getDetallePedidoFacturado(Map criteria);

	/**
	 * Retorna el numero de faltantes Anunciados
	 * 
	 * @param criteria
	 * @return
	 * @throws RemoteException
	 */
	public List getFaltantesAnunciados(Map criteria);

	/**
	 * Genera archivo TXT en Oracle con la informacion de Reporte Sunat
	 * 
	 * @param criteria
	 */
	public Map generarReporteRUVSunatTXT(Map criteria);

	/**
	 * Genera archivo CSV en Oracle con la informacion de Reporte Sunat
	 * 
	 * @param criteria
	 */
	public Map generarReporteRUVSunatCSV(Map criteria);

	/**
	 * Genera archivo Excel en Oracle con la informacion de
	 * 
	 * @param criteria
	 * @return
	 */
	public Map generarReporteRUVSunatExcel(Map criteria);

	/**
	 * Retornar los tipos de bloqueos existentes
	 * 
	 * @return
	 */
	public List getTipoBloqueos();

	/**
	 * Genera archivo TXT en Oracle con la informacion de Reporte Sunat Errores
	 * 
	 * @param criteria
	 */
	public Map generarReporteRUVSunatErrorTXT(Map criteria);

	/**
	 * Genera archivo CSV en Oracle con la informacion de Reporte Sunat Errores
	 * 
	 * @param criteria
	 */
	public Map generarReporteRUVSunatErrorCSV(Map criteria);

	/**
	 * Retorna lista del Reporte de Pedidos en GP 1 sin errores
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReporteOCRPedidosGP1SinError(Map criteria);

	/**
	 * Retorna la lista del ReportePRIGenerarSolicitudesPrivilege
	 * 
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
	 *            Permite generar el reporte de Mapa Anaqueles Balanceo Diario
	 */
	public void executeGenerarReporteMapaAnaquelBalanceoDiario(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la Tabla Temporal de Generacin de
	 *            Reporte Mapa Anaqueles Balanceo Diario
	 */
	public void eliminarRegistrosTablaTempReporteMapaAnaq(Map criteria);

	/**
	 * @param criteria
	 * @return La secuencia para la tabla Temp de Balanceo Lnea y Productos
	 */
	public String getObtenerSecuenciaTempBalanceoLinea(Map criteria);

	/**
	 * @param criteria
	 *            Permite generar el reporte de Balanceo Lnea y Productos
	 */
	public void executeGenerarReporteBalanceoLinea(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la Tabla Temporal de Generacin de
	 *            Reporte Balanceo de Lnea y Productos
	 */
	public void eliminarRegistrosTablaTemporalReporteBalanceoLinea(Map criteria);

	/**
	 * Retorna la campanha activa d ela zona
	 * 
	 * @param criteriaPeriodo
	 * @return
	 */
	public String getCampanhaActivaByZona(Map criteriaPeriodo);

	/**
	 * Retornar el oid de la region usando como parametros el codigo de
	 * pais,marca,canal,region
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getOidRegionByPaisMarcaCanal(Map criteria);

	/**
	 * Retornar el oid de la zona usando como parametros el codigo de
	 * pais,marca,canal,region,zona
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getOidZonaByPaisMarcaCanalRegion(Map criteria);

	/**
	 * Retornar el oid de la seccion usando como parametros el codigo de
	 * pais,marca,canal,zonay codigo de seccion
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getOidSeccionByPaisMarcaCanalZona(Map criteria);

	/**
	 * Retorna la lista de retencion de pedidos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRetencionPedidos(Map criteria);

	/**
	 * Retorna la lista d epedidos digitados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPedidosDigitados(Map criteria);

	/**
	 * Retorna las zonas que pertecen a grupo de facturacion
	 * 
	 * @param grupoFacturacion
	 * @return
	 */
	public List getZonasGrupo(String grupoFacturacion);

	/**
	 * Retorna los grupos de facturacion asociados al pais marca y canal
	 * 
	 * @param params
	 * @return
	 */
	public List getGrupoFacturacion(Map params);

	/**
	 * Retorna las actividades asociados al pais marca y canal
	 * 
	 * @param params
	 * @return
	 */
	public List getActividad(Map params);

	/**
	 * Ejecuta la actualizacion de Tipo Pedido de la tabla
	 * REC_REPOR_OPERA_UNIDA_ADMIN
	 */
	public void executeActualizarTipoPedido();

	/**
	 * Retorna la lista de consultoras activas sin pedido
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConsultorasActivasSinPedidoAct(Map criteria);

	/**
	 * Retorna la cunsulta de ocr en un solo campo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getInformeAvancePedidoAct(Map criteria);

	/**
	 * Retorna la lista de solicitudes reclamos
	 * 
	 * @return
	 */
	public List getTiposSolicitudReclamos();

	/**
	 * Obtener oid periodo en base al codigo periodo
	 * 
	 * @param criteria
	 * @return
	 */
	public int getOidPeriodo(Map criteria);

	/**
	 * Retorna la lista de indicadores de caja / bolsa
	 * 
	 * @return
	 */
	public List getIndicadoresCajaBolsa();

	/**
	 * @param criteria
	 *            Inserta los valores de STO Rechazadas en tabla Global Tempory
	 */
	public void insertTemporalSTOReporteRechazadas(Map criteria);

	/**
	 * @param criteria
	 * @return Obtener Secuencia Temporal STO Rechazadas
	 */
	public String getObtenerSecuenciaTempSTORechazadas(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la tabla temporal STO Rechazadas
	 */
	public void eliminarRegistrosTablaTempSTORechazadas(Map criteria);

	/**
	 * Obtiene el oid de la actividad
	 * 
	 * @param params
	 *            . El metodo espera un oidPais y un codActivdad
	 * @return
	 */
	public String getOidActividad(Map critera);

	/**
	 * Capturar el valor tope para validar el cuadro Diferencia del Reporte
	 * Comparativo Retail vs SICC
	 * 
	 * @param criteria
	 * @return
	 */
	public Double getValidarMontoTope(Map criteria);

	/**
	 * @param criteria
	 * @return La secuencia para la tabla temporal de Reporte Cierre de Costos
	 *         Ventas
	 */
	public String getObtenerSecuenciaTempCOSCierreCostoVentas(Map criteria);

	/**
	 * @param criteria
	 *            Permite Generar el reporte Cierre de Costo - Ventas en la
	 *            tabla temporal
	 */
	public void executeGenerarReporteCierreCostoVentas(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la tabla temporal de Cierre de Costos
	 *            Ventas
	 */
	public void eliminarRegistrosTablaTempReporteCierreCostoVentas(Map criteria);

	/**
	 * @param criteria
	 * @return La secuencia para la tabla temporal de Reporte Cierre de Costos
	 *         Devoluciones
	 */
	public String getObtenerSecuenciaTempCOSCierreCostoDevoluciones(Map criteria);

	/**
	 * @param criteria
	 *            Permite Generar el reporte Cierre de Costo - Devoluciones en
	 *            la tabla temporal
	 */
	public void executeGenerarReporteCierreCostoDevoluciones(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la tabla temporal de Cierre de Costos
	 *            Devoluciones
	 */
	public void eliminarRegistrosTablaTempReporteCierreCostoDevoluciones(
			Map criteria);

	/**
	 * Retorna la consulta OCR , previo proceso
	 * 
	 * @param criteria
	 * @return
	 */
	public List getProcesoInformeAvancePedido(Map criteria);

	/**
	 * Retorna la consulta OCR Actualizado, previo proceso
	 * 
	 * @param criteria
	 * @return
	 */
	public List getProcesoInformeAvancePedidoAct(Map criteria);

	/**
	 * Genera archivo CSV en Oracle con la informacion de Libro de Ventas
	 * Mensual
	 * 
	 * @param criteria
	 * @return
	 */
	public Map generarReporteRUVLibroVentasMensualCSV(Map criteria);
	
	

	/**
	 * Genera archivo CSV en Oracle con la informacion de Reporte Pedidos
	 * Bonificacion
	 * 
	 * @param criteria
	 * @return
	 */
	public Map generarReportePEDBonificacionCSV(Map criteria);

	/**
	 * Obtiene el titulo para el reporte por Pais marca
	 * 
	 * @param criteria
	 * @return
	 */
	public String getTituloReportePaisMarca(Map criteria);

	/**
	 * @return Lista los tipos de documentos
	 */
	public List getTipoDocumentoList();

	/**
	 * @return El OidTipoDocumento
	 */
	public String getOidTipoDocumento(Map criteria);

	/**
	 * @param criteria
	 * @return Secuencia para Tabla temporal SAP Descuento Comercial
	 */
	public String getObtenerSecuenciaTempDesctoComercial(Map criteria);

	/**
	 * @param criteria
	 *            Ejecuta el proceso de insercion de datos en la tabla temporal
	 */
	public void executeGenerarReporteSAPFiDescuentoComercial(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTempReporteDescuentoComercial(Map criteria);

	/**
	 * @param criteria
	 * @return Secuencia de tabla temporal Descuento Volumen
	 */
	public String getObtenerSecTempDsctoVol(Map criteria);

	/**
	 * @param criteria
	 *            Ejecuta el proceso de insercion de datos en la tabla temporal
	 */
	public void executeGenerarReporteSAPFiDescuentoVolumen(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTempReporteDescuentoVolumen(Map criteria);

	/**
	 * @param criteria
	 * @return Oid Secuencia de Tabla Temporal
	 */
	public String getObtenerSecuenciaTempVentaLinea(Map criteria);

	/**
	 * @param criteria
	 *            Ejecuta el proceso de insercion de datos en la tabla temporal
	 */
	public void executeGenerarReporteSAPFiVentaLinea(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTempReporteVentaLinea(Map criteria);

	/**
	 * @param criteria
	 * @return Oid Secuencia de Tabla Temporal
	 */
	public String getObtenerSecuenciaTempOperaReclam(Map criteria);

	/**
	 * @param criteria
	 *            Ejecuta el proceso de insercion de datos en la tabla temporal
	 */
	public void executeGenerarReporteRECOperaReclaPedidos(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTemporalReporOperaReclaPedidos(
			Map criteria);

	/**
	 * @return La Lista de Tipos de Reportes
	 */
	public List getTipoReporteList();

	/**
	 * La Lista de Tipos de Reportes
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTipoReporteList(Map criteria);

	/**
	 * @param criteria
	 *            Ejecuta el proceso de generar Reporte MAE consultoras
	 *            bloqueadas y desbloqueadas
	 */
	public void executeGenerarReporteMAEConsultoraBloquedasDesbloq(Map criteria);

	/**
	 * @return Retorna el Id Secuencia de Tabla Temporal
	 */
	public String getObtenerSecuenciaTempConsultBloqDesb();

	/**
	 * Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTemporalReporConsultBloqDesb();

	/**
	 * Indica el tipo de plantilla de concurso
	 * 
	 * @param criteria
	 * @return
	 */
	public String getTipoPlantilla(Map criteria);

	/**
	 * @return La Lista de almacen
	 */
	public List getListaAlmacen(Map criteria);

	/**
	 * @return La Lista de origenes
	 */
	public List getOrigenSTOByTipoDocumento(Map params);

	/**
	 * RETORNA LA LISTA DE CONTROL ASISTENCIA
	 * 
	 * @param params
	 * @return
	 */
	public List getControlAsistencia(Map params);

	/**
	 * @param criteria
	 *            Ejecuta el proceso de generar Reporte MAE Consultoras Nuevas
	 *            Pedidos, Activas e Inactivas
	 */
	public void executeGenerarReporteMAEConsultorasPedidos(Map criteria);

	/**
	 * retorna la lista de detalles de pedidos no facturados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDetallePedidoNoFacturado(Map criteria);

	/* INI SA RCR PER-SiCC-2013-0003 */
	/**
	 * Retornar la diferencia de periodos de un determinado rango
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getDiferenciaPeriodos(Map criteria);

	/* FIN SA RCR PER-SiCC-2013-0003 */

	public void executeComisionesCalculadasPorRegion(Map criteria);

	public void executeCalcularComisionVentaNetaEfectiva(Map criteria);

	public void executeCalcularVentaZona(Map criteria);

	public List getTipoReporte();

	/**
	 * Obtiene un listado de los Tipos de MAV para Reporte
	 * 
	 * @return Lista con los Tipos de MAv para Reporte.
	 */
	public List getTipoMav();

	public List getTipoOferta();

	/**
	 * Ejecuta el proceso de Saldo de Campaa generado
	 * 
	 * @param criteria
	 */
	public void executeCargarTablasReporteCCCDeudorasConMasUnaCampanha(
			Map criteria);

	/**
	 * Ejecuta el proceso de Eliminacin Lgica de la parametra de Saldo Campaa
	 * 
	 * @param criteria
	 */
	public void executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanha(
			Map criteria);

	/**
	 * Ejecuta el proceso de Eliminacin Lgica de la parametra de Saldo Campaa
	 * 
	 * @param criteria
	 */
	public void executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanhaArray(
			Map criteria);

	/**
	 * Genera la data para el reporte de Primeros Pedidos con Deuda
	 * 
	 * @param method
	 */
	public void executeReporteCCCPrimPedDeud(Map map);

	/**
	 * Genera la data para el reporte de Primeros y Segundos Pedidos con Deuda
	 * 
	 * @param method
	 */
	public void executeReporteCCCPrimSegPedDeud(Map map);

	public void deleteTableReporteCCCPrimPedDeud(Map criteria);

	public void deleteTableReporteCCCPrimSegPedDeud(Map criteria);

	/**
	 * Ejecuta el proceso de Consultoras a Chequear
	 * 
	 * @param criteria
	 */
	public void executeCargaTemporalReportePEDConsultorasChequear(Map criteria);

	/**
	 * Ejecuta el proceso de Eliminacin en la Tabla Temporal de Reportes de
	 * Consultoras a Chequear
	 * 
	 * @param criteria
	 */
	public void executeLimpiarTablaTemporalReportePEDConsultorasChequear(
			Map criteria);

	/**
	 * Lanza el stored que carga las tablas para el reporte
	 * 
	 * @param params
	 */
	public void executeCargarTablasReporteCCCDiasCartera(Map params);

	/**
	 * Limpia las tablas que fueron cargadas en el proceso
	 * executeCargarTablasReporteCCCDiasCartera
	 * 
	 * @param params
	 */
	public void deleteTablasReporteCCCDiasCartera(Map params);

	/**
	 * Carga los reportes de nueva y/o rechazadas en tabla temporal para su
	 * exportacin a csv
	 * 
	 * @param criteria
	 * @return
	 */
	public void executeCargaTemporalReporteMAEConsultorasPedidos(Map criteria);

	/**
	 * Genera el archivo CSV desde la tabla temporal nueva y/o rechazadas
	 * 
	 * @param parameterMap
	 * @return
	 */
	public Map generarReporteMAEConsultorasPedidosCSV(Map criteria);

	/**
	 * Limpia la tabla temporal de nueva y/o rechazadas para el id proceso
	 * generado
	 * 
	 * @param criteria
	 */
	public void deleteTemporalReporteMAEConsultorasPedidos(Map criteria);

	/**
	 * @param criteria
	 *            Metodo que carga la tabla para generar el reporte de Cuadre
	 *            SAP
	 */
	public void executeCargaReporteRECCuadreSAP(Map params) throws Exception;

	/**
	 * Proceso que pobla las tablas para generar el reporte
	 * 
	 * @param params
	 */
	public void executeReportePEDExportarDemandaAnticipada(Map params);

	/**
	 * @param criteria
	 *            Metodo que carga la tabla para generar el reporte de Centro de
	 *            Acopio Facturado
	 */
	public void executeCargaReporteCentroAcopioFacturado(Map params);

	/**
	 * Carga data en tabla temporal para su visualizacion en el Reporte MAV de
	 * Atencion por Campaa
	 * 
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
	 *            Metodo que carga la tabla para generar el reporte de
	 *            simulacion de faltantes
	 */
	public void executeCargaReporteSimulacionFaltantes(Map params);

	/**
	 * @param criteria
	 *            Metodo que registra todos los datos obtenidos en una
	 *            temportal.
	 * 
	 * */
	public void executeReportePedidosEnviados(Map params);

	/**
	 * @param criteria
	 *            Metodo que registra todos los datos obtenidos en una
	 *            temportal.
	 * 
	 * */
	public void executeReportePosiblesCandidatas(Map params);

	/**
	 * @param params
	 */
	public void executeReportePedidosRecibidos(Map params);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte factura detalle Sac
	 */
	public void executeReporteFacturaDetalle(Map criteria);

	/**
	 * Genera archivo CSV en Oracle con la informacion de Reporte ZON Unidades
	 * Geograficas
	 * 
	 * @param criteria
	 */
	public Map generarReporteZONUnidadesGeograficasCSV(Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte direcciones consultas
	 */
	public void executeReporteGISDireccionConsulta(Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta consulta de direccion de consulta.
	 */
	public void insertConsultaDireccionConsulta(Map criteria);

	/**
	 * Metodo que elimina los registros de la tabla direccion de consulta.
	 */
	public void deleteConsultaDireccionConsulta();

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte direcciones consultas en CSV
	 */
	public Map generarReporteGISEnviarDireccionConsultorasCSV(Map criteria);

	/**
	 * Devuelve lista de zonas.
	 * 
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
	 * 
	 * @param criteria
	 * @return
	 */
	public String getTipoConcurso(Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Gerente Con Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaCSV(Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Gerente Con Fecha Region Zona en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaRegionZonaCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Gerente Detalle Con Sin Fecha TipoCargo en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaTipoCargoCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Gerente Detalle Con Sin Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Gerente Sin Fecha Region en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Gerente Sin Fecha Region Zona en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionZonaCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Gerente Sin Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaCSV(Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Envios Fecha Campanha Con Sin
	 *            Fecha en CSV
	 */
	public Map generarReporteMAVEnviosFechaCampConSinFechaCSV(Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Consultora Con Fecha Region en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Consultora Con Fecha Region Zona en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionZonaCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Camapanha Por
	 *            Consultora Con Fecha CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha
	 *            Consultora Detalle Con Sin Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraDetalleConSinFechaCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Consultora Sin Fecha Region en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Consultora Sin Fecha Region Zona en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionZonaCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Consultora Sin Fecha en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Metodo que ejecuta el reporte Atencion Fecha Campanha Por
	 *            Gerente Con Fecha Region en CSV
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaRegionCSV(
			Map criteria);

	/**
	 * @param criteria
	 *            Obtiene el codigo del Period sea anteriores,siguientes,
	 *            descuento.
	 */
	public String getCodigoPeriodoASD(Map criteria);

	/**
	 * Ejecuta calculo de Comisiones Venta Retail
	 * 
	 * @param criteria
	 */
	public void executeComisionesVentaRetail(Map criteria);

	/**
	 * Se genera la data para el Reporte del Programa de Reconocimiento
	 * 
	 * @param criteria
	 */
	public void executeINCProgramaReconocimiento(Map criteria);

	/**
	 * Se genera la data para el Reporte del Programa de Reconocimiento COLOMBIA
	 * 
	 * @param criteria
	 */
	public void executeINCProgramaReconocimiento2(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public Map generarReporteGestionarConsultoraCSV(Map criteria);

	/**
	 * Reporte de Zonas a Facturar por Fecha COLOMBIA
	 * 
	 * @param criteria
	 */
	public void executeReporteCRAZonasFacturaFecha(Map criteria);

	/**
	 * Genera Reporte de Porcentaje de Desviacin de Pedidos
	 * 
	 * @param criteria
	 */
	public void executeReporteIMPPorceDesviPedido(Map criteria);

	/**
	 * Crea la data para el reporte de cupones electronicos
	 * 
	 * @param params
	 */
	public void executeReporteINCCuponesElectronicos(Map criteria);

	/**
	 * Genera el reporte de Cupones Electronicos
	 * 
	 * @param parameterMap
	 * @return
	 */
	public Map generarReporteINCCuponesElectronicosCSV(Map criteria);

	/**
	 * Genera el reporte de Nuevas Unidades de Atendidas
	 * 
	 * @param criteria
	 * @return
	 */
	public Map generarReporteCUPNuevaUnidadAtendidaCSV(Map criteria);

	/**
	 * Ejecuta Eliminacion de Gerentes Retiradas para Comision de Base 07
	 * 
	 * @param params
	 */
	public void executeEliminarComisionesGerenteRetiradas(Map criteria);

	/**
	 * Genera data para la tabla temporal ccc_tempo_saldo_diario, para el
	 * Reporte Auditora - Saldo de Cuentas por Cobrar
	 * 
	 * @param criteria
	 */
	public void executeAuditoriaSaldoCuentasPorCobrar();

	/**
	 * @param criteria
	 * @return
	 */
	public Map executeReporteMgpedxdiaCsv1(Map criteria);

	/**
	 * Ejecuta la eliminacion e insercion de las zonas seleccionadas
	 */
	public void executeDeleteInsertZonasDistribucionFacturacionReal(Map params);
	
	/**
	 * @param params
	 */
	public void executeDeleteInsertZonasInventarioCampo(Map params);

	/**
	 * @param criteria
	 * @return
	 */
	public Map executeReporteAPEDistribucionCdrsFacturacionReal(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public Map executeReporteAPEIventarioCampo(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public Map executeReporteSACFacturacionDetalle(Map criteria);

	/**
	 * Genera Reporte SIC Detalle Unidades Atendidas Faltantes en tabla temporal
	 * 
	 * @param criteria
	 */
	public void executeBeforeReporteSICDetalleUnidadesAtendidasFaltantes(
			Map criteria);

	/**
	 * Genera Reporte SIC Detalle Unidades Atendidas Faltantes en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteSICDetalleUnidadesAtendidasFaltantesCSV(
			Map criteria);

	/**
	 * Genera Reporte MAE Consejeras Bloqueadas Desbloqueadas en tabla temporal
	 * 
	 * @param criteria
	 */
	public void executeBeforeReporteMAEConsejerasBloqueadasDesbloqueadas(
			Map criteria);

	/**
	 * Genera Reporte MAE Consejeras Bloqueadas Desbloqueadas en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteMAEConsejerasBloqueadasDesbloqueadasCSV(
			Map criteria);

	/**
	 * Genera Reporte COB Detallado Recuperacion Cartera Cobrador en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteCOBDetalladoRecuperacionCarteraCobradorCSV(
			Map criteria);

	/**
	 * Genera Reporte COB Carga Masiva Gestion
	 * 
	 * @param criteria
	 */
	public void executeReporteCOBCargaMasivaGestionCSV(Map criteria);

	/**
	 * Genera Reporte INC Puntos Obtenidos Bolsa Faltantes en tabla temporal
	 * 
	 * Atendidas Faltantes
	 * 
	 * @param criteria
	 */
	public void executeBeforeReporteINCPuntObtenidosBolsaFaltantesInc(
			Map criteria);

	/**
	 * Genera Reporte INC Puntos Obtenidos Bolsa Faltantes en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteINCPuntObtenidosBolsaFaltantesIncCSV(Map criteria);

	/**
	 * Genera Reporte COB Detallado Cobranza 31 dias en tabla temporal para
	 * formato CSV
	 * 
	 * @param criteria
	 */
	public void insertReporteCOBDetalladCobranza31dias(Map criteria);

	/**
	 * Genera Reporte PED Seguimiento Consultora en tabla temporal para formato
	 * CSV
	 * 
	 * @param criteria
	 */
	public void insertReportePEDSeguimientoConsultora(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void insertReporteAvanceFacturadoProgramaReconocimientoVZ(Map criteria);
	
	/**
	 * Ejecuta procedimiento para creacion de la data del ReportePEDAvanceFacturadoProgramaReconocimientoVZ 
	 * @param criteria
	 */
	public void executeReportePEDAvanceFacturadoProgramaReconocimientoVZ(Map criteria);

	/**
	 * Genera Reporte COB Detallado Cobranza 31 dias en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteCOBDetalladCobranza31diasCSV(Map criteria);

	/**
	 * Genera Reporte COB Saldos Pendientes en tabla temporal para formato CSV
	 * 
	 * @param criteria
	 */
	public void insertReporteCOBSaldosPendientes(Map criteria);

	/**
	 * Genera Reporte COB Saldos Pendientes en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteCOBSaldosPendientesCSV(Map criteria);

	/**
	 * Genera Reporte CCC Liquidacion Cobranzas en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteCCCLiquidacionCobranzasCSV(Map criteria);

	/**
	 * Genera archivo TXT en Oracle con informacion del Reporte Historico de
	 * Operacion Crediticia
	 * 
	 * @param criteria
	 * @return
	 */
	public Map generarHistoricoOperacionCrediticiaTXT(Map criteria);

	/**
	 * Genera los datos del Reporte Historico de Operacion Crediticia
	 * 
	 * @param criteria
	 */
	public void executeGenerarHistoricoOperacionCrediticia(Map criteria);

	/**
	 * Genera Reporte COB Detallado Movimiento Recuperacion Incobrable en tabla
	 * temporal
	 * 
	 * @param params
	 */
	public void executeBeforeReporteCOBDetalladoMovRecuperacionIncobrable(
			Map criteria);

	/**
	 * Genera Reporte COB Detallado Movimiento Recuperacion Incobrable en
	 * formato CSV
	 * 
	 * @param parameterMap
	 */
	public void executeReporteCOBDetalladoMovRecuperacionIncobrableCSV(
			Map criteria);

	/**
	 * Genera Reporte CCC Medios Magneticos en tabla temporal
	 * 
	 * @param params
	 */
	public void executeBeforeReporteCCCArchivosMediosMagneticos(Map criteria);

	/**
	 * Genera Reporte CCC Medios Magneticos en formato CSV
	 * 
	 * @param parameterMap
	 */
	public void executeGeneraReporteCCCArchivosMediosMagneticosCSV(Map criteria);

	/**
	 * Genera Reporte MAE Vinculos Cliente en tabla temporal
	 * 
	 * @param criteria
	 */
	public void executeBeforeReporteMAEVinculosCliente(Map criteria);

	/**
	 * Genera Reporte MAE Vinculos Cliente en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteMAEVinculosClienteCSV(Map criteria);

	/**
	 * Genera Reporte CCC Detalle Cuenta Corriente en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteCCCDetalleCuentaCorrienteContableCSV(Map criteria);

	/**
	 * Genera Reporte CCC Detallado Pago x Regularizar en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteCCCDetalladoPagoxRegularizarCSV(Map criteria);

	/**
	 * Genera data en tabla temporal para el Reporte INC Puntajes Obtenidos y
	 * Puntajes Faltantes en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeBeforeReporteINCPuntObtenidosPuntFaltantes(Map criteria);

	/**
	 * Genera Reporte INC Puntajes Obtenidos y Puntajes Faltantes en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteINCPuntObtenidosPuntFaltantesCSV(Map criteria);

	/**
	 * Genera Reporte CCC Auditoria Saldox Cuentas x Cobrar en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteCCCAuditoriaSaldoCuentasPorCobrarCSV(Map criteria);

	/**
	 * Mtodo que se encarga de generar el reporte Detallado IFC en formato CSV.
	 * 
	 * @param criteria
	 *            the criteria
	 */
	public void executeReporteDetalleIFCCSV(Map criteria);

	/**
	 * @param historicoReporte
	 */
	public void insertHistoricoReporte(HistoricoReporte historicoReporte);

	/**
	 * @param historicoReporte
	 */
	public void updateHistoricoReporte(HistoricoReporte historicoReporte);

	/**
	 * Execute reporte cob detallad cobranza31dias xlsx.
	 * 
	 * @param criteria
	 *            the criteria
	 */
	public void executeReporteCOBDetalladCobranza31diasXLSX(Map criteria);

	/**
	 * Método que se encarga de generar el reporte Clasificaciones por Cliente
	 * en formato CSV.
	 * 
	 * @param criteria
	 *            the criteria
	 */
	public void executeReporteMAEClasificacionXClienteCSV(Map criteria);

	/**
	 * Verifica si hay lista para el Proceso REC Enviar CDRs Recepcionados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getExisteListaProcesoRECEnviarCDRRecepcionados(Map criteria);

	/**
	 * Devuelve lista en base a los criterios de busqueda (Proceso REC Enviar
	 * CDRs Recepcionados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListaProcesoRECEnviarCDRRecepcionados(Map criteria);

	/**
	 * Metodo que se encarga de llenar la tabla temporal de movimientos de
	 * territorio
	 * 
	 * @param criteria
	 */
	public void executeCargaTemporalReporteZONMovimientoTerritorio(Map criteria);

	/**
	 * Mtodo que se encarga de generar el reporte de movimientos territorio por
	 * campaa en formato CSV
	 * 
	 * @param criteria
	 * @return
	 */
	public Map generarReporteZONMovimientoTerritorioCSV(Map criteria);

	/**
	 * Genera Reporte CCC Gasto Cupon en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteCCCGastoCuponCSV(Map criteria);

	/**
	 * Actualiza los registros no terminados de Auditoria con la fecha actual
	 */
	public void actualizarRegistroAuditoriaReinicioServidor();

	/**
	 * Ejecuta el reporte de recall por trazabilidad
	 * 
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
	 * Mtodo que se encarga de generar el reporte de Puntos Consultora en
	 * formato CSV
	 * 
	 * @param params
	 */
	public void executeReporteINCPuntosConsultoraCSV(Map params);

	/**
	 * Mtodo que se encarga de llenar la tabla temporal de Puntos Campania
	 * 
	 * @param params
	 */
	public void executeBeforeReporteINCPuntosCampania(Map params);

	/**
	 * Mtodo que se encarga de generar el reporte de Puntos Campania en formato
	 * CSV
	 * 
	 * @param parameterMap
	 */
	public void executeReporteINCPuntosCampaniaCSV(Map params);

	/**
	 * Mtodo que se encarga de llenar la tabla del reporte retiradas sin deudas
	 * 
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
	 * 
	 * @param params
	 */
	public void executeReporteCOBPedidosFacturadosConDeuda(Map params);

	/**
	 * Mtodo que se encarga de llenar la tabla de Primeros y Segundo Pedidos con
	 * Deuda
	 * 
	 * @param params
	 */
	public void executeReporteCOBPrimerosSegundoPedidosConDeuda(Map params);

	/**
	 * Ejecuta el proceso del Primer, Segundo y Tercer Pedido con Deuda
	 */
	public void executeReporteCOBPrimerSegundoTercerPedidoConDeuda(Map params);

	/**
	 * Inserta data en Tabla Temporal Reporte de ]Liquedacion Cobranzas
	 * (Consolidado Zona)
	 * 
	 * @param criteria
	 */
	public void insertReporteCCCLiquidacionCobranzasCZ(Map criteria);

	/**
	 * Inserta data en Tabla Temporal Reporte de ]Liquedacion Cobranzas
	 * (Detallado Zona)
	 * 
	 * @param criteria
	 */
	public void insertReporteCCCLiquidacionCobranzasDZ(Map criteria);

	/**
	 * Obtiene el estado del indicador del cliente o cedula
	 * 
	 * @param criteria
	 * @return
	 */
	public String getIndicadorClienteCedula(Map criteria);

	/**
	 * Genera Reporte CUP Nuevas Cupones en tabla temporal
	 * 
	 * @param criteria
	 */
	public void executeBeforeReporteCUPNuevasCupones(Map criteria);

	/**
	 * Genera Reporte CUP Nuevas Cupones en formato CSV
	 * 
	 * @param criteria
	 */
	public void executeReporteCUPNuevasCuponesCSV(Map criteria);

	/**
	 * Devuelve ID sgte para insertar en el Historico Reporte
	 * 
	 * @return
	 */
	public Long getDevuelveIdSgteCodHistoricoReporte();

	/**
	 * Ejecuta el reporte de Control Facturacion y Entrega de Pedido
	 * 
	 * @param parameterMap
	 * @return
	 */
	public Map executeReporteSACControlFacturacionEntregaPedido(Map criteria);

	/**
	 * Ejecuta el reporte de resultados
	 * 
	 * @param criteria
	 * @return
	 */
	public Map executeReporteLETResultado(Map criteria);

	/**
	 * Ejecuta el reporte SGR de solicitudes
	 * 
	 * @param criteria
	 * @return
	 */
	public Map executeReporteSGRSolicitudesTod(Map criteria);

	/**
	 * Obtiene el siguiente valor de la secuencia como parametro
	 * 
	 * @return
	 */
	public String getSecuenciaIndicadorControlEntregaNextValue();

	/**
	 * Genera Reporte CCC Medios Magneticos cuenta corriente bimennsual en tabla
	 * temporal
	 * 
	 * @param params
	 */
	public void executeBeforeReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual(
			Map criteria);

	/**
	 * Genera Reporte CCC Medios Magneticos Cuenta Corriente Bimensual en
	 * formato CSV
	 * 
	 * @param params
	 */
	public void executeGeneraReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensualCSV(
			Map criteria);

	/**
	 * Genera Reporte CCC Antiguedad Saldos en tabla temporal
	 * 
	 * @param params
	 */
	public void executeBeforeReporteCCCAntiguedadSaldos(Map criteria);

	/**
	 * Genera Reporte CCC Antiguedad Saldos en formato CSV
	 * 
	 * @param params
	 */
	public void executeGeneraReporteCCCAntiguedadSaldosCSV(Map criteria);

	/**
	 * Genera la data para el reporte de proyeccion LEC
	 * 
	 * @param params
	 */
	public void executeReporteLECEnviarReporteProyecion(Map params);

	/**
	 * Obtiene las regiones del reporte de proyccion LEC
	 * 
	 * @param params
	 * @return
	 */
	public List getRegionesReporteLECEnviarReporteProyecion(Map params);

	/**
	 * Obtiene las zonas del reporte de proyccion LEC
	 * 
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
	 * Genera Reporte CCC Detallado Consultoras Incobrable en formato CSV -
	 * Obteniendo DATA
	 * 
	 * @param criteria
	 */
	public void executeReporteCCCDetalladoConsultorasIncobrable(Map criteria);

	/**
	 * Genera Reporte CCC Detallado Consultoras Incobrable en formato CSV
	 * 
	 * @param criteria
	 * @return
	 */
	public Map executeReporteCCCDetalladoConsultorasIncobrableCSV(Map criteria);

	/**
	 * Ejecuta la eliminacion e insercion de los detalles de Consultoras
	 * Incobrables
	 */
	public void executeDeleteInsertCCCDetalladoConsultorasIncobrable(Map params);

	/**
	 * Genera Reporte CCC Detallado Provision Incobrable en formato CSV
	 * 
	 * @param criteria
	 * @return
	 */
	public Map executeReporteCCCDetalladoProvisionIncobrableCSV(Map criteria);

	/**
	 * Genera Reporte CCC Detallado Provision Incobrable Movimiento en formato
	 * CSV
	 * 
	 * @param criteria
	 * @return
	 */
	public Map executeReporteCCCDetalladoProvisionIncobrableMovCSV(Map criteria);

	/**
	 * Genera la data para el reporte de Recaudo por Campaña
	 * 
	 * @param params
	 */
	public void executeReporteCCCConsolidadoRecaudoCampana(Map params);

	/**
	 * Elimina la data para el reporte de Recaudo por Campaña
	 * 
	 * @param params
	 */
	public void deleteReporteCCCConsolidadoRecaudoCampana(Map params);

	/**
	 * Genera el reporte de territorios y unidades geograficas
	 * 
	 * @param parameterMap
	 */
	public void executeReporteZONTerritorioUnidadGeograficaCSV(Map params);

	/**
	 * Genera el reporte de recuperacion de cobranza ffvv
	 * 
	 * @param params
	 * @return
	 */
	public void executeGenerarReporteCOBRecuperacionCobranzaFFVVTXT(Map params);

	/**
	 * Prepara los datos para el reporte
	 * 
	 * @param params
	 */
	public void executePrepararReporteCOBRecuperacionCobranzaFFVVTXT(Map params);

	/**
	 * Genera Reporte CCC Registro de Ventas Bolivia en formato CSV
	 * 
	 * @param params
	 */
	public void executeGeneraReporteCCCRegistroVentasBoliviaCSV(Map criteria);

	/**
	 * Genera Reporte CCC Registro de Abonos Bolivia en formato CSV
	 * 
	 * @param params
	 */
	public void executeGeneraReporteCCCRegistroAbonosBoliviaCSV(Map criteria);

	/**
	 * Genera Reporte CCC Buro Credito en formato CSV
	 * 
	 * @param criteria
	 */

	public Map executeReporteCCCBuroCreditoCSV(Map criteria);

	/**
	 * @param criteria
	 *            Elimina los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTemporalReporProyeccion(Map criteria);

	/**
	 * Genera archivo CSV en Oracle con la informacion de Cabeceras Facturas
	 * Anuladas (Bolivia)
	 * 
	 * @param criteria
	 * @return
	 */
	public Map generarReporteVENCabecerasFacturasAnuladasCSV(Map criteria);

	/**
	 * Genera archivo CSV en Oracle con la informacion de Detalle NC y Marca UN (Bolivia)	
	 * @param criteria
	 * @return
	 */
	public Map generarReporteVENDetalleNCMarcaUNCSV(Map criteria);
	
	/**
	 * Genera archivo CSV en Oracle con la informacion de Detalle Nota de Debito y Marca UN (Bolivia)
	 * @param criteria
	 * @return
	 */
	public Map generarReporteVENDetalleNDebitoMarcaUNCSV(Map criteria);
	
	/**
	 * Genera archivo CSV en Oracle con la informacion de Detalle de Productos Atendidos x UN (Bolivia)
	 * @param criteria
	 * @return
	 */
	public Map generarReporteVENDetalleProductosAtendidosUNCSV(Map criteria);

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
	
	/**
	 * Generar Reporte de Interes por Tipo de Reporte	 
	 */
	public Map executeReporteCCCInteresCCorrienteCSV(Map criteria);
	
	/**
	 * Inserta en una Tabla temporal el Reporte de Interes
	 */
	public void executeGenerarReporteCCCInteresCCorriente(Map params);
	
	/**
	 * Mtodo que se encarga de generar el reporte de Programa Puntos por Nacional
	 * formato CSV
	 * 
	 * @param params
	 */
	public void executeReporteINCDetallePuntosRegionZonaByNacionalCSV(Map params);	
	
	/**
	 * Mtodo que se encarga de generar el reporte de Programa Puntos por Region
	 * formato CSV
	 * 
	 * @param params
	 */
	public void executeReporteINCDetallePuntosRegionZonaByRegionCSV(Map params);	
	
	/**
	 * Mtodo que se encarga de generar el reporte de Programa Puntos por Zona
	 * formato CSV
	 * 
	 * @param params
	 */
	public void executeReporteINCDetallePuntosRegionZonaByZonaCSV(Map params);	
	
	/**
	 * Mtodo que se encarga de generar el reporte de Programa Puntos por Consultora
	 * formato CSV
	 * 
	 * @param params
	 */
	public void executeReporteINCDetallePuntosRegionZonaByConsultoraCSV(Map params);	
	
	/**
	 * Mtodo que se encarga de generar el reporte de Programa Puntos por Campania
	 * formato CSV
	 * 
	 * @param params
	 */
	public void executeReporteINCDetallePuntosRegionZonaByCampaniaCSV(Map params);
	
	/**
	 * Mtodo que se encarga de generar el reporte para Provisión Contable Ingresos
	 * formato CSV
	 * 
	 * @param params
	 */
	public void executeReporteINCProvisionContableIngresosCSV(Map params);	
	
	/**
	 * Mtodo que se encarga de generar el reporte para Provisión Contable Gastos
	 * formato CSV
	 * 
	 * @param params
	 */
	public void executeReporteINCProvisionContableGastosCSV(Map params);	
	
	
    public Map executeReportePERResumenDiarioPercepcionesSunatCSV(Map params);
	
	public Map executeReportePERResumenDiarioPercepcionesSunatTXT(Map params);
	
	
	
}

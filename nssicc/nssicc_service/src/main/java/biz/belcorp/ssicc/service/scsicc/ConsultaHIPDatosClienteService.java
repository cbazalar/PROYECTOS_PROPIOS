package biz.belcorp.ssicc.service.scsicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * Service con metodos para las consultas invocados por la pantalla de HiperConsulta
 * 
 * @author <a href="">Sergio Apaza</a>
 * 
 */
public interface ConsultaHIPDatosClienteService extends Service {

	/**
	 * Se recupera los datos principales de la consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDatosGenerales(Map criteria);
	
	/**
	 * Se verifica si la consultora tiene asignado un bloqueo
	 * 
	 * @param criteria
	 * @return
	 */
	public String getMotivoBloqueo(Map criteria);
		
	/**
	 * Se recupera los datos del domicilio de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDireccionDomicilio(Map criteria);
	
	/**
	 * Se recupera los datos de la direccion de despacho de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDireccionDespacho(Map criteria);
	
	/**
	 * Se recupera informacion del ultimo pedido de la Consultora (periodo, monto, numeroBoleta)
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getUltimoPedido(Map criteria);
	
	
	/**
	 * Se recupera el monto del Saldo unico de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public String getSaldoUnico(Map criteria);
	
	/**
	 * Se recupera el saldo de la campaña del ultimo pedido
	 * 
	 * @param criteria
	 * @return
	 */
	public String getSaldoCampana(Map criteria);
	
	/**
	 * Se recupera las unidades administrativas de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getUnidadesAdministrativas (Map criteria);
	
	/**
	 * Se recupera los Tipos/SubTipos y Tipo/Clasificaciones de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTipificacionCliente (Map criteria);
	
	/**
	 * Se recupera las opciones de Consulta que tiene autorizado realizar el usuario logueado
	 * 
	 * @param criteria
	 * @return
	 */
	public List getOpcionesPermitidas(Map criteria);
	
	/**
	 * Se valida si el usuario logueado tiene acceso ingresar una opcion de pantalla secundaria de HiperConsulta
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validarOpcionSecundaria(Map criteria);
	
	
	/**
	 * Se recupera los reclamos de la consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCabeceraReclamos (Map criteria);
	
		
	/**
	 * Se recupera el detalle de un determinado reclamo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDetalleReclamo (Map criteria);
	
	/**
	 * Se recupera informacion de las boletas de recojo relacionado a un reclamo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getBoletasRecojo (Map criteria);
	
	
	/**
	 * Se recupera las consultoras que cumplan con los filtros seleccionados
	 * 
	 * @param criteria
	 * @return
	 */
	public List getClientesByCriteria (Map criteria);
	
	public List getClientesByCriteria2 (Map criteria);
	
	/**
	 * Se recupera los tipos de Documentos de identidad
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTiposDocumento (Map criteria);

	
	/**
	 * Se recupera los concursos que ha participado o puntuado una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConcursos (Map criteria);
	
	/**
	 * Se recupera el detalle de los puntajes ganados por una consultora en un determinado concurso
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPuntajeDetalleConcurso(Map criteria);
	
	/**
	 * Se recupera el resumen de los puntajes ganados por una consultora en un determinado concurso
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPuntajeResumenConcurso(Map criteria);
			
	/**
	 * Se recupera la informacion de la hija Dupla asociada
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getDuplaAsociada(Map criteria);
	
	/**
	 * Se recupera informacion de los premios x niveles de un determinado concurso
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPremiosByNivel(Map criteria);
	
	/**
	 * Se recupera la informacion de las bolsas faltantes de una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getBolsaFaltantes(Map criteria);

	
	/**
	 * Se recupera el saldo a Pagar para el proximo Pedido, dependiendo si ha cerrado la region de la consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public String getSaldoPagar(Map criteria);
		
	/**
	 * Genera el cronograma de actividades
	 * 
	 * @param criteria
	 * @return
	 */
	public List executaCronogramaActividadesList(Map criteria);
	
	/**
	 * Lista de los atributos del gerente de Zona
	 * 
	 * @param criteria
	 * @return
	 */
	public List getGerenteZonaList(Map criteria);

	/**
	 * Lista de los atributos del gerente de Region
	 * 
	 * @param criteria
	 * @return
	 */
	public List getGerenteRegionList(Map criteria);
	
	/**
	 * Genera la lista de los envios preferenciales
	 * 
	 * @param criteria
	 * @return
	 */
	public List executaEnviosPreferencialesList(Map criteria);
	
	/**
	 * Suscribe para envios preferenciales
	 * 
	 * @param criteria
	 */
	public void executaSuscribirEnviosPreferenciales(Map criteria);
	
	/**
	 * Fin de Suscripcion para envios preferenciales
	 * 
	 * @param criteria
	 */
	public void executaFinSuscribirEnviosPreferenciales(Map criteria);
	
	/**
	 * Lista de los atributos de los responsables por seccion 
	 * 
	 * @param criteria
	 * @return
	 */
	public List getResponsableSeccionList(Map criteria);
	
	/**
	 * Lista de los historicos de Bloqueo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getHistoriaBloqueoList(Map criteria);
	
	/**
	 * Lista Consultoras
	 * 
	 * @param criteria
	 * @return
	 */
	public List getEduConsultorasList(Map criteria);

	/**
	 * Lista de Cursos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getEduCursosList(Map criteria);
	
	/**
	 * Lista Tipo Certificacion
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTipoCertificacionList(Map criteria);	
	
	/**
	 * Lista Premios Cliente
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPremiosClienteList(Map criteria);
	
	/**
	 * Fecha para la certificacion formateada en MMYYYY
	 * 
	 * @param paramsOidCli
	 * @return
	 */
	public String getFecMesAnno(Map paramsOidCli);

	/**
	 * Genera las Certificaciones
	 * 
	 * @param criteria
	 */
	public void execGeneraCertificacion(Map criteria);
	
	/**
	 * Genera los mensajes para las Certificaciones
	 * 
	 * @param paramsTemp
	 * @return
	 */
	public String getOidMsj(Map paramsTemp);
	
	/**
	 * Se recupera el promedio de Venta de las Cuentas Corrientes x Facturacion de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public String getPromedioVentas(Map criteria);
	
	/**
	 * Lista los productos solicitados por la consultora
	 * 
	 * @param criteria (
	 * 		  codigoConsultora )
	 * @return Lista de productos solicitados por la consultora
	 */
	public List getProductosSolicitadosConsultoras(Map criteria);
	
	/**
	 * Lista los productos despachados automaticamente
	 * 
	 * @param criteria (
	 * 		  codigoCliente )
	 * @return Lista de productos despachados automaticamente
	 */
	public List getProductosDespachadosAutomaticamente(Map criteria);
	
	/**
	 * Recupera la dupla actual
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDuplaActual(Map criteria);
	
	/**
	 * Genera una solicitud Dupla Cyzone (STO)
	 * 
	 * @param criteria
	 */
	public void execGenerarSolicitudDuplaCyzone(Map criteria);

	/**
	 * Genera una solicitud de Actualizacion de Datos de Consultora (STO)
	 * 
	 * @param criteria
	 */
	public void execGenerarSolicitudActualizacionDatos(Map criteria);

	/**
	 * Lista los productos despachados automaticamente
	 * 
	 * @param criteria (
	 * 		  codigoCliente )
	 * @return Lista de pedidos de la consultora
	 */
	public List getPedidosConsultora(Map criteria);

	
	/**
	 * Lista el detalle de un pedido
	 * 
	 * @param criteria (
	 * 		  oidSoliCabecera )
	 * @return Detalle de pedido
	 */
	public List getDetallePedido(Map criteria);

	
	
	/**
	 * Retorna los CDR Rechazados de la consultora
	 * @param criteria
	 * @return
	 */
	public List getConsultaCdrRechazados(Map criteria);

	/**
	 * Retorna el detalle de los CDR Rechazados
	 * @param criteria
	 * @return
	 */
	public List getDetalleCdrRechazados(Map criteria);

	/**
	 * Retorna la lista de tipo de premio
	 * @param criteria
	 * @return
	 */
	public List getListaTipoPremios(Map criteria);

	/**
	 * Retorna data si es empedida de pasar CDR , si retorna nullo o cadena vacia  puede 
	 * pasar CDR 
	 * @param criterios
	 * @return
	 */
	public String getImpedidaPasarCdr(Map criterios);

	/**
	 * Retorna data si es empedida de pasar Pedido , si retorna nullo o cadena vacia puede pasar pedido
	 * @param criterios
	 * @return
	 */
	public String getImpedidaPasarPedido(Map criterios);

	/**
	 * Se recupera los vinculos y referencias del cliente
	 * 
	 * @param criteria
	 * @return
	 */
	public List getVinculosReferencias(Map criteria);

	/**
	 * Obtiene el promedio de ventas de las ultimas 5 campañas de la consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public String getPromedioVentasxCampanhas(Map criteria);
	
	/**
	 * Obtiene todos los medios de comunicacion de la consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getMediosComunicacion (Map criteria);

	/**
	 * Obtener los datos del lider de una seccion
	 * 
	 * @param criteria
	 * @return
	 */
	public List getLiderSeccionList(Map criteria);

	/**
	 * Se verifica si la consultora tiene asignado una clasificacion LOVE
	 * 
	 * @param criteria
	 * @return
	 */
	public String getClasificacionLove(Map criteria);

	/**
	 * Se recupera la informacion de los premios atendidos y faltantes de una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPremiosAtendidosFaltantes(Map criteria);

	/**
	 * Se recupera la informacion de las recomendaciones de una consultora para un determinado concurso
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRecomendaciones(Map criteria);

	/**
	 * Se recupera los tipos de vinculos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTiposVinculos(Map criteria);
	
	/**
	 * Se recupera la informacion de las consultoras vinculadas
	 * 
	 * @param criteria
	 * @return
	 */
	public List getHistoricoVinculos(Map criteria);

	/**
	 * Recupera la fecha castigada para un determinado cliente
	 * 
	 * @param criterios
	 * @return
	 */
	public String getFechaCastigada(Map criterios);

	/**
	 * Recupera el limite de credito para un determinado cliente
	 * @param criteria
	 * @return
	 */
	public String getLimiteCredito(Map criteria);
	
	
	/**
	 * Se recupera el saldo Vencido y el Saldo por Pagar
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getSaldoVencidos(Map criteria);

	/**
	 * Se recupera los datos de Familia Protegida
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDatosFamiliaProtegida(Map criteria);

	/**
	 * Lista  de las solicitudes de poliza de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getSolicitudesPoliza(Map criteria);
	
	/**
	 * Lista  los historicos de cargos de la poliza de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getHistoricoCargosPoliza(Map criteria);
	
	/**
	 * @param criteria
	 * @return El monto de Plan Superacion
	 */
	public String getObtenerMontoPlanSuperacion(Map criteria);

	/**
	 * Se valida si el cliente tiene imagenes escaneados de Solicitudes de Credito
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validarImagenesEscaneoSC(String codigoCliente);

	/* INI SA PER-SiCC-2012-0385 */
	/**
	 * Se recupera los datos asociados al codigo de Venta Principal
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDatosCodigoVentaPrincipal(Map criteria);

	/**
	 * Se recupera la lista de codigo de Venta relacionados a un codigo de Venta Principal
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListCodigoVentaRelacionados (Map criteria);

	/**
	 * Se recupera la lista de venta exclusiva relacionados a un codigo de Venta Principal
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListVentaExclusiva (Map criteria);

	/**
	 * Se recupera la lista de Limite Venta relacionado a un codigo de Venta Principal
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListLimiteVenta (Map criteria);
	
	/**
	 * Se recupera la lista de Control Stock relacionado a un codigo de Venta Principal
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListControlStock (Map criteria);
	/* FIN SA PER-SiCC-2012-0385 */
	
	public String getTipoConsultora(Map criteria);

	public List getListCoberturaCentroAcopio(Map criteria);

	/**
	 * Obtiene datos adicionales de la cabecera del reclamo
	 * @param oidCabecera
	 * @return
	 */
	public List getAdicionalesCabeceraReclamo(Map criteria);

	/**
	 * Obtiene los datos de cabecera del detalle de pedido solicitado
	 * @param secuencia
	 * @return
	 */
	public List getDatosCabeceraDetallePedidoSolicitado(String secuencia);
	
	/**
	 * Se recupera los datos de la Poliza
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDatosPoliza(Map criteria);
	
	/**
	 * Se recupera historial de cobros por el Seguro
	 * 
	 * @param criteria
	 * @return
	 */
	public List getHistorialCobrosSeguro(Map criteria);
	
	/**
	 * Se recupera los datos de la Meta
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDatosMetas(Map criteria);
	
	
	/**
	 * Obtiene el lista de de facturacion adicional del cliente
	 * @param codigoCliente
	 * @return
	 */
	public List getFacturacionAdicionalList(String codigoCliente);

	/**
	 * Se recupera las retails agrupado x campaña realizadas por una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListPeriodoRetail(Map criteria);
	
		
	/**
	 * Se recupera las cabeceras retails realizadas por una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListCabeceraRetail(Map criteria);
	
	/**
	 * Se recupera los detalles retails realizadas por una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListDetalleRetail(Map criteria);
	
		
	/**
	 * Actualiza datos basicos del cliente 
	 * 
	 * @param criteria
	 */
	public void executeActualizarDatosCliente(Map params);
	
	/**
	 * Se recupera los datos de la Ejecutiva
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDatosEjecutiva(Map criteria);

	/**
	 * Obtiene una Fase Activa del Programa de acuerdo a la Campaña Proceso enviada
	 * @param criteria
	 * @return
	 */
	public String getFaseActivaPrograma(Map criteria);
	
	/**
	 * Se recupera el detalle de evaluacion de la Ejecutiva
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDetalleEvaluacionEjecutiva(Map criteria);
	
	/**
	 * Se recupera las Etapas para Evaluar a la Ejecutivas
	 * 
	 * @param criteria
	 * @return
	 */
	public List getEtapasEjecutivas(Map criteria);
	
	/**
	 * Se verifica si la consultora tiene bloqueo activo
	 * 
	 * @param criteria
	 * @return
	 */
	public String getBloqueoActivo(String codigoCliente);
	
	/**
	 * Obtiene el saldo cupon 3.
	 * 
	 * @param criteria
	 * @return
	 */
	public Double getSaldoCupon3(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public Integer getOidPeriodo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public Integer getOidCliente(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public Double getMontoMinimo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getDesPeriodoByCodigoPeriodoX(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public Map getAplicaMontoMinimo(Map criteria);
	
	/**
	 * Obtiene el promedio de ventas de la consultora por el numero de pedidos
	 * 
	 * @param criteria
	 * @return
	 */
	public String getPromedioVentasxNumeroPedidos(Map criteria);
	
		
	/**
	 * Obtiene el detalle de productos solicitados por la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConsultaDetallePedidoSolicitado(Map criteria);

	/**
	 * Obtiene la lista de cabeceras de consultoras castigadas
	 * @author sguerra
	 * @param criterios
	 * @return
	 */
	public List getCabeceraConsultoraCastigada(Map criteria);

	/**
	 * Obtiene la lista de detalles de consultoras castigadas
	 * @author sguerra
	 * @param criterios
	 * @return
	 */
	public List getDetalleConsultoraCastigada(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getIndicadorBasparampais(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getEtapaDeCobro(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getCobrador(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCuentaCorrienteConsultoraCastigada(Map criteria);

	/**
	 * Gets the saldo a campana.
	 * 
	 * @param criteria the criteria
	 * @return the saldo a campana
	 */
	public String getSaldoACampana(Map criteria);

	/**
	 * Obtiene los pedidos base y linea de credito de 3 campañas, activa-1, activa, cativa+1
	 * @param params
	 * @return
	 */
	public Map getPedidoBaseLineaCredito(Map params);
	
	/**
	 * Se recupera los detalles de los puntos en concurso
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListDetallePuntosConcursoRetail(Map criteria);
	
	/**
	 * Se recupera el detalle de premios de recuperacion por anulacion  
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDetalleRecuperacionAnulacion(Map criteria);
	
	/**
	 * Inserta los registros eliminados de recuperacion por anulacion  
	 * 
	 * @param criteria
	 * @return
	 */
	public void insertRecuperacionAnulacionAudit(Map criteria);
	
	/**
	 * Eliminar los registros de recuperacion por anulacion  
	 * 
	 * @param oidSoliPosi
	 * @return
	 */
	public void deleteRecuperacionAnulacion(String oidSoliPosi);
	
	/**
	 * Verifica si existe integridad
	 * 
	 * @param oidSoliCabe
	 * @return
	 */
	public String getExisteIntegridadCabecera(String oidSoliCabe);
	
	/**
	 * Eliminar los registros de recuperacion por anulacion de la cabecera
	 * 
	 * @param oidSoliCabe
	 * @return
	 */
	public void deleteRecuperacionAnulacionCabecera(String oidSoliCabe);
	
	/**
	 * Se recupera la informacion de los premios rechazados de una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPremiosRechazados(Map criteria);

	/**
	 * Se recupera la informacion de las etapas de cobro de una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getEtapasCobro(Map criteria);

	/**
	 * Verifica si se deshabilita los datos de Zona y Territorio
	 * 
	 * @param criteria
	 * @return
	 */
	public String getDeshabilitarZonaTerritorio(Map criteria);

	/**
	 * Se recupera la informacion de los premios elegidos de una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPremiosElegidos(Map criteria);

	/**
	 * Obtine el codigo del programa LET correspondiente a la campaña de proceso
	 * @param campanyaProceso
	 * @return
	 */
	public String getCodigoProgramaLET(String campanyaProceso);

	/**
	 * Obtiene los datos de la socia empresaria.
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDatosSociaEmpresaria(Map criteria);

	/**
	 * Obtiene el historial de la socia empresaria
	 * 
	 * @param criteria
	 * @return
	 */
	public List getHistorialSociaEmpresaria(Map criteria);

	/**
	 * Obtiene los resultados de la socia empresaria
	 * 
	 * @param criteria
	 * @return
	 */
	public List getResultadosSociaEmpresaria(Map criteria);
	
	/**
	 * Obtiene el estatus del cliente
	 * @param criteria
	 * @return
	 */
	public String getEstatusCliente(Map criteria);
	
	/**
	 * Obtiene la fecha y campaña de activación del cliente 
	 * @param criteria
	 * @return
	 */
	public Map getFechaCampanaActivacionCliente(Map criteria);
	
	/**
	 * Obtiene la fecha de cancelacion del cliente
	 * @param criteria
	 * @return
	 */
	public String getFechaCancelacionCliente(Map criteria);
	
	/**
	 * Obtiene el motivo de cancelacion del cliente
	 * @param criteria
	 * @return
	 */
	public String getMotivoCancelacionCliente(Map criteria);
	
	/**
	 * Obtiene el periodo de comunicacion del cliente
	 * @param criteria
	 * @return
	 */
	public String getPeriodoComunicacionCliente(Map criteria);
	
	/**
	 * Obtiene la fecha de comunicacion del cliente
	 * @param criteria
	 * @return
	 */
	public String getFechaComunicacionCliente(Map criteria);
	
	/**
	 * Obtiene el oid del periodo y el numero del pedido
	 * @param criteria
	 * @return
	 */
	public Map getPeriodoNumeroPedido(Map criteria);

	/**
	 * OBtiene la sitiacion del cleinte en flexipago
	 * 
	 * @param codigoPais
	 * @param codigoCliente
	 * @return
	 */
	public String getSituacionFlexipago(String codigoPais, String codigoCliente, String campanhaProceso);
	
	/**
	 * Devuelve el subtipo del cliente
	 * @param criteria
	 * @return
	 */
	public String getOidSubtipoCliente(Map criteria);
	
	public String getObtieAbonoPendi(Map criteria);
	
	public String getTipoPersonaxOidCliente(Map criteria);
	
	public String getOrigenIngresosxOidCliente(Map criteria);
	
	public String getOrigenxCodCliente(Map criteria);
	
	
}
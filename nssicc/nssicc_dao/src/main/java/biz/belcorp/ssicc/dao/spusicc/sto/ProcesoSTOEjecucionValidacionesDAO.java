
package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DetalleOferta;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;

/**
 * <p>
 * <a href="ProcesoSTOEjecucionValidacionesDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristian Roman </a>
 */
public interface ProcesoSTOEjecucionValidacionesDAO extends DAO {

	public List getTiposDocumentosSTO(Map criteria);
	
	public List getComponentesDocumentos(Map criteria);
	
	
	
	public void executeValidacion(String method, Map criteria);

	public TipoDocumentoDigitado getTipoDocumentoDigitado(String codigo);


	public void executeValidacionConsolidado(String method, Map criteria);

	public void executeInicializacionRegistrosProceso(Map criteria);

	public String executeGenerarCodigoClienteSTO(Map criteria);
	
	public void updateNumeroSolicitud(Map criteria);
	
	public void updateRegistrosControlValidacion(Map criteria);
	
	public List getTiposDocumentosIdentidadSTO();
	
	public List getConsultaClientesList(Map criteria);	
	
	public void STOGrabarPedRec(Map criteria);
	
	public String getNumeroProcesoActual(Map criteria);

	public void insertInicioProcesoSTO(Map criteria);
	
	public void updateFinProcesoSTO(Map criteria);
	
	public void updateNumeroProceso(Map criteria);

	public void updateNumeroProcesoDetalles(Map criteria);
	
	public String getCodigoDetalle(Map criteria);
	
	public List getOrdenCompraCabecera(Map criteria);
	
	public List getOrdenCompraDetalle(Map criteria);
	
	public List getSolicitudCredito(Map criteria);
	
	public void updateOrdenCompraCabecera(Map criteria);
	
	public void updateOrdenCompraDetalle(Map criteria);
	
	public List getSolicitudPostVentaDetalle(Map criteria);
	
	public List getSolicitudPostVentaCabecera(Map criteria);
	
	public void updateSolicitudPostVentaCabecera(Map criteria);
	
	public void updateSolicitudPostVentaDetalle(Map criteria);
	
	public void updateSolicitudCredito(Map criteria);
	
	public void executeAlmacenamientoHistorico(String metodo, Map criteria);

	public List getTiposDocumentosHistoricoSTO(Map criteria);

	/**Metodo que se utiliza para recuperar la clasificacion de la consultora 
	 * @param criteria
	 */		
	public List executeClasificacionConsultora(Map criteria);

	/**Metodo que recupera registros de las ultimas campaas 
	 * @param criteria
	 */	
	public List getEstadisticaUltimasCampanas(Map criteria);
	
	/**Metodo que inserta los detalles de la cabecera de OCC 
	 * @param criteria
	 */
	public void insercionDetalles(Map criteria); 
	
	/**Metodo que devuelve el numero de secuencia para documentos de STO
	 * @return
	 */
	public String getSecuenciaSTONextValue();
	
	/**Metodo que inserta los detalles de la cabecera de OCC  en la tabla STO_DOCUM_DIGIT
	 * @param criteria
	 */
	public void insercionDetallesSTODocumDigit(Map criteria);
	
	/**Metodo que obtiene la lista de detalles de una cabecera de OCC
	 * @param criteria
	 * @return
	 */
	public List getDetallesOCC(Map criteria);
	
	/**Metodo que borra los detalles de las cabeceras de OCC
	 * @param metodo
	 * @param criteria
	 */
	public void deleteDetalle(String metodo, Map criteria);
	
	/**Metodo que modifica  los valores de los detalles de las cabeceras de OCC
	 * @param criteria
	 */
	public void updateDetalle(Map criteria);
	
	/**Metodo que modifica la cabecera cuando se marca la validacion de monto minimo o maximo
	 * @param criteria
	 */
	public void updateCabeceraDesactiva(Map criteria);
	
	/**Metodo que devuelve el indicador de monto minimo de INT_SOLIC_CONSO_CABEC
	 * @param criteria
	 * @return
	 */
	public String getIndicadorMontoMinimo(Map criteria);
	
	/**Metodo que devuelve el indicador de monto maximo de INT_SOLIC_CONSO_CABEC
	 * @param criteria
	 * @return
	 */
	public String getIndicadorMontoMaximo(Map criteria);
	
	/**Metodo que ejecuta el proceso de monto minimo
	 * @param criteria
	 */
	public void executeMontoMinimo(Map criteria);
	
	/**Metodo que ejecuta el proceso de revertir monto minimo
	 * @param criteria
	 */
	public void executeRevertirMontoMinimo(Map criteria);
	
	/**Metodo que ejecuta el proceso de monto maximo
	 * @param criteria
	 */
	public void executeMontoMaximo(Map criteria);
	
	/**Metodo que ejecuta el proceso de revertir monto maximo
	 * @param criteria
	 */
	public void executeRevertirMontoMaximo(Map criteria);
	
	/**Metodo que verifica si ya existen detalles iguales a los que se va ingresar
	 * @param criteria
	 * @return
	 */
	public List verificacionDetalles(Map criteria);
	
	/**Metodo para obtener el valor del parametro de la tabla STO_PARAM_GENER_OCCRR
	 * @param criteria
	 * @return
	 */
	public String getParametroSTO(Map criteria);
	
	/**Metodo para obtener la lista de excepciones de monto minimo y maximo
	 * @param criteria
	 * @return
	 */
	public List getListExcepciones(Map criteria);
	
	/**
	 *Metodo para obtener el indicador de STO
	 * @param criteria
	 * @return
	 */
	public String getIndicadorSTO(Map criteria);
	
	/**
	 * Metodo para obtener la lista de actualizacion de datos
	 * @param criteria
	 * @return
	 */
	public List getActualizacionDatos(Map criteria);
	
	/**
	 * Metodo para actualizar la actualizacion de datos
	 * @param criteria
	 */
	public void updateActualizacionDatos(Map criteria);
	
	
	/**
	 * Metodo para obtener la lista de dupla cyzone
	 * @param criteria
	 * @return
	 */
	public List getDuplaCyzone(Map criteria);
	
	/**
	 * Metodo para actualizar la dupla cyzone
	 * @param criteria
	 */
	public void updateDuplaCyzone(Map criteria);
	
	/**
	 * Metodo para obtener la lista de cupon pago
	 * @param criteria
	 * @return
	 */
	public List getCuponPago(Map criteria);
	
	/**
	 * Metodo para actualizar el cupon pago
	 * @param criteria
	 */
	public void updateCuponPago(Map criteria);
	
	/**
	 * Metodo para obtener la accion de editar
	 * @param criteria
	 * @return
	 */
	public String getAccionEditable(Map criteria);
	
	/**
	 * @param criteria
	 * Carga los valores en una tabla para el posterior 
	 * envio de Mails de Solicitud de Credito
	 */
	public void executeEnvioMailSCC(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devueleve la lista de Emails a enviar
	 */
	public List getEnvioMailsSCCList(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve los parametros de Envio de Correo
	 */
	public List getEnvioMailsSCCParams(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina los registros de los mails enviados
	 */
	public void deleteEnvioMailsSCC(Map criteria);
	
	/**
	 * Metodo para obtener la lista de bloqueos de la consultora
	 * @param criteria
	 * @return
	 */
	public List getBloqueosConsultora(Map criteria);
	
	/**
	 * Metodo para obtener la lista de documentos de referencia x consultora 
	 * @param criteria
	 * @return
	 */
	public List getDocumentosReferenciaConsultora(Map criteria);
	
	/**
	 * Metodo que inserta los cupones en la tabla INT_SOLIC_CONSO_CUPON_PAGO
	 * @param criteria
	 */
	public void insertCuponPago(Map criteria); 
	
	/**
	 * Metodo que devuelve el codigo de compaia
	 * @param criteria
	 * @return
	 */
	public String getCodigoCompania(Map criteria);
	
	/**
	 * Metodo que devuelve el codigo de cliente
	 * @param criteria
	 * @return
	 */
	public String getCodigoCliente(Map criteria);
	
	/**
	 * Metodo que devuelve la lista de cupones
	 * @param criteria
	 * @return
	 */
	public List getCuponPagoSearchList(Map criteria);
	
	/**
	 * Metodo para eliminar un cupon
	 * @param criteria
	 */
	public void deleteCuponPago(Map criteria);
	
	/**
	 * Metodo que devuelve un cupon para modificar
	 * @param criteria
	 * @return
	 */
	public List getCuponPagoModificar(Map criteria);
	
	/**
	 * Metodo para modificar un cupon
	 * @param criteria
	 */
	public void updateCuponPagoModificar(Map criteria);
	
	/**
	 * Obtiene el secuenccial del cupon
	 * @return
	 */
	public String getSecuenciaCuponPagoSTONextValue();
	
	/**
	 * Obtiene el nombre de la consultora
	 * @return
	 */
	public String getNombreConsultora(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de operaciones
	 * @param criteria
	 * @return
	 */
	public List  getOpeacionCodigoVenta(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de operaciones para SPVD
	 * @param criteria
	 * @return
	 */
	public List  getOpeacionCodigoVentaSPVD(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de CDR
	 * @param criteria
	 * @return
	 */
	public List getCantidadDevuelveList(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de codigos de venta pedidos
	 * @param criteria
	 * @return
	 */
	public List getCodigoVentaPedidoList(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de codigos de venta matriz
	 * @param criteria
	 * @return
	 */
	public List getCodigoVentaMatrizList(Map criteria);
	
	/**
	 * Metodo que obtiene el periodo de referencia
	 * @param criteria
	 * @return
	 */
	public String getPeriodoReferencia(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de codigos de venta matriz de precios
	 * @param criteria
	 * @return
	 */
	public List getCodigoVentaMatrizPrecioList(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de codigos de venta matriz de incentivos
	 * @param criteria
	 * @return
	 */
	public List getCodigoVentaMatrizIncentivoList(Map criteria);

	/**
	 * Metodo que obtiene la lista de operaciones
	 * @return
	 */
	public List getReferenciaOperacionList();

	/**
	 * Metodo que obtiene la lista del detalle del movimiento de un producto del cual se esta procesando un devuelve
	 * @param criteria
	 * @return
	 */
	public List getCantidadDevuelveDetalleList(Map criteria);

	/**
	 * Metodo que obtiene la lista de codigos de actividades faltantes
	 * @param criteria
	 * @return
	 */
	public List getListaValidacionExistenciaCronograma(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de zonas con diferencias
	 * @param criteria
	 * @return
	 */
	public List getListaValidacionVencimientoCronograma(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de zonas con diferencias en el segundo caso
	 * @param criteria
	 * @return
	 */
	public List getListaValidacionVencimientoCronogramaSegundoCaso(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de zonas con diferencias en el tercer caso
	 * @param criteria
	 * @return
	 */
	public List getListaValidacionVencimientoCronogramaTercerCaso(Map criteria);
	
	/**
	 * Metodo que actualiza el codigo de consultora en los detalles de pedido
	 * en base al PK de la cabecera
	 * @param criteria
	 */
	public void updateCodigoClienteOrdenCompraDetalle(Map criteria);
	
	/**
	 * Metodo que actualiza el codigo de consultora en la tabla sto_docum_digit
	 * @param criteria
	 */
	public void updateCodigoClienteSTODocumDigit(Map criteria);
	
	/**
	 * Obtienela lista de Tipos de via
	 * @return
	 */
	public List getTipoViaCliente();
	
	/**
	 * Obtiene la lista de Motivos de devolucion
	 * @param criteria
	 * @return
	 */
	public List getMotivosDevolucion(Map criteria);
	
	/**
	 * Obtiene la lista de Tipos de Documento
	 * @return
	 */
	public List getTipoDocumento();

	/**
	 * Inserta en la nueva tabla de auditoria el cambio hecho en el codigo de cliente
	 * @param criteria
	 */
	public void insertAuditoriaCambioCodCliente(Map criteria);
	
	
	
	/**
	 * Obtiene la lista de ordenes de transporte
	 * @param criteria
	 * @return
	 */
	public List getOrdenTransporte(Map criteria);
	
	/**
	 * Actualiza una orden de transporte
	 * @param criteria
	 */
	public void updateOrdenTransporte(Map criteria);
	
	/**
	 * Obtiene la lista de Calificaciones de Orden de Transporte
	 * @param criteria
	 * @return
	 */
	public List getCalificacionesOrdenTransporte(Map criteria);
	
	/**
	 * Obtiene la lista de Novedades Acciones de Orden de Transporte
	 * @param criteria
	 * @return
	 */
	public List getNovedadesAccionesOrdenTransporte(Map criteria);
	
	/**
	 * Proceso que se ejecuta al realizar una modificacion de
	 * una orden de transporte
	 * @param criteria
	 */
	public void executeModificarOrdenTransporte(Map criteria);
	
	/**
	 * Devueleve la informacion de los centros de acopio
	 * @param criteria
	 * @return
	 */
	public List getCentrosDeAcopio(Map criteria);
	
	/**
	 * Actualiza el indicador de envio luego de enviarlo por email
	 */
	public void updateIndicadorEnvio(Map criteria);

	/**
	 * Devueleve la informacion de los centros de acopio
	 * @return
	 */
	public List getCentrosDeAcopioSolucionesCentroAcopioAutomatico();
	
	/**
	 * Devuelve la lista de companias de transporte
	 * @return
	 */
	public List getCompaniasTransporte();
	
	/**
	 * Devuelve la lista de centros de acopio
	 * @return
	 */
	public List getCentrosAcopio();
	
	/**
	 * Ejecuta el proceso de actualizar codigo de consultora
	 * @param criteria
	 */
	void executeUpdateCodigoConsultora(Map criteria);
	
	/**
	 * @param criteria
	 * @return 	Obtiene el valor del parametro de la tabla STO_PARAM_GENER_OCCRR
	 */
	public String getObtenerValParametroSTO(Map criteria);

	/**
	 * Ejecuta el proceso de homologar el codigo de consultora en
	 * base al documento de identidad o preimpreso segun parametro
	 * @param criteria
	 */
	public void executeHomologarCodigoConsultora(Map criteria);
	
	/**
	 * Recupera el codigo de cliente en base al numero de documento de identidad
	 * @param criteria
	 * @return
	 */
	public String getCodigoClienteByDocumentoIdentidad(Map criteria);

	/**
	 * Metodo para obtener la lista de ingreso de metas
	 * @param criteria
	 * @return
	 */
	public List getIngresoMetas(Map criteria);
	
	/**
	 * Metodo para obtener la lista de carta invitacion flexipago
	 * @param criteria
	 * @return
	 */
	public List getCartaInvitacionFlexipago(Map criteria);	
	
	/**
	 * Actualiza los campos solicitados en el consolidado
	 * @param criteria
	 */
	public void updateIngresoMetas(Map criteria);
	
	/**
	 * Actualiza los campos solicitados en el consolidado
	 * @param criteria
	 */
	public void updateCartaInvitacionFlexipago(Map criteria);
	
	/**
	 * Retorna los valores de Oid de Detalle Oferta
	 * @param criteria
	 * @return
	 */
	public DetalleOferta getValoresOidDetalleOferta(Map criteria);
	
	/**
	 * Retorna el Detalle de Ofertas
	 * @param criteria
	 * @return
	 */
	public List getDetalleOfertaList(Map criteria);
	
	/**
	 * Retorna la lista de detalle de movimientos por producto
	 * @param criteria
	 * @return
	 */
	public List getDetalleMovimientoProductoList(Map criteria);
	
	/**
	 * Metodo para obtener la lista de Familia Segura
	 * @param criteria
	 * @return
	 */
	public List getFamiliaSegura(Map criteria);
	
	/**
	 * Actualiza los campos solicitados en el consolidado
	 * @param criteria
	 */
	public void updateFamiliaSegura(Map criteria);
	
	/**
	 * Devuelve los datos actuales del cliente 
	 * @param criteria
	 * @return
	 */
	public List getDatosClienteActual(Map criteria);
	
	/**
	 * Obtiene la lista de los documentos que tienen el indicador de 
	 * pase a historico automatico
	 * @param criteria
	 * @return
	 */
	public List getTiposDocumentosHistoricoSTOAut(Map criteria);
	
	/**
	 * Obtiene la descripcion del Origen OnLine
	 * @param criteria
	 * @return
	 */
	public String getDescripcionOnline(Map criteria);
	
	/**
	 * @param id
	 * @return
	 */
	public List getDetallePedidoFolio(String id);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getTiposDocumentosHistoricoSTOSinGestion(Map criteria);

	/**
	 * @param documentoMap
	 */
	public void executeHistoricoSinGestionar(Map documentoMap);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getListaPeriodosHistoricosSinGestion(Map criteria);
	
	public String getURLOCRParametro(Map criteria);
	
	public String getMarcaPais(Map criteria);
	
	public String getValorTipoDocumento(Map criteria);

	public Map getListaHistoricoSolicitudAprobados(Map criteria);

	public void updateDocumentoDigital(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getTiposDocumentosExcepcionSTO(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public Map getContratoEjecutiva(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void updateContratoEjecutiva(Map criteria);
		
	/**
	 * @param criteria
	 * @return
	 */
	public List getDatosClienteActualNumDocIdent(Map criteria);
	
	/**
	 * Devuelve a informacion de los centros de acopio que se han facturado
	 * @param criteria
	 * @return
	 */
	public List getCentrosDeAcopioFacturado(Map criteria);	
	
	/**
	 * Devuelve si debe o no mostrarse el combo de motivo de gestion
	 * @param criteria
	 * @return
	 */
	public String getValidaMostrarCombo (Map criteria);
	/**
	 * Obtiene la lista de motivos de gestion
	 * @return
	 */
	public List getMotivosGestion();
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getIndicadorTerritorioBuzon(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getCodigoNumeroTipoDocumentoConsultoraPorCodigo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getCodigoNumeroTipoDocumentoConsultoraPorDocumento(Map criteria);

	/**
	 * Valida la solicitud de pedido de la OT en base al nmero de documento
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	public String validateSolicitudPedidoOrdenTransporte(Map criteria);
	
	/**
	 * Valida la boleta o recibo de la OT en base al nmero de documento
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	public String validateBoletaReciboOrdenTransporte(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getIndInformacionOk(Map criteria);
	/**
	 * @param criteria
	 * @return
	 */
	public String getIndSitCrediticia(Map criteria);
	/**
	 * @param criteria
	 * @return
	 */
	public String getIndSinSalAmbas(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getMotivosGestionDocumento(Map criteria);
	
	/**
	 * Obtiene la estructura geopolitica por Unidad Administrativa
	 * @param codigoPais
	 * @param unidadAdmin
	 * @return
	 */
	public String getEstructGeopoByUA(String codigoPais,String unidadAdmin);
	
	public String getExisteOidDetaOferta(Map criteria);
	
	/**
	 * Devuelve fecha de nacimiento usando como filtro el codigo del cliente
	 * @param criteria
	 * @return
	 */
	public String getFechaNacimientoByCodigoCliente(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de Tipos de Documentos Digi
	 * @return
	 */
	public List getTipoDocumentosDigitList();
	
	/**
	 * Metodo que obtiene la lista de Motivos de Rechazo 
	 * @param criteria
	 * @return
	 */
	public List getMotivosRechazoSTOList(Map criteria);
	
	/**
	 * Metodo que genera el codigo de validacion
	 * @param codigoTipoDocumentoDigi
	 * @return
	 */
	public String getCodigoValidacion(String codigoTipoDocumentoDigi);
	
	/**
	 * Metodo que obtiene la lista de Validaciones STO
	 * @param criteria
	 * @return
	 */
	public List getValidacionesSTOList(Map criteria);
	
	/**
	 * Metodo que genera el codigo de mensaje
	 * @param codigoValidacion
	 * @return
	 */
	public String getCodigoMensaje(String codigoValidacion);
	
	/**
	 * Metodo que obtiene la lista de Validaciones STO
	 * @param criteria
	 * @return
	 */
	public List getValidacionesSTO(Map criteria);
	
	/**
	 * Obtiene la lista de Estados Civiles
	 * @return
	 */
	public List getEstadoCivil();
	
	/**
	 * Obtiene la lista de Tipos de Persona
	 * @return
	 */
	public List getTipoPersona();
	
	/**
	 * Obtiene la lista de Tipos de Persona
	 * @return
	 */
	public List getTipoPersonaCodigo();
	
	/**
	 * Obtiene la lista de Nacionalidades
	 * @return
	 */
	public List getNacionalidad();
	
	/**
	 * Obtiene la lista de Nacionalidades
	 * @return
	 */
	public List getNacionalidadCodigo();
	
	/**
	 * Obtiene la lista de Orígenes de Ingreso
	 * @return
	 */
	public List getOrigenIngreso();
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCuadroOfertaOCC(Map criteria);
	
	/**
	 * Obtiene la lista de Orígenes de Ingreso
	 * @return
	 */
	public List getOrigenIngresoCodigo();
	
	/**
	 * Obtiene el resultado de evaluacion Crediticia -infO Comercial
	 * @param codigo
	 * @return
	 */	
	public List getDataCrediticiaInfoComercial(String codigo);
}
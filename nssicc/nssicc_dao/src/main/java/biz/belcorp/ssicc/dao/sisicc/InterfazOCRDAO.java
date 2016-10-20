/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.sisicc.model.PaisCompania;

/**
  * <p>
 * <a href="InterfazOCRDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public interface InterfazOCRDAO extends DAO {

	/**
	 * Metodo que elimina los CUV Errados por Scaneo OCR
	 * @param params
	 * @throws Exception
	 */
	public void deleteOCRCUVErradosScaneo(Map params) throws Exception ;
	
	/**
	 * Inserta Lista OCR obtenido del Comercial y lo graba en Tabla del SSiCC 
	 * @param listaOCR
	 * @param params
	 * @param usuario
	 * @throws Exception
	 */
	public void insertOCRCUVErradosScaneo(List listaOCR, Map params, Usuario usuario) throws Exception ;
	
	
	/**
	 * Devuelve valores de conexion del OCR Comercial
	 * @param codigoPais
	 * @return
	 * @throws Exception
	 */
	public ConexionOCRWrapper getDevuelveConexionOCR(String codigoPais) throws Exception;	

	
	/**
	 * @param queryParams
	 * Ejecuta la interfaz de Recepcionar OCS Web/DD cabeceras
	 */
	public void executeInterfazOCRWebDDCabecera(Map queryParams);
	
	/**
	 * @param queryParams
	 * Ejecuta la interfaz de Recepcionar OCS Web/DD detalles
	 */
	public void executeInterfazOCRWebDDDetalle(Map queryParams);

	/**
	 * @param map
	 * Ejecuta la consolidacion de Pedidos DD y Web 
	 */
	public void executeConsolidadoPedidos(Map queryParams);	
		
	/**
	 * Ejecuta la interfaz de Dupla Cyzone
	 * @param map
	 */
	public void executeInterfazOCRRecepcionarDuplaCyzone(Map map);

	
	/**
	 * Metodo que elimina las Dupla Cyzone
	 */
	public void deleteInterfazOCRRecepcionarDuplaCyzone();
	
	
	/**
	 * Procesa los consolidados de dupla cyzone
	 * @param map
	 */
	public void executeInterfazOCRProcesarConsolidadoDuplaCyzone(Map map);
	
	/**
	 *Metodo que elimina los Cupones de pago
	 */
	public void deleteInterfazOCRRecepcionarCuponPago();
	
	/**
	 * Ejecuta la interfaz de Cupon Pago
	 * @param map
	 */
	public void executeInterfazOCRRecepcionarCuponPago(Map map);
	
	/**
	 *  Procesa los consolidados de Cupon Pago
	 * @param map
	 */
	public void executeInterfazOCRProcesarConsolidadoCuponPago(Map map);

	/**
	 * @param params
	 * COnsolida las Solicitudes de Post Venta
	 */
	public void executeConsolidadoPostVenta(Map params);
	
	/**
	 * Metodo q obtiene el nombre del archivo 
	 * @param map
	 * @return
	 */
	public String getNombreArchivoCarga(Map map);
	
	/**
	 * Metodo que verifica el num_docu de la tabla int_solic_cabec
	 * @param map
	 * @return
	 */
	public String getNumeroDocumentoOCC(Map map);
	
	/**
	 * Inserta en  la tabla  INT_OCR_CONTR_CARGA de la tabla int_solic_cabec
	 * @param map
	 */
	public void insertTablaArchivosCargadosOCC(Map map);
	
	/**
	 * Metodo que verifica el num_docu de la tabla int_ocr_solic_credi
	 * @param map
	 * @return
	 */
	public String getNumeroDocumentoSCC(Map map);
	
	/**
	 * Metodo que verifica el num_docu de la tabla INT_OCR_CABEC_SERVI_POSTV
	 * @param map
	 * @return
	 */
	public String getNumeroDocumentoSPVC(Map map);
	
	/**
	 * Metodo que verifica el num_docu de la tabla INT_OCR_ACTUA_DATOS
	 * @param map
	 * @return
	 */
	public String getNumeroDocumentoSAD(Map map);
	
	/**
	 * Metodo que verifica el num_docu de la tabla INT_OCR_DUPLA_CYZON
	 * @param map
	 * @return
	 */
	public String getNumeroDocumentoDCYZ(Map map);
	
	/**
	  * Metodo que verifica el num_docu de la tabla INT_OCR_CUPON_PAGO
	 * @param map
	 * @return
	 */
	public String getNumeroDocumentoCUP(Map map);
	
	/**
	 * Inserta en  la tabla  INT_OCR_CONTR_CARGA de la tabla int_ocr_solic_credi
	 * @param map
	 */
	public void insertTablaArchivosCargadosSCC(Map map);
	
	/**
	 * Inserta en  la tabla  INT_OCR_CONTR_CARGA de la tabla INT_OCR_CABEC_SERVI_POSTV
	 * @param map
	 */
	public void insertTablaArchivosCargadosSPVC(Map map);
	
	/**
	 * Inserta en  la tabla  INT_OCR_CONTR_CARGA de la tabla INT_OCR_DUPLA_CYZON
	 * @param map
	 */
	public void insertTablaArchivosCargadosDCYZ(Map map);
	
	/**
	 * Inserta en  la tabla  INT_OCR_CONTR_CARGA de la tabla INT_OCR_ACTUA_DATOS
	 * @param map
	 */
	public void insertTablaArchivosCargadosSAD(Map map);
	
	/**
	 * Inserta en  la tabla  INT_OCR_CONTR_CARGA de la tabla INT_OCR_CUPON_PAGO
	 * @param map
	 */
	public void insertTablaArchivosCargadosCUP(Map map);
	
	/**
	 * @param map
	 * ejecuta la interfaz de solicitud de credito corporativa
	 */
	public void executeInterfazOCRRecepcionarSolicitudCreditoCorporativa(Map map);
	
	/**
	 * Borra la tabla de solicitud de credito corporativa
	 */	
	public void deleteInterfazOCRRecepcionarSolicitudCreditoCorporativa();
	
	/**
	 * @param params
	 * ejecuta la consolidacion de solicitud de credito corporativa
	 */
	public void executeInterfazOCRProcesarConsolidadoSolicitudCredito(Map params);
	
	/**
	 * Borra la tabla de solicitud de credito corporativa
	 */
	public void deleteInterfazOCRRecepcionarSolicitudCreditoWeb();		
	
	/**
	 * @param map
	 * Ejecuta la interfaz de solicitud de credito web
	 */
	public void executeInterfazOCRRecepcionarSolicitudCreditoWeb(Map map);
	
	/**
	 * @param map
	 * Ejecuta la interfaz de Actualizacion de Datos corporativa 
	 */
	public void executeInterfazOCRRecepcionarActualizacionDatosCorporativa(Map map);
	
	/**
	 * @param map
	 * Ejecuta la interfaz de Actualizacion de Datos web 
	 */
	public void executeInterfazOCRRecepcionarActualizacionDatosWeb(Map map);
		
	/**
	 * executa la interfaz de post venta detalle accion
	 * @param parametros
	 */
	public void executeInterfazOCRRecepcionarServiciosPostventasDetalAccion(Map parametros);
	
	/**
	 * Borra la tabla de Actualizacion de Datos web
	 */
	public void deleteInterfazOCRRecepcionarActualizacionDatos();
	
	/**
	 * @param map
	 * Ejecuta la consolidacion de Actualizacion de Datos
	 */
	public void executeInterfazOCRProcesarConsolidadoActualizacionDatos(Map map);
	
	
	/**
	 * executa la interfaz de envio de clientes a web
	 * @param parametros
	 */
	public void executeInterfazOCREnviarClientesWeb(Map parametros);
	
	/**
	 * executa la interfaz de envio de territorios a web
	 * @param parametros
	 */
	public void executeInterfazOCREnviarTerritoriosWeb(Map parametros);
	
	/**
	 * executa la interfaz de envio de segmentos a web
	 * @param parametros
	 */
	public void executeInterfazOCREnviarSegmentosWeb(Map parametros);
	
	/**
	 * Obtiene el codigo de compaia de la tabla bas_pais_compa
	 * @param params
	 * @return
	 */
	public PaisCompania getBasPaisCompa(Map params);

	/**
	 * Borra la tabla de Codigos Venta Rechazados 
	 */
	public void deleteInterfazOCRRecepcionarCodigosVentaRechazados();

	/**
	 * executa la interfaz de Recepcionar Codigos Venta Rechazados
	 * @param map
	 */
	public void executeInterfazOCRRecepcionarCodigosVentaRechazados(Map map);
	
	/**
	 * executa la interfaz de Recepcionar Orden de Transporte
	 * @param map
	 */
	public void executeInterfazOCRRecepcionarOrdenTransporte(Map map);
	
	/**
	 * executa el consolidado de la interfaz de Recepcionar Orden de Transporte
	 * @param map
	 */
	public void executeInterfazOCRProcesarConsolidadoOrdenTransporte(Map map);
	
	/**
	 * executa la interfaz de Enviar Orden de Transporte del proceso de facturacion
	 * @param params
	 */
	public void executeInterfazOCREnviarOrdenTransporteFacturacion(Map params);
	
	/**
	 * executa la interfaz de Enviar Orden de Transporte Anuladas
	 * @param params
	 */
	public void executeInterfazOCREnviarOrdenTransporteAnulada(Map params);
	
	/**
	 * executa la interfaz de Retorno Codigos Asignados
	 * @param params
	 */
	public void executeInterfazOCRRetornoCodigosAsignados(Map params);
	
	/**
	 * Permite eliminar los registros en la tabla de recepcion de Ingreso de Metas 
	 */
	public void deleteInterfazOCRRecepcionarIngresoMetas();
	
	/**
	 * Permite eliminar los registros en la tabla de recepcion de Carta Flexipago  
	 */
	public void deleteInterfazOCRRecepcionarCartaInvitacionFlexipago();
	
	/**
	 * Ejecuta el proceso de recepcin de Ingreso de Metas
	 * @param params
	 */
	public void executeInterfazOCRRecepcionarIngresoMetas(Map params);
	
	/**
	 * Ejecuta el proceso de recepcin de Carta Flexipago
	 * @param params
	 */
	public void executeInterfazOCRRecepcionarCartaInvitacionFlexipago(Map params);
	
	/**
	 * Procesa los consolidados de ingreso de metas
	 * @param map
	 */
	public void executeInterfazOCRProcesarConsolidadoIngresoMetas(Map params);

	/**
	 * Procesa las cartas de invitacin a flexipago
	 * @param map
	 */
	public void executeInterfazOCRProcesarConsolidadoCartaInvitacionFlexipago(Map params);

	/**
	 * Permite eliminar los registros en la tabla de recepcion de Familia Segura 
	 */
	public void deleteInterfazOCRRecepcionarFamiliaSegura();
	
	/**
	 * Ejecuta el proceso de recepcin de Familia Segura
	 * @param params
	 */
	public void executeInterfazOCRRecepcionarFamiliaSegura(Map params);
	
	/**
	 * Procesa los consolidados de Familia Segura
	 * @param map
	 */
	public void executeInterfazOCRProcesarConsolidadoFamiliaSegura(Map params);
	
	
	/**
	 * Procesa los consolidados cabecera y detalle de Mica WEB
	 * @param queryParams
	 */
	public void executeConsolidadoMicaWeb(Map queryParams);

	/**
	 * obtiene la lista de consultoras para ser invocadas con el ws 
	 * @param map
	 * @return
	 */
	public List getListWsSocCredito(Map map);

	/**
	 * Actualiza la informacion de InfoCom
	 * @param hmap
	 */
	public void updateInformacionInfoCom(Map map);
	/**
	 * Recepciona la cabera de Pedidos PROL de la Web
	 * @param map
	 */
	public void executeInterfazOCRWebDDPROLCabecera(Map queryParams);
	
	/**
	 * Ejecuta el proceso de recepcin de Familia Segura WEB
	 * @param params
	 */
	public void executeInterfazWEBRecepcionarFamiliaSegura(Map params);
	
	/**
	 * Procesa los consolidados de Familia Segura WEB
	 * @param map
	 */
	public void executeInterfazWEBProcesarConsolidadoFamiliaSegura(Map params);

	public void executeInterfazOCREnviarLimiteDeVentaProl(Map params);

	public void executeInterfazOCREnviarRangoDePromocionesProl(Map params);

	public void executeInterfazOCREnviarOfertasProl(Map params);

	public void executeInterfazOCREnviarPrecioCatalogo(Map params);

	public void executeInterfazOCREnviarMatrizDeRemplazosProl(Map params);

	public void executeInterfazOCREnviarCabeceraDeMatrizDeFacturacionProl(Map params);

	public void executeInterfazOCREnviarCronogramaDeFacturacionProl(Map params);

	public void executeInterfazOCREnviarDescripcionDeProductosProl(Map params);

	public void executeInterfazOCREnviarDetalleDeOfertasProl(Map params);

	public void executeInterfazOCREnviarGestionDeStockProl(Map params);

	public void executeInterfazOCREnviarGruposDeOfertaProl(Map params);

	public void executeInterfazOCREnviarMaestroDeProductosProl(Map params);

	public void executeInterfazOCREnviarMatrizDeFacturacionProl(Map params);

	public void executeInterfazOCREnviarPromocionesProl(Map params);

	/**ejecuta el proceso de rececion del archivo de contrato
	 * @param map
	 */
	public void executeInterfazOCRContratoEjecutiva(Map map);

	/**Ejecuta el proceso de consolidado de contrato de ejecutivas
	 * @param map
	 */
	public void executeInterfazOCRProcesarConsolidadoContratoEjecutiva(Map map);	

	public void executeInterfazOCREnviarInformacionNuevosFaltantes(Map params);

	public void executeInterfazOCREnviarCuponProl(Map params);
	
	public void executeInterfazOCREnviarCuponHomologacionProl(Map params);
	
	public void executeInterfazOCREnviarCuponParticipantesProl(Map params);

	/**
	 * Ejecuta el proceso de recepcion de boletas de entrega
	 * @param params
	 */
	public void executeInterfazOCRRecepcionarBoletaEntrega(Map params);

	/**
	 * Ejecuta el proceso de recepcion de boletas de recojo
	 */
	public void executeInterfazOCRRecepcionarBoletaRecojo(Map params);
	
	/**
	 * @param queryParams
	 */
	public void executeEnviarMatrizFacturacionFutura(Map queryParams);
	
	/**
	 * Devuelve Valor del Paramtro de la tabla STO_PARAM_GENER_OCCRR
	 * @param params
	 * @return
	 */
	public String getSTOParametroGeneralOCR(Map params);	
}

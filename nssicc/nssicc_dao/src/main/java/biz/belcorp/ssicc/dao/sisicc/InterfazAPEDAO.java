package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <a href="InterfazAPEDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
public interface InterfazAPEDAO extends DAO {
	
	/**
	 * @param params
	 * Ejecuta la interfaz Enviar Cabecera Documentos
	 */	
	public void executeInterfazAPEEnviarCabeceraDocumentosDYTCAB(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Enviar Detalle Documentos
	 */	
	public void executeInterfazAPEEnviarDetalleDocumentosDYTDET(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Enviar Maestro Destinos
	 */	
	public void executeInterfazAPEEnviarMaestroDestinosDYTCLI(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Enviar Maestro Cuentas
	 */	
	public void executeInterfazAPEEnviarMaestroCuentasDYTCTA(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Enviar Maestro Territorios
	 */	
	public void executeInterfazAPEEnviarMaestroTerritoriosDYTSCT(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Enviar Secuencia Cuentas
	 */	
	public void executeInterfazAPEEnviarSecuenciaCuentasDYTSCX(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Enviar Cronograma Operaciones
	 */	
	public void executeInterfazAPEEnviarCronogramaOperacionesDYTCRO(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Enviar Control Proceso
	 */	
	public void executeInterfazAPEEnviarControlProcesoDYTCTR(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Enviar Estimados Distribucion
	 */	
	public void executeInterfazAPEEnviarEstimadosDistribucion(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Recepcionar Anaqueles
	 */	
	public void executeInterfazAPERecepcionarAnaqueles(Map params);
	/**
	 * @param params
	 * Ejecuta la interfaz Recepcionar Chequeo
	 */	
	public void executeInterfazAPERecepcionarChequeo(Map params);
	
	/**
	 * @param criteria
	 * Obtiene la parametria para envio de correos
	 */
	public List getEnvioMailsAPEParams(Map criteria);

	/**
	 * @param criteria
	 * @return
	 * Obtiene la lista de  productos sin indicador
	 */
	public List getListaProductosSinIndicador(Map criteria);
		
	/**
	 * @return
	 * Devuelve el periodo de proceso del envio de anaqueles
	 */
	public String getPeriodoProcesoEnvioAnaqueles();
	
	/**
	 * Ejecuta la Interfaz de Envio Costo por Caja a SAT
	 * @param params
	 */
	public void executeInterfazAPEEnviarCostoCaja(Map params);
	
	/**
	 * Obtiene el codigo de Pais y Marca SAT
	 * @param criteria
	 * @return
	 */
	public List getPaisMarcaSat(Map criteria);
	
		/**
	 * @param criteria
	 * Ejecuta la interfaz Enviar Maestro de Productos
	 */
	public void executeInterfazAPEEnviarArchivoProductos(Map criteria);
	
	/**
	 * @param criteria
	 * Ejecuta la interfaz Enviar Mapa de Anaqueles
	 */
	public void executeInterfazAPEEnviarArchivoAnaqueles(Map criteria);

	/**
	 * @param criteria
	 * Ejecuta la interfaz Enviar Olas a WCS
	 */
	public void executeInterfazAPEEnviarOlasWCS(Map criteria);
	
	/**
	 * @param criteria
	 * Ejecuta la interfaz Enviar Datos de Etiquetado a WCS
	 */
	public void executeInterfazAPEEnviarDatosEtiquetadoWCS(Map criteria);
	
	/**
	 * @param criteria
	 * Ejecuta la interfaz Confirmar Ola a WCS
	 */
	public void executeInterfazAPEEnviarOlasConfirmarWCS(Map criteria);
	
	/**
	 * @param criteria
	 * Ejecuta la interfaz Confirmar Datos Etiquetado a WCS
	 */
	public void executeInterfazAPEEnviarDatosEtiquetadoConfirmarWCS(Map criteria);
	
	/**
	 * Mtodo que permite generar en archivo xml las Facturas Anuladas a WCS
	 * @param criteria
	 */
	public void executeGenerarFacturasAnuladasWCS(Map criteria);
	
	/**
	 * Ejecuta la interfaz Recepcionar Cajas Chequeadas WCS
	 * @param criteria
	 */
	public void executeProcesarCajaEmbalaje(Map criteria);	
	
	/**
	 * Inserta en la tabla temporal la ista de anulados
	 * @param params
	 */
	public void executeInsertarListaAnuladoTemporal(Map params);
	
	public void executeInterfazAPEEnviarProductosMaterialGerenteZonaRegional(Map params);
	
	public void executeInterfazAPEEnviarCabeceraBoletasRecojo(Map params);
	
	public void executeInterfazAPEEnviarCabeceraBoletasRecojoFacturacion(Map params);
	
	public void executeInterfazAPEEnviarDetalleBoletasRecojo(Map params);	
	
	public void executeInterfazAPEEnviarDetalleBoletasRecojoFacturacion(Map params);
	
	/**
	 * Genera el archivo de interface de estimados
	 * @param params
	 */
	public void executeInterfazAPEEnviarEstimadosDistribucionDA(Map params);
	
	/**
	 * @param params
	 */
	public void insertInterfazAPEPedidosDespachados(Map params);
	
	/**
	 * @param params
	 */
	public void insertInterfazAPEPedidosChequeados(Map params);
	
	/**
	 * @param params
	 */
	public void updateInterfazAPEPedidosChequeados(Map params);
	
	/**
	 * @param params
	 */
	public void insertInterfazAPEInicioArmadoPedido(Map params);
	
	
	/**
	 * @param params
	 */
	public void executeInterfazAPECambioFechaPromesa(Map params);
	
	/**
	 * 
	 * @param usuario
	 */
	public void deleteInterfazAPERecepcionarPicadoCabecera(String usuario);
	
	/**
	 * 
	 * @param row
	 */
	public void insertInterfazAPERecepcionarPicadoCabecera(Map row);
	
	/**
	 * 
	 * @param usuario
	 */
	public void deleteInterfazAPERecepcionarPicadoDetalle(String usuario);
	
	/**
	 * 
	 * @param row
	 */
	public void insertInterfazAPERecepcionarPicadoDetalle(Map row);
	
	/**
	 * 
	 * @param row
	 */
	public void insertInterfazAPERecepcionarOrdenImpresion(Map row);
	
	/**
	 * @return
	 */
	public String getCodigoProcesoPrint();
	
	/**
	 * @param params
	 */
	public void executeInterfazAPERecepcionarPicadoCabecera(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazAPERecepcionarPicadoDetalle(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazAPERecepcionarOrdenImpresion(Map params);

}

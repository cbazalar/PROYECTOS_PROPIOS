package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoPEDDAO extends DAO{

	/**
	 * @param params
	 */
	public void executeCargaPedidosDigitados(Map params);

	/**
	 * @param params
	 */
	public void executeConsolidadoPedidos(Map params);

	/**
	 * @param criteria
	 * @return
	 */
	public List getPedidosDigitadosByCriteria(Map criteria);

	/**
	 * Obtiene el indicador  de activida PROL
	 * @param params
	 * @return
	 */
	public String getIndicadorActividadPROL(Map params);
	
	/**
	 * Ejecuta el proceso para inicializar stocks
	 * 
	 */
	public void executeInicializaStock();
	
	/**
	 * Activa el Flag de Control de Actividad para PROL
	 * @param params
	 * @return
	 */
	public void executeEnvioPortalSICCFinDia(Map params);

	/**
	 * Inserta un texto xml en un tabla
	 * @param archivoEntrada
	 */
	public void insertaArchivoEntradaPROL(Map archivoEntrada);
	
	/**
	 * Obtiene la cadena resultado
	 * @param criteria
	 * @return
	 */
	public String getCadenaResultadoPROL(Map criteria);
	
	/**
	 * Genera el archivo de Salida para PROL
	 * @param criteria
	 */
	public void generaArchivoSalidaPROL(Map criteria);
	
	/**
	 * Obtiene el pais y la fecha de facturacion
	 * @param criteria
	 * @return
	 */
	public List getPaisFecha(Map criteria);
	
	/**
	 * Obtiene los totales de un pedido PROL
	 * @param criteria
	 * @return
	 */
	public List getTotalesPROL(Map criteria);
	
	/**
	 * Obtiene el detalle del Pedido PROL
	 * @param criteria
	 * @return
	 */
	public List getDetallePedidoPROL(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getPedidoTemporalById(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getOidSolicitudPROL(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getPedidoDocumentoDigitadoPKByCriteria(Map criteria);
	
	/**
	 * Actualiza el indicador de PROL a cero
	 * @param criteria
	 */
	public void executeActualizaIndicadorPROL(Map criteria);
	
	/**
	 * Obtiene el indicador  de activida PROL
	 * @return
	 */
	public String getIndicadorActividadPROL2();

	/**
	 * Obtiene la lista con los tipos de Carga de la Recepcon de Stock
	 * @return
	 */
	public List getTiposCargaStock();
	
	/**
	 * inserta la audioria PROL y retona el oid en el Map
	 * @param criteria
	 */
	public void insertarAuditoriaPROL(Map criteria);

	/**
	 * Actualiza la audioria PROL
	 * @param criteria
	 */
	public void updateAuditoriaPROL(Map criteria);	

	/**numero de procesos ejecutandose via filtros
	 * @param params
	 * @return
	 */
	public int getProcesoPROL(Map params);

	/**
	 * @param criteria
	 * @return
	 */
	public Map getProcesoColaPROL(Map criteria);
	

	/**
	 * Realiza el envio de correo segun parametro de ind_mail del Bas_repor_param
	 * @param params
	 */
	void sendMailOcr(Map params);
	
	/**
	 * Devuelve lista de procesos a ejecutarse en Nuevo PROL
	 * @param criteria
	 * @return
	 */
	public List getProcesosNuevoPROL(Map criteria);
	
	/**
	 * Ejecuta proceso basado en el Map ingresado como parametro
	 * @param criteria
	 * @param criteria
	 */
	public void executeProceso(Map criteria, Map queryParams);
	
	/**
	 * Ejecuta el prceso de asignacin de stock GP3
	 * @param params
	 */
	void executeProcesoAsignacionStockGP3(Map params);
	
	/**
	 * Ejecuta el proceso de generacion de oportunidad de ahorro.
	 * @param params
	 */
	public void executeProcesoGeneracionOportunidadAhorro(Map params);

	/**
	 * Inserta un registro en la tabla de demanda anticipada
	 * @param params
	 */
	public void insertDemandaAnticipada(Map params);

	/**
	 * Elimina los datos del periodo
	 * @param params
	 */
	public void deleteDemandaAnticipada(Map params);
	
	/**
	 * Obtiene los Subaccesos
	 * @return
	 */
	public List getSubacceso();
	
	/**
	 * Elimina registros de RUV
	 * @param criteria
	 */
	public void eliminaRegistroRUV(Map criteria);
	
	/**
	 * Ejecuta proceso de Pedido Inventariado
	 * @param criteria
	 */
	public void executeProcesoPEDPedidoInventariado(Map criteria);
	
	
	/**
	 * Ejecuta proceso de Servicio Generico
	 * @param criteria
	 */
	public void executeProcesoPEDServicioGenerico(Map criteria);
	
	/**
	 * Obtiene el correlativo para insertar Pedido Rechazadas
	 * @param 
	 * @return
	 */
	public String getNextCorrelativoPedidoRechazadas();
	
	/**
	 * Inserta Pedidos Cabecera en tabla
	 * @param criteria
	 */
	public void insertaCabeceraEntradaPedidoRechazado(Map criteria);
	
	/**
	 * Inserta Pedidos Detalle en tabla
	 * @param criteria
	 */
	public void insertaDetalleEntradaPedidoRechazado(Map criteria);
	
	/**
	 * Inserta Pedidos Alternativos en tabla
	 * @param criteria
	 */
	public void insertaAlternativosEntradaPedidoRechazado(Map criteria);
	
	/**
	 * Ejecuta proceso de Pedido Rechazado
	 * @param criteria
	 */
	public void executeProcesoPEDPedidoRechazado(Map criteria);
	
	/**
	 * Obtiene Pedido Cabecera Rechazados
     * @param criteria
     * @return
     */
    public List getPedidosCabeceraRechazados(Map criteria);
    
    /**
     * Obtiene Pedido Detalle Rechazados
     * @param criteria
     * @return
     */
    public List getPedidosDetalleRechazados(Map criteria);
    
    /**
     * Obtiene Pedido Alternativos Rechazados
     * @param criteria
     * @return
     */
    public List getPedidosAlternativosRechazados(Map criteria);
    
    /**
     * Obtiene Pedido Errores Rechazados
     * @param criteria
     * @return
     */
    public List getPedidosErroresRechazados(Map criteria);
    
    /**
     * Obtiene Pedido Mensajes Rechazados
     * @param criteria
     * @return
     */
    public List getPedidosMensajesRechazados(Map criteria);
    
    /**
     * Devuelve la lista de Zonas (oid) en base a la campaña y a la fecha de facturacion
     * @param criteria
     * @return
     */
    public List getListaZonasByCampanaFechaFacturacion(Map criteria);
	
	/**
	 * Devuelve la lista de pedidos (oid) en base a la zona (oid), campaña y a la fecha de facturacion
	 * @param criteria
	 * @return
	 */
	public List getListaPedidosByZonasCampanaFechaFacturacion(Map criteria);
	
	/**
	 * Ejecuta stored cuadrar ofertas
	 * @param criteria
	 */
	public void executeCuadrarOfertasPedidoZonaMultihilo(Map criteria);
	
	/**
	 * Ejecuta stored Monto Minimo
	 * @param criteria
	 */
	public void executeMontoMinimoPedidoZonaMultihilo(Map criteria);
	
	/**
	 * Ejecuta stored Monto Maximo
	 * @param criteria
	 */
	public void executeMontoMaximoPedidoZonaMultihilo(Map criteria);
	
	/**
	 * Ejecuta stored Recuperación Obligatoria
	 * @param criteria
	 */
	public void executeRecuperacionObligatoriaPedidoZonaMultihilo(Map criteria);
	
	/**
	 * Ejecuta stored Validar Agregados Mav
	 * @param criteria
	 */
	public void executeValidarAgregadosMavPedidoZonaMultihilo(Map criteria);
	
	/**
	 * Ejecuta Cambiar Pedido GP1 a GP3
	 * @param criteria
	 */
	public void executeCambiarPedidoGP1aGP3(Map criteria);
	
	/**
	 * Ejecuta Procesos Especiales
	 * @param criteria
	 */
	public void executeEjecutarProcesosEspeciales(Map criteria);
	
}
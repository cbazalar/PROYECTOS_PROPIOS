/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.scsicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.scsicc.ws.beans.ConsultaOCRWebServiceResultado;


/**
 * <p>
 * <a href="ConsultaInformeOCRAvancePedidoWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva </a>
 */
public interface ConsultaOCRAvancePedidoWebService {
 
    /**
     * Retorna lista de consultoras activas sin pedido
     * @param codigoPeriodo
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getConsultorasActivasSinPedido(String codigoZona,String codigoIsoIdioma) throws RemoteException;
     
    /**
     * Retorna la fecha de ultima actualizacion dela zona
     * @param codigoPeriodo
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getFechaUltimaActualizacionZona(String codigoZona,String codigoIsoIdioma) throws RemoteException;
    
    /**
     * Retorna el numero de registros
     * @param codigoPeriodo
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getNumeroRegistros(String codigoZona,String codigoIsoIdioma) throws RemoteException;
    
    /**
     * Retorna en uno solo el Informe de Avance de Pedido
     * @param codigoPeriodo
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public String []  getInformeAvancePedido(String codigoZona,String codigoIsoIdioma) throws RemoteException;
    
    
    /**
     * Retorna los codigo de venta rechazados para la consultora
     * @param codigoPeriodo
     * @param codigoCliente
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getCodigoVentasRechazados(String codigoCliente,String codigoZona,String codigoIsoIdioma) throws RemoteException;
    

    
    /**
     * Retorna los detalle de pedidos facturados para la consultora
     * @param oidPedido 
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getDetallePedidoFacturado(Integer oidPedido,
    		 String codigoIsoIdioma) throws RemoteException;
    
    
    
    /**
     * Retorna el numero de faltantes Anunciados
     * @param codigoPeriodo
     * @param codigoCliente
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getFaltantesAnunciados(String codigoCliente,String codigoZona,
    		String codigoIsoIdioma) throws RemoteException;
    

    /**
     * Devuelve la campnha activa de facturacion
     * @param codigoZona
     * @return
     * @throws RemoteException
     */
    public String getCampanhaActivaByZona(String codigoZona)throws RemoteException;
    
    /**
     * Devuelve los detalles no facturados del cliente
     * @param codigoCliente
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getDetallePedidoNoFacturado(String codigoCliente) throws RemoteException;
    	
}

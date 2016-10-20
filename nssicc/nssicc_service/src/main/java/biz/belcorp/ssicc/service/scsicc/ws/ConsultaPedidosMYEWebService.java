/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.scsicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.scsicc.ws.beans.ConsultaPedidosMYEWebServiceResultado;


/**
 * <p>
 * <a href="ConsultaPedidosMYEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva </a>
 */
public interface ConsultaPedidosMYEWebService {
 

    /**
     * Obtiene informacion del numero de retencion de pedidos de la consultora en campanhas consecurtivas
     * @param codigoPais
     * @param codigoPeriodo
     * @param codigoRegion
     * @param codigoZona
     * @param numeroRetencion
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaPedidosMYEWebServiceResultado getRetencionPedidos(String codigoPais,
    																 String codigoPeriodo,
    																 String codigoRegion,
    																 String codigoZona,
    																 String numeroRetencion,    																 
    																 String codigoIsoIdioma) throws RemoteException;


    /**
     * Obtiene informacion de los peidos digitados de la consultora de una region y zona especifica
     * @param codigoPais
     * @param codigoPeriodo
     * @param codigoRegion
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaPedidosMYEWebServiceResultado getPedidosDigitados(String codigoPais,
			 String codigoPeriodo,
			 String codigoRegion,
			 String codigoZona,    																 
			 String codigoIsoIdioma) throws RemoteException;
   
}

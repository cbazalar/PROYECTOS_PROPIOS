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
public interface ConsultaInformeOCRAvancePedidoWebService {

 
    /**
     * Retorna la lista de Informe de Avance de Pedido
     * @param codigoPeriodo
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getConsultaInformeAvancePedido(String codigoPeriodo,
    		 String codigoZona,String codigoIsoIdioma) throws RemoteException;
    
    /**
     * Retorna lista de consultoras activas sin pedido
     * @param codigoPeriodo
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getConsultorasActivasSinPedido(String codigoPeriodo,
   		 String codigoZona,String codigoIsoIdioma) throws RemoteException;
     
    /**
     * Retorna la fecha de ultima actualizacion dela zona
     * @param codigoPeriodo
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getFechaUltimaActualizacionZona(String codigoPeriodo,
      		 String codigoZona,String codigoIsoIdioma) throws RemoteException;
    
    /**
     * Retorna el numero de registros
     * @param codigoPeriodo
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public ConsultaOCRWebServiceResultado getNumeroRegistros(String codigoPeriodo,
     		 String codigoZona,String codigoIsoIdioma) throws RemoteException;
    
    /**
     * Retorna en uno solo el Informe de Avance de Pedido
     * @param codigoPeriodo
     * @param codigoZona
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public String []  getInformeAvancePedido(String codigoPeriodo,
    		 String codigoZona,String codigoIsoIdioma) throws RemoteException;    
}

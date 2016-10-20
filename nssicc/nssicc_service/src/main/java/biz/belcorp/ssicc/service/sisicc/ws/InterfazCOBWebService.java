package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;


/**
 * <p>
 * <a href="InterfazCOBWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz"> Sergio Buchelli </a>
 */
public interface InterfazCOBWebService {

    /**
     * Servicio web que envia archivo de recuperacion de cobranza por cobrador
     * 
     * @param codigoPais
     * @param codigoSociedad
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeEnvioRecuperacionCobranzaByCobrador(String codigoPais,
    		String codigoSociedad) throws RemoteException;
     
}

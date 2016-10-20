/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;


/**
 * <p>
 * <a href="InterfazLARWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cdiaz@csigcomt.com"> Carlos Diaz Valverde </a>
 */
public interface InterfazLARWebService {

    /**
     * Servicio web que realiza la recepcion de archivos LAR
     * @param codigoPais
     * @param codigoInterfaz
     * @param codigoPeriodo
     * @param fechaFacturacion
     * @param usuario
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionLARWeb(String codigoPais,
    		String codigoInterfaz,
    		String usuario) throws RemoteException;
     
}

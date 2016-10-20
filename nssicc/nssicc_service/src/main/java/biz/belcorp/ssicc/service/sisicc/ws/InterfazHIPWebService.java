package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;


/**
 * <p>
 * <a href="InterfazHIPWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz"> Sergio Apaza </a>
 */
public interface InterfazHIPWebService {

    /**
     * Servicio web que permite recepcionar la informaci�n del Registro de Ventas
     * 
     * @param codigoPais
     * @param codigoInterfaz
     * @param codigoPeriodo
     * @param fechaFacturacion
     * @param usuario
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionRegistroVentasWeb(String codigoPais,
    		String usuario, String nombreArchivo) throws RemoteException;
     
}

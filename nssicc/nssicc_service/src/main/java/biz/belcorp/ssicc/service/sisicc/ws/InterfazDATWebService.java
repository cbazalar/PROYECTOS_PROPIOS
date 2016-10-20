/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;


/**
 * <p>
 * <a href="InterfazDATWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
public interface InterfazDATWebService {

    /**
     * Servicio web para envio de Interfaz DAT-X
     * @param codigoPais
     * @param codigoInterfaz
     * @param codigoPeriodo
     * @param fechaFacturacion
     * @param usuario
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeInterfazDATEnviarAdministracionFlujos(String codigoPais,
    		String codigoInterfaz,
    		String codigoPeriodo,
    		String fechaFacturacion,
    		String codigoMarca,
    		String codigoCanal,
    		String codigoAcceso,
    		String usuario) throws RemoteException;
    
    /**
     * Servicio web que realiza la recepcion de archivos ocr
     * @param codigoPais
     * @param codigoInterfaz
     * @param codigoPeriodo
     * @param fechaFacturacion
     * @param usuario
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionOCSWebDD(String codigoPais,
    		String codigoInterfaz,
    		String usuario) throws RemoteException;
     
}

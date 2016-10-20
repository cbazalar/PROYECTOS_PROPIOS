/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

/**
 * <p>
 * <a href="InterfazCompuestaOCRWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
public interface InterfazCompuestaOCRWebService {
	
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
    public void executeRecepcionCompuestaWeb(String codigoPaisOcr,
    		String codigoCompania,
    		String usuario) throws RemoteException;
     
}

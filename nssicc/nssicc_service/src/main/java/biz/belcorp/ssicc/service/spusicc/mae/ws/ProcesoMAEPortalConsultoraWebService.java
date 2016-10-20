/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.mae.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.ConsultoraMAEWebService;


/**
 * <p>
 * <a href="ProcesoMAEPortalConsultoraWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva </a>
 */
public interface ProcesoMAEPortalConsultoraWebService {

 
    /**
     * se ejecuta la actualizacion de datos , servicio proviene de portal consultoras ,
     * retorna 1 en caso de exito , o error
     * @param codigoPais
     * @param consultora
     * @return
     * @throws RemoteException
     */
    public String actualizacionDatos(String codigoPais,ConsultoraMAEWebService consultora
    		 ) throws RemoteException;
    
    
     
}

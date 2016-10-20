package biz.belcorp.ssicc.service.spusicc.let.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.let.ws.beans.ProcesoLETWebServiceResultado;


/**
 * <p>
 * <a href="ProcesoMAEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface ProcesoLETWebService {

     /**
     * Permite obtener los datos de Consultora L�der.
     *  
     * @param codigoConsultora
     * @return
     * @throws RemoteException
     */
    public ProcesoLETWebServiceResultado consultarLider(String codigoConsultora) throws RemoteException;

    public ProcesoLETWebServiceResultado obtenerLideres(String codigoPais) throws RemoteException;
}

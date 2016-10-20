/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.scsicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.scsicc.ws.beans.ConsultaPERWebServiceResultado;


/**
 * <p>
 * <a href="ConsultaPERControlAsistenciaWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva </a>
 */
public interface ConsultaPERControlAsistenciaWebService {
 

    /**
     * Retorna lista de control ASISTENCIA
     * @param codigoPais
     * @param codigoMarca
     * @param codigoCanal
     * @param codigoPeriodo
     * @param CodigoRegion
     * @param codigoZona
     * @param seleccion (A:activas T:Todas)
     * @return
     * @throws RemoteException
     */
    public ConsultaPERWebServiceResultado getControlAsistencia(String codigoPais,String codigoMarca,
    						String codigoCanal,String codigoPeriodo,String codigoRegion,
    						String codigoZona,String seleccion) throws RemoteException;
     
  
    	
}

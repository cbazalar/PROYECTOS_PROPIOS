/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;


/**
 * <p>
 * <a href="InterfazWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno </a>
 */
public interface InterfazSAMWebService {

    /**
     * Servicio web para envio de Interfaz SAM-7
     * @param codigoPais
     * @param codigoInterfaz
     * @param codigoPeriodo
     * @param fechaFacturacion
     * @param numeroLote
     * @param indicadorMaterialPromocional
     * @param usuario
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeInterfazSAMEnviarMovimientosAlmacen(String codigoPais,String codigoInterfaz
    		,String codigoPeriodo,String fechaFacturacion,String numeroLote,
    		String indicadorMaterialPromocional,String usuario) throws RemoteException;
     
}

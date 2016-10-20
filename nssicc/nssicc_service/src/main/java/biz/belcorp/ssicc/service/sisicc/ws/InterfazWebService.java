/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultadoChequeoLar;


/**
 * <p>
 * <a href="InterfazWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno </a>
 */
public interface InterfazWebService {

    /**
     * Servicio web para envio Interfaz RET-9
     * 
     * @param codigoPais
     *            codigo del pais
     * @return
     */
    public InterfazResultado executeEnvioMatrizPuntajes(String codigoPais, String usuario) throws RemoteException;

    public InterfazResultadoChequeoLar resultadoChequeoList(String codigoPais, String codigoInterfaz, String codigoMarca,
    		String codigoCanal,String codigoAcceso,String codigoSubacceso,
    		String usuarioLogin,String codigoIsoIdioma) throws RemoteException;

    public InterfazResultadoChequeoLar resultadoSecuenciacionZonasList(String codigoPais, String codigoInterfaz, 
    		String usuarioLogin,String codigoIsoIdioma) throws RemoteException;    
    
    /**
     * Servicio web que expone la ejecucion de la interfaz RET-1
     * @param codigoPais
     * @param periodoInicial
     * @param periodoFinal
     * @param usuario
     * @return
     */
    public InterfazResultado executeEnvioMatrizRetail(String codigoPais,
			String periodoInicial, String periodoFinal, String usuario) throws RemoteException;
    
}

package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;


/**
 * <p>
 * <a href="InterfazSATWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz"> Sergio Buchelli </a>
 */
public interface InterfazSATWebService {

    /**
     * Servicio web que permite recepcionar la 
     * SAT-13 RECEPCIONAR CIAS TRANSPORTE Y CENTRO ACOPIO
     * 
     * @param codigoPais
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionCiaTrasnportesCentroAcopio(String codigoPais) throws RemoteException;
    
    /**
     * Servicio web que permite recepcionar la 
     * SAT-14 RECEPCIONAR COBERTURA POR CODIGO TERRITORIAL
     * 
     * @param codigoPais
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionCoberturaPorCodigoTerritorial(String codigoPais) throws RemoteException;
    
    /**
     * Servicio web que permite recepcionar la 
     * SAT-15 RECEPCION ARCHIVO DIVISION DE ARMADO CDP
     * 
     * @param codigoPais
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionDivisionArmadoCDP(String codigoPais) throws RemoteException;
    
    /**
     * Servicio web que permite recepcionar la 
     * APE-11 Recepcionar Chequeo
     * 
     * @param codigoPais
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionChequeo(String codigoPais) throws RemoteException;

    /**
     * Servicio web que permite recepcionar la 
     * SAT-16 Orden de Impresi�n Generado por APESAT.
     * 
     * @param codigoPais
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionOrdenImpresionAPESAT(String codigoPais) throws RemoteException;

    /**
     * Servicio web que permite recepcionar la 
	 * SAT-17 Parametrizaci�n para c�lculo de fecha de entrega exacta.
     * 
     * @param codigoPais
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionParametrizacionCalculoFechaEntregaExacta(String codigoPais) throws RemoteException;

    /**
     * Servicio web que permite recepcionar la 
     * SAT-18 Excepciones para fecha de entrega Exacta.
     * 
     * @param codigoPais
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionExcepcionesFechaEntregaExacta(String codigoPais) throws RemoteException;
    
    /**
     * Servicio web que permite recepcionar la 
	 * SAT-19 Seguimiento al Pedido.
     * 
     * @param codigoPais
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionSeguimientoPedido(String codigoPais) throws RemoteException;

    /**
     * Servicio web que permite recepcionar la 
	 * SAT-20 IMPRESI�N BOLETAS DE ENTREGA.
     * 
     * @param codigoPais
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeRecepcionImpresionBoletaEntrega(String codigoPais) throws RemoteException;
    
}

package biz.belcorp.ssicc.service.spusicc.cuentacorriente.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ws.beans.ProcesoCCCWebServiceResultado;


/**
 * <p>
 * <a href="ProcesoCCCWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar </a>
 */
public interface ProcesoCCCWebService {

    /**
     * Proceso que inserta Pago para una consultora
     * @param codigoBanco
     * @param numeroOperacion
     * @param consultora
     * @param fechaPago
     * @param montoPago
     * @return
     * @throws RemoteException
     */
    public ProcesoCCCWebServiceResultado insertarPago(
    		String codigoBanco, 
    		String numeroOperacion, 
    		String consultora, 
    		String fechaPago,
    		String montoPago) throws RemoteException;

    /**
     * Proceso de Reversion de Pago para una consultora
     * @param codigoBanco
     * @param numeroOperacion
     * @param consultora
     * @param fechaPago
     * @return
     * @throws RemoteException
     */
    public ProcesoCCCWebServiceResultado reversarPago(
    		String codigoBanco, 
    		String numeroOperacion, 
    		String consultora, 
    		String fechaPago) throws RemoteException;
    
    /**
     * Consultar Saldo de la Consultora
     * @param consultora
     * @return
     * @throws RemoteException
     */
    public ProcesoCCCWebServiceResultado consultarSaldo(String consultora) throws RemoteException;
}

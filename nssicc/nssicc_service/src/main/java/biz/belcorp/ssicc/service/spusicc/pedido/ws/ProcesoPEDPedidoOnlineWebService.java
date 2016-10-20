/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.pedido.ws;

import java.rmi.RemoteException;
import java.util.Date;


/**
 * <p>
 * <a href="ProcesoPEDPedidoOnlineWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface ProcesoPEDPedidoOnlineWebService {

	/**
	 * Activa el Flag de Actividad para PROL de la tabla BAS_CTRL_FACT
	 * @param codigoPeriodo
	 * @throws RemoteException
	 */
	public boolean executeEnvioPortalSICCFinDia(String pais, String marca, Date fechaLlamada) throws RemoteException;
	
	/**
	 * Recepciona un pedido PROL
	 * @param archivoEntrada
	 * @return
	 * @throws RemoteException
	 */
	public String[] executeRecepcionPedidosPROL(String archivoEntrada,long oidProceso,long oidCola) throws RemoteException;
}

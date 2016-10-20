/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.pedido.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.PedidoWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDPedidoRechazadoWebServiceResponse;

/**
 * <p>
 * <a href="ProcesoPEDPedidoRechazadoWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Aurelio Oviedo</a>
 */
public interface ProcesoPEDPedidoRechazadoWebService {

	/**
	 * Ejecuta WebService de Pedidos Rechazados
	 * @param beanPedido
	 * @return
	 * @throws RemoteException
	 */
	public ProcesoPEDPedidoRechazadoWebServiceResponse ejecutarProcesoPEDPedidoRechazado(PedidoWebServiceParameter beanPedido) throws RemoteException;
	
}
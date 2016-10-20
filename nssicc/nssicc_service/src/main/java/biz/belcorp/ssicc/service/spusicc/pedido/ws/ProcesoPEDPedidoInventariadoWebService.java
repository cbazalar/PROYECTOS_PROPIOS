/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.pedido.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.NovedadWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDPedidoInventariadoWebServiceResponse;


/**
 * <p>
 * <a href="ProcesoPEDPedidoInventariadoWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Carlos Bazalar</a>
 */
public interface ProcesoPEDPedidoInventariadoWebService {

	/**
	 * Ejecuta WebService de Pedidos Inventariados
	 * @param beanNovedad
	 * @return
	 * @throws RemoteException
	 */
	public ProcesoPEDPedidoInventariadoWebServiceResponse ejecutarProcesoPEDPedidoInventariado(NovedadWebServiceParameter beanNovedad) throws RemoteException;
	
	
}

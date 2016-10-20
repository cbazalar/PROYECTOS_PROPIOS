/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.pedido.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDServicioGenericoWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ServicioGenericoWebServiceParameter;


/**
 * <p>
 * <a href="ProcesoPEDServicioGenericoWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Gonzalo Huertas</a>
 */
public interface ProcesoPEDServicioGenericoWebService {

	
	/**
	 * Ejecuta web service de pedido servicio generico
	 * @param beanServicio
	 * @return
	 * @throws RemoteException
	 */
	public ProcesoPEDServicioGenericoWebServiceResponse ejecutarProcesoPEDServicioGenerico(ServicioGenericoWebServiceParameter beanServicio) throws RemoteException;
	
	
}

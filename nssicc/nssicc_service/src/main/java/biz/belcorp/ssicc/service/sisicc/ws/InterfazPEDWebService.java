package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;

/**
 * <p>
 * <a href="InterfazPEDWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com"> Gonzalo Javier Huertas Agurto </a>
 */
public interface InterfazPEDWebService {

	/**
	 * @param codigoCentro
	 * @param codigoCampanha
	 * @return
	 * @throws RemoteException
	 */
	public InterfazResultado executeEnviarMatrizFacturacionFutura(String codigoCentro, String codigoCampanha) throws RemoteException;
}

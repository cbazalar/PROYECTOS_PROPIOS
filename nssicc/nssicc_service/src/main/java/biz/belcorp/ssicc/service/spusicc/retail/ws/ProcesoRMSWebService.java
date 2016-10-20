/*
 * Created on 23/12/2015 11:09:35 AM 
 * biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DescuentosRetailRETWebServiceResultado;


/**
 * <p>
 * <a href="ProcesoRMSWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado
 */
public interface ProcesoRMSWebService {
 
	/**
	 * RCR PER-SiCC-2015-0823. Desarrollo de WebService Tabla Rango de Descuento
	 * Permite obtener el objeto Descuento, el cual devolverá información de la escala de descuentos.
	 * 
	 */
	public DescuentosRetailRETWebServiceResultado getEscalaDescuentoRetail()
			throws RemoteException;
	

}

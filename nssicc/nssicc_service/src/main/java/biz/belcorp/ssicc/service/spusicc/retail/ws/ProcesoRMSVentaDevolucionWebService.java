/*
 * Created on 30/12/2015 09:16:15 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSVentaDevolucionWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentaDevolucionRMSWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentaDevolucionWebServiceParameter;


/**
 * <p>
 * <a href="ProcesoRMSVentaDevolucionWebService.java"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado
 */
public interface ProcesoRMSVentaDevolucionWebService {
 	
    
	/**
	 * RCR PER-SiCC-2015-0840: Desarrollo de WebService ejecuta proceso de Grabar Venta de Devolución
	 * Permite obtener el objeto VentaDevolucion, el cual grabará la venta de devolución.
	 * 
	 */ 	
	public VentaDevolucionRMSWebServiceResultado getVentaDevolucion(VentaDevolucionWebServiceParameter beanVentaDevolucion) throws RemoteException;
	
	
}

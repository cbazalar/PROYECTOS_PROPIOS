/*
 * Created on 28/12/2015 09:24:15 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSPrecioProductoWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.PrecioProductoRMSWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.PrecioProductoWebServiceParameter;


/**
 * <p>
 * <a href="ProcesoRMSDatosConsultoraWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado
 */
public interface ProcesoRMSPrecioProductoWebService {
 	
    
	/**
	 * PER-SiCC-2015-0838: Desarrollo de WebService Consulta de Precios de un Producto
	 * Permite obtener el objeto PrecioProducto, el cual devolverá los precios de un producto.
	 * 
	 */ 	
	public PrecioProductoRMSWebServiceResultado getPrecioProducto(PrecioProductoWebServiceParameter beanProducto) throws RemoteException;
	
	
}

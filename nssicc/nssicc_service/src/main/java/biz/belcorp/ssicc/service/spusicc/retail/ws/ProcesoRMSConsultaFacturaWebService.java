/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaFacturaRMSWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaFacturaWebServiceParameter;

/**
 * @author Richar Cruzado
 * @date   29/12/2015
 */
public interface ProcesoRMSConsultaFacturaWebService {

	
	/**
	 * RCR PER-SiCC-2015-0843: Desarrollo de WebService Información Consulta de Facturas
	 * Permite obtener el objeto ConsultaFactura, el cual devolverá datos de consulta de facturas.
	 * 
	 */
	public ConsultaFacturaRMSWebServiceResultado getConsultaFactura(ConsultaFacturaWebServiceParameter beanFactura) throws RemoteException;
	
	
}

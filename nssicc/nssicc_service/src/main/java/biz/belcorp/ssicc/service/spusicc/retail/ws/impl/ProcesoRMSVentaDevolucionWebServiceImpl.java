/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSVentaDevolucionWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentaDevolucionRMSWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentaDevolucionRMSWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentaDevolucionWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.ventadevolucion.retail.ProcesoRMSVentaDevolucionRetailService;

/**
 * @author Richar Cruzado
 * @date   30/12/2015
 */
public class ProcesoRMSVentaDevolucionWebServiceImpl extends ServletEndpointSupport implements
	ProcesoRMSVentaDevolucionWebService{
	
	Log log = LogFactory.getLog(ProcesoRMSVentaDevolucionWebServiceImpl.class);
    
    ProcesoRMSVentaDevolucionRetailService procesoRMSVentaDevolucionRetailService;
    
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	
    	procesoRMSVentaDevolucionRetailService = (ProcesoRMSVentaDevolucionRetailService)getWebApplicationContext().getBean("spusicc.procesoRMSVentaDevolucionRetailService");
    	    	    	    	    	    
    }

	@Override
	public VentaDevolucionRMSWebServiceResultado getVentaDevolucion(VentaDevolucionWebServiceParameter beanVentaDevolucion)
			throws RemoteException {
		
		log.debug("Entrando al método getVentaDevolucion");
		VentaDevolucionRMSWebServiceResultado objRespuesta = new VentaDevolucionRMSWebServiceResultado();
		VentaDevolucionRMSWebService obj= null;
		
		try {			
			Map criteria = new HashMap();  
			criteria = BeanUtils.describe(beanVentaDevolucion);
			if (!"".equals(beanVentaDevolucion.getNroDocInterno())) {
				log.debug("numeroDocInterno>>> " +beanVentaDevolucion.getNroDocInterno());
				obj = new VentaDevolucionRMSWebService();
				BeanUtils.copyProperties(obj, criteria);
				log.debug("criteria>>> " +criteria);
				procesoRMSVentaDevolucionRetailService.insertaVentaDevolucion(criteria);			
				//Respuesta al WebService
				objRespuesta.setStatus("OK");
				objRespuesta.setMensaje("");
				log.debug("El proceso Grabar Venta de Devolución se realizó correctamente.");
			} else {
				log.debug("nroDocInterno>>> " +beanVentaDevolucion.getNroDocInterno());
				objRespuesta.setStatus("ERROR");
				objRespuesta.setMensaje("");
				log.debug("Hubo un error en la inserción de venta de devolución.");
			}
						
		} catch (Exception e) {
			log.error(e.getMessage());
			objRespuesta.setStatus("ERROR");
			objRespuesta.setMensaje(e.getMessage());			
		}
		
		log.debug("Finalizando al método getVentaDevolucion");
		
		return objRespuesta;
	}	
		
}

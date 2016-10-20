/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.service.spusicc.precioproducto.retail.ProcesoRMSPrecioProductoRetailService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSPrecioProductoWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.PrecioProductoRMSWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.PrecioProductoRMSWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.PrecioProductoWebServiceParameter;

/**
 * @author Richar Cruzado
 * @date   28/12/2015
 */
public class ProcesoRMSPrecioProductoWebServiceImpl extends ServletEndpointSupport implements
	ProcesoRMSPrecioProductoWebService{
	
	Log log = LogFactory.getLog(ProcesoRMSPrecioProductoWebServiceImpl.class);
    
    ProcesoRMSPrecioProductoRetailService procesoRMSPrecioProductoRetailService;
    
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	
    	procesoRMSPrecioProductoRetailService = (ProcesoRMSPrecioProductoRetailService)getWebApplicationContext().getBean("spusicc.procesoRMSPrecioProductoRetailService");
    	    	    	    	    	    
    }

	@Override
	public PrecioProductoRMSWebServiceResultado getPrecioProducto(PrecioProductoWebServiceParameter beanProducto)
			throws RemoteException {
		
		log.debug("Entrando al metodo getPrecioProducto");		
		PrecioProductoRMSWebServiceResultado resultado = new PrecioProductoRMSWebServiceResultado();		
		PrecioProductoRMSWebService precioProductoRMSWebServices[] = null;			
		 
		Map criteria = new HashMap();	
		criteria.put("codigoPeriodo", beanProducto.getCodigoPeriodo());		
		criteria.put("codigoSap", beanProducto.getCodigoSap());	
													
		List listaPrecioProducto = procesoRMSPrecioProductoRetailService.getPrecioProductoRetail(criteria);		
		precioProductoRMSWebServices = new PrecioProductoRMSWebService[listaPrecioProducto.size()];
		log.debug("size:>>> " + listaPrecioProducto.size());		
		
		PrecioProductoRMSWebService obj= null;						
		if(listaPrecioProducto.size() > 0) {
			for (int i = 0; i < listaPrecioProducto.size(); i++) {
				obj = new PrecioProductoRMSWebService();
				Map map = (Map) listaPrecioProducto.get(i);
				log.debug("map>>> " +map);
				try {
					BeanUtils.copyProperties(obj, map);	
					criteria = BeanUtils.describe(beanProducto);
				} catch (Exception e) {
					e.printStackTrace();
				}
				precioProductoRMSWebServices[i]=obj; 
			}
		
		} else {
			log.debug("No existen registros.>>> Nro. de registros: " + listaPrecioProducto.size());
			precioProductoRMSWebServices = new PrecioProductoRMSWebService[listaPrecioProducto.size()+1];				
			for(int i = 0; i < listaPrecioProducto.size()+1; i++) {
				obj = new PrecioProductoRMSWebService();			
				Map map = new HashMap();	
				map.put("estrategia", "No existe");		
				map.put("codigoOferta", "No existe");
				map.put("codigoSap", beanProducto.getCodigoSap());
				map.put("codigoPeriodo", beanProducto.getCodigoPeriodo());
				map.put("descripcionProducto", "No existe");
				map.put("codigoVenta", null);
				map.put("precioCatalogo", null);
				map.put("codigoCatalogo", null);
				map.put("descripcionCatalogo", null);
				map.put("numeroPagina", null);
				map.put("cuadre", null);	
				map.put("tasaImpuesto", null);
				map.put("valorDescuentoEspe", null);
				map.put("valorLibre1", null);
				map.put("valorLibre2", null);
				map.put("valorLibre3", null);
				
				log.debug("map>>> " +map);
				try {
					BeanUtils.copyProperties(obj, map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				precioProductoRMSWebServices[listaPrecioProducto.size()]=obj;
			}
		}
		  
		resultado.setPrecioProductoRMSWebServices(precioProductoRMSWebServices);
		log.debug("Finalizando al metodo getPrecioProducto");
		
		return resultado;
		
	}
		
}

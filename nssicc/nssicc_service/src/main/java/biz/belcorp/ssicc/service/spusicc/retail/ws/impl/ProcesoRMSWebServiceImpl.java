/*
 * Created on 23/12/2015 10:59:05 AM 
 * biz.belcorp.ssicc.service.spusicc.retail.ws.impl.ProcesoRMSWebServiceImpl
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.service.spusicc.descuento.retail.ProcesoRMSEscalaDescuentosRetailService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DescuentoRMSWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DescuentosRetailRETWebServiceResultado;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoRMSWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado 
 */
public class ProcesoRMSWebServiceImpl extends ServletEndpointSupport implements
		ProcesoRMSWebService{

    Log log = LogFactory.getLog(ProcesoRMSWebServiceImpl.class);
    
    ProcesoRMSEscalaDescuentosRetailService procesoRMSService;
    
    
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	
    	procesoRMSService = (ProcesoRMSEscalaDescuentosRetailService)getWebApplicationContext().getBean("spusicc.procesoRMSEscalaDescuentosRetailService");
    	    	    	    	    	    
    }

	
	/**
	 * Retorna resultado de la consulta de escala de descuento.
	 */	
    public DescuentosRetailRETWebServiceResultado getEscalaDescuentoRetail() {
		
		log.debug("Entrando al método getEscalaDescuentoRetail");
		DescuentosRetailRETWebServiceResultado resultado = new DescuentosRetailRETWebServiceResultado();
		DescuentoRMSWebService descuentoRMSWebServices[] = null;
		
	    List listaDsctos = procesoRMSService.getEscalaDescuentoRetail();
	    descuentoRMSWebServices = new DescuentoRMSWebService[listaDsctos.size()];
	    log.debug("size " + listaDsctos.size());
	    Iterator it = listaDsctos.iterator();
	    DescuentoRMSWebService obj= null;

	    for (int i = 0; i < listaDsctos.size(); i++) {
	    	obj= new DescuentoRMSWebService();
	    	Map map = (Map)listaDsctos.get(i);
	    	log.debug("map>>> "+map);	    	
	    	try {
				BeanUtils.copyProperties(obj, map);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
	    	descuentoRMSWebServices[i]= obj;
		}

		resultado.setDescuentoRMSWebServices(descuentoRMSWebServices);
		log.debug("Finalizando al método consultarEscalaDescuento");
		return resultado;
	}

}

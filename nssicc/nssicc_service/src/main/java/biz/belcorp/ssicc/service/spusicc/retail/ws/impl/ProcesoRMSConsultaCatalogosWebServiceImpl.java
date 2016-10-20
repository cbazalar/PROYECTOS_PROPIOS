/*
 * Created on 21/01/2016 10:59:05 AM 
 * biz.belcorp.ssicc.service.spusicc.retail.ws.impl.ProcesoRMSConsultaCatalogosWebServiceImpl
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.impl;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.service.spusicc.consultacatalogos.retail.ProcesoRMSConsultaCatalogosRetailService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSConsultaCatalogosWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaCatalogosRMSWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaCatalogosRMSWebServiceResultado;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoRMSConsultaCatalogosWebServiceImpl.java"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado Vallejos
 */
public class ProcesoRMSConsultaCatalogosWebServiceImpl extends ServletEndpointSupport implements
		ProcesoRMSConsultaCatalogosWebService {

    Log log = LogFactory.getLog(ProcesoRMSConsultaCatalogosWebServiceImpl.class);
    
    ProcesoRMSConsultaCatalogosRetailService procesoRMSConsultaCatalogosRetailService;
    
    
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	
    	procesoRMSConsultaCatalogosRetailService = (ProcesoRMSConsultaCatalogosRetailService)getWebApplicationContext().getBean("spusicc.procesoRMSConsultaCatalogosRetailService");
    	    	    	    	    	    
    }

	
	/**
	 * Retorna resultado de la consulta de catalogos.
	 */	    
	@Override
	public ConsultaCatalogosRMSWebServiceResultado getConsultaCatalogos()
			throws RemoteException {
		
		log.debug("Entrando al método getConsultaCatalogosRetail");
		ConsultaCatalogosRMSWebServiceResultado resultado = new ConsultaCatalogosRMSWebServiceResultado();
		ConsultaCatalogosRMSWebService consultaCatalogosRMSWebServices[] = null;			  
		
		List listaCatalogos = procesoRMSConsultaCatalogosRetailService.getConsultaCatalogosRetail();
		consultaCatalogosRMSWebServices = new ConsultaCatalogosRMSWebService[listaCatalogos.size()];
	    log.debug("size " + listaCatalogos.size());
	    
	    ConsultaCatalogosRMSWebService obj = null;

	    for (int i = 0; i < listaCatalogos.size(); i++) {
	    	obj= new ConsultaCatalogosRMSWebService();
	    	Map map = (Map)listaCatalogos.get(i);
	    	log.debug("map>>> "+map);	    	
	    	try {
				BeanUtils.copyProperties(obj, map);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
	    	consultaCatalogosRMSWebServices[i]= obj;
		}

	    resultado.setConsultaCatalogosRMSWebServices(consultaCatalogosRMSWebServices);
		log.debug("Finalizando al método getConsultaCatalogosRetail");
		return resultado;
	}

}

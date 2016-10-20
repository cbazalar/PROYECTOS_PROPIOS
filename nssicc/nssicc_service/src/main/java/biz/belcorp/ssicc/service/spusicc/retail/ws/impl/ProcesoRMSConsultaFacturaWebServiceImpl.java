/**
 * Created on 23/12/2015 11:20:15 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.impl.ProcesoRMSConsultaFacturaWebServiceImpl
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

import biz.belcorp.ssicc.service.spusicc.consultafactura.retail.ProcesoRMSConsultaFacturaRetailService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSConsultaFacturaWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaFacturaRMSWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaFacturaRMSWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaFacturaWebServiceParameter;

/**
 * @author Richar Cruzado
 * @date 20/12/2015
 */
public class ProcesoRMSConsultaFacturaWebServiceImpl extends ServletEndpointSupport implements
	ProcesoRMSConsultaFacturaWebService {
	
	Log log = LogFactory.getLog(ProcesoRMSConsultaFacturaWebServiceImpl.class);
    
    ProcesoRMSConsultaFacturaRetailService procesoRMSConsultaFacturaRetailService;
    
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	
    	procesoRMSConsultaFacturaRetailService = (ProcesoRMSConsultaFacturaRetailService)getWebApplicationContext().getBean("spusicc.procesoRMSConsultaFacturaRetailService");
    	    	    	    	    	    
    }

	@Override
	public ConsultaFacturaRMSWebServiceResultado getConsultaFactura(ConsultaFacturaWebServiceParameter beanFactura)
			throws RemoteException {
		
		log.debug("Entrando al método getConsultaFactura");
		ConsultaFacturaRMSWebServiceResultado resultado = new ConsultaFacturaRMSWebServiceResultado();		
		ConsultaFacturaRMSWebService consultaFacturaRMSWebServices[] = null;
		    
		Map criteria = new HashMap();
		criteria.put("numeroFactura", beanFactura.getNumeroFactura());
		criteria.put("numeroPedido", beanFactura.getNumeroPedido());
		criteria.put("codigoPeriodo", beanFactura.getCodigoPeriodo()); //RC			
		
		List listaConsultaFactura = procesoRMSConsultaFacturaRetailService.getConsultaFacturaRetail(criteria);
		
		consultaFacturaRMSWebServices = new ConsultaFacturaRMSWebService[listaConsultaFactura.size()];
		log.debug("size:>>> " + listaConsultaFactura.size());
		ConsultaFacturaRMSWebService obj = null;
		
		for (int i = 0; i < listaConsultaFactura.size(); i++) {
			obj = new ConsultaFacturaRMSWebService();
			Map map = (Map) listaConsultaFactura.get(i);
			log.debug("map>>> " +map);
			try {
				BeanUtils.copyProperties(obj, map);
				criteria = BeanUtils.describe(beanFactura);
			} catch (Exception e) {
				e.printStackTrace();
			}
			consultaFacturaRMSWebServices[i] = obj;			
		}
		
		resultado.setConsultaFacturaRMSWebServices(consultaFacturaRMSWebServices);
		log.debug("Finalizando al método getConsultaFactura");
		
		return resultado;
		
	}
		
}

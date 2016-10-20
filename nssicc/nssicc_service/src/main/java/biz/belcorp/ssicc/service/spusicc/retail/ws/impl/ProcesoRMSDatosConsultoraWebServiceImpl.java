/*
 * Created on 23/12/2015 11:29:20 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.impl.ProcesoRMSDatosConsultoraWebServiceImpl
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.impl;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.datosconsultora.retail.ProcesoRMSDatosConsultoraRetailService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSDatosConsultoraWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DatosConsultoraRMSWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DatosConsultoraRMSWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DatosConsultoraWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentaDevolucionRMSWebService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoRMSDatosConsultoraWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado 
 */
public class ProcesoRMSDatosConsultoraWebServiceImpl extends ServletEndpointSupport implements
	ProcesoRMSDatosConsultoraWebService{

    Log log = LogFactory.getLog(ProcesoRMSDatosConsultoraWebServiceImpl.class);
    
    ProcesoRMSDatosConsultoraRetailService procesoRMSDatosConsultoraService;
    
    
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	
    	procesoRMSDatosConsultoraService = (ProcesoRMSDatosConsultoraRetailService)getWebApplicationContext().getBean("spusicc.procesoRMSDatosConsultoraRetailService");
    	    	    	    	    	    
    }

    /**
	 * Retorna resultado de la consulta de información de datos de la Consultora.
	 */	    
	public DatosConsultoraRMSWebServiceResultado getDatosConsultora(DatosConsultoraWebServiceParameter beanConsultora)
			throws RemoteException {
		
		log.debug("Entrando al método getDatosConsultora");		
		DatosConsultoraRMSWebServiceResultado resultado = new DatosConsultoraRMSWebServiceResultado();		
		DatosConsultoraRMSWebService datosConsultoraRMSWebServices[] = null;				
					
		Map criteria = new HashMap();	
		criteria.put("codigoConsultora", beanConsultora.getCodigoConsultora());		
		criteria.put("nroDocumento", beanConsultora.getNroDocumento());
		criteria.put("primerApellido", beanConsultora.getPrimerApellido());
		criteria.put("segundoApellido", beanConsultora.getSegundoApellido());
		criteria.put("nombres", beanConsultora.getNombres());
		
		//Llamada al SP RET_PR_OBTIE_DATOS_CONSU
		procesoRMSDatosConsultoraService.executeDatosConsultoraRetail(criteria);
		
		//Llamada a la funcion RET_FN_OBTIE_DATOS_CONSU
		List listaDatosConsultora = procesoRMSDatosConsultoraService.getDatosConsultoraRetail(criteria);		
		
		datosConsultoraRMSWebServices = new DatosConsultoraRMSWebService[listaDatosConsultora.size()];
		log.debug("size:>>> " + listaDatosConsultora.size());
		DatosConsultoraRMSWebService obj= null;			
		
		if(listaDatosConsultora.size() > 0) {	
			for (int i = 0; i < listaDatosConsultora.size(); i++) {
				obj = new DatosConsultoraRMSWebService();
				Map map = (Map) listaDatosConsultora.get(i);
				
				String fechaProceso = DateUtil.convertDateToString("dd/MM/yyyy", new Date() );
				map.put("fechaProceso", fechaProceso);
				log.debug("map>>> " +map);
				try {
					BeanUtils.copyProperties(obj, map);	
					criteria = BeanUtils.describe(beanConsultora);
				} catch (Exception e) {
					e.printStackTrace();
				}
				datosConsultoraRMSWebServices[i++]=obj;
			} 			
		}
		
		resultado.setDatosConsultoraRMSWebServices(datosConsultoraRMSWebServices); 
		
		log.debug("Finalizando al método getDatosConsultora");
		
		return resultado;
	}
	
	
}

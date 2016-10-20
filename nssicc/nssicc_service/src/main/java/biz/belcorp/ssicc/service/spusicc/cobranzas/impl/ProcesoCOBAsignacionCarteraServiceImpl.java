package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBAsignacionCarteraDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBAsignacionCarteraService;


/**
 * Service que controla la Asignacin de Cartera
 *  
 * <p>
 * <a href="ProcesoCOBAsignacionCarteraServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCOBAsignacionCarteraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBAsignacionCarteraServiceImpl extends BaseService implements ProcesoCOBAsignacionCarteraService {

	@Resource(name="sisicc.mailUtil")
	private BaseMailService mailUtil;
	
	@Resource(name="spusicc.procesoCOBAsignacionCarteraDAO")
	private ProcesoCOBAsignacionCarteraDAO procesoCOBAsignacionCarteraDAO;

	
  

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBAsignacionCarteraService#executeAsignacionCartera(java.util.Map)
	 */
	public void executeAsignacionCartera (Map criteria) {
		this.procesoCOBAsignacionCarteraDAO.executeAsignacionCartera(criteria);
	}

	/**
     * @param criteria
     * Envio de correos de confirmacion
     */
    public void executeEnvioMail(Map criteria){
            	    	
    	Map params = new HashMap();
		params = (Map)(procesoCOBAsignacionCarteraDAO.getEnvioMailsAsignacionCarteraParams(criteria).get(0));
    	//------Parametros Generales------		
		String correoOrigen     = params.get("mailOrigen").toString();		
		String plantilla        = params.get("plantilla").toString();
		String subject          = params.get("subject").toString();
		
		List carterasAsignadas = procesoCOBAsignacionCarteraDAO.getCarterasAsignadasProceso();
		
		Iterator iter = carterasAsignadas.iterator();
		
		while (iter.hasNext()) {
			
			Map paramMail = (Map)iter.next();
			
			String correoDestino = (String)paramMail.get("emailCobrador");
			//String correoDestino    = paramMail.get("emailCobrador").toString();
			
			String subjectFinal = subject + " "  + paramMail.get("descripcionEtapaDeuda").toString()
			                              + " "  + paramMail.get("codigoPeriodo").toString()
										  + " "  + paramMail.get("descripcionRegion").toString()
										  + " "  + paramMail.get("descripcionZona").toString();
			
			log.debug(correoOrigen);
			log.debug(correoDestino);
			log.debug(plantilla);
			log.debug(subjectFinal);
											
			mailUtil.enviarMail(correoOrigen,     //correoOrigen
							correoDestino,    //correo Destino 
							subjectFinal,          //Subject  
							plantilla,        //plantilla 
							paramMail);		
		}
    
		
				
    }

}

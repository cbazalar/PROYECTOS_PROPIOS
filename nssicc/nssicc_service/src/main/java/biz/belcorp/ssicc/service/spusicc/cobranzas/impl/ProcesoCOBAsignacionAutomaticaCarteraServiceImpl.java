package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBAsignacionAutomaticaCarteraDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBAsignacionAutomaticaCarteraService;


/**
 * Service que controla la Asignacin Automatica de Cartera
 *  
 * <p>
 * <a href="ProcesoCOBAsignacionAutomaticaCarteraServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCOBAsignacionAutomaticaCarteraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBAsignacionAutomaticaCarteraServiceImpl extends BaseService implements ProcesoCOBAsignacionAutomaticaCarteraService {

	@Resource(name="sisicc.mailUtil")
	private BaseMailService mailUtil;
	
	@Resource(name="spusicc.procesoCOBAsignacionAutomaticaCarteraDAO")
	private ProcesoCOBAsignacionAutomaticaCarteraDAO procesoCOBAsignacionAutomaticaCarteraDAO;
	
		
   	
	public void executeAsignacionAutomaticaCartera (Map criteria) {
		this.procesoCOBAsignacionAutomaticaCarteraDAO.executeAsignacionAutomaticaCartera(criteria);
									
		String codigoError = criteria.get("codigoError").toString();				
		Usuario usuario = (Usuario)criteria.get("usuario");
				
		String mensajeError;
							
		mensajeError = this.messageSource.getMessage(codigoError,null,getLocale(usuario)); 
		log.debug(mensajeError);
		
		criteria.put("mensajeError", mensajeError);
								
	}

	/**
     * @param criteria
     * Envio de correos de confirmacion
     */
    public void executeEnvioMail(Map criteria){
            	    	
    	Map params = new HashMap();
		params = (Map)(procesoCOBAsignacionAutomaticaCarteraDAO.getEnvioMailsAsignacionAutomaticaCarteraParams(criteria).get(0));
    	//------Parametros Generales------		
		String correoOrigen     = params.get("mailOrigen").toString();		
		String plantilla        = params.get("plantilla").toString();
		String subject          = params.get("subject").toString();
		
		List carterasAsignadas = procesoCOBAsignacionAutomaticaCarteraDAO.getCarterasAsignadasProceso();
		
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

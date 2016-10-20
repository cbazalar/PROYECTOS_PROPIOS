package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBActualizarCarteraDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBActualizarCarteraService;


/**
 * Service que controla la Actualizar de Cartera
 *  
 * <p>
 * <a href="ProcesoCOBActualizarCarteraServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCOBActualizarCarteraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBActualizarCarteraServiceImpl extends BaseService implements ProcesoCOBActualizarCarteraService {

	@Resource(name="sisicc.mailUtil")
	private BaseMailService mailUtil;
			
	@Resource(name="spusicc.procesoCOBActualizarCarteraDAO")
	private ProcesoCOBActualizarCarteraDAO procesoCOBActualizarCarteraDAO;
	

	
	
    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeActualizarCartera(java.util.Map)
	 */
	public void executeActualizarCartera (Map criteria) {
		this.procesoCOBActualizarCarteraDAO.executeActualizarCartera(criteria);
	}

	/**
     * @param criteria
     * Envio de correos de confirmacion
     */
    public void executeEnvioMail(Map criteria){
    	    	    	
    	Map params = new HashMap();
		params = (Map)(procesoCOBActualizarCarteraDAO.getEnvioMailsActualizarCarteraParams(criteria).get(0));
    	//------Parametros Generales------		
		String correoOrigen     = params.get("mailOrigen").toString();
		String correoDestino    = params.get("mailDestino").toString();
		String plantilla        = params.get("plantilla").toString();
		String subject          = params.get("subject").toString();
		
		//log.debug(correoOrigen);
		//log.debug(correoDestino);
		//log.debug(plantilla);
		//log.debug(subject);
						
		Map paramMail = new HashMap();
		
		mailUtil.enviarMail(correoOrigen,     //correoOrigen
							correoDestino,    //correo Destino 
							subject,          //Subject  
							plantilla,        //plantilla 
							paramMail);		
				
    }
}

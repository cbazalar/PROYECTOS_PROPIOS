package biz.belcorp.ssicc.service.util.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.BaseMailAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

/**
 * Metodos utilitarios para el envio de mails.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */

@Service("sisicc.mailUtil")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailUtil extends BaseMailAbstractService implements BaseMailService {
	protected final Log log = LogFactory.getLog(MailUtil.class);

	/**
	 * Metodo que permitira enviar correos de acuerdo a los parametros ingresados.
	 * 
	 * @param from
	 * @param destinos
	 * @param subject
	 * @param mensaje
	 * @return
	 */
	public MailResult enviarMail(String from, String destinos, String subject, String mensaje) {
		MailParams mailParams = new MailParams();
	    Map queryParams = new HashMap();
	    queryParams.put("correoOrigen", from);
	    queryParams.put("correosDestinos", destinos);
	    queryParams.put("subject", subject);
	    queryParams.put("body", mensaje);
	    
	    Map params = new HashMap();
	    
	    queryParams.put("parameterMap", params);
	    
	    mailParams.setQueryParams(queryParams);
		
		return enviarMail(mailParams);
	}

	/**
	 * Metodo que permitira enviar correos, de acuerdo a la plantilla y parametros seleccionados
	 * 
	 * @param from
	 * @param destinos
	 * @param subject
	 * @param plantillaBody
	 * @param parametros
	 * @return
	 */
	public MailResult enviarMail(String from, String destinos, String subject, String plantillaBody, Map parametros) {
		MailParams mailParams = new MailParams();
		Map queryParams = new HashMap();
		
		queryParams.put("correoOrigen", from);
		queryParams.put("correosDestinos", destinos);
		queryParams.put("subject", subject);

	    Map params = new HashMap();
	    //agregamos los parametros adicionales
	    Iterator it = parametros.keySet().iterator();
	    while(it.hasNext()) {
	    	String clave = (String)it.next();
	    	params.put(clave, parametros.get(clave));
	    }
	    params.put("bodyTxt", plantillaBody);
	    params.put("bodyHtml", plantillaBody);
	    queryParams.put("parameterMap", params);
	    
	    deleteConflictosSMTP();
	    mailParams.setQueryParams(queryParams);
	    
		return enviarMail(mailParams);
	}
	
	
	/**
	 * Metodo que permitira enviar correos, de acuerdo a la plantilla y parametros seleccionados
	 * 
	 * @param from
	 * @param destinos
	 * @param subject
	 * @param plantillaBody
	 * @param parametros
	 * @return
	 */
	public MailResult enviarMailAdjunto(String from, String destinos, String subject, String plantillaTxt, 
			String plantillaHtml, Map parametros) {
		MailParams mailParams = new MailParams();
		Map queryParams = new HashMap();
		
		queryParams.put("correoOrigen", from);
		queryParams.put("correosDestinos", destinos);
		queryParams.put("subject", subject);

	    Map params = new HashMap();
	    //agregamos los parametros adicionales
	    Iterator it = parametros.keySet().iterator();
	    while(it.hasNext()) {
	    	String clave = (String)it.next();
	    	params.put(clave, parametros.get(clave));
	    }
	    
	    params.put("bodyTxt", plantillaTxt);
	    params.put("bodyHtml", plantillaHtml);
	    
	    queryParams.put("parameterMap", params);
	    
	    deleteConflictosSMTP();
	    mailParams.setQueryParams(queryParams);
    
		return enviarMail(mailParams);
	}
	
	
	protected String getEnviarCC(MailParams mailParams) throws Exception {
		Map params = mailParams.getQueryParams();
		String correoCC = (String) params.get("correoCC");
		return correoCC;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getFrom()
	 */
	protected String getFrom(MailParams mailParams) throws Exception {
		Map params = mailParams.getQueryParams();
		String correoOrigen = (String) params.get("correoOrigen");
		return correoOrigen;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getSubject()
	 */
	protected String getSubject(MailParams mailParams) throws Exception {
		Map params = mailParams.getQueryParams();
		String subject = (String) params.get("subject");
		return subject;
	}

	protected String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		Map params = mailParams.getQueryParams();
		String subject = (String) params.get("correosDestinos");
		return subject;
	}
	
	protected String getBodyTxt(MailParams mailParams) throws Exception {
		//Map params = mailParams.getQueryParams();
		 Map model = (Map) mailParams.getQueryParams().get("parameterMap");
		String bodyTxt = (String) model.get("bodyTxt");
		return bodyTxt;
	}	

	protected String getBodyHtml(MailParams mailParams) throws Exception {
		//Map params = mailParams.getQueryParams();
		 Map model = (Map) mailParams.getQueryParams().get("parameterMap");
		String bodyHtml = (String) model.get("bodyHtml");
		return bodyHtml; 
	}

	private void deleteConflictosSMTP() {
		JavaMailSenderImpl impl = (JavaMailSenderImpl)getMailSender();
		log.warn("------------------------------------------------------------------");
		log.warn("PROVEEDORES JAVAMAIL : " + impl.getSession().getProviders());
		
		for(int i=0; i<impl.getSession().getProviders().length; i++) {
			log.warn("PROVEEDOR: " + impl.getSession().getProviders()[i].getProtocol()  
								+ ", Version: " + impl.getSession().getProviders()[i].getVersion()
								+ ", Clase: " + impl.getSession().getProviders()[i].getClassName()
								+ ", Protocolo: " + impl.getSession().getProviders()[i].getProtocol()
								+ ", Vendedor: " + impl.getSession().getProviders()[i].getVendor());  
		}	
		log.warn("------------------------------------------------------------------");
		
	}	

	/**
	 * Metodo que permitira enviar correos, de acuerdo a la plantilla y parametros seleccionados
	 * Envia correos CC
	 * 
	 * @param from
	 * @param destinos
	 * @param copias
	 * @param subject
	 * @param plantillaBody
	 * @param parametros
	 * @return
	 */
	public MailResult enviarMail(String from, String destinos, String copias, String subject, String plantillaBody, Map parametros) {
		MailParams mailParams = new MailParams();
		Map queryParams = new HashMap();
		
		queryParams.put("correoOrigen", from);
		queryParams.put("correosDestinos", destinos);
		queryParams.put("correoCC", copias);
		queryParams.put("subject", subject);

	    Map params = new HashMap();
	    //agregamos los parametros adicionales
	    Iterator it = parametros.keySet().iterator();
	    while(it.hasNext()) {
	    	String clave = (String)it.next();
	    	params.put(clave, parametros.get(clave));
	    }
	    params.put("bodyTxt", plantillaBody);
	    params.put("bodyHtml", plantillaBody);
	    queryParams.put("parameterMap", params);
	    
	    deleteConflictosSMTP();
	    mailParams.setQueryParams(queryParams);
	   // this.plantillaBodyTxt = plantillaBody;
	    
		return enviarMail(mailParams);
	}
	
	
}

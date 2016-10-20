package biz.belcorp.ssicc.service.sisicc.framework;

import java.io.File;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

/**
 * Clase Service abstracta para el envio de Correo. Implementa el template
 * method 'enviarMail' que contiene el algoritmo generico para el
 * envio de correo. Provee diversos metodos para extender este comportamiento 
 * generico en las subclases.
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */
@Service("sisicc.baseMailAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseMailAbstractService extends BaseService implements BaseMailService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private JavaMailSender mailSender;
	
	private VelocityEngine velocityEngine;


	/**
	 * Template method que define el algoritmo bsico para el envio de Correo
	 * 
	 * @param MailResult
	 *            con el resultado de la ejecucin del envio de correo
	 * @return resultado de la ejecucin del envio del correo
	 */
	synchronized public final MailResult enviarMail(MailParams mailParams) {
		
		log.debug("Entering 'enviarMail' method");
		MailResult mailResult = new MailResult();
		Map paramsMap = mailParams.getQueryParams();
		String correosDestinos = new String(); 
		String correoCC = new String();
		String correos = "";
		
		try {
			log.info("Iniciando procesamiento de Envio de Correo");
			paramsMap = this.prepareParameterMap(paramsMap);
			paramsMap.put("pais", mailParams.getPais());
			paramsMap.put("usuario", mailParams.getUsuario());
			mailParams.setQueryParams(paramsMap);
			
			 // Creamos el mensaje y configuramos los principales atributos
            MimeMessage mime = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
            
            // Obteniendo correo origen
            String correoOrigen = this.getFrom(mailParams); 
            log.debug("correoOrigen " +correoOrigen);
            if (StringUtils.isBlank(correoOrigen)) {
            	mailResult = this.generaErrorResult("email.error.fromEmail", mailParams.getUsuario());
            	return mailResult;
            }            
            helper.setFrom(correoOrigen);
            
            // Obteniendo los correos destinos
            correosDestinos = this.getEnviarCorreoElectronico(mailParams);
            log.debug("correosDestinos " +correosDestinos);
            String[] listaCorreoTo = StringUtils.split(correosDestinos, ',');
            for(int i= 0; i < listaCorreoTo.length; i++) {
            	listaCorreoTo[i] = listaCorreoTo[i].trim();
            }
            if (listaCorreoTo == null || listaCorreoTo.length <= 0) {
            	mailResult = this.generaErrorResult("email.error.toEmail",  mailParams.getUsuario());
            	return mailResult;
            }            
            helper.setTo(listaCorreoTo);
            
            // Obteniendo los correos CC
            correoCC = this.getEnviarCC(mailParams);
            String[] listaCorreoCC = StringUtils.split(correoCC, ',');
            if (listaCorreoCC != null && listaCorreoCC.length > 0) {
            	for(int i= 0; i < listaCorreoCC.length; i++) {
                	listaCorreoCC[i] = listaCorreoCC[i].trim();
                }
            	helper.setCc(listaCorreoCC);
            }
            
            //
            correos = StringUtils.isNotBlank(correoCC) ? String.format("%s,%s", correosDestinos, correoCC) : correosDestinos; 
            //
            
            // Obteniendo subject
            String tituloCorreo = this.getSubject(mailParams);
            if (StringUtils.isBlank(tituloCorreo)) {
            	mailResult = this.generaErrorResult("email.error.subjectEmail",  mailParams.getUsuario());
            	return mailResult;
            }
            helper.setSubject(tituloCorreo);
            log.debug("titulo correo "+ tituloCorreo);
            //Obteniendo Body
            String plainText = "";
            String html = "";
            String bodyTxt  = this.getBodyTxt(mailParams);
            String bodyHtml = this.getBodyHtml(mailParams);
            Map model = (Map) mailParams.getQueryParams().get("parameterMap");
            model.put("pais", mailParams.getPais());
            model.put("usuario", mailParams.getUsuario());
			
            if (StringUtils.isNotBlank(bodyTxt)) {
                plainText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                    		bodyTxt, model);
            }
            if (StringUtils.isNotBlank(bodyHtml)) {
            	html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                		bodyHtml, model);
            }
            if (StringUtils.isNotBlank(bodyTxt) && StringUtils.isNotBlank(bodyHtml))
            	helper.setText(plainText, html);
            else {
            	if (StringUtils.isNotBlank(bodyTxt) && StringUtils.isBlank(bodyHtml)) {
            		helper.setText(plainText, null);
            	}
            	if (StringUtils.isBlank(bodyTxt) && StringUtils.isNotBlank(bodyHtml)) {
            		helper.setText(null, html);
            	}
            }
            
            // Atachando archivo
            if (this.getAttachment(mailParams)) {
            	log.debug("atachando archivo");
            	FileSystemResource fsr = this.getAttachmentFile(mailParams);
            	if (fsr == null) {
            		mailResult = this.generaErrorResult("email.error.fileAttachment",  mailParams.getUsuario(), correos);
                	return mailResult;
            	}
            	String nombreArchivoAtachado = this.getNombreFileAttachment(mailParams);
            	if (StringUtils.isBlank(tituloCorreo)) { 
            		mailResult = this.generaErrorResult("email.error.nombreFileAttachment",  mailParams.getUsuario(), correos);
                	return mailResult;	
            	}
            	helper.addAttachment(nombreArchivoAtachado, fsr);
            }	
            
            //Enviando correo
            log.debug("[ENVIANDO CORREO] mailSender ");
            mailSender.send(mime);
			
		} catch (Exception e) {
			log.error("Ocurrio una excepcion no controlada.");
			log.error(e);
			mailResult = this.generaErrorResult(e, correos);
        	return mailResult;
			
		} finally {
		}
		log.info("Se termino la ejecucion del envio de correo");
		mailResult = this.generaOKResult(correos);
		return mailResult;
	}
	
	
	/**
	 * Metodo para agregar nuevos parametros al Map
	 * @param params
	 * @return
	 */
	protected Map prepareParameterMap(Map params) {
		return params;
	}
	
	/**
	 * Metodo que devuelve nombre de Plantilla Velocity en formato TXT para el cuerpo del correo
	 * a enviar 
	 * @param mailParams
	 * @return
	 * @throws Exception TODO
	 */
	protected String getBodyTxt(MailParams mailParams) throws Exception {
		return null;
	}
	
	/**
	 * Metodo que devuelve nombre de Plantilla Velocity en formato HTML para el cuerpo del correo
	 * a enviar
	 * @param mailParams
	 * @return
	 * @throws Exception TODO
	 */
	protected String getBodyHtml(MailParams mailParams) throws Exception {
		return null;
	}
	
	
	/**
	 * Mtodo Hook, en la cual se debe sobreeescribir para ingresar el Correo Origen
	 * @param mailParams TODO
	 * @return
	 * @throws Exception TODO
	 */
	protected abstract String getFrom(MailParams mailParams) throws Exception;

	
	/**
	 *  Mtodo Hook, en la cual se debe sobreeescribir para ingresar la lista de correos cc (con copia)
	 *  Dicha cadena puede contener una o varias direcciones electronicas. 
	 *  En caso sean varias direcciones electronicas, deberan separarse por comas
	 * @param mailParams TODO
	 *  @return
	 * @throws Exception TODO
	 */
	protected abstract String getEnviarCC(MailParams mailParams) throws Exception;
	
	
	/**
	 *  Mtodo Hook, en la cual se debe sobreeescribir para ingresar la lista de correos destino
	 *  Dicha cadena puede contener una o varias direcciones electronicas. 
	 *  En caso sean varias direcciones electronicas, deberan separarse por comas
	 * @param mailParams TODO
	 *  @return
	 * @throws Exception TODO
	 */
	protected abstract String getEnviarCorreoElectronico(MailParams mailParams) throws Exception;
	
	
	/**
	 * Metodo Hook, en el cual se debe sobreescribir para ingresar el titulo del correo a enviar  
	 * @param mailParams TODO
	 * @return
	 * @throws Exception TODO
	 */
	protected abstract String getSubject(MailParams mailParams) throws Exception;
	
	

	
	/**
	 * 
	 * @param keyMensaje
	 * @return MailResult
	 */
	private MailResult generaErrorResult(String keyMensaje, Usuario usuario) {
		MailResult mailResult = new MailResult();
		mailResult.setCompletado(false);		
		String mensajeError = this.messageSource.getMessage(keyMensaje,null, getLocale(usuario));		
		mailResult.setMensajeError(mensajeError);
		return mailResult;
	}
	
	/**
	 * @param keyMensaje
	 * @param usuario
	 * @param correoDestino
	 * @return
	 */
	private MailResult generaErrorResult(String keyMensaje, Usuario usuario, String correoDestino) {
		MailResult mailResult = this.generaErrorResult(keyMensaje, usuario);
		mailResult.setCorreoDestino(correoDestino);
		return mailResult;
	}
	
	/**
	 * 
	 * @param e
	 * @return MailResult
	 * 
	 */
	private MailResult generaErrorResult(Exception e) {
		MailResult mailResult = new MailResult();
		mailResult.setCompletado(false);
		mailResult.setMensajeError(this.obtieneMensajeErrorException(e));
		return mailResult;
	}
	
	/**
	 * @param e
	 * @param correoDestino
	 * @return
	 */
	private MailResult generaErrorResult(Exception e, String correoDestino) {
		MailResult mailResult = this.generaErrorResult(e);
		mailResult.setCorreoDestino(correoDestino);
		return mailResult;
	}
	
	/**
	 * @return
	 */
	private MailResult generaOKResult() {
		MailResult mailResult = new MailResult();
		mailResult.setCompletado(true);
		mailResult.setMensajeError(null);
		return mailResult;
	}
	
	
	/**
	 * @param correoDestino
	 * @return
	 */
	private MailResult generaOKResult(String correoDestino) {
		MailResult mailResult = this.generaOKResult();
		mailResult.setCorreoDestino(correoDestino);
		return mailResult;
	}
	
	/**
	 * Mtodo que devuelve Nombre del Archivo a Atachar en el correo. Dicho metodo puede ser
	 * sobreescrito
	 * @param mailParams
	 * @return
	 */
	protected String getNombreFileAttachment(MailParams mailParams) {
		Map params = mailParams.getQueryParams();
		String retorno = (String) params.get("nombreArchivoReporte");
		return retorno;
	}
	
	/**
	 * Metodo que determina si se atachara algun archivo en el envio de Correo.
	 * Setear a true en caso se dese atachar archivo
	 * @param mailParams
	 * @return
	 */
	protected boolean getAttachment(MailParams mailParams) {
		Map params = mailParams.getQueryParams();
		if(params.get("fileAttachment") == null){
		return false;
	}
	
		return true;
	}
	
	/**
	 * Metodo que obtiene archivo a atachar. Dicho metodo debe ser sobreeescrito en caso se 
	 * desee atachar algun archivo en el envio del correo
	 * @param mailParams
	 * @return
	 */
	protected FileSystemResource getAttachmentFile(MailParams mailParams) {
		Map params = mailParams.getQueryParams();
		File archivoAtachado = (File) params.get("fileAttachment");
		FileSystemResource fsr = new FileSystemResource(archivoAtachado);
		return fsr;
	}

	/**
	 * @param mailSender The mailSender to set.
	 */	
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	
	/**
	 * @param velocityEngine The velocityEngine to set.
	 */	
	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	/**
	 * @return the mailSender
	 */
	public JavaMailSender getMailSender() {
		return mailSender;
	}


	/**
	 * @return the velocityEngine
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}
}
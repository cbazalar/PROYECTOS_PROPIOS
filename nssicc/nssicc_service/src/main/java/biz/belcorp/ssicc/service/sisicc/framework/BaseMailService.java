package biz.belcorp.ssicc.service.sisicc.framework;

import java.util.Map;

import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;

/**
 * Interface que deben implementar el envio de correo
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */
public interface BaseMailService {

	/**
	 * Ejecuta el envio de Mail dado los parametros.
	 * 
	 * @param mailParams
	 *            parametros del envio del correo
	 * @return resultado del envio de correo
	 * @throws InterfazException
	 */
	public MailResult enviarMail(MailParams mailParams);
	
	
	/* INI NSSICC */
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
	public MailResult enviarMail(String from, String destinos, String subject, String plantillaBody, Map parametros);
	
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
			String plantillaHtml, Map parametros) ;
	
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
	public MailResult enviarMail(String from, String destinos, String copias, String subject, String plantillaBody, Map parametros);
	
	/**
	 * Metodo que permitira enviar correos de acuerdo a los parametros ingresados.
	 * 
	 * @param from
	 * @param destinos
	 * @param subject
	 * @param mensaje
	 * @return
	 */
	public MailResult enviarMail(String from, String destinos, String subject, String mensaje);
	
	/* FIN NSSICC */
}
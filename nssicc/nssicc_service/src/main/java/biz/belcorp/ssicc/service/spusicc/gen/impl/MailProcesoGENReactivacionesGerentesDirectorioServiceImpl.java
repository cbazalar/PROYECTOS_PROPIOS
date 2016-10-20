package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.BaseMailAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;


/**
 * Clase Service para el envio de Correo luego de generado el archivo de interfaz
 * envio de cargos 
 * 
 */
@Service("sisicc.mailProcesoGENReactivacionesGerentesDirectorioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailProcesoGENReactivacionesGerentesDirectorioServiceImpl extends BaseMailAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	public String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		String resultado = mailParams.getQueryParams().get("correosDestinos").toString(); 
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getFrom(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	public String getFrom(MailParams mailParams) throws Exception {
		String resultado = mailParams.getQueryParams().get("correoOrigen").toString(); 
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getSubject(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	public String getSubject(MailParams mailParams) throws Exception {
		String resultado = mailParams.getQueryParams().get("subject").toString(); 
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getBodyHtml(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	public String getBodyHtml(MailParams mailParams) throws Exception {		 
		return "MailHtmlProcesoGENReactivacionesGerentesDirectorio.vm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getBodyTxt(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	public String getBodyTxt(MailParams mailParams) throws Exception {
		return "MailHtmlProcesoGENReactivacionesGerentesDirectorio.vm";
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCC(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCC(MailParams mailParams) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MailResult enviarMail(String from, String destinos, String subject,
			String plantillaBody, Map parametros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MailResult enviarMailAdjunto(String from, String destinos,
			String subject, String plantillaTxt, String plantillaHtml,
			Map parametros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MailResult enviarMail(String from, String destinos, String copias,
			String subject, String plantillaBody, Map parametros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MailResult enviarMail(String from, String destinos, String subject,
			String mensaje) {
		// TODO Auto-generated method stub
		return null;
	}
}
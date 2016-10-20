package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;


/**
 * Clase Service para el envio de Correo luego de generado el Reporte de Generacion de
 * Mensajes. 
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("msg.mailReporteCOBRecuperacionCarteraFFVVService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteCOBRecuperacionCarteraFFVVServiceImpl extends BaseMailReporteAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams)
			throws Exception {
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		String correoGerenteZona = (String) parameterMap.get("correoGerenteZona");		
		String correoGerenteRegion = (String) parameterMap.get("correoGerenteRegion");

		String flagGerenteZona = (String) parameterMap.get("flagGerenteZona");
		String correoElectronico = "";
		
		if(org.apache.commons.lang.StringUtils.equals(flagGerenteZona, Constants.SI))
		{
			correoElectronico = correoGerenteZona;
		}
		else
		{
			correoElectronico = correoGerenteRegion;
		}
		
		log.debug("correo electronico " + correoElectronico);
		
		return StringUtils.isNotEmpty(correoElectronico)?correoElectronico:(String) parameterMap.get("correoDefault");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getBodyHtml(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getBodyHtml(MailParams mailParams) throws Exception {
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		String flagGerenteZona = (String) parameterMap.get("flagGerenteZona");
		String plantillaHTML = "";
		
		if(StringUtils.equals(flagGerenteZona, Constants.SI))
		{
			plantillaHTML = "MailHtmlReporteCOBRecuperacionCarteraFFVVGZ.vm";
		}
		else
		{
			plantillaHTML = "MailHtmlReporteCOBRecuperacionCarteraFFVVGR.vm";
		}
		
		return plantillaHTML;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getBodyTxt(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getBodyTxt(MailParams mailParams) throws Exception {
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		String flagGerenteZona = (String) parameterMap.get("flagGerenteZona");
		String plantillaTXT = "";
		
		if(StringUtils.equals(flagGerenteZona, Constants.SI))
		{
			plantillaTXT = "MailHtmlReporteCOBRecuperacionCarteraFFVVGZ.vm";
		}
		else
		{
			plantillaTXT = "MailHtmlReporteCOBRecuperacionCarteraFFVVGR.vm";
		}
		
		return plantillaTXT;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getSubject(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getSubject(MailParams mailParams) throws Exception {
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		String flagGerenteZona = (String) parameterMap.get("flagGerenteZona");

		String codigoPeriodo = (String) parameterMap.get("codigoPeriodo");
		String codigoRegion = (String) parameterMap.get("codigoRegion");
		String codigoZona = (String) parameterMap.get("codigoZona");

		String subject = "";
		
		if(StringUtils.equals(flagGerenteZona, Constants.SI))
		{
			subject = String.format("Recuperacin de Cobranza Campaa %s Region %s Zona %s", codigoPeriodo, codigoRegion, codigoZona);
		}
		else
		{
			subject = String.format("Recuperacin de Cobranza Campaa %s Region %s", codigoPeriodo, codigoRegion);
		}
		
		return subject;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getAttachment(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected boolean getAttachment(MailParams mailParams) {
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		String flagGerenteZona = (String) parameterMap.get("flagGerenteZona");
		boolean attach = false;
		
		if(StringUtils.equals(flagGerenteZona, Constants.SI))
		{
			attach = true;
		}
		else
		{
			attach = false;
		}
		
		return attach;
		
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
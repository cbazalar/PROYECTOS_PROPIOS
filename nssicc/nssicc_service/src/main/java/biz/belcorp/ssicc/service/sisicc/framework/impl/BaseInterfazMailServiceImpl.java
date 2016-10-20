package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

/* POR REESTRUCTURAR NSSICC */
/**
 * Clase Service abstracta para el envio de Correo luego de ejecutado la Interfaz. 
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */

@Service("sisicc.baseInterfazMailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class BaseInterfazMailServiceImpl extends BaseMailAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#prepareParameterMap(java.util.Map)
	 */
	public Map prepareParameterMap(Map params) {
		Map parameterMap = (Map)params.get("parameterMap");
		Interfaz interfaz = (Interfaz)parameterMap.get("interfaz");
		
		String correoOrigen  = (String) interfaz.getCorreoOrigen();
		String correoDestinos = (String) interfaz.getCorreoDestinos();		
		String correoCC = (String) interfaz.getCorreoCC();
		String subject  = (String) interfaz.getSubject();
		
		if(StringUtils.isEmpty(subject)) {
			subject = "Interfaz " + interfaz.getCodigo() + " " + interfaz.getDescripcion();
		}
		
		params.put("correoOrigen", correoOrigen);
		params.put("correoDefault", correoDestinos);		
		params.put("correoCC", correoCC);
		params.put("subject", subject);		
	
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		Map params = mailParams.getQueryParams();
		String correoCC = (String) params.get("correoDefault");
		return correoCC;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCC(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getAttachment(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected boolean getAttachment(MailParams mailParams) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getBodyHtml(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getBodyHtml(MailParams mailParams) throws Exception {
		Map params = (Map) mailParams.getQueryParams().get("parameterMap");
		Interfaz interfaz = (Interfaz)params.get("interfaz");
		
		if(Constants.TIPO_GENERACION_UNITARIA.equals(interfaz.getTipoGeneracion()))	
			return "MailHtmlEjecucionInterfazUnitaria.vm";
		else
			return "MailHtmlEjecucionInterfazPaquete.vm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getBodyTxt(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getBodyTxt(MailParams mailParams) throws Exception {
		Map params = (Map) mailParams.getQueryParams().get("parameterMap");
		Interfaz interfaz = (Interfaz)params.get("interfaz");
		
		if(Constants.TIPO_GENERACION_UNITARIA.equals(interfaz.getTipoGeneracion()))	
			return "MailTxtEjecucionInterfazUnitaria.vm";
		else
			return "MailTxtEjecucionInterfazPaquete.vm";
		
	}

	/* INI NSSICC */
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
	/* FIN NSSICC */

	@Override
	public MailResult enviarMail(String from, String destinos, String subject,
			String mensaje) {
		// TODO Auto-generated method stub
		return null;
	}

}

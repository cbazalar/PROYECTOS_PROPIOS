/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.BaseMailAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MailProcesoZONDirectorioVentasImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jvelasquez@belcorp.biz">Jorge Velasquez</a>
 * 
 */

@Service("sic.mailReporteGenerarDirectorioVenta")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailProcesoZONDirectorioVentasImpl extends BaseMailAbstractService{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getFrom(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getFrom(MailParams mailParams) throws Exception {
		log.info("Entro a getFrom - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)");
		String resultado = MapUtils.getString(mailParams.getQueryParams(), "correoOrigen",""); 
		log.info("Salio a getFrom - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCC(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCC(MailParams mailParams) throws Exception {
		log.info("Entro a getSubject - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)");
		String resultado = MapUtils.getString(mailParams.getQueryParams(), "listaCorreosDestinos",""); 
		log.info("Salio a getSubject - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams)
			throws Exception {
		log.info("Entro a MailProcesoPRIGenerarSolicitudesPrivilegeImpl - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)");
		String resultado = MapUtils.getString(mailParams.getQueryParams(), "correosDestino","");  
		log.info("Salio a MailProcesoPRIGenerarSolicitudesPrivilegeImpl - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getSubject(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getSubject(MailParams mailParams) throws Exception {
		log.info("Entro a getSubject - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)");
		String resultado = MapUtils.getString(mailParams.getQueryParams(), "subject",""); 
		log.info("Salio a getSubject - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getBodyTxt(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getBodyTxt(MailParams mailParams) throws Exception {
		log.info("Entro a getSubject - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)");
		String resultado = MapUtils.getString(mailParams.getQueryParams(), "bodyTxt",""); 
		log.info("Salio a getSubject - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getBodyHtml(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getBodyHtml(MailParams mailParams) throws Exception {
		log.info("Entro a getSubject - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)");
		String resultado = MapUtils.getString(mailParams.getQueryParams(), "bodyHtml",""); 
		log.info("Salio a getSubject - getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams) - Resultado:"+resultado);
		return resultado;
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

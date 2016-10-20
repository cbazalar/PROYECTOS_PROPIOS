/**
 * 
 */
package biz.belcorp.ssicc.service.scsicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;


/**
 * @author peextrramirez - Rosalvina Ramirez Guardia
 *
 */
@Service("scsicc.mailReporteGenerarConsolidadoPremioDespachadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteGenerarConsolidadoPremioDespachadoServiceImpl extends BaseMailReporteAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getEnviarCC(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCC(MailParams mailParams) throws Exception { 
	
		return super.getEnviarCC(mailParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams)
			throws Exception {
		log.debug("Entering 'getEnviarCorreoElectronico' method");
		String correoElectronico = new String();
		Map params = (Map) mailParams.getQueryParams();
		correoElectronico = (String) params.get("correoDefault");
		return correoElectronico;
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

	@Override
	public MailResult enviarMail(String from, String destinos, String subject,
			String mensaje) {
		// TODO Auto-generated method stub
		return null;
	}

	/* FIN NSSICC */
	
	

}

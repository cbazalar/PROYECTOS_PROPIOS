package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

/**
 * Clase Service para el envio de Correo luego de generado el Reporte de Generación de
 * Consolidado. 
 * 
 * @author <a href="mailto:diegotl66991@sigcomt.com">Diego Torres Loyola</a>
 */
@Service("msg.mailReporteGenerarEnviarReporteConsolidadoInterMoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteGenerarEnviarReporteConsolidadoInterMoraServiceImpl extends BaseMailReporteAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	@Override
	protected String getEnviarCorreoElectronico(MailParams mailParams)
			throws Exception {
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		String correoElectronico = (String) parameterMap.get("correoDefault");
		log.debug("correo electronico "+correoElectronico);
		return correoElectronico;
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
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

/**
 * Clase Service para el envio de Correo luego de Generar
 *   Reporte de Porcentaje de Desviacin de Pedidos
 * 
 * @author <a href="#">Yahir Rivas Luna</a>
 */
@Service("ccc.mailReporteCCCAuditoriaSaldoCuentasPorCobrarService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteCCCAuditoriaSaldoCuentasPorCobrarServiceImpl extends BaseMailReporteAbstractService {


	protected String getEnviarCorreoElectronico(MailParams mailParams)
		throws Exception {
	log.debug("Entering 'getEnviarCorreoElectronico' method");
	String correoElectronico = new String();
	Map params = (Map) mailParams.getQueryParams();
	correoElectronico = (String) params.get("correoDefault");
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
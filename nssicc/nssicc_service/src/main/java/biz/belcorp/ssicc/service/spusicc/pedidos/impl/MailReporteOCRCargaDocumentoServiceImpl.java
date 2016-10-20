package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MailReporteOCRCargaDocumentoService;

/**
 * Clase Service para el envio de Correo luego de generado el 
 * Reporte de Carga de Documentos OCR/WS.
 * 
 * @author <a href="#">Yahir Rivas Luna</a>
 */
@Service("ocr.mailReporteOCRCargaDocumento")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteOCRCargaDocumentoServiceImpl extends BaseMailReporteAbstractService implements MailReporteOCRCargaDocumentoService {



	protected String getEnviarCorreoElectronico(MailParams mailParams)
			throws Exception {
		Map parameterMap = (Map) mailParams.getQueryParams();	

		return MapUtils.getString(parameterMap, "correoCC", "");
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getSubject(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getSubject(MailParams mailParams) throws Exception {
		String titulo = super.getSubject(mailParams);		
		Pais pais = mailParams.getPais();
		  titulo = titulo +" - "+ pais.getDescripcion();
		
		return titulo;
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService#getEnviarCC(biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams)
	 */
	@Override
	protected String getEnviarCC(MailParams mailParams) throws Exception {
		return "";
	}

}
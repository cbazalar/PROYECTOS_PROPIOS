package biz.belcorp.ssicc.service.spusicc.app.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

/**
 * Clase Service para el envio de Correo de las secuencias de Zonas 
 * y territorios
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
@Service("app.mailReporteAPPSecuenciarZonaTerritorioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteAPPSecuenciarZonaTerritorioServiceImpl extends BaseMailReporteAbstractService {

	protected String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		String correoElectronico = new String();
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");				
		//Evalua el parametro a ver si lo envia 			
		log.debug("getEnviarCC params " + parameterMap);		
		String nivelEnvioCorreoCC = (String) mailParams.getQueryParams().get("nivelEnvioCorreoCC");
		log.debug("-----------nivelEnvioCorreoCC "+nivelEnvioCorreoCC);
		
		correoElectronico = (String) parameterMap.get("email");
		
		Map criteria = new HashMap();
		List listMail = new ArrayList();		
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", mailParams.getPais().getCodigo());
		criteriaParam.put("codigoSistema", "APP");
		criteriaParam.put("nombreParametro", "reporteAPPSecuenciar");
			
		listMail = reporteDAO.getParametroGenericoSistemaMail(criteriaParam);
		
		criteria.put("listaReporte",listMail);
		
		Map paramRepor=mailParams.getQueryParams();
		paramRepor.put("parameterMap", criteria);
		mailParams.setQueryParams(paramRepor);
		
		log.info(listMail);
		
		//Si no se encuentra el correo, se envia a la direccion configurada por defecto
		if (StringUtils.isBlank(correoElectronico)) {
			Map params = (Map) mailParams.getQueryParams();
			correoElectronico = (String) params.get("correoDefault");
		}
		return correoElectronico;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getSubject(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getSubject(MailParams mailParams) throws Exception {
		String titulo = super.getSubject(mailParams);		
		Pais pais = mailParams.getPais();
		titulo = titulo + " " + pais.getDescripcion() + " - ";
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");				
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
	
	
}
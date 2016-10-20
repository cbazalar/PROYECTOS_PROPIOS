package biz.belcorp.ssicc.service.spusicc.sac.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.sac.ProcesoSACGenerarReporteDAO;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

@Service("sac.mailReporteSACAsistenciaCompartamosEsikaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteSACAsistenciaCompartamosEsikaServiceImpl extends BaseMailReporteAbstractService {

	@Resource(name="spusicc.procesoSACGenerarReporteDAO")
	private ProcesoSACGenerarReporteDAO procesoSACGenerarReporteDAO;			
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("getEnviarCorreoElectronico");
		}
		String correoElectronico = new String();
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");				
		//Evalua el parametro a ver si lo envia a la gerente de zona				
		log.debug("getEnviarCC params " + parameterMap);
		//String nivelEnvioCorreoCC = (String) parameterMap.get("nivelEnvioCorreoCC");
		String nivelEnvioCorreoCC = (String) mailParams.getQueryParams().get("nivelEnvioCorreoCC");
		log.debug("-----------nivelEnvioCorreoCC "+nivelEnvioCorreoCC);
		if (!StringUtils.equals(nivelEnvioCorreoCC, Constants.NUMERO_CERO)) {
			// Obtiene la direccion de correo de la gerente de la zona que ingresa como parametro
			List resultado = procesoSACGenerarReporteDAO.getListaCorreoReporteSACAsistenciaCompartamosEsika(parameterMap);
			if (resultado != null && resultado.size()> 0) {			
				correoElectronico = (String)resultado.get(0);
			}
		}
		//Si no se encuentra el correo de la gerente de zona, se envia a la direccion configurada por defecto
		if (StringUtils.isBlank(correoElectronico)) {
			Map params = (Map) mailParams.getQueryParams();
			correoElectronico = (String) params.get("correoDefault");
		}
		return correoElectronico;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getSubject(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	@Override
	protected String getSubject(MailParams mailParams) throws Exception {
		String titulo = super.getSubject(mailParams);		
		Pais pais = mailParams.getPais();
		titulo = titulo + pais.getDescripcion();
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");		
		titulo = titulo + " Zona: " + (String) parameterMap.get("codigoZona");
		return titulo;
	}

	public MailResult enviarMail(String from, String destinos, String subject,
			String plantillaBody, Map parametros) {
		if(log.isDebugEnabled()){
			log.debug("enviarMail");
		}
		return null;
	}

	public MailResult enviarMailAdjunto(String from, String destinos,
			String subject, String plantillaTxt, String plantillaHtml,
			Map parametros) {
		if(log.isDebugEnabled()){
			log.debug("enviarMailAdjunto");
		}
		return null;
	}

	public MailResult enviarMail(String from, String destinos, String copias,
			String subject, String plantillaBody, Map parametros) {
		if(log.isDebugEnabled()){
			log.debug("enviarMail");
		}
		return null;
	}

	public MailResult enviarMail(String from, String destinos, String subject,
			String mensaje) {
		if(log.isDebugEnabled()){
			log.debug("enviarMail");
		}
		return null;
	}

	@Override
	protected String getBodyTxt(MailParams mailParams) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("getBodyTxt");
		}
		String plantillaTXT = "MailTxtReporteSACAsistencia.vm";
		return plantillaTXT;
	}

	
}
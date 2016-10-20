package biz.belcorp.ssicc.service.sisicc.framework;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;

/**
 * Clase Service abstracta para el envio de Correo luego de generado un Reporte. 
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */
@Service("sisicc.baseMailReporteAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseMailReporteAbstractService extends BaseMailAbstractService {

	@Resource(name="scsicc.reporteDAO")
	protected ReporteDAO reporteDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#prepareParameterMap(java.util.Map)
	 */
	protected Map prepareParameterMap(Map params) {
		Map paramReporte = reporteDAO.getParametrosReporte(params);
		String correoOrigen  = (String) paramReporte.get("correoOrigen");
		String correoCC = (String) paramReporte.get("correoCC");
		String subject  = (String) paramReporte.get("subject");
		String bodyTxt  = (String) paramReporte.get("bodyTxt");
		String bodyHtml = (String) paramReporte.get("bodyHtml");
		String nivelEnvioCorreoCC = (String) paramReporte.get("nivelEnvioCorreoCC");
		String correoDefault = (String) paramReporte.get("correoDefault");
		//String correoDefault = "gonz.fk@gmail.com";
		//indEmaiRegional
		String indEmailRegional = (String) paramReporte.get("indEmailRegional");
		
		params.put("correoOrigen", correoOrigen);
		params.put("correoCC", correoCC);
		params.put("subject", subject);		
		params.put("bodyTxt", bodyTxt);
		params.put("bodyHtml", bodyHtml);
		params.put("nivelEnvioCorreoCC", nivelEnvioCorreoCC);
		params.put("correoDefault", correoDefault);
		params.put("indEmailRegional",indEmailRegional);
		return params;
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
		return true;
	}
	
	
	
		/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getBodyHtml(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getBodyHtml(MailParams mailParams) throws Exception {
		Map params = mailParams.getQueryParams();
		String body = (String) params.get("bodyHtml");
		return body;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getBodyTxt(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getBodyTxt(MailParams mailParams) throws Exception {
		Map params = mailParams.getQueryParams();
		String body = (String) params.get("bodyTxt");
		return body;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getAttachmentFile(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected FileSystemResource getAttachmentFile(MailParams mailParams) {
		Map params = mailParams.getQueryParams();
		File archivoAtachado = (File) params.get("fileAttachment");
		FileSystemResource fsr = new FileSystemResource(archivoAtachado);
		return fsr;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getNombreFileAttachment(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getNombreFileAttachment(MailParams mailParams) {
		Map params = mailParams.getQueryParams();
		String retorno = (String) params.get("nombreArchivoReporte");
		return retorno;
	}
	
	

	/**
	 * @return Returns the reporteDAO.
	 */
	public ReporteDAO getReporteDAO() {
		return reporteDAO;
	}

	/**
	 * @param reporteDAO The reporteDAO to set.
	 */
	public void setReporteDAO(ReporteDAO reporteDAO) {
		this.reporteDAO = reporteDAO;
	}
	
	
	
	
}
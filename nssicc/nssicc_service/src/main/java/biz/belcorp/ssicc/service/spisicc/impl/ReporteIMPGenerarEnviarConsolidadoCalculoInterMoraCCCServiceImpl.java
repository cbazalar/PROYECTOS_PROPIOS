package biz.belcorp.ssicc.service.spisicc.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spisicc.ReporteIMPGenerarEnviarConsolidadoCalculoInterMoraCCCService;

@Service("spisicc.reporteIMPGenerarEnviarConsolidadoCalculoInterMoraCCCService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteIMPGenerarEnviarConsolidadoCalculoInterMoraCCCServiceImpl extends
   BaseSubReporteAbstractService implements ReporteIMPGenerarEnviarConsolidadoCalculoInterMoraCCCService{

	@Resource(name="msg.mailReporteGenerarEnviarReporteConsolidadoInterMoraService")
	private BaseMailService mailService;
	
	protected String getReporteFileName() {
		return "reporteCCCCalculoInterMoraCorreoXLS";
	}
	
	protected String getSubReporteFileName() {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteMSGCCCCalculoInterMoraCorreoForm.error";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void prepareParameterMap(Map params) throws Exception {
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String titulo = messageSource.getMessage("reporteMSGCCCCalculoInterMoraCorreoForm.titulo",null,getLocale(usuario));
		log.debug("titulo");
		params.put("titulo", titulo);
		params.put("formatoExportacion","XLS");
	
		
		log.debug("this.isVisualizarReporte() "+this.isVisualizarReporte());
	   
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		
		String nombreArchivoReporte;		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		nombreArchivoReporte = this.getPrefijoArchivo() +  "_" +
						           sdf.format(new Date(System.currentTimeMillis()));
		
		return nombreArchivoReporte;
	}
	
	/**
	 * @return the mailService
	 */
	public BaseMailService getMailService() {
		return mailService;
	}
	
	/**
	 * @param mailService the mailService to set
	 */
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}	
}

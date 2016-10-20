package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ReporteOCREnvioCargaDocumentoService;

@Service("spusicc.reporteOCREnvioCargaDocumentoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteOCREnvioCargaDocumentoServiceImpl  extends BaseSubReporteAbstractService implements ReporteOCREnvioCargaDocumentoService{
	
    private BaseMailService mailService;
    
    protected String getReporteFileName() {
		return "reporteOCRCargaDocumentoXLS";
	}
	
    protected String getSubReporteFileName() {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteOCREnvioCargaDocumento.error";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void prepareParameterMap(Map params) throws Exception{
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String titulo = messageSource.getMessage("reporteOCRCargaDocumentoForm.titulo",null,getLocale(usuario));
		
		log.debug("titulo");
		params.put("titulo", titulo);

		params.put("fechaInicio", (String)params.get("fechaProceso"));
		params.put("fechaFin", (String)params.get("fechaProceso"));
		
		try {
			Date fecha = null;
			fecha = DateUtils.parseDate((String)params.get("fechaProceso"), new String[]{"yyyyMMdd"});
			params.put("fecha", DateFormatUtils.format(fecha, "dd/MM/yyyy"));
		} catch (ParseException e) { }
		
		params.put("formatoExportacion","XLS");
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
	@Autowired
	@Qualifier("ocr.mailReporteOCRCargaDocumento")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}	
}

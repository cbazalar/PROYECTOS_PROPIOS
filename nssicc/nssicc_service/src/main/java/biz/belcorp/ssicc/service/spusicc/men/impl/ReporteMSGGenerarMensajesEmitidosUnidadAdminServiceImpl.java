package biz.belcorp.ssicc.service.spusicc.men.impl;

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
import biz.belcorp.ssicc.service.spusicc.men.ReporteMSGGenerarMensajesEmitidosUnidadAdminService;


@Service("spusicc.reporteMSGGenerarMensajesEmitidosUnidadAdminService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteMSGGenerarMensajesEmitidosUnidadAdminServiceImpl extends
	BaseSubReporteAbstractService implements ReporteMSGGenerarMensajesEmitidosUnidadAdminService {
	
    private String codigoPeriodo;
    
    @Resource(name="msg.mailReporteGenerarMensajesEmitidosService")
    private BaseMailService mailService;
    
    
           
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	protected String getReporteFileName() {
		return "reporteMaestroHorizontal";
	}
	
	protected String getSubReporteFileName() {
		return "reporteMSGGenerarMensajesEmitidosPDF";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteEDUGenerarResumenProgramacion.error.nivelGenerarPlanilla";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void prepareParameterMap(Map params) throws Exception {
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String titulo = messageSource.getMessage("reporteMSGGenerarMensajesEmitidosForm.titulo",null,getLocale(usuario));
		log.debug("titulo");
		params.put("NroReporte", "");
		params.put("titulo", titulo);
		params.put("formatoExportacion","VPDF");		
		params.put("codigoPeriodo", codigoPeriodo);
		
		log.debug("this.isVisualizarReporte() "+this.isVisualizarReporte());
       
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String codigoRegion = (String)parameterMap.get("codigoRegion");
		String codigoZona = (String)parameterMap.get("codigoZona");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		nombreArchivoReporte =getPrefijoArchivo()+ "_"+ codigoRegion+ codigoZona  
					 + "_" +codigoPeriodo+"_"+
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

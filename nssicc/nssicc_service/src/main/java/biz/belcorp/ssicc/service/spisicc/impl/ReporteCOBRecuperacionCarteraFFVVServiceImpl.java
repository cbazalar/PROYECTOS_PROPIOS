package biz.belcorp.ssicc.service.spisicc.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spisicc.ReporteCOBRecuperacionCarteraFFVVService;

@Service("spusicc.reporteCOBRecuperacionCarteraFFVVService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOBRecuperacionCarteraFFVVServiceImpl extends BaseSubReporteAbstractService implements ReporteCOBRecuperacionCarteraFFVVService  {
	
    private BaseMailService mailService;
    
    private String nombreArchivoReporteParam;
    
	protected String getReporteFileName() {
		if(StringUtils.isNotBlank(nombreArchivoReporteParam)){
			log.debug("El nombreArchivoReporte es: " + nombreArchivoReporteParam);
		}
		return nombreArchivoReporteParam;
	}
	
	protected String getSubReporteFileName() {
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteMSGSTOErroresCorreoForm.error";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void prepareParameterMap(Map params) throws Exception {
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		
		String titulo = messageSource.getMessage("procesoCOBRecuperacionCarteraFFVVForm.titulo",null,getLocale(usuario));
		log.debug("titulo");
		
		nombreArchivoReporteParam = MapUtils.getString(params, "nombreArchivoReporteParam");
		String codigoPeriodoInicial = MapUtils.getString(params, "codigoPeriodo");
		String codigoPeriodoFinal = MapUtils.getString(params, "codigoPeriodo");
		
		String condicionCodigoRegion = "";
		String condicionCodigoZona = "";
		String codigoRegion = MapUtils.getString(params, "codigoRegion");
		String codigoZona = MapUtils.getString(params, "codigoZona");	
		
		if(StringUtils.isNotBlank(codigoRegion)){
			condicionCodigoRegion = " AND est.cod_regi = '" + codigoRegion + "' ";
		}		
		if(StringUtils.isNotBlank(codigoZona)){
			condicionCodigoZona = " AND est.cod_zona = '" + codigoZona + "' ";
		}
		
		String condicion = condicionCodigoRegion + condicionCodigoZona;
		
		params.put("titulo", titulo);
		params.put("formatoExportacion","XLS");
		params.put("codigoPeriodoInicial", codigoPeriodoInicial);
		params.put("codigoPeriodoFinal", codigoPeriodoFinal);
		params.put("condicion", condicion);
		
		super.prepareParameterMap(params);
		
		log.debug(MapUtils.getString(params, "rutaPath"));
		
		log.debug("this.isVisualizarReporte() "+this.isVisualizarReporte());
       
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nar;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String codigoRegion = MapUtils.getString(parameterMap, "codigoRegion", ""); 
		String codigoZona = MapUtils.getString(parameterMap, "codigoZona", ""); 
		String codigoPeriodo = MapUtils.getString(parameterMap, "codigoPeriodo", ""); 
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		nar = "RepCobRecuCarteraFFVV" + "_"+ codigoRegion+ codigoZona  
					 + "_" +codigoPeriodo+"_"+
					sdf.format(new Date(System.currentTimeMillis()));

		return nar;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseReporteAbstractService#getMailService()
	 */
	public BaseMailService getMailService() {
		return mailService;
	}

	/**
	 * @param mailService the mailService to set
	 */
	@Autowired
	@Qualifier("msg.mailReporteCOBRecuperacionCarteraFFVVService")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}


	
}

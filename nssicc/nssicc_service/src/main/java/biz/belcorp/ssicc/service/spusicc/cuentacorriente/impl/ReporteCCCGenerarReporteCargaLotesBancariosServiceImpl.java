package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ReporteCCCGenerarReporteCargaLotesBancariosService;


@Service("spusicc.reporteCCCGenerarReporteCargaLotesBancariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCGenerarReporteCargaLotesBancariosServiceImpl extends
	BaseSubReporteAbstractService implements ReporteCCCGenerarReporteCargaLotesBancariosService {
	
    private String numeroLote;
    private BaseMailService mailService;
    
    
           
	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
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
		
		params.put("formatoExportacion","VXLS");		
		params.put("numeroLote", numeroLote);
		
		log.debug("this.isVisualizarReporte() "+this.isVisualizarReporte());
       
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		nombreArchivoReporte =getPrefijoArchivo() + "_" + numeroLote+"_"+
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
	@Qualifier("msg.mailReporteCCCGenerarReporteCargaLotesBancariosService")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}

	@Override
	protected String getSubReporteFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getReporteFileName() {
		// TODO Auto-generated method stub
		return null;
	}


	
}

package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionArchivosProveedoresFTPAsignaHoyService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ReporteCOBGeneracionArchivosProveedoresFTPAsignaHoyService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("spusicc.reporteCOBGeneracionArchivosProveedoresFTPAsignaHoyService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOBGeneracionArchivosProveedoresFTPAsignaHoyServiceImpl extends BaseSubReporteAbstractService 
     implements ReporteCOBGeneracionArchivosProveedoresFTPAsignaHoyService {

	protected String getReporteFileName() {
		return "reporteCOBGeneracionArchivosProveedoresFTPAsignaHoyXLS";
	}
	
	protected String getSubReporteFileName() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
				
		nombreArchivoReporte =  "Asig_Hoy_" + 
								parameterMap.get("nombreProveedor") + "_" + 
								parameterMap.get("nombreEtapa") + "_" + 
								sdf.format(new Date(System.currentTimeMillis()));

		return nombreArchivoReporte;
	}

	public BaseMailService getMailService() {
		// TODO Auto-generated method stub
		return null;
	}
}
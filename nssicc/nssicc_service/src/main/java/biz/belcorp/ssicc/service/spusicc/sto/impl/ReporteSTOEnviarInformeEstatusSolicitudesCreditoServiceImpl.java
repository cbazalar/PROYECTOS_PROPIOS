package biz.belcorp.ssicc.service.spusicc.sto.impl;

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

import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.ReporteSTOEnviarInformeEstatusSolicitudesCreditoService;


/**
 * @author itocto
 *
 */

@Service("spusicc.reporteSTOEnviarInformeEstatusSolicitudesCreditoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSTOEnviarInformeEstatusSolicitudesCreditoServiceImpl extends BaseSubReporteAbstractService implements ReporteSTOEnviarInformeEstatusSolicitudesCreditoService {
	
    private BaseMailService mailService;
    
	protected String getReporteFileName() {
		return "reporteSTOInformeEstatusSolicitudesCreditoXLS";
	}
	
	protected String getSubReporteFileName() {
		return "";
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
	@Qualifier("spusicc.mailReporteSTOEnviarInformeEstatusSolicitudesCreditoService")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		
		String codigoRegion = MapUtils.getString(parameterMap, "codigoRegion", "");
		String codigoZona = MapUtils.getString(parameterMap, "codigoZona", "");
		
		nombreArchivoReporte =  "InformeEstatusSolicitudesDeCredito_" + sdf.format(new Date(System.currentTimeMillis()));
		
		if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isBlank(codigoZona))
			nombreArchivoReporte =  "InformeEstatusSolicitudesDeCredito_" + codigoRegion + "_" + sdf.format(new Date(System.currentTimeMillis()));
		
		if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isNotBlank(codigoZona))
			nombreArchivoReporte =  "InformeEstatusSolicitudesDeCredito_" + codigoRegion + "_" + codigoZona + "_" + sdf.format(new Date(System.currentTimeMillis()));
		
		return nombreArchivoReporte;
	}
	
}

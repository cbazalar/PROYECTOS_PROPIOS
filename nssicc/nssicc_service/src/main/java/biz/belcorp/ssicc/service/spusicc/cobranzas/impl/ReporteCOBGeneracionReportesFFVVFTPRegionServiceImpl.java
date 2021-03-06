package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionReportesFFVVFTPRegionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ReporteCOBGeneracionReportesFFVVFTPRegionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("spusicc.reporteCOBGeneracionReportesFFVVFTPRegionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOBGeneracionReportesFFVVFTPRegionServiceImpl extends BaseSubReporteAbstractService implements ReporteCOBGeneracionReportesFFVVFTPRegionService {

	private String formatoExportacion;
	
	protected String getReporteFileName() {
		if(StringUtils.equals(formatoExportacion, "XLS"))
			return "reporteCOBGeneracionReportesFFVVFTPRegionXLS";
		else
			return "reporteMaestroVertical";
	}
	
	protected String getSubReporteFileName() {
		return "reporteCOBGeneracionReportesFFVVFTPRegionPDF";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseSubReporteAbstractService#prepareParameterMap(java.util.Map)
	 */
	protected void prepareParameterMap(Map params) throws Exception {
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		
		this.formatoExportacion = MapUtils.getString(params, "formatoExportacion");
		 
		ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteCOBGeneracionReportesFFVVFTPRegion" + JASPER_EXTENSION);
		try {
			params.put("SUBREPORT_DIR1", (JasperReport)JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath()))); 
					//JRLoader.loadObject(resource1.getFile()));
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String titulo = messageSource.getMessage("reporteCOBGeneracionReportesFFVVFTPRegionForm.titulo1", null, getLocale(usuario));
		params.put("titulo", titulo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		
		nombreArchivoReporte =  "Recuperacion de Cobranza Region " + 
								parameterMap.get("codigoRegion") + 
								"-" + 
								sdf.format(new Date(System.currentTimeMillis()));

		return nombreArchivoReporte;
	}

	public BaseMailService getMailService() {
		// TODO Auto-generated method stub
		return null;
	}
}
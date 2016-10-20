package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseReporteInterfaceService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;

@Service("spusicc.reporteLECEnviarReporteProyecionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteLECEnviarReporteProyecionService extends BaseSubReporteAbstractService implements BaseReporteInterfaceService{
	
	@Resource(name="lec.mailReporteLECEnviarReporteProyecionService")
	protected BaseMailService mailService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getReporteFileName()
	 */
	protected String getReporteFileName() {
		return "reporteMaestroHorizontalLECProyeccion";
	}
	
	@Override
	protected String getSubReporteFileName() {
		return "reporteLECProyeccionPDF";
	}
	
	@Override
	protected void prepareParameterMap(Map params) throws Exception{
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String titulo = messageSource.getMessage("reporteLECProyeccionForm.titulo", null, getLocale(usuario));
		params.put("titulo", titulo);
		
		ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLECProyeccion" + JASPER_EXTENSION);
		ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLECPProyeccionPDF" + JASPER_EXTENSION);
		try {
			params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath())));
			params.put("SUBREPORT_DIR2", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath())));
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String codigoRegion = (String)parameterMap.get("codigoRegion");
		String codigoZona = (String)parameterMap.get("codigoZona");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		nombreArchivoReporte =getPrefijoArchivo()+ "_"+ codigoRegion+ codigoZona + sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
	}
	
	@Override
	public BaseMailService getMailService() {
		return this.mailService;
	}
	
	
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}

}

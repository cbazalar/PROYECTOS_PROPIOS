package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

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
import biz.belcorp.ssicc.service.spusicc.pedidos.ReportePEDEnviarReporteSeguimientoConsultoraService;

@Service("spusicc.reportePEDEnviarReporteSeguimientoConsultoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReportePEDEnviarReporteSeguimientoConsultoraServiceImpl extends BaseSubReporteAbstractService implements ReportePEDEnviarReporteSeguimientoConsultoraService {
	
	/** The mail service. */
    private BaseMailService mailService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getReporteFileName()
	 */
	protected String getReporteFileName() {
		String reporte=  "reportePEDSeguimientoConsultoraDXLS";
		return reporte;
	}
	
	@Override
	protected String getSubReporteFileName() {
		String reporte="";
		return reporte;
	}
	
	@Override
	protected void prepareParameterMap(Map params) throws Exception {
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String titulo = messageSource.getMessage("reporteLECProyeccionForm.titulo", null, getLocale(usuario));
		params.put("titulo", titulo);
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
		
		nombreArchivoReporte =getPrefijoArchivo()+ "_"+  codigoZona + sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
	}
	
	@Override
	public BaseMailService getMailService() {
		return this.mailService;
	}
	
	@Autowired
	@Qualifier("lec.mailReporteLECPagoSociaEmpresaria")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}

}

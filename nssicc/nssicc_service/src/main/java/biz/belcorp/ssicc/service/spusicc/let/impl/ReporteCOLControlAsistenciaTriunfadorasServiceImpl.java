package biz.belcorp.ssicc.service.spusicc.let.impl;

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
import biz.belcorp.ssicc.service.spusicc.let.ReporteCOLControlAsistenciaTriunfadorasService;

@Service("spusicc.reporteCOLControlAsistenciaTriunfadorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOLControlAsistenciaTriunfadorasServiceImpl extends
		BaseSubReporteAbstractService implements ReporteCOLControlAsistenciaTriunfadorasService {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getReporteFileName()
	 */
	protected String getReporteFileName() {
		return "reporteMaestroVerticalControlAsistenciaTriunfadoras";
	}
	
	@Override
	protected String getSubReporteFileName() {
		return "reporteCOLAsistenciaTriunPDF";
	}
	
	@Override
    protected void beforeExecuteReporte(ReporteParams reporteParams)
    		throws Exception {
		super.beforeExecuteReporte(reporteParams);
		Map params = (Map)reporteParams.getQueryParams().get("parameterMap");
		log.debug("Los parametros del Reporte en el before son: "
				+ params.toString());
		
		this.reporteService.executeControlAsistenciaTriunfadoras(params);
		return;
    }

	
	@Override
	protected void prepareParameterMap(Map params) throws Exception {
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String codigoPeriodo = (String) params.get("codigoPeriodo");
		String titulo = messageSource.getMessage("reporteCOLControlAsistenciaTriunfadorasForm.titulo",null,getLocale(usuario));
		String subTitulo = messageSource.getMessage("reporteCOLControlAsistenciaTriunfadorasForm.subTitulo",null,getLocale(usuario)) + " " + codigoPeriodo;
		params.put("titulo", titulo);
		params.put("subTitulo", subTitulo);
		log.debug("prepareParameterMap "+this.isVisualizarReporte());
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
		
		nombreArchivoReporte =getPrefijoArchivo()+ "_"+ codigoRegion+ codigoZona +
					sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
	}
	
	@Override
	public BaseMailService getMailService() {
		return this.mailService;
	}
	
	@Autowired
	@Qualifier("ccc.mailReporteCOLControlAsistenciaTriunfadorasService")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}

	
	
	
}

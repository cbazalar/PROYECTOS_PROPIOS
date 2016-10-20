package biz.belcorp.ssicc.service.spusicc.sap.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sap.ReporteSAPUnidadesDespachoCodigosDAO;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spusicc.sap.ReporteSAPUnidaDespCodigosService;

@Service("spusicc.reporteSAPUnidaDespCodigosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSAPUnidaDespCodigosServiceImpl extends	BaseSubReporteAbstractService implements ReporteSAPUnidaDespCodigosService{

	@Resource(name="spusicc.reporteSAPUnidadesDespachoCodigosDAO")
	private ReporteSAPUnidadesDespachoCodigosDAO reporteSAPUnidadesDespachoCodigosDAO;
	
	@Override
	protected String getSubReporteFileName() {
		return "reporteSAPUnidadDespCodPDF";
	}
	
	@Override
    protected void beforeExecuteReporte(ReporteParams reporteParams)
    		throws Exception {
    	super.beforeExecuteReporte(reporteParams);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");

		reporteSAPUnidadesDespachoCodigosDAO.executeRegistrosUnidadesDespachoCodigosSAP(params);
    }

	
	@Override
	protected void prepareParameterMap(Map params) throws Exception {
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String titulo = messageSource.getMessage("reporteSAPUnidDespCod.titulo",null,getLocale(usuario));
		params.put("titulo", titulo);
		log.debug("prepareParameterMap "+this.isVisualizarReporte());
	}
	
	protected String getReporteFileName() {
		return "reporteMaestroVertical";
	}	
	
	public BaseMailService getMailService() {
		return this.mailService;
	}
	
	@Autowired
	@Qualifier("ocr.mailReporteOCRCargaDocumento")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}	
}

/**
 * 
 */
package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

/**
 * @author Danny Amaro
 *
 */
@Service("reportes.reporteCCCMediosMagneticosCuentaCorrienteBimensualService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCMediosMagneticosCuentaCorrienteBimensualServiceImpl extends BaseReporteInterfaceImpl {
	private static final long serialVersionUID = -4390094424857702131L;
		
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;

	
	
	
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_CCC_MEDIOS_MAGNETICOS_CUENTA_CORRIENTE_BIMENSUAL;
		return nombreArchivo;
	}
	
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String titulo = "";
		titulo = "reporteCCCMediosMagneticosCuentaCorrienteBimensualForm.tituloReporteCCCMediosMagneticosCuentaCorrienteBimensualCSV";
		
		return titulo;
	}
	
	public Map generarReporteOracle(Map parameterMap) {
		reporteService.executeGeneraReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensualCSV(parameterMap);
		return parameterMap;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
			
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		reporteService.executeBeforeReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual(params);
		return reporteParams;
	}
	

	
	
	
}

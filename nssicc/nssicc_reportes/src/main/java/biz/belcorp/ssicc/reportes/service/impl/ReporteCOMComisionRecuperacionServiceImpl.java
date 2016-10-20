/**
 * 
 */
package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComisionRecuperacionForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionGerenteZonaService;

/**
 * @author Danny Amaro
 *
 */
@Service("reportes.reporteCOMComisionRecuperacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOMComisionRecuperacionServiceImpl extends BaseReporteInterfaceImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4390094424857704111L;
	
	@Resource(name="spusicc.mantenimientoCOMComisionGerenteZonaService")
	private MantenimientoCOMComisionGerenteZonaService mantenimientoCOMComisionGerenteZonaService;
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionRecuperacionServiceImpl.beforeExecuteReporte' method");
		}
		
						
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		reporteService.devuelvePorcentajeTramoComision(params);
		
		/* Obteniendo indicadores de comision */
		Integer oidComision = mantenimientoCOMComisionGerenteZonaService.getDevuelveIDComision(params);
		params.put("oidComision", oidComision);
		Map paramIndicadorComision = mantenimientoCOMComisionGerenteZonaService.getIndicadoresComision(params);
		this.log.debug("Los parametros Indicador Comision: " + paramIndicadorComision.toString());
		params.put("indicadorFeriado", paramIndicadorComision.get("indicadorFeriado").toString().trim());
		params.put("indicadorDsctoImpuesto", paramIndicadorComision.get("indicadorDsctoImpuesto").toString().trim());
				
		//params.putAll(paramIndicadorComision);
		this.log.debug("Los parametros del Reporte en el before son: "+ params.toString());
		
		ReporteCOMComisionRecuperacionForm reporteForm = (ReporteCOMComisionRecuperacionForm)formReporte;
		
		/* Ejecutando procesos previos */
		if ("5".equals(reporteForm.getPresentacion())) {
			reporteService.executeComisionRecuperacionGZona(params);
		}
		if ("2".equals(reporteForm.getPresentacion())) {
			reporteService.executeReporteSQL("executeComisionRecuperacionSeccion", params);
		}
		if ("3".equals(reporteForm.getPresentacion())) {
			reporteService.executeReporteSQL("executeComisionRecuperacionZona", params);
		}
		if ("4".equals(reporteForm.getPresentacion())) {
			reporteService.executeReporteSQL("executeComisionRecuperacionRegion", params);
		}
		
		reporteParams.setQueryParams(params);
		
		return reporteParams;
		
	}
	
}

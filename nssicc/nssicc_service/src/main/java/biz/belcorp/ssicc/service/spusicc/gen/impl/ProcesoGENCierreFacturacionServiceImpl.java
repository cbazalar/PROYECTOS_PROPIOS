package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionDAO;
import biz.belcorp.ssicc.service.scsicc.framework.ReporteExecutionService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECalificacionEstatusService;

@Service("spusicc.procesoGENCierreFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENCierreFacturacionServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name = "spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionDAO")
	private MantenimientoOCRPedidoControlFacturacionDAO mantenimientoOCRPedidoControlFacturacionDAO;
	
	@Resource(name = "spusicc.procesoMAECalificacionEstatusService")
	private ProcesoMAECalificacionEstatusService procesoMAECalificacionEstatusService;
	
	@Resource(name = "scsicc.reporteExecutionService")
	private ReporteExecutionService reporteExecutionService;
	
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		if (log.isDebugEnabled()) {
			log.debug(params);
		}
		mantenimientoOCRPedidoControlFacturacionDAO.executeProcesoOCRActualizaGP2(params);
		procesoMAECalificacionEstatusService.executeCalificacionEstatusFacturacionDiaria(params);
		ReporteParams reportParams = new ReporteParams();
        reportParams.setJasperFileName("reporteSACIndicadoresFNA.jasper");
        reportParams.setQueryParams(params);
        ReporteResult reporteResult = reporteExecutionService.executeReporte(reportParams);
        
        String outfile = new String("");// archivo de salida
		outfile += "reporteGenerico.xls";
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, reporteResult.getJasperPrint());
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outfile );
		exporter.exportReport();
		
		params.put("fileAttachment", new File(outfile));
		procesoMAECalificacionEstatusService.enviarCorreoAdjunto(params);
	}


	
}

package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.ventas.ProcesoVENService;

@Service("reportes.procesoVENLibroVentasDetalleSIIService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProcesoVENLibroVentasDetalleSIIServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Resource(name = "spusicc.procesoVENService")
	private ProcesoVENService procesoVENService; 
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarReporteOracle(java.util.Map)
	 */
	@Override
	public Map<String, Object> generarReporteOracle(
			Map<String, Object> parameterMap) {
		
		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OZIP"))
			procesoVENService.executeGenerarArchivosLibroVentasDetalleSII(parameterMap);
		
		return parameterMap;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	@Override
	public String generarNombreArchivoReporteOracle(
			Map<String, Object> parameterMap) {
		String nombreArchivo = "";
		if(parameterMap.get("accion").equals(Constants.REPORTE_VENTAS_CODIGO_ACCION_NOTA_CREDITO)){
			nombreArchivo = "NotaCredito_" + DateUtil.convertDateToString("yyyyMMddHHMMss", new Date());
		}
		if(parameterMap.get("accion").equals(Constants.REPORTE_VENTAS_CODIGO_ACCION_BOLETA_VENTA)){
			nombreArchivo = "BoletaVenta_" + DateUtil.convertDateToString("yyyyMMddHHMMss", new Date());
		}
		return nombreArchivo;
	}

	

}


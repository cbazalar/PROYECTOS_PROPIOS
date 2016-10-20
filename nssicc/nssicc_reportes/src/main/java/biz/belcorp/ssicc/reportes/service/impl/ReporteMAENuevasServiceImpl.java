package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

/**
 * The Class ReporteCOBDetalladoCobranza31DiasServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/03/2014
 */
@Service("reportes.reporteMAENuevasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteMAENuevasServiceImpl extends BaseReporteInterfaceImpl {
	
		
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarReporteOracle(java.util.Map)
	 */
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		if(log.isDebugEnabled()){
			log.debug("generarReporteOracle");
		}
		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV")){
			parameterMap = reporteService.generarReporteMAEConsultorasPedidosCSV(parameterMap);
		}		
		return parameterMap;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'beforeExecuteReporte' method");
		}
		
		Map criteria = (Map)reporteParams.getQueryParams().get("parameterMap");
		
		log.debug("Parametros a usar: [oidPais=" + (String)criteria.get("oidPais") +
				  ", codigoPais="   + (String)criteria.get("codigoPais") + 
				  ", oidPeriodo="   + (Integer)criteria.get("oidPeriodo") + 
				  ", oidActividad=" + (String)criteria.get("oidActividad") +
				  ", condicionFechaInicio=" + (String)criteria.get("condicionFechaInicio") + 
				  ", condicionFechaFin=" + (String)criteria.get("condicionFechaFin") + 
				  ", condicionRegion1=" + (String)criteria.get("condicionRegion1") +
				  ", condicionZona1=" + (String)criteria.get("condicionZona1") + 
				  ", condicionFechaInicio2=" + (String)criteria.get("condicionFechaInicio2") + 
				  ", condicionFechaFin2=" + (String)criteria.get("condicionFechaFin2") +
				  ", condicionRegion2=" + (String)criteria.get("condicionRegion2") + 
				  ", condicionZona2=" + (String)criteria.get("condicionZona2") + 
				  ", tipoReporte=" + (String)criteria.get("tipoReporte") +
				  ", codigoPeriodo=" + (String)criteria.get("codigoPeriodo") +
				  ", condicionRegion=" + (String)criteria.get("condicionRegion") +
				  ", condicionZona=" + (String)criteria.get("condicionZona") +
				  ", codigoUsuario=" + (String)criteria.get("codigoUsuario") +
				  ", fechaInicioCumpleanios=" + (String)criteria.get("fechaInicioCumpleanios") +
				  ", fechaFinCumpleanios=" + (String)criteria.get("fechaFinCumpleanios") +
				  //", condicionBloqueo=" + (String)criteria.get("condicionBloqueo") +
				  ", condicionEstado=" + (String)criteria.get("condicionEstado") +
				  ", condicionSaldo=" + (String)criteria.get("condicionSaldo") +
				  ", condicionPedido=" + (String)criteria.get("condicionPedido") +
				  "]");
		
		int tipo  = Integer.parseInt((String)criteria.get("tipoReporte"));
		
		if (tipo > 1 && tipo!=7) {
			reporteService.executeGenerarReporteMAEConsultorasPedidos(criteria);
		}
		if (tipo < 2 || tipo==7) {
			reporteService.executeCargaTemporalReporteMAEConsultorasPedidos(criteria);
		}
		
		return reporteParams;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		if(log.isDebugEnabled()){
			log.debug("generarNombreArchivoReporteOracle");
		}
		
		String fe = MapUtils.getString(parameterMap, "formatoExportacion", "") + MapUtils.getString(parameterMap, "tipoConsulta", "");
		
		if ("OCSV0".equals(fe))
			return "reporteMAENuevasCSV";
		else if ("OCSV1".equals(fe))
			return "reporteMAENuevasRechazadasCSV";
		else if ("OCSV2".equals(fe))
			return "reporteMAENuevasPrimerPedidoCSV";
		else if ("OCSV3".equals(fe))
			return "reporteMAENuevasSegundoPedidoCSV";
		else if ("OCSV4".equals(fe))
			return "reporteMAENuevasTercerPedidoCSV";
		else if ("OCSV5".equals(fe))
			return "reporteMAENuevasConsultorasActivasCSV";
		else if ("OCSV6".equals(fe))
			return "reporteMAENuevasConsultorasInactivasCSV";
		else if (StringUtils.equals(fe, "OCSV7"))
			return "reporteMAENuevasConsultorasLLamadaBievenidaCSV";
		else
			return "reporteMaestroHorizontal";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
//		return "reporteCOBDetalladoCobranza31DiasForm.tituloReporteCSV";
		return "";
	}

	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,	BaseReporteForm formReporte) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("afterExecuteReporte");
		}
		Map criteria = (Map)reporteParams.getQueryParams().get("parameterMap");		
		reporteService.deleteTemporalReporteMAEConsultorasPedidos(criteria);
		return reporteParams;
	}
	
}

package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;

/**
 * The Class ReporteCOBDetalladoRecuperacionCarteraCobradorServiceImpl.
 *
 * @autor: Hernando Huaman
 * @version: 1.0
 * 18/11/2014
 */
@Service("reportes.reporteCOBDetalladoRecuperacionCarteraCobradorService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOBDetalladoRecuperacionCarteraCobradorServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;	 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_COB_DETA_RECU_CARTE_COBRA;
		return nombreArchivo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		return "reporteCOBDetalladoRecuperacionCarteraCobradorForm.tituloReporteCSV";
	}
	
		
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#generarReporteOracle(java.util.Map)
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map generarReporteOracle (Map parameterMap) {
		//-- Variables
		ReporteService reporteService = (ReporteService) this.reporteService;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("reporteCOBDetalladoRecuperacionCarteraCobrador - formatoExportacion: " + formatoExportacion);
		if (formatoExportacion.equals("OCSV")) {
			reporteService.executeReporteCOBDetalladoRecuperacionCarteraCobradorCSV(parameterMap);
		}
		return parameterMap;
	}	

}

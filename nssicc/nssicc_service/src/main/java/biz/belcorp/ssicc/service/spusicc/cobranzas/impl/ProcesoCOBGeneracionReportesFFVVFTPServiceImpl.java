package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBGeneracionReportesFFVVFTPDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBGeneracionReportesFFVVFTPService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionReportesFFVVFTPDetalladoDeudorasService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionReportesFFVVFTPDetalladoTotalesService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionReportesFFVVFTPRegionService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionReportesFFVVFTPZonaService;

/**
 * <p>
 * <a href="ProcesoCOBGeneracionReportesFFVVFTPServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */
@Service("spusicc.procesoCOBGeneracionReportesFFVVFTPService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBGeneracionReportesFFVVFTPServiceImpl extends BaseService implements ProcesoCOBGeneracionReportesFFVVFTPService {

	@Resource(name="sisicc.mailUtil")
	private BaseMailService mailUtil;
			
	@Resource(name="spusicc.procesoCOBGeneracionReportesFFVVFTPDAO")
	private ProcesoCOBGeneracionReportesFFVVFTPDAO procesoCOBGeneracionReportesFFVVFTPDAO;
	
	@Resource(name="spusicc.reporteCOBGeneracionReportesFFVVFTPRegionService")
	private ReporteCOBGeneracionReportesFFVVFTPRegionService reporteCOBGeneracionReportesFFVVFTPRegionService;
	
	@Resource(name="spusicc.reporteCOBGeneracionReportesFFVVFTPZonaService")
	private ReporteCOBGeneracionReportesFFVVFTPZonaService reporteCOBGeneracionReportesFFVVFTPZonaService;
	
	@Resource(name="spusicc.reporteCOBGeneracionReportesFFVVFTPDetalladoDeudorasService")
	private ReporteCOBGeneracionReportesFFVVFTPDetalladoDeudorasService reporteCOBGeneracionReportesFFVVFTPDetalladoDeudorasService;
	
	@Resource(name="spusicc.reporteCOBGeneracionReportesFFVVFTPDetalladoTotalesService")
	private ReporteCOBGeneracionReportesFFVVFTPDetalladoTotalesService reporteCOBGeneracionReportesFFVVFTPDetalladoTotalesService;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBGeneracionReportesFFVVFTPDAO#executeGenerarDataFFVVFTP(java.util.Map)
	 */
	public void executeGenerarDataFFVVFTP(Map criteria) {
		procesoCOBGeneracionReportesFFVVFTPDAO.executeGenerarDataFFVVFTP(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBGeneracionReportesFFVVFTPService#executeReportes(java.util.Map)
	 */
	public void executeReportes(Map params) {
		String codigoUsuario = MapUtils.getString(params, "codigoUsuario");
		
		params.put("usuarioTemp", MapUtils.getObject(params, "usuario"));
		
		//Reporte de Regiones
		List regiones = procesoCOBGeneracionReportesFFVVFTPDAO.obtenerRegionesA(codigoUsuario);
		
		for (int i = 0; i < regiones.size(); i++) {
			Map regionMap = (Map) regiones.get(i);
			String codigoRegion = MapUtils.getString(regionMap, "codigoRegion");
			params.put("codigoRegion", codigoRegion);
			params.put("codigoUsuario", codigoUsuario);
			
			try {
				params.put("formatoExportacion", "VPDF");
				reporteCOBGeneracionReportesFFVVFTPRegionService.grabarReporte(params);
				
				params.put("formatoExportacion", "XLS");
				reporteCOBGeneracionReportesFFVVFTPRegionService.grabarReporte(params);
				
				//Reporte de Zonas
				List zonas = procesoCOBGeneracionReportesFFVVFTPDAO.obtenerZonasA(params);
				
				for (int j = 0; j < zonas.size(); j++) {
					Map zonaMap = (Map) zonas.get(j);
					String codigoZona = MapUtils.getString(zonaMap, "codigoZona");
					params.put("codigoZona", codigoZona);
					
					try {
						params.put("formatoExportacion", "VPDF");
						reporteCOBGeneracionReportesFFVVFTPZonaService.grabarReporte(params);
						
						params.put("formatoExportacion", "XLS");
						reporteCOBGeneracionReportesFFVVFTPZonaService.grabarReporte(params);
						
						try {
							//Reporte Detallado Deudoras
							reporteCOBGeneracionReportesFFVVFTPDetalladoDeudorasService.grabarReporte(params);
							
							//Reporte Detallado Totales
							reporteCOBGeneracionReportesFFVVFTPDetalladoTotalesService.grabarReporte(params);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
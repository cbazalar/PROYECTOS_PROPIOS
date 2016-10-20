package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBGeneracionArchivosProveedoresFTPDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBGeneracionArchivosProveedoresFTPService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionArchivosProveedoresFTPAsignaHoyService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionArchivosProveedoresFTPAsignaPorDiasService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionArchivosProveedoresFTPSeguiCampanaService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionArchivosProveedoresFTPSeguiEtapaService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ReporteCOBGeneracionArchivosProveedoresFTPSeguiRegionService;

/**
 * <p>
 * <a href="ProcesoCOBGeneracionArchivosProveedoresFTPServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */
@Service("spusicc.procesoCOBGeneracionArchivosProveedoresFTPService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBGeneracionArchivosProveedoresFTPServiceImpl extends BaseService implements ProcesoCOBGeneracionArchivosProveedoresFTPService {

	@Resource(name="sisicc.mailUtil")
	private BaseMailService mailUtil;
		
	@Resource(name="spusicc.procesoCOBGeneracionArchivosProveedoresFTPDAO")
	private ProcesoCOBGeneracionArchivosProveedoresFTPDAO procesoCOBGeneracionArchivosProveedoresFTPDAO;
	
	@Resource(name="spusicc.reporteCOBGeneracionArchivosProveedoresFTPSeguiEtapaService")
	private ReporteCOBGeneracionArchivosProveedoresFTPSeguiEtapaService reporteCOBGeneracionArchivosProveedoresFTPSeguiEtapaService;
	
	@Resource(name="spusicc.reporteCOBGeneracionArchivosProveedoresFTPSeguiCampanaService")
	private ReporteCOBGeneracionArchivosProveedoresFTPSeguiCampanaService reporteCOBGeneracionArchivosProveedoresFTPSeguiCampanaService;
	
	@Resource(name="spusicc.reporteCOBGeneracionArchivosProveedoresFTPSeguiRegionService")
	private ReporteCOBGeneracionArchivosProveedoresFTPSeguiRegionService reporteCOBGeneracionArchivosProveedoresFTPSeguiRegionService;
	
	@Resource(name="spusicc.reporteCOBGeneracionArchivosProveedoresFTPAsignaHoyService")
	private ReporteCOBGeneracionArchivosProveedoresFTPAsignaHoyService reporteCOBGeneracionArchivosProveedoresFTPAsignaHoyService;
	
	@Resource(name="spusicc.reporteCOBGeneracionArchivosProveedoresFTPAsignaPorDiasService")
	private ReporteCOBGeneracionArchivosProveedoresFTPAsignaPorDiasService reporteCOBGeneracionArchivosProveedoresFTPAsignaPorDiasService;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBGeneracionReportesFFVVFTPService#executeReportes(java.util.Map)
	 */
	public void executeReportes(Map params) {
		String codigoUsuario = MapUtils.getString(params, "codigoUsuario");
		
		params.put("usuarioTemp", MapUtils.getObject(params, "usuario"));
		
		//Reporte de Regiones
		List proveedoresEtapas = procesoCOBGeneracionArchivosProveedoresFTPDAO.obtenerProveedoresEtapas();
		
		for (int i = 0; i < proveedoresEtapas.size(); i++) {
			Map proveedorEtapaMap = (Map) proveedoresEtapas.get(i);
			String codigoProveedor = MapUtils.getString(proveedorEtapaMap, "codigoProveedor");
			String nombreProveedor = MapUtils.getString(proveedorEtapaMap, "nombreProveedor");
			String codigoEtapa = MapUtils.getString(proveedorEtapaMap, "codigoEtapa");
			String nombreEtapa = MapUtils.getString(proveedorEtapaMap, "nombreEtapa");
			params.put("codigoProveedor", codigoProveedor);
			params.put("nombreProveedor", nombreProveedor);
			params.put("codigoEtapa", codigoEtapa);
			params.put("nombreEtapa", nombreEtapa);
			
			try {
				params.put("formatoExportacion", "VPDF");
				reporteCOBGeneracionArchivosProveedoresFTPSeguiEtapaService.grabarReporte(params);
								
				params.put("formatoExportacion", "VXLS");
				reporteCOBGeneracionArchivosProveedoresFTPSeguiCampanaService.grabarReporte(params);
				
				reporteCOBGeneracionArchivosProveedoresFTPSeguiRegionService.grabarReporte(params);
				
				reporteCOBGeneracionArchivosProveedoresFTPAsignaHoyService.grabarReporte(params);
				
				reporteCOBGeneracionArchivosProveedoresFTPAsignaPorDiasService.grabarReporte(params);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
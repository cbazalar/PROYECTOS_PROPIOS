package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEnvioCentroAcopioFacturadoService;
import biz.belcorp.ssicc.service.spusicc.sto.ReporteSTOEnvioCentroAcopioFacturadoDetalleService;
import biz.belcorp.ssicc.service.spusicc.sto.ReporteSTOEnvioCentroAcopioFacturadoService;


/**
 * Service para el proceso de Reportes al termino de facturacin de Centros de Acopio
 * 
 * @author <a href="mailto:esanchez@sigcomt.com">Eduardo Snchez</a>
 */
@Service("spusicc.procesoSTOEnvioCentroAcopioFacturadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOEnvioCentroAcopioFacturadoServiceImpl extends BaseInterfazProcesoAbstractService implements ProcesoSTOEnvioCentroAcopioFacturadoService{

	@Resource(name="scsicc.reporteDAO")
	private ReporteDAO reporteDAO;
	
	@Resource(name="spusicc.reporteSTOEnvioCentroAcopioFacturadoService")
	private ReporteSTOEnvioCentroAcopioFacturadoService reporteService;
	
	@Resource(name="spusicc.reporteSTOEnvioCentroAcopioFacturadoDetalleService")
	private ReporteSTOEnvioCentroAcopioFacturadoDetalleService reporteDetalleService;
	
	@Resource(name="spusicc.procesoSTOEjecucionValidacionesDAO")
	private ProcesoSTOEjecucionValidacionesDAO procesoSTOEjecucionValidacionesDAO;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService
	 * #executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {

		log.debug("inicio ProcesoSTOEnvioCentroAcopioFacturadoServiceImpl"
				+ params);
		reporteDAO.executeCargaReporteCentroAcopioFacturado(params);
		
		Map criteria = new HashMap();
		List listaParametros = procesoSTOEjecucionValidacionesDAO.getCentrosDeAcopioFacturado(criteria);
		
		if(listaParametros!= null && listaParametros.size() > 0)
		{
			params.put("usuarioTemp", MapUtils.getObject(params, "usuario"));
			
			log.info("Nro de reportes a generar : " + listaParametros.size()*2);
			
			for(int i=0; i<listaParametros.size(); i++)
			{
				Map map = (Map)listaParametros.get(i);
				
				params.put("nomCentroAcopio", MapUtils.getString(map, "nomCentroAcopio", ""));
				params.put("ciaTransporte",   MapUtils.getString(map, "ciaTransporte", ""));
				params.put("codigoCiaTransporte", MapUtils.getString(map, "codCiaTransporte", ""));
				params.put("codigoCentroAcopio", MapUtils.getString(map, "codCentroAcopio", ""));
				params.put("emailCentroAcopio", MapUtils.getString(map, "emailCentroAcopio", ""));
				
				try {
					reporteService.grabarReporte(params);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
				
				try {
					reporteDetalleService.grabarReporte(params);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		}				
		else
		{
			log.info("No se generaron reportes..");
		}		
	}

}

package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.spusicc.mae.ReporteMAENovedadesZonaService;


/**
 * Service para el proceso que Genera Reporte de Novedades Zonas
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
@Service("spusicc.procesoMAENovedadesZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAENovedadesZonaServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="scsicc.reporteService")
	ReporteService reporteService;
	
	//service de reporte
	@Resource(name="spusicc.reporteMAENovedadesZonaService")
	ReporteMAENovedadesZonaService reporteMAENovedadesZonaService;
	 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		 log.debug("inicio ProcesoMAENovedadesZonasServiceImpl " + params);
		 
		 //SE TRATA D EUNA EMISON DE REPORTES NO ES NESESARIOP PARA TODOS LOS PROCESOS
		 //INVOLUCRADOS SOLO BASTA CON UNO	
		 List listCorreoGz = reporteService.getListaZonasReporteMAENovedadesZona();
		 for (int i=0; i < listCorreoGz.size(); i++) {
				String[] cods = StringUtils.split(((Base)listCorreoGz.get(i)).getCodigo(), ',');
				String codigoZona = cods[0]; 
				String codigoRegion = cods[1]; 
				String valorFiltro =  "Zona: ".concat(cods[0]).concat(" Region: ").concat(cods[1]);	
				params.put("codigoRegion", codigoRegion);
				params.put("codigoZona", codigoZona);
				params.put("valorFiltro", valorFiltro);
				
				try {
					reporteMAENovedadesZonaService.grabarReporte(params);
					//return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		 }	
		 Map map = new HashMap();
		 map.put("indProcEnvio", "E");			
		 reporteService.updateReporteMAENovedadesZona(map);
	 
	 
	}


	
	
}
package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseReporteInterfaceService;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECEnviarReporteProyeccionService;


/**
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("spusicc.procesoLECEnviarReporteProyeccionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECEnviarReporteProyeccionServiceImpl extends BaseService implements ProcesoLECEnviarReporteProyeccionService{

	@Resource(name="spusicc.reporteLECEnviarReporteProyecionService")
	private BaseReporteInterfaceService reporteLECEnviarReporteProyecionService;
	
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.ProcesoLECEnviarReporteProyeccionService#executeEnviarReporteProyeccion(java.util.Map)
	 */
	public void executeEnviarReporteProyeccion(Map params) throws Exception {
		
		String codigoUsuario = MapUtils.getString(params, "codigoUsuario");
		
		params.put("usuarioTemp", usuarioService.getUsuarioByUsername(codigoUsuario));
		params.put("formatoExportacion", "PDF");
		
		reporteService.executeReporteLECEnviarReporteProyecion(params);
		
		//obtenemos la lista de regiones del reporte
		List listaRegiones = reporteService.getRegionesReporteLECEnviarReporteProyecion(params);
		
		if (listaRegiones != null && listaRegiones.size() > 0)
		{				
			//Por cada region y zona obtenemos la data generamos el reporte y enviamos a la gz, si hay data				
			for(int i=0; i<listaRegiones.size(); i++)
			{
				//obtenemos las zonas de cada region
				Map zonaParams = new HashMap();
				zonaParams.put("codigoRegion", ((Base)listaRegiones.get(i)).getCodigo());
				zonaParams.put("codigoUsuario", codigoUsuario);
				
				List zonasRegion = reporteService.getZonasReporteLECEnviarReporteProyecion(zonaParams);
				
				if(zonasRegion != null && zonasRegion.size() > 0)
				{
					for(int j=0; j<zonasRegion.size(); j++)
					{
						Base baseRegion = (Base)listaRegiones.get(i);
						Base baseZona= (Base)zonasRegion.get(j);
						
						if(StringUtils.isNotBlank(baseZona.getDescripcion()))
						{
						    params.put("codigoRegion", baseRegion.getCodigo());
							params.put("codigoZona", baseZona.getCodigo());
							params.put("correoGZ", baseZona.getDescripcion());
							
							reporteLECEnviarReporteProyecionService.grabarReporte(params);
						}
					}
				}
			}
		}
			
	}

		
}

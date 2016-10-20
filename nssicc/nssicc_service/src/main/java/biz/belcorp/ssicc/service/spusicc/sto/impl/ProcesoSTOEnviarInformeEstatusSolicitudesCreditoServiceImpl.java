/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEnviarInformeEstatusSolicitudesCreditoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEnviarInformeEstatusSolicitudesCreditoService;
import biz.belcorp.ssicc.service.spusicc.sto.ReporteSTOEnviarInformeEstatusSolicitudesCreditoService;

/**
 * 
 * <p>
 * <a href="ProcesoSTOEnviarInformeEstatusSolicitudesCreditoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("spusicc.procesoSTOEnviarInformeEstatusSolicitudesCreditoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOEnviarInformeEstatusSolicitudesCreditoServiceImpl extends BaseService implements ProcesoSTOEnviarInformeEstatusSolicitudesCreditoService {

	@Resource(name="spusicc.procesoSTOEnviarInformeEstatusSolicitudesCreditoDAO")
	private ProcesoSTOEnviarInformeEstatusSolicitudesCreditoDAO procesoSTOEnviarInformeEstatusSolicitudesCreditoDAO;
	
	@Resource(name="spusicc.reporteSTOEnviarInformeEstatusSolicitudesCreditoService")
	private ReporteSTOEnviarInformeEstatusSolicitudesCreditoService reporteSTOEnviarInformeEstatusSolicitudesCreditoService;
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEnviarInformeEstatusSolicitudesCreditoService#executeEnviarReportes(java.util.Map)
	 */
	public String executeEnviarReportes(Map params) throws Exception {
		
		String status = Constants.OK;
	
		    //Obtenemos el listado de Regiones del reporte
			List regiones = procesoSTOEnviarInformeEstatusSolicitudesCreditoDAO.getRegiones(params);
			
			if(regiones != null && regiones.size() > 0)
			{
				for(int i=0; i<regiones.size(); i++)
				{
					Base region = (Base)regiones.get(i);
					params.put("codigoRegion", region.getCodigo());
					params.put("codigoZona", null);
					params.put("flagCualCorreo", Constants.ZON_MANT_GERENTE_REGION);
					
					String correoGR = procesoSTOEnviarInformeEstatusSolicitudesCreditoDAO.getCorreo(params);
					
					if(StringUtils.isNotBlank(correoGR))
					{
						//Generar y enviar el reporte para la region
						params.put("correoDestino", correoGR);
						reporteSTOEnviarInformeEstatusSolicitudesCreditoService.grabarReporte(params);
					}
					
					List zonas = procesoSTOEnviarInformeEstatusSolicitudesCreditoDAO.getZonas(params);
					
					if(zonas != null && zonas.size() > 0)
					{
						for(int j=0; j<zonas.size(); j++)
						{
							Base zona = (Base)zonas.get(j);
							params.put("codigoZona", zona.getCodigo());
							params.put("flagCualCorreo", Constants.ZON_MANT_GERENTE_ZONA);
							String correoGZ = procesoSTOEnviarInformeEstatusSolicitudesCreditoDAO.getCorreo(params);
							
							if(StringUtils.isNotBlank(correoGZ))
							{
								//Generar y enviar el reporte para la zona
								params.put("correoDestino", correoGZ);
								reporteSTOEnviarInformeEstatusSolicitudesCreditoService.grabarReporte(params);
								//
							}
						}
					}
				}
			}
		
		return status;
		
	}

}

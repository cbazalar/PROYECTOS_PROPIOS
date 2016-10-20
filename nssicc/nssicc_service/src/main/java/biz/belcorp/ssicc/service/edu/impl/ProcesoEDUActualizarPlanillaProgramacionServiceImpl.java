package biz.belcorp.ssicc.service.edu.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUActualizarPlanillaProgramacionDAO;
import biz.belcorp.ssicc.service.edu.ProcesoEDUActualizarPlanillaProgramacionService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Service("edu.procesoEDUActualizarPlanillaProgramacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUActualizarPlanillaProgramacionServiceImpl extends BaseService  
		implements ProcesoEDUActualizarPlanillaProgramacionService	{
	
	@Resource(name="edu.procesoEDUComercialService")
	ProcesoEDUComercialService	procesoEDUComercialService;
	
	@Resource(name="edu.procesoEDUActualizarPlanillaProgramacionDAO")
	ProcesoEDUActualizarPlanillaProgramacionDAO procesoEDUActualizarPlanillaProgramacionDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUActualizarPlanillaProgramacionService#executeActualizarPlanillaProgramacion(java.lang.String, java.util.Map)
	 */
	public void executeActualizarPlanillaProgramacion(String codigoPais, Map params) throws Exception {
		
		/* Invocando proceso previo a la Actualizacin de Planilla de Programacin */
		procesoEDUComercialService.executeProcesoEDUCargarAptasPorProgramar(codigoPais, params);
					
        /* Invocando al Proceso de Actualizacion de Planilla de Programacion */
		params.put("codigoPais", codigoPais);
		procesoEDUActualizarPlanillaProgramacionDAO.executeActualizarPlanillaProgramacion(params);
			
	}



		
}

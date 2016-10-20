package biz.belcorp.ssicc.service.edu.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUInicioProcesosDiariosDAO;
import biz.belcorp.ssicc.service.edu.ProcesoEDUInicioProcesosDiariosService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Service("edu.procesoEDUInicioProcesosDiariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUInicioProcesosDiariosServiceImpl extends BaseService  
		implements ProcesoEDUInicioProcesosDiariosService	{
	
	@Resource(name="edu.procesoEDUInicioProcesosDiariosDAO")
	ProcesoEDUInicioProcesosDiariosDAO procesoEDUInicioProcesosDiariosDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUInicioProcesosDiariosService#executeInicioProcesosDiarios(java.lang.String, java.util.Map)
	 */
	public void executeInicioProcesosDiarios(String codigoPais, Map params) throws Exception {
		String listaRegion[] = (String[])params.get("regiones");
			
		/* Registro de Planillas no procesadas */
		for(int i=0; i < listaRegion.length; i++ ) {
			listaRegion[i] = listaRegion[i].trim();
			params.put("codigoPais", codigoPais);
			params.put("codigoRegion", listaRegion[i]);
			
			/* Invocando Procedimiento Oracle efectua el Registro de Planillas no procesadas */
			procesoEDUInicioProcesosDiariosDAO.executeRegistrarPlanillasNoProcesadas(params);
		}
	}

	
	public void executeProcesoConsultoraRezagadas(String codigoPais, Map params) throws Exception {
		String listaRegion[] = (String[])params.get("regiones");
		
		/* Registro de Planillas no procesadas */
		for(int i=0; i < listaRegion.length; i++ ) {
			listaRegion[i] = listaRegion[i].trim();
			params.put("codigoPais", codigoPais);
			params.put("codigoRegion", listaRegion[i]);
			
			/* Invocando Procedimiento Oracle efectua el Registro de Planillas no procesadas */
			procesoEDUInicioProcesosDiariosDAO.executeProcesoConsultoraRezagadas(params);
		}
		
	}
	
		
}

package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUCerrarCursosVigentesDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUGenerarPlanillaProgramacionDAO;
import biz.belcorp.ssicc.dao.edu.model.ParametroProceso;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCerrarCursosVigentesService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Service("edu.procesoEDUCerrarCursosVigentesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUCerrarCursosVigentesServiceImpl extends BaseService  
		implements ProcesoEDUCerrarCursosVigentesService	{
	
	@Resource(name="edu.procesoEDUCerrarCursosVigentesDAO")
	ProcesoEDUCerrarCursosVigentesDAO procesoEDUCerrarCursosVigentesDAO;
	
	@Resource(name="edu.procesoEDUGenerarPlanillaProgramacionDAO")
	ProcesoEDUGenerarPlanillaProgramacionDAO procesoEDUGenerarPlanillaProgramacionDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCerrarCursosVigentesService#executeCerrarCursosVigentes(java.util.Map)
	 */
	public void executeCerrarCursosVigentes(Map params)  {
		
		/*Insertamos los parametro de Regiones seleccionadas*/
		List regionList = (List) params.get("regionList");
		String codigoProceso = Constants.EDU_PARAM_PROC_REGION;
		String codigoParam   = Constants.EDU_PARAM_PROC_REGION;
		params.put("codigoProceso", codigoProceso);
		params.put("codigoParam", codigoParam);
		if ((regionList!=null) && (regionList.size()>0)){
			ParametroProceso parametroProceso = new ParametroProceso();
			for (int i=0;i<regionList.size();i++){
				parametroProceso = new ParametroProceso();
				String region = (String) regionList.get(i);
				parametroProceso.setCodigoProceso(codigoProceso);
				parametroProceso.setCodigoParametro(codigoParam);
				parametroProceso.setValorCadenaParametro(region);
				procesoEDUGenerarPlanillaProgramacionDAO.insertParametroProceso(parametroProceso);
			}
		}
		procesoEDUCerrarCursosVigentesDAO.executeCerrarCursosVigentes(params);
	}
	

	

		
}

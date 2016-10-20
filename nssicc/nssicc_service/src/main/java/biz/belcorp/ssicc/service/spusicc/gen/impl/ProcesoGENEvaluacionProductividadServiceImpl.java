package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoGENEvaluacionProductividadService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENEvaluacionProductividadServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name = "spusicc.procesoLETEvaluacionProductividadCierreRegionCampaniaDAO")
	private ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAO procesoLETEvaluacionProductividadCierreRegionCampaniaDAO;
	
	

	protected void executeStoreProcedure(Map params) {
		procesoLETEvaluacionProductividadCierreRegionCampaniaDAO.executeProcesoLETEvaluacionProductividadCierreRegionCampania(params);
	}
}
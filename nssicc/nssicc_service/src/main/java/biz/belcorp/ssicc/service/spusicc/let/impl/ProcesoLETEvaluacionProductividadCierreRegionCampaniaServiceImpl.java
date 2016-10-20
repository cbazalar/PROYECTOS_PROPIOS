package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETEvaluacionProductividadCierreRegionCampaniaService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETEvaluacionProductividadCierreRegionCampaniaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.procesoLETEvaluacionProductividadCierreRegionCampaniaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETEvaluacionProductividadCierreRegionCampaniaServiceImpl extends BaseService implements ProcesoLETEvaluacionProductividadCierreRegionCampaniaService {

	@Resource(name="spusicc.procesoLETEvaluacionProductividadCierreRegionCampaniaDAO")
	private ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAO procesoLETEvaluacionProductividadCierreRegionCampaniaDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCalculoPedidoObjetivoSeccionCampaniaService#executeProcesoLETCalculoPedidoObjetivoSeccionCampania(java.util.Map)
	 */
	public void executeProcesoLETEvaluacionProductividadCierreRegionCampania(Map params) {
		log.info("Entro a ProcesoLETEvaluacionProductividadCierreRegionCampaniaServiceImpl - executeProcesoLETEvaluacionProductividadCierreRegionCampania(java.util.Map)");
		procesoLETEvaluacionProductividadCierreRegionCampaniaDAO.executeProcesoLETEvaluacionProductividadCierreRegionCampania(params);
		log.info("Salio a ProcesoLETEvaluacionProductividadCierreRegionCampaniaServiceImpl - executeProcesoLETEvaluacionProductividadCierreRegionCampania(java.util.Map)");
	}


	
}

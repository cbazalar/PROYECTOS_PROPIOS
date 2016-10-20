package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETGenerarRecomendacionLiderDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETGenerarRecomendacionLiderService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETGenerarRecomendacionLiderServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.procesoLETGenerarRecomendacionLiderService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETGenerarRecomendacionLiderServiceImpl extends BaseService implements ProcesoLETGenerarRecomendacionLiderService {

	@Resource(name="spusicc.procesoLETGenerarRecomendacionLiderDAO")
	private ProcesoLETGenerarRecomendacionLiderDAO procesoLETGenerarRecomendacionLiderDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETGenerarRecomendacionLiderService#executeProcesoLETGenerarRecomendacionesLider(java.util.Map)
	 */
	public void executeProcesoLETGenerarRecomendacionesLider(Map params) {
		log.info("Entro a ProcesoLETGenerarRecomendacionLiderServiceImpl - executeProcesoLETGenerarRecomendacionesLider(java.util.Map)");
		procesoLETGenerarRecomendacionLiderDAO.executeProcesoLETGenerarRecomendacionesLider(params);
		log.info("Salio a ProcesoLETGenerarRecomendacionLiderServiceImpl - executeProcesoLETGenerarRecomendacionesLider(java.util.Map)");
	}

	
}

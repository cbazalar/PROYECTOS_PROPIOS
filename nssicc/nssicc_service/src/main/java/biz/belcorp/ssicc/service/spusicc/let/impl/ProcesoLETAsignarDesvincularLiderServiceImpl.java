package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETAsignarDesvincularLiderDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETAsignarDesvincularLiderService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETAsignarDesvincularLiderServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.procesoLETAsignarDesvincularLiderService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETAsignarDesvincularLiderServiceImpl extends BaseService implements ProcesoLETAsignarDesvincularLiderService {

	@Resource(name="spusicc.procesoLETAsignarDesvincularLiderDAO")
	private ProcesoLETAsignarDesvincularLiderDAO procesoLETAsignarDesvincularLiderDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETAsignarDesvincularLiderService#executeAsignarDesvincularLider(java.util.Map)
	 */
	public void executeAsignarDesvincularLider(Map params) {
		log.info("Entro a ProcesoLETAsignarDesvincularLiderServiceImpl - executeAsignarDesvincularLider(java.util.Map)");
		procesoLETAsignarDesvincularLiderDAO.executeAsignarDesvincularLider(params);
		log.info("Salio a ProcesoLETAsignarDesvincularLiderServiceImpl - executeAsignarDesvincularLider(java.util.Map)");
	}


	
}

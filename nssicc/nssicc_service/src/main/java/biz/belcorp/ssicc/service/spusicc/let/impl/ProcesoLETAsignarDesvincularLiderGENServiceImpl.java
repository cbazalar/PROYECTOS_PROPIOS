package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETAsignarDesvincularLiderDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Clase de la implementaci�n de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETAsignarDesvincularLiderGENServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="ghuertas@sigcomt.com">Gonzalo Javier Huertas Agurto</a>
 *         
 */
@Service("spusicc.procesoLETAsignarDesvincularLiderGENService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETAsignarDesvincularLiderGENServiceImpl extends BaseInterfazProcesoAbstractService {

	
	@Resource(name="spusicc.procesoLETAsignarDesvincularLiderDAO")
	private ProcesoLETAsignarDesvincularLiderDAO procesoLETAsignarDesvincularLiderDAO;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	
	protected void executeStoreProcedure(Map params){
		log.info("Entro a ProcesoLETAsignarDesvincularLiderServiceImpl - executeAsignarDesvincularLider(java.util.Map)");
		procesoLETAsignarDesvincularLiderDAO.executeAsignarDesvincularLider(params);
		log.info("Salio a ProcesoLETAsignarDesvincularLiderServiceImpl - executeAsignarDesvincularLider(java.util.Map)");
		
	}

	
	
}

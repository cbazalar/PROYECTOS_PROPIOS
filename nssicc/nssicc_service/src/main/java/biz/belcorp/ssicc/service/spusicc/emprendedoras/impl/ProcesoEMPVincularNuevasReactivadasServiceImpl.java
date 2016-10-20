package biz.belcorp.ssicc.service.spusicc.emprendedoras.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.emprendedoras.ProcesoEMPReasignacionMasivaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.ProcesoEMPVincularNuevasReactivadasService;

/**
 * Service que va a realizar las vinculaciones de empresarias al cierre de campaa
 * 
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Service("spusicc.procesoEMPVincularNuevasReactivadasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEMPVincularNuevasReactivadasServiceImpl extends BaseService implements ProcesoEMPVincularNuevasReactivadasService {
	                    
	@Resource(name = "spusicc.procesoEMPReasignacionMasivaDAO")
	private ProcesoEMPReasignacionMasivaDAO procesoEMPReasignacionMasivaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	public void executeStoreProcedure(Map params) {
		procesoEMPReasignacionMasivaDAO.executeVinculacionNuevasReactivadas(params);				
	}


			
}

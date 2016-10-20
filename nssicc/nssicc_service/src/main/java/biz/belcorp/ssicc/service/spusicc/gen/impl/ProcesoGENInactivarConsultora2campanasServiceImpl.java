package biz.belcorp.ssicc.service.spusicc.gen.impl;
 
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ProcesoMAEClasificacionClientesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author <a href="mailto: cbazalar@sigcomt.com">Carlos Bazalar</a>
 * 
 */
@Service("spusicc.procesoGENInactivarConsultora2campanasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENInactivarConsultora2campanasServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name = "spusicc.procesoMAEClasificacionClientesDAO")
	private ProcesoMAEClasificacionClientesDAO procesoMAEClasificacionClientesDAO;
	


	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException,	Exception {
		
		log.debug(">> ProcesoGENInactivarConsultora2campanasServiceImpl.executeStoreProcedure");
		procesoMAEClasificacionClientesDAO.executeInactivarConsultora2campanas(params);
	}

	
}

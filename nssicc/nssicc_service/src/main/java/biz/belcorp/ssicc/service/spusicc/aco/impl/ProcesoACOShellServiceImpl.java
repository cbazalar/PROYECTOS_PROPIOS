package biz.belcorp.ssicc.service.spusicc.aco.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.aco.ProcesoACOShellDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que ejecuta los shell de ACO
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */

@Service("spusicc.procesoACOShellService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoACOShellServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoACOShellDAO")
	private ProcesoACOShellDAO procesoACOShellDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
	 log.debug("inicio ProcesoACOShellServiceImpl " + params);
	 procesoACOShellDAO.executeProcesoShell(params);
	}
	
}
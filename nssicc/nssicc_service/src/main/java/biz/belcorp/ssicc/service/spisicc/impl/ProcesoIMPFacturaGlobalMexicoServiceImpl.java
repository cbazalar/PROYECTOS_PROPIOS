/**
 * 
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * <p>
 * <a href="ProcesoIMPFacturaGlobalMexicoServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:sguerra@belcorp.biz">Sebastian Guerra</a>
 */
@Service("spisicc.procesoIMPFacturaGlobalMexicoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPFacturaGlobalMexicoServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spisicc.procesoImpresionDAO")
	private ProcesoImpresionDAO procesoImpresionDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		procesoImpresionDAO.executeGeneraFacturaGlobalMexico(params);
	}

}

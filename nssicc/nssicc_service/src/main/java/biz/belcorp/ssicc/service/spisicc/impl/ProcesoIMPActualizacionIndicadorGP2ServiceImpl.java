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
 * <a href="ProcesoIMPActualizacionIndicadorGP2ServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="">Sebastian Guerra</a>
 */
@Service("spisicc.procesoIMPActualizacionIndicadorGP2Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPActualizacionIndicadorGP2ServiceImpl extends BaseInterfazProcesoAbstractService {
	
	@Resource(name="spisicc.procesoImpresionDAO")
	private ProcesoImpresionDAO procesoImpresionDAO;

	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		procesoImpresionDAO.executeActualizaIndicadorGP2(params);
	}

}

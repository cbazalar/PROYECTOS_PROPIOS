package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCProcesoVencimientoPuntosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

@Service("spusicc.procesoINCProcesoVencimientoPuntosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCProcesoVencimientoPuntosServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoINCProcesoVencimientoPuntosDAO")
	private ProcesoINCProcesoVencimientoPuntosDAO procesoINCProcesoVencimientoPuntosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCProcesoVencimientoPuntosDAO.executeProcesoVencimientoPuntos(params);
	}
	
}


package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalcularVencimientoPuntosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Sergio Apaza
 *
 */
@Service("spusicc.procesoINCCalcularVencimientoPuntosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCalcularVencimientoPuntosServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoINCCalcularVencimientoPuntosDAO")
	private ProcesoINCCalcularVencimientoPuntosDAO procesoINCCalcularVencimientoPuntosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCCalcularVencimientoPuntosDAO.executeCalcularVencimientoPuntos(params);
	}
	
}

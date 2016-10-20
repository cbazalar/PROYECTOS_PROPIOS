package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDeterminarGanadorasRecomendacionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Sergio Apaza
 *
 */
@Service("spusicc.procesoINCDeterminarGanadorasRecomendacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCDeterminarGanadorasRecomendacionServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoINCDeterminarGanadorasRecomendacionDAO")
	private ProcesoINCDeterminarGanadorasRecomendacionDAO procesoINCDeterminarGanadorasRecomendacionDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCDeterminarGanadorasRecomendacionDAO.executeDeterminarGanadorasRecomendacion(params);
	}
}

package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDeterminarGanadorasRecomendacionPeriodoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Sergio Apaza
 *
 */
@Service("spusicc.procesoINCDeterminarGanadorasRecomendacionPeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCDeterminarGanadorasRecomendacionPeriodoServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoINCDeterminarGanadorasRecomendacionPeriodoDAO")
	private ProcesoINCDeterminarGanadorasRecomendacionPeriodoDAO procesoINCDeterminarGanadorasRecomendacionPeriodoDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCDeterminarGanadorasRecomendacionPeriodoDAO.executeDeterminarGanadorasRecomendacionPeriodo(params);
	}
}

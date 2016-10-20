package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDeterminarGanadorasRecomendacionZonaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Sergio Apaza
 *
 */
@Service("spusicc.procesoINCDeterminarGanadorasRecomendacionZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCDeterminarGanadorasRecomendacionZonaServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoINCDeterminarGanadorasRecomendacionZonaDAO")
	private ProcesoINCDeterminarGanadorasRecomendacionZonaDAO procesoINCDeterminarGanadorasRecomendacionZonaDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCDeterminarGanadorasRecomendacionZonaDAO.executeDeterminarGanadorasRecomendacionZona(params);
	}
}
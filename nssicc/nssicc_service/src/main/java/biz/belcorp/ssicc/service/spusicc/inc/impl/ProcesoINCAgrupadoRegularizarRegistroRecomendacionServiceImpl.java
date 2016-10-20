package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCRegularizarRegistroRecomendacionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoINCAgrupadoRegularizarRegistroRecomendacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCAgrupadoRegularizarRegistroRecomendacionServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoINCRegularizarRegistroRecomendacionDAO")
	private ProcesoINCRegularizarRegistroRecomendacionDAO procesoINCRegularizarRegistroRecomendacionDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCRegularizarRegistroRecomendacionDAO.executeRegularizarRegistroRecomendacionInicioCampana(params);
	}
}
package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETDesvinculacionAutomaticaLideresDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoLETAgrupadoDesvinculacionAutomaticaLideresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETAgrupadoDesvinculacionAutomaticaLideresServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLETDesvinculacionAutomaticaLideresDAO")
	private ProcesoLETDesvinculacionAutomaticaLideresDAO procesoLETDesvinculacionAutomaticaLideresDAO;

		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoLETDesvinculacionAutomaticaLideresDAO.executeDesvinculacionAutomaticaLideres(params);
	}
}
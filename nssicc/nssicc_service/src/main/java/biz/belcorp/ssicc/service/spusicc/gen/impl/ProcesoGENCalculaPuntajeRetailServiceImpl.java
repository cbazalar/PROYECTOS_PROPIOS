package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalculaPuntajeRetailDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Sergio Apaza
 *
 */
@Service("spusicc.procesoGENCalculaPuntajeRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENCalculaPuntajeRetailServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name = "spusicc.procesoINCCalculaPuntajeRetailDAO")
	private ProcesoINCCalculaPuntajeRetailDAO procesoINCCalculaPuntajeRetailDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCCalculaPuntajeRetailDAO.executeCalculaPuntajeRetail(params);
	}
}
package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETGenerarRecomendacionLiderDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jess James Rios Franco
 *
 */
@Service("spusicc.procesoLETAgrupadoGenerarRecomendacionLiderService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETAgrupadoGenerarRecomendacionLiderServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLETGenerarRecomendacionLiderDAO")
	private ProcesoLETGenerarRecomendacionLiderDAO procesoLETGenerarRecomendacionLiderDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params){
		procesoLETGenerarRecomendacionLiderDAO.executeProcesoLETGenerarRecomendacionesLider(params);
	}
}
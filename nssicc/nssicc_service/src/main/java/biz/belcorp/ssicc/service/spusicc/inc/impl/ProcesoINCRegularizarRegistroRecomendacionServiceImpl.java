package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCRegularizarRegistroRecomendacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCRegularizarRegistroRecomendacionService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoINCRegularizarRegistroRecomendacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCRegularizarRegistroRecomendacionServiceImpl extends BaseService implements
	ProcesoINCRegularizarRegistroRecomendacionService {
		
	@Resource(name="spusicc.procesoINCRegularizarRegistroRecomendacionDAO")
	private ProcesoINCRegularizarRegistroRecomendacionDAO procesoINCRegularizarRegistroRecomendacionDAO;

	/**
	 * @param procesoINCRegularizarRegistroRecomendacionDAO the procesoINCRegularizarRegistroRecomendacionDAO to set
	 */
	public void setProcesoINCRegularizarRegistroRecomendacionDAO(
			ProcesoINCRegularizarRegistroRecomendacionDAO procesoINCRegularizarRegistroRecomendacionDAO) {
		this.procesoINCRegularizarRegistroRecomendacionDAO = procesoINCRegularizarRegistroRecomendacionDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCRegularizarRegistroRecomendacionService#executeRegularizarRegistroRecomendacion(java.util.Map)
	 */
	public void executeRegularizarRegistroRecomendacion(Map params) {
		procesoINCRegularizarRegistroRecomendacionDAO.executeRegularizarRegistroRecomendacion(params);
	}

}

package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalculoMetasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCalculoMetasService;

/**
 * @author <a href="mailto:avillavicencio@csigcomt.com">AV</a>
 *
 */
@Service("spusicc.procesoINCCalculoMetasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCalculoMetasServiceImpl extends BaseService implements
	ProcesoINCCalculoMetasService {
		
	@Resource(name="spusicc.procesoINCCalculoMetasDAO")
	private ProcesoINCCalculoMetasDAO procesoINCcalculoDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCalculoMetasService#executeCalculoMetas(java.util.Map)
	 */
	public void executeCalculoMetas(Map params) {
		//codigoPais, numLote
		procesoINCcalculoDAO.executeCalculoMetas(params);
	}

	
}

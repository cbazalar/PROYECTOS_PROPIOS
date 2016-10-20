package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCReemplazoPremioBolsaFaltantesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCReemplazoPremioBolsaFaltantesService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoINCReemplazoPremioBolsaFaltantesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCReemplazoPremioBolsaFaltantesServiceImpl extends BaseService implements
	ProcesoINCReemplazoPremioBolsaFaltantesService {
		
	@Resource(name="spusicc.procesoINCReemplazoPremioBolsaFaltantesDAO")
	private ProcesoINCReemplazoPremioBolsaFaltantesDAO procesoINCReemplazoPremioBolsaFaltantesDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCReemplazoPremioBolsaFaltantesService#getListConcursosFaltantes()
	 */
	public List getListConcursosFaltantes() {
		return procesoINCReemplazoPremioBolsaFaltantesDAO.getListConcursosFaltantes();	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCReemplazoPremioBolsaFaltantesService#executeReemplazoPremioBolsaFaltantes(java.util.Map)
	 */
	public void executeReemplazoPremioBolsaFaltantes(Map params) {
		procesoINCReemplazoPremioBolsaFaltantesDAO.executeReemplazoPremioBolsaFaltantes(params);
	}
	
}

package biz.belcorp.ssicc.service.spusicc.mav.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mav.ProcesoMAVFacturacionGerentesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVFacturacionGerentesService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoMAVFacturacionGerentesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAVFacturacionGerentesServiceImpl extends BaseService implements
	ProcesoMAVFacturacionGerentesService {
	
	@Resource(name="spusicc.procesoMAVFacturacionGerentesDAO")
	private ProcesoMAVFacturacionGerentesDAO procesoMAVFacturacionGerentesDAO;


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVFacturacionGerentesService#executeFacturacionGerentes(java.util.Map)
	 */
	public void executeFacturacionGerentes(Map params) {
		procesoMAVFacturacionGerentesDAO.executeFacturacionGerentes(params);
	}

}

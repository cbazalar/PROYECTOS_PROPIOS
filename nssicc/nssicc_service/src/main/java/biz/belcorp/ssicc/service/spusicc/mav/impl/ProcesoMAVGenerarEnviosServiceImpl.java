package biz.belcorp.ssicc.service.spusicc.mav.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mav.ProcesoMAVGenerarEnviosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVGenerarEnviosService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoMAVGenerarEnviosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAVGenerarEnviosServiceImpl extends BaseService implements
	ProcesoMAVGenerarEnviosService {
		
	@Resource(name="spusicc.procesoMAVGenerarEnviosDAO")
	private ProcesoMAVGenerarEnviosDAO procesoMAVGenerarEnviosDAO;


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVGenerarEnviosService#executeGenerarEnvios(java.util.Map)
	 */
	public void executeGenerarEnvios(Map params) {
		procesoMAVGenerarEnviosDAO.executeGenerarEnvios(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVGenerarEnviosService#existeMAVEnvios(java.util.Map)
	 */
	public boolean existeMAVEnvios(Map criteria) {
		return procesoMAVGenerarEnviosDAO.existeMAVEnvios(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVGenerarEnviosService#getExisteMatrizFacturacion(java.util.Map)
	 */
	public boolean getExisteMatrizFacturacion(Map criteria) {
		return procesoMAVGenerarEnviosDAO.getExisteMatrizFacturacion(criteria);
	}	
	
	
}

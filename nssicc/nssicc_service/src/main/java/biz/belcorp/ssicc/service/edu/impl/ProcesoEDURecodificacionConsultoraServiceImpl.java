package biz.belcorp.ssicc.service.edu.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.ProcesoEDURecodificacionConsultoraDAO;
import biz.belcorp.ssicc.service.edu.ProcesoEDURecodificacionConsultoraService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 *
 */
@Service("edu.procesoEDURecodificacionConsultoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDURecodificacionConsultoraServiceImpl extends BaseService  
		implements ProcesoEDURecodificacionConsultoraService	{

	@Resource(name="edu.procesoEDURecodificacionConsultoraDAO")
	ProcesoEDURecodificacionConsultoraDAO dao;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURecodificacionConsultoraService#executeRecodificacionConsultora(java.util.Map)
	 */
	public void executeRecodificacionConsultora(Map params) {	
		dao.executeRecodificacionConsultora(params);		
	}

}

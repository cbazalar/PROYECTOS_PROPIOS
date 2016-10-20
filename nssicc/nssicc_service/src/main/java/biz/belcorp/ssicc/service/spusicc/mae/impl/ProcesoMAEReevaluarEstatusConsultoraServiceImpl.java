package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEReevaluarEstatusConsultoraDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAEReevaluarEstatusConsultoraService;

/**
 * Service que executa las metodos de Reevaluacion de Estatus de Consultora
 * 
 * <p>
 * <a href="ProcesoMAEReevaluarEstatusConsultoraServiceImpl.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoMAEReevaluarEstatusConsultoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEReevaluarEstatusConsultoraServiceImpl extends BaseService
		implements ProcesoMAEReevaluarEstatusConsultoraService {
	
	@Resource(name="spusicc.procesoMAEReevaluarEstatusConsultoraDAO")
	ProcesoMAEReevaluarEstatusConsultoraDAO procesoMAEReevaluarEstatusConsultoraDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAEReevaluarEstatusConsultoraService#existenRegionesCerradas(java.util.Map)
	 */
	public boolean existenRegionesCerradas(Map criteria) {
		return procesoMAEReevaluarEstatusConsultoraDAO.existenRegionesCerradas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAEReevaluarEstatusConsultoraService#executeReevaluarEstatusConsultora(java.util.Map)
	 */
	public void executeReevaluarEstatusConsultora(Map params) {
		procesoMAEReevaluarEstatusConsultoraDAO.executeReevaluarEstatusConsultora(params);
	}
	
}

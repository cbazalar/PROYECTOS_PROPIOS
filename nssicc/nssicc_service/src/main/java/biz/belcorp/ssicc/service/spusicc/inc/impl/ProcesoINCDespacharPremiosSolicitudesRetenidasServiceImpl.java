package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDespacharPremiosSolicitudesRetenidasDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que Despacha Premios de Solicitudes Retenidas
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCDespacharPremiosSolicitudesRetenidasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCDespacharPremiosSolicitudesRetenidasServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoINCDespacharPremiosSolicitudesRetenidasDAO")
	private ProcesoINCDespacharPremiosSolicitudesRetenidasDAO procesoINCDespacharPremiosSolicitudesRetenidasDAO;
	                                                          
	protected void executeStoreProcedure(Map params) {
		procesoINCDespacharPremiosSolicitudesRetenidasDAO.executeDespacharPremiosSolicitudesRetenidas(params);
	}
	
	
}
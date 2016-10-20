package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCValidacionPremiosElectivosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service que va a realizar la validacion de premios electivos
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCValidacionPremiosElectivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCValidacionPremiosElectivosServiceImpl extends
	BaseInterfazProcesoAbstractService {
	 
	@Resource(name="spusicc.procesoINCValidacionPremiosElectivosDAO")
	private ProcesoINCValidacionPremiosElectivosDAO procesoINCValidacionPremiosElectivosDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCValidacionPremiosElectivosDAO.executeValidacionPremiosElectivos(params);
	}
	
	
	
}

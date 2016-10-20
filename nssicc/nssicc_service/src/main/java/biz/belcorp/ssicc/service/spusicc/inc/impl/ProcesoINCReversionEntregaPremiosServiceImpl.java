package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCReversionEntregaPremiosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que realiza la reversion de entrega de premios
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCReversionEntregaPremiosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCReversionEntregaPremiosServiceImpl extends
	BaseInterfazProcesoAbstractService {
	    
	@Resource(name="spusicc.procesoINCReversionEntregaPremiosDAO")
	private ProcesoINCReversionEntregaPremiosDAO procesoINCReversionEntregaPremiosDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCReversionEntregaPremiosDAO.executeReversionEntregaPremios(params);
	}
	

	
}

package biz.belcorp.ssicc.service.spusicc.love.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVAtencionPremiosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que Genera las solicitudes de premiacion del programa Love
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoLOVAtencionPremiosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLOVAtencionPremiosServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoLOVAtencionPremiosDAO")
	private ProcesoLOVAtencionPremiosDAO procesoLOVAtencionPremiosDAO;

	protected void executeStoreProcedure(Map params) {
		procesoLOVAtencionPremiosDAO.executeAtencionPremios(params);
	}
	
	
	
}

package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarEstatusConsultorasDAO;

/**
 * Service que va a actualizar los estatus de las consultoras
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoMAEActualizarEstatusConsultorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEActualizarEstatusConsultorasServiceImpl extends
	BaseInterfazProcesoAbstractService {
	
	@Resource(name="spusicc.procesoMAEActualizarEstatusConsultorasDAO")
	private ProcesoMAEActualizarEstatusConsultorasDAO procesoMAEActualizarEstatusConsultorasDAO;

	protected void executeStoreProcedure(Map params) {
		procesoMAEActualizarEstatusConsultorasDAO.executeActualizarEstatusConsultoras(params);
	}
	
}

package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarEstatusConsultorasPeriodoDAO;

/**
 * Service que va a actualizar los estatus de las consultoras
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoMAEActualizarEstatusConsultorasPeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEActualizarEstatusConsultorasPeriodoServiceImpl extends
	BaseInterfazProcesoAbstractService {
	
	@Resource(name="spusicc.procesoMAEActualizarEstatusConsultorasPeriodoDAO")
	private ProcesoMAEActualizarEstatusConsultorasPeriodoDAO procesoMAEActualizarEstatusConsultorasPeriodoDAO;

	protected void executeStoreProcedure(Map params) {
		procesoMAEActualizarEstatusConsultorasPeriodoDAO.executeActualizarEstatusConsultorasPeriodo(params);
	}
	
}
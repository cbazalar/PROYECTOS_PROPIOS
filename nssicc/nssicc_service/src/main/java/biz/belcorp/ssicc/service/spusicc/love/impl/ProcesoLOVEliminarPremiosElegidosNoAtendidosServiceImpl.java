package biz.belcorp.ssicc.service.spusicc.love.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVEliminarPremiosElegidosNoAtendidosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso de Eliminar Premios Elegidos no Atendidos para el programa LOVE
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoLOVEliminarPremiosElegidosNoAtendidosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLOVEliminarPremiosElegidosNoAtendidosServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoLOVEliminarPremiosElegidosNoAtendidosDAO")
	private ProcesoLOVEliminarPremiosElegidosNoAtendidosDAO procesoLOVEliminarPremiosElegidosNoAtendidosDAO;

	protected void executeStoreProcedure(Map params) {
		procesoLOVEliminarPremiosElegidosNoAtendidosDAO.executeEliminarPremiosElegidosNoAtendidos(params);
	}
	

	
}

package biz.belcorp.ssicc.service.spusicc.love.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVGenerarPuntajeComprasDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que Genera Puntaje por Compras para el programa LOVE
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoLOVGenerarPuntajeComprasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLOVGenerarPuntajeComprasServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoLOVGenerarPuntajeComprasDAO")
	private ProcesoLOVGenerarPuntajeComprasDAO procesoLOVGenerarPuntajeComprasDAO;

	protected void executeStoreProcedure(Map params) {
		List zonaList = (List)params.get("zonaList");
		for(int i = 0; i < zonaList.size(); i++) {
			String codigoZona = (String)zonaList.get(i);
			params.put("codigoZona", codigoZona);
			procesoLOVGenerarPuntajeComprasDAO.executeGenerarPuntajeCompras(params);	
		}
		
	}
	

}


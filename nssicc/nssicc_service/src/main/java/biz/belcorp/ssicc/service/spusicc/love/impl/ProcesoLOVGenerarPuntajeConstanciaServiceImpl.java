package biz.belcorp.ssicc.service.spusicc.love.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVGenerarPuntajeConstanciaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que Genera Puntaje por Constancia para el programa LOVE
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoLOVGenerarPuntajeConstanciaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLOVGenerarPuntajeConstanciaServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoLOVGenerarPuntajeConstanciaDAO")
	private ProcesoLOVGenerarPuntajeConstanciaDAO procesoLOVGenerarPuntajeConstanciaDAO;

	protected void executeStoreProcedure(Map params) {
		List zonaList = (List)params.get("zonaList");
		for(int i = 0; i < zonaList.size(); i++) {
			String codigoZona = (String)zonaList.get(i);
			params.put("codigoZona", codigoZona);
			procesoLOVGenerarPuntajeConstanciaDAO.executeGenerarPuntajeConstancia(params);
		}	
	}

}


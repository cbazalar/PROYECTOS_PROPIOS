package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarEstatusConsultorasRegionDAO;

/**
 * Service que va a actualizar los estatus de las consultoras
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoMAEActualizarEstatusConsultorasRegionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEActualizarEstatusConsultorasRegionServiceImpl extends
	BaseInterfazProcesoAbstractService {
	                               
	@Resource(name="spusicc.procesoMAEActualizarEstatusConsultorasRegionDAO")
	private ProcesoMAEActualizarEstatusConsultorasRegionDAO procesoMAEActualizarEstatusConsultorasRegionDAO;

	protected void executeStoreProcedure(Map params) {
		String[] codigoRegiones = (String[])params.get("codigoRegionList");
		for(int i=0; i<codigoRegiones.length; i++){
			params.put("codigoRegion", codigoRegiones[i]);
			procesoMAEActualizarEstatusConsultorasRegionDAO.executeActualizarEstatusConsultorasRegion(params);
		}
	}
	
}

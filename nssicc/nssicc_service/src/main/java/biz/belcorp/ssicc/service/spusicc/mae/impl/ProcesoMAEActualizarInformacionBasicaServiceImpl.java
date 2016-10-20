package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarInformacionBasicaDAO;

/**
 * Service que va a actualizar la informacion basica de las consultoras
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("spusicc.procesoMAEActualizarInformacionBasicaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEActualizarInformacionBasicaServiceImpl extends
	BaseInterfazProcesoAbstractService {
	                  
	@Resource(name="spusicc.procesoMAEActualizarInformacionBasicaDAO")
	private ProcesoMAEActualizarInformacionBasicaDAO procesoMAEActualizarInformacionBasicaDAO;

	protected void executeStoreProcedure(Map params) {
		procesoMAEActualizarInformacionBasicaDAO.executeActualizarInformacionBasica(params);
	}
	
	
}
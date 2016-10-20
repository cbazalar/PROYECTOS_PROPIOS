package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCRetenerSolicitudesPremiacionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que Retiene Solicitudes de Premiacion de Niveles Electivos
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCRetenerSolicitudesPremiacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCRetenerSolicitudesPremiacionServiceImpl extends
	BaseInterfazProcesoAbstractService {
	   
	@Resource(name="spusicc.procesoINCRetenerSolicitudesPremiacionDAO")
	private ProcesoINCRetenerSolicitudesPremiacionDAO procesoINCRetenerSolicitudesPremiacionDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCRetenerSolicitudesPremiacionDAO.executeRetenerSolicitudesPremiacion(params);
	}

	
}
package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ProcesoGENGenerarInformacionSolicitudesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoGENGenerarInformacionSolicitudesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENGenerarInformacionSolicitudesServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name = "spusicc.procesoGENGenerarInformacionSolicitudesDAO")
	private ProcesoGENGenerarInformacionSolicitudesDAO procesoGENGenerarInformacionSolicitudesDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) 
	throws InterfazException,Exception {
		procesoGENGenerarInformacionSolicitudesDAO.executeProcesoGENGenerarInformacionSolicitudes(params);
	}

}

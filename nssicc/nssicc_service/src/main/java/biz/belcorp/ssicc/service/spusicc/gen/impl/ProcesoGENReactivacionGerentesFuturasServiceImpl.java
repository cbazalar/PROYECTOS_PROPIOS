package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONDirectorioDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

@Service("spusicc.procesoGENReactivacionGerentesFuturasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENReactivacionGerentesFuturasServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name = "spusicc.mantenimientoZONDirectorioDAO")
	private MantenimientoZONDirectorioDAO mantenimientoZONDirectorioDAO;
	
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		
		if(log.isDebugEnabled())
			log.debug(params);
		
		mantenimientoZONDirectorioDAO.executeReactivacionGerentesFuturas(params);
	}

}

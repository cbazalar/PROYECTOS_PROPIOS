package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoGENActualizarNivelesRiesgoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENActualizarNivelesRiesgoServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name = "spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params){
		mantenimientoMAEClienteDAO.executeActualizarNivelesRiesgo(params);
	}
}
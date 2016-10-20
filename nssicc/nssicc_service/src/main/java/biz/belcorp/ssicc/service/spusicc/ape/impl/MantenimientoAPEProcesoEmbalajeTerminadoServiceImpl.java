package biz.belcorp.ssicc.service.spusicc.ape.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEProcesoEmbalajeTerminadoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEProcesoEmbalajeTerminadoService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEProcesoEmbalajeTerminadoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">David Ramos</a>
 * 
 */

@Service("spusicc.mantenimientoAPEProcesoEmbalajeTerminadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEProcesoEmbalajeTerminadoServiceImpl extends BaseService implements MantenimientoAPEProcesoEmbalajeTerminadoService{
	
	@Resource(name="spusicc.mantenimientoAPEProcesoEmbalajeTerminadoDAO")
	private MantenimientoAPEProcesoEmbalajeTerminadoDAO mantenimientoAPEProcesoEmbalajeTerminadoDAO;

	

	

	
	
}




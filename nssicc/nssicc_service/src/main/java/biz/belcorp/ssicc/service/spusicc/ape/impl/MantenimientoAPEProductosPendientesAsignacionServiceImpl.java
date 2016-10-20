package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEProductosPendientesAsignacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEProductosPendientesAsignacionService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEProductosPendientesAsignacionServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Christian Gonzales</a>
 * 
 */
@Service("spusicc.mantenimientoAPEProductosPendientesAsignacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEProductosPendientesAsignacionServiceImpl extends BaseService implements MantenimientoAPEProductosPendientesAsignacionService {

	@Resource(name="spusicc.mantenimientoAPEProductosPendientesAsignacionDAO")
	private MantenimientoAPEProductosPendientesAsignacionDAO mantenimientoAPEProductosPendientesAsignacionDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEProductosPendientesAsignacionService#getOidPeriodobyMarcaCanal(java.util.Map)
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria) {
		return mantenimientoAPEProductosPendientesAsignacionDAO.getOidPeriodobyMarcaCanal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEProductosPendientesAsignacionService#getAnaquelesSinProductoAsignado(java.util.Map)
	 */
	public List getAnaquelesSinProductosAsignados(Map criteria) {
		return mantenimientoAPEProductosPendientesAsignacionDAO.getAnaquelesSinProductosAsignados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEProductosPendientesAsignacionService#getDescripconProductoAPE(java.util.Map)
	 */
	public String getDescripconProductoAPE(Map criteria){
		return mantenimientoAPEProductosPendientesAsignacionDAO.getDescripconProductoAPE(criteria);
	}

	@Override
	public List getProductosPendientesAsignacionList(Map criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}

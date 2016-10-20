package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEControlEnvioInterfacesWCSDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ControlEnvioWCS;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EstadoOlaWCS;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEControlEnvioInterfacesWCSService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEControlEnvioInterfacesWCSServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEControlEnvioInterfacesWCSService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEControlEnvioInterfacesWCSServiceImpl extends BaseService implements MantenimientoAPEControlEnvioInterfacesWCSService {

	@Resource(name="spusicc.mantenimientoAPEControlEnvioInterfacesWCSDAO")
	private MantenimientoAPEControlEnvioInterfacesWCSDAO mantenimientoAPEControlEnvioInterfacesWCSDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlEnvioInterfacesWCSService#getControlEnvioInterfacesList(java.util.Map)
	 */
	public List getControlEnvioInterfacesList(Map criteria) {
		return this.mantenimientoAPEControlEnvioInterfacesWCSDAO.getControlEnvioInterfacesList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlEnvioInterfacesWCSService#getControlEnvioWCSObject(java.util.Map)
	 */
	public ControlEnvioWCS getControlEnvioWCSObject(Map criteria) {
		return this.mantenimientoAPEControlEnvioInterfacesWCSDAO.getControlEnvioWCSObject(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlEnvioInterfacesWCSService#getValEstadoOla(java.util.Map)
	 */
	public EstadoOlaWCS getValEstadoOla(Map criteria) {
		return this.mantenimientoAPEControlEnvioInterfacesWCSDAO.getValEstadoOla(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlEnvioInterfacesWCSService#updateEstadoOlasxDia(java.util.Map)
	 */
	public void updateEstadoOlasxDia(Map criteria) {
		this.mantenimientoAPEControlEnvioInterfacesWCSDAO.updateEstadoOlasxDia(criteria);
	}
	
}

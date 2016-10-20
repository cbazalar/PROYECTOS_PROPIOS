package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConfiguracionBalanceoSubLineaDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConfiguracionBalanceoSubLinea;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEConfiguracionBalanceoSubLineaService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEConfiguracionBalanceoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicolas Lopez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEConfiguracionBalanceoSubLineaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEConfiguracionBalanceoSubLineaServiceImpl extends BaseService implements MantenimientoAPEConfiguracionBalanceoSubLineaService {

	@Resource(name="spusicc.mantenimientoAPEConfiguracionBalanceoSubLineaDAO")
	private MantenimientoAPEConfiguracionBalanceoSubLineaDAO mantenimientoAPEConfiguracionBalanceoSubLineaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoSubLineaService#getConfiguracionBlSublineaList(java.util.Map)
	 */
	public List getConfiguracionBlSublineaList(Map criteria) {
		return this.mantenimientoAPEConfiguracionBalanceoSubLineaDAO.getConfiguracionBlSublineaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoSubLineaService#getObtenerConfiguracionBalanceoSubLinea(java.util.Map)
	 */
	public ConfiguracionBalanceoSubLinea getObtenerConfiguracionBalanceoSubLinea(Map criteria) {
		return this.mantenimientoAPEConfiguracionBalanceoSubLineaDAO.getObtenerConfiguracionBalanceoSubLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoSubLineaService#actualizaConfiguracionSubLinea(java.util.Map)
	 */
	public void actualizaConfiguracionSubLinea(Map criteria) {
		this.mantenimientoAPEConfiguracionBalanceoSubLineaDAO.actualizaConfiguracionSubLinea(criteria);
	}

}

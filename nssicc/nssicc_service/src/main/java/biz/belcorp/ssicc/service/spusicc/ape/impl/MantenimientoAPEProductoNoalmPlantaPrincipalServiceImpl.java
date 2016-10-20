/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEProductoNoalmPlantaPrincipalDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.MantenimientoAPEProductoNoalmPlantaPrincipal;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEProductoNoalmPlantaPrincipalService;

/**
 * @author csoberon
 *
 */
@Service("spusicc.mantenimientoAPEProductoNoalmPlantaPrincipalService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEProductoNoalmPlantaPrincipalServiceImpl extends
		BaseService implements
		MantenimientoAPEProductoNoalmPlantaPrincipalService {

	@Resource(name="spusicc.mantenimientoAPEProductoNoalmPlantaPrincipalDAO")
	private MantenimientoAPEProductoNoalmPlantaPrincipalDAO mantenimientoAPEProductoNoalmPlantaPrincipalDAO;
		


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEProductoNoalmPlantaPrincipalService#getEmpresasExternas()
	 */
	public List getEmpresasExternas() {
		return mantenimientoAPEProductoNoalmPlantaPrincipalDAO.getEmpresasExternas();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEProductoNoalmPlantaPrincipalService#getProductosNoalmPlantaPrincipalByCriteria(java.util.Map)
	 */
	public List getProductosNoalmPlantaPrincipalByCriteria(Map params) {
		return mantenimientoAPEProductoNoalmPlantaPrincipalDAO.getProductosNoalmPlantaPrincipalByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEProductoNoalmPlantaPrincipalService#getProductoNoalmPlantaPrincipal(java.lang.String)
	 */
	public MantenimientoAPEProductoNoalmPlantaPrincipal getProductoNoalmPlantaPrincipal(String id) {
		return (MantenimientoAPEProductoNoalmPlantaPrincipal)mantenimientoAPEProductoNoalmPlantaPrincipalDAO.getProductoNoalmPlantaPrincipal(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEProductoNoalmPlantaPrincipalService#updateProductoNoalmPlantaPrincipal(biz.belcorp.ssicc.spusicc.ape.model.MantenimientoAPEProductoNoalmPlantaPrincipal)
	 */
	public void updateProductoNoalmPlantaPrincipal(
			MantenimientoAPEProductoNoalmPlantaPrincipal producto) {
		mantenimientoAPEProductoNoalmPlantaPrincipalDAO.updateProductoNoalmPlantaPrincipal(producto);
	}
	
	
}

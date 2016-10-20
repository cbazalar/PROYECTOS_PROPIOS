package biz.belcorp.ssicc.service.spusicc.dto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTODescuentoAdicionalDetalleDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTODescuentoAdicionalDetalleService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoDTODescuentoAdicionalDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoDTODescuentoAdicionalDetalleServiceImpl extends BaseService implements
	MantenimientoDTODescuentoAdicionalDetalleService {

	@Resource(name = "spusicc.mantenimientoDTODescuentoAdicionalDetalleDAO")
	MantenimientoDTODescuentoAdicionalDetalleDAO mantenimientoDTODescuentoAdicionalDetalleDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalDetalleService#getListDescuentoAdicionalDetalle(java.util.Map)
	 */
	public List getListDescuentoAdicionalDetalle(Map criteria) {
		return mantenimientoDTODescuentoAdicionalDetalleDAO.getListDescuentoAdicionalDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalDetalleService#getDescuentosAdicionales(java.util.Map)
	 */
	public List getDescuentosAdicionales(Map criteria) {
		return mantenimientoDTODescuentoAdicionalDetalleDAO.getDescuentosAdicionales(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalDetalleService#insertDescuentoAdicionalDetalle(java.util.Map)
	 */
	public void insertDescuentoAdicionalDetalle(Map params) {
		String codigoDetalle = mantenimientoDTODescuentoAdicionalDetalleDAO.getMaxDescuentoAdicionalDetalle();
		params.put("codigoDetalle", codigoDetalle);
		
		mantenimientoDTODescuentoAdicionalDetalleDAO.insertDescuentoAdicionalDetalle(params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalDetalleService#updateDescuentoAdicionalDetalle(java.util.Map)
	 */
	public void updateDescuentoAdicionalDetalle(Map params) {
		mantenimientoDTODescuentoAdicionalDetalleDAO.updateDescuentoAdicionalDetalle(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalDetalleService#deleteDescuentoAdicionalDetalle(java.util.Map)
	 */
	public void deleteDescuentoAdicionalDetalle(Map params) {
		mantenimientoDTODescuentoAdicionalDetalleDAO.deleteDescuentoAdicionalDetalle(params);
	}

	
}

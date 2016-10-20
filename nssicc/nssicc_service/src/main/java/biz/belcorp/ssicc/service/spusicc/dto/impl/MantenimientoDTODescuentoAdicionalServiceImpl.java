package biz.belcorp.ssicc.service.spusicc.dto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTODescuentoAdicionalDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTODescuentoAdicionalService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoDTODescuentoAdicionalService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoDTODescuentoAdicionalServiceImpl extends BaseService implements
	MantenimientoDTODescuentoAdicionalService {

	@Resource(name = "spusicc.mantenimientoDTODescuentoAdicionalDAO")
	MantenimientoDTODescuentoAdicionalDAO mantenimientoDTODescuentoAdicionalDAO;
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalService#getListDescuentoAdicional(java.util.Map)
	 */
	public List getListDescuentoAdicional(Map criteria) {
		return mantenimientoDTODescuentoAdicionalDAO.getListDescuentoAdicional(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalService#insertDescuentoAdicional(java.util.Map)
	 */
	public void insertDescuentoAdicional(Map params) {
		String codigoAdicional = mantenimientoDTODescuentoAdicionalDAO.getMaxDescuentoAdicional();
		params.put("codigoAdicional", codigoAdicional);
		
		mantenimientoDTODescuentoAdicionalDAO.insertDescuentoAdicional(params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalService#updateDescuentoAdicional(java.util.Map)
	 */
	public void updateDescuentoAdicional(Map params) {
		mantenimientoDTODescuentoAdicionalDAO.updateDescuentoAdicional(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalService#deleteDescuentoAdicional(java.util.Map)
	 */
	public void deleteDescuentoAdicional(Map params) {
		mantenimientoDTODescuentoAdicionalDAO.deleteDescuentoAdicional(params);
	}

	
}

package biz.belcorp.ssicc.service.spusicc.dto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTODescuentoAdicionalGrupoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTODescuentoAdicionalGrupoService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoDTODescuentoAdicionalGrupoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoDTODescuentoAdicionalGrupoServiceImpl extends BaseService implements
	MantenimientoDTODescuentoAdicionalGrupoService {

	@Resource(name = "spusicc.mantenimientoDTODescuentoAdicionalGrupoDAO")
	MantenimientoDTODescuentoAdicionalGrupoDAO mantenimientoDTODescuentoAdicionalGrupoDAO;
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalGrupoService#getListDescuentoAdicionalGrupo(java.util.Map)
	 */
	public List getListDescuentoAdicionalGrupo(Map criteria) {
		return mantenimientoDTODescuentoAdicionalGrupoDAO.getListDescuentoAdicionalGrupo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalGrupoService#insertDescuentoAdicionalGrupo(java.util.Map)
	 */
	public void insertDescuentoAdicionalGrupo(Map params) {
		mantenimientoDTODescuentoAdicionalGrupoDAO.insertDescuentoAdicionalGrupo(params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalGrupoService#updateDescuentoAdicionalGrupo(java.util.Map)
	 */
	public void updateDescuentoAdicionalGrupo(Map params) {
		mantenimientoDTODescuentoAdicionalGrupoDAO.updateDescuentoAdicionalGrupo(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTODescuentoAdicionalGrupoService#deleteDescuentoAdicionalGrupo(java.util.Map)
	 */
	public void deleteDescuentoAdicionalGrupo(Map params) {
		mantenimientoDTODescuentoAdicionalGrupoDAO.deleteDescuentoAdicionalGrupo(params);
	}

	
}

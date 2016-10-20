package biz.belcorp.ssicc.service.spusicc.dto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTOGrupoDescuentoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTOGrupoDescuentoService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoDTOGrupoDescuentoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoDTOGrupoDescuentoServiceImpl extends BaseService implements
	MantenimientoDTOGrupoDescuentoService {

	@Resource(name = "spusicc.mantenimientoDTOGrupoDescuentoDAO")
	MantenimientoDTOGrupoDescuentoDAO mantenimientoDTOGrupoDescuentoDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOGrupoDescuentoService#getListGrupoDescuento(java.util.Map)
	 */
	public List getListGrupoDescuento(Map criteria) {
		return mantenimientoDTOGrupoDescuentoDAO.getListGrupoDescuento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOGrupoDescuentoService#insertGrupoDescuento(java.util.Map)
	 */
	public void insertGrupoDescuento(Map params) {
		String codigoGrupo = mantenimientoDTOGrupoDescuentoDAO.getMaxGrupoDescuento();
		params.put("codigoGrupo", codigoGrupo);
		
		mantenimientoDTOGrupoDescuentoDAO.insertGrupoDescuento(params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOGrupoDescuentoService#updateGrupoDescuento(java.util.Map)
	 */
	public void updateGrupoDescuento(Map params) {
		mantenimientoDTOGrupoDescuentoDAO.updateGrupoDescuento(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOGrupoDescuentoService#deleteGrupoDescuento(java.util.Map)
	 */
	public void deleteGrupoDescuento(Map params) {
		mantenimientoDTOGrupoDescuentoDAO.deleteGrupoDescuento(params);
	}

	
}

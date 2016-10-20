package biz.belcorp.ssicc.service.spusicc.dto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTORangoDescuentoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTORangoDescuentoService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoDTORangoDescuentoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoDTORangoDescuentoServiceImpl extends BaseService implements
	MantenimientoDTORangoDescuentoService {

	@Resource(name = "spusicc.mantenimientoDTORangoDescuentoDAO")
	MantenimientoDTORangoDescuentoDAO mantenimientoDTORangoDescuentoDAO;
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTORangoDescuentoService#getListRangoDescuento(java.util.Map)
	 */
	public List getListRangoDescuento(Map criteria) {
		return mantenimientoDTORangoDescuentoDAO.getListRangoDescuento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTORangoDescuentoService#getGruposDescuento(java.util.Map)
	 */
	public List getGruposDescuento(Map criteria) {
		return mantenimientoDTORangoDescuentoDAO.getGruposDescuento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTORangoDescuentoService#insertRangoDescuento(java.util.Map)
	 */
	public void insertRangoDescuento(Map params) {
		String codigoGrupo = (String)params.get("codigoGrupo");
		String codigoRango = mantenimientoDTORangoDescuentoDAO.getMaxRangoDescuento(codigoGrupo);
		params.put("codigoRango", codigoRango);
		
		mantenimientoDTORangoDescuentoDAO.insertRangoDescuento(params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTORangoDescuentoService#updateRangoDescuento(java.util.Map)
	 */
	public void updateRangoDescuento(Map params) {
		mantenimientoDTORangoDescuentoDAO.updateRangoDescuento(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTORangoDescuentoService#deleteRangoDescuento(java.util.Map)
	 */
	public void deleteRangoDescuento(Map params) {
		mantenimientoDTORangoDescuentoDAO.deleteRangoDescuento(params);
	}

	
}

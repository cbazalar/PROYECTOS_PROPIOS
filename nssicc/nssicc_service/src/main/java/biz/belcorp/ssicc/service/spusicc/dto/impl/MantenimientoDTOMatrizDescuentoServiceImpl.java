package biz.belcorp.ssicc.service.spusicc.dto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTOMatrizDescuentoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTOMatrizDescuentoService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoDTOMatrizDescuentoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoDTOMatrizDescuentoServiceImpl extends BaseService implements
	MantenimientoDTOMatrizDescuentoService {

	@Resource(name = "spusicc.mantenimientoDTOMatrizDescuentoDAO")
	MantenimientoDTOMatrizDescuentoDAO mantenimientoDTOMatrizDescuentoDAO;
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#getListMatrizDescuento(java.util.Map)
	 */
	public List getListMatrizDescuento(Map criteria) {
		return mantenimientoDTOMatrizDescuentoDAO.getListMatrizDescuento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#getGruposDescuento(java.util.Map)
	 */
	public List getGruposDescuento(Map criteria) {
		return mantenimientoDTOMatrizDescuentoDAO.getGruposDescuento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#getCategoriasDescuento(java.util.Map)
	 */
	public List getCategoriasDescuento(Map criteria) {
		return mantenimientoDTOMatrizDescuentoDAO.getCategoriasDescuento(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#getTiposOferta(java.util.Map)
	 */
	public List getTiposOferta(Map criteria) {
		return mantenimientoDTOMatrizDescuentoDAO.getTiposOferta(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#getNegocios(java.util.Map)
	 */
	public List getNegocios(Map criteria) {
		return mantenimientoDTOMatrizDescuentoDAO.getNegocios(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#getUnidadesNegocio(java.util.Map)
	 */
	public List getUnidadesNegocio(Map criteria) {
		return mantenimientoDTOMatrizDescuentoDAO.getUnidadesNegocio(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#existeMatrizDescuento(java.util.Map)
	 */
	public boolean existeMatrizDescuento(Map criteria) {
		return mantenimientoDTOMatrizDescuentoDAO.existeMatrizDescuento(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#insertMatrizDescuento(java.util.Map)
	 */
	public void insertMatrizDescuento(Map params) {
		mantenimientoDTOMatrizDescuentoDAO.insertMatrizDescuento(params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#updateMatrizDescuento(java.util.Map)
	 */
	public void updateMatrizDescuento(Map params) {
		mantenimientoDTOMatrizDescuentoDAO.updateMatrizDescuento(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.service.MantenimientoDTOMatrizDescuentoService#deleteMatrizDescuento(java.util.Map)
	 */
	public void deleteMatrizDescuento(Map params) {
		mantenimientoDTOMatrizDescuentoDAO.deleteMatrizDescuento(params);
	}

	
}

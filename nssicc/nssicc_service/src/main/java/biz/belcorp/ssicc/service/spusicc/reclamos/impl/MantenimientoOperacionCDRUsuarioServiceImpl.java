/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoOperacionCDRUsuarioDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.OperacionCDRUsuario;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoOperacionCDRUsuarioService;

/**
 * @author Danny Amaro
 *
 */
@Service("spusicc.mantenimientoOperacionCDRUsuarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoOperacionCDRUsuarioServiceImpl extends BaseService implements MantenimientoOperacionCDRUsuarioService{
	
	@Resource(name="spusicc.mantenimientoOperacionCDRUsuarioDAO")
	MantenimientoOperacionCDRUsuarioDAO mantenimientoOperacionCDRUsuarioDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoOperacionCDRUsuarioService#getOperacionesCDRxUsuario(java.util.Map)
	 */
	public List getOperacionesCDRxUsuario(Map criteria) {		
		return this.mantenimientoOperacionCDRUsuarioDAO.getOperacionesCDRxUsuario(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoOperacionCDRUsuarioService#getOperacionesUsuario(java.util.Map)
	 */
	public List getOperacionesUsuario(Map criteria) {		
		return this.mantenimientoOperacionCDRUsuarioDAO.getOperacionesUsuario(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoOperacionCDRUsuarioService#insertOperacionCDRUsuario(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionCDRUsuario, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertOperacionCDRUsuario(
			OperacionCDRUsuario operacionCDRUsuario, Usuario usuario) {
		
		this.mantenimientoOperacionCDRUsuarioDAO.insertOperacionCDRUsuario(operacionCDRUsuario, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoOperacionCDRUsuarioService#insertOperacionCDRUsuarioAudit(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionCDRUsuario, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertOperacionCDRUsuarioAudit(
			OperacionCDRUsuario operacionCDRUsuario, Usuario usuario) {
		
		this.mantenimientoOperacionCDRUsuarioDAO.insertOperacionCDRUsuarioAudit(operacionCDRUsuario, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoOperacionCDRUsuarioService#removeOperacionCDRUsuario(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionCDRUsuario)
	 */
	public void removeOperacionCDRUsuario(
			Map criteria) {
		
		this.mantenimientoOperacionCDRUsuarioDAO.removeOperacionCDRUsuario(criteria);
		
	}

}
